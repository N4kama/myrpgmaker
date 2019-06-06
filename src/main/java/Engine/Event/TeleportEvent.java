package Engine.Event;

import Engine.Direction;
import Engine.Position;
import Engine.Character.EngineObj;
import Game.Map;

public class TeleportEvent implements GameEvents {

    public TeleportEvent(EngineObj c, int x, int y, Map m)
    {
        this.c = c;
        this.p = new Position(x, y);
        this.m = m;
    }
    @Override
    public boolean run() {
        if(m.getTile(p).getIs_Walkable())
            {
                c.setTeleportedPos(c.getPosition_());
                c.setTeleported(true);
                c.setPosition_(p);
                return true;
            }
        return false;
    }

    private EngineObj c;
    private Position p;
    private Map m;
}