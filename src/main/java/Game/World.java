package Game;

import java.util.ArrayList;

import Engine.Character.EngineObj;
import Utils.WorldTools;


public class World {
    private ArrayList<Map> gameWorld_;
    private Integer id_cur_map;

    private String name_;
    private EngineObj player_;

    public World(String s, String player_sprite_path) {
        this.gameWorld_ = new ArrayList<>();
        this.name_ = s;
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

    public void addMap(Map m) {
        this.gameWorld_.add(gameWorld_.size(), m);
        if(gameWorld_.size() == 1)
        {
            changeMap(0);
        }
    }

    public Map getCurMap()
    {
        return gameWorld_.get(id_cur_map);
    }

    public void changeMap(int n)
    {
        Map m = gameWorld_.get(n);
        player_.set_map(m);
        id_cur_map = n;
    }


    public Map getMap(int index) {
        return gameWorld_.get(index);
    }

    public void setName(String name) {
        this.name_ = name;
    }

    public void setPlayer(EngineObj p) {
        this.player_ = p;
    }
}
