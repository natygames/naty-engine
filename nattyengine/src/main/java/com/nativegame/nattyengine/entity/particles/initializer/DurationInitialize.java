package com.nativegame.nattyengine.entity.particles.initializer;

import com.nativegame.nattyengine.entity.particles.Particle;

import java.util.Random;

public class DurationInitialize implements ParticleInitializer {

    private final long mDuration;

    public DurationInitialize(long duration) {
        mDuration = duration;
    }

    @Override
    public void initParticle(Particle particle, Random random) {
        particle.mDuration = mDuration;
    }

}
