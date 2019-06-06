package MapPannel;

import Engine.Character.EngineObj;
import Engine.Position;
import Game.Map;
import Game.Tile;
import Utils.SpriteTools;
import Utils.WorldTools;

import java.util.ArrayList;
import java.util.Observable;

public class MapModel extends Observable {
    public enum ObjectMoved {
        ENGINEOBJ,
        TILE,
        NULL
    }

    public Map map;
    protected boolean is_moving = false;
    public ObjectMoved objectMoved = ObjectMoved.NULL;
    public Tile moved_tile;
    public String saved_moved_tilePath;
    public EngineObj moved_object;

    public MapModel(Map map) {
        this.map = map;
        this.addObserver(WorldTools.inspectorModel);
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
            res = map.setTile(x, y, SpriteTools.selectedSprite);
        } else {
            if (SpriteTools.playerModelSelected) {
                res = map.setPlayer(x, y, SpriteTools.selectedSprite);
                if (res != null)
                    map.setSpawn_(new Position(x, y));
            } else if(SpriteTools.npcModelSelected)
                res = map.setNpc(x, y, SpriteTools.selectedSprite);
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

    public EngineObj getPlayer(ArrayList<EngineObj> arr) {
        for (EngineObj obj : arr) {
            if (obj.getIs_player()) {
                return obj;
            }
        }
        return null;
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

    public void moveSpite(int x, int y) {
        if (!is_moving) {
            EngineObj obj = map.getGameObject(x, y);
            if (obj != null) {
                moved_object = obj;
                objectMoved = ObjectMoved.ENGINEOBJ;
                is_moving = true;
                setChanged();
                notifyObservers(moved_object);
                System.out.println("This will be moved " + obj.getSprite_());
            } else {
                Tile tile = map.getGameTile(x / 16, y / 16);
                if (SpriteTools.selectedSprite.equals(map.getDefault_tile()))
                    return;
                moved_tile = tile;
                saved_moved_tilePath = tile.get_path();
                tile.set_path(map.getDefault_tile());
                objectMoved = ObjectMoved.TILE;
                is_moving = true;
                setChanged();
                notifyObservers(moved_tile);
                System.out.println("This will be moved " + tile.get_path());
            }
        } else {
            is_moving = false;
            System.out.println("it has been moved");
            if (objectMoved == ObjectMoved.TILE) {
                Tile tile = map.getGameTile(x / 16, y / 16);
                if (tile != null) {
                    tile.set_path(saved_moved_tilePath);
                    setChanged();
                    notifyObservers(tile);
                }
            }
        }
    }
}
