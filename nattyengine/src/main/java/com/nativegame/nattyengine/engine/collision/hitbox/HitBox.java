package com.nativegame.nattyengine.engine.collision.hitbox;

import android.graphics.Bitmap;
import android.graphics.Rect;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public interface HitBox {

    Bitmap getCollisionBitmap();

    Rect getCollisionBounds();

    void setCollisionBoundsPosition(int x, int y);

}
