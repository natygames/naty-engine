package com.nativegame.nattyengine.entity.particle;

import com.nativegame.nattyengine.engine.Engine;
import com.nativegame.nattyengine.entity.Entity;
import com.nativegame.nattyengine.entity.Reusable;
import com.nativegame.nattyengine.entity.particle.initializer.AccelerationXParticleInitializer;
import com.nativegame.nattyengine.entity.particle.initializer.AccelerationYParticleInitializer;
import com.nativegame.nattyengine.entity.particle.initializer.ColorParticleInitializer;
import com.nativegame.nattyengine.entity.particle.initializer.DurationParticleInitialize;
import com.nativegame.nattyengine.entity.particle.initializer.ParticleInitializer;
import com.nativegame.nattyengine.entity.particle.initializer.RotationParticleInitializer;
import com.nativegame.nattyengine.entity.particle.initializer.RotationSpeedParticleInitializer;
import com.nativegame.nattyengine.entity.particle.initializer.SpeedWithAngleParticleInitializer;
import com.nativegame.nattyengine.entity.particle.initializer.SpeedXParticleInitializer;
import com.nativegame.nattyengine.entity.particle.initializer.SpeedYParticleInitializer;
import com.nativegame.nattyengine.entity.particle.modifier.AlphaParticleModifier;
import com.nativegame.nattyengine.entity.particle.modifier.ParticleModifier;
import com.nativegame.nattyengine.entity.particle.modifier.RotationParticleModifier;
import com.nativegame.nattyengine.entity.particle.modifier.ScaleParticleModifier;
import com.nativegame.nattyengine.util.math.RandomUtils;
import com.nativegame.nattyengine.util.modifier.DurationModifier;
import com.nativegame.nattyengine.util.modifier.Modifier;
import com.nativegame.nattyengine.util.pool.Pool;

import java.util.ArrayList;
import java.util.List;

