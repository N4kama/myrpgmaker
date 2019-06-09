package Engine.Event;

import java.awt.image.BufferedImage;

import Engine.Direction;
import Engine.Position;
import Engine.Character.EngineObj;
import Game.Map;
import Game.Tile;
import Game.World;
import Utils.SpriteTools;

public class ChangeMapEvent implements GameEvents {

    public ChangeMapEvent(EngineObj c, int id_map, Position np, World m) {
        this.c = c;
        this.p = m.getMap(id_map);
        this.np = np;
        this.m = m;
        BufferedImage img = SpriteTools.openObject(c.getSprite_());
        this.w = img.getWidth() / 16;
        this.h = img.getHeight() / 16;
    }

    @Override
    public boolean run() {
        System.out.println(c.get_x() + ":" + c.get_y());
        EngineObj e = m.player_;
        if (e.isIs_player() && e.getPosition_().is_in(c.getPosition_(), w, h)) {
            Tile t = m.getCurMap().getTile(e.getPosition_());
            m.changeMap(m.getGameWorld().indexOf(p));
            t.setHas_Obj(false);
            e.setChangedMap(true);
            e.setCur_map(p);
            e.setPosition_(new Position(np.getX() % p.getWidth(), np.getY() %p.getHeight()));
            t = p.getTile(e.getPosition_());
            t.setHas_Obj(true);
            return true;
        }
        return false;
    }

    /**
     * @return the p
     */
    public Map getP() {
        return p;
    }

    /**
     * @return the np
     */
    public Position getNp() {
        return np;
    }

    private EngineObj c;
    private Map p;
    private Position np;
    private World m;
    private int h;
    private int w;
}