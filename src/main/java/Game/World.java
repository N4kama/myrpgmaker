package Game;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Engine.Character.EngineObj;
import Engine.Character.EngineSprite;
import Utils.WorldTools;

public class World {
    public ArrayList<Map> gameWorld_;
    public ArrayList<String> tilesSprite;

    public ArrayList<String> getTilesSprite() {
        return tilesSprite;
    }

    public int getTileId(String t) {
        if (tilesSprite.contains(t)) {
            return tilesSprite.indexOf(t);
        }
        tilesSprite.add(t);
        return tilesSprite.size() - 1;
    }

    public String getTileImg(int i) {
        if (i > tilesSprite.size())
            return null;
        return tilesSprite.get(i);
    }

    private Integer id_cur_map;

    private String name_;
    public EngineObj player_;
    private String default_player_path = "player/hero.png";

    public World(String s, String player_sprite_path) {
        this.gameWorld_ = new ArrayList<>();
        this.name_ = s;
        tilesSprite = new ArrayList<>();

        // init player basics
        player_ = new EngineObj("player", player_sprite_path, true, true);
        WorldTools.player = player_;
    }

    public ArrayList<Map> getGameWorld() {
        return gameWorld_;
    }

    public void setGameWorld(ArrayList<Map> gameWorld) {
        this.gameWorld_ = gameWorld_;
    }

    public String getName() {
        return name_;
    }

    public int addMap(Map m) {
        this.gameWorld_.add(gameWorld_.size(), m);
        if (gameWorld_.size() == 1) {
            id_cur_map = 0;
            
            //player_.set_map(m);
            /*player_.setPosition_(m.getSpawn());
            m.getEngineObjs().add(player_);
            Tile t = m.getTile(player_.getPosition_());
            t.setHas_Obj(true);
            player_.setSprite_(default_player_path);
            player_.setEs(new EngineSprite(default_player_path));*/
        }
        return gameWorld_.size() - 1;
    }

    public Map getCurMap() {
        return gameWorld_.get(id_cur_map);
    }

    public void changeMap(int n) {
        id_cur_map = n;
    }

    public int nextMap() {
        return (id_cur_map + 1) % gameWorld_.size();
    }

    public Map getMap(int index) {
        if (gameWorld_.size() > index)
            return gameWorld_.get(index);
        return null;
    }

    public void setName(String name) {
        this.name_ = name;
    }

    public void setPlayer(EngineObj p) {
        this.player_ = p;
        p.setIs_player(true);
    }
}
