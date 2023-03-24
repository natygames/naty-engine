package com.nativegame.nattyengine.entity.sprite.modifier;

import com.nativegame.nattyengine.entity.modifier.tween.Tweener;
import com.nativegame.nattyengine.entity.sprite.Sprite;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class PositionModifier extends DoubleValueSpriteModifier {

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public PositionModifier(long duration) {
        super(0, 0, 0, 0, duration);
    }

    public PositionModifier(long duration, long startDelay) {
        super(0, 0, 0, 0, duration, startDelay);
    }

    public PositionModifier(long duration, Tweener tweener) {
        super(0, 0, 0, 0, duration, tweener);
    }

    public PositionModifier(long duration, long startDelay, Tweener tweener) {
        super(0, 0, 0, 0, duration, startDelay, tweener);
    }

    public PositionModifier(float startValueX, float endValueX, float startValueY, float endValueY, long duration) {
        super(startValueX, endValueX, startValueY, endValueY, duration);
    }

    public PositionModifier(float startValueX, float endValueX, float startValueY, float endValueY, long duration, long startDelay) {
        super(startValueX, endValueX, startValueY, endValueY, duration, startDelay);
    }

    public PositionModifier(float startValueX, float endValueX, float startValueY, float endValueY, long duration, Tweener tweener) {
        super(startValueX, endValueX, startValueY, endValueY, duration, tweener);
    }

    public PositionModifier(float startValueX, float endValueX, float startValueY, float endValueY, long duration, long startDelay, Tweener tweener) {
        super(startValueX, endValueX, startValueY, endValueY, duration, startDelay, tweener);
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    protected void onInitValue(Sprite sprite, float positionX, float positionY) {
        sprite.setX(positionX);
        sprite.setY(positionY);
    }

    @Override
    protected void onUpdateValue(Sprite sprite, float positionX, float positionY) {
        sprite.setX(positionX);
        sprite.setY(positionY);
    }

    @Override
    protected void onEndValue(Sprite sprite, float positionX, float positionY) {
        sprite.setX(positionX);
        sprite.setY(positionY);
    }
    //========================================================

}
