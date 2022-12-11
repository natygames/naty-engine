package com.nativegame.nattyengine.collision.shape;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class CircleCollisionShape extends CollisionShape {

    private final Bitmap mCollisionBitmap;

    public CircleCollisionShape(int width, int height) {
        super(width, height);
        mCollisionBitmap = getBitmap(width, height);
    }

    private Bitmap getBitmap(int width, int height) {
        // Create an empty canvas
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        // Draw a yellow circle on canvas
        Paint paint = new Paint();
        paint.setColor(Color.YELLOW);
        canvas.drawCircle(width / 2f, height / 2f, Math.min(width, height) / 2f, paint);
        return bitmap;
    }

    @Override
    public Bitmap getCollisionBitmap() {
        return mCollisionBitmap;
    }

}
