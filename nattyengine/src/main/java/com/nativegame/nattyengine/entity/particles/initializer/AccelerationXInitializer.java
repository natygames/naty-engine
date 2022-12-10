package com.nativegame.nattyengine.entity.particles.initializer;

import com.nativegame.nattyengine.entity.particles.Particle;

import java.util.Random;

public class AccelerationXInitializer implements ParticleInitializer {

    private final float mMinAccelerationX;
    private final float mMaxAccelerationX;

    public AccelerationXInitializer(float minAccelerationX, float maxAccelerationX) {
        mMinAccelerationX = minAccelerationX;
        mMaxAccelerationX = maxAccelerationX;
    }

    @Override
    public void initParticle(Particle particle, Random random) {
        particle.mAccelerationX = (random.nextFloat() *
                (mMaxAccelerationX - mMinAccelerationX) + mMinAccelerationX) / 1000f;
    }

}
