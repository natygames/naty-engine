package com.nativegame.natyengine.entity.shape.primitive;

import android.graphics.Canvas;

import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.entity.shape.RectangleShapeEntity;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class Grid extends RectangleShapeEntity {

    protected int mRow;
    protected int mColumn;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Grid(Engine engine, int row, int column) {
        this(engine, 0, 0, engine.getCamera().getWorldWidth(), engine.getCamera().getWorldHeight(), row, column);
    }

    public Grid(Engine engine, float x, float y, int width, int height, int row, int column) {
        super(engine, x, y, width, height);
        mRow = row;
        mColumn = column;
    }
    //========================================================

    //--------------------------------------------------------
    // Getter and Setter
    //--------------------------------------------------------
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

    public int getGridWidth() {
        return getWidth() / mColumn;
    }

    public int getGridHeight() {
        return getHeight() / mRow;
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    protected void onDrawCanvas(Canvas canvas) {
        // Draw vertical line
        float gridWidth = getGridWidth();
        for (int i = 0; i <= mColumn; i++) {
            float x = i * gridWidth;
            canvas.drawLine(x, 0, x, getHeight(), mPaint);
        }

        // Draw horizontal line
        float gridHeight = getGridHeight();
        for (int i = 0; i <= mRow; i++) {
            float y = i * gridHeight;
            canvas.drawLine(0, y, getWidth(), y, mPaint);
        }
    }
    //========================================================

}
