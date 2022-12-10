package com.nativegame.nattyengine.entity;

import android.graphics.Canvas;

public interface Drawable {

    void draw(Canvas canvas);

    int getLayer();

    boolean isVisible();

}
