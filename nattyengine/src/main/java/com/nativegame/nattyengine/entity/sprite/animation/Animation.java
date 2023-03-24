package com.nativegame.nattyengine.entity.sprite.animation;

import com.nativegame.nattyengine.util.math.MathUtils;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class Animation {

    private long[] mFrameDurations;
    private long mDuration;
    private int mFrameCount;
    private boolean mIsLooping;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Animation() {
    }
    //========================================================

    //--------------------------------------------------------
    // Getter and Setter
    //--------------------------------------------------------
    public long[] getFrameDurations() {
        return mFrameDurations;
    }

    public long getDuration() {
        return mDuration;
    }

    public int getFrameCount() {
        return mFrameCount;
    }

    public boolean isLooping() {
        return mIsLooping;
    }

    public void set(long frameDuration, int frameCount) {
        set(MathUtils.fill(frameDuration, frameCount), false);
    }

    public void set(long frameDuration, int frameCount, boolean looping) {
        set(MathUtils.fill(frameDuration, frameCount), looping);
    }

    public void set(long[] frameDurations) {
        set(frameDurations, false);
    }

    public void set(long[] frameDurations, boolean looping) {
        mFrameDurations = frameDurations;
        mDuration = MathUtils.sum(mFrameDurations);
        mFrameCount = mFrameDurations.length;
        mIsLooping = looping;
    }
    //========================================================

}
