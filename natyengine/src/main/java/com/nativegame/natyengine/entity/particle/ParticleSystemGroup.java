package com.nativegame.natyengine.entity.particle;

import com.nativegame.natyengine.entity.particle.initializer.ParticleInitializer;
import com.nativegame.natyengine.entity.particle.modifier.ParticleModifier;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class ParticleSystemGroup {

    private final List<ParticleSystem> mParticleSystems = new ArrayList<>();

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public ParticleSystemGroup() {
    }

    public ParticleSystemGroup(List<ParticleSystem> particleSystems) {
        mParticleSystems.addAll(particleSystems);
    }
    //========================================================

    //--------------------------------------------------------
    // Getter and Setter
    //--------------------------------------------------------
    public ParticleSystemGroup setLayer(int layer) {
        int size = mParticleSystems.size();
        for (int i = 0; i < size; i++) {
            ParticleSystem particleSystem = mParticleSystems.get(i);
            particleSystem.setLayer(layer);
        }
        return this;
    }

    public ParticleSystemGroup setEmissionRate(int particlePerSecond) {
        int size = mParticleSystems.size();
        for (int i = 0; i < size; i++) {
            ParticleSystem particleSystem = mParticleSystems.get(i);
            particleSystem.setEmissionRate(1000 / particlePerSecond);
        }
        return this;
    }

    public ParticleSystemGroup setEmissionDuration(long duration) {
        int size = mParticleSystems.size();
        for (int i = 0; i < size; i++) {
            ParticleSystem particleSystem = mParticleSystems.get(i);
            particleSystem.setEmissionDuration(duration);
        }
        return this;
    }

    public ParticleSystemGroup setEmissionPositionX(float x) {
        int size = mParticleSystems.size();
        for (int i = 0; i < size; i++) {
            ParticleSystem particleSystem = mParticleSystems.get(i);
            particleSystem.setEmissionPositionX(x);
        }
        return this;
    }

    public ParticleSystemGroup setEmissionPositionY(float y) {
        int size = mParticleSystems.size();
        for (int i = 0; i < size; i++) {
            ParticleSystem particleSystem = mParticleSystems.get(i);
            particleSystem.setEmissionPositionY(y);
        }
        return this;
    }

    public ParticleSystemGroup setEmissionRangeX(float minX, float maxX) {
        int size = mParticleSystems.size();
        for (int i = 0; i < size; i++) {
            ParticleSystem particleSystem = mParticleSystems.get(i);
            particleSystem.setEmissionRangeX(minX, maxX);
        }
        return this;
    }

    public ParticleSystemGroup setEmissionRangeY(float minY, float maxY) {
        int size = mParticleSystems.size();
        for (int i = 0; i < size; i++) {
            ParticleSystem particleSystem = mParticleSystems.get(i);
            particleSystem.setEmissionRangeY(minY, maxY);
        }
        return this;
    }

    public ParticleSystemGroup setDuration(long duration) {
        int size = mParticleSystems.size();
        for (int i = 0; i < size; i++) {
            ParticleSystem particleSystem = mParticleSystems.get(i);
            particleSystem.setDuration(duration);
        }
        return this;
    }

    public ParticleSystemGroup setSpeedWithAngle(float minSpeed, float maxSpeed) {
        int size = mParticleSystems.size();
        for (int i = 0; i < size; i++) {
            ParticleSystem particleSystem = mParticleSystems.get(i);
            particleSystem.setSpeedWithAngle(minSpeed, maxSpeed);
        }
        return this;
    }

    public ParticleSystemGroup setSpeedWithAngle(float minSpeed, float maxSpeed, int minAngle, int maxAngle) {
        int size = mParticleSystems.size();
        for (int i = 0; i < size; i++) {
            ParticleSystem particleSystem = mParticleSystems.get(i);
            particleSystem.setSpeedWithAngle(minSpeed, maxSpeed, minAngle, maxAngle);
        }
        return this;
    }

    public ParticleSystemGroup setSpeedX(float speedX) {
        int size = mParticleSystems.size();
        for (int i = 0; i < size; i++) {
            ParticleSystem particleSystem = mParticleSystems.get(i);
            particleSystem.setSpeedX(speedX);
        }
        return this;
    }

    public ParticleSystemGroup setSpeedX(float minSpeedX, float maxSpeedX) {
        int size = mParticleSystems.size();
        for (int i = 0; i < size; i++) {
            ParticleSystem particleSystem = mParticleSystems.get(i);
            particleSystem.setSpeedX(minSpeedX, maxSpeedX);
        }
        return this;
    }

    public ParticleSystemGroup setSpeedY(float speedY) {
        int size = mParticleSystems.size();
        for (int i = 0; i < size; i++) {
            ParticleSystem particleSystem = mParticleSystems.get(i);
            particleSystem.setSpeedY(speedY);
        }
        return this;
    }

    public ParticleSystemGroup setSpeedY(float minSpeedY, float maxSpeedY) {
        int size = mParticleSystems.size();
        for (int i = 0; i < size; i++) {
            ParticleSystem particleSystem = mParticleSystems.get(i);
            particleSystem.setSpeedY(minSpeedY, maxSpeedY);
        }
        return this;
    }

    public ParticleSystemGroup setAccelerationX(float accelerateX) {
        int size = mParticleSystems.size();
        for (int i = 0; i < size; i++) {
            ParticleSystem particleSystem = mParticleSystems.get(i);
            particleSystem.setAccelerationX(accelerateX);
        }
        return this;
    }

    public ParticleSystemGroup setAccelerationX(float minAccelerateX, float maxAccelerateX) {
        int size = mParticleSystems.size();
        for (int i = 0; i < size; i++) {
            ParticleSystem particleSystem = mParticleSystems.get(i);
            particleSystem.setAccelerationX(minAccelerateX, maxAccelerateX);
        }
        return this;
    }

    public ParticleSystemGroup setAccelerationY(float accelerateY) {
        int size = mParticleSystems.size();
        for (int i = 0; i < size; i++) {
            ParticleSystem particleSystem = mParticleSystems.get(i);
            particleSystem.setAccelerationY(accelerateY);
        }
        return this;
    }

    public ParticleSystemGroup setAccelerationY(float minAccelerateY, float maxAccelerateY) {
        int size = mParticleSystems.size();
        for (int i = 0; i < size; i++) {
            ParticleSystem particleSystem = mParticleSystems.get(i);
            particleSystem.setAccelerationY(minAccelerateY, maxAccelerateY);
        }
        return this;
    }

    public ParticleSystemGroup setInitialRotation(int angle) {
        int size = mParticleSystems.size();
        for (int i = 0; i < size; i++) {
            ParticleSystem particleSystem = mParticleSystems.get(i);
            particleSystem.setInitialRotation(angle);
        }
        return this;
    }

    public ParticleSystemGroup setInitialRotation(int minAngle, int maxAngle) {
        int size = mParticleSystems.size();
        for (int i = 0; i < size; i++) {
            ParticleSystem particleSystem = mParticleSystems.get(i);
            particleSystem.setInitialRotation(minAngle, maxAngle);
        }
        return this;
    }

    public ParticleSystemGroup setRotationSpeed(float rotationSpeed) {
        int size = mParticleSystems.size();
        for (int i = 0; i < size; i++) {
            ParticleSystem particleSystem = mParticleSystems.get(i);
            particleSystem.setRotationSpeed(rotationSpeed);
        }
        return this;
    }

    public ParticleSystemGroup setRotationSpeed(float minRotationSpeed, float maxRotationSpeed) {
        int size = mParticleSystems.size();
        for (int i = 0; i < size; i++) {
            ParticleSystem particleSystem = mParticleSystems.get(i);
            particleSystem.setRotationSpeed(minRotationSpeed, maxRotationSpeed);
        }
        return this;
    }

    public ParticleSystemGroup addInitializer(ParticleInitializer initializer) {
        int size = mParticleSystems.size();
        for (int i = 0; i < size; i++) {
            ParticleSystem particleSystem = mParticleSystems.get(i);
            particleSystem.addInitializer(initializer);
        }
        return this;
    }

    public ParticleSystemGroup clearInitializer() {
        int size = mParticleSystems.size();
        for (int i = 0; i < size; i++) {
            ParticleSystem particleSystem = mParticleSystems.get(i);
            particleSystem.clearInitializer();
        }
        return this;
    }

    public ParticleSystemGroup setRotation(float startValue, float endValue) {
        int size = mParticleSystems.size();
        for (int i = 0; i < size; i++) {
            ParticleSystem particleSystem = mParticleSystems.get(i);
            particleSystem.setRotation(startValue, endValue);
        }
        return this;
    }

    public ParticleSystemGroup setRotation(float startValue, float endValue, long startDelay) {
        int size = mParticleSystems.size();
        for (int i = 0; i < size; i++) {
            ParticleSystem particleSystem = mParticleSystems.get(i);
            particleSystem.setRotation(startValue, endValue, startDelay);
        }
        return this;
    }

    public ParticleSystemGroup setScale(float startValue, float endValue) {
        int size = mParticleSystems.size();
        for (int i = 0; i < size; i++) {
            ParticleSystem particleSystem = mParticleSystems.get(i);
            particleSystem.setScale(startValue, endValue);
        }
        return this;
    }

    public ParticleSystemGroup setScale(float startValue, float endValue, long startDelay) {
        int size = mParticleSystems.size();
        for (int i = 0; i < size; i++) {
            ParticleSystem particleSystem = mParticleSystems.get(i);
            particleSystem.setScale(startValue, endValue, startDelay);
        }
        return this;
    }

    public ParticleSystemGroup setAlpha(float startValue, float endValue) {
        int size = mParticleSystems.size();
        for (int i = 0; i < size; i++) {
            ParticleSystem particleSystem = mParticleSystems.get(i);
            particleSystem.setAlpha(startValue, endValue);
        }
        return this;
    }

    public ParticleSystemGroup setAlpha(float startValue, float endValue, long startDelay) {
        int size = mParticleSystems.size();
        for (int i = 0; i < size; i++) {
            ParticleSystem particleSystem = mParticleSystems.get(i);
            particleSystem.setAlpha(startValue, endValue, startDelay);
        }
        return this;
    }

    public ParticleSystemGroup addModifier(ParticleModifier modifier) {
        int size = mParticleSystems.size();
        for (int i = 0; i < size; i++) {
            ParticleSystem particleSystem = mParticleSystems.get(i);
            particleSystem.addModifier(modifier);
        }
        return this;
    }

    public ParticleSystemGroup clearModifier() {
        int size = mParticleSystems.size();
        for (int i = 0; i < size; i++) {
            ParticleSystem particleSystem = mParticleSystems.get(i);
            particleSystem.clearModifier();
        }
        return this;
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    public void addParticleSystem(ParticleSystem particleSystem) {
        mParticleSystems.add(particleSystem);
    }

    public void removeParticleSystem(ParticleSystem particleSystem) {
        mParticleSystems.remove(particleSystem);
    }

    public void addParticleSystems(List<ParticleSystem> particleSystems) {
        mParticleSystems.addAll(particleSystems);
    }

    public void removeParticleSystems(List<ParticleSystem> particleSystems) {
        mParticleSystems.removeAll(particleSystems);
    }

    public void emit() {
        int size = mParticleSystems.size();
        for (int i = 0; i < size; i++) {
            ParticleSystem particleSystem = mParticleSystems.get(i);
            particleSystem.emit();
        }
    }

    public void stopEmit() {
        int size = mParticleSystems.size();
        for (int i = 0; i < size; i++) {
            ParticleSystem particleSystem = mParticleSystems.get(i);
            particleSystem.stopEmit();
        }
    }

    public void oneShot(float x, float y) {
        int size = mParticleSystems.size();
        for (int i = 0; i < size; i++) {
            ParticleSystem particleSystem = mParticleSystems.get(i);
            particleSystem.oneShot(x, y);
        }
    }

    public void oneShot(float x, float y, int count) {
        int size = mParticleSystems.size();
        for (int i = 0; i < size; i++) {
            ParticleSystem particleSystem = mParticleSystems.get(i);
            particleSystem.oneShot(x, y, count);
        }
    }
    //========================================================

}
