package com.nativegame.nattyengine.entity.sprite.animation;

import com.nativegame.nattyengine.engine.Engine;
import com.nativegame.nattyengine.entity.sprite.TileSprite;
import com.nativegame.nattyengine.texture.Texture;
import com.nativegame.nattyengine.texture.TextureGroup;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class AnimatedSprite extends TileSprite {

    private AnimationListener mListener;
    private int mStartIndex;
    private int mEndIndex;
    private long mTotalTime;
    private boolean mIsAutoStart = true;
    private boolean mIsAutoRemove = false;
    private boolean mIsPlaying = false;

    private final Animation mAnimation = new Animation();

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public AnimatedSprite(Engine engine, TextureGroup<? extends Texture> textureGroup) {
        this(engine, 0, 0, textureGroup, 0, textureGroup.getTextureCount() - 1);
    }

    public AnimatedSprite(Engine engine, TextureGroup<? extends Texture> textureGroup, int startIndex) {
        this(engine, 0, 0, textureGroup, startIndex, textureGroup.getTextureCount() - 1);
    }

    public AnimatedSprite(Engine engine, TextureGroup<? extends Texture> textureGroup, int startIndex, int endIndex) {
        this(engine, 0, 0, textureGroup, startIndex, endIndex);
    }

    public AnimatedSprite(Engine engine, float x, float y, TextureGroup<? extends Texture> textureGroup) {
        this(engine, x, y, textureGroup, 0, textureGroup.getTextureCount() - 1);
    }

    public AnimatedSprite(Engine engine, float x, float y, TextureGroup<? extends Texture> textureGroup, int startIndex) {
        this(engine, x, y, textureGroup, startIndex, textureGroup.getTextureCount() - 1);
    }

    public AnimatedSprite(Engine engine, float x, float y, TextureGroup<? extends Texture> textureGroup, int startIndex, int endIndex) {
        super(engine, x, y, textureGroup, startIndex);
        mStartIndex = startIndex;
        mEndIndex = endIndex;
    }
    //========================================================

    //--------------------------------------------------------
    // Getter and Setter
    //--------------------------------------------------------
    public AnimationListener getAnimationListener() {
        return mListener;
    }

    public void setAnimationListener(AnimationListener listener) {
        mListener = listener;
    }

    public int getStartIndex() {
        return mStartIndex;
    }

    public void setStartIndex(int startIndex) {
        mStartIndex = startIndex;
    }

    public int getEndIndex() {
        return mEndIndex;
    }

    public void setEndIndex(int endIndex) {
        mEndIndex = endIndex;
    }

    public boolean isAnimationAutoStart() {
        return mIsAutoStart;
    }

    public void setAnimationAutoStart(boolean autoStart) {
        mIsAutoStart = autoStart;
    }

    public boolean isAnimationAutoRemove() {
        return mIsAutoRemove;
    }

    public void setAnimationAutoRemove(boolean autoRemove) {
        mIsAutoRemove = autoRemove;
    }

    public boolean isPlaying() {
        return mIsPlaying;
    }

    public Animation getAnimation() {
        return mAnimation;
    }

    public void setAnimation(long frameDuration) {
        mAnimation.set(frameDuration, mTextureGroup.getTextureCount());
    }

    public void setAnimation(long frameDuration, boolean looping) {
        mAnimation.set(frameDuration, mTextureGroup.getTextureCount(), looping);
    }

    public void setAnimation(long frameDuration, int frameCount) {
        mAnimation.set(frameDuration, frameCount);
    }

    public void setAnimation(long frameDuration, int frameCount, boolean looping) {
        mAnimation.set(frameDuration, frameCount, looping);
    }

    public void setAnimation(long[] frameDurations) {
        mAnimation.set(frameDurations);
    }

    public void setAnimation(long[] frameDurations, boolean looping) {
        mAnimation.set(frameDurations, looping);
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public void addToGame() {
        super.addToGame();
        if (mIsAutoStart) {
            startAnimation();
        }
    }

    @Override
    public void onPreUpdate(long elapsedMillis) {
        if (!mIsPlaying) {
            return;
        }

        // Calculate animation duration
        long duration = 0;
        for (int i = mStartIndex; i <= mEndIndex; i++) {
            duration += mAnimation.getFrameDurations()[i];
        }

        // Update animation duration
        mTotalTime += elapsedMillis;
        if (mTotalTime >= duration) {
            if (mAnimation.isLooping()) {
                if (mListener != null) {
                    mListener.onAnimationRepeat();
                }
                mTotalTime = mTotalTime % duration;
            } else {
                stopAnimation();
                return;
            }
        }

        // Update animation frame
        long animationElapsedTime = 0;
        for (int i = mStartIndex; i <= mEndIndex; i++) {
            animationElapsedTime += mAnimation.getFrameDurations()[i];
            if (animationElapsedTime > mTotalTime) {
                mCurrentIndex = i;
                break;
            }
        }
    }

    @Override
    public void reset() {
        mCurrentIndex = mStartIndex;
        super.reset();
        stopAnimation();
        mIsAutoStart = true;
        mIsAutoRemove = false;
        mTotalTime = 0;
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    public void startAnimation() {
        if (mIsPlaying) {
            return;
        }
        mCurrentIndex = mStartIndex;
        mIsPlaying = true;
        mTotalTime = 0;
        if (mListener != null) {
            mListener.onAnimationStart();
        }
    }

    public void stopAnimation() {
        if (!mIsPlaying) {
            return;
        }
        mIsPlaying = false;
        mTotalTime = 0;
        if (mIsAutoRemove) {
            removeFromGame();
        }
        if (mListener != null) {
            mListener.onAnimationStop();
        }
    }

    public void pauseAnimation() {
        if (!mIsPlaying) {
            return;
        }
        mIsPlaying = false;
        if (mListener != null) {
            mListener.onAnimationPause();
        }
    }

    public void resumeAnimation() {
        if (mIsPlaying) {
            return;
        }
        mIsPlaying = true;
        if (mListener != null) {
            mListener.onAnimationResume();
        }
    }
    //========================================================

    //--------------------------------------------------------
    // Inner Classes
    //--------------------------------------------------------
    public interface AnimationListener {

        void onAnimationStart();

        void onAnimationStop();

        void onAnimationPause();

        void onAnimationResume();

        void onAnimationRepeat();

    }
    //========================================================

}
