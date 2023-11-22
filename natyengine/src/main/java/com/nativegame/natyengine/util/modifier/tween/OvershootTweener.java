package com.nativegame.natyengine.util.modifier.tween;

import android.view.animation.Interpolator;
import android.view.animation.OvershootInterpolator;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class OvershootTweener implements Tweener {

    private static final Interpolator INTERPOLATOR = new OvershootInterpolator();

    private static OvershootTweener INSTANCE;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    private OvershootTweener() {
    }
    //========================================================

    //--------------------------------------------------------
    // Static methods
    //--------------------------------------------------------
    public static OvershootTweener getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new OvershootTweener();
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
