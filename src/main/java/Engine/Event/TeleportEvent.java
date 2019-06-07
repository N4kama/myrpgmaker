package Engine.Event;

import Engine.Direction;
import Engine.Position;
import Engine.Character.EngineObj;
import Game.Map;
import Game.World;

public class TeleportEvent implements GameEvents {

    public TeleportEvent(EngineObj c, int x, int y, World m) {
        this.c = c;
        this.p = new Position(x, y);
        this.m = m;
    }

    /**
     * @return the p
     */
    public Position getP() {
        return p;
    }

    @Override
    public boolean run() {
        System.out.println(c.get_x() +":"+c.get_y());
        for (EngineObj e : m.getCurMap().getEngineObjs()) {
            if (e.isAlive() && e.getPosition_().equals(c.getPosition_())) {
                if (m.getCurMap().getTile(p).getIs_Walkable()) {
                    e.setTeleportedPos(e.getPosition_());
                    e.setTeleported(true);
                    e.setPosition_(p);
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
}