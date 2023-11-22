package com.nativegame.natyengine.input.touch;

import android.view.View;

import com.nativegame.natyengine.util.pool.Pool;
import com.nativegame.natyengine.util.pool.SafeFixedObjectPool;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public abstract class BaseTouchController implements TouchController {

    protected boolean mIsEnable = false;

    protected final Pool<TouchEvent> mTouchEventPool = new SafeFixedObjectPool<>(new Pool.PoolObjectFactory<TouchEvent>() {
        @Override
        public TouchEvent createObject() {
            return new TouchEvent();
        }
    }, 100);
    protected final List<TouchEvent> mTouchEvents = new ArrayList<>();
    protected final List<TouchEvent> mTouchEventsBuffer = new ArrayList<>();

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    protected BaseTouchController(View view) {
        view.setOnTouchListener(this);
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public void start() {
        mIsEnable = true;
    }

    @Override
    public void stop() {
        mIsEnable = false;
        mTouchEventPool.release();
        mTouchEvents.clear();
        mTouchEventsBuffer.clear();
    }

    @Override
    public void pause() {
        mIsEnable = false;
    }

    @Override
    public void resume() {
        mIsEnable = true;
    }
    //========================================================

}
