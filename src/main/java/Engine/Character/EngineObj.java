package Engine.Character;

import java.util.ArrayList;
import java.util.List;

import Engine.Direction;
import Engine.Position;
import Game.Map;
import Game.Tile;
import Engine.Event.GameEvents;
import Engine.Event.MoveEvent;

public class EngineObj {
    private Boolean is_player = false;
    private List<GameEvents> events;

    public EngineObj(int x, int y, String sprite_path) {
        this.position_ = new Position(x, y);
        this.sprite_ = sprite_path;
        alive = false;
        is_player = false;
        events = new ArrayList<>();
        name_ = "NPC";
    }

    public EngineObj(String name, String sprite, Boolean alive, Boolean is_player) {
        name_ = name;
        sprite_ = sprite;
        changeDir(Direction.DOWN);
        setAnim_state_(1);
        this.alive = alive;
        this.is_player = is_player;
        this.position_ = new Position(0, 0);
        events = new ArrayList<>();
    }

    public Boolean getIs_player() {
        return is_player;
    }
    public String getDialog() {
        return dialog_;
    }
    public void setDialog(String d) {dialog_ = d;}

    public void set_map(Map m)
    {
        position_ = m.getSpawn();
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
        //if (rotate(dir))
        //    return false;
        if (canMove(dir, m)) {
            // set prev tile to walkable
            Tile t = m.getTile(position_);
            //t.setIs_Walkable(true);
            position_.move(dir);
            // set new tile to nonwalkable
            t = m.getTile(position_);
            //t.setIs_Walkable(false);
            //if (is_player)
            //    t.run_events();
            return true;
        }
        if (is_player) {
            Tile t = m.getTile(position_.tempPos(dir));
            if (t != null)
                t.run_events();
            for(EngineObj eo : m.getEngineObjs())
            {
                for (GameEvents g : eo.events)
                {
                    g.run();
                }
            }
        }
        return false;
    }

    private boolean canMove(Direction dir, Map m) {
        Tile t = m.getTile(position_.tempPos(dir));
        if (t == null)
        {
            GameEvents g = new MoveEvent(this, Direction.DOWN, m);
            g.run();
            return false;
        }
        System.out.println(getPosition_().getX()+":"+getPosition_().getY()+" -> "+t.get_x() +":"+t.get_y());
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

    public Boolean getAlive()
    {
        return alive;
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

    //dialog
    private String dialog_;

    public void setIs_player(Boolean is_player) {
        this.is_player = is_player;
    }

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

    public int get_x() {
        return position_.getX();
    }

    public int get_y() {
        return position_.getY();
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