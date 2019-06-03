package Engine;

import MapPannel.MapModel;
import MapPannel.MapView;

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
        MapView mapView = new MapView(mapModel);
        gamePanel = (JPanel) mapView;
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


    @Override
    public void update(Observable observable, Object o) {

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
