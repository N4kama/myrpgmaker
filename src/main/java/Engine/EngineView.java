package Engine;

import Engine.Character.EngineObj;
import Game.Map;
import Game.Tile;
import MapPannel.MapModel;
import MapPannel.MapView;
import Utils.SpriteTools;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
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
    JPanel progressPanel;

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
        setSize(600,600);

        MapModel mapModel = new MapModel(model_.getGameWorld().getCurMap());
        map_view = new EngineMapView(mapModel);
        gamePanel = (JPanel) map_view;
        progressPanel = new JPanel();
        progressPanel.setLayout(new GridBagLayout());

        JProgressBar jProgressBar = new JProgressBar();
        Border border = BorderFactory.createTitledBorder("Loading in progress");
        jProgressBar.setBorder(border);
        jProgressBar.setValue(0);
        jProgressBar.setStringPainted(true);

        progressPanel.add(jProgressBar);
        add(progressPanel);
        setVisible(true);

        Runnable r = new Runnable() {
            @Override
            public void run() {
                Timer timer = new Timer(10, new ActionListener() {
                    float p = 0;
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        if (p != 101) {
                            jProgressBar.setValue((int)p);
                            p += 0.5;
                        } else {
                            Timer t = (Timer) actionEvent.getSource();
                            t.stop();
                            jProgressBar.setVisible(false);
                            remove(progressPanel);

                            setBackground(Color.black);
                            add(gamePanel);
                            gamePanel.repaint();
                            gamePanel.setVisible(true);
                            setFocusable(true);
                            requestFocusInWindow();
                        }
                    }
                });
                timer.start();
            }
        };
        r.run();
    }

    /*
    public void deleteEngineOBJ(Graphics g, EngineObj obj) {
        Tile tile = this.model_.getGameWorld().getCurMap().getGameTile(obj.get_y() -
                this.map_view.curAnim.getSprite().getHeight() / 2,obj.get_x()
                    - this.map_view.curAnim.getSprite().getWidth() / 2);
        g.drawImage(SpriteTools.pathToImg.get(tile.get_path()), tile.get_x(), tile.get_y(), null);
    }
    */


    private void deleteEngineOBJ(Graphics g, EngineObj obj) {
        int x = (obj.get_x() - (obj.get_x() % 16)) / 16;
        int y = (obj.get_y() - (obj.get_y() % 16)) / 16;
        System.out.println("x: " + x + " y: " + y);
        Position p = new Position(x, y);
        Tile tile = map.getTile(p);
        System.out.println(tile.get_path());
        if (tile != null) {
            BufferedImage i = SpriteTools.pathToImg.get(tile.get_path());
            g.drawImage(i, x * 16, y * 16, null);
            g.drawImage(i, (x + 1) * 16, y * 16, null);
            g.drawImage(i, (x + 1) * 16, (y + 1) * 16, null);
            g.drawImage(i, (x + 1) * 16, (y + 2) * 16, null);
            g.drawImage(i, (x + 1) * 16, (y - 2) * 16, null);
            g.drawImage(i, (x + 1) * 16, (y - 1) * 16, null);
            g.drawImage(i, (x + 2) * 16, (y - 1) * 16, null);
            g.drawImage(i, (x + 2) * 16, (y - 2) * 16, null);

            g.drawImage(i, x * 16, (y - 1) * 16, null);
            g.drawImage(i, x * 16, (y - 2) * 16, null);
            g.drawImage(i, x * 16, (y + 1) * 16, null);
            g.drawImage(i, x * 16, (y + 2) * 16, null);

            g.drawImage(i, (x - 1) * 16, y * 16, null);
            g.drawImage(i, (x - 2) * 16, y * 16, null);
            g.drawImage(i, (x - 1) * 16, (y + 1) * 16, null);
            g.drawImage(i, (x - 2) * 16, (y + 1) * 16, null);
            g.drawImage(i, (x - 1) * 16, (y + 2) * 16, null);
            g.drawImage(i, (x - 1) * 16, (y - 1) * 16, null);
            g.drawImage(i, (x - 1) * 16, (y - 2) * 16, null);
            g.drawImage(i, (x - 2) * 16, (y - 1) * 16, null);
        }
    }

    public void paintComponent(Graphics g, EngineObj obj) {
        g.drawImage(this.map_view.curAnim.getSprite(), obj.get_x() - this.map_view.curAnim.getSprite().getHeight()
                / 2, obj.get_y() - this.map_view.curAnim.getSprite().getWidth() / 2, null);
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("UPDATE!!!");
        map_view.curAnim.update();
        revalidate();
        deleteEngineOBJ(this.getGraphics(), this.model_.getGameWorld().player_);
        paintComponent(this.getGraphics(), this.model_.getGameWorld().player_);
    }

    private JPanel create_menuPanel(Dimension d) {
        return new JPanel() {
            @Override
            protected void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                g.drawImage(gameImage, 0, 0 , null);
            }
        };
    }
/*
    public void displayPauseMenu() {
        inMenu=true;
        remove(gamePanel);
        add(menuPanel);
    }*/
}
