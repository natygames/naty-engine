package com.nativegame.natyengine.entity.modifier;

import com.nativegame.natyengine.entity.shape.Shape;
import com.nativegame.natyengine.util.modifier.tween.Tweener;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class RotationModifier extends SingleValueShapeModifier {

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public RotationModifier(float startValue, float endValue, long duration) {
        super(startValue, endValue, duration);
    }

    public RotationModifier(float startValue, float endValue, long duration, long startDelay) {
        super(startValue, endValue, duration, startDelay);
    }

    public RotationModifier(float startValue, float endValue, long duration, Tweener tweener) {
        super(startValue, endValue, duration, tweener);
    }

    public RotationModifier(float startValue, float endValue, long duration, long startDelay, Tweener tweener) {
        super(startValue, endValue, duration, startDelay, tweener);
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    protected void onInitValue(Shape shape, float rotation) {
        shape.setRotation(rotation);
    }

    @Override
    protected void onUpdateValue(Shape shape, float rotation) {
        shape.setRotation(rotation);
    }

    @Override
    protected void onEndValue(Shape shape, float rotation) {
        shape.setRotation(rotation);
    }
    //========================================================

}
