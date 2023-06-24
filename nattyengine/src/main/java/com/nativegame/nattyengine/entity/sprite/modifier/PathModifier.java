package com.nativegame.nattyengine.entity.sprite.modifier;

import android.graphics.Path;
import android.graphics.PathMeasure;

import com.nativegame.nattyengine.entity.modifier.BaseValueModifier;
import com.nativegame.nattyengine.entity.modifier.tween.Tweener;
import com.nativegame.nattyengine.entity.sprite.Sprite;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class PathModifier extends BaseValueModifier<Sprite> {

    private final PathMeasure mPathMeasure;

    private final float[] mPathPosition = {0f, 0f};

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public PathModifier(Path path, long duration, long startDelay, Tweener tweener) {
        super(duration, startDelay, tweener);
        mPathMeasure = new PathMeasure(path, false);
    }
    //========================================================

    //--------------------------------------------------------
    // Getter and Setter
    //--------------------------------------------------------
    public void setPath(Path path) {
        mPathMeasure.setPath(path, false);
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    protected void initValue(Sprite sprite) {
        mPathMeasure.getPosTan(0, mPathPosition, null);
        sprite.setX(mPathPosition[0]);
        sprite.setY(mPathPosition[1]);
    }

    @Override
    protected void updateValue(Sprite sprite, float percentage) {
        float percentageValue = mPathMeasure.getLength() * percentage;
        mPathMeasure.getPosTan(percentageValue, mPathPosition, null);
        sprite.setX(mPathPosition[0]);
        sprite.setY(mPathPosition[1]);
    }

    @Override
    protected void endValue(Sprite sprite) {
        mPathMeasure.getPosTan(mPathMeasure.getLength(), mPathPosition, null);
        sprite.setX(mPathPosition[0]);
        sprite.setY(mPathPosition[1]);
    }
    //========================================================

}
