package com.nativegame.nattyengine.collision.shape;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class BitmapCollisionShape extends CollisionShape {

    private final Bitmap mCollisionBitmap;

    public BitmapCollisionShape(Bitmap bitmap, int width, int height) {
        super(width, height);
        mCollisionBitmap = getBitmap(bitmap, width, height);
    }

    private Bitmap getBitmap(Bitmap sourceBitmap, int width, int height) {
        // Create an empty canvas
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        // Draw a yellow bitmap on canvas
        Paint paint = new Paint();
        paint.setColorFilter(new PorterDuffColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP));
        canvas.drawBitmap(sourceBitmap, null, new Rect(0, 0, width, height), paint);
        return bitmap;
    }

    @Override
    public Bitmap getCollisionBitmap() {
        return mCollisionBitmap;
    }

}
