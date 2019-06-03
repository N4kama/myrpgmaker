package SpritePannel;

import Utils.SpriteTools;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class SpriteModel {

    private boolean is_background;
    private boolean npc;
    private boolean player;
    private String path;

    public ArrayList<BufferedImage> sprites = new ArrayList<>();

    public SpriteModel(String path, boolean is_background, boolean npc, boolean player) {
        this.path = path;
        this.is_background = is_background;
        this.npc = npc;
        this.player = player;
        loadSprites(path);
    }

    private void loadSprites(String path) {
        File directory = new File(path);
        String check_ext = null;
        if (path.contains("npc"))
            check_ext = ".npc";
        for (File file : directory.listFiles()) {
            if (!file.isDirectory()) {
                try {
                    if (check_ext != null && !file.getName().contains(check_ext))
                        continue;
                    BufferedImage img = ImageIO.read(file);
                    sprites.add(img);
                    SpriteTools.registerSprite(img, file.getPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public boolean is_background() {
        return is_background;
    }

    public boolean isNpc() {
        return npc;
    }

    public boolean isPlayer() {
        return player;
    }
}
