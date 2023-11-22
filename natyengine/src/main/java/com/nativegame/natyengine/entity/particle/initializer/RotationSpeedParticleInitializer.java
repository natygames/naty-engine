package com.nativegame.natyengine.entity.particle.initializer;

import com.nativegame.natyengine.entity.particle.Particle;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class RotationSpeedParticleInitializer extends SingleValueParticleInitializer {

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public RotationSpeedParticleInitializer(float minValue, float maxValue) {
        super(minValue, maxValue);
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    protected void onInitParticle(Particle particle, float value) {
        particle.setRotationSpeed(value / 1000);
    }
    //========================================================

}
