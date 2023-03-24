package com.nativegame.nattyengine.entity.particles.initializer;

import com.nativegame.nattyengine.entity.particles.Particle;
import com.nativegame.nattyengine.util.math.RandomUtils;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class SpeedYParticleInitializer implements ParticleInitializer {

    private final float mMinSpeedY;
    private final float mMaxSpeedY;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public SpeedYParticleInitializer(float minSpeedY, float maxSpeedY) {
        mMinSpeedY = minSpeedY;
        mMaxSpeedY = maxSpeedY;
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public void initParticle(Particle particle) {
        float value = RandomUtils.nextFloat(mMinSpeedY, mMaxSpeedY) / 1000;
        particle.setSpeedY(value);
    }
    //========================================================

}
