package Utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class SpriteTools {
    public enum MousePointerState {
        PLACE, MOVE, SELECT, DELETE, SET_WALKABLE_OR_NOT,
    }

    public static HashMap<BufferedImage, String> imgToPath = new HashMap<>();
    public static HashMap<String, BufferedImage> pathToImg = new HashMap<>();
    public static boolean playerModelSelected = false;
    public static boolean npcModelSelected = false;
    public static boolean is_background = true;
    public static boolean grid_display = false;
    public static String selectedSprite = null;
    public static boolean walkable = true;
    public static MousePointerState mousePointerState = MousePointerState.PLACE;

    public static void registerSprite(BufferedImage img, String path) {
        imgToPath.put(img, path);
        pathToImg.put(path, img);
    }

    public static BufferedImage openTile(String path) {
        BufferedImage img = pathToImg.get(path);
        if(img == null) 
        {
            try {
                img = ImageIO.read(SpriteTools.class.getResourceAsStream(path));
                registerSprite(img, path);
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
      return img;
    }

    public static BufferedImage openObject(String path) {
        BufferedImage img = pathToImg.get(path);
        if(img == null) 
        {
            try {
                img = ImageIO.read(SpriteTools.class.getResource(path));
                registerSprite(img, path);
                return img;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
      return img;
    }

    public static void setSpriteMove(BufferedImage img, ArrayList<BufferedImage> arr, String move) {
        if (move.equals("left")) {
            arr.add(img.getSubimage(0 * 16, 1 * 24, 16, 24));
            arr.add(img.getSubimage(2 * 16, 1 * 24, 16, 24));
        } else if (move.equals("right")) {
            arr.add(img.getSubimage(0 * 16, 2 * 24, 16, 24));
            arr.add(img.getSubimage(2 * 16, 2 * 24, 16, 24));
        } else if (move.equals("up")) {
            arr.add(img.getSubimage(0 * 16, 3 * 24, 16, 24));
            arr.add(img.getSubimage(2 * 16, 3 * 24, 16, 24));
        } else if (move.equals("down")) {
            arr.add(img.getSubimage(0 * 16, 0 * 24, 16, 24));
            arr.add(img.getSubimage(2 * 16, 0 * 24, 16, 24));
        }
    }

    public static BufferedImage getStandingSprite(BufferedImage img, int x, int y) {
        return img.getSubimage(x * 16, y * 24, 16, 24);
    }
}
