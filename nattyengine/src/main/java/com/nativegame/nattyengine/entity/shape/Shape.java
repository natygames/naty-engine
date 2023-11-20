package com.nativegame.nattyengine.entity.shape;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.MaskFilter;
import android.graphics.Paint;
import android.graphics.Shader;

import com.nativegame.nattyengine.camera.Camera;
import com.nativegame.nattyengine.engine.Engine;
import com.nativegame.nattyengine.entity.Manipulable;
import com.nativegame.nattyengine.entity.ScreenEntity;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public abstract class Shape extends ScreenEntity implements Manipulable {

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
    protected Shape(Engine engine, float x, float y) {
        super(engine);
        mX = x;
        mY = y;
    }
    //========================================================

    //--------------------------------------------------------
    // Getter and Setter
    //--------------------------------------------------------
    public Paint getPaint() {
        return mPaint;
    }

    public void setPaint(Paint paint) {
        mPaint.set(paint);
    }

    public int getColor() {
        return mPaint.getColor();
    }

    public void setColor(int color) {
        mPaint.setColor(color);
    }

    public Paint.Style getStyle() {
        return mPaint.getStyle();
    }

    public void setStyle(Paint.Style style) {
        mPaint.setStyle(style);
    }

    public float getStrokeWidth() {
        return mPaint.getStrokeWidth();
    }

    public void setStrokeWidth(float width) {
        mPaint.setStrokeWidth(width);
    }

    public Paint.Cap getStrokeCap() {
        return mPaint.getStrokeCap();
    }

    public void setStrokeCap(Paint.Cap cap) {
        mPaint.setStrokeCap(cap);
    }

    public Paint.Join getStrokeJoin() {
        return mPaint.getStrokeJoin();
    }

    public void setStrokeJoin(Paint.Join join) {
        mPaint.setStrokeJoin(join);
    }

    public Shader getShader() {
        return mPaint.getShader();
    }

    public void setShader(Shader shader) {
        mPaint.setShader(shader);
    }

    public ColorFilter getColorFilter() {
        return mPaint.getColorFilter();
    }

    public void setColorFilter(ColorFilter filter) {
        mPaint.setColorFilter(filter);
    }

    public MaskFilter getMaskFilter() {
        return mPaint.getMaskFilter();
    }

    public void setMaskFilter(MaskFilter filter) {
        mPaint.setMaskFilter(filter);
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
