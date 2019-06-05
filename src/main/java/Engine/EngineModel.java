package Engine;

import Game.World;

import java.util.Observable;


public class EngineModel extends Observable {
    private World gameWorld_;
    public EngineModel(World w) {
        this.gameWorld_ = w;
    }

    public World getGameWorld() {
        return gameWorld_;
    }

    public void move(Direction d)
    {
        /*if (m.equals("right"))
            gameWorld_.player_.move(Direction.RIGHT, gameWorld_.getCurMap());
        else if (m.equals("left"))
            gameWorld_.player_.move(Direction.RIGHT, gameWorld_.getCurMap());
        else if (m.equals("up"))
            gameWorld_.player_.move(Direction.RIGHT, gameWorld_.getCurMap());
        else if (m.equals("down"))
            gameWorld_.player_.move(Direction.RIGHT, gameWorld_.getCurMap());*/
        setChanged();
        notifyObservers("move");
    }

}
