package Utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SpriteTools {

    public static BufferedImage openTile(String path) {
        try {
            BufferedImage img = ImageIO.read(new File(path));
            if (img.getWidth() != 16 || img.getHeight() != 16) {
                System.out.println("Wrong file format : " + path);
                return null;
            }
            return img;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
