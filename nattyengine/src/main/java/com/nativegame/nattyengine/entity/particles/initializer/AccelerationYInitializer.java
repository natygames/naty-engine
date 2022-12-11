package com.nativegame.nattyengine.entity.particles.initializer;

import com.nativegame.nattyengine.entity.particles.Particle;

import java.util.Random;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class AccelerationYInitializer implements ParticleInitializer {

    private final float mMinAccelerationY;
    private final float mMaxAccelerationY;

    public AccelerationYInitializer(float minAccelerationY, float maxAccelerationY) {
        mMinAccelerationY = minAccelerationY;
        mMaxAccelerationY = maxAccelerationY;
    }

    @Override
    public void initParticle(Particle particle, Random random) {
        particle.mAccelerationY = (random.nextFloat() *
                (mMaxAccelerationY - mMinAccelerationY) + mMinAccelerationY) / 1000f;
    }

}
