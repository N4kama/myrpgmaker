package Game;

import Engine.Direction;
import Engine.Position;
import Engine.Character.EngineObj;
import Engine.Character.EngineSprite;
import Engine.Event.GameEvents;
import Engine.Event.MoveEvent;
import Engine.Event.TeleportEvent;
import Utils.SpriteTools;
import Utils.WorldTools;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Map {
    private ArrayList<Tile> gameTiles_;
    private ArrayList<EngineObj> engineObjs;
    private Position spawn_;
    private Integer width_;
    private Integer height_;
    private Position goal_;
    private String default_tile;
    private boolean is_player_set = false;

    public Map(int width, int height, String tile_model) {
        gameTiles_ = new ArrayList<>();
        engineObjs = new ArrayList<>();
        width_ = width;
        height_ = height;
        this.default_tile = tile_model;

        for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w++) {
                Tile tile = new Tile(w, h, tile_model);
                gameTiles_.add(tile);
            }
        }
    }

    public Map(int width, int height, Position spawn, Position goal, String tile_model) {
        gameTiles_ = new ArrayList<>();
        engineObjs = new ArrayList<>();
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

    public Map(String map, int width, int height, String default_tile_path) {
        gameTiles_ = new ArrayList<>();
        engineObjs = new ArrayList<>();
        width_ = width;
        height_ = height;
        this.default_tile = default_tile_path;

        for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w++) {
                Tile tile = new Tile(w, h, default_tile_path);
                gameTiles_.add(tile);
            }
        }
    }

    public String getDefault_tile() {
        return default_tile;
    }

    public void addEngineObj(EngineObj e) {
        engineObjs.add(e);
    }

    public ArrayList<Tile> getTiles() {
        return gameTiles_;
    }

    public void setTiles(ArrayList<Tile> tiles) {
        this.gameTiles_ = tiles;
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

    public Tile getTile(Position p) {
        for (int i = 0; i < gameTiles_.size(); i++) {
            if (gameTiles_.get(i).getPos().equals(p))
                return gameTiles_.get(i);
        }
        return null;
    }

    public String getPathTile(int i, int j) {
        Tile tile = getGameTile(i, j);
        return tile.get_path();
    }

    public Tile deleteTile(int x, int y) {
        Tile tile = getGameTile(x, y);
        if (tile == null)
            return null;
        tile.set_path(default_tile);
        return tile;
    }

    public Tile setTile(int x, int y, String path) {
        if (getGameObject(x, y) != null)
            return null;
        Tile tile = getGameTile(x / 16, y / 16);
        tile.setIs_Walkable(SpriteTools.walkable);
        tile.set_path(path);
        return tile;
    }

    public boolean is_there_obj_at_coords(EngineObj obj, int x, int y) {
        BufferedImage img = SpriteTools.pathToImg.get(obj.getSprite_());
        int Height = img.getHeight() / 16;
        int Width = img.getWidth() / 16;
        boolean res = (obj.get_x() <= x);
        res &= (x < obj.get_x() + Width);
        res &= (obj.get_y() <= y);
        res &= (y < obj.get_y() + Height);
        return res;
    }

    public EngineObj getGameObject(int x, int y) {
        ArrayList<EngineObj> arr = engineObjs.stream()
                .filter(obj -> is_there_obj_at_coords(obj, x, y))
                .collect(Collectors.toCollection(ArrayList::new));
        if (arr.size() > 0) {
            return arr.get(0);
        }
        return null;
    }

    public EngineObj deleteGameObject(int x, int y) {
        EngineObj obj = getGameObject(x / 16, y / 16);
        if (obj == null)
            return null;
        engineObjs.remove(obj);
        return obj;
    }

    public EngineObj setObject(int x, int y, String path) {
        EngineObj obj = getGameObject(x / 16, y / 16);
        if (obj == null) {
            obj = new EngineObj(x / 16, y / 16, path);
            BufferedImage img = SpriteTools.openObject(obj.getSprite_());
            for (int i = 0; i < img.getWidth(); i++) {
                for (int j = 0; j < img.getHeight(); j++) {
                    Tile t = getTile(new Position(x / 16 + i ,x / 16 + j));
                    t.setHas_Obj(true);
                }
            }
            GameEvents e = new TeleportEvent(obj, 10,10, this);
            obj.add_event(e);
            engineObjs.add(obj);
        }
        obj.setSprite_(path);
        return obj;
    }

    public EngineObj setNpc(int x, int y, String path) {
        EngineObj objj = getGameObject(x / 16, y / 16);
        if (objj == null) {
            final EngineObj obj = new EngineObj("npc", path, true, false);
            obj.setPosition_(new Position(x /16, y /16));
            GameEvents e = new MoveEvent(obj, null, this);
            obj.setEs(new EngineSprite(path));
            obj.add_event(e);
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    Tile t = getTile(new Position(x / 16 + i ,x / 16 + j));
                    t.setHas_Obj(true);
                }
            }
            engineObjs.add(obj);
            obj.setSprite_(path);
            return obj;
        }
        objj.setSprite_(path);
        return objj;
    }

    public ArrayList<EngineObj> getGameObjects() {
        return engineObjs;
    }

    public Tile getGameTile(int i, int j) {
        return gameTiles_.get(j * width_ + i);
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

    public EngineObj setPlayer(int x, int y, String path) {
        EngineObj player = WorldTools.player;
        player.setIs_player(true);
        if (player == null) {
            System.err.println("Error, player should exist");
            return null;
        }
        if (!is_player_set) {
            player.setPosition_(new Position(x / 16, y / 16));
            engineObjs.add(player);
            is_player_set = true;
        }
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                Tile t = getTile(new Position(x / 16 + i ,x / 16 + j));
                t.setHas_Obj(true);
            }
        }
        player.setSprite_(path);
        player.setEs(new EngineSprite(path));
        return player;
    }
}