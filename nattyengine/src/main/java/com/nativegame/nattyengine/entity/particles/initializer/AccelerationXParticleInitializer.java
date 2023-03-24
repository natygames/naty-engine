package com.nativegame.nattyengine.entity.particles.initializer;

import com.nativegame.nattyengine.entity.particles.Particle;
import com.nativegame.nattyengine.util.math.RandomUtils;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class AccelerationXParticleInitializer implements ParticleInitializer {

    private final float mMinAccelerationX;
    private final float mMaxAccelerationX;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public AccelerationXParticleInitializer(float minAccelerationX, float maxAccelerationX) {
        mMinAccelerationX = minAccelerationX;
        mMaxAccelerationX = maxAccelerationX;
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public void initParticle(Particle particle) {
        float value = RandomUtils.nextFloat(mMinAccelerationX, mMaxAccelerationX) / 1000;
        particle.setAccelerationX(value);
    }
    //========================================================

}
