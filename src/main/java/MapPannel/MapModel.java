package MapPannel;

import Engine.Character.EngineObj;
import Engine.Position;
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
        Object res;
        if (SpriteTools.selectedSprite == null)
            return;
        if (SpriteTools.is_background) {
            res = map.setTile(x / 16, y / 16, SpriteTools.selectedSprite);
        } else {
            if (SpriteTools.playerModelSelected) {
                res = map.setPlayer(x, y, SpriteTools.selectedSprite);
                if (res != null)
                    map.setSpawn_(new Position(x, y));
            }
            else
                res = map.setObject(x, y, SpriteTools.selectedSprite);
        }
        if (res != null) {
            setChanged();
            notifyObservers(res);
        }
    }

    public ArrayList<EngineObj> getObjects() {
        return map.getGameObjects();
    }

    public void deleteSprite(int x, int y) {
        Object res;
        res = map.deleteGameObject(x, y);
        if (res == null)
            res = map.deleteTile(x / 16, y / 16);
        if (res != null) {
            setChanged();
            notifyObservers(res);
        }
    }
}
