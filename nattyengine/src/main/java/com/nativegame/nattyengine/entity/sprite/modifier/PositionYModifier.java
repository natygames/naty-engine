package com.nativegame.nattyengine.entity.sprite.modifier;

import com.nativegame.nattyengine.entity.modifier.tween.Tweener;
import com.nativegame.nattyengine.entity.sprite.Sprite;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class PositionYModifier extends SingleValueSpriteModifier {

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
    protected void onInitValue(Sprite sprite, float positionY) {
        sprite.setY(positionY);
    }

    @Override
    protected void onUpdateValue(Sprite sprite, float positionY) {
        sprite.setY(positionY);
    }

    @Override
    protected void onEndValue(Sprite sprite, float positionY) {
        sprite.setY(positionY);
    }
    //========================================================

}
