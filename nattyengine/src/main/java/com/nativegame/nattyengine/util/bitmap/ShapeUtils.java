package com.nativegame.nattyengine.util.bitmap;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class ShapeUtils {

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    private ShapeUtils() {
    }
    //========================================================

    //--------------------------------------------------------
    // Static methods
    //--------------------------------------------------------
    public static Bitmap createCircleBitmap(int radius) {
        return createCircleBitmap(radius, new Paint());
    }

    public static Bitmap createCircleBitmap(int radius, Paint paint) {
        Bitmap bitmap = BitmapUtils.createBitmap(radius * 2, radius * 2);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawCircle(radius, radius, radius, paint);
        return bitmap;
    }

    public static Bitmap createOvalBitmap(int width, int height) {
        return createOvalBitmap(width, height, new Paint());
    }

    public static Bitmap createOvalBitmap(int width, int height, Paint paint) {
        Bitmap bitmap = BitmapUtils.createBitmap(width, height);
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
        Bitmap bitmap = BitmapUtils.createBitmap(width, height);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawRoundRect(0, 0, width, height, radiusX, radiusY, paint);
        return bitmap;
    }
    //========================================================

}
