package Utils;

import Engine.Character.EngineObj;
import Game.Map;

public class EditorEvent {

    public EditorEvent(EventType action, Map map, int x, int y) {
        this.action = action;
        this.map = map;
        this.x = x;
        this.y = y;
        old_path = map.getGameTile(x / 16, y / 16).get_path();
        old_path_img = map.getGameTile(x / 16, y / 16).get_path();//getTile_img_();
        was_walkable =  map.getGameTile(x / 16, y / 16).isIs_Walkable_();
        object = null;
    }

    public enum EventType {
        ADD_TILE,
        ADD_OBJECT,
        DEL_OBJECT
    }

    public EventType action;
    Map map;
    public int x;
    public int y;
    String old_path;
    String old_path_img;
    boolean was_walkable;
    EngineObj object;
}
