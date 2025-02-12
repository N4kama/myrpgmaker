package MapPannel;

import Engine.Character.EngineObj;
import Engine.Position;
import Game.Map;
import Game.Tile;
import Utils.DoTools;
import Utils.EditorEvent;
import Utils.SpriteTools;
import Utils.WorldTools;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class MapModel extends Observable implements Observer {
    protected Rectangle selection_rect = null;
    protected int x_rect;
    protected int y_rect;
    protected BufferedImage unwalkable = SpriteTools.openTile("/misc/not_walkable.png");

    @Override
    public void update(Observable o, Object arg) {
        if (arg.getClass() == String.class && ((String) arg).equals("toggleGrid")) {
            setChanged();
            notifyObservers("toggleGrid");
        }
    }

    public void setWalkable(int x, int y) {
        EngineObj obj = map.getGameObject(x / 16, y / 16, false);
        Tile tile = null;
        if (selection_rect != null && ((selection_rect.x <= x / 16 && x / 16 <= selection_rect.x + selection_rect.width)
                && (selection_rect.y <= y / 16 && y / 16 <= selection_rect.y + selection_rect.height))) {
            for (int i = selection_rect.x; i < selection_rect.x + selection_rect.width; i++) {
                for (int j = selection_rect.y; j < selection_rect.y + selection_rect.height; j++) {
                    tile = map.getGameTile(i, j);
                    tile.setIs_Walkable(SpriteTools.walkable);
                }
            }
        }
        else {
            tile = map.getGameTile(x / 16, y / 16);
            if (obj != null)
                tile.setBehindObject(true);
            tile.setIs_Walkable(SpriteTools.walkable);
        }
        setChanged();
        notifyObservers(tile);
    }

    public void selectTiles(int x, int y, boolean new_selection) {
        if (new_selection) {
            x_rect = x / 16;
            y_rect = y / 16;
            selection_rect = null;
            setChanged();
            notifyObservers("clearSelection");
        } else {
            selection_rect = new Rectangle(x_rect, y_rect, x / 16 - x_rect + 1, y / 16 - y_rect + 1);
            if (selection_rect.height == 1 && selection_rect.width == 1) {
                selection_rect = null;
                return;
            }
            setChanged();
            notifyObservers(selection_rect);
        }
    }

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

    public MapModel(Map map, Observable editorModel) {
        this.map = map;
        this.addObserver(WorldTools.inspectorModel);
        editorModel.addObserver(this);
    }

    public int getWidth() {
        return map.getWidth();
    }

    public int getHeight() {
        return map.getHeight();
    }

    public void modifySprite(int x, int y) {
        Object res = null;
        if (SpriteTools.selectedSprite == null)
            return;
        if (SpriteTools.is_background) {
            if (selection_rect != null && ((selection_rect.x <= x / 16 && x / 16 <= selection_rect.x + selection_rect.width)
                    && (selection_rect.y <= y / 16 && y / 16 <= selection_rect.y + selection_rect.height))) {
                for (int i = selection_rect.x; i < selection_rect.x + selection_rect.width; i++) {
                    for (int j = selection_rect.y; j < selection_rect.y + selection_rect.height; j++) {
                        res = map.setTile(i, j, SpriteTools.selectedSprite);
                    }
                }
            } else {
                res = map.setTile(x / 16, y / 16, SpriteTools.selectedSprite);
                DoTools.addUndoEvent(new EditorEvent(EditorEvent.EventType.ADD_TILE, map, x, y));
            }
        } else {
            if (SpriteTools.playerModelSelected) {
                res = map.setPlayer(x, y, SpriteTools.selectedSprite);
                if (res != null) {
                    map.setSpawn_(new Position(x / 16, y / 16));
                    DoTools.addUndoEvent(new EditorEvent(EditorEvent.EventType.ADD_OBJECT, map, x, y));
                }
            } else if (SpriteTools.npcModelSelected) {
                res = map.setNpc(x, y, SpriteTools.selectedSprite);
                DoTools.addUndoEvent(new EditorEvent(EditorEvent.EventType.ADD_OBJECT, map, x, y));
            } else {
                res = map.setObject(x, y, SpriteTools.selectedSprite);
                DoTools.addUndoEvent(new EditorEvent(EditorEvent.EventType.ADD_OBJECT, map, x, y));
            }
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
        if (res != null)
            DoTools.addUndoEvent(new EditorEvent(EditorEvent.EventType.DEL_OBJECT, map, x, y));
        else {
            if (selection_rect != null && ((selection_rect.x <= x / 16 && x / 16 <= selection_rect.x + selection_rect.width)
                    && (selection_rect.y <= y / 16 && y / 16 <= selection_rect.y + selection_rect.height))) {
                for (int i = selection_rect.x; i < selection_rect.x + selection_rect.width; i++) {
                    for (int j = selection_rect.y; j < selection_rect.y + selection_rect.height; j++) {
                        res = map.deleteTile(i, j);
                    }
                }
            } else {
                res = map.deleteTile(x / 16, y / 16);
                DoTools.addUndoEvent(new EditorEvent(EditorEvent.EventType.DEL_OBJECT, map, x, y));
            }
            if (res != null) {
                setChanged();
                notifyObservers(res);
            }
        }
    }

    public void moveSpite(int x, int y) {
        if (!is_moving) {
            EngineObj obj = map.getGameObject(x / 16, y / 16);
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
            }
        } else {
            is_moving = false;
            if (objectMoved == ObjectMoved.TILE) {
                Tile tile = map.getGameTile(x / 16, y / 16);
                if (tile != null) {
                    tile.set_path(saved_moved_tilePath);
                    setChanged();
                    notifyObservers(tile);
                }
            } else if (objectMoved == ObjectMoved.ENGINEOBJ) {
                if (map.canPlaceObj(x / 16, y / 16, moved_object)) {
                    moved_object.setPosition_(new Position(x / 16, y / 16));
                    setChanged();
                    notifyObservers(moved_object);
                }
            }
        }
    }
}
