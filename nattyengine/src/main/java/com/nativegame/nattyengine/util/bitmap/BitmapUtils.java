package com.nativegame.nattyengine.util.bitmap;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class BitmapUtils {

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    private BitmapUtils() {
    }
    //========================================================

    //--------------------------------------------------------
    // Static methods
    //--------------------------------------------------------
    public static Bitmap createBitmap(int width, int height) {
        return createBitmap(width, height, Bitmap.Config.ARGB_8888);
    }

    public static Bitmap createBitmap(int width, int height, Bitmap.Config config) {
        return Bitmap.createBitmap(width, height, config);
    }

    public static Bitmap createBitmap(Bitmap src, int width, int height) {
        return createBitmap(src, width, height, null, Bitmap.Config.ARGB_8888);
    }

    public static Bitmap createBitmap(Bitmap src, int width, int height, Paint paint) {
        return createBitmap(src, width, height, paint, Bitmap.Config.ARGB_8888);
    }

    public static Bitmap createBitmap(Bitmap src, int width, int height, Bitmap.Config config) {
        return createBitmap(src, width, height, null, config);
    }

    public static Bitmap createBitmap(Bitmap src, int width, int height, Paint paint, Bitmap.Config config) {
        Bitmap bitmap = createBitmap(width, height, config);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawBitmap(src, null, new Rect(0, 0, width, height), paint);
        return bitmap;
    }
    //========================================================

}
