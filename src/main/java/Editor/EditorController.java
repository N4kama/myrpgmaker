package Editor;

import Engine.Character.EngineObj;
import Engine.EngineController;
import Engine.EngineModel;
import Engine.EngineView;
import Game.Map;
import Utils.SpriteTools;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Observable;
import java.util.Observer;

import static Utils.WorldTools.loadWorld;
import static Utils.WorldTools.saveWorld;

public class EditorController extends Observable {
    private EditorModel model;
    private EditorView view;

    public EditorController(EditorModel model, EditorView v) {
        this.model = model;
        this.view = v;
        this.addObserver(view.backgroundSpriteController.model);
        this.addObserver(view.foregroundSpriteController.model);
        this.addObserver(view.NPCSpriteController.model);
        this.addObserver(view.PlayerSpriteController.model);
        set_controls();
    }

    public void set_controls() {

        //set listener for menu item
        view.file_open.addActionListener(open_action());
        view.file_save.addActionListener(save_action());
        view.file_new.addActionListener(new_action());
        view.edit_undo.addActionListener(undo_action());
        view.edit_redo.addActionListener(redo_action());
        view.file_exit.addActionListener(actionEvent -> view.frame.dispose());
        view.add_tile.addActionListener(add_tiles_action());
        view.add_object.addActionListener(add_object_action());
        view.add_npc.addActionListener(add_npc_action());
        view.add_player.addActionListener(add_player_action());
        //set listener for toolbar
        view.openButton.addActionListener(open_action());
        view.saveButton.addActionListener(save_action());
        view.newButton.addActionListener(new_action());
        view.undoButton.addActionListener(undo_action());
        view.redoButton.addActionListener(redo_action());
        view.playButton.addActionListener(play_action());
        view.moveButton.addActionListener(move_action());
        view.placeButton.addActionListener(place_action());
        view.selectButton.addActionListener(select_action());
        view.removeButton.addActionListener(remove_action());
        view.gridButton.addActionListener(grid_action());
        view.add_map.addActionListener(addMap_action());
        view.walkableButton.addActionListener(walkableAction());
        view.not_walkableButton.addActionListener(not_walkableButtonAction());
    }

    private ActionListener grid_action() {
        return ActionEvent -> model.toggleGrid();
    }

    private ActionListener not_walkableButtonAction() {
        return ActionEvent -> {
            SpriteTools.mousePointerState = SpriteTools.MousePointerState.SET_WALKABLE_OR_NOT;
            SpriteTools.walkable = false;
            view.not_walkableButton.setOpaque(false);
            view.walkableButton.setOpaque(true);
        };
    }

    private ActionListener walkableAction() {
        return ActionEvent -> {
            SpriteTools.mousePointerState = SpriteTools.MousePointerState.SET_WALKABLE_OR_NOT;
            SpriteTools.walkable = true;
            view.walkableButton.setOpaque(false);
            view.not_walkableButton.setOpaque(true);
        };
    }

    private ActionListener addMap_action() {
        return ActionEvent -> {
            int height = 100;
            int width = 100;
            String default_tile_path = System.getProperty("user.dir") + "/resources/backgroundTile/grass.png";
            //popup asking for width  and height
            String w = JOptionPane.showInputDialog("enter map width");
            width = Integer.parseInt(w);
            String h = JOptionPane.showInputDialog("enter map height");
            height = Integer.parseInt(h);
            model.add_map(width, height, default_tile_path);
        };
    }

    private ActionListener add_player_action() {
        return add_action("resources/player/");
    }

    private ActionListener remove_action() {
        return ActionEvent -> SpriteTools.mousePointerState = SpriteTools.MousePointerState.DELETE;
    }

    private ActionListener select_action() {
        return ActionEvent -> SpriteTools.mousePointerState = SpriteTools.MousePointerState.SELECT;
    }

    private ActionListener place_action() {
        return ActionEvent -> SpriteTools.mousePointerState = SpriteTools.MousePointerState.PLACE;
    }

    private ActionListener move_action() {
        return ActionEvent -> SpriteTools.mousePointerState = SpriteTools.MousePointerState.MOVE;
    }


    private ActionListener play_action() {
        return actionEvent -> {
            view.frame.setVisible(false);
            view.frame.dispose();
            //EngineObj charact = new EngineObj(0, 0, "path to char");

            Map map = model.gameWorld.getCurMap();

            //model.gameWorld.setPlayer(charact);
            EngineModel engineModel = new EngineModel(model.gameWorld);
            EngineView engineView = new EngineView(engineModel);
            engineModel.addObserver(engineView);
            EngineController engineCtrl = new EngineController(engineModel, engineView);
            engineCtrl.set_controls();
        };
    }


    private ActionListener redo_action() {
        return actionEvent -> {
            // redo
        };
    }

    private ActionListener undo_action() {
        return actionEvent -> {
            // undo
        };
    }

    private ActionListener new_action() {
        return actionEvent -> {
            // comportement new boutton
        };
    }

    private ActionListener add_tiles_action() {
        return add_action("resources/backgroundTile/");
    }

    private ActionListener add_object_action() {
        return add_action("resources/foregroundObject/");
    }

    private ActionListener add_npc_action() {
        return add_action("resources/npc/");
    }

    private ActionListener add_action(String destPath) {
        return actionEvent -> {
            JFileChooser fc = new JFileChooser();
            fc.setDialogTitle("Choose a file to import");
            int returnVal = fc.showSaveDialog(view);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                Path filePath = Paths.get(file.getPath());

                try {
                    BufferedImage img = ImageIO.read(file);
                    if ((img.getHeight() / 16 == 1 && img.getWidth() / 16 == 1 && destPath.equals("resources/backgroundTile/")) ||
                            (img.getHeight() % 16 == 0 && img.getWidth() % 16 == 0 && (destPath.equals("resources/foregroundObject/")) || (destPath.equals("resources/npc/")))) {
                        Files.copy(filePath, (new File(destPath + file.getName()).toPath()),
                                StandardCopyOption.REPLACE_EXISTING);
                        setChanged();
                        notifyObservers(destPath + file.getName());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
    }

    private ActionListener save_action() {
        return actionEvent -> {
            JFileChooser fc = new JFileChooser();
            fc.setDialogTitle("Choose a directory to save your world");
            int returnVal = fc.showSaveDialog(view);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                String filePath = file.getPath();
                //model save
                try {
                    saveWorld(filePath, model.gameWorld);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
    }

    private ActionListener open_action() {
        return actionEvent -> {
            JFileChooser fc = new JFileChooser();
            fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fc.setDialogTitle("Choose a world to load");
            int returnValue = fc.showOpenDialog(view);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fc.getSelectedFile();
                String filePath = selectedFile.getPath();

                // model open
                try {
                    model.gameWorld = loadWorld(filePath);
                    model.setWorld(model.gameWorld);
                    //view.update();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        };
    }
}

