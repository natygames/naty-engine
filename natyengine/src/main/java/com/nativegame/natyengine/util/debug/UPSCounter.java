package com.nativegame.natyengine.util.debug;

import com.nativegame.natyengine.camera.Camera;
import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.entity.text.Text;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class UPSCounter extends Text {

    private int mUpdateCount;
    private long mTotalTime;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public UPSCounter(Engine engine, int width, int height) {
        this(engine, 0, 0, width, height);
    }

    public UPSCounter(Engine engine, float x, float y, int width, int height) {
        super(engine, x, y, width, height);
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
