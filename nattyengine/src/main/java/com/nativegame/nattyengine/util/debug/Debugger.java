package com.nativegame.nattyengine.util.debug;

import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class Debugger {

    public static final int DEFAULT_DEBUG_COLOR = Color.BLUE;
    public static final Paint.Style DEFAULT_DEBUG_STYLE = Paint.Style.STROKE;

    public static final int DEFAULT_DEBUG_TEXT_SIZE = 50;
    public static final Paint.Style DEFAULT_DEBUG_TEXT_STYLE = Paint.Style.FILL;

    private boolean mIsDebugText = true;
    private boolean mIsDebugCulling = true;

    private final Paint mDebugPaint = new Paint();
    private final Paint mDebugTextPaint = new Paint();

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Debugger() {
        mDebugPaint.setColor(DEFAULT_DEBUG_COLOR);
        mDebugPaint.setStyle(DEFAULT_DEBUG_STYLE);

        mDebugTextPaint.setColor(DEFAULT_DEBUG_COLOR);
        mDebugTextPaint.setTextSize(DEFAULT_DEBUG_TEXT_SIZE);
        mDebugTextPaint.setStyle(DEFAULT_DEBUG_TEXT_STYLE);
    }
    //========================================================

    //--------------------------------------------------------
    // Getter and Setter
    //--------------------------------------------------------
    public Paint getDebugPaint() {
        return mDebugPaint;
    }

    public void setDebugPaint(Paint paint) {
        mDebugPaint.set(paint);
    }

    public Paint getDebugTextPaint() {
        return mDebugTextPaint;
    }

    public void setDebugTextPaint(Paint paint) {
        mDebugTextPaint.set(paint);
    }

    public boolean isDebugText() {
        return mIsDebugText;
    }

    public void setDebugText(boolean isDebugText) {
        mIsDebugText = isDebugText;
    }

    public boolean isDebugCulling() {
        return mIsDebugCulling;
    }

    public void setDebugCulling(boolean isDebugCulling) {
        mIsDebugCulling = isDebugCulling;
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    public void set(Debugger debugger) {
        mIsDebugText = debugger.isDebugText();
        mIsDebugCulling = debugger.isDebugCulling();
    }
    //========================================================

}
