package com.nativegame.nattyengine.entity.counter;

import com.nativegame.nattyengine.camera.Camera;
import com.nativegame.nattyengine.engine.Engine;
import com.nativegame.nattyengine.entity.text.Text;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class UPSCounter extends Text {

    private int mUpdateCount;
    private long mTotalTime;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public UPSCounter(Engine engine, float relativeCameraX, float relativeCameraY, int width, int height) {
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
        mUpdateCount = 0;
        mTotalTime = 0;
    }

    @Override
    protected void onUpdate(long elapsedMillis) {
        mUpdateCount++;
        mTotalTime += elapsedMillis;
        if (mTotalTime >= 1000) {
            float ups = mUpdateCount * 1000f / mTotalTime;
            setText("UPS: " + ups);
            mUpdateCount = 0;
            mTotalTime = mTotalTime % 1000;
        }
    }
    //========================================================

}
