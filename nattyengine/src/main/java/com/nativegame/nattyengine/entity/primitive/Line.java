package com.nativegame.nattyengine.entity.primitive;

import android.graphics.Canvas;

import com.nativegame.nattyengine.engine.Engine;
import com.nativegame.nattyengine.engine.camera.Camera;
import com.nativegame.nattyengine.entity.shape.Shape;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class Line extends Shape {

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
    public void onDraw(Canvas canvas, Camera camera) {
        float screenX = camera.getWorldToScreenX(mX);
        float screenY = camera.getWorldToScreenY(mY);
        float screenX2 = camera.getWorldToScreenX(mX2);
        float screenY2 = camera.getWorldToScreenY(mY2);
        canvas.drawLine(screenX, screenY, screenX2, screenY2, mPaint);
    }

    @Override
    public boolean isCulling(Camera camera) {
        float yLeft = camera.getWorldToScreenX(getYFromX(0));
        float yRight = camera.getWorldToScreenY(getYFromX(camera.getScreenWidth()));
        return camera.getWorldToScreenX(Math.min(mX, mX2)) > camera.getScreenWidth()
                || camera.getWorldToScreenY(Math.min(mY, mY2)) > camera.getScreenHeight()
                || camera.getWorldToScreenX(Math.max(mX, mX2)) < 0
                || camera.getWorldToScreenY(Math.max(mY, mY2)) < 0
                || (yLeft > camera.getScreenHeight() && yRight > camera.getScreenHeight())
                || (yLeft < 0 && yRight < 0);
    }
    //========================================================

}
