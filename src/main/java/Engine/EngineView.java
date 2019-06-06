package Engine;

import Editor.EditorModel;
import Engine.Character.EngineObj;
import Game.Map;
import Game.Tile;
import MapPannel.MapModel;
import Utils.SpriteTools;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
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

        setBackground(Color.black);
        add(gamePanel);
        gamePanel.repaint();
        gamePanel.setVisible(true);
        setFocusable(true);
        requestFocusInWindow();

        createPauseMenu();

        for (EngineObj obj: model_.getGameWorld().getCurMap().getEngineObjs()) {

            Thread myThread = new Thread(() -> {
                while(true) {
                    model_.moveNPC(obj);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            });
            myThread.start();
        }
    }

    private void createPauseMenu() {
        menuPanel.removeAll();
        menuPanel.setBackground(Color.black);
        menuPanel.add(continueButton);
        menuPanel.add(exitButton);
        menuPanel.setVisible(false);
    }

    private void repaintPos(Graphics g, Position p) {
        Tile tile = map.getTile(p);
        if (tile != null) {
            BufferedImage i = SpriteTools.pathToImg.get(tile.get_path());
            g.drawImage(i, p.getX() * 16, p.getY() * 16, null);
        }
    }

    private void deletePos(Graphics g, Position p) {
        int x = (p.getX());
        int y = (p.getY());
        //System.out.println("x: " + x + " y: " + y);
        repaintPos(g, new Position(x, y));
        repaintPos(g, new Position(x + 1, y));
        repaintPos(g, new Position(x + 2, y));
        repaintPos(g, new Position(x - 1, y));
        repaintPos(g, new Position(x - 2, y));
        repaintPos(g, new Position(x, y - 1));
        repaintPos(g, new Position(x + 1, y - 1));
        repaintPos(g, new Position(x + 2, y - 1));
        repaintPos(g, new Position(x - 1, y - 1));
        repaintPos(g, new Position(x - 2, y - 1));
        repaintPos(g, new Position(x, y - 2));
        repaintPos(g, new Position(x + 1, y - 2));
        repaintPos(g, new Position(x + 2, y - 2));
        repaintPos(g, new Position(x - 1, y - 2));
        repaintPos(g, new Position(x - 2, y - 2));
        repaintPos(g, new Position(x, y + 2));
        repaintPos(g, new Position(x + 1, y + 2));
        repaintPos(g, new Position(x + 2, y + 2));
        repaintPos(g, new Position(x - 1, y + 2));
        repaintPos(g, new Position(x - 2, y + 2));
        repaintPos(g, new Position(x, y + 1));
        repaintPos(g, new Position(x + 1, y + 1));
        repaintPos(g, new Position(x + 2, y + 1));
        repaintPos(g, new Position(x - 1, y + 1));
        repaintPos(g, new Position(x - 2, y + 1));
    }

    public void paintComponent(Graphics g, EngineObj obj) {
        map_view.drayObjects(this.getGraphics());
        remove(gamePanel);
    }

    @Override
    public void update(Observable o, Object arg) {
        if(arg != null)
        {
            EngineObj obj = (EngineObj) arg;
            if(obj == null)
            {
                return;
            }
            if (obj.isAlive() && !inMenu) {
                obj.getEs().getCurAnim().update();
                revalidate();
                deletePos(this.getGraphics(), new Position(obj.get_x(), obj.get_y()));
                paintComponent(this.getGraphics(), obj);
                if(obj.getTeleported())
                {
                    obj.setTeleported(false);
                    deletePos(this.getGraphics(), obj.getTeleportedPos());
                }
            }
            return;
        }
        else {
            System.out.println("diplay pause menu");
            displayPauseMenu();
        }
    }

    public void displayPauseMenu() {
        inMenu = true;
        gamePanel.setVisible(false);
        remove(gamePanel);
        menuPanel.setVisible(true);
        setContentPane(menuPanel);
    }

    public void displayGame() {
        inMenu = false;
        menuPanel.setVisible(false);
        remove(menuPanel);
        gamePanel.setVisible(true);
        setContentPane(gamePanel);
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
