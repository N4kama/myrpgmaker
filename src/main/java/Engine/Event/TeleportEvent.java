package Engine.Event;

import Engine.Direction;
import Engine.Position;
import Engine.Character.EngineObj;
import Game.Map;
import Game.World;

public class TeleportEvent implements GameEvents {

    public TeleportEvent(EngineObj c, int x, int y, World m)
    {
        this.c = c;
        this.p = new Position(x, y);
        this.m = m;
    }
    @Override
    public boolean run() {
        if(m.getCurMap().getTile(p).getIs_Walkable())
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
    private World m;
}