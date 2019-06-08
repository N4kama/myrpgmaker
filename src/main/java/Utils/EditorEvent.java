package Utils;

import Engine.Character.EngineObj;
import Game.Map;

public class EditorEvent {


    public EventType action;
    Map map;
    public int x;
    public int y;
    String old_path;
    String old_path_img;
    boolean was_walkable;
    EngineObj object;
}
