package com.nativegame.nattyengine.entity.particle.modifier;

import com.nativegame.nattyengine.entity.particle.Particle;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public abstract class DoubleValueParticleModifier implements ParticleModifier {

    private final float mStartValueX;
    private final float mStartValueY;
    private final float mRangeX;
    private final float mRangeY;
    private final long mStartDelay;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    protected DoubleValueParticleModifier(float startValueX, float endValueX, float startValueY, float endValueY, long startDelay) {
        mStartValueX = startValueX;
        mStartValueY = startValueY;
        mRangeX = endValueX - startValueX;
        mRangeY = endValueY - startValueY;
        mStartDelay = startDelay;
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public void updateParticle(Particle particle, long elapsedMillis) {
        if (elapsedMillis < mStartDelay) {
            onUpdateParticle(particle, mStartValueX, mStartValueY);
        } else {
            float percentage = (elapsedMillis - mStartDelay) * 1f / (particle.getDuration() - mStartDelay);
            float valueX = mStartValueX + mRangeX * percentage;
            float valueY = mStartValueY + mRangeY * percentage;
            onUpdateParticle(particle, valueX, valueY);
        }
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    protected abstract void onUpdateParticle(Particle particle, float valueX, float valueY);
    //========================================================

}
