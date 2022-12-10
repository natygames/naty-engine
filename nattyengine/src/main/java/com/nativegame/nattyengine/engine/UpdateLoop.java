package com.nativegame.nattyengine.engine;

public class UpdateLoop extends Loop {

    public UpdateLoop(GameEngine gameEngine) {
        super(gameEngine);
    }

    @Override
    public void onLoopUpdate(long elapsedMillis) {
        mGameEngine.update(elapsedMillis);
    }

}
