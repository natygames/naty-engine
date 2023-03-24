package com.nativegame.nattyengine.entity.sprite.modifier;

import com.nativegame.nattyengine.entity.modifier.tween.Tweener;
import com.nativegame.nattyengine.entity.sprite.Sprite;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class ScaleModifier extends DoubleValueSpriteModifier {

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public ScaleModifier(long duration) {
        super(0, 0, 0, 0, duration);
    }

    public ScaleModifier(long duration, long startDelay) {
        super(0, 0, 0, 0, duration, startDelay);
    }

    public ScaleModifier(long duration, Tweener tweener) {
        super(0, 0, 0, 0, duration, tweener);
    }

    public ScaleModifier(long duration, long startDelay, Tweener tweener) {
        super(0, 0, 0, 0, duration, startDelay, tweener);
    }

    public ScaleModifier(float startValue, float endValue, long duration) {
        super(startValue, endValue, startValue, endValue, duration);
    }

    public ScaleModifier(float startValue, float endValue, long duration, long startDelay) {
        super(startValue, endValue, startValue, endValue, duration, startDelay);
    }

    public ScaleModifier(float startValue, float endValue, long duration, Tweener tweener) {
        super(startValue, endValue, startValue, endValue, duration, tweener);
    }

    public ScaleModifier(float startValue, float endValue, long duration, long startDelay, Tweener tweener) {
        super(startValue, endValue, startValue, endValue, duration, startDelay, tweener);
    }

    public ScaleModifier(float startValueX, float endValueX, float startValueY, float endValueY, long duration) {
        super(startValueX, endValueX, startValueY, endValueY, duration);
    }

    public ScaleModifier(float startValueX, float endValueX, float startValueY, float endValueY, long duration, long startDelay) {
        super(startValueX, endValueX, startValueY, endValueY, duration, startDelay);
    }

    public ScaleModifier(float startValueX, float endValueX, float startValueY, float endValueY, long duration, Tweener tweener) {
        super(startValueX, endValueX, startValueY, endValueY, duration, tweener);
    }

    public ScaleModifier(float startValueX, float endValueX, float startValueY, float endValueY, long duration, long startDelay, Tweener tweener) {
        super(startValueX, endValueX, startValueY, endValueY, duration, startDelay, tweener);
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    protected void onInitValue(Sprite sprite, float scaleX, float scaleY) {
        sprite.setScaleX(scaleX);
        sprite.setScaleY(scaleY);
    }

    @Override
    protected void onUpdateValue(Sprite sprite, float scaleX, float scaleY) {
        sprite.setScaleX(scaleX);
        sprite.setScaleY(scaleY);
    }

    @Override
    protected void onEndValue(Sprite sprite, float scaleX, float scaleY) {
        sprite.setScaleX(scaleX);
        sprite.setScaleY(scaleY);
    }
    //========================================================

}
