package com.nativegame.nattyengine.collision.shape;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class RectCollisionShape extends CollisionShape {

    private final Bitmap mCollisionBitmap;

    public RectCollisionShape(int width, int height) {
        super(width, height);
        mCollisionBitmap = getBitmap(width, height);
    }

    private Bitmap getBitmap(int width, int height) {
        // Create an empty canvas
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        // Draw a yellow rectangle on canvas
        Paint paint = new Paint();
        paint.setColor(Color.YELLOW);
        canvas.drawRect(0, 0, width, height, paint);
        return bitmap;
    }

    @Override
    public Bitmap getCollisionBitmap() {
        return mCollisionBitmap;
    }

}
