package com.nativegame.nattyengine.entity.modifier.tween;

import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class LinearTweener implements Tweener {

    private static final Interpolator INTERPOLATOR = new LinearInterpolator();

    private static LinearTweener INSTANCE;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    private LinearTweener() {
    }
    //========================================================

    //--------------------------------------------------------
    // Static methods
    //--------------------------------------------------------
    public static LinearTweener getInstance() {
        if (null == INSTANCE) {
            INSTANCE = new LinearTweener();
        }

        return INSTANCE;
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public float getTweenValue(float percentage) {
        return INTERPOLATOR.getInterpolation(percentage);
    }
    //========================================================

}
