package com.nativegame.nattyengine.entity.primitive;

import android.graphics.Canvas;

import com.nativegame.nattyengine.engine.Engine;
import com.nativegame.nattyengine.engine.camera.Camera;
import com.nativegame.nattyengine.entity.shape.Shape;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class Point extends Shape {

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Point(Engine engine, float x, float y) {
        super(engine, x, y);
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public void onDraw(Canvas canvas, Camera camera) {
        float screenX = camera.getWorldToScreenX(mX);
        float screenY = camera.getWorldToScreenY(mY);
        canvas.drawPoint(screenX, screenY, mPaint);
    }

    @Override
    public boolean isCulling(Camera camera) {
        return camera.getWorldToScreenX(mX) > camera.getScreenWidth()
                || camera.getWorldToScreenY(mY) > camera.getScreenHeight()
                || camera.getWorldToScreenX(mX) < 0
                || camera.getWorldToScreenY(mY) < 0;
    }
    //========================================================

}
