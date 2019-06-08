package Editor;

import Game.Map;
import Game.World;
import Utils.SpriteTools;

import java.util.Observable;

import Engine.Position;

public class EditorModel extends Observable {
    public static EditorModel singleton;

    public World gameWorld;

    public EditorModel() {
        this.singleton = this;
    }

    public void setWorld(World gameWorld) {
        this.gameWorld = gameWorld;
    }

    public void add_map(int width, int height, String default_tile_path) {
        Map map = new Map(width, height, default_tile_path);
        int id = gameWorld.addMap(map);
        gameWorld.changeMap(id);
        setChanged();
        notifyObservers(map);
    }

    public void load_map(Map m)
    {
        setChanged();
        notifyObservers(m);
    }

    public void add_map(int width, int height, Position p, String default_tile_path) {
        Map map = new Map(width, height, p, default_tile_path);
        int id = gameWorld.addMap(map);
        //gameWorld.changeMap(id);
        setChanged();
        notifyObservers(map);
    }

    public void toggleGrid() {
        SpriteTools.grid_display = !SpriteTools.grid_display;
        setChanged();
        notifyObservers("toggleGrid");
    }
}
