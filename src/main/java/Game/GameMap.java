package Game;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class GameMap {
    public String name;
    public int width;
    public int height;

    ArrayList<GameTile> tiles = new ArrayList<>();
    ArrayList<GameObject> objects = new ArrayList<>();

    public GameMap(String name, int width, int height, String default_tile_path) {
        this.name = name;
        this.width = width;
        this.height = height;

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                tiles.add(new GameTile(i, j, default_tile_path));
            }
        }
    }

    public String getPathTile(int i, int j) {
        GameTile tile = getGameTile(i, j);
        return tile.path;
    }

    private GameTile getGameTile(int i, int j) {
        ArrayList<GameTile> res = tiles
                .stream()
                .filter(tile -> tile.x_pos == i && tile.y_pos == j)
                .collect(Collectors.toCollection(ArrayList::new));
        if (res.size() > 0) {
            return res.get(0);
        }
        else
            return null;
    }

    public void deleteTile(int x, int y) {
        tiles.removeIf(tile -> tile.x_pos == x && tile.y_pos == y);
    }

    public void setTile(int x, int y, String path) {
        tiles.add(new GameTile(x, y, path));
    }
}
