package com.nativegame.nattyengine.input.touch;

import android.view.View;

import com.nativegame.nattyengine.util.pool.ObjectPool;
import com.nativegame.nattyengine.util.pool.Pool;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public abstract class BaseTouchController implements TouchController {

    protected final Pool<TouchEvent> mTouchEventPool;

    protected boolean mIsEnable = false;

    protected final List<TouchEvent> mTouchEvents = new ArrayList<>();
    protected final List<TouchEvent> mTouchEventsBuffer = new ArrayList<>();

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    protected BaseTouchController(View view) {
        mTouchEventPool = new ObjectPool<>(new Pool.PoolObjectFactory<TouchEvent>() {
            @Override
            public TouchEvent createObject() {
                return new TouchEvent();
            }
        }, 100);
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
