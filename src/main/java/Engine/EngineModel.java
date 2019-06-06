package Engine;

import Engine.Character.Animation;
import Engine.Character.EngineObj;
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

    public void move(Direction d) {
        
        gameWorld_.player_.move(d, gameWorld_.getCurMap());
        setChanged();
        notifyObservers(gameWorld_.player_);
    }

    public void moveNPC(EngineObj obj) {
        obj.run_events();
        setChanged();
        notifyObservers(obj);
    }

    public void stand() {
        setChanged();
        notifyObservers(gameWorld_.player_);
    }

}
