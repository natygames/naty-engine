package com.nativegame.natyengine.entity.modifier;

import com.nativegame.natyengine.entity.shape.Shape;
import com.nativegame.natyengine.util.modifier.tween.Tweener;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class PositionYModifier extends SingleValueShapeModifier {

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public PositionYModifier(long duration) {
        super(0, 0, duration);
    }

    public PositionYModifier(long duration, long startDelay) {
        super(0, 0, duration, startDelay);
    }

    public PositionYModifier(long duration, Tweener tweener) {
        super(0, 0, duration, tweener);
    }

    public PositionYModifier(long duration, long startDelay, Tweener tweener) {
        super(0, 0, duration, startDelay, tweener);
    }

    public PositionYModifier(float startValue, float endValue, long duration) {
        super(startValue, endValue, duration);
    }

    public PositionYModifier(float startValue, float endValue, long duration, long startDelay) {
        super(startValue, endValue, duration, startDelay);
    }

    public PositionYModifier(float startValue, float endValue, long duration, Tweener tweener) {
        super(startValue, endValue, duration, tweener);
    }

    public PositionYModifier(float startValue, float endValue, long duration, long startDelay, Tweener tweener) {
        super(startValue, endValue, duration, startDelay, tweener);
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    protected void onInitValue(Shape shape, float positionY) {
        shape.setY(positionY);
    }

    @Override
    protected void onUpdateValue(Shape shape, float positionY) {
        shape.setY(positionY);
    }

    @Override
    protected void onEndValue(Shape shape, float positionY) {
        shape.setY(positionY);
    }
    //========================================================

}
