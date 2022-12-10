package com.nativegame.nattyengine.entity.particles.initializer;

import com.nativegame.nattyengine.entity.particles.Particle;

import java.util.Random;

public class SpeedXInitializer implements ParticleInitializer {

    private final float mMinSpeedX;
    private final float mMaxSpeedX;

    public SpeedXInitializer(float speedMinX, float speedMaxX) {
        mMinSpeedX = speedMinX;
        mMaxSpeedX = speedMaxX;
    }

    @Override
    public void initParticle(Particle particle, Random random) {
        particle.mSpeedX = (random.nextFloat() * (mMaxSpeedX - mMinSpeedX) + mMinSpeedX) / 1000f;
    }

}
