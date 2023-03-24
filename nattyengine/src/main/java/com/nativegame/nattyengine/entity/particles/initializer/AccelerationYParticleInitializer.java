package com.nativegame.nattyengine.entity.particles.initializer;

import com.nativegame.nattyengine.entity.particles.Particle;
import com.nativegame.nattyengine.util.math.RandomUtils;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class AccelerationYParticleInitializer implements ParticleInitializer {

    private final float mMinAccelerationY;
    private final float mMaxAccelerationY;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public AccelerationYParticleInitializer(float minAccelerationY, float maxAccelerationY) {
        mMinAccelerationY = minAccelerationY;
        mMaxAccelerationY = maxAccelerationY;
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public void initParticle(Particle particle) {
        float value = RandomUtils.nextFloat(mMinAccelerationY, mMaxAccelerationY) / 1000;
        particle.setAccelerationY(value);
    }
    //========================================================

}
