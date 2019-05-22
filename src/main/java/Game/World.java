package Game;

import java.util.ArrayList;

import Engine.Character.EngineObj;


public class World {
    private ArrayList<Map> gameWorld_;
    private Integer id_cur_map;
    private String name_;
    private EngineObj player_;

    public World(String s, String player_sprite_path) {
        this.gameWorld_ = new ArrayList<>();
        this.name_ = s;
        player_ = new EngineObj("player", player_sprite_path, true, true);
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
        if(gameWorld_.size() == 0)
        {
            changeMap(0);
        }
        this.gameWorld_.add(m, gameWorld_.size());
    }

    public Map getCurMap()
    {
        return gameWorld_.get(id_cur_map);
    }

    public void changeMap(int n)
    {
        player_.set_map(gameWorld_.get(n));
        id_cur_map = n;
    }

    public void setName(String name) {
        this.name_ = name;
    }
}
