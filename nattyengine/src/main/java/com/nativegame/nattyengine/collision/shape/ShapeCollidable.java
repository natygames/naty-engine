package com.nativegame.nattyengine.collision.shape;

import android.graphics.Bitmap;
import android.graphics.Rect;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public interface ShapeCollidable {

    Bitmap getCollisionBitmap();

    Rect getCollisionBounds();

    void setCollisionBoundsPosition(int x, int y);

}
