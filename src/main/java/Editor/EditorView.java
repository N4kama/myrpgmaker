package Editor;

import FileExplorerPannel.FileExplorerView;
import Game.Map;
import Game.World;
import MapPannel.MapController;
import MapPannel.MapModel;
import MapPannel.MapView;
import SpritePannel.SpriteController;
import SpritePannel.SpriteModel;
import SpritePannel.SpriteView;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.Observer;

public class EditorView extends JFrame implements Observer {
    EditorModel model;

    JFrame frame;

    JMenuItem file_new;
    JMenuItem file_open;
    JMenuItem file_save;
    JMenuItem file_exit;
    JMenuItem edit_undo;
    JMenuItem edit_redo;
    JMenuItem add_tile;
    JMenuItem add_object;
    JMenuItem add_npc;

    JButton newButton;
    JButton openButton;
    JButton saveButton;
    JButton placeButton;
    JButton moveButton;
    JButton selectButton;
    JButton removeButton;
    JButton undoButton;
    JButton redoButton;
    JButton playButton;

    public EditorView(EditorModel model) {
        this.model = model;
        //Creating Frame
        frame = new JFrame("My RPG Maker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);

        //Creating Menu bar
        JMenuBar menuBar = create_menu_bar();
        //Creating Tool bar
        JToolBar toolBar = create_tool_bar();
        //creating Sprite pannel
        JTabbedPane spriteTab = create_spriteTab();
        //creating file explorer pannel
        JTabbedPane fileExplorerTab = create_fileExplorer();
        //creating Map pannel
        JTabbedPane mapTab = create_mapTab();

        //Adding every component to the frame
        //Menu
        frame.setJMenuBar(menuBar);
        //toolbar
        frame.add(toolBar, BorderLayout.NORTH);

        //Placing the different scrollPane in order into the main frame
        JSplitPane sprite_fileExplorer = new JSplitPane(JSplitPane.VERTICAL_SPLIT, spriteTab, fileExplorerTab);
        sprite_fileExplorer.setResizeWeight(0.5);
        JSplitPane panelsOrganization = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, sprite_fileExplorer, mapTab);
        panelsOrganization.setResizeWeight(0.5);
        frame.add(panelsOrganization); //only one JSplitPane should be added

        frame.setVisible(true);
    }

    private JTabbedPane create_mapTab() {
        //Creating world at program launch
        model.setWorld(new World("Asuma", System.getProperty("user.dir") + "resources/NPC/player.png"));
        String default_tile_path = System.getProperty("user.dir") + "/resources/backgroundTile/grass.png";
        model.gameWorld.addMap(new Map("Shikamaru", 100, 100, default_tile_path));

        //Init MVC
        MapModel mapModel = new MapModel(model.gameWorld.getMap(0));
        MapView mapView = new MapView(mapModel);
        MapController mapController = new MapController(mapModel, mapView);
        mapController.start();

        //Linking to Pane
        JScrollPane mapPane = new JScrollPane(mapView);
        mapPane.setPreferredSize(new Dimension(200, 200));

        //Joining all the panes into a Tab (bar)
        JTabbedPane mapTab = new JTabbedPane();
        mapTab.addTab("Map", mapPane);

        return mapTab;
    }

    private JTabbedPane create_fileExplorer() {

        FileExplorerView fileExplorerView = new FileExplorerView(new DefaultMutableTreeNode("Worlds"));
        //file explorer
        JScrollPane fileExplorerPane = new JScrollPane(fileExplorerView);

        //adding tabs
        JTabbedPane fileExplorerTab = new JTabbedPane();
        fileExplorerTab.addTab("World Selector", fileExplorerPane);

        return fileExplorerTab;
    }

    private JTabbedPane create_spriteTab() {
        //Init MVC
        SpriteModel backgroundSpriteModel = new SpriteModel(System.getProperty("user.dir") + "/resources/backgroundTile/", true, false, false);
        SpriteModel foregroundSpriteModel = new SpriteModel(System.getProperty("user.dir") + "/resources/foregroundObject/", false, false, false);
        SpriteModel NPCSpriteModel = new SpriteModel(System.getProperty("user.dir") + "/resources/npc/", false, true, false);
        SpriteModel PlayerSpriteModel = new SpriteModel(System.getProperty("user.dir") + "/resources/npc/", false, false, true);
        SpriteView backgroundSpriteView = new SpriteView(backgroundSpriteModel);
        SpriteView foregroundSpriteView = new SpriteView(foregroundSpriteModel);
        SpriteView NPCSpriteView = new SpriteView(NPCSpriteModel);
        SpriteView PlayerSpriteView = new SpriteView(PlayerSpriteModel);
        SpriteController backgroundSpriteController = new SpriteController(backgroundSpriteModel, backgroundSpriteView);
        SpriteController foregroundSpriteController = new SpriteController(foregroundSpriteModel, foregroundSpriteView);
        SpriteController NPCSpriteController = new SpriteController(NPCSpriteModel, NPCSpriteView);
        SpriteController PlayerSpriteController = new SpriteController(PlayerSpriteModel, PlayerSpriteView);
        backgroundSpriteController.start();
        foregroundSpriteController.start();
        NPCSpriteController.start();
        PlayerSpriteController.start();

        //Linking to Pane
        JScrollPane backgroundSpritePane = new JScrollPane(backgroundSpriteView);
        JScrollPane foregroundSpritePane = new JScrollPane(foregroundSpriteView);
        JScrollPane NPCSpritePane = new JScrollPane(NPCSpriteView);
        JScrollPane PlayerSpritePane = new JScrollPane(PlayerSpriteView);
        backgroundSpritePane.setPreferredSize(new Dimension(200,200));
        foregroundSpritePane.setPreferredSize(new Dimension(200,200));
        NPCSpritePane.setPreferredSize(new Dimension(200,200));
        PlayerSpritePane.setPreferredSize(new Dimension(200,200));

        //Adding tabs all together
        JTabbedPane spriteTab = new JTabbedPane();
        spriteTab.addTab("Background sprites", backgroundSpritePane);
        spriteTab.addTab("Foreground sprites", foregroundSpritePane);
        spriteTab.addTab("NPC sprites", NPCSpritePane);
        spriteTab.addTab("Player sprites", PlayerSpritePane);
        return spriteTab;
    }

