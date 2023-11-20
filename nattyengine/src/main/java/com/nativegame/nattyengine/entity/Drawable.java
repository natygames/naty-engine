package com.nativegame.nattyengine.entity;

import android.graphics.Canvas;

import com.nativegame.nattyengine.camera.Camera;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public interface Drawable {

    int getLayer();

    void setLayer(int layer);

    boolean isVisible();

    void setVisible(boolean visible);

    boolean isCulling(Canvas canvas, Camera camera);

    void draw(Canvas canvas, Camera camera);

}
