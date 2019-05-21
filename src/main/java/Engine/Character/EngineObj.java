package Engine.Character;

import java.util.ArrayList;
import java.util.List;

import Engine.Direction;
import Engine.Position;
import Game.Map;
import Game.Tile;
import Engine.Event.GameEvents;

public class EngineObj {
    private Boolean is_player;
    private List<GameEvents> events;
    private transient Map m;
    public EngineObj(String name, String sprite, Map map, Boolean alive, Boolean is_player) {
        name_ = name;
        sprite_ = sprite;
        m = map;
        changeDir(Direction.DOWN);
        setAnim_state_(1);
        position_ = map.getSpawn();
        this.alive = alive;
        this.is_player = is_player;
        events = new ArrayList<>();
    }

    public void add_event(GameEvents e) {
        events.add(e);
    }

    public boolean run_events() {
        boolean res = true;
        for (GameEvents e : events) {
            res &= e.run();
        }
        return res;
    }

    public boolean move(Direction dir, Map m) {
        // if not in correct dir, just rotate
        if (rotate(dir))
            return false;
        if (canMove(dir, m)) {
            // set prev tile to walkable
            Tile t = m.getTile(position_);
            t.setIs_Walkable(true);
            position_.move(dir);
            // set new tile to nonwalkable
            t = m.getTile(position_);
            t.setIs_Walkable(false);
            if (is_player)
                t.run_events();
            return true;
        }
        if (is_player) {
            Tile t = m.getTile(position_.tempPos(dir));
            if (t != null)
                t.run_events();
        }
        return false;
    }

    private void animate() {
        if (alive)
            setAnim_state_((anim_state_ + 1) % 3);
        else {
            run_events();
        }
    }

    private boolean canMove(Direction dir, Map m) {
        Tile t = m.getTile(position_.tempPos(dir));
        if (t == null)
            return false;
        return t.getIs_Walkable();
    }

    private boolean rotate(Direction dir) {
        if (dir == getDir()) {
            return false;
        }
        setAnim_state_(1);
        changeDir(dir);
        return true;
    }

    // ids
    private Integer id_;
    private String name_;

    // sprite
    private String sprite_;
    private Boolean alive;

    // map
    private Position position_;

    // state
    private Direction cur_dir_;
    private Integer anim_state_;

    /**
     * @return Integer return the id_
     */
    public Integer getId_() {
        return id_;
    }

    /**
     * @param id_ the id_ to set
     */
    public void setId_(Integer id_) {
        this.id_ = id_;
    }

    /**
     * @return String return the name_
     */
    public String getName_() {
        return name_;
    }

    /**
     * @param name_ the name_ to set
     */
    public void setName_(String name_) {
        this.name_ = name_;
    }

    /**
     * @return String return the sprite_
     */
    public String getSprite_() {
        return sprite_;
    }

    /**
     * @param sprite_ the sprite_ to set
     */
    public void setSprite_(String sprite_) {
        this.sprite_ = sprite_;
    }

    /**
     * @return Direction return the cur_dir_
     */
    public Direction getDir() {
        return cur_dir_;
    }

    /**
     * @param cur_dir_ the cur_dir_ to set
     */
    public void changeDir(Direction cur_dir_) {
        this.cur_dir_ = cur_dir_;
    }

    /**
     * @return Direction return the cur_dir_
     */
    public Direction getCur_dir_() {
        return cur_dir_;
    }

    /**
     * @param cur_dir_ the cur_dir_ to set
     */
    public void setCur_dir_(Direction cur_dir_) {
        this.cur_dir_ = cur_dir_;
    }

    /**
     * @return Position return the position_
     */
    public Position getPosition_() {
        return position_;
    }

    /**
     * @param position_ the position_ to set
     */
    public void setPosition_(Position position_) {
        this.position_ = position_;
    }

    /**
     * @return Integer return the anim_state_
     */
    public Integer getAnim_state_() {
        return anim_state_;
    }

    /**
     * @param anim_state_ the anim_state_ to set
     */
    public void setAnim_state_(Integer anim_state_) {
        this.anim_state_ = anim_state_;
    }

}