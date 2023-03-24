package com.nativegame.nattyengine.engine.collision.hitbox;

import android.graphics.Bitmap;

import com.nativegame.nattyengine.util.bitmap.ShapeUtils;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class CircleHitBox extends BaseHitBox {

    private final Bitmap mHitBoxBitmap;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public CircleHitBox(int hitBoxRadius) {
        super(hitBoxRadius * 2, hitBoxRadius * 2);
        mHitBoxBitmap = ShapeUtils.createCircleBitmap(hitBoxRadius);
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
