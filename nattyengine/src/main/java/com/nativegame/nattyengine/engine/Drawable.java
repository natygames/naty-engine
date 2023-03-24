package com.nativegame.nattyengine.engine;

import android.graphics.Canvas;

import com.nativegame.nattyengine.engine.camera.Camera;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public interface Drawable {

    int getLayer();

    void setLayer(int layer);

    boolean isVisible();

    void setVisible(boolean visible);

    void draw(Canvas canvas, Camera camera);

    boolean isCulling(Camera camera);

}
