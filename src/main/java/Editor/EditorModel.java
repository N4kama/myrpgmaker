package Editor;

import Game.Map;
import Game.World;

import java.util.Observable;

public class EditorModel extends Observable {

    public World gameWorld;

    public EditorModel() {
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
}
