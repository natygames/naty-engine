package com.nativegame.nattyengine.entity.sprite.modifier;

import com.nativegame.nattyengine.entity.modifier.tween.Tweener;
import com.nativegame.nattyengine.entity.sprite.Sprite;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class RotationModifier extends SingleValueSpriteModifier {

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
    protected void onInitValue(Sprite sprite, float rotation) {
        sprite.setRotation(rotation);
    }

    @Override
    protected void onUpdateValue(Sprite sprite, float rotation) {
        sprite.setRotation(rotation);
    }

    @Override
    protected void onEndValue(Sprite sprite, float rotation) {
        sprite.setRotation(rotation);
    }
    //========================================================

}
