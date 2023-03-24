package com.nativegame.nattyengine.entity.modifier;

import com.nativegame.nattyengine.entity.Entity;
import com.nativegame.nattyengine.entity.modifier.tween.Tweener;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public abstract class BaseValueModifier<T extends Entity> extends BaseModifier<T> {

    private Tweener mTweener;
    private boolean mIsResetBefore = true;
    private boolean mIsModifyBefore = true;
    private boolean mIsModifyAfter = true;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    protected BaseValueModifier(long duration, long startDelay, Tweener tweener) {
        super(duration, startDelay);
        mTweener = tweener;
    }
    //========================================================

    //--------------------------------------------------------
    // Getter and Setter
    //--------------------------------------------------------
    public Tweener getTweener() {
        return mTweener;
    }

    public void setTweener(Tweener tweener) {
        mTweener = tweener;
    }

    public boolean isResetBefore() {
        return mIsResetBefore;
    }

    public void setResetBefore(boolean resetBefore) {
        mIsResetBefore = resetBefore;
    }

    public boolean isModifyBefore() {
        return mIsModifyBefore;
    }

    public void setModifyBefore(boolean modifyBefore) {
        mIsModifyBefore = modifyBefore;
    }

    public boolean isModifyAfter() {
        return mIsModifyAfter;
    }

    public void setModifyAfter(boolean modifyAfter) {
        mIsModifyAfter = modifyAfter;
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    protected void onStartModifier(T entity) {
        if (mIsModifyBefore) {
            initValue(entity);
        }
    }

    @Override
    protected void onUpdateModifier(T entity, float durationPercentage) {
        float percentage = mTweener.getTweenValue(durationPercentage);
        updateValue(entity, percentage);
    }

    @Override
    protected void onEndModifier(T entity) {
        if (mIsModifyAfter) {
            endValue(entity);
        }
    }

    @Override
    protected void onResetModifier(T entity) {
        if (mIsResetBefore) {
            initValue(entity);
        } else {
            endValue(entity);
        }
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    protected abstract void initValue(T entity);

    protected abstract void updateValue(T entity, float percentage);

    protected abstract void endValue(T entity);
    //========================================================

}
