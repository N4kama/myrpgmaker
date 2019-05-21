package Engine.Event;

import Engine.Direction;
import Engine.Character.EngineObj;
import Game.Map;

public class MoveEvent implements GameEvents {

    public MoveEvent(EngineObj c, Direction d, Map m)
    {
        this.c = c;
        this.d = d;
        this.m = m;
    }
    @Override
    public boolean run() {
        return c.move(d, m);
    }

    private EngineObj c;
    private Direction d;
    private Map m;
}