package com.nativegame.nattyengine.entity.particles.initializer;

import com.nativegame.nattyengine.entity.particles.Particle;

import java.util.Random;

public class SpeedYInitializer implements ParticleInitializer {

    private final float mMinSpeedY;
    private final float mMaxSpeedY;

    public SpeedYInitializer(float speedMinY, float speedMaxY) {
        mMinSpeedY = speedMinY;
        mMaxSpeedY = speedMaxY;
    }

    @Override
    public void initParticle(Particle particle, Random random) {
        particle.mSpeedY = (random.nextFloat() * (mMaxSpeedY - mMinSpeedY) + mMinSpeedY) / 1000f;
    }

}
