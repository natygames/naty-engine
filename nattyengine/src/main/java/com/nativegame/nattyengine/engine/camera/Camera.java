package com.nativegame.nattyengine.engine.camera;

import com.nativegame.nattyengine.ui.GameView;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class Camera {

    private final int mScreenWidth;
    private final int mScreenHeight;
    private final int mCameraWidth;
    private final int mCameraHeight;
    private final int mWorldWidth;
    private final int mWorldHeight;

    private final int mScreenCenterX;
    private final int mScreenCenterY;
    private final int mWorldCenterX;
    private final int mWorldCenterY;

    private final float mPixelFactor;

    private float mOffsetX;
    private float mOffsetY;
    private float mZoom = 1.0f;

    private boolean mIsFocus = false;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Camera(GameView gameView, int cameraWidth, int cameraHeight, int worldWidth, int worldHeight) {
        this(gameView, gameView.getWidth() / 2, gameView.getHeight() / 2, cameraWidth, cameraHeight, worldWidth, worldHeight);
    }

    public Camera(GameView gameView, int centerX, int centerY, int cameraWidth, int cameraHeight, int worldWidth, int worldHeight) {
        mScreenWidth = gameView.getWidth();
        mScreenHeight = gameView.getHeight();
        mCameraWidth = cameraWidth;
        mCameraHeight = cameraHeight;
        mWorldWidth = worldWidth;
        mWorldHeight = worldHeight;

        mScreenCenterX = centerX;
        mScreenCenterY = centerY;
        mWorldCenterX = worldWidth / 2;
        mWorldCenterY = worldHeight / 2;

        mPixelFactor = cameraWidth * 1f / worldWidth;
    }
    //========================================================

    //--------------------------------------------------------
    // Getter and Setter
    //--------------------------------------------------------
    public int getScreenWidth() {
        return mScreenWidth;
    }

    public int getScreenHeight() {
        return mScreenHeight;
    }

    public float getCameraWidth() {
        return mCameraWidth * mZoom;
    }

    public float getCameraHeight() {
        return mCameraHeight * mZoom;
    }

    public int getWorldWidth() {
        return mWorldWidth;
    }

    public int getWorldHeight() {
        return mWorldHeight;
    }

    public float getPixelFactor() {
        return mPixelFactor * mZoom;
    }

    public float getOffsetX() {
        return mOffsetX;
    }

    public void setOffsetX(float offsetX) {
        mOffsetX = offsetX;
    }

    public float getOffsetY() {
        return mOffsetY;
    }

    public void setOffsetY(float offsetY) {
        mOffsetY = offsetY;
    }

    public float getZoom() {
        return mZoom;
    }

    public void setZoom(float zoom) {
        mZoom = zoom;
    }

    public boolean isFocus() {
        return mIsFocus;
    }

    public void setFocus(boolean focus) {
        mIsFocus = focus;
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    public float getWorldToScreenX(float worldX) {
        return mScreenCenterX - (mWorldCenterX - worldX) * getPixelFactor();
    }

    public float getWorldToScreenY(float worldY) {
        return mScreenCenterY - (mWorldCenterY - worldY) * getPixelFactor();
    }

    public float getScreenToWorldX(float screenX) {
        // Margin between screen and camera
        float marginX = mScreenCenterX - getCameraWidth() / 2f;
        return (screenX - marginX) / getPixelFactor();
    }

    public float getScreenToWorldY(float screenY) {
        // Margin between screen and camera
        float marginY = mScreenCenterY - getCameraHeight() / 2f;
        return (screenY - marginY) / getPixelFactor();
    }
    //========================================================

}
