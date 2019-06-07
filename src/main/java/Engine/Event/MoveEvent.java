package Engine.Event;

import java.util.Random;

import Engine.Direction;
import Engine.Character.EngineObj;
import Game.Map;
import Game.World;

public class MoveEvent implements GameEvents {

    public MoveEvent(EngineObj c, Direction d, World m)
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
                return c.move(Direction.UP, m.getCurMap());
                case 1:
                return c.move(Direction.LEFT, m.getCurMap());
                case 2:
                return c.move(Direction.RIGHT, m.getCurMap());
                default:
                return c.move(Direction.DOWN, m.getCurMap());
            }
        }
        return c.move(d, m.getCurMap());
    }

    private EngineObj c;
    private Direction d;
    private World m;
}