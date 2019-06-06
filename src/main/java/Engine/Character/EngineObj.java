package Engine.Character;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import Engine.Direction;
import Engine.Position;
import Game.Map;
import Game.Tile;
import Engine.Event.GameEvents;
import Engine.Event.MoveEvent;
import Engine.Event.TeleportEvent;

public class EngineObj {
    private Boolean is_player = false;
    private List<GameEvents> events;
    private List<GameEvents> msgs;
    private boolean teleported;
    private Position teleportedPos;
    private EngineSprite es;

    public void setTeleported(boolean b)
    {
        teleported = b;
    }

    public boolean getTeleported()
    {
        return teleported;
    }

    public void setTeleportedPos(Position b)
    {
        teleportedPos = b;
    }

    public Position getTeleportedPos()
    {
        return teleportedPos;
    }

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

    public void animate(Direction d)
    {
        Animation cur = es.getCurAnim();
        switch (d) {
            case DOWN:
            es.setCurAnim(es.getWalkDown());
            break;
            case UP:
            es.setCurAnim(es.getWalkUp());
            break;
            case LEFT:
            es.setCurAnim(es.getWalkLeft());
            break;
            case RIGHT:
            es.setCurAnim(es.getWalkRight());
            break;
        }
        cur.start();
    }

    public boolean move(Direction dir, Map m) {
        // if not in correct dir, just rotate
        //if (rotate(dir))
        //    return false;
        if (canMove(dir, m)) {
            // set prev tile to walkable
            Tile t = null;
            for (int i = 0; i < 2; i++) {
                t = m.getTile(new Position(position_.getX() / 16 + i,
                position_.getY() / 16));
            t.setHas_Obj(false);
            }
            if(alive)
                animate(dir);
            position_.move(dir);
            // set new tile to nonwalkable
            for (int i = 0; i < 2; i++) {
                t = m.getTile(new Position(position_.getX() / 16 + i,
                position_.getY() / 16));
            t.setHas_Obj(true);
            }
            //t.setHas_Obj(true);
            //if (is_player)
            //    t.run_events();
            return true;
        }
        if (is_player) {
            Tile t = m.getTile(position_.tempPos(dir));
            if (t != null)
                t.run_events();
            /*for(EngineObj eo : m.getEngineObjs())
            {
                for (GameEvents g : eo.events)
                {
                    g.run();
                }
            }*/
        }
        return false;
    }

    private boolean canMove(Direction dir, Map m) {
        Tile t = m.getTile(position_.tempPos(dir));
        if (t == null)
        {
            GameEvents g = new TeleportEvent(this, 10, 10, m);
            g.run();
            return false;
        }
        System.out.println(t.isHas_Obj());
        //System.out.println(getPosition_().getX()+":"+getPosition_().getY()+" -> "+t.get_x() +":"+t.get_y());
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


    /**
     * @return Boolean return the is_player
     */
    public Boolean isIs_player() {
        return is_player;
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

    /**
     * @return EngineSprite return the es
     */
    public EngineSprite getEs() {
        return es;
    }

    /**
     * @param es the es to set
     */
    public void setEs(EngineSprite es) {
        this.es = es;
    }

    /**
     * @return Boolean return the alive
     */
    public Boolean isAlive() {
        return alive;
    }

    /**
     * @param alive the alive to set
     */
    public void setAlive(Boolean alive) {
        this.alive = alive;
    }


    /**
     * @return String return the dialog_
     */
    public String getDialog_() {
        return dialog_;
    }

    /**
     * @param dialog_ the dialog_ to set
     */
    public void setDialog_(String dialog_) {
        this.dialog_ = dialog_;
    }

}