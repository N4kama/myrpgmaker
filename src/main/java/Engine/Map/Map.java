package Engine.Map;

import Engine.Position;

public class Map {
    private Tile[] map;
    private Position spawn;
    private Integer width;
    private Integer height;

    public Map(int width, int height, Position spawn) {
        int size = width * height;
        this.width = width;
        this.height = height;
        map = new Tile[size];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                map[j * width + i] = new Tile(i, j, TileType.GROUND);
            }
        }
        this.spawn = spawn;
    }

    /**
     * @return Tile[] return the map
     */
    public Tile[] getMap() {
        return map;
    }

    public Tile getTile(Position p) {
        if (p.getX() >= width || p.getX() < 0 || p.getY() >= height || p.getY() < 0)
            return new Tile(p.getX(), p.getY(), TileType.VOID);
        return map[p.getY() * width + p.getX()];
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