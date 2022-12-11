package com.nativegame.nattyengine.entity.particles.initializer;

import com.nativegame.nattyengine.entity.particles.Particle;

import java.util.Random;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class AlphaInitializer implements ParticleInitializer {

    private final float mInitialValue;
    private final float mFinalValue;
    private final long mStartDelay;

    public AlphaInitializer(float initialValue, float finalValue, long startDelay) {
        mInitialValue = initialValue;
        mFinalValue = finalValue;
        mStartDelay = startDelay;
    }

    @Override
    public void initParticle(Particle particle, Random random) {
        particle.mAlpha = (int) mInitialValue;
        particle.mAlphaSpeed = (mFinalValue - mInitialValue) / (particle.mDuration - mStartDelay);
        particle.mAlphaStartDelay = mStartDelay;
    }

}
