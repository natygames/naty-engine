package com.nativegame.natyengine.entity.timer;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class TimerEvent {

    private TimerEventListener mListener;
    private long mEventTime;
    private boolean mIsEventDispatch = false;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public TimerEvent(TimerEventListener listener) {
        this(listener, 0);
    }

    public TimerEvent(TimerEventListener listener, long time) {
        mListener = listener;
        mEventTime = time;
    }
    //========================================================

    //--------------------------------------------------------
    // Getter and Setter
    //--------------------------------------------------------
    public TimerEventListener getListener() {
        return mListener;
    }

    public void setListener(TimerEventListener listener) {
        mListener = listener;
    }

    public long getEventTime() {
        return mEventTime;
    }

    public void setEventTime(long eventTime) {
        mEventTime = eventTime;
    }

    public boolean isTimerEventDispatch() {
        return mIsEventDispatch;
    }

    public void setTimerEventDispatch(boolean eventDispatch) {
        mIsEventDispatch = eventDispatch;
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    public void dispatchTimerEvent() {
        mListener.onTimerEvent(mEventTime);
        mIsEventDispatch = true;
    }

    public void resetTimerEvent() {
        mIsEventDispatch = false;
    }
    //========================================================

    //--------------------------------------------------------
    // Inner Classes
    //--------------------------------------------------------
    public interface TimerEventListener {

        void onTimerEvent(long eventTime);

    }
    //========================================================

}
