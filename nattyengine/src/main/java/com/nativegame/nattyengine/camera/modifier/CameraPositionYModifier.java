package com.nativegame.nattyengine.camera.modifier;

import com.nativegame.nattyengine.camera.Camera;
import com.nativegame.nattyengine.util.modifier.tween.Tweener;

public class CameraPositionYModifier extends SingleValueCameraModifier {

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public CameraPositionYModifier(long duration) {
        super(0, 0, duration);
    }

    public CameraPositionYModifier(long duration, long startDelay) {
        super(0, 0, duration, startDelay);
    }

    public CameraPositionYModifier(long duration, Tweener tweener) {
        super(0, 0, duration, tweener);
    }

    public CameraPositionYModifier(long duration, long startDelay, Tweener tweener) {
        super(0, 0, duration, startDelay, tweener);
    }

    public CameraPositionYModifier(float startValue, float endValue, long duration) {
        super(startValue, endValue, duration);
    }

    public CameraPositionYModifier(float startValue, float endValue, long duration, long startDelay) {
        super(startValue, endValue, duration, startDelay);
    }

    public CameraPositionYModifier(float startValue, float endValue, long duration, Tweener tweener) {
        super(startValue, endValue, duration, tweener);
    }

    public CameraPositionYModifier(float startValue, float endValue, long duration, long startDelay, Tweener tweener) {
        super(startValue, endValue, duration, startDelay, tweener);
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    protected void onInitValue(Camera camera, float y) {
        camera.setY(y);
    }

    @Override
    protected void onUpdateValue(Camera camera, float y) {
        camera.setY(y);
    }

    @Override
    protected void onEndValue(Camera camera, float y) {
        camera.setY(y);
    }
    //========================================================

}
