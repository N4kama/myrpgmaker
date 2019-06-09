package Engine.Event;

import java.util.Random;

import Editor.EditorModel;
import Engine.Direction;
import Engine.Character.EngineObj;
import Game.Map;
import Game.World;

public class MoveEvent implements GameEvents {

    public MoveEvent(EngineObj c, Direction d, World m) {
        this.c = m.getCurMap().getEngineObjs().indexOf(c);
        this.d = d;
    }

    public MoveEvent() {
        this.c = EditorModel.singleton.gameWorld.getCurMap().getEngineObjs().indexOf(c);
        this.d = null;
    }

    @Override
    public boolean run() {
        World w = EditorModel.singleton.gameWorld;
        EngineObj e = w.getCurMap().getEngineObjs().get(c);
        if (d == null) {
            Random rnd = new Random();
            switch (rnd.nextInt(3)) {
            case 0:
                return e.move(Direction.UP, w.getCurMap());
            case 1:
                return e.move(Direction.LEFT, w.getCurMap());
            case 2:
                return e.move(Direction.RIGHT, w.getCurMap());
            default:
                return e.move(Direction.DOWN, w.getCurMap());
            }
        }
        return e.move(d, w.getCurMap());
    }

    private int c;
    private Direction d;
}