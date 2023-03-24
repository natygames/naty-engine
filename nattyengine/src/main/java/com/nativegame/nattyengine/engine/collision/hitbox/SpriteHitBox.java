package com.nativegame.nattyengine.engine.collision.hitbox;

import android.graphics.Bitmap;

import com.nativegame.nattyengine.util.bitmap.BitmapUtils;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class SpriteHitBox extends BaseHitBox {

    private final Bitmap mHitBoxBitmap;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public SpriteHitBox(Bitmap bitmap, int hitBoxWidth, int hitBoxHeight) {
        super(hitBoxWidth, hitBoxHeight);
        mHitBoxBitmap = BitmapUtils.createBitmap(bitmap, hitBoxWidth, hitBoxHeight);
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
