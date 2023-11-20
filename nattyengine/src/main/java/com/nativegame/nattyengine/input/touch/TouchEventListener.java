package com.nativegame.nattyengine.input.touch;

import com.nativegame.nattyengine.camera.Camera;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public interface TouchEventListener {

    Camera.CoordinateType getCoordinateType();

    void setCoordinateType(Camera.CoordinateType type);

    void onTouchEvent(int type, float touchX, float touchY);

}
