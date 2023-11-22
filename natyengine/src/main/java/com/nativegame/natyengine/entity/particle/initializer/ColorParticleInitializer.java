package com.nativegame.natyengine.entity.particle.initializer;

import com.nativegame.natyengine.entity.particle.Particle;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class ColorParticleInitializer implements ParticleInitializer {

    private final int mColor;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public ColorParticleInitializer(int color) {
        mColor = color;
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public void initParticle(Particle particle) {
        particle.setColor(mColor);
    }
    //========================================================

}
