package com.nativegame.nattyengine.entity;

import android.graphics.Canvas;

import com.nativegame.nattyengine.engine.Engine;
import com.nativegame.nattyengine.camera.Camera;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public abstract class ScreenEntity extends Entity implements Drawable {

    protected int mLayer;
    private boolean mIsVisible = true;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    protected ScreenEntity(Engine engine) {
        super(engine);
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public int getLayer() {
        return mLayer;
    }

    @Override
    public void setLayer(int layer) {
        mLayer = layer;
    }

    @Override
    public boolean isVisible() {
        return mIsVisible;
    }

    @Override
    public void setVisible(boolean visible) {
        mIsVisible = visible;
    }

    @Override
    public void draw(Canvas canvas, Camera camera) {
        onPreDraw(canvas, camera);
        onDraw(canvas, camera);
        onPostDraw(canvas, camera);
    }

    @Override
    public void reset() {
        super.reset();
        mIsVisible = true;
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    protected void onPreDraw(Canvas canvas, Camera camera) {
    }

    protected abstract void onDraw(Canvas canvas, Camera camera);

    protected void onPostDraw(Canvas canvas, Camera camera) {
    }
    //========================================================

}
