package Engine.Map;

import Engine.Position;

public class Map {
    private Tile[] map;
    private Position spawn;
    public Map(int width, int height, int x, int y)
    {
        int size = width * height;
        map = new Tile[size];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                map[j * width + i] = new Tile(i, j, TileType.GROUND);
            }
        }
        spawn = new Position(x, y);
    }

    /**
     * @return Tile[] return the map
     */
    public Tile[] getMap() {
        return map;
    }

    /**
     * @param map the map to set
     */
    public void setMap(Tile[] map) {
        this.map = map;
    }

    /**
     * @return Position return the spawn
     */
    public Position getSpawn() {
        return spawn;
    }

    /**
     * @param spawn the spawn to set
     */
    public void setSpawn(Position spawn) {
        this.spawn = spawn;
    }

}