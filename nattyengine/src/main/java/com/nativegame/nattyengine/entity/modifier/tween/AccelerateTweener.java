package com.nativegame.nattyengine.entity.modifier.tween;

import android.view.animation.AccelerateInterpolator;
import android.view.animation.Interpolator;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class AccelerateTweener implements Tweener {

    private static final Interpolator INTERPOLATOR = new AccelerateInterpolator();

    private static AccelerateTweener INSTANCE;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    private AccelerateTweener() {
    }
    //========================================================

    //--------------------------------------------------------
    // Static methods
    //--------------------------------------------------------
    public static AccelerateTweener getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AccelerateTweener();
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
