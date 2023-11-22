package com.nativegame.natyengine.entity.modifier;

import com.nativegame.natyengine.entity.shape.Shape;
import com.nativegame.natyengine.util.modifier.tween.Tweener;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class AlphaModifier extends SingleValueShapeModifier {

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public AlphaModifier(long duration) {
        super(0, 0, duration);
    }

    public AlphaModifier(long duration, long startDelay) {
        super(0, 0, duration, startDelay);
    }

    public AlphaModifier(long duration, Tweener tweener) {
        super(0, 0, duration, tweener);
    }

    public AlphaModifier(long duration, long startDelay, Tweener tweener) {
        super(0, 0, duration, startDelay, tweener);
    }

    public AlphaModifier(float startValue, float endValue, long duration) {
        super(startValue, endValue, duration);
    }

    public AlphaModifier(float startValue, float endValue, long duration, long startDelay) {
        super(startValue, endValue, duration, startDelay);
    }

    public AlphaModifier(float startValue, float endValue, long duration, Tweener tweener) {
        super(startValue, endValue, duration, tweener);
    }

    public AlphaModifier(float startValue, float endValue, long duration, long startDelay, Tweener tweener) {
        super(startValue, endValue, duration, startDelay, tweener);
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    protected void onInitValue(Shape shape, float alpha) {
        shape.setAlpha((int) alpha);
    }

    @Override
    protected void onUpdateValue(Shape shape, float alpha) {
        shape.setAlpha((int) alpha);
    }

    @Override
    protected void onEndValue(Shape shape, float alpha) {
        shape.setAlpha((int) alpha);
    }
    //========================================================

}
