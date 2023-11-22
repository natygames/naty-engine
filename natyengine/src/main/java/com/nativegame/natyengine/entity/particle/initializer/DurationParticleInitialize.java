package com.nativegame.natyengine.entity.particle.initializer;

import com.nativegame.natyengine.entity.particle.Particle;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class DurationParticleInitialize implements ParticleInitializer {

    private final long mDuration;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public DurationParticleInitialize(long duration) {
        mDuration = duration;
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public void initParticle(Particle particle) {
        particle.setDuration(mDuration);
    }
    //========================================================

}
