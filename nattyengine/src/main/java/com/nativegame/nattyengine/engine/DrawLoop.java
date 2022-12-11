package com.nativegame.nattyengine.engine;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class DrawLoop extends Loop {

    private static final int DELTA_TIME = 16;   // This is 60 fps

    public DrawLoop(GameEngine gameEngine) {
        super(gameEngine);
    }

    public void onLoopUpdate(long elapsedMillis) {
        if (elapsedMillis < DELTA_TIME) {
            try {
                // We make sure each loop is 16ms
                Thread.sleep(DELTA_TIME - elapsedMillis);
            } catch (InterruptedException e) {
                // We stay on the loop
            }
        }
        mGameEngine.draw();
    }

}
