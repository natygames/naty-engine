package com.nativegame.natyengine.entity.shape;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.nativegame.natyengine.camera.Camera;
import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.entity.ScreenEntity;

public abstract class ShapeEntity extends ScreenEntity implements Shape {

    protected float mX;
    protected float mY;

    protected float mScaleX = 1;
    protected float mScaleY = 1;
    protected float mRotation;
    protected int mAlpha = 255;
    protected Camera.CoordinateType mCoordinateType = Camera.CoordinateType.WORLD;

    private float mScalePivotX;
    private float mScalePivotY;
    private float mRotationPivotX;
    private float mRotationPivotY;

    protected final Paint mPaint = new Paint();

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    protected ShapeEntity(Engine engine, float x, float y) {
        super(engine);
        mX = x;
        mY = y;
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public Camera.CoordinateType getCoordinateType() {
        return mCoordinateType;
    }

    @Override
    public void setCoordinateType(Camera.CoordinateType type) {
        mCoordinateType = type;
    }

    @Override
    public float getX() {
        return mX;
    }

    @Override
    public void setX(float x) {
        mX = x;
    }

    @Override
    public float getY() {
        return mY;
    }

    @Override
    public void setY(float y) {
        mY = y;
    }

    @Override
    public float getScaleX() {
        if (mScaleX < 0) {
            return 0;
        }
        return mScaleX;
    }

    @Override
    public void setScaleX(float scaleX) {
        mScaleX = scaleX;
    }

    @Override
    public float getScaleY() {
        if (mScaleY < 0) {
            return 0;
        }
        return mScaleY;
    }

    @Override
    public void setScaleY(float scaleY) {
        mScaleY = scaleY;
    }

    @Override
    public void setScale(float scale) {
        mScaleX = scale;
        mScaleY = scale;
    }

    @Override
    public float getRotation() {
        return mRotation;
    }

    @Override
    public void setRotation(float rotation) {
        mRotation = rotation;
    }

    @Override
    public int getAlpha() {
        if (mAlpha < 0) {
            return 0;
        }
        if (mAlpha > 255) {
            return 255;
        }
        return mAlpha;
    }

    @Override
    public void setAlpha(int alpha) {
        mAlpha = alpha;
    }

    @Override
    public float getRotationPivotX() {
        return mRotationPivotX;
    }

    @Override
    public void setRotationPivotX(float rotationPivotX) {
        mRotationPivotX = rotationPivotX;
    }

    @Override
    public float getRotationPivotY() {
        return mRotationPivotY;
    }

    @Override
    public void setRotationPivotY(float rotationPivotY) {
        mRotationPivotY = rotationPivotY;
    }

    @Override
    public float getScalePivotX() {
        return mScalePivotX;
    }

    @Override
    public void setScalePivotX(float scalePivotX) {
        mScalePivotX = scalePivotX;
    }

    @Override
    public float getScalePivotY() {
        return mScalePivotY;
    }

    @Override
    public void setScalePivotY(float scalePivotY) {
        mScalePivotY = scalePivotY;
    }

    @Override
    public Paint getPaint() {
        return mPaint;
    }

    @Override
    public void setPaint(Paint paint) {
        mPaint.set(paint);
    }

    @Override
    protected void onDraw(Canvas canvas, Camera camera) {
        canvas.save();
        float realX = mX + (1 - mScaleX) * mScalePivotX;
        float realY = mY + (1 - mScaleY) * mScalePivotY;
        float scaleFactor = camera.getPixelFactor() * camera.getWorldToScreenZoom(mCoordinateType);
        float scaleX = getScaleX() * scaleFactor;
        float scaleY = getScaleY() * scaleFactor;
        canvas.translate(camera.getWorldToScreenX(realX, mCoordinateType), camera.getWorldToScreenY(realY, mCoordinateType));
        canvas.rotate(mRotation, mRotationPivotX * scaleX, mRotationPivotY * scaleY);
        canvas.scale(scaleX, scaleY);
        mPaint.setAlpha(getAlpha());
        onDrawCanvas(canvas);
        canvas.restore();
    }

    @Override
    public void reset() {
        super.reset();
        mScaleX = 1;
        mScaleY = 1;
        mRotation = 0;
        mAlpha = 255;
        mPaint.reset();
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    protected abstract void onDrawCanvas(Canvas canvas);
    //========================================================

}
