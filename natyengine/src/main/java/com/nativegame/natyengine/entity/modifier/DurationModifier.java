package com.nativegame.natyengine.entity.modifier;

import com.nativegame.natyengine.entity.Updatable;
import com.nativegame.natyengine.util.modifier.BaseModifier;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class DurationModifier extends BaseModifier<Updatable> {

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
    protected void onStartModifier(Updatable entity) {
    }

    @Override
    protected void onUpdateModifier(Updatable entity, float durationPercentage) {
    }

    @Override
    protected void onEndModifier(Updatable entity) {
        if (mIsAutoRemove) {
            entity.removeFromGame();
        }
    }

    @Override
    protected void onResetModifier(Updatable entity) {
    }
    //========================================================

}
