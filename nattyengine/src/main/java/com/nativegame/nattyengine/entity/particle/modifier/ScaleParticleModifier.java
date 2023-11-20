package com.nativegame.nattyengine.entity.particle.modifier;

import com.nativegame.nattyengine.entity.particle.Particle;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class ScaleParticleModifier extends SingleValueParticleModifier {

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public ScaleParticleModifier(float startValue, float endValue, long startDelay) {
        super(startValue, endValue, startDelay);
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    protected void onUpdateParticle(Particle particle, float value) {
        particle.setScale(value);
    }
    //========================================================

}
