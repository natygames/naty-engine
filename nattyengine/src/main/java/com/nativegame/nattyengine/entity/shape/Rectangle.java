package com.nativegame.nattyengine.entity.shape;

import android.graphics.Canvas;

import com.nativegame.nattyengine.engine.Engine;
import com.nativegame.nattyengine.engine.camera.Camera;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class Rectangle extends RectangleShape {

    protected int mRadiusX;
    protected int mRadiusY;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Rectangle(Engine engine, int width, int height) {
        this(engine, 0, 0, width, height, 0, 0);
    }

    public Rectangle(Engine engine, int width, int height, int radius) {
        this(engine, 0, 0, width, height, radius, radius);
    }

    public Rectangle(Engine engine, int width, int height, int radiusX, int radiusY) {
        this(engine, 0, 0, width, height, radiusX, radiusY);
    }

    public Rectangle(Engine engine, float x, float y, int width, int height) {
        this(engine, x, y, width, height, 0, 0);
    }

    public Rectangle(Engine engine, float x, float y, int width, int height, int radius) {
        this(engine, x, y, width, height, radius, radius);
    }

    public Rectangle(Engine engine, float x, float y, int width, int height, int radiusX, int radiusY) {
        super(engine, x, y, width, height);
        mRadiusX = radiusX;
        mRadiusY = radiusY;
    }
    //========================================================

    //--------------------------------------------------------
    // Getter and Setter
    //--------------------------------------------------------
    public int getRadiusX() {
        return mRadiusX;
    }

    public void setRadiusX(int radiusX) {
        mRadiusX = radiusX;
    }

    public int getRadiusY() {
        return mRadiusY;
    }

    public void setRadiusY(int radiusY) {
        mRadiusY = radiusY;
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public void onDraw(Canvas canvas, Camera camera) {
        float screenLeft = camera.getWorldToScreenX(mX);
        float screenTop = camera.getWorldToScreenY(mY);
        float screenRight = camera.getWorldToScreenX(mX + mWidth);
        float screenBottom = camera.getWorldToScreenY(mY + mHeight);
        canvas.drawRoundRect(screenLeft, screenTop, screenRight, screenBottom,
                mRadiusX * camera.getPixelFactor(), mRadiusY * camera.getPixelFactor(), mPaint);
    }
    //========================================================

}
