package com.nativegame.nattyengine.engine;

public abstract class Loop extends Thread implements Loopable {

    protected final GameEngine mGameEngine;

    private volatile boolean mIsRunning;
    private volatile boolean mIsPause;

    private final Object mLock = new Object();

    public Loop(GameEngine gameEngine) {
        mGameEngine = gameEngine;
    }

    @Override
    public void run() {
        long elapsedTimeMillis;
        long currentTimeMillis;
        long previousTimeMillis = System.currentTimeMillis();

        while (mIsRunning) {
            currentTimeMillis = System.currentTimeMillis();
            elapsedTimeMillis = currentTimeMillis - previousTimeMillis;
            if (mIsPause) {
                while (mIsPause) {
                    try {
                        synchronized (mLock) {
                            mLock.wait();
                        }
                    } catch (InterruptedException e) {
                        // We stay on the loop
                    }
                }
                currentTimeMillis = System.currentTimeMillis();
            }
            onLoopUpdate(elapsedTimeMillis);
            previousTimeMillis = currentTimeMillis;
        }
    }

    @Override
    public void startLoop() {
        mIsRunning = true;
        mIsPause = false;
        start();
    }

    @Override
    public void stopLoop() {
        mIsRunning = false;
        resumeLoop();
    }

    @Override
    public void pauseLoop() {
        mIsPause = true;
    }

    @Override
    public void resumeLoop() {
        if (mIsPause) {
            mIsPause = false;
            synchronized (mLock) {
                mLock.notify();
            }
        }
    }

    @Override
    public boolean isRunning() {
        return mIsRunning;
    }

    @Override
    public boolean isPaused() {
        return mIsPause;
    }

}
