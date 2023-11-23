package com.nativegame.natyengine.entity.particle;

import android.graphics.Paint;

import com.nativegame.natyengine.entity.particle.initializer.ParticleInitializer;
import com.nativegame.natyengine.entity.particle.modifier.ParticleModifier;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public interface ParticleSystem {

    ParticleSystem setLayer(int layer);

    ParticleSystem setEmissionRate(int particlePerSecond);

    ParticleSystem setEmissionDuration(long duration);

    ParticleSystem setEmissionPositionX(float x);

    ParticleSystem setEmissionPositionY(float y);

    ParticleSystem setEmissionPosition(float x, float y);

    ParticleSystem setEmissionRangeX(float minX, float maxX);

    ParticleSystem setEmissionRangeY(float minY, float maxY);

    ParticleSystem setDuration(long duration);

    ParticleSystem setSpeedWithAngle(float minSpeed, float maxSpeed);

    ParticleSystem setSpeedWithAngle(float minSpeed, float maxSpeed, int minAngle, int maxAngle);

    ParticleSystem setSpeedX(float speedX);

    ParticleSystem setSpeedX(float minSpeedX, float maxSpeedX);

    ParticleSystem setSpeedY(float speedY);

    ParticleSystem setSpeedY(float minSpeedY, float maxSpeedY);

    ParticleSystem setAccelerationX(float accelerateX);

    ParticleSystem setAccelerationX(float minAccelerateX, float maxAccelerateX);

    ParticleSystem setAccelerationY(float accelerateY);

    ParticleSystem setAccelerationY(float minAccelerateY, float maxAccelerateY);

    ParticleSystem setRotationSpeed(float rotationSpeed);

    ParticleSystem setRotationSpeed(float minRotationSpeed, float maxRotationSpeed);

    ParticleSystem setInitialRotation(int angle);

    ParticleSystem setInitialRotation(int minAngle, int maxAngle);

    ParticleSystem setPaint(Paint paint);

    ParticleSystem addInitializer(ParticleInitializer initializer);

    ParticleSystem removeInitializer(ParticleInitializer initializer);

    ParticleSystem clearInitializer();

    ParticleSystem setRotation(float startValue, float endValue);

    ParticleSystem setRotation(float startValue, float endValue, long startDelay);

    ParticleSystem setScale(float startValue, float endValue);

    ParticleSystem setScale(float startValue, float endValue, long startDelay);

    ParticleSystem setAlpha(float startValue, float endValue);

    ParticleSystem setAlpha(float startValue, float endValue, long startDelay);

    ParticleSystem addModifier(ParticleModifier modifier);

    ParticleSystem removeModifier(ParticleModifier modifier);

    ParticleSystem clearModifier();

    public void emit();

    public void stopEmit();

    public void oneShot(float x, float y);

    public void oneShot(float x, float y, int count);

}
