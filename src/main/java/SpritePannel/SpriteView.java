package SpritePannel;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;

public class SpriteView extends JPanel implements Observer {

    private SpriteModel model;

    public int pad_x = 10;// '5'for spacing between sprites
    public int pad_y = 10;

    public SpriteView(SpriteModel model) {
        this.model = model;
        this.model.addObserver(this);
        setPreferredSize(new Dimension(1000, 1000));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = 0, y = 0;
        int maxHeight = 0;
        for (BufferedImage img : model.sprites) {
            BufferedImage sub = img;
            try {
                if(model.isNpc() || model.isPlayer())
                img = img.getSubimage(16, 0, 16, 24);
            } catch (Exception e) {
                //TODO: handle exception
            }
            g.drawImage(sub, x + pad_x, y + pad_y, null);
            if (x + sub.getWidth() > getWidth()) {
                y += maxHeight;
                x = 0;
              }
            else {
                x += sub.getWidth() + pad_x;
                maxHeight = Math.max(maxHeight, sub.getHeight());
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        repaint();
        revalidate();
    }
}
