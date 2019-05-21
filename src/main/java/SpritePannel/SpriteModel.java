package SpritePannel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class SpriteModel {

    private boolean is_background;
    private String path;

    public ArrayList<BufferedImage> sprites = new ArrayList<>();

    public SpriteModel(String path, boolean is_background) {
        this.path = path;
        this.is_background = is_background;
        loadSprites(path);
    }

    private void loadSprites(String path) {
        File directory = new File(path);
        for (File file : directory.listFiles()) {
            if (!file.isDirectory()) {
                try {
                    sprites.add(ImageIO.read(file));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
