package com.nativegame.nattyengine.entity.particles;

import com.nativegame.nattyengine.Game;
import com.nativegame.nattyengine.entity.GameObject;
import com.nativegame.nattyengine.entity.particles.initializer.AccelerationXInitializer;
import com.nativegame.nattyengine.entity.particles.initializer.AccelerationYInitializer;
import com.nativegame.nattyengine.entity.particles.initializer.AlphaInitializer;
import com.nativegame.nattyengine.entity.particles.initializer.DurationInitialize;
import com.nativegame.nattyengine.entity.particles.initializer.ParticleInitializer;
import com.nativegame.nattyengine.entity.particles.initializer.RotationInitializer;
import com.nativegame.nattyengine.entity.particles.initializer.RotationSpeedInitializer;
import com.nativegame.nattyengine.entity.particles.initializer.ScaleInitializer;
import com.nativegame.nattyengine.entity.particles.initializer.SpeedAngleInitializer;
import com.nativegame.nattyengine.entity.particles.initializer.SpeedXInitializer;
import com.nativegame.nattyengine.entity.particles.initializer.SpeedYInitializer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class ParticleSystem extends GameObject {

    public static final int RATE_LOW = 10;
    public static final int RATE_NORMAL = 20;
    public static final int RATE_HIGH = 50;

    private final float mPixelFactor;
    private final Random mRandom;

    private final List<ParticleInitializer> mInitializers = new ArrayList<>();
    private final List<Particle> mParticlePool = new ArrayList<>();

    private float mEmissionX;
    private float mEmissionY;
    private float mEmissionMinX;
    private float mEmissionMaxX;
    private float mEmissionMinY;
    private float mEmissionMaxY;
    private int mLayer;

    private long mTimePerParticles = 1000 / RATE_NORMAL;
    private long mDuration;
    private long mCurrentDuration;
    private long mTotalTime;

    private boolean mIsEmitting;

    public ParticleSystem(Game game, int[] drawableIds, int size) {
        super(game);
        mPixelFactor = game.getPixelFactor();
        mRandom = game.getRandom();
        // We add them to the pool now
        for (int i = 0; i < size; i++) {
            // Generate a random int from [0, length - 1]
            int random = (int) (mRandom.nextFloat() * drawableIds.length);
            mParticlePool.add(new Particle(this, game, drawableIds[random]));
        }
    }

    public ParticleSystem(Game game, int drawableId, int size) {
        this(game, new int[]{drawableId}, size);
    }

    public ParticleSystem setLayer(int layer) {
        mLayer = layer;
        return this;
    }

    public ParticleSystem setDuration(long duration) {
        mDuration = duration;
        return this;
    }

    public ParticleSystem setDurationPerParticle(long duration) {
        mInitializers.add(new DurationInitialize(duration));
        return this;
    }

    public ParticleSystem setEmissionRate(int particlesPerSecond) {
        mTimePerParticles = 1000 / particlesPerSecond;
        return this;
    }

    public ParticleSystem setEmissionPosition(float x, float y) {
        mEmissionX = x;
        mEmissionY = y;
        return this;
    }

    public ParticleSystem setEmissionPositionX(float x) {
        mEmissionX = x;
        return this;
    }

    public ParticleSystem setEmissionPositionY(float y) {
        mEmissionY = y;
        return this;
    }

    public ParticleSystem setEmissionRangeX(float minX, float maxX) {
        mEmissionMinX = minX;
        mEmissionMaxX = maxX;
        return this;
    }

    public ParticleSystem setEmissionRangeY(float minY, float maxY) {
        mEmissionMinY = minY;
        mEmissionMaxY = maxY;
        return this;
    }

    public ParticleSystem setSpeedAngle(float speedMin, float speedMax) {
        mInitializers.add(new SpeedAngleInitializer(speedMin * mPixelFactor,
                speedMax * mPixelFactor, 0, 360));
        return this;
    }

    public ParticleSystem setSpeedAngle(float speedMin, float speedMax, int minAngle, int maxAngle) {
        mInitializers.add(new SpeedAngleInitializer(speedMin * mPixelFactor,
                speedMax * mPixelFactor, minAngle, maxAngle));
        return this;
    }

    public ParticleSystem setSpeedX(float speedX) {
        mInitializers.add(new SpeedXInitializer(speedX * mPixelFactor, speedX * mPixelFactor));
        return this;
    }

    public ParticleSystem setSpeedX(float speedMinX, float speedMaxX) {
        mInitializers.add(new SpeedXInitializer(speedMinX * mPixelFactor, speedMaxX * mPixelFactor));
        return this;
    }

    public ParticleSystem setSpeedY(float speedY) {
        mInitializers.add(new SpeedYInitializer(speedY * mPixelFactor, speedY * mPixelFactor));
        return this;
    }

    public ParticleSystem setSpeedY(float speedMinY, float speedMaxY) {
        mInitializers.add(new SpeedYInitializer(speedMinY * mPixelFactor, speedMaxY * mPixelFactor));
        return this;
    }

    public ParticleSystem setAccelerationX(float accelerateX) {
        mInitializers.add(new AccelerationXInitializer(accelerateX * mPixelFactor, accelerateX * mPixelFactor));
        return this;
    }

    public ParticleSystem setAccelerationX(float minAccelerateX, float maxAccelerateX) {
        mInitializers.add(new AccelerationXInitializer(minAccelerateX * mPixelFactor, maxAccelerateX * mPixelFactor));
        return this;
    }

    public ParticleSystem setAccelerationY(float accelerateY) {
        mInitializers.add(new AccelerationYInitializer(accelerateY * mPixelFactor, accelerateY * mPixelFactor));
        return this;
    }

    public ParticleSystem setAccelerationY(float minAccelerateY, float maxAccelerateY) {
        mInitializers.add(new AccelerationYInitializer(minAccelerateY * mPixelFactor, maxAccelerateY * mPixelFactor));
        return this;
    }

    public ParticleSystem setInitialRotation(int minAngle, int maxAngle) {
        mInitializers.add(new RotationInitializer(minAngle, maxAngle));
        return this;
    }

    public ParticleSystem setRotationSpeed(float minRotationSpeed, float maxRotationSpeed) {
        mInitializers.add(new RotationSpeedInitializer(minRotationSpeed, maxRotationSpeed));
        return this;
    }

    public ParticleSystem setAlpha(float initialValue, float finalValue) {
        mInitializers.add(new AlphaInitializer(initialValue, finalValue, 0));
        return this;
    }

    public ParticleSystem setAlpha(float initialValue, float finalValue, long startDelay) {
        mInitializers.add(new AlphaInitializer(initialValue, finalValue, startDelay));
        return this;
    }

    public ParticleSystem setScale(float initialValue, float finalValue) {
        mInitializers.add(new ScaleInitializer(initialValue, finalValue, 0));
        return this;
    }

    public ParticleSystem setScale(float initialValue, float finalValue, long startDelay) {
        mInitializers.add(new ScaleInitializer(initialValue, finalValue, startDelay));
        return this;
    }

    public ParticleSystem addInitializer(ParticleInitializer initializer) {
        mInitializers.add(initializer);
        return this;
    }

    public ParticleSystem clearInitializers() {
        mInitializers.clear();
        return this;
    }

    public void emit() {
        mIsEmitting = true;
        mTotalTime = 0;
        mCurrentDuration = 0;
    }

    public void stopEmit() {
        mIsEmitting = false;
    }

    public void oneShot(float x, float y, int numParticles) {
        mIsEmitting = false;
        // We create particles based on the parameters
        for (int i = 0; !mParticlePool.isEmpty() && i < numParticles; i++) {
            addOneParticle(x, y);
        }
    }

    @Override
    public void onUpdate(long elapsedMillis) {
        if (!mIsEmitting) {
            return;
        }
        mTotalTime += elapsedMillis;
        // Check is enough particles in pool, and time between particles
        if (!mParticlePool.isEmpty() && mTotalTime >= mTimePerParticles) {
            // Activate a new particle
            updatePosition();
            addOneParticle(mEmissionX, mEmissionY);
            mTotalTime = 0;
        }

        // Update ParticleSystem duration
        if (mDuration != 0) {
            mCurrentDuration += elapsedMillis;
            if (mCurrentDuration >= mDuration) {
                stopEmit();
                mCurrentDuration = 0;
            }
        }
    }

    private void addOneParticle(float x, float y) {
        Particle p = mParticlePool.remove(0);
        int size = mInitializers.size();
        for (int i = 0; i < size; i++) {
            mInitializers.get(i).initParticle(p, mRandom);
        }
        p.activate(x, y, mLayer);
    }

    private void updatePosition() {
        if (mEmissionMinX != mEmissionMaxX) {
            // Generate random emit x position
            mEmissionX = mRandom.nextFloat() * (mEmissionMaxX - mEmissionMinX) + mEmissionMinX;
        }
        if (mEmissionMinY != mEmissionMaxY) {
            // Generate random emit Y position
            mEmissionY = mRandom.nextFloat() * (mEmissionMaxY - mEmissionMinY) + mEmissionMinY;
        }
    }

    public void returnToPool(Particle particle) {
        mParticlePool.add(particle);
    }

}
