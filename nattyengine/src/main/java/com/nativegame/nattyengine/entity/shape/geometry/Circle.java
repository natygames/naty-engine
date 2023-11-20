package com.nativegame.nattyengine.entity.shape.geometry;

import android.graphics.Canvas;

import com.nativegame.nattyengine.camera.Camera;
import com.nativegame.nattyengine.engine.Engine;
import com.nativegame.nattyengine.entity.shape.RectangleShape;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class Circle extends RectangleShape {

    private int mRadius;

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
        super.setWidth(radius * 2);
        super.setHeight(radius * 2);
        mRadius = radius;
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public void setWidth(int width) {
        setRadius(width / 2);
    }

    @Override
    public void setHeight(int height) {
        setRadius(height / 2);
    }

    @Override
    public boolean isCulling(Canvas canvas, Camera camera) {
        // Check is all four edges out of bound
        boolean isCulling = camera.getWorldToScreenX(mX, mCoordinateType) > canvas.getWidth()
                || camera.getWorldToScreenY(mY, mCoordinateType) > canvas.getHeight()
                || camera.getWorldToScreenX(getEndX(), mCoordinateType) < 0
                || camera.getWorldToScreenY(getEndY(), mCoordinateType) < 0;

        // Print debug info
        if (!isCulling && mEngine.isDebugMode() && mEngine.getDebugger().isDebugCulling()) {
            canvas.drawRect(camera.getWorldToScreenX(mX, mCoordinateType),
                    camera.getWorldToScreenY(mY, mCoordinateType),
                    camera.getWorldToScreenX(getEndX(), mCoordinateType),
                    camera.getWorldToScreenY(getEndY(), mCoordinateType),
                    mEngine.getDebugger().getDebugPaint());
        }

        return isCulling;
    }

    @Override
    protected void onDrawCanvas(Canvas canvas) {
        canvas.drawCircle(mRadius, mRadius, mRadius, mPaint);
    }
    //========================================================

}
