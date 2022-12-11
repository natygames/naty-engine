package com.nativegame.nattyengine.input;

import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class TouchController implements View.OnTouchListener {

    public float mXDown, mYDown;   // Player press position

    public float mXUp, mYUp;   // Player release position

    public boolean mTouching;

    protected void onActionDown() {
        mTouching = true;
    }

    protected void onActionMove() {
        mTouching = true;
    }

    protected void onActionUp() {
        mTouching = false;
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mXDown = (int) event.getX();
                mYDown = (int) event.getY();
                onActionDown();
                break;
            case MotionEvent.ACTION_MOVE:
                mXDown = (int) event.getX();
                mYDown = (int) event.getY();
                onActionMove();
                break;
            case MotionEvent.ACTION_UP:
                mXUp = (int) event.getX();
                mYUp = (int) event.getY();
                onActionUp();
                break;
        }

        return true;
    }

}
