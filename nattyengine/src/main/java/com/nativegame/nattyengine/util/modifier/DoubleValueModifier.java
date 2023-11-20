package com.nativegame.nattyengine.util.modifier;

import com.nativegame.nattyengine.util.modifier.tween.Tweener;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public abstract class DoubleValueModifier<T> extends BaseValueModifier<T> {

    private float mStartValueX;
    private float mStartValueY;
    private float mEndValueX;
    private float mEndValueY;
    private float mRangeX;
    private float mRangeY;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    protected DoubleValueModifier(float startValueX, float endValueX, float startValueY, float endValueY,
                                  long duration, long startDelay, Tweener tweener) {
        super(duration, startDelay, tweener);
        mStartValueX = startValueX;
        mStartValueY = startValueY;
        mEndValueX = endValueX;
        mEndValueY = endValueY;
        mRangeX = endValueX - startValueX;
        mRangeY = endValueY - startValueY;
    }
    //========================================================

    //--------------------------------------------------------
    // Getter and Setter
    //--------------------------------------------------------
    public float getStartValueX() {
        return mStartValueX;
    }

    public float getStartValueY() {
        return mStartValueY;
    }

    public float getEndValueX() {
        return mEndValueX;
    }

    public float getEndValueY() {
        return mEndValueY;
    }

    public void setValue(float startValue, float endValue) {
        setValue(startValue, endValue, startValue, endValue);
    }

    public void setValue(float startValueX, float endValueX, float startValueY, float endValueY) {
        mStartValueX = startValueX;
        mStartValueY = startValueY;
        mEndValueX = endValueX;
        mEndValueY = endValueY;
        mRangeX = endValueX - startValueX;
        mRangeY = endValueY - startValueY;
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    protected void initValue(T entity) {
        onInitValue(entity, mStartValueX, mStartValueY);
    }

    @Override
    protected void updateValue(T entity, float percentage) {
        float currentValueX = mStartValueX + mRangeX * percentage;
        float currentValueY = mStartValueY + mRangeY * percentage;
        onUpdateValue(entity, currentValueX, currentValueY);
    }

    @Override
    protected void endValue(T entity) {
        onEndValue(entity, mEndValueX, mEndValueY);
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    protected abstract void onInitValue(T entity, float valueX, float valueY);

    protected abstract void onUpdateValue(T entity, float valueX, float valueY);

    protected abstract void onEndValue(T entity, float valueX, float valueY);
    //========================================================

}
