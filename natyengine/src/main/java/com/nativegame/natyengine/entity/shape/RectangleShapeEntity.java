package com.nativegame.natyengine.entity.shape;

import android.graphics.Canvas;

import com.nativegame.natyengine.camera.Camera;
import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.util.TransformUtils;

import java.util.Arrays;

public abstract class RectangleShapeEntity extends ShapeEntity implements RectangleShape {

    private int mWidth;
    private int mHeight;

    private final float[] mRotatePoints = new float[4];

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    protected RectangleShapeEntity(Engine engine, float x, float y, int width, int height) {
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
        float x = TransformUtils.getTransformScaleX(mX, scalePivotX, mScaleX);
        float y = TransformUtils.getTransformScaleY(mY, scalePivotY, mScaleY);
        float endX = TransformUtils.getTransformScaleX(getEndX(), scalePivotX, mScaleX);
        float endY = TransformUtils.getTransformScaleY(getEndY(), scalePivotY, mScaleY);
        float rotationPivotX = TransformUtils.getTransformScaleX(mX + getRotationPivotX(), scalePivotX, mScaleX);
        float rotationPivotY = TransformUtils.getTransformScaleY(mY + getRotationPivotY(), scalePivotY, mScaleY);

        // Apply rotation
        float rotateLeftTopX = TransformUtils.getTransformRotateX(x, y, rotationPivotX, rotationPivotY, mRotation);
        float rotateLeftTopY = TransformUtils.getTransformRotateY(x, y, rotationPivotX, rotationPivotY, mRotation);
        float rotateRightTopX = TransformUtils.getTransformRotateX(endX, y, rotationPivotX, rotationPivotY, mRotation);
        float rotateRightTopY = TransformUtils.getTransformRotateY(endX, y, rotationPivotX, rotationPivotY, mRotation);
        float rotateLeftBottomX = TransformUtils.getTransformRotateX(x, endY, rotationPivotX, rotationPivotY, mRotation);
        float rotateLeftBottomY = TransformUtils.getTransformRotateY(x, endY, rotationPivotX, rotationPivotY, mRotation);
        float rotateRightBottomX = TransformUtils.getTransformRotateX(endX, endY, rotationPivotX, rotationPivotY, mRotation);
        float rotateRightBottomY = TransformUtils.getTransformRotateY(endX, endY, rotationPivotX, rotationPivotY, mRotation);

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
