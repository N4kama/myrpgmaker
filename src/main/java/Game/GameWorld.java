package Game;

import java.util.ArrayList;

public class GameWorld {
    public String name;
    public ArrayList<GameMap> maps = new ArrayList<>();

    public GameWorld(String name) {
        this.name = name;
    }

    public void addMap(GameMap gameMap) {
        maps.add(gameMap);
    }

    public GameMap getMap(int index) {
        return maps.get(0);
    }
}
