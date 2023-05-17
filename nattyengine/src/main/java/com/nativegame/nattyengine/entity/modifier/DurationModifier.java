package com.nativegame.nattyengine.entity.modifier;

import com.nativegame.nattyengine.entity.Entity;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class DurationModifier extends BaseModifier<Entity> {

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
    protected void onStartModifier(Entity entity) {
    }

    @Override
    protected void onUpdateModifier(Entity entity, float durationPercentage) {
    }

    @Override
    protected void onEndModifier(Entity entity) {
    }

    @Override
    protected void onResetModifier(Entity entity) {
    }
    //========================================================

}
