package Engine.Character;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import Editor.EditorModel;
import Engine.Direction;
import Engine.Position;
import Game.Map;
import Game.Tile;
import Engine.Event.ChangeMapEvent;
import Engine.Event.GameEvents;
import Engine.Event.MoveEvent;
import Engine.Event.TeleportEvent;

public class EngineObj {
    private transient Map cur_map;

    private Boolean is_player;
    private List<GameEvents> events;
    private boolean teleported;
    private Position teleportedPos;
    private transient EngineSprite es;
    private boolean stop = false;
    private boolean changedMap = false;
    private transient EngineObj talkTo;

    public EngineObj(int x, int y, String sprite_path) {
        this.position_ = new Position(x, y);
        this.sprite_ = sprite_path;
        alive = false;
        is_player = false;
        events = new ArrayList<>();
        name_ = "NPC";
    }

    public Map getCur_map() {
        return cur_map;
    }

    public void setCur_map(Map cur_map_) {
        if(cur_map !=  null)
        {
            this.cur_map.getEngineObjs().remove(this);
        }
        cur_map_.addEngineObj(this);
        this.cur_map = cur_map_;
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

    public void reloadSprites() {
        setEs(new EngineSprite(sprite_));
    }

    public boolean run_events() {
        boolean res = true;
        for (GameEvents e : events) {
            res &= e.run();
        }
        return res;
    }

    public void animate(Direction d) {
        if(es == null)
        {
            reloadSprites();
        }
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
        if (rotate(dir))
            return false;
        if (stop)
            return false;
        if (canMove(dir, m)) {
            Tile t = m.getTile(position_);
            t.setHas_Obj(false);
            if (alive)
                animate(dir);
            position_.move(dir);
            t = m.getTile(position_);
            t.setHas_Obj(true);
            return true;
        }
        if (is_player) {
            Tile t = m.getTile(position_.tempPos(dir));
            if (t != null)
                t.run_events();
        }
        return false;
    }

    public void stopTalk() {
        if (talkTo != null)
            talkTo.stop = false;
        stop = false;
        talkTo = null;
    }

    public boolean talkto(Map m) {
        Direction dir = getDir();
        for (EngineObj e : m.getEngineObjs()) {
            if (e.position_.equals(position_.tempPos(dir))) {
                e.stop = true;
                stop = true;
                talkTo = e;
                System.out.println(e.dialog_);
                return true;
            }
        }
        return false;
    }

    private boolean canMove(Direction dir, Map m) {
        Tile t = m.getTile(position_.tempPos(dir));
        if (t == null) {
            ChangeMapEvent e = (new ChangeMapEvent(this, EditorModel.singleton.gameWorld.nextMap(), new Position(0, 0),
                    EditorModel.singleton.gameWorld));
            e.run();
            return false;
        }
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

    // GETTER SETTER

    /**
     * @return the talkTo
     */
    public EngineObj getTalkTo() {
        return talkTo;
    }

    public boolean getChangedMap() {
        return changedMap;
    }

    /**
     * @param changedMap the changedMap to set
     */
    public void setChangedMap(boolean changedMap) {
        this.changedMap = changedMap;
    }

    public void setTeleported(boolean b) {
        teleported = b;
    }

    public boolean getTeleported() {
        return teleported;
    }

    public void setTeleportedPos(Position b) {
        teleportedPos = b;
    }

    public Position getTeleportedPos() {
        return teleportedPos;
    }

    public Boolean getAlive() {
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

    // dialog
    private String dialog_ = "I have nothing to tell you !";

    public void setIs_player(Boolean is_player) {
        this.is_player = is_player;
    }

    public Boolean getIs_player() {
        return is_player;
    }

    public String getDialog() {
        return dialog_;
    }

    public void setDialog(String d) {
        dialog_ = d;
    }

    public void set_map(Map m) {
        position_ = m.getSpawn();
    }

    public void add_event(GameEvents e) {
        events.add(e);
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