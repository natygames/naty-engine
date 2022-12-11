package com.nativegame.nattyengine.entity.particles;

import com.nativegame.nattyengine.Game;
import com.nativegame.nattyengine.entity.sprite.Sprite;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class Particle extends Sprite {

    private final ParticleSystem mParent;

    public float mSpeedX;
    public float mSpeedY;
    public float mRotationSpeed;
    public float mScaleSpeed;
    public float mAlphaSpeed;

    public float mAccelerationX;
    public float mAccelerationY;

    public long mDuration;
    public long mScaleStartDelay;
    public long mAlphaStartDelay;
    private long mTotalTime;

    public Particle(ParticleSystem particleSystem, Game game, int drawableId) {
        super(game, drawableId);
        mParent = particleSystem;
    }

    public void activate(float x, float y, int layer) {
        mX = x - mWidth / 2f;
        mY = y - mHeight / 2f;
        mLayer = layer;
        addToGame();
        mTotalTime = 0;
    }

    @Override
    public void onRemove() {
        mParent.returnToPool(this);
    }

    @Override
    public void onUpdate(long elapsedMillis) {
        mTotalTime += elapsedMillis;
        if (mTotalTime >= mDuration) {
            // Remove from engine and return it to the pool
            removeFromGame();
        } else {
            updateParticle(elapsedMillis);
        }
    }

    private void updateParticle(long elapsedMillis) {
        mX += mSpeedX * elapsedMillis;
        mY += mSpeedY * elapsedMillis;
        mSpeedX += mAccelerationX * elapsedMillis;
        mSpeedY += mAccelerationY * elapsedMillis;
        mRotation += mRotationSpeed * elapsedMillis;

        // Update alpha after delay time
        if (mTotalTime >= mAlphaStartDelay) {
            mAlpha += mAlphaSpeed * elapsedMillis;
            // We make sure no negative value
            if (mAlpha < 0) {
                mAlpha = 0;
            }
        }

        // Update scale after delay time
        if (mTotalTime >= mScaleStartDelay) {
            mScale += mScaleSpeed * elapsedMillis;
            // We make sure no negative value
            if (mScale < 0) {
                mScale = 0;
            }
        }
    }

}
