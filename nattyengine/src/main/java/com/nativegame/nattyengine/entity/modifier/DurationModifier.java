package com.nativegame.nattyengine.entity.modifier;

import com.nativegame.nattyengine.entity.Entity;
import com.nativegame.nattyengine.util.modifier.BaseModifier;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class DurationModifier extends BaseModifier<Entity> {

    private boolean mIsAutoRemove = false;

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
    // Getter and Setter
    //--------------------------------------------------------
    public boolean isAutoRemove() {
        return mIsAutoRemove;
    }

    public void setAutoRemove(boolean autoRemove) {
        mIsAutoRemove = autoRemove;
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
        if (mIsAutoRemove) {
            entity.removeFromGame();
        }
    }

    @Override
    protected void onResetModifier(Entity entity) {
    }
    //========================================================

}
