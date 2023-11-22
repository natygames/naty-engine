package com.nativegame.natyengine.camera.modifier;

import com.nativegame.natyengine.camera.Camera;
import com.nativegame.natyengine.util.modifier.tween.Tweener;

public class CameraPositionModifier extends DoubleValueCameraModifier {

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public CameraPositionModifier(long duration) {
        super(0, 0, 0, 0, duration);
    }

    public CameraPositionModifier(long duration, long startDelay) {
        super(0, 0, 0, 0, duration, startDelay);
    }

    public CameraPositionModifier(long duration, Tweener tweener) {
        super(0, 0, 0, 0, duration, tweener);
    }

    public CameraPositionModifier(long duration, long startDelay, Tweener tweener) {
        super(0, 0, 0, 0, duration, startDelay, tweener);
    }

    public CameraPositionModifier(float startValueX, float endValueX, float startValueY, float endValueY, long duration) {
        super(startValueX, endValueX, startValueY, endValueY, duration);
    }

    public CameraPositionModifier(float startValueX, float endValueX, float startValueY, float endValueY, long duration, long startDelay) {
        super(startValueX, endValueX, startValueY, endValueY, duration, startDelay);
    }

    public CameraPositionModifier(float startValueX, float endValueX, float startValueY, float endValueY, long duration, Tweener tweener) {
        super(startValueX, endValueX, startValueY, endValueY, duration, tweener);
    }

    public CameraPositionModifier(float startValueX, float endValueX, float startValueY, float endValueY, long duration, long startDelay, Tweener tweener) {
        super(startValueX, endValueX, startValueY, endValueY, duration, startDelay, tweener);
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    protected void onInitValue(Camera camera, float x, float y) {
        camera.setX(x);
        camera.setY(y);
    }

    @Override
    protected void onUpdateValue(Camera camera, float x, float y) {
        camera.setX(x);
        camera.setY(y);
    }

    @Override
    protected void onEndValue(Camera camera, float x, float y) {
        camera.setX(x);
        camera.setY(y);
    }
    //========================================================

}
