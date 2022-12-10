package com.nativegame.nattyengine.engine;

public interface Loopable {

    void startLoop();

    void stopLoop();

    void pauseLoop();

    void resumeLoop();

    boolean isRunning();

    boolean isPaused();

    void onLoopUpdate(long elapsedMillis);

}
