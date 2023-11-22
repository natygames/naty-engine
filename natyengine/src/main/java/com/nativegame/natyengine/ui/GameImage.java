package com.nativegame.natyengine.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.Interpolator;
import android.view.animation.OvershootInterpolator;

import androidx.appcompat.widget.AppCompatImageView;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class GameImage extends AppCompatImageView {

    public static final OvershootInterpolator DEFAULT_POP_UP_INTERPOLATOR = new OvershootInterpolator();

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public GameImage(Context context) {
        super(context);
    }

    public GameImage(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
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
