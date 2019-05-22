package Game;

import Engine.Position;
import Engine.Character.EngineObj;

import java.util.ArrayList;

public class Map {
    private ArrayList<Tile> gameTiles_;
    private ArrayList<Object> gameObjects_;
    private ArrayList<EngineObj> engineObjs;
    private EngineObj player_;
    private Position spawn_;
    private Integer width_;
    private Integer height_;
    private Position goal_;

    public Map(int width, int height, Position spawn, Position goal, String tile_model) {
        gameTiles_ = new ArrayList<>();
        gameObjects_ = new ArrayList<>();
        engineObjs = new ArrayList<>();
        width_ = width;
        height_ = height;
        spawn_ = spawn;
        goal_ = goal;
        player_ = new EngineObj("player", "inserer_sprite_du_player", this, true, true);
        
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

    public Tile getTile(Position p) {
        for (int i = 0; i < gameTiles_.size(); i++) {
            if (gameTiles_.get(i).getPos().equals(p))
                return gameTiles_.get(i);
        }
        return null;
    }

    /**
     * @return ArrayList<Tile> return the gameTiles_
     */
    public ArrayList<Tile> getGameTiles_() {
        return gameTiles_;
    }

    /**
     * @param gameTiles_ the gameTiles_ to set
     */
    public void setGameTiles_(ArrayList<Tile> gameTiles_) {
        this.gameTiles_ = gameTiles_;
    }

    /**
     * @return ArrayList<Object> return the gameObjects_
     */
    public ArrayList<Object> getGameObjects_() {
        return gameObjects_;
    }

    /**
     * @param gameObjects_ the gameObjects_ to set
     */
    public void setGameObjects_(ArrayList<Object> gameObjects_) {
        this.gameObjects_ = gameObjects_;
    }

    /**
     * @return ArrayList<EngineObj> return the engineObjs
     */
    public ArrayList<EngineObj> getEngineObjs() {
        return engineObjs;
    }

    /**
     * @param engineObjs the engineObjs to set
     */
    public void setEngineObjs(ArrayList<EngineObj> engineObjs) {
        this.engineObjs = engineObjs;
    }

    /**
     * @return EngineObj return the player_
     */
    public EngineObj getPlayer_() {
        return player_;
    }

    /**
     * @param player_ the player_ to set
     */
    public void setPlayer_(EngineObj player_) {
        this.player_ = player_;
    }

    /**
     * @return Position return the spawn_
     */
    public Position getSpawn_() {
        return spawn_;
    }

    /**
     * @param spawn_ the spawn_ to set
     */
    public void setSpawn_(Position spawn_) {
        this.spawn_ = spawn_;
    }

    /**
     * @return Integer return the width_
     */
    public Integer getWidth_() {
        return width_;
    }

    /**
     * @param width_ the width_ to set
     */
    public void setWidth_(Integer width_) {
        this.width_ = width_;
    }

    /**
     * @return Integer return the height_
     */
    public Integer getHeight_() {
        return height_;
    }

    /**
     * @param height_ the height_ to set
     */
    public void setHeight_(Integer height_) {
        this.height_ = height_;
    }

    /**
     * @return Position return the goal_
     */
    public Position getGoal_() {
        return goal_;
    }

    /**
     * @param goal_ the goal_ to set
     */
    public void setGoal_(Position goal_) {
        this.goal_ = goal_;
    }

}