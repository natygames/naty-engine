package com.nativegame.nattyengine.entity.sprite.modifier;

import com.nativegame.nattyengine.entity.modifier.SingleValueModifier;
import com.nativegame.nattyengine.entity.modifier.tween.LinearTweener;
import com.nativegame.nattyengine.entity.modifier.tween.Tweener;
import com.nativegame.nattyengine.entity.sprite.Sprite;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public abstract class SingleValueSpriteModifier extends SingleValueModifier<Sprite> {

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    protected SingleValueSpriteModifier(float startValue, float endValue, long duration) {
        super(startValue, endValue, duration, 0, LinearTweener.getInstance());
    }

    protected SingleValueSpriteModifier(float startValue, float endValue, long duration, long startDelay) {
        super(startValue, endValue, duration, startDelay, LinearTweener.getInstance());
    }

    protected SingleValueSpriteModifier(float startValue, float endValue, long duration, Tweener tweener) {
        super(startValue, endValue, duration, 0, tweener);
    }

    protected SingleValueSpriteModifier(float startValue, float endValue, long duration, long startDelay, Tweener tweener) {
        super(startValue, endValue, duration, startDelay, tweener);
    }
    //========================================================

}
