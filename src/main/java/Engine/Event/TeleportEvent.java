package Engine.Event;

import java.awt.image.BufferedImage;

import Engine.Direction;
import Engine.Position;
import Engine.Character.EngineObj;
import Game.Map;
import Game.Tile;
import Game.World;
import Utils.SpriteTools;

public class TeleportEvent implements GameEvents {

    public TeleportEvent(EngineObj c, int x, int y, World m) {
        this.c = c;
        this.p = new Position(x, y);
        this.m = m;
        BufferedImage img = SpriteTools.openObject(c.getSprite_());
        this.w = img.getWidth() / 16;
        this.h = img.getHeight() / 16;
    }

    /**
     * @return the p
     */
    public Position getP() {
        return p;
    }

    @Override
    public boolean run() {
        System.out.println(c.get_x() + ":" + c.get_y());
        for (EngineObj e : m.getCurMap().getEngineObjs()) {
            if (e.isAlive() && e.getPosition_().is_in(c.getPosition_(), w, h)) {
                if (m.getCurMap().getTile(p).getIs_Walkable()) {
                    Tile t = m.getCurMap().getTile(e.getPosition_());
                    t.setHas_Obj(false);
                    e.setTeleportedPos(e.getPosition_());
                    e.setTeleported(true);
                    e.setPosition_(p);
                    t = m.getCurMap().getTile(e.getPosition_());
                    t.setHas_Obj(true);
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    private EngineObj c;
    private Position p;
    private World m;
    private int h;
    private int w;
}