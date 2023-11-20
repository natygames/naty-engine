package com.nativegame.nattyengine.entity.particle.initializer;

import com.nativegame.nattyengine.entity.particle.Particle;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class AccelerationYParticleInitializer extends SingleValueParticleInitializer {

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public AccelerationYParticleInitializer(float minValue, float maxValue) {
        super(minValue, maxValue);
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    protected void onInitParticle(Particle particle, float value) {
        particle.setAccelerationY(value / 1000);
    }
    //========================================================

}
