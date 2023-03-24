package com.nativegame.nattyengine.input.sensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class OrientationController extends BaseSensorController {

    private float mYaw;
    private float mPitch;
    private float mRoll;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public OrientationController(Context context) {
        super(context);
    }
    //========================================================

    //--------------------------------------------------------
    // Getter and Setter
    //--------------------------------------------------------
    public float getYaw() {
        return mYaw;
    }

    public float getPitch() {
        return mPitch;
    }

    public float getRoll() {
        return mRoll;
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public void onSensorChanged(SensorEvent event) {
        mYaw = event.values[0];
        mPitch = event.values[1];
        mRoll = event.values[2];
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    protected void registerListener() {
        mSensorManager.registerListener(this,
                mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
                SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void unregisterListener() {
        mSensorManager.unregisterListener(this);
    }
    //========================================================

}
