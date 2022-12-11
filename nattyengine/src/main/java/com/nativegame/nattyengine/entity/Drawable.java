package com.nativegame.nattyengine.entity;

import android.graphics.Canvas;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public interface Drawable {

    void draw(Canvas canvas);

    int getLayer();

    boolean isVisible();

}
