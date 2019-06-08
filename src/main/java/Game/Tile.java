package Game;

import java.util.ArrayList;
import java.util.List;

import Editor.EditorModel;
import Engine.Position;
import Engine.Event.GameEvents;

public class Tile {
    private Position pos_;
    private Boolean is_Walkable_ = true;
    private Boolean has_Obj = true;
    private int tile_img_;
    private List<GameEvents> events;
    private boolean behindObject = false;

    public Tile(int x, int y, String img)
    {
        pos_ = new Position(x, y);
        tile_img_ = EditorModel.singleton.gameWorld.getTileId(img);
        events = new ArrayList<>();
        has_Obj = false;
        is_Walkable_ = true;
    }

    public void add_event(GameEvents e)
    {
        events.add(e);
    }

    public boolean run_events()
    {
        boolean res = true;
        if(events.size() == 0)
            return true;
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
        return EditorModel.singleton.gameWorld.getTileImg(tile_img_);
    }

    public void set_path(String path) {
        tile_img_ = EditorModel.singleton.gameWorld.getTileId(path);
    }

    public Boolean getIs_Walkable() {
        return is_Walkable_ && !has_Obj;
    }

    public void setIs_Walkable(Boolean is_Walkable_) {
        this.is_Walkable_ = is_Walkable_;
    }

    public String getForeGround_img() {
        return get_path();
    }

    public void setForeGround_img(String path) {
        this.tile_img_ = EditorModel.singleton.gameWorld.getTileId(path);

    }


    /**
     * @return Position return the pos_
     */
    public Position getPos_() {
        return pos_;
    }

    /**
     * @param pos_ the pos_ to set
     */
    public void setPos_(Position pos_) {
        this.pos_ = pos_;
    }

    /**
     * @return Boolean return the is_Walkable_
     */
    public Boolean isIs_Walkable_() {
        return is_Walkable_;
    }

    /**
     * @param is_Walkable_ the is_Walkable_ to set
     */
    public void setIs_Walkable_(Boolean is_Walkable_) {
        this.is_Walkable_ = is_Walkable_;
    }

    /**
     * @return Boolean return the has_Obj
     */
    public Boolean isHas_Obj() {
        return has_Obj;
    }

    /**
     * @param has_Obj the has_Obj to set
     */
    public void setHas_Obj(Boolean has_Obj) {
        this.has_Obj = has_Obj;
    }

    /**
     * @return List<GameEvents> return the events
     */
    public List<GameEvents> getEvents() {
        return events;
    }

    /**
     * @param events the events to set
     */
    public void setEvents(List<GameEvents> events) {
        this.events = events;
    }

    public boolean isBehindObject() {
        return behindObject;
    }

    public void setBehindObject(boolean behindObject) {
        this.behindObject = behindObject;
    }
}