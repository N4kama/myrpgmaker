package Game;

import java.util.ArrayList;
import java.util.List;

import Engine.Position;
import Engine.Event.GameEvents;

public class Tile {
    private Position pos_;
    private Boolean is_Walkable_;
    private String tile_img_;
    private List<GameEvents> events;

    public Tile(int x, int y, String img)
    {
        pos_ = new Position(x, y);
        tile_img_ = img;
        events = new ArrayList<>();
    }

    public void add_event(GameEvents e)
    {
        events.add(e);
    }

    public boolean run_events()
    {
        boolean res = true;
        for(GameEvents e : events)
        {
            res &= e.run();
        }
        return res;
    }
    /**
     * @return the p_
     */
    public Position getPos() {
        return pos_;
    }

    public void setPos(Position pos) {
        this.pos_ = pos;
    }

    public int get_x() {
        return pos_.getX();
    }

    public int get_y() {
        return pos_.getY();
    }

    public String get_path() {
        return tile_img_;
    }

    public void set_path(String path) {
        tile_img_ = path;
    }

    public Boolean getIs_Walkable() {
        return is_Walkable_;
    }

    public void setIs_Walkable(Boolean is_Walkable_) {
        this.is_Walkable_ = is_Walkable_;
    }

    public String getForeGround_img() {
        return tile_img_;
    }

    public void setForeGround_img(String foreGround_img_) {
        this.tile_img_ = foreGround_img_;
    }

}