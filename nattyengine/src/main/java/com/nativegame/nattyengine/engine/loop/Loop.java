package com.nativegame.nattyengine.engine.loop;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public interface Loop {

    void startLoop();

    void stopLoop();

    void pauseLoop();

    void resumeLoop();

    void updateLoop(long elapsedMillis);

    boolean isRunning();

    boolean isPaused();

}
