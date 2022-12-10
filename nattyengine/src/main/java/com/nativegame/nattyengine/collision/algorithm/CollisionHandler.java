package com.nativegame.nattyengine.collision.algorithm;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Rect;

import com.nativegame.nattyengine.collision.Collidable;
import com.nativegame.nattyengine.collision.CollisionType;

public class CollisionHandler {

    private CollisionHandler() {
    }

    public static boolean isCollisionsDetected(Collidable collidableA, Collidable collidableB) {
        // We skip the passive collision type
        if (collidableA.getCollisionType() == CollisionType.PASSIVE
                && collidableB.getCollisionType() == CollisionType.PASSIVE) {
            return false;
        }

        // Get the CollisionBox bounds
        Rect boundsA = collidableA.getCollisionShape().getCollisionBounds();
        Rect boundsB = collidableB.getCollisionShape().getCollisionBounds();

        // Check is two bounds intersect
        if (Rect.intersects(boundsA, boundsB)) {

            // Get the collision bounds
            Rect collisionBounds = getCollisionBounds(boundsA, boundsB);
            for (int i = collisionBounds.left; i < collisionBounds.right; i += 10) {
                for (int j = collisionBounds.top; j < collisionBounds.bottom; j += 10) {

                    // Get the pixel color from bitmap
                    int collidableAPixel = getBitmapPixel(collidableA.getCollisionShape().getCollisionBitmap(),
                            (int) ((i - boundsA.left)),
                            (int) ((j - boundsA.top)));
                    int collidableBPixel = getBitmapPixel(collidableB.getCollisionShape().getCollisionBitmap(),
                            (int) ((i - boundsB.left)),
                            (int) ((j - boundsB.top)));
                    if (isFilled(collidableAPixel) && isFilled(collidableBPixel)) {
                        // Collision detected if both pixel colors are filled
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private static Rect getCollisionBounds(Rect rectA, Rect rectB) {
        int left = Math.max(rectA.left, rectB.left);
        int top = Math.max(rectA.top, rectB.top);
        int right = Math.min(rectA.right, rectB.right);
        int bottom = Math.min(rectA.bottom, rectB.bottom);
        return new Rect(left, top, right, bottom);
    }

    private static int getBitmapPixel(Bitmap bitmap, int i, int j) {
        // We make sure the value >= 0
        if (i < 0) {
            i = 0;
        }
        if (j < 0) {
            j = 0;
        }

        // We make sure the value < bitmap width
        int maxX = bitmap.getWidth() - 1;
        if (i > maxX) {
            i = maxX;
        }
        int maxY = bitmap.getHeight() - 1;
        if (j > maxY) {
            j = maxY;
        }

        return bitmap.getPixel(i, j);
    }

    private static boolean isFilled(int pixel) {
        return pixel != Color.TRANSPARENT;
    }

}
