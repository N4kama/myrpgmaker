package Engine;

import Engine.Character.Animation;
import Engine.Character.EngineObj;
import Game.World;

import java.util.Observable;

public class EngineModel extends Observable {
    private World gameWorld_;
    private boolean stopMov = false;

    public EngineModel(World w) {
        this.gameWorld_ = w;

    }

    public void setStopMov(boolean stopMov) {
        this.stopMov = stopMov;
    }

    public World getGameWorld() {
        return gameWorld_;
    }

    public void move(Direction d) {
        if (stopMov)
            return;
        gameWorld_.player_.move(d, gameWorld_.getCurMap());
        setChanged();
        notifyObservers(gameWorld_.player_);
    }

    public void stopTalking() {
        gameWorld_.player_.stopTalk();
        setChanged();
        notifyObservers("stopTalk");
    }

    public void talkto() {
        gameWorld_.player_.talkto(gameWorld_.getCurMap());
        setChanged();
        notifyObservers("startTalk");
    }

    public void moveNPC(EngineObj obj) {
        if (stopMov || !obj.run_events())
            return;
        setChanged();
        notifyObservers(obj);
    }

    public void stand() {
        setChanged();
        notifyObservers(gameWorld_.player_);
    }

    public void pause() {
        setChanged();
        notifyObservers(null);
    }

}
