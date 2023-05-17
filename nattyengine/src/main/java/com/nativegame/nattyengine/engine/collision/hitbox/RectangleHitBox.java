package com.nativegame.nattyengine.engine.collision.hitbox;

import android.graphics.Bitmap;

import com.nativegame.nattyengine.util.bitmap.BitmapUtils;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class RectangleHitBox extends BaseHitBox {

    private final Bitmap mHitBoxBitmap;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public RectangleHitBox(int hitBoxWidth, int hitBoxHeight) {
        this(hitBoxWidth, hitBoxHeight, 0, 0);
    }

    public RectangleHitBox(int hitBoxWidth, int hitBoxHeight, int radius) {
        this(hitBoxWidth, hitBoxHeight, radius, radius);
    }

    public RectangleHitBox(int hitBoxWidth, int hitBoxHeight, int radiusX, int radiusY) {
        super(hitBoxWidth, hitBoxHeight);
        mHitBoxBitmap = BitmapUtils.createRectangleBitmap(hitBoxWidth, hitBoxHeight, radiusX, radiusY);
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public Bitmap getCollisionBitmap() {
        return mHitBoxBitmap;
    }
    //========================================================

}
