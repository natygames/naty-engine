package com.nativegame.nattyengine.entity.text;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;

import com.nativegame.nattyengine.engine.Engine;
import com.nativegame.nattyengine.engine.camera.Camera;
import com.nativegame.nattyengine.entity.shape.RectangleShape;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class Text extends RectangleShape {

    private String mText;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Text(Engine engine, String text) {
        this(engine, 0, 0, 0, 0, text);
    }

    public Text(Engine engine, int width, int height, String text) {
        this(engine, 0, 0, width, height, text);
    }

    public Text(Engine engine, float x, float y, int width, int height, String text) {
        super(engine, x, y, width, height);
        mText = text;
    }
    //========================================================

    //--------------------------------------------------------
    // Getter and Setter
    //--------------------------------------------------------
    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }

    public float getTextSize() {
        return mPaint.getTextSize();
    }

    public void setTextSize(float textSize) {
        mPaint.setTextSize(mPixelFactor * textSize);
    }

    public Paint.Align getTextAlign() {
        return mPaint.getTextAlign();
    }

    public void setTextAlign(Paint.Align align) {
        mPaint.setTextAlign(align);
    }

    public Typeface getTextTypeface() {
        return mPaint.getTypeface();
    }

    public void setTextTypeface(Typeface typeface) {
        mPaint.setTypeface(typeface);
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public void onDraw(Canvas canvas, Camera camera) {
        float screenX = camera.getWorldToScreenX(getCenterX());
        float screenY = camera.getWorldToScreenY(getCenterY());
        canvas.drawText(mText, screenX, screenY, mPaint);
    }
    //========================================================

}
