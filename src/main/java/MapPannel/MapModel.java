package MapPannel;

import Engine.Character.EngineObj;
import Game.Map;
import Utils.SpriteTools;
import java.util.ArrayList;
import java.util.Observable;

public class MapModel extends Observable {
    public Map map;

    public MapModel(Map map) {
        this.map = map;
    }

    public int getWidth() {
        return map.getWidth();
    }

    public int getHeight() {
        return map.getHeight();
    }

    public void modifySprite(int x, int y) {
        if (SpriteTools.selectedSprite == null)
            return;
        if (SpriteTools.is_background) {
            map.setTile(x / 16, y / 16, SpriteTools.selectedSprite);
        } else {
            map.setObject(x, y, SpriteTools.selectedSprite);
        }
        setChanged();
        notifyObservers();
    }

    public ArrayList<EngineObj> getObjects() {
        return map.getGameObjects();
    }
}
