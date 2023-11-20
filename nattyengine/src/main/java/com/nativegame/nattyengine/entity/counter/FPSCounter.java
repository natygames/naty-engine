package com.nativegame.nattyengine.entity.counter;

import android.graphics.Canvas;

import com.nativegame.nattyengine.camera.Camera;
import com.nativegame.nattyengine.engine.Engine;
import com.nativegame.nattyengine.entity.text.Text;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class FPSCounter extends Text {

    private int mDrawCount;
    private long mTotalTime;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public FPSCounter(Engine engine, float relativeCameraX, float relativeCameraY, int width, int height) {
        super(engine, relativeCameraX, relativeCameraY, width, height, "");
        setCoordinateType(Camera.CoordinateType.CAMERA);
        setPaint(engine.getDebugger().getDebugTextPaint());
        setTextHorizontalAlign(TextHorizontalAlign.LEFT);
        setTextVerticalAlign(TextVerticalAlign.TOP);
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    protected void onStart() {
        mDrawCount = 0;
        mTotalTime = 0;
    }

    @Override
    protected void onUpdate(long elapsedMillis) {
        mTotalTime += elapsedMillis;
        if (mTotalTime >= 1000) {
            float fps = mDrawCount * 1000f / mTotalTime;
            setText("FPS: " + fps);
            mDrawCount = 0;
            mTotalTime = mTotalTime % 1000;
        }
    }

    @Override
    protected void onPostDraw(Canvas canvas, Camera camera) {
        mDrawCount++;
    }
    //========================================================

}
