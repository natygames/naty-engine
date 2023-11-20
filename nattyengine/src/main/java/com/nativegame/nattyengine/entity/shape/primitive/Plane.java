package com.nativegame.nattyengine.entity.shape.primitive;

import android.graphics.Canvas;
import android.graphics.PorterDuff;

import com.nativegame.nattyengine.camera.Camera;
import com.nativegame.nattyengine.engine.Engine;
import com.nativegame.nattyengine.entity.ScreenEntity;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class Plane extends ScreenEntity {

    protected int mColor;
    protected PorterDuff.Mode mColorMode = PorterDuff.Mode.SRC_ATOP;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Plane(Engine engine, int color) {
        super(engine);
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

    public PorterDuff.Mode getColorMode() {
        return mColorMode;
    }

    public void setColorMode(PorterDuff.Mode mode) {
        mColorMode = mode;
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public boolean isCulling(Canvas canvas, Camera camera) {
        // Plane is always on screen
        return false;
    }

    @Override
    public void onDraw(Canvas canvas, Camera camera) {
        canvas.drawColor(mColor, mColorMode);
    }
    //========================================================

}
