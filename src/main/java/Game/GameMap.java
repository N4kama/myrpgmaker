package Game;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class GameMap {
    public String name;
    public int width;
    public int height;
    String default_tile;

    ArrayList<ArrayList<GameTile>> tiles = new ArrayList<>();
    ArrayList<ArrayList<GameObject>> objects = new ArrayList<>();

    public GameMap(String name, int width, int height, String default_tile_path) {
        this.name = name;
        this.width = width;
        this.height = height;
        this.default_tile = default_tile_path;

        for (int i = 0; i < height; i++) {
            tiles.add(new ArrayList<>());
            for (int j = 0; j < width; j++) {
                tiles.get(i).add(new GameTile(j, i, default_tile_path));
            }
        }
    }

    public String getPathTile(int i, int j) {
        GameTile tile = getGameTile(i, j);
        return tile.path;
    }

    private GameTile getGameTile(int i, int j) {
        ArrayList<GameTile> tmp = tiles.get(j);
        if (tmp != null) {
            return tmp.get(i);
        }
        return null;
    }

    public void deleteTile(int x, int y) {
        GameTile tile = getGameTile(x, y);
        tile.path = default_tile;
    }

    public void setTile(int x, int y, String path) {
        GameTile tile = getGameTile(x, y);
        tile.path = path;
    }
}
