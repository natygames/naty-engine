package com.nativegame.nattyengine.engine;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public interface Loopable {

    void startLoop();

    void stopLoop();

    void pauseLoop();

    void resumeLoop();

    boolean isRunning();

    boolean isPaused();

    void onLoopUpdate(long elapsedMillis);

}
