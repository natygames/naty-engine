package com.nativegame.nattyengine.entity.particles.initializer;

import com.nativegame.nattyengine.entity.particles.Particle;
import com.nativegame.nattyengine.util.math.RandomUtils;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class RotationSpeedParticleInitializer implements ParticleInitializer {

    private final float mMinRotationSpeed;
    private final float mMaxRotationSpeed;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public RotationSpeedParticleInitializer(float minRotationSpeed, float maxRotationSpeed) {
        mMinRotationSpeed = minRotationSpeed;
        mMaxRotationSpeed = maxRotationSpeed;
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public void initParticle(Particle particle) {
        float value = RandomUtils.nextFloat(mMinRotationSpeed, mMaxRotationSpeed) / 1000;
        particle.setRotationSpeed(value);
    }
    //========================================================

}
