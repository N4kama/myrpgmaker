package Engine.Event;

import java.util.Random;

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
        if(d == null)
        {
            Random rnd = new Random();
            switch(rnd.nextInt(3))
            {
                case 0:
                return c.move(Direction.UP, m);
                case 1:
                return c.move(Direction.LEFT, m);
                case 2:
                return c.move(Direction.RIGHT, m);
                default:
                return c.move(Direction.DOWN, m);
            }
        }
        return c.move(d, m);
    }

    private EngineObj c;
    private Direction d;
    private Map m;
}