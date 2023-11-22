package com.nativegame.natyengine.entity.modifier;

import com.nativegame.natyengine.entity.shape.Shape;
import com.nativegame.natyengine.util.modifier.DoubleValueModifier;
import com.nativegame.natyengine.util.modifier.tween.LinearTweener;
import com.nativegame.natyengine.util.modifier.tween.Tweener;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public abstract class DoubleValueShapeModifier extends DoubleValueModifier<Shape> {

    private boolean mIsAutoRemove = false;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    protected DoubleValueShapeModifier(float startValueX, float endValueX, float startValueY, float endValueY,
                                       long duration) {
        super(startValueX, endValueX, startValueY, endValueY, duration, 0, LinearTweener.getInstance());
    }

    protected DoubleValueShapeModifier(float startValueX, float endValueX, float startValueY, float endValueY,
                                       long duration, long startDelay) {
        super(startValueX, endValueX, startValueY, endValueY, duration, startDelay, LinearTweener.getInstance());
    }

    protected DoubleValueShapeModifier(float startValueX, float endValueX, float startValueY, float endValueY,
                                       long duration, Tweener tweener) {
        super(startValueX, endValueX, startValueY, endValueY, duration, 0, tweener);
    }

    protected DoubleValueShapeModifier(float startValueX, float endValueX, float startValueY, float endValueY,
                                       long duration, long startDelay, Tweener tweener) {
        super(startValueX, endValueX, startValueY, endValueY, duration, startDelay, tweener);
    }
    //========================================================

    //--------------------------------------------------------
    // Getter and Setter
    //--------------------------------------------------------
    public boolean isAutoRemove() {
        return mIsAutoRemove;
    }

    public void setAutoRemove(boolean autoRemove) {
        mIsAutoRemove = autoRemove;
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    protected void onEndModifier(Shape shape) {
        super.onEndModifier(shape);
        if (mIsAutoRemove) {
            shape.removeFromGame();
        }
    }
    //========================================================

}
