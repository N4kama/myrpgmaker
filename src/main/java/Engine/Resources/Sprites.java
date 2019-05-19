package Engine.Resources;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class Sprites {
    public static HashMap<String, BufferedImage> paths = new HashMap<>();
    public static HashMap<BufferedImage, String> imgs = new HashMap<>();

    public static void addImage(String path) {
        try {
            BufferedImage img = ImageIO.read(new File(path));
            String imagePath = path.replace("\\", "/");
            Sprites.paths.put(imagePath, img);
            Sprites.imgs.put(img, imagePath);
        } catch (Exception e) {
            //
        }
    }
}