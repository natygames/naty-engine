package com.nativegame.nattyengine.entity.sprite.modifier;

import com.nativegame.nattyengine.entity.modifier.tween.Tweener;
import com.nativegame.nattyengine.entity.sprite.Sprite;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class AlphaModifier extends SingleValueSpriteModifier {

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
    protected void onInitValue(Sprite sprite, float alpha) {
        sprite.setAlpha((int) alpha);
    }

    @Override
    protected void onUpdateValue(Sprite sprite, float alpha) {
        sprite.setAlpha((int) alpha);
    }

    @Override
    protected void onEndValue(Sprite sprite, float alpha) {
        sprite.setAlpha((int) alpha);
    }
    //========================================================

}
