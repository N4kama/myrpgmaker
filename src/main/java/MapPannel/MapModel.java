package MapPannel;

import Game.GameMap;
import Utils.SpriteTools;

import java.awt.image.BufferedImage;
import java.util.Observable;

public class MapModel extends Observable {
    public GameMap map;

    public MapModel(GameMap map) {
        this.map = map;
    }

    public int getWidth() {
        return map.width;
    }

    public int getHeight() {
        return map.height;
    }

    public void modifySprite(int x, int y) {
        if (SpriteTools.selectedSprite == null)
            return;
        if (SpriteTools.is_background) {
            map.setTile(x / 16, y / 16, SpriteTools.selectedSprite);
        } else {
            //do it for foreground
        }
        System.out.println("Changed!!");
        setChanged();
        notifyObservers();
    }
}
