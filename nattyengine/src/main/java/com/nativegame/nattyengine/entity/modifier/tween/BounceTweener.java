package com.nativegame.nattyengine.entity.modifier.tween;

import android.view.animation.BounceInterpolator;
import android.view.animation.Interpolator;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class BounceTweener implements Tweener {

    private static final Interpolator INTERPOLATOR = new BounceInterpolator();

    private static BounceTweener INSTANCE;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    private BounceTweener() {
    }
    //========================================================

    //--------------------------------------------------------
    // Static methods
    //--------------------------------------------------------
    public static BounceTweener getInstance() {
        if (null == INSTANCE) {
            INSTANCE = new BounceTweener();
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
