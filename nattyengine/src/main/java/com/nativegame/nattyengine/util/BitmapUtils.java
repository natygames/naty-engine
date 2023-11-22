package com.nativegame.nattyengine.util;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.nativegame.nattyengine.util.exception.EngineRuntimeException;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class BitmapUtils {

    public enum BitmapRadiate {
        LEFT_TO_RIGHT,
        RIGHT_TO_LEFT,
        TOP_TO_DOWN,
        BOTTOM_TO_UP,
        IN_TO_OUT,
        OUT_TO_IN
    }

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

    public static Bitmap createBitmapRegion(Bitmap src, int x, int y, int width, int height) {
        return Bitmap.createBitmap(src, x, y, width, height);
    }

    public static Bitmap createBitmapRegion(Bitmap src, int x, int y, int width, int height, int targetWidth, int targetHeight) {
        Bitmap bitmap = createBitmap(targetWidth, targetHeight);
        Canvas canvas = new Canvas(bitmap);
        Bitmap clipBitmap = Bitmap.createBitmap(src, x, y, width, height);
        canvas.drawBitmap(clipBitmap, null, new Rect(0, 0, targetWidth, targetHeight), null);
        return bitmap;
    }

    public static Bitmap[] createRadiateBitmapRegions(Bitmap src, int count, BitmapRadiate radiate) {
        switch (radiate) {
            case LEFT_TO_RIGHT:
                return createRadiateBitmapRegionsLeftRight(src, count);
            case RIGHT_TO_LEFT:
                return createRadiateBitmapRegionsRightLeft(src, count);
            case TOP_TO_DOWN:
                return createRadiateBitmapRegionsTopDown(src, count);
            case BOTTOM_TO_UP:
                return createRadiateBitmapRegionsBottomUp(src, count);
            case IN_TO_OUT:
                return createRadiateBitmapRegionsInOut(src, count);
            case OUT_TO_IN:
                return createRadiateBitmapRegionsOutIn(src, count);
            default:
                throw new EngineRuntimeException("BitmapRadiate not found!");
        }
    }

    private static Bitmap[] createRadiateBitmapRegionsLeftRight(Bitmap src, int count) {
        Bitmap[] bitmaps = new Bitmap[count];
        int width = src.getWidth();
        int height = src.getHeight();

        // Clip bitmap by size
        for (int i = 0; i < count; i++) {
            // Create an empty bitmap and canvas
            Bitmap bitmap = createBitmap(width, height);
            Canvas canvas = new Canvas(bitmap);

            // Clip bitmap progressively from left to right
            int clipWidth = width * (i + 1) / count;
            Bitmap clipBitmap = Bitmap.createBitmap(src, 0, 0, clipWidth, height);

            // Draw clipped bitmap on canvas
            canvas.drawBitmap(clipBitmap, null, new Rect(0, 0, clipWidth, height), null);
            bitmaps[i] = bitmap;
        }

        return bitmaps;
    }

    private static Bitmap[] createRadiateBitmapRegionsRightLeft(Bitmap src, int count) {
        Bitmap[] bitmaps = new Bitmap[count];
        int width = src.getWidth();
        int height = src.getHeight();

        // Clip bitmap by size
        for (int i = 0; i < count; i++) {
            // Create an empty bitmap and canvas
            Bitmap bitmap = createBitmap(width, height);
            Canvas canvas = new Canvas(bitmap);

            // Clip bitmap progressively from left to right
            int clipWidth = width * (i + 1) / count;
            Bitmap clipBitmap = Bitmap.createBitmap(src, width - clipWidth, 0, clipWidth, height);

            // Draw clipped bitmap on canvas
            canvas.drawBitmap(clipBitmap, null, new Rect(width - clipWidth, 0, width, height), null);
            bitmaps[i] = bitmap;
        }

        return bitmaps;
    }

    private static Bitmap[] createRadiateBitmapRegionsTopDown(Bitmap src, int count) {
        Bitmap[] bitmaps = new Bitmap[count];
        int width = src.getWidth();
        int height = src.getHeight();

        // Clip bitmap by size
        for (int i = 0; i < count; i++) {
            // Create an empty bitmap and canvas
            Bitmap bitmap = createBitmap(width, height);
            Canvas canvas = new Canvas(bitmap);

            // Clip bitmap progressively from top to down
            int clipHeight = height * (i + 1) / count;
            Bitmap clipBitmap = Bitmap.createBitmap(src, 0, 0, width, clipHeight);

            // Draw clipped bitmap on canvas
            canvas.drawBitmap(clipBitmap, null, new Rect(0, 0, width, clipHeight), null);
            bitmaps[i] = bitmap;
        }

        return bitmaps;
    }

    private static Bitmap[] createRadiateBitmapRegionsBottomUp(Bitmap src, int count) {
        Bitmap[] bitmaps = new Bitmap[count];
        int width = src.getWidth();
        int height = src.getHeight();

        // Clip bitmap by size
        for (int i = 0; i < count; i++) {
            // Create an empty bitmap and canvas
            Bitmap bitmap = createBitmap(width, height);
            Canvas canvas = new Canvas(bitmap);

            // Clip bitmap progressively from top to down
            int clipHeight = height * (i + 1) / count;
            Bitmap clipBitmap = Bitmap.createBitmap(src, 0, height - clipHeight, width, clipHeight);

            // Draw clipped bitmap on canvas
            canvas.drawBitmap(clipBitmap, null, new Rect(0, height - clipHeight, width, height), null);
            bitmaps[i] = bitmap;
        }

        return bitmaps;
    }

    private static Bitmap[] createRadiateBitmapRegionsInOut(Bitmap src, int count) {
        Bitmap[] bitmaps = new Bitmap[count];
        int width = src.getWidth();
        int height = src.getHeight();

        // Clip bitmap by size
        for (int i = 0; i < count; i++) {
            // Create an empty bitmap and canvas
            Bitmap bitmap = createBitmap(width, height);
            Canvas canvas = new Canvas(bitmap);

            // Clip bitmap progressively from inner to outer
            int clipWidth = width * (i + 1) / count;
            int clipHeight = height * (i + 1) / count;
            int startX = (width - clipWidth) / 2;
            int startY = (height - clipHeight) / 2;
            Bitmap clipBitmap = Bitmap.createBitmap(src, startX, startY, clipWidth, clipHeight);

            // Draw clipped bitmap on canvas
            canvas.drawBitmap(clipBitmap, null, new Rect(startX, startY,
                    startX + clipWidth, startY + clipHeight), null);
            bitmaps[i] = bitmap;
        }

        return bitmaps;
    }

    private static Bitmap[] createRadiateBitmapRegionsOutIn(Bitmap src, int count) {
        Bitmap[] bitmaps = new Bitmap[count];
        int width = src.getWidth();
        int height = src.getHeight();

        // Clip bitmap by size
        for (int i = 0; i < count; i++) {
            // Create an empty bitmap and canvas
            Bitmap bitmap = createBitmap(width, height);
            Canvas canvas = new Canvas(bitmap);

            // Clip bitmap progressively from inner to outer
            int clipWidth = width * (count - i) / count;
            int clipHeight = height * (count - i) / count;
            int startX = (width - clipWidth) / 2;
            int startY = (height - clipHeight) / 2;
            Bitmap clipBitmap = Bitmap.createBitmap(src, startX, startY, clipWidth, clipHeight);

            // Draw clipped bitmap on canvas
            canvas.drawBitmap(clipBitmap, null, new Rect(startX, startY,
                    startX + clipWidth, startY + clipHeight), null);
            bitmaps[i] = bitmap;
        }

        return bitmaps;
    }
    //========================================================

}
