package com.nativegame.natyengine.entity.shape.primitive;

import android.graphics.Canvas;

import com.nativegame.natyengine.camera.Camera;
import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.entity.shape.ShapeEntity;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class Line extends ShapeEntity {

    protected float mX2;
    protected float mY2;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Line(Engine engine, float x, float y, float x2, float y2) {
        super(engine, x, y);
        mX2 = x2;
        mY2 = y2;
    }
    //========================================================

    //--------------------------------------------------------
    // Getter and Setter
    //--------------------------------------------------------
    public float getX2() {
        return mX2;
    }

    public void setX2(float x2) {
        mX2 = x2;
    }

    public float getY2() {
        return mY2;
    }

    public void setY2(float y2) {
        mY2 = y2;
    }

    public float getXFromY(float y) {
        // (y - b) / a = x
        float a = (mY - mY2) / (mX - mX2);
        float b = (mX2 * mY - mX * mY2) / (mX2 - mX);
        return (y - b) / a;
    }

    public float getYFromX(float x) {
        // ax + b = y
        float a = (mY - mY2) / (mX - mX2);
        float b = (mX2 * mY - mX * mY2) / (mX2 - mX);
        return a * x + b;
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public boolean isCulling(Canvas canvas, Camera camera) {
        float yLeft = camera.getWorldToScreenX(getYFromX(0), mCoordinateType);
        float yRight = camera.getWorldToScreenY(getYFromX(canvas.getWidth()), mCoordinateType);
        return camera.getWorldToScreenX(Math.min(mX, mX2), mCoordinateType) > canvas.getWidth()
                || camera.getWorldToScreenY(Math.min(mY, mY2), mCoordinateType) > canvas.getHeight()
                || camera.getWorldToScreenX(Math.max(mX, mX2), mCoordinateType) < 0
                || camera.getWorldToScreenY(Math.max(mY, mY2), mCoordinateType) < 0
                || (yLeft > canvas.getHeight() && yRight > canvas.getHeight())
                || (yLeft < 0 && yRight < 0);
    }

    @Override
    protected void onDrawCanvas(Canvas canvas) {
        canvas.drawLine(0, 0, mX2 - mX, mY2 - mY, mPaint);
    }
    //========================================================

}
