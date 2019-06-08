package Game;

import java.util.ArrayList;

import Engine.Character.EngineObj;
import Engine.Character.EngineSprite;
import Utils.WorldTools;

public class World {
    public ArrayList<Map> gameWorld_;
    private Integer id_cur_map;

    private String name_;
    public EngineObj player_;
    private String default_player_path = System.getProperty("user.dir") + "/resources/player/hero.png";

    public World(String s, String player_sprite_path) {
        this.gameWorld_ = new ArrayList<>();
        this.name_ = s;

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
            changeMap(0);
        }
        return gameWorld_.size() - 1;
    }

    public Map getCurMap() {
        return gameWorld_.get(id_cur_map);
    }

    public void changeMap(int n) {
        Map m = gameWorld_.get(n);
        player_.set_map(m);
        player_.setPosition_(m.getSpawn());
        m.getEngineObjs().add(player_);
        Tile t = m.getTile(player_.getPosition_());
        t.setHas_Obj(true);
        player_.setSprite_(default_player_path);
        player_.setEs(new EngineSprite(default_player_path));
        id_cur_map = n;
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
