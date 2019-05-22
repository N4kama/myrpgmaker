package Game;

import java.util.ArrayList;


public class World {
    private ArrayList<Map> gameWorld_;
    private String name_;

    public World(String s) {
        this.gameWorld_ = new ArrayList<>();
        this.name_ = s;
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
        this.gameWorld_.add(m);
    }

    public void setName(String name) {
        this.name_ = name;
    }
}
