package com.nativegame.nattyengine.entity.sprite;

import android.graphics.Canvas;
import android.graphics.Matrix;

import com.nativegame.nattyengine.engine.Engine;
import com.nativegame.nattyengine.engine.camera.Camera;
import com.nativegame.nattyengine.entity.shape.RectangleShape;
import com.nativegame.nattyengine.texture.Texture;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class Sprite extends RectangleShape {

    protected Texture mTexture;
    protected float mScaleX = 1;
    protected float mScaleY = 1;
    protected float mRotation;
    protected float mAlpha = 255;

    private float mScalePivotX;
    private float mScalePivotY;
    private float mRotationPivotX;
    private float mRotationPivotY;

    private final Matrix mMatrix = new Matrix();

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Sprite(Engine engine, Texture texture) {
        this(engine, 0, 0, texture);
    }

    public Sprite(Engine engine, float x, float y, Texture texture) {
        super(engine, x, y, texture.getWidth(), texture.getHeight());
        mTexture = texture;
        resetScalePivot();
        resetRotationPivot();
    }
    //========================================================

    //--------------------------------------------------------
    // Getter and Setter
    //--------------------------------------------------------
    public Texture getTexture() {
        return mTexture;
    }

    public void setTexture(Texture texture) {
        mTexture = texture;
    }

    public float getScaleX() {
        if (mScaleX < 0) {
            return 0;
        }
        return mScaleX;
    }

    public void setScaleX(float scaleX) {
        mScaleX = scaleX;
    }

    public float getScaleY() {
        if (mScaleY < 0) {
            return 0;
        }
        return mScaleY;
    }

    public void setScaleY(float scaleY) {
        mScaleY = scaleY;
    }

    public void setScale(float scale) {
        mScaleX = scale;
        mScaleY = scale;
    }

    public float getRotation() {
        return mRotation;
    }

    public void setRotation(float rotation) {
        mRotation = rotation;
    }

    public float getRotationPivotX() {
        return mRotationPivotX;
    }

    public void setRotationPivotX(float rotationPivotX) {
        mRotationPivotX = rotationPivotX;
    }

    public float getRotationPivotY() {
        return mRotationPivotY;
    }

    public void setRotationPivotY(float rotationPivotY) {
        mRotationPivotY = rotationPivotY;
    }

    public float getScalePivotX() {
        return mScalePivotX;
    }

    public void setScalePivotX(float scalePivotX) {
        mScalePivotX = scalePivotX;
    }

    public float getScalePivotY() {
        return mScalePivotY;
    }

    public void setScalePivotY(float scalePivotY) {
        mScalePivotY = scalePivotY;
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public int getAlpha() {
        if (mAlpha < 0) {
            return 0;
        }
        if (mAlpha > 255) {
            return 255;
        }
        return (int) mAlpha;
    }

    @Override
    public void setAlpha(int alpha) {
        mAlpha = alpha;
    }

    @Override
    public void onDraw(Canvas canvas, Camera camera) {
        float offsetX = (1 - mScaleX) * mScalePivotX;
        float offsetY = (1 - mScaleY) * mScalePivotY;
        mMatrix.reset();
        mMatrix.postScale(getScaleX() * camera.getPixelFactor(), getScaleY() * camera.getPixelFactor());
        mMatrix.postTranslate(camera.getWorldToScreenX(mX + offsetX), camera.getWorldToScreenY(mY + offsetY));
        mMatrix.postRotate(mRotation, camera.getWorldToScreenX(mX + mRotationPivotX), camera.getWorldToScreenY(mY + mRotationPivotY));
        mPaint.setAlpha(getAlpha());
        canvas.drawBitmap(mTexture.getBitmap(), mMatrix, mPaint);
    }

    @Override
    public void reset() {
        super.reset();
        mScaleX = 1;
        mScaleY = 1;
        mRotation = 0;
        mAlpha = 255;
        mMatrix.reset();
        resetSize();
        resetScalePivot();
        resetRotationPivot();
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    public void resetSize() {
        mWidth = mTexture.getWidth();
        mHeight = mTexture.getHeight();
    }

    public void resetScalePivot() {
        mScalePivotX = mWidth / 2f;
        mScalePivotY = mHeight / 2f;
    }

    public void resetRotationPivot() {
        mRotationPivotX = mWidth / 2f;
        mRotationPivotY = mHeight / 2f;
    }
    //========================================================

}
