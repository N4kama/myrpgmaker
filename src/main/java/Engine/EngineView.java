package Engine;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;


public class EngineView extends JFrame implements Observer {
    private EngineModel model_;
    JButton startButton;
    JButton exitButton;
    JButton continueButton;
    JPanel gamePanel;
    JPanel menuPanel;
    JPanel savePanel;

    BufferedImage gameImage;


    public EngineView(EngineModel m) {
        this.model_ = m;
        try {
            displayMenu(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void displayMenu(int pause) throws IOException {
        File f = new File("resources/icons/game.png");
        gameImage = ImageIO.read(f);
        setSize(gameImage.getWidth(), gameImage.getHeight());

        gamePanel = new JPanel();

        gamePanel.setLayout(new GridBagLayout());

        menuPanel = create_menuPanel(this.getSize());

        savePanel = (JPanel) getContentPane();

        startButton = new JButton("Start");
        exitButton = new JButton("Exit");
        continueButton = new JButton("Continue");


        menuPanel.add(startButton);
        menuPanel.add(exitButton);

        setContentPane(menuPanel);

        setVisible(true);
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

    public void exitGame() {
        this.dispose();
    }

    public void startGame() {

    }
}
