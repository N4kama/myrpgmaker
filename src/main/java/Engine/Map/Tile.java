package Engine.Map;

import Engine.Position;

public class Tile {
    private Position p_;
    private TileType t_;
    public Tile(int x, int y, TileType t)
    {
        p_ = new Position(x, y);
        t_ = t;
    }
}