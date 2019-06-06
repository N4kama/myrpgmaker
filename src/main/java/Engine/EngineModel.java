package Engine;

import Engine.Character.EngineObj;
import Game.World;

import java.util.Observable;


public class EngineModel extends Observable {
    private World gameWorld_;
    public EngineModel(World w) {
        this.gameWorld_ = w;
        for (EngineObj obj: w.getCurMap().getEngineObjs()
             ) {

        Thread myThread = new Thread(() -> {
            while(true) {
                obj.run_events();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });
        myThread.start();
        }

    }

    public World getGameWorld() {
        return gameWorld_;
    }

    public void move(Direction d)
    {
        gameWorld_.player_.move(d, gameWorld_.getCurMap());
        setChanged();
        notifyObservers("move");
    }

    public void stand() {
        setChanged();
        notifyObservers("stand");
    }

}
