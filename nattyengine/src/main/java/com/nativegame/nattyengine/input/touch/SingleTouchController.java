package com.nativegame.nattyengine.input.touch;

import android.view.MotionEvent;
import android.view.View;

import java.util.List;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class SingleTouchController extends BaseTouchController {

    private float mTouchX;
    private float mTouchY;
    private boolean mIsTouchDown = false;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public SingleTouchController(View view) {
        super(view);
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public float getTouchX() {
        synchronized (this) {
            return mTouchX;
        }
    }

    @Override
    public float getTouchY() {
        synchronized (this) {
            return mTouchY;
        }
    }

    @Override
    public boolean isTouchDown() {
        synchronized (this) {
            return mIsTouchDown;
        }
    }

    @Override
    public List<TouchEvent> getTouchEvents() {
        synchronized (this) {
            // Remove previous event and add new event
            int eventCount = mTouchEvents.size();
            for (int i = 0; i < eventCount; i++) {
                mTouchEventPool.returnObject(mTouchEvents.get(i));
            }
            mTouchEvents.clear();
            mTouchEvents.addAll(mTouchEventsBuffer);
            mTouchEventsBuffer.clear();
            return mTouchEvents;
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        synchronized (this) {
            if (!mIsEnable) {
                return true;
            }
            TouchEvent touchEvent = mTouchEventPool.obtainObject();
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    touchEvent.setType(TouchEvent.TOUCH_DOWN);
                    mIsTouchDown = true;
                    break;
                case MotionEvent.ACTION_MOVE:
                    touchEvent.setType(TouchEvent.TOUCH_DRAGGED);
                    mIsTouchDown = true;
                    break;
                case MotionEvent.ACTION_CANCEL:
                case MotionEvent.ACTION_UP:
                    touchEvent.setType(TouchEvent.TOUCH_UP);
                    mIsTouchDown = false;
                    break;
            }
            mTouchX = event.getX();
            mTouchY = event.getY();
            touchEvent.setTouchX(mTouchX);
            touchEvent.setTouchY(mTouchY);
            mTouchEventsBuffer.add(touchEvent);

            return true;
        }
    }
    //========================================================

}
