package com.nativegame.nattyengine.entity.sprite;

import android.graphics.Bitmap;

import com.nativegame.nattyengine.Game;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public abstract class AnimatedSprite extends Sprite {

    public Bitmap[] mAnimatedBitmaps;
    public int mFrame;
    public long mTimePreFrame;

    private long mTotalTime;
    private boolean mIsRunning = false;

    protected AnimatedSprite(Game game, int drawableId) {
        super(game, drawableId);
    }

    //--------------------------------------------------------
    // Methods to change state of animation
    // start, stop, resume, repeat
    //--------------------------------------------------------
    public final void startAnimation() {
        mIsRunning = true;
        mFrame = 0;
        mTotalTime = 0;
        onAnimationStart();
    }

    public final void stopAnimation() {
        mIsRunning = false;
        onAnimationStop();
    }

    public final void resumeAnimation() {
        mIsRunning = true;
        onAnimationResume();
    }

    public final void repeatAnimation() {
        mFrame = 0;
        mTotalTime = 0;
        onAnimationRepeat();
    }

    public boolean isRunning() {
        return mIsRunning;
    }
    //=================================================================================

    @Override
    public void onPreUpdate(long elapsedMillis) {
        if (!mIsRunning) {
            return;
        }
        mTotalTime += elapsedMillis;
        if (mTotalTime >= mTimePreFrame) {
            if (mFrame < mAnimatedBitmaps.length - 1) {
                mFrame++;
            } else {
                repeatAnimation();
            }
            mBitmap = mAnimatedBitmaps[mFrame];
            mTotalTime = 0;
        }
    }

    protected void onAnimationStart() {
    }

    protected void onAnimationStop() {
    }

    protected void onAnimationResume() {
    }

    protected void onAnimationRepeat() {
    }

}
