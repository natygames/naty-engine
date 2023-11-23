package com.nativegame.natyengine.entity.shape.primitive;

import android.graphics.Canvas;

import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.entity.shape.RectangleShapeEntity;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class Oval extends RectangleShapeEntity {

    private int mRadiusX;
    private int mRadiusY;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Oval(Engine engine, int radiusX, int radiusY) {
        this(engine, 0, 0, radiusX, radiusY);
    }

    public Oval(Engine engine, float x, float y, int radiusX, int radiusY) {
        super(engine, x, y, radiusX * 2, radiusY * 2);
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
        super.setWidth(radiusX * 2);
        mRadiusX = radiusX;
    }

    public int getRadiusY() {
        return mRadiusY;
    }

    public void setRadiusY(int radiusY) {
        super.setHeight(radiusY * 2);
        mRadiusY = radiusY;
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public void setWidth(int width) {
        setRadiusX(width / 2);
    }

    @Override
    public void setHeight(int height) {
        setRadiusY(height / 2);
    }

    @Override
    protected void onDrawCanvas(Canvas canvas) {
        canvas.drawOval(0, 0, getWidth(), getHeight(), mPaint);
    }
    //========================================================

}
