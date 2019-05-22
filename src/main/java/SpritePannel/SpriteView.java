package SpritePannel;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;

public class SpriteView extends JPanel implements Observer {

    private SpriteModel model;

    public int printing_spacing_value = 5;// '5'for spacing between sprites

    public SpriteView(SpriteModel model) {
        this.model = model;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = 0, y = 0;
        int maxHeight = 0;
        for (BufferedImage img : model.sprites) {
            g.drawImage(img, x, y, null);
            if (x + img.getWidth() > getWidth()) {
                y += maxHeight;
                x = 0;
                maxHeight = 0;
            }
            else {
                x += img.getWidth() + printing_spacing_value;
                maxHeight = Math.max(maxHeight, img.getHeight());
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
