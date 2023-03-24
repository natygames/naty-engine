package com.nativegame.nattyengine.engine.collision.hitbox;

import android.graphics.Bitmap;

import com.nativegame.nattyengine.util.bitmap.ShapeUtils;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class OvalHitBox extends BaseHitBox {

    private final Bitmap mHitBoxBitmap;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public OvalHitBox(int hitBoxWidth, int hitBoxHeight) {
        super(hitBoxWidth, hitBoxHeight);
        mHitBoxBitmap = ShapeUtils.createOvalBitmap(hitBoxWidth, hitBoxHeight);
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
