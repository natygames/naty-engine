package com.nativegame.nattyengine.entity.shape;

import android.graphics.ColorFilter;
import android.graphics.MaskFilter;
import android.graphics.Paint;
import android.graphics.Shader;

import com.nativegame.nattyengine.engine.Engine;
import com.nativegame.nattyengine.entity.ScreenEntity;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public abstract class Shape extends ScreenEntity {

    protected final Paint mPaint = new Paint();

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    protected Shape(Engine engine, float x, float y) {
        super(engine, x, y);
    }
    //========================================================

    //--------------------------------------------------------
    // Getter and Setter
    //--------------------------------------------------------
    public Paint getPaint() {
        return mPaint;
    }

    public void setPaint(Paint paint) {
        mPaint.set(paint);
    }

    public int getColor() {
        return mPaint.getColor();
    }

    public void setColor(int color) {
        mPaint.setColor(color);
    }

    public int getAlpha() {
        return mPaint.getAlpha();
    }

    public void setAlpha(int alpha) {
        mPaint.setAlpha(alpha);
    }

    public Paint.Style getStyle() {
        return mPaint.getStyle();
    }

    public void setStyle(Paint.Style style) {
        mPaint.setStyle(style);
    }

    public float getStrokeWidth() {
        return mPaint.getStrokeWidth();
    }

    public void setStrokeWidth(float width) {
        mPaint.setStrokeWidth(width);
    }

    public Paint.Cap getStrokeCap() {
        return mPaint.getStrokeCap();
    }

    public void setStrokeCap(Paint.Cap cap) {
        mPaint.setStrokeCap(cap);
    }

    public Paint.Join getStrokeJoin() {
        return mPaint.getStrokeJoin();
    }

    public void setStrokeJoin(Paint.Join join) {
        mPaint.setStrokeJoin(join);
    }

    public Shader getShader() {
        return mPaint.getShader();
    }

    public void setShader(Shader shader) {
        mPaint.setShader(shader);
    }

    public void clearShader() {
        mPaint.setShader(null);
    }

    public ColorFilter getColorFilter() {
        return mPaint.getColorFilter();
    }

    public void setColorFilter(ColorFilter filter) {
        mPaint.setColorFilter(filter);
    }

    public void clearColorFilter() {
        mPaint.setColorFilter(null);
    }

    public MaskFilter getMaskFilter() {
        return mPaint.getMaskFilter();
    }

    public void setMaskFilter(MaskFilter filter) {
        mPaint.setMaskFilter(filter);
    }

    public void clearMaskFilter() {
        mPaint.setMaskFilter(null);
    }

    public void setShadow(float radius, float dx, float dy, int color) {
        mPaint.setShadowLayer(mPixelFactor * radius, mPixelFactor * dx, mPixelFactor * dy, color);
    }

    public void clearShadow() {
        mPaint.clearShadowLayer();
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public void reset() {
        super.reset();
        mPaint.reset();
    }
    //========================================================

}
