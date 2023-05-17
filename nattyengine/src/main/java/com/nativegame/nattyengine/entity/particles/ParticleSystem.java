package com.nativegame.nattyengine.entity.particles;

import com.nativegame.nattyengine.engine.Engine;
import com.nativegame.nattyengine.entity.Entity;
import com.nativegame.nattyengine.entity.modifier.DurationModifier;
import com.nativegame.nattyengine.entity.modifier.Modifier;
import com.nativegame.nattyengine.entity.particles.initializer.AccelerationXParticleInitializer;
import com.nativegame.nattyengine.entity.particles.initializer.AccelerationYParticleInitializer;
import com.nativegame.nattyengine.entity.particles.initializer.DurationParticleInitialize;
import com.nativegame.nattyengine.entity.particles.initializer.ParticleInitializer;
import com.nativegame.nattyengine.entity.particles.initializer.RotationParticleInitializer;
import com.nativegame.nattyengine.entity.particles.initializer.RotationSpeedParticleInitializer;
import com.nativegame.nattyengine.entity.particles.initializer.SpeedParticleInitializer;
import com.nativegame.nattyengine.entity.particles.initializer.SpeedXParticleInitializer;
import com.nativegame.nattyengine.entity.particles.initializer.SpeedYParticleInitializer;
import com.nativegame.nattyengine.entity.particles.modifier.AlphaParticleModifier;
import com.nativegame.nattyengine.entity.particles.modifier.ParticleModifier;
import com.nativegame.nattyengine.entity.particles.modifier.RotationParticleModifier;
import com.nativegame.nattyengine.entity.particles.modifier.ScaleParticleModifier;
import com.nativegame.nattyengine.texture.Texture;
import com.nativegame.nattyengine.util.math.RandomUtils;
import com.nativegame.nattyengine.util.pool.ObjectPool;
import com.nativegame.nattyengine.util.pool.Pool;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class ParticleSystem extends Entity implements Modifier.ModifierListener {

    public static final int RATE_LOW = 10;
    public static final int RATE_NORMAL = 20;
    public static final int RATE_HIGH = 50;
    public static final long INFINITE = -1;

    private final Pool<Particle> mParticlePool;
    private final DurationModifier mDurationModifier;

    private int mLayer;
    private float mEmissionMinX;
    private float mEmissionMaxX;
    private float mEmissionMinY;
    private float mEmissionMaxY;
    private long mEmissionRate = 1000 / RATE_NORMAL;
    private long mEmissionDuration = INFINITE;
    private long mTotalTime;
    private boolean mIsEmitting = false;

    private final List<ParticleInitializer> mInitializers = new ArrayList<>();
    private final List<ParticleModifier> mModifiers = new ArrayList<>();

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public ParticleSystem(Engine engine, Texture texture, int minParticle) {
        this(engine, texture, minParticle, minParticle);
    }

    public ParticleSystem(Engine engine, Texture texture, int minParticle, int maxParticle) {
        super(engine);
        mParticlePool = new ObjectPool<>(new Pool.PoolObjectFactory<Particle>() {
            @Override
            public Particle createObject() {
                return new Particle(ParticleSystem.this, engine, texture);
            }
        }, minParticle, maxParticle);
        mDurationModifier = new DurationModifier(INFINITE);
        mDurationModifier.setListener(this);
    }
    //========================================================

    //--------------------------------------------------------
    // Getter and Setter
    //--------------------------------------------------------
    public ParticleSystem setLayer(int layer) {
        mLayer = layer;
        return this;
    }

    public ParticleSystem setEmissionRate(int particlePerSecond) {
        mEmissionRate = 1000 / particlePerSecond;
        return this;
    }

    public ParticleSystem setEmissionDuration(long duration) {
        mEmissionDuration = duration;
        return this;
    }

    public ParticleSystem setEmissionPositionX(float x) {
        mEmissionMinX = x;
        mEmissionMaxX = x;
        return this;
    }

    public ParticleSystem setEmissionPositionY(float y) {
        mEmissionMinY = y;
        mEmissionMaxY = y;
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

    public ParticleSystem setDuration(long duration) {
        mInitializers.add(new DurationParticleInitialize(duration));
        return this;
    }

    public ParticleSystem setSpeedWithAngle(float minSpeed, float maxSpeed) {
        mInitializers.add(new SpeedParticleInitializer(minSpeed, maxSpeed, 0, 360));
        return this;
    }

    public ParticleSystem setSpeedWithAngle(float minSpeed, float maxSpeed, int minAngle, int maxAngle) {
        mInitializers.add(new SpeedParticleInitializer(minSpeed, maxSpeed, minAngle, maxAngle));
        return this;
    }

    public ParticleSystem setSpeedX(float speedX) {
        mInitializers.add(new SpeedXParticleInitializer(speedX, speedX));
        return this;
    }

    public ParticleSystem setSpeedX(float minSpeedX, float maxSpeedX) {
        mInitializers.add(new SpeedXParticleInitializer(minSpeedX, maxSpeedX));
        return this;
    }

    public ParticleSystem setSpeedY(float speedY) {
        mInitializers.add(new SpeedYParticleInitializer(speedY, speedY));
        return this;
    }

    public ParticleSystem setSpeedY(float minSpeedY, float maxSpeedY) {
        mInitializers.add(new SpeedYParticleInitializer(minSpeedY, maxSpeedY));
        return this;
    }

    public ParticleSystem setAccelerationX(float accelerateX) {
        mInitializers.add(new AccelerationXParticleInitializer(accelerateX, accelerateX));
        return this;
    }

    public ParticleSystem setAccelerationX(float minAccelerateX, float maxAccelerateX) {
        mInitializers.add(new AccelerationXParticleInitializer(minAccelerateX, maxAccelerateX));
        return this;
    }

    public ParticleSystem setAccelerationY(float accelerateY) {
        mInitializers.add(new AccelerationYParticleInitializer(accelerateY, accelerateY));
        return this;
    }

    public ParticleSystem setAccelerationY(float minAccelerateY, float maxAccelerateY) {
        mInitializers.add(new AccelerationYParticleInitializer(minAccelerateY, maxAccelerateY));
        return this;
    }

    public ParticleSystem setInitialRotation(int angle) {
        mInitializers.add(new RotationParticleInitializer(angle, angle));
        return this;
    }

    public ParticleSystem setInitialRotation(int minAngle, int maxAngle) {
        mInitializers.add(new RotationParticleInitializer(minAngle, maxAngle));
        return this;
    }

    public ParticleSystem setRotationSpeed(float rotationSpeed) {
        mInitializers.add(new RotationSpeedParticleInitializer(rotationSpeed, rotationSpeed));
        return this;
    }

    public ParticleSystem setRotationSpeed(float minRotationSpeed, float maxRotationSpeed) {
        mInitializers.add(new RotationSpeedParticleInitializer(minRotationSpeed, maxRotationSpeed));
        return this;
    }

    public ParticleSystem addInitializer(ParticleInitializer initializer) {
        mInitializers.add(initializer);
        return this;
    }

    public ParticleSystem clearInitializer() {
        mInitializers.clear();
        return this;
    }

    public ParticleSystem setRotation(float startValue, float endValue) {
        mModifiers.add(new RotationParticleModifier(startValue, endValue, 0));
        return this;
    }

    public ParticleSystem setRotation(float startValue, float endValue, long startDelay) {
        mModifiers.add(new RotationParticleModifier(startValue, endValue, startDelay));
        return this;
    }

    public ParticleSystem setScale(float startValue, float endValue) {
        mModifiers.add(new ScaleParticleModifier(startValue, endValue, 0));
        return this;
    }

    public ParticleSystem setScale(float startValue, float endValue, long startDelay) {
        mModifiers.add(new ScaleParticleModifier(startValue, endValue, startDelay));
        return this;
    }

    public ParticleSystem setAlpha(float startValue, float endValue) {
        mModifiers.add(new AlphaParticleModifier(startValue, endValue, 0));
        return this;
    }

    public ParticleSystem setAlpha(float startValue, float endValue, long startDelay) {
        mModifiers.add(new AlphaParticleModifier(startValue, endValue, startDelay));
        return this;
    }

    public ParticleSystem addModifier(ParticleModifier modifier) {
        mModifiers.add(modifier);
        return this;
    }

    public ParticleSystem clearModifier() {
        mModifiers.clear();
        return this;
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public void onUpdate(long elapsedMillis) {
        if (!mIsEmitting) {
            return;
        }
        // Update emission particle
        mTotalTime += elapsedMillis;
        if (mTotalTime >= mEmissionRate) {
            // Add one new particle
            float emissionX = RandomUtils.nextFloat(mEmissionMinX, mEmissionMaxX);
            float emissionY = RandomUtils.nextFloat(mEmissionMinY, mEmissionMaxY);
            addOneParticle(emissionX, emissionY);
            mTotalTime = 0;
        }
        mDurationModifier.update(this, elapsedMillis);
    }

    @Override
    public void reset() {
        super.reset();
        mIsEmitting = false;
        mEmissionRate = 1000 / RATE_NORMAL;
        mEmissionDuration = INFINITE;
        mDurationModifier.reset(this);
        mTotalTime = 0;
    }

    @Override
    public void dispose() {
        super.dispose();
        mParticlePool.release();
        mInitializers.clear();
        mModifiers.clear();
    }

    @Override
    public void onModifierComplete() {
        stopEmit();
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    public void emit() {
        mIsEmitting = true;
        if (mEmissionDuration != INFINITE) {
            mDurationModifier.setDuration(mEmissionDuration);
            mDurationModifier.init(this);
        }
        addToGame();
        mTotalTime = 0;
    }

    public void stopEmit() {
        mIsEmitting = false;
        mDurationModifier.reset(this);
        removeFromGame();
        mTotalTime = 0;
    }

    public void oneShot(float x, float y, int count) {
        for (int i = 0; i < count; i++) {
            addOneParticle(x, y);
        }
    }

    public void returnToPool(Particle particle) {
        mParticlePool.returnObject(particle);
    }

    private void addOneParticle(float x, float y) {
        Particle p = mParticlePool.obtainObject();
        int size = mInitializers.size();
        for (int i = 0; i < size; i++) {
            mInitializers.get(i).initParticle(p);
        }
        p.activate(x, y, mLayer, mModifiers);
    }
    //========================================================

}
