package Engine;

import Editor.EditorModel;
import Engine.Character.EngineObj;
import Game.Map;
import Game.Tile;
import MapPannel.MapModel;
import Utils.SpriteTools;
import Utils.WorldTools;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class EngineView extends JFrame implements Observer {
    public boolean inMenu = true;
    public Map map;
    public EngineMapView map_view;
    private EngineModel model_;
    JButton startButton;
    JButton exitButton;
    JButton continueButton;
    JPanel gamePanel;
    JPanel menuPanel;
    JPanel textPanel;
    private JPanel savePanel;
    private JLabel label;

    BufferedImage gameImage;

    public EngineView(EngineModel m) {
        this.model_ = m;
        this.map = m.getGameWorld().getCurMap();
        try {
            displayStartMenu(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void displayStartMenu(int pause) throws IOException {
        setResizable(false);
        File f = new File("resources/icons/game.png");
        gameImage = ImageIO.read(f);
        setSize(gameImage.getWidth(), gameImage.getHeight());

        menuPanel = create_menuPanel(this.getSize());

        gamePanel = (JPanel) getContentPane();

        startButton = new JButton("Start");
        exitButton = new JButton("Exit");
        continueButton = new JButton("Continue");

        menuPanel.add(startButton);
        menuPanel.add(exitButton);

        setContentPane(menuPanel);

        setVisible(true);
    }

    public void exitGame() {
        this.dispose();
    }

    public void startGame() {
        inMenu = false;
        remove(menuPanel);
        setResizable(true);
        setContentPane(gamePanel);
        setTitle(this.model_.getGameWorld().getName());
        setSize(600, 600);

        MapModel mapModel = new MapModel(model_.getGameWorld().getCurMap(), EditorModel.singleton);
        map_view = new EngineMapView(mapModel);
        gamePanel = (JPanel) map_view;
        label = new JLabel("");
        label.setVisible(true);
        add(label, "North");

        setBackground(Color.black);
        gamePanel.repaint();
        gamePanel.setVisible(true);
        add(gamePanel);
        setFocusable(true);
        requestFocusInWindow();

        createPauseMenu();

        // Thread myThread = new Thread(() -> {
        // while (true) {
        // for (EngineObj obj : model_.getGameWorld().getCurMap().getEngineObjs()) {
        // model_.moveNPC(obj);
        // }
        // try {
        // Thread.sleep(1000);
        // } catch (InterruptedException ex) {
        // ex.printStackTrace();
        // }
        // }
        // });
        // myThread.start();
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (EngineObj obj : model_.getGameWorld().getCurMap().getEngineObjs()) {
                    model_.moveNPC(obj);
                }
            }
        });
        timer.start();
        map_view.drawTiles(this.getGraphics());
        map_view.drayObjects(this.getGraphics());
    }

    private void createPauseMenu() {
        menuPanel = new JPanel();
        menuPanel.setLayout(new GridBagLayout());
        menuPanel.setBackground(Color.black);
        menuPanel.add(continueButton);
        menuPanel.add(exitButton);

        menuPanel.setVisible(true);
    }

    private void repaintPos(Graphics g, Position p) {
        Tile tile = map.getTile(p);
        if (tile != null) {
            BufferedImage i = SpriteTools.openTile(tile.get_path());
            g.drawImage(i, p.getX() * 16, p.getY() * 16, null);
        }
    }

    private void deletePos(Graphics g, Position p) {
        int x = (p.getX());
        int y = (p.getY());
        repaintPos(g, new Position(x, y));
        repaintPos(g, new Position(x + 1, y));
        repaintPos(g, new Position(x - 1, y));
        repaintPos(g, new Position(x, y - 1));
        repaintPos(g, new Position(x + 1, y - 1));
        repaintPos(g, new Position(x - 1, y - 1));
        repaintPos(g, new Position(x, y - 2));
        repaintPos(g, new Position(x, y + 1));
        repaintPos(g, new Position(x + 1, y + 1));
        repaintPos(g, new Position(x - 1, y + 1));
    }

    public void paintComponent(Graphics g, EngineObj obj) {
        map_view.drayObjects(this.getGraphics());
        remove(gamePanel);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg != null) {
            if (arg instanceof String) {
                String str = (String) arg;
                if (str.equals("startTalk")) {
                    displayDialog();
                } else if (str.equals("stopTalk")) {
                    closeDialog();
                }

            } else {
                EngineObj obj = (EngineObj) arg;
                if (obj == null) {
                    return;
                }
                if (obj.isIs_player() && !inMenu && obj.getChangedMap()) {
                    this.map = EditorModel.singleton.gameWorld.getCurMap();
                    super.paint(this.getGraphics());
                    this.getGraphics().setColor(Color.BLACK);
                    map_view.setBackground(Color.BLACK);
                    this.getGraphics().fillRect(0, 0, 0, 0);
                    map_view.mapModel.map = EditorModel.singleton.gameWorld.getCurMap();
                    map_view.drawTiles(this.getGraphics());
                    map_view.drayObjects(this.getGraphics());
                    obj.setChangedMap(false);
                    // paindre nouvelles map;
                } else if (obj.isAlive() && !inMenu) {
                    obj.getEs().getCurAnim().update();
                    revalidate();
                    deletePos(this.getGraphics(), new Position(obj.get_x(), obj.get_y()));
                    paintComponent(this.getGraphics(), obj);
                    if (obj.getTeleported()) {
                        obj.setTeleported(false);
                        deletePos(this.getGraphics(), obj.getTeleportedPos());
                    }
                }
                return;
            }
        } else {
            System.out.println("display pause menu");
            displayPauseMenu();
        }
    }

    public void displayDialog() {
        String msg = null;
        try {
            msg = model_.getGameWorld().player_.getTalkTo().getDialog();
        } catch (NullPointerException e) {
            label.setText("");
        }
        if (msg == null)
            label.setText("");
        else {
            label.setText("NPC: " + msg);
            label.revalidate();
            label.repaint();
        }
    }

    public void closeDialog() {
        eraseDialogBox(this.getGraphics());
    }

    public void eraseDialogBox(Graphics g) {
        int x = (this.getWidth() + (this.getWidth() % 16)) / 16;
        for (int i = 0; i < x; i++) {
            repaintPos(g, new Position(i, 0));
        }
    }

    public void displayPauseMenu() {
        inMenu = true;
        label.setText("Game Paused");
        add(menuPanel);
        revalidate();
        repaint();
    }

    public void displayGame() {
        inMenu = false;

        remove(menuPanel);
        map_view.drawTiles(this.getGraphics());
        map_view.drayObjects(this.getGraphics());
        eraseDialogBox(this.getGraphics());
    }

    private JPanel create_menuPanel(Dimension d) {
        return new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(gameImage, 0, 0, null);
            }
        };
    }
    /*
     * public void displayPauseMenu() { inMenu=true; remove(gamePanel);
     * add(menuPanel); }
     */

    /**
     * @return EngineModel return the model_
     */
    public EngineModel getModel_() {
        return model_;
    }

    /**
     * @param model_ the model_ to set
     */
    public void setModel_(EngineModel model_) {
        this.model_ = model_;
    }

}
