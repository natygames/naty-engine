package com.nativegame.natyengine.camera.modifier;

import com.nativegame.natyengine.camera.Camera;
import com.nativegame.natyengine.util.modifier.tween.Tweener;

public class CameraPositionXModifier extends SingleValueCameraModifier {

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public CameraPositionXModifier(long duration) {
        super(0, 0, duration);
    }

    public CameraPositionXModifier(long duration, long startDelay) {
        super(0, 0, duration, startDelay);
    }

    public CameraPositionXModifier(long duration, Tweener tweener) {
        super(0, 0, duration, tweener);
    }

    public CameraPositionXModifier(long duration, long startDelay, Tweener tweener) {
        super(0, 0, duration, startDelay, tweener);
    }

    public CameraPositionXModifier(float startValue, float endValue, long duration) {
        super(startValue, endValue, duration);
    }

    public CameraPositionXModifier(float startValue, float endValue, long duration, long startDelay) {
        super(startValue, endValue, duration, startDelay);
    }

    public CameraPositionXModifier(float startValue, float endValue, long duration, Tweener tweener) {
        super(startValue, endValue, duration, tweener);
    }

    public CameraPositionXModifier(float startValue, float endValue, long duration, long startDelay, Tweener tweener) {
        super(startValue, endValue, duration, startDelay, tweener);
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    protected void onInitValue(Camera camera, float x) {
        camera.setX(x);
    }

    @Override
    protected void onUpdateValue(Camera camera, float x) {
        camera.setX(x);
    }

    @Override
    protected void onEndValue(Camera camera, float x) {
        camera.setX(x);
    }
    //========================================================

}
