package com.nativegame.natyengine.entity.particle.modifier;

import com.nativegame.natyengine.entity.particle.Particle;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class AlphaParticleModifier extends SingleValueParticleModifier {

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public AlphaParticleModifier(float startValue, float endValue, long startDelay) {
        super(startValue, endValue, startDelay);
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    protected void onUpdateParticle(Particle particle, float value) {
        particle.setAlpha((int) value);
    }
    //========================================================

}
