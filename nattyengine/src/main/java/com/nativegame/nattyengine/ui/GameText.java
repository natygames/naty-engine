package com.nativegame.nattyengine.ui;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.BounceInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.OvershootInterpolator;

import androidx.appcompat.widget.AppCompatTextView;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class GameText extends AppCompatTextView {

    public static final OvershootInterpolator DEFAULT_POP_UP_INTERPOLATOR = new OvershootInterpolator();
    public static final BounceInterpolator DEFAULT_PRESS_INTERPOLATOR = new BounceInterpolator();
    public static final int DEFAULT_PRESS_COLOR = Color.parseColor("#66000000");
    public static final float DEFAULT_PRESS_SCALE_X = 0.8f;
    public static final float DEFAULT_PRESS_SCALE_Y = 0.8f;
    public static final long DEFAULT_PRESS_DURATION = 300;

    private Interpolator mPressInterpolator = DEFAULT_PRESS_INTERPOLATOR;
    private int mPressColor = DEFAULT_PRESS_COLOR;
    private float mPressScaleX = DEFAULT_PRESS_SCALE_X;
    private float mPressScaleY = DEFAULT_PRESS_SCALE_Y;
    private long mPressDuration = DEFAULT_PRESS_DURATION;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public GameText(Context context) {
        super(context);
        // To create view from Java
    }

    public GameText(Context context, AttributeSet attrs) {
        super(context, attrs);
        // To create view from xml
    }
    //========================================================

    //--------------------------------------------------------
    // Getter and Setter
    //--------------------------------------------------------
    public Interpolator getPressInterpolator() {
        return mPressInterpolator;
    }

    public void setPressInterpolator(Interpolator interpolator) {
        mPressInterpolator = interpolator;
    }

    public int getPressColor() {
        return mPressColor;
    }

    public void setPressColor(int color) {
        mPressColor = color;
    }

    public float getPressScaleX() {
        return mPressScaleX;
    }

    public void setPressScaleX(float scaleX) {
        mPressScaleX = scaleX;
    }

    public float getPressScaleY() {
        return mPressScaleY;
    }

    public void setPressScaleY(float scaleY) {
        mPressScaleY = scaleY;
    }

    public long getPressDuration() {
        return mPressDuration;
    }

    public void setPressDuration(long duration) {
        mPressDuration = duration;
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!isEnabled()) {
            return true;
        }
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                animate().setStartDelay(0)
                        .setDuration(mPressDuration)
                        .scaleX(mPressScaleX)
                        .scaleY(mPressScaleY)
                        .setInterpolator(mPressInterpolator);
                addColorFilter(mPressColor);
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                animate().setStartDelay(0)
                        .setDuration(mPressDuration)
                        .scaleX(1)
                        .scaleY(1)
                        .setInterpolator(mPressInterpolator);
                removeColorFilter();
                break;
        }

        return super.onTouchEvent(event);
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    public void addColorFilter(int color) {
        if (getBackground() == null) {
            return;
        }
        getBackground().setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
        invalidate();
    }

    public void removeColorFilter() {
        if (getBackground() == null) {
            return;
        }
        getBackground().clearColorFilter();
        invalidate();
    }

    public void popUp(long duration) {
        popUp(duration, 0, DEFAULT_POP_UP_INTERPOLATOR);
    }

    public void popUp(long duration, Interpolator interpolator) {
        popUp(duration, 0, interpolator);
    }

    public void popUp(long duration, long startDelay) {
        popUp(duration, startDelay, DEFAULT_POP_UP_INTERPOLATOR);
    }

    public void popUp(long duration, long startDelay, Interpolator interpolator) {
        setScaleX(0);
        setScaleY(0);
        animate().setStartDelay(startDelay)
                .setDuration(duration)
                .scaleX(1)
                .scaleY(1)
                .setInterpolator(interpolator);
    }
    //========================================================

}
