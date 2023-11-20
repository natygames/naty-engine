package com.nativegame.nattyengine.util.math;

public class TransformUtils {

    private TransformUtils() {
    }

    public static float getScaleX(float x, float scalePivotX, float scaleX) {
        float distance = x - scalePivotX;
        float offset = (scaleX - 1) * distance;
        return x + offset;
    }

    public static float getScaleY(float y, float scalePivotY, float scaleY) {
        float distance = y - scalePivotY;
        float offset = (scaleY - 1) * distance;
        return y + offset;
    }

    public static float getRotateX(float x, float y, float rotationPivotX, float rotationPivotY, float angle) {
        // Calculate distance to pivot
        float angleInRadians = (float) Math.toRadians(angle);
        float distanceX = x - rotationPivotX;
        float distanceY = y - rotationPivotY;

        // Calculate new distance to pivot
        float rotateDistance = (float) (distanceX * Math.cos(angleInRadians) - distanceY * Math.sin(angleInRadians));

        return rotationPivotX + rotateDistance;
    }

    public static float getRotateY(float x, float y, float rotationPivotX, float rotationPivotY, float angle) {
        // Calculate distance to pivot
        float angleInRadians = (float) Math.toRadians(angle);
        float distanceX = x - rotationPivotX;
        float distanceY = y - rotationPivotY;

        // Calculate new distance to pivot
        float rotateDistance = (float) (distanceY * Math.cos(angleInRadians) + distanceX * Math.sin(angleInRadians));

        return rotationPivotY + rotateDistance;
    }

}
