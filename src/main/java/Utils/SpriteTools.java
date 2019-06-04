package Utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class SpriteTools {
    private static final int TILE_SIZE = 32;
    public enum MousePointerState {
        PLACE,
        MOVE,
        SELECT,
        DELETE,
    }

    public static HashMap<BufferedImage, String> imgToPath = new HashMap<>();
    public static HashMap<String, BufferedImage> pathToImg = new HashMap<>();
    public static boolean playerModelSelected = false;
    public static boolean is_background = true;
    public static String selectedSprite = null;
    public static MousePointerState mousePointerState = MousePointerState.PLACE;

    public static void registerSprite(BufferedImage img, String path) {
        imgToPath.put(img, path);
        pathToImg.put(path, img);
    }


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

    public static BufferedImage openObject(String path) {
        try {
            BufferedImage img = ImageIO.read(new File(path));
            return img;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void setSpriteMove(BufferedImage img, ArrayList<BufferedImage> arr, String move) {
        if (move.equals("left")) {
            arr.add(img.getSubimage(0 * TILE_SIZE, 1 * TILE_SIZE, TILE_SIZE, TILE_SIZE));
            arr.add(img.getSubimage(1 * TILE_SIZE, 1 * TILE_SIZE, TILE_SIZE, TILE_SIZE));
            arr.add(img.getSubimage(2 * TILE_SIZE, 1 * TILE_SIZE, TILE_SIZE, TILE_SIZE));
        }
        else if (move.equals("right")) {
            arr.add(img.getSubimage(0 * TILE_SIZE, 2 * TILE_SIZE, TILE_SIZE, TILE_SIZE));
            arr.add(img.getSubimage(1 * TILE_SIZE, 2 * TILE_SIZE, TILE_SIZE, TILE_SIZE));
            arr.add(img.getSubimage(2 * TILE_SIZE, 2 * TILE_SIZE, TILE_SIZE, TILE_SIZE));
        }
        else if (move.equals("up")) {
            arr.add(img.getSubimage(0 * TILE_SIZE, 3 * TILE_SIZE, TILE_SIZE, TILE_SIZE));
            arr.add(img.getSubimage(1 * TILE_SIZE, 3 * TILE_SIZE, TILE_SIZE, TILE_SIZE));
            arr.add(img.getSubimage(2 * TILE_SIZE, 3 * TILE_SIZE, TILE_SIZE, TILE_SIZE));
        }
        else if (move.equals("down")) {
            arr.add(img.getSubimage(0 * TILE_SIZE, 0 * TILE_SIZE, TILE_SIZE, TILE_SIZE));
            arr.add(img.getSubimage(1 * TILE_SIZE, 0 * TILE_SIZE, TILE_SIZE, TILE_SIZE));
            arr.add(img.getSubimage(2 * TILE_SIZE, 0 * TILE_SIZE, TILE_SIZE, TILE_SIZE));
        }
    }
}
