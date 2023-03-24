package com.nativegame.nattyengine.entity.particles;

import com.nativegame.nattyengine.engine.Engine;
import com.nativegame.nattyengine.entity.particles.modifier.ParticleModifier;
import com.nativegame.nattyengine.entity.sprite.Sprite;
import com.nativegame.nattyengine.texture.Texture;

import java.util.List;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class Particle extends Sprite {

    private final ParticleSystem mParent;

    private List<ParticleModifier> mModifiers;
    private float mSpeedX;
    private float mSpeedY;
    private float mAccelerationX;
    private float mAccelerationY;
    private float mRotationSpeed;
    private long mDuration;
    private long mTotalTime;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Particle(ParticleSystem particleSystem, Engine engine, Texture texture) {
        super(engine, texture);
        mParent = particleSystem;
    }
    //========================================================

    //--------------------------------------------------------
    // Getter and Setter
    //--------------------------------------------------------
    public void setSpeedX(float speedX) {
        mSpeedX = speedX;
    }

    public void setSpeedY(float speedY) {
        mSpeedY = speedY;
    }

    public void setAccelerationX(float accelerationX) {
        mAccelerationX = accelerationX;
    }

    public void setAccelerationY(float accelerationY) {
        mAccelerationY = accelerationY;
    }

    public void setRotationSpeed(float rotationSpeed) {
        mRotationSpeed = rotationSpeed;
    }

    public long getDuration() {
        return mDuration;
    }

    public void setDuration(long duration) {
        mDuration = duration;
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public void onRemove() {
        mParent.returnToPool(this);
    }

    @Override
    public void onUpdate(long elapsedMillis) {
        mTotalTime += elapsedMillis;
        if (mTotalTime >= mDuration) {
            removeFromGame();
            mTotalTime = 0;
        } else {
            mX += mSpeedX * elapsedMillis;
            mY += mSpeedY * elapsedMillis;
            mSpeedX += mAccelerationX * elapsedMillis;
            mSpeedY += mAccelerationY * elapsedMillis;
            mRotation += mRotationSpeed * elapsedMillis;

            // Update modifier
            int size = mModifiers.size();
            for (int i = 0; i < size; i++) {
                ParticleModifier modifier = mModifiers.get(i);
                modifier.updateParticle(this, mTotalTime);
            }
        }
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    public void activate(float x, float y, int layer, List<ParticleModifier> modifiers) {
        setCenterX(x);
        setCenterY(y);
        setLayer(layer);
        mModifiers = modifiers;
        addToGame();
        mTotalTime = 0;
    }
    //========================================================

}
