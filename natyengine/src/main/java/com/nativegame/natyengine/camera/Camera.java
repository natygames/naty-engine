package com.nativegame.natyengine.camera;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public interface Camera {

    public enum CoordinateType {
        WORLD,
        CAMERA
    }

    int getCameraWidth();

    int getCameraHeight();

    int getWorldWidth();

    int getWorldHeight();

    float getPixelFactor();

    float getX();

    void setX(float x);

    float getY();

    void setY(float y);

    float getCenterX();

    void setCenterX(float centerX);

    float getCenterY();

    void setCenterY(float centerY);

    float getZoom();

    void setZoom(float zoom);

    float getWorldToScreenZoom(CoordinateType type);

    float getWorldToScreenX(float worldX, CoordinateType type);

    float getWorldToScreenY(float worldY, CoordinateType type);

    float getScreenToWorldX(float screenX, CoordinateType type);

    float getScreenToWorldY(float screenY, CoordinateType type);

    void update(long elapsedMillis);

}
