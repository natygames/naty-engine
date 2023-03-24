package com.nativegame.nattyengine.entity.runnable;

import com.nativegame.nattyengine.engine.Engine;
import com.nativegame.nattyengine.entity.Entity;
import com.nativegame.nattyengine.ui.GameActivity;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public abstract class RunnableEntity extends Entity implements Runnable {

    protected final GameActivity mActivity;

    private boolean mIsPostRunnable = false;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    protected RunnableEntity(GameActivity activity, Engine engine) {
        super(engine);
        mActivity = activity;
    }
    //========================================================

    //--------------------------------------------------------
    // Getter and Setter
    //--------------------------------------------------------
    public boolean isPostRunnable() {
        return mIsPostRunnable;
    }

    public void setPostRunnable(boolean post) {
        mIsPostRunnable = post;
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public void onPostUpdate(long elapsedMillis) {
        if (mIsPostRunnable) {
            mActivity.runOnUiThread(this);
            mIsPostRunnable = false;
            // Important to only post the Runnable one time, so it won't block the ui thread
        }
    }

    @Override
    public void run() {
        onUpdateRunnable();
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    protected abstract void onUpdateRunnable();
    //========================================================

}
