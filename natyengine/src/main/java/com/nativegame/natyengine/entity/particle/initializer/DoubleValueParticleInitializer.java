package com.nativegame.natyengine.entity.particle.initializer;

import com.nativegame.natyengine.entity.particle.Particle;
import com.nativegame.natyengine.util.RandomUtils;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public abstract class DoubleValueParticleInitializer implements ParticleInitializer {

    private final float mMinValueX;
    private final float mMaxValueX;
    private final float mMinValueY;
    private final float mMaxValueY;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    protected DoubleValueParticleInitializer(float minValueX, float maxValueX, float minValueY, float maxValueY) {
        mMinValueX = minValueX;
        mMaxValueX = maxValueX;
        mMinValueY = minValueY;
        mMaxValueY = maxValueY;
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public void initParticle(Particle particle) {
        float valueX = RandomUtils.nextFloat(mMinValueX, mMaxValueX);
        float valueY = RandomUtils.nextFloat(mMinValueY, mMaxValueY);
        onInitParticle(particle, valueX, valueY);
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    protected abstract void onInitParticle(Particle particle, float valueX, float valueY);
    //========================================================

}
