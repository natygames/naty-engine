package com.nativegame.nattyengine.entity.particles.initializer;

import com.nativegame.nattyengine.entity.particles.Particle;
import com.nativegame.nattyengine.util.math.RandomUtils;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class RotationParticleInitializer implements ParticleInitializer {

    private final int mMinAngle;
    private final int mMaxAngle;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public RotationParticleInitializer(int minAngle, int maxAngle) {
        mMinAngle = minAngle;
        mMaxAngle = maxAngle;
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public void initParticle(Particle particle) {
        float value = RandomUtils.nextFloat(mMinAngle, mMaxAngle);
        particle.setRotation(value);
    }
    //========================================================

}
