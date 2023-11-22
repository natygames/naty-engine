package com.nativegame.natyengine.camera;

import com.nativegame.natyengine.util.exception.EngineRuntimeException;
import com.nativegame.natyengine.util.ResolutionUtils;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public abstract class BaseCamera implements Camera {

    private final int mCameraWidth;
    private final int mCameraHeight;
    private final int mWorldWidth;
    private final int mWorldHeight;
    private final int mProjectWorldWidth;
    private final int mProjectWorldHeight;
    private final float mPixelFactor;

    private float mX;
    private float mY;
    private float mZoom = 1.0f;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    protected BaseCamera(int cameraWidth, int cameraHeight) {
        this(cameraWidth, cameraHeight, cameraWidth, cameraHeight);
    }

    protected BaseCamera(int cameraWidth, int cameraHeight,
                         int worldWidth, int worldHeight) {
        this(cameraWidth, cameraHeight, worldWidth, worldHeight,
                ResolutionUtils.getResolutionWidth(cameraWidth, cameraHeight, worldWidth, worldHeight),
                ResolutionUtils.getResolutionHeight(cameraWidth, cameraHeight, worldWidth, worldHeight));
    }

    protected BaseCamera(int cameraWidth, int cameraHeight,
                         int worldWidth, int worldHeight,
                         int projectWorldWidth, int projectWorldHeight) {
        mPixelFactor = projectWorldWidth * 1f / worldWidth;
        mCameraWidth = (int) (cameraWidth / mPixelFactor);
        mCameraHeight = (int) (cameraHeight / mPixelFactor);
        mWorldWidth = worldWidth;
        mWorldHeight = worldHeight;
        mProjectWorldWidth = projectWorldWidth;
        mProjectWorldHeight = projectWorldHeight;
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public int getCameraWidth() {
        return (int) (mCameraWidth / mZoom);
    }

    @Override
    public int getCameraHeight() {
        return (int) (mCameraHeight / mZoom);
    }

    @Override
    public int getWorldWidth() {
        return mWorldWidth;
    }

    @Override
    public int getWorldHeight() {
        return mWorldHeight;
    }

    @Override
    public float getPixelFactor() {
        return mPixelFactor;
    }

    @Override
    public float getX() {
        return getCenterX() - (mCameraWidth / 2f) / mZoom;
    }

    @Override
    public void setX(float x) {
        mX = x;
        if (mX < 0) {
            mX = 0;
        }
        if (mX > mWorldWidth - mCameraWidth) {
            mX = mWorldWidth - mCameraWidth;
        }
    }

    @Override
    public float getY() {
        return getCenterY() - (mCameraHeight / 2f) / mZoom;
    }

    @Override
    public void setY(float y) {
        mY = y;
        if (mY < 0) {
            mY = 0;
        }
        if (mY > mWorldHeight - mCameraHeight) {
            mY = mWorldHeight - mCameraHeight;
        }
    }

    @Override
    public float getCenterX() {
        return mX + mCameraWidth / 2f;
    }

    @Override
    public void setCenterX(float centerX) {
        setX(centerX - mCameraWidth / 2f);
    }

    @Override
    public float getCenterY() {
        return mY + mCameraHeight / 2f;
    }

    @Override
    public void setCenterY(float centerY) {
        setY(centerY - mCameraHeight / 2f);
    }

    @Override
    public float getZoom() {
        return mZoom;
    }

    @Override
    public void setZoom(float zoom) {
        mZoom = zoom;
    }

    @Override
    public float getWorldToScreenZoom(CoordinateType type) {
        switch (type) {
            case WORLD:
                return mZoom;
            case CAMERA:
                return 1;
            default:
                throw new EngineRuntimeException("CoordinateType not found!");
        }
    }

    @Override
    public float getWorldToScreenX(float worldX, CoordinateType type) {
        switch (type) {
            case WORLD:
                return getProjectX(worldX) - getScreenMarginX();
            case CAMERA:
                return getScreenWidth() / 2f - (mCameraWidth / 2f - worldX) * mPixelFactor;
            default:
                throw new EngineRuntimeException("CoordinateType not found!");
        }
    }

    @Override
    public float getWorldToScreenY(float worldY, CoordinateType type) {
        switch (type) {
            case WORLD:
                return getProjectY(worldY) - getScreenMarginY();
            case CAMERA:
                return getScreenHeight() / 2f - (mCameraHeight / 2f - worldY) * mPixelFactor;
            default:
                throw new EngineRuntimeException("CoordinateType not found!");
        }
    }

    @Override
    public float getScreenToWorldX(float screenX, CoordinateType type) {
        switch (type) {
            case WORLD:
                return getWorldX(screenX + getScreenMarginX());
            case CAMERA:
                return mCameraWidth / 2f - (getScreenWidth() / 2f - screenX) / mPixelFactor;
            default:
                throw new EngineRuntimeException("CoordinateType not found!");
        }
    }

    @Override
    public float getScreenToWorldY(float screenY, CoordinateType type) {
        switch (type) {
            case WORLD:
                return getWorldY(screenY + getScreenMarginY());
            case CAMERA:
                return mCameraHeight / 2f - (getScreenHeight() / 2f - screenY) / mPixelFactor;
            default:
                throw new EngineRuntimeException("CoordinateType not found!");
        }
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    private float getProjectX(float worldX) {
        return mProjectWorldWidth / 2f - (mWorldWidth / 2f - worldX) * mPixelFactor * mZoom;
    }

    private float getProjectY(float worldY) {
        return mProjectWorldHeight / 2f - (mWorldHeight / 2f - worldY) * mPixelFactor * mZoom;
    }

    private float getWorldX(float projectX) {
        return mWorldWidth / 2f - (mProjectWorldWidth / 2f - projectX) / mPixelFactor * mZoom;
    }

    private float getWorldY(float projectY) {
        return mWorldHeight / 2f - (mProjectWorldHeight / 2f - projectY) / mPixelFactor * mZoom;
    }

    private float getScreenWidth() {
        return mCameraWidth * mPixelFactor;
    }

    private float getScreenHeight() {
        return mCameraHeight * mPixelFactor;
    }

    private float getScreenMarginX() {
        return mX * mPixelFactor;
    }

    private float getScreenMarginY() {
        return mY * mPixelFactor;
    }
    //========================================================

}
