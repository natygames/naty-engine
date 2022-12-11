package com.nativegame.nattyengine.engine;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class UpdateLoop extends Loop {

    public UpdateLoop(GameEngine gameEngine) {
        super(gameEngine);
    }

    @Override
    public void onLoopUpdate(long elapsedMillis) {
        mGameEngine.update(elapsedMillis);
    }

}
