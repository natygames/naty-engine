package com.nativegame.nattyengine.entity.timer;

import com.nativegame.nattyengine.engine.Engine;
import com.nativegame.nattyengine.entity.Entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class Timer extends Entity {

    private int mEventCount;
    private long mTotalEventTime;
    private long mTotalTime;
    private boolean mIsLooping;
    private boolean mIsTimerRunning = false;

    private final List<TimerEvent> mEvents = new ArrayList<>();

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Timer(Engine engine) {
        this(engine, false);
    }

    public Timer(Engine engine, boolean isLooping) {
        super(engine);
        mIsLooping = isLooping;
    }
    //========================================================

    //--------------------------------------------------------
    // Getter and Setter
    //--------------------------------------------------------
    public boolean isLooping() {
        return mIsLooping;
    }

    public void setLooping(boolean looping) {
        mIsLooping = looping;
    }

    public List<TimerEvent> getAllTimerEvents() {
        return mEvents;
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public void onUpdate(long elapsedMillis) {
        if (!mIsTimerRunning) {
            return;
        }
        mTotalTime += elapsedMillis;

        // Dispatch timer event
        int eventCount = mEvents.size();
        for (int i = 0; i < eventCount; i++) {
            TimerEvent event = mEvents.get(i);
            // Dispatch one event if time passed
            if (!event.isTimerEventDispatch() && mTotalTime >= event.getEventTime()) {
                event.dispatchTimerEvent();
                mEventCount--;
            }
        }

        // Check is total time passed
        if (mEventCount <= 0) {
            if (mIsLooping) {
                mTotalTime = mTotalTime % mTotalEventTime;
                // Reset event state
                for (int i = 0; i < mEventCount; i++) {
                    TimerEvent event = mEvents.get(i);
                    event.resetTimerEvent();
                }
            } else {
                stopTimer();
            }
        }
    }

    @Override
    public void reset() {
        super.reset();
        stopTimer();
        clearTimerEvent();
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    public void startTimer() {
        mIsTimerRunning = true;
        resetTimer();
        addToGame();
    }

    public void stopTimer() {
        // Check has timer been started yet
        if (isRunning()) {
            removeFromGame();
        }
        mIsTimerRunning = false;
        mEventCount = 0;
        mTotalEventTime = 0;
        mTotalTime = 0;
    }

    public void pauseTimer() {
        mIsTimerRunning = false;
    }

    public void resumeTimer() {
        mIsTimerRunning = true;
    }

    public void resetTimer() {
        mEventCount = mEvents.size();
        mTotalEventTime = 0;
        mTotalTime = 0;
        // Reset event state
        for (int i = 0; i < mEventCount; i++) {
            TimerEvent event = mEvents.get(i);
            event.resetTimerEvent();
            if (event.getEventTime() > mTotalEventTime) {
                mTotalEventTime = event.getEventTime();
            }
        }
    }

    public void addTimerEvent(TimerEvent event) {
        mEvents.add(event);
    }

    public void removeTimerEvent(TimerEvent event) {
        mEvents.remove(event);
    }

    public void clearTimerEvent() {
        mEvents.clear();
    }
    //========================================================

}