    private JToolBar create_tool_bar() {
        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);
        ImageIcon new_I = get_icon("new.png");
        newButton = new JButton(new_I);
        toolBar.add(newButton);
        ImageIcon open_I = get_icon("open.png");
        openButton = new JButton(open_I);
        toolBar.add(openButton);
        ImageIcon save_i = get_icon("save.png");
        saveButton = new JButton(save_i);
        toolBar.add(saveButton);
        ImageIcon place_I = get_icon("place.png");
        placeButton = new JButton(place_I);
        toolBar.add(placeButton);
        ImageIcon move_I = get_icon("move.png");
        moveButton = new JButton(move_I);
        toolBar.add(moveButton);
        ImageIcon select_I = get_icon("select.png");
        selectButton = new JButton(select_I);
        toolBar.add(selectButton);
        ImageIcon remove_I = get_icon("remove.png");
        removeButton = new JButton(remove_I);
        toolBar.add(removeButton);
        ImageIcon undo_I = get_icon("undo.png");
        undoButton = new JButton(undo_I);
        toolBar.add(undoButton);
        ImageIcon redo_I = get_icon("redo.png");
        redoButton = new JButton(redo_I);
        toolBar.add(redoButton);
        ImageIcon play_I = get_icon("play.png");
        playButton = new JButton(play_I);
        toolBar.add(playButton);

        return toolBar;
    }

    private ImageIcon get_icon(String file) {
        return new ImageIcon(new ImageIcon(System.getProperty("user.dir") + "/resources/icons/" + file)
                .getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
    }

    private JMenuBar create_menu_bar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenu edit = new JMenu("Edit");
        JMenu sprite = new JMenu("Sprite");
        menuBar.add(file);
        menuBar.add(edit);
        menuBar.add(sprite);

        //Creating Menu Items
        //NEW//
        file_new = new JMenuItem("new");
        file_new.setMnemonic(KeyEvent.VK_M);
        file_new.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, Event.CTRL_MASK, true));
        file_new.setEnabled(true);
        file.add(file_new);
        //OPEN//
        file_open = new JMenuItem("open");
        file_open.setMnemonic(KeyEvent.VK_M);
        file_open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, Event.CTRL_MASK, true));
        file_open.setEnabled(true);
        file.add(file_open);
        //SAVE//
        file_save = new JMenuItem("save");
        file_save.setMnemonic(KeyEvent.VK_M);
        file_save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, Event.CTRL_MASK, true));
        file_save.setEnabled(true);
        file.add(file_save);
        //EXIT//
        file_exit = new JMenuItem("exit");
        file_exit.setMnemonic(KeyEvent.VK_M);
        file_exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, Event.CTRL_MASK, true));
        file_exit.setEnabled(true);
        file.add(file_exit);
        //UNDO//
        edit_undo = new JMenuItem("undo");
        edit_undo.setMnemonic(KeyEvent.VK_M);
        edit_undo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, Event.CTRL_MASK, true));
        edit_undo.setEnabled(true);
        edit.add(edit_undo);
        //REDO//
        edit_redo = new JMenuItem("redo");
        edit_redo.setMnemonic(KeyEvent.VK_M);
        edit_redo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, Event.CTRL_MASK, true));
        edit_redo.setEnabled(true);
        edit.add(edit_redo);
        //ADD_TILE//
        add_tile = new JMenuItem("add tile");
        add_tile.setEnabled(true);
        sprite.add(add_tile);
        //ADD_OBJECT//
        add_object = new JMenuItem("add object");
        add_object.setEnabled(true);
        sprite.add(add_object);
        //ADD_NPC//
        add_npc = new JMenuItem("add npc");
        add_npc.setEnabled(true);
        sprite.add(add_npc);
        return menuBar;
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
