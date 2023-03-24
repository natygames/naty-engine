package com.nativegame.nattyengine.entity.particles.initializer;

import com.nativegame.nattyengine.entity.particles.Particle;
import com.nativegame.nattyengine.util.math.RandomUtils;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class SpeedXParticleInitializer implements ParticleInitializer {

    private final float mMinSpeedX;
    private final float mMaxSpeedX;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public SpeedXParticleInitializer(float minSpeedX, float maxSpeedX) {
        mMinSpeedX = minSpeedX;
        mMaxSpeedX = maxSpeedX;
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public void initParticle(Particle particle) {
        float value = RandomUtils.nextFloat(mMinSpeedX, mMaxSpeedX) / 1000;
        particle.setSpeedX(value);
    }
    //========================================================

}
