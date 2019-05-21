package MapPannel;

import Game.GameMap;

public class MapModel {
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
}
