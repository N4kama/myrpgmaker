package Engine;

import Game.World;

import java.util.Observable;

public class EngineModel {
    private World gameWorld_;
    public EngineModel(World w) {
        this.gameWorld_ = w;
    }

    public World getGameWorld() {
        return gameWorld_;
    }
}
