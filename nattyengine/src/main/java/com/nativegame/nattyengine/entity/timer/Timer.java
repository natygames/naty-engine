package com.nativegame.nattyengine.entity.timer;

import com.nativegame.nattyengine.engine.Engine;
import com.nativegame.nattyengine.entity.Entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class Timer extends Entity {

    private TimerListener mListener;
    private long mPeriod;
    private long mTotalTime;
    private boolean mIsLooping = false;

    private final List<TimerEvent> mTimerEvents = new ArrayList<>();

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Timer(Engine engine, TimerListener listener) {
        this(engine, listener, 0);
    }

    public Timer(Engine engine, TimerListener listener, long period) {
        super(engine);
        mListener = listener;
        mPeriod = period;
    }
    //========================================================

    //--------------------------------------------------------
    // Getter and Setter
    //--------------------------------------------------------
    public TimerListener getListener() {
        return mListener;
    }

    public void setListener(TimerListener listener) {
        mListener = listener;
    }

    public long getPeriod() {
        return mPeriod;
    }

    public void setPeriod(long period) {
        mPeriod = period;
    }

    public boolean isLooping() {
        return mIsLooping;
    }

    public void setLooping(boolean looping) {
        mIsLooping = looping;
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public void onUpdate(long elapsedMillis) {
        mTotalTime += elapsedMillis;
        // Dispatch timer event
        int size = mTimerEvents.size();
        for (int i = 0; i < size; i++) {
            TimerEvent event = mTimerEvents.get(i);
            if (!event.isTimerEventDispatch() && mTotalTime >= event.getEventTime()) {
                event.dispatchTimerEvent();
            }
        }
        // Check is time passed
        if (mTotalTime >= mPeriod) {
            if (!mIsLooping) {
                removeFromGame();
            }
            mListener.onTimerComplete(this);
            mTotalTime = 0;
        }
    }

    @Override
    public void reset() {
        super.reset();
        cancelTimer();
        clearTimerEvent();
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    public void startTimer() {
        // Reset event state
        int size = mTimerEvents.size();
        for (int i = 0; i < size; i++) {
            TimerEvent event = mTimerEvents.get(i);
            event.resetTimerEvent();
        }
        addToGame();
        mTotalTime = 0;
    }

    public void cancelTimer() {
        // Check has timer been started yet
        if (isRunning()) {
            removeFromGame();
        }
        mTotalTime = 0;
    }

    public void addTimerEvent(TimerEvent event) {
        mTimerEvents.add(event);
    }

    public void removeTimerEvent(TimerEvent event) {
        mTimerEvents.remove(event);
    }

    public void clearTimerEvent() {
        mTimerEvents.clear();
    }
    //========================================================

    //--------------------------------------------------------
    // Inner Classes
    //--------------------------------------------------------
    public interface TimerListener {

        void onTimerComplete(Timer timer);

    }
    //========================================================

}
