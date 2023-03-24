package com.nativegame.nattyengine.entity.shape;

import android.graphics.Canvas;

import com.nativegame.nattyengine.engine.Engine;
import com.nativegame.nattyengine.engine.camera.Camera;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class Circle extends RectangleShape {

    protected int mRadius;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Circle(Engine engine, int radius) {
        this(engine, 0, 0, radius);
    }

    public Circle(Engine engine, float x, float y, int radius) {
        super(engine, x, y, radius * 2, radius * 2);
        mRadius = radius;
    }
    //========================================================

    //--------------------------------------------------------
    // Getter and Setter
    //--------------------------------------------------------
    public int getRadius() {
        return mRadius;
    }

    public void setRadius(int radius) {
        mRadius = radius;
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public void onDraw(Canvas canvas, Camera camera) {
        float screenX = camera.getWorldToScreenX(getCenterX());
        float screenY = camera.getWorldToScreenY(getCenterY());
        canvas.drawCircle(screenX, screenY, mRadius * camera.getPixelFactor(), mPaint);
    }
    //========================================================

}
