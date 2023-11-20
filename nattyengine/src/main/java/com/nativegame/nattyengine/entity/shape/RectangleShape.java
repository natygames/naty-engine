package com.nativegame.nattyengine.entity.shape;

import android.graphics.Canvas;

import com.nativegame.nattyengine.camera.Camera;
import com.nativegame.nattyengine.engine.Engine;
import com.nativegame.nattyengine.util.math.TransformUtils;

import java.util.Arrays;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public abstract class RectangleShape extends Shape {

    private int mWidth;
    private int mHeight;

    private final float[] mRotatePoints = new float[4];

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    protected RectangleShape(Engine engine, float x, float y, int width, int height) {
        super(engine, x, y);
        mWidth = width;
        mHeight = height;
        resetScalePivot();
        resetRotationPivot();
    }
    //========================================================

    //--------------------------------------------------------
    // Getter and Setter
    //--------------------------------------------------------
    public int getWidth() {
        return mWidth;
    }

    public void setWidth(int width) {
        mWidth = width;
    }

    public int getHeight() {
        return mHeight;
    }

    public void setHeight(int height) {
        mHeight = height;
    }

    public float getCenterX() {
        return mX + mWidth / 2f;
    }

    public void setCenterX(float x) {
        mX = x - mWidth / 2f;
    }

    public float getCenterY() {
        return mY + mHeight / 2f;
    }

    public void setCenterY(float y) {
        mY = y - mHeight / 2f;
    }

    public float getEndX() {
        return mX + mWidth;
    }

    public void setEndX(float x) {
        mX = x - mWidth;
    }

    public float getEndY() {
        return mY + mHeight;
    }

    public void setEndY(float y) {
        mY = y - mHeight;
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public boolean isCulling(Canvas canvas, Camera camera) {
        // We use the four edge of rectangle to check culling
        float leftmostX;
        float rightmostX;
        float topmostY;
        float bottommostY;

        // Apply scale
        float scalePivotX = mX + getScalePivotX();
        float scalePivotY = mY + getScalePivotY();
        float x = TransformUtils.getScaleX(mX, scalePivotX, mScaleX);
        float y = TransformUtils.getScaleY(mY, scalePivotY, mScaleY);
        float endX = TransformUtils.getScaleX(getEndX(), scalePivotX, mScaleX);
        float endY = TransformUtils.getScaleY(getEndY(), scalePivotY, mScaleY);
        float rotationPivotX = TransformUtils.getScaleX(mX + getRotationPivotX(), scalePivotX, mScaleX);
        float rotationPivotY = TransformUtils.getScaleY(mY + getRotationPivotY(), scalePivotY, mScaleY);

        // Apply rotation
        float rotateLeftTopX = TransformUtils.getRotateX(x, y, rotationPivotX, rotationPivotY, mRotation);
        float rotateLeftTopY = TransformUtils.getRotateY(x, y, rotationPivotX, rotationPivotY, mRotation);
        float rotateRightTopX = TransformUtils.getRotateX(endX, y, rotationPivotX, rotationPivotY, mRotation);
        float rotateRightTopY = TransformUtils.getRotateY(endX, y, rotationPivotX, rotationPivotY, mRotation);
        float rotateLeftBottomX = TransformUtils.getRotateX(x, endY, rotationPivotX, rotationPivotY, mRotation);
        float rotateLeftBottomY = TransformUtils.getRotateY(x, endY, rotationPivotX, rotationPivotY, mRotation);
        float rotateRightBottomX = TransformUtils.getRotateX(endX, endY, rotationPivotX, rotationPivotY, mRotation);
        float rotateRightBottomY = TransformUtils.getRotateY(endX, endY, rotationPivotX, rotationPivotY, mRotation);

        // Init a temp array to store all x value
        mRotatePoints[0] = rotateLeftTopX;
        mRotatePoints[1] = rotateRightTopX;
        mRotatePoints[2] = rotateLeftBottomX;
        mRotatePoints[3] = rotateRightBottomX;

        // Sort the array to get the smallest and largest value
        Arrays.sort(mRotatePoints);
        leftmostX = mRotatePoints[0];
        rightmostX = mRotatePoints[3];

        // Init a temp array to store all y value
        mRotatePoints[0] = rotateLeftTopY;
        mRotatePoints[1] = rotateRightTopY;
        mRotatePoints[2] = rotateLeftBottomY;
        mRotatePoints[3] = rotateRightBottomY;

        // Sort the array to get the smallest and largest value
        Arrays.sort(mRotatePoints);
        topmostY = mRotatePoints[0];
        bottommostY = mRotatePoints[3];

        // Check is all four vertex out of bound
        boolean isCulling = camera.getWorldToScreenX(leftmostX, mCoordinateType) > canvas.getWidth()
                || camera.getWorldToScreenY(topmostY, mCoordinateType) > canvas.getHeight()
                || camera.getWorldToScreenX(rightmostX, mCoordinateType) < 0
                || camera.getWorldToScreenY(bottommostY, mCoordinateType) < 0;

        // Print debug info
        if (!isCulling && mEngine.isDebugMode() && mEngine.getDebugger().isDebugCulling()) {
            canvas.drawRect(camera.getWorldToScreenX(leftmostX, mCoordinateType),
                    camera.getWorldToScreenY(topmostY, mCoordinateType),
                    camera.getWorldToScreenX(rightmostX, mCoordinateType),
                    camera.getWorldToScreenY(bottommostY, mCoordinateType),
                    mEngine.getDebugger().getDebugPaint());
        }

        return isCulling;
    }

    @Override
    public void reset() {
        super.reset();
        resetScalePivot();
        resetRotationPivot();
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    public void resetScalePivot() {
        setScalePivotX(getWidth() / 2f);
        setScalePivotY(getHeight() / 2f);
    }

    public void resetRotationPivot() {
        setRotationPivotX(getWidth() / 2f);
        setRotationPivotY(getHeight() / 2f);
    }
    //========================================================

}
