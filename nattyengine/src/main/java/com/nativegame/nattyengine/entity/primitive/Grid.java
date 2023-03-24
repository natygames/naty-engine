package com.nativegame.nattyengine.entity.primitive;

import android.graphics.Canvas;

import com.nativegame.nattyengine.engine.Engine;
import com.nativegame.nattyengine.engine.camera.Camera;
import com.nativegame.nattyengine.entity.shape.Shape;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class Grid extends Shape {

    protected float mX2;
    protected float mY2;

    protected int mRow;
    protected int mColumn;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Grid(Engine engine, int row, int column) {
        this(engine, 0, 0, engine.getCamera().getWorldWidth(), engine.getCamera().getWorldHeight(), row, column);
    }

    public Grid(Engine engine, float x, float y, float x2, float y2, int row, int column) {
        super(engine, x, y);
        mX2 = x2;
        mY2 = y2;
        mRow = row;
        mColumn = column;
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

    public int getRow() {
        return mRow;
    }

    public void setRow(int row) {
        mRow = row;
    }

    public int getColumn() {
        return mColumn;
    }

    public void setColumn(int column) {
        mColumn = column;
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public void onDraw(Canvas canvas, Camera camera) {
        // Draw vertical line
        for (int i = 0; i < mColumn + 1; i++) {
            float x = mX + i * (mX2 - mX) / mColumn;
            float screenX = camera.getWorldToScreenX(x);
            float screenY = camera.getWorldToScreenY(mY);
            float screenY2 = camera.getWorldToScreenY(mY2);
            canvas.drawLine(screenX, screenY, screenX, screenY2, mPaint);
        }

        // Draw horizontal line
        for (int i = 0; i < mRow + 1; i++) {
            float y = mY + i * (mY2 - mY) / mRow;
            float screenX = camera.getWorldToScreenX(mX);
            float screenX2 = camera.getWorldToScreenX(mX2);
            float screenY = camera.getWorldToScreenY(y);
            canvas.drawLine(screenX, screenY, screenX2, screenY, mPaint);
        }
    }

    @Override
    public boolean isCulling(Camera camera) {
        return camera.getWorldToScreenX(mX) > camera.getScreenWidth()
                || camera.getWorldToScreenY(mY) > camera.getScreenHeight()
                || camera.getWorldToScreenX(mX2) < 0
                || camera.getWorldToScreenY(mY2) < 0;
    }
    //========================================================

}
