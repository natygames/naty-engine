package com.nativegame.nattyengine.collision.shape;

import android.graphics.Bitmap;
import android.graphics.Rect;

public interface ShapeCollidable {

    Bitmap getCollisionBitmap();

    Rect getCollisionBounds();

    void setCollisionBoundsPosition(int x, int y);

}
