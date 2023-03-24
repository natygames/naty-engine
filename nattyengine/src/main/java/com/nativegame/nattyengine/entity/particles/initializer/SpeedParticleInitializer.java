package com.nativegame.nattyengine.entity.particles.initializer;

import com.nativegame.nattyengine.entity.particles.Particle;
import com.nativegame.nattyengine.util.math.RandomUtils;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class SpeedParticleInitializer implements ParticleInitializer {

    private final float mMinSpeed;
    private final float mMaxSpeed;
    private final int mMinAngle;
    private final int mMaxAngle;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public SpeedParticleInitializer(float minSpeed, float maxSpeed, int minAngle, int maxAngle) {
        mMinSpeed = minSpeed;
        mMaxSpeed = maxSpeed;
        mMinAngle = minAngle;
        mMaxAngle = maxAngle;
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public void initParticle(Particle particle) {
        float speed = RandomUtils.nextFloat(mMinSpeed, mMaxSpeed);
        float angle = RandomUtils.nextFloat(mMinAngle, mMaxAngle);
        double angleInRads = Math.toRadians(angle);
        float valueX = (float) (speed * Math.cos(angleInRads) / 1000);
        float valueY = (float) (speed * Math.sin(angleInRads) / 1000);
        particle.setSpeedX(valueX);
        particle.setSpeedY(valueY);
    }
    //========================================================

}