public class GenericParticleSystem extends Entity
        implements ParticleSystem, Reusable.RecycleListener<Particle>, Modifier.ModifierListener {

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
    public GenericParticleSystem(Engine engine, Pool<Particle> particlePool) {
        super(engine);
        mParticlePool = particlePool;
        mDurationModifier = new DurationModifier(INFINITE);
        mDurationModifier.setListener(this);
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public ParticleSystem setLayer(int layer) {
        mLayer = layer;
        return this;
    }

    @Override
    public ParticleSystem setEmissionRate(int particlePerSecond) {
        mEmissionRate = 1000 / particlePerSecond;
        return this;
    }

    @Override
    public ParticleSystem setEmissionDuration(long duration) {
        mEmissionDuration = duration;
        return this;
    }

    @Override
    public ParticleSystem setEmissionPositionX(float x) {
        mEmissionMinX = x;
        mEmissionMaxX = x;
        return this;
    }

    @Override
    public ParticleSystem setEmissionPositionY(float y) {
        mEmissionMinY = y;
        mEmissionMaxY = y;
        return this;
    }

    @Override
    public ParticleSystem setEmissionPosition(float x, float y) {
        mEmissionMinX = x;
        mEmissionMaxX = x;
        mEmissionMinY = y;
        mEmissionMaxY = y;
        return this;
    }

    @Override
    public ParticleSystem setEmissionRangeX(float minX, float maxX) {
        mEmissionMinX = minX;
        mEmissionMaxX = maxX;
        return this;
    }

    @Override
    public ParticleSystem setEmissionRangeY(float minY, float maxY) {
        mEmissionMinY = minY;
        mEmissionMaxY = maxY;
        return this;
    }

    @Override
    public ParticleSystem setDuration(long duration) {
        mInitializers.add(new DurationParticleInitialize(duration));
        return this;
    }

    @Override
    public ParticleSystem setSpeedWithAngle(float minSpeed, float maxSpeed) {
        mInitializers.add(new SpeedWithAngleParticleInitializer(minSpeed, maxSpeed, 0, 360));
        return this;
    }

    @Override
    public ParticleSystem setSpeedWithAngle(float minSpeed, float maxSpeed, int minAngle, int maxAngle) {
        mInitializers.add(new SpeedWithAngleParticleInitializer(minSpeed, maxSpeed, minAngle, maxAngle));
        return this;
    }

    @Override
    public ParticleSystem setSpeedX(float speedX) {
        mInitializers.add(new SpeedXParticleInitializer(speedX, speedX));
        return this;
    }

    @Override
    public ParticleSystem setSpeedX(float minSpeedX, float maxSpeedX) {
        mInitializers.add(new SpeedXParticleInitializer(minSpeedX, maxSpeedX));
        return this;
    }

    @Override
    public ParticleSystem setSpeedY(float speedY) {
        mInitializers.add(new SpeedYParticleInitializer(speedY, speedY));
        return this;
    }

    @Override
    public ParticleSystem setSpeedY(float minSpeedY, float maxSpeedY) {
        mInitializers.add(new SpeedYParticleInitializer(minSpeedY, maxSpeedY));
        return this;
    }

    @Override
    public ParticleSystem setAccelerationX(float accelerateX) {
        mInitializers.add(new AccelerationXParticleInitializer(accelerateX, accelerateX));
        return this;
    }

    @Override
    public ParticleSystem setAccelerationX(float minAccelerateX, float maxAccelerateX) {
        mInitializers.add(new AccelerationXParticleInitializer(minAccelerateX, maxAccelerateX));
        return this;
    }

    @Override
    public ParticleSystem setAccelerationY(float accelerateY) {
        mInitializers.add(new AccelerationYParticleInitializer(accelerateY, accelerateY));
        return this;
    }

    @Override
    public ParticleSystem setAccelerationY(float minAccelerateY, float maxAccelerateY) {
        mInitializers.add(new AccelerationYParticleInitializer(minAccelerateY, maxAccelerateY));
        return this;
    }

    @Override
    public ParticleSystem setRotationSpeed(float rotationSpeed) {
        mInitializers.add(new RotationSpeedParticleInitializer(rotationSpeed, rotationSpeed));
        return this;
    }

    @Override
    public ParticleSystem setRotationSpeed(float minRotationSpeed, float maxRotationSpeed) {
        mInitializers.add(new RotationSpeedParticleInitializer(minRotationSpeed, maxRotationSpeed));
        return this;
    }

    @Override
    public ParticleSystem setInitialRotation(int angle) {
        mInitializers.add(new RotationParticleInitializer(angle, angle));
        return this;
    }

    @Override
    public ParticleSystem setInitialRotation(int minAngle, int maxAngle) {
        mInitializers.add(new RotationParticleInitializer(minAngle, maxAngle));
        return this;
    }

    @Override
    public ParticleSystem setColor(int color) {
        mInitializers.add(new ColorParticleInitializer(color));
        return this;
    }

    @Override
    public ParticleSystem addInitializer(ParticleInitializer initializer) {
        mInitializers.add(initializer);
        return this;
    }

    @Override
    public ParticleSystem removeInitializer(ParticleInitializer initializer) {
        mInitializers.remove(initializer);
        return this;
    }

    @Override
    public ParticleSystem clearInitializer() {
        mInitializers.clear();
        return this;
    }

    @Override
    public ParticleSystem setRotation(float startValue, float endValue) {
        mModifiers.add(new RotationParticleModifier(startValue, endValue, 0));
        return this;
    }

    @Override
    public ParticleSystem setRotation(float startValue, float endValue, long startDelay) {
        mModifiers.add(new RotationParticleModifier(startValue, endValue, startDelay));
        return this;
    }

    @Override
    public ParticleSystem setScale(float startValue, float endValue) {
        mModifiers.add(new ScaleParticleModifier(startValue, endValue, 0));
        return this;
    }

    @Override
    public ParticleSystem setScale(float startValue, float endValue, long startDelay) {
        mModifiers.add(new ScaleParticleModifier(startValue, endValue, startDelay));
        return this;
    }

    @Override
    public ParticleSystem setAlpha(float startValue, float endValue) {
        mModifiers.add(new AlphaParticleModifier(startValue, endValue, 0));
        return this;
    }

    @Override
    public ParticleSystem setAlpha(float startValue, float endValue, long startDelay) {
        mModifiers.add(new AlphaParticleModifier(startValue, endValue, startDelay));
        return this;
    }

    @Override
    public ParticleSystem addModifier(ParticleModifier modifier) {
        mModifiers.add(modifier);
        return this;
    }

    @Override
    public ParticleSystem removeModifier(ParticleModifier modifier) {
        mModifiers.remove(modifier);
        return this;
    }

    @Override
    public ParticleSystem clearModifier() {
        mModifiers.clear();
        return this;
    }

    @Override
    public void emit() {
        mIsEmitting = true;
        if (mEmissionDuration != INFINITE) {
            mDurationModifier.setDuration(mEmissionDuration);
            mDurationModifier.init(this);
        }
        addToGame();
        mTotalTime = 0;
    }

    @Override
    public void stopEmit() {
        mIsEmitting = false;
        mDurationModifier.reset(this);
        removeFromGame();
        mTotalTime = 0;
    }

    @Override
    public void oneShot(float x, float y) {
        oneShot(x, y, mParticlePool.getObjectCount());
    }

    @Override
    public void oneShot(float x, float y, int count) {
        for (int i = 0; i < count; i++) {
            addOneParticle(x, y);
        }
    }

    @Override
    public void recycle(Particle particle) {
        mParticlePool.returnObject(particle);
    }

    @Override
    public void onModifierComplete() {
        stopEmit();
    }

    @Override
    protected void onUpdate(long elapsedMillis) {
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
            mTotalTime = mTotalTime % mEmissionRate;
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
    public void release() {
        super.release();
        mParticlePool.release();
        mInitializers.clear();
        mModifiers.clear();
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    private void addOneParticle(float x, float y) {
        Particle p = mParticlePool.obtainObject();
        p.setParticleInitializer(mInitializers);
        p.setParticleModifier(mModifiers);
        p.setRecycleListener(this);
        p.activate(x, y, mLayer);
    }
    //========================================================

}
