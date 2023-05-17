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

    public static Bitmap createCircleBitmap(int radius) {
        return createCircleBitmap(radius, new Paint());
    }

    public static Bitmap createCircleBitmap(int radius, Paint paint) {
        Bitmap bitmap = createBitmap(radius * 2, radius * 2);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawCircle(radius, radius, radius, paint);
        return bitmap;
    }

    public static Bitmap createOvalBitmap(int width, int height) {
        return createOvalBitmap(width, height, new Paint());
    }

    public static Bitmap createOvalBitmap(int width, int height, Paint paint) {
        Bitmap bitmap = createBitmap(width, height);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawOval(0, 0, width, height, paint);
        return bitmap;
    }

    public static Bitmap createRectangleBitmap(int width, int height) {
        return createRectangleBitmap(width, height, 0, 0, new Paint());
    }

    public static Bitmap createRectangleBitmap(int width, int height, int radius) {
        return createRectangleBitmap(width, height, radius, radius, new Paint());
    }

    public static Bitmap createRectangleBitmap(int width, int height, int radiusX, int radiusY) {
        return createRectangleBitmap(width, height, radiusX, radiusY, new Paint());
    }

    public static Bitmap createRectangleBitmap(int width, int height, Paint paint) {
        return createRectangleBitmap(width, height, 0, 0, paint);
    }

    public static Bitmap createRectangleBitmap(int width, int height, int radius, Paint paint) {
        return createRectangleBitmap(width, height, radius, radius, paint);
    }

    public static Bitmap createRectangleBitmap(int width, int height, int radiusX, int radiusY, Paint paint) {
        Bitmap bitmap = createBitmap(width, height);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawRoundRect(0, 0, width, height, radiusX, radiusY, paint);
        return bitmap;
    }
    //========================================================

}
