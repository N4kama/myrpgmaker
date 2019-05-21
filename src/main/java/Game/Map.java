package Game;

import Engine.Position;

import java.util.ArrayList;

public class Map {
    private ArrayList<Tile> gameTiles_;
    private ArrayList<Object> gameObjects_;
    private Position spawn_;
    private Integer width_;
    private Integer height_;
    private Position goal_;

    public Map(int width, int height, Position spawn, Position goal, String tile_model) {
        gameTiles_ = new ArrayList<>();
        gameObjects_ = new ArrayList<>();
        width_ = width;
        height_ = height;
        spawn_ = spawn;
        goal_ = goal;

        for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w++) {
                Tile tile = new Tile(w, h, tile_model);
                gameTiles_.add(tile);
            }
        }
    }

    public ArrayList<Tile> getTiles() {
        return gameTiles_;
    }

    public void setTiles(ArrayList<Tile> tiles) {
        this.gameTiles_ = tiles;
    }

    public ArrayList<Object> getObjects() {
        return gameObjects_;
    }

    public void setObjects(ArrayList<Object> objects) {
        this.gameObjects_ = objects;
    }

    public Position getSpawn() {
        return spawn_;
    }

    public void setSpawn(Position spawn) {
        this.spawn_ = spawn;
    }

    public Position getGoal() {
        return goal_;
    }

    public void setGoal(Position g) {
        this.goal_ = g;
    }

    public Integer getWidth() {
        return width_;
    }

    public void setWidth(Integer width) {
        this.width_ = width;
    }

    public Integer getHeight() {
        return height_;
    }

    public void setHeight(Integer height) {
        this.height_ = height;
    }

    public void addObject(Object o) {
        gameObjects_.add(o);
    }
}