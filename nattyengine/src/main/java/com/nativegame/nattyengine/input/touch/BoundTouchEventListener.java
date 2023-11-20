package com.nativegame.nattyengine.input.touch;

import com.nativegame.nattyengine.camera.Camera;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public interface BoundTouchEventListener {

    Camera.CoordinateType getCoordinateType();

    void setCoordinateType(Camera.CoordinateType type);

    float getX();

    float getY();

    float getEndX();

    float getEndY();

    void onAreaTouchEvent(int type, float relativeTouchX, float relativeTouchY);

}
