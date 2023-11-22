package com.nativegame.natyengine.entity.particle.initializer;

import com.nativegame.natyengine.entity.particle.Particle;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class SpeedWithAngleParticleInitializer extends DoubleValueParticleInitializer {

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public SpeedWithAngleParticleInitializer(float minValueX, float maxValueX, float minValueY, float maxValueY) {
        super(minValueX, maxValueX, minValueY, maxValueY);
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    protected void onInitParticle(Particle particle, float speed, float angle) {
        double angleInRads = Math.toRadians(angle);
        float valueX = (float) (speed * Math.cos(angleInRads) / 1000);
        float valueY = (float) (speed * Math.sin(angleInRads) / 1000);
        particle.setSpeedX(valueX);
        particle.setSpeedY(valueY);
    }
    //========================================================

}
