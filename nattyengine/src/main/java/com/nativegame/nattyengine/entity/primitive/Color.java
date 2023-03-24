package com.nativegame.nattyengine.entity.primitive;

import android.graphics.Canvas;
import android.graphics.PorterDuff;

import com.nativegame.nattyengine.engine.Engine;
import com.nativegame.nattyengine.engine.camera.Camera;
import com.nativegame.nattyengine.entity.ScreenEntity;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class Color extends ScreenEntity {

    protected int mColor;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Color(Engine engine, int color) {
        super(engine, 0, 0);
        mColor = color;
    }
    //========================================================

    //--------------------------------------------------------
    // Getter and Setter
    //--------------------------------------------------------
    public int getColor() {
        return mColor;
    }

    public void setColor(int color) {
        mColor = color;
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public void onDraw(Canvas canvas, Camera camera) {
        canvas.drawColor(mColor, PorterDuff.Mode.SRC_ATOP);
    }

    @Override
    public boolean isCulling(Camera camera) {
        // Color Bg is always on screen
        return false;
    }
    //========================================================

}
