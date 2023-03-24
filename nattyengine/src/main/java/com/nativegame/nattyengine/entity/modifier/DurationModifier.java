package com.nativegame.nattyengine.entity.modifier;

import com.nativegame.nattyengine.entity.Entity;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class DurationModifier<T extends Entity> extends BaseModifier<T> {

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public DurationModifier(long duration) {
        super(duration, 0);
    }

    public DurationModifier(long duration, long startDelay) {
        super(duration, startDelay);
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    protected void onStartModifier(T entity) {
    }

    @Override
    protected void onUpdateModifier(T entity, float durationPercentage) {
    }

    @Override
    protected void onEndModifier(T entity) {
    }

    @Override
    protected void onResetModifier(T entity) {
    }
    //========================================================

}
