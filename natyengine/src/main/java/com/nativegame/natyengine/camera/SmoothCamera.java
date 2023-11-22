package com.nativegame.natyengine.camera;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class SmoothCamera extends BaseCamera {

    private float mSpeedX;
    private float mSpeedY;
    private float mTargetX;
    private float mTargetY;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public SmoothCamera(int cameraWidth, int cameraHeight) {
        super(cameraWidth, cameraHeight);
    }

    public SmoothCamera(int cameraWidth, int cameraHeight, int worldWidth, int worldHeight) {
        super(cameraWidth, cameraHeight, worldWidth, worldHeight);
    }

    public SmoothCamera(int cameraWidth, int cameraHeight, int worldWidth, int worldHeight, int projectWorldWidth, int projectWorldHeight) {
        super(cameraWidth, cameraHeight, worldWidth, worldHeight, projectWorldWidth, projectWorldHeight);
    }
    //========================================================

    //--------------------------------------------------------
    // Getter and Setter
    //--------------------------------------------------------
    public float getSpeedX() {
        return mSpeedX;
    }

    public void setSpeedX(float speedX) {
        mSpeedX = speedX;
    }

    public float getSpeedY() {
        return mSpeedY;
    }

    public void setSpeedY(float speedY) {
        mSpeedY = speedY;
    }

    public float getTargetX() {
        return mTargetX;
    }

    public void setTargetX(float targetX) {
        mTargetX = targetX;
    }

    public float getTargetY() {
        return mTargetY;
    }

    public void setTargetY(float targetY) {
        mTargetY = targetY;
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public void update(long elapsedMillis) {
        float offsetX = mSpeedX * elapsedMillis;
        float offsetY = mSpeedY * elapsedMillis;
        if (getCenterX() != mTargetX) {
            if (getCenterX() < mTargetX) {
                setCenterX(getCenterX() + Math.min(offsetX, mTargetX - getCenterX()));
            } else {
                setCenterX(getCenterX() - Math.min(offsetX, getCenterX() - mTargetX));
            }
        }
        if (getCenterY() != mTargetY) {
            if (getCenterY() < mTargetY) {
                setCenterY(getCenterY() + Math.min(offsetY, mTargetY - getCenterY()));
            } else {
                setCenterY(getCenterY() - Math.min(offsetY, getCenterY() - mTargetY));
            }
        }
    }
    //========================================================

}
