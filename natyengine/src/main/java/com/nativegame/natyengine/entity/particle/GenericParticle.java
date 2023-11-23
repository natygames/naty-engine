package com.nativegame.natyengine.entity.particle;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.nativegame.natyengine.camera.Camera;
import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.entity.Reusable;
import com.nativegame.natyengine.entity.ScreenEntity;
import com.nativegame.natyengine.entity.particle.initializer.ParticleInitializer;
import com.nativegame.natyengine.entity.particle.modifier.ParticleModifier;
import com.nativegame.natyengine.entity.shape.RectangleShape;

import java.util.List;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class GenericParticle<T extends RectangleShape> extends ScreenEntity implements Particle {

    private final T mChild;

    private Reusable.RecycleListener<Particle> mListener;
    private List<ParticleInitializer> mInitializers;
    private List<ParticleModifier> mModifiers;
    private float mSpeedX;
    private float mSpeedY;
    private float mAccelerationX;
    private float mAccelerationY;
    private float mRotationSpeed;
    private float mRotation;
    private float mScale;
    private int mAlpha;
    private long mDuration;
    private long mTotalTime;

    private final Paint mPaint = new Paint();

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public GenericParticle(Engine engine, T child) {
        super(engine);
        mChild = child;
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public long getDuration() {
        return mDuration;
    }

    @Override
    public void setDuration(long duration) {
        mDuration = duration;
    }

    @Override
    public void setSpeedX(float speedX) {
        mSpeedX = speedX;
    }

    @Override
    public void setSpeedY(float speedY) {
        mSpeedY = speedY;
    }

    @Override
    public void setAccelerationX(float accelerationX) {
        mAccelerationX = accelerationX;
    }

    @Override
    public void setAccelerationY(float accelerationY) {
        mAccelerationY = accelerationY;
    }

    @Override
    public void setRotationSpeed(float rotationSpeed) {
        mRotationSpeed = rotationSpeed;
    }

    @Override
    public void setRotation(float rotation) {
        mRotation = rotation;
    }

    @Override
    public void setScale(float scale) {
        mScale = scale;
    }

    @Override
    public void setAlpha(int alpha) {
        mAlpha = alpha;
    }

    @Override
    public void setPaint(Paint paint) {
        mPaint.set(paint);
    }

    @Override
    public void setParticleInitializer(List<ParticleInitializer> initializers) {
        mInitializers = initializers;
    }

    @Override
    public void setParticleModifier(List<ParticleModifier> modifiers) {
        mModifiers = modifiers;
    }

    @Override
    public void activate(float x, float y, int layer) {
        setLayer(layer);
        initParticle();
        onUpdateChild(x, y);
        addToGame();
        mTotalTime = 0;
    }

    @Override
    public RecycleListener<Particle> getRecycleListener() {
        return mListener;
    }

    @Override
    public void setRecycleListener(RecycleListener<Particle> listener) {
        mListener = listener;
    }

    @Override
    protected void onRemove() {
        if (mListener != null) {
            mListener.recycle(this);
        }
    }

    @Override
    protected void onUpdate(long elapsedMillis) {
        mTotalTime += elapsedMillis;
        if (mTotalTime >= mDuration) {
            removeFromGame();
            mTotalTime = 0;
        } else {
            onUpdateChild(mChild.getCenterX() + mSpeedX * elapsedMillis,
                    mChild.getCenterY() + mSpeedY * elapsedMillis);
            mSpeedX += mAccelerationX * elapsedMillis;
            mSpeedY += mAccelerationY * elapsedMillis;
            mRotation += mRotationSpeed * elapsedMillis;
            updateParticle();
        }
    }

    @Override
    public boolean isCulling(Canvas canvas, Camera camera) {
        return mChild.isCulling(canvas, camera);
    }

    @Override
    protected void onDraw(Canvas canvas, Camera camera) {
        mChild.draw(canvas, camera);
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    protected void onUpdateChild(float x, float y) {
        mChild.setCenterX(x);
        mChild.setCenterY(y);
        mChild.setRotation(mRotation);
        mChild.setScale(mScale);
        mChild.setAlpha(mAlpha);
        mChild.setPaint(mPaint);
    }

    private void initParticle() {
        int initializerCount = mInitializers.size();
        for (int i = 0; i < initializerCount; i++) {
            ParticleInitializer initializer = mInitializers.get(i);
            initializer.initParticle(this);
        }
    }

    private void updateParticle() {
        int modifierCount = mModifiers.size();
        for (int i = 0; i < modifierCount; i++) {
            ParticleModifier modifier = mModifiers.get(i);
            modifier.updateParticle(this, mTotalTime);
        }
    }
    //========================================================

}
