package com.nativegame.nattyengine.camera.modifier;

import com.nativegame.nattyengine.camera.Camera;
import com.nativegame.nattyengine.util.modifier.tween.Tweener;

public class CameraZoomModifier extends SingleValueCameraModifier {

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public CameraZoomModifier(long duration) {
        super(0, 0, duration);
    }

    public CameraZoomModifier(long duration, long startDelay) {
        super(0, 0, duration, startDelay);
    }

    public CameraZoomModifier(long duration, Tweener tweener) {
        super(0, 0, duration, tweener);
    }

    public CameraZoomModifier(long duration, long startDelay, Tweener tweener) {
        super(0, 0, duration, startDelay, tweener);
    }

    public CameraZoomModifier(float startValue, float endValue, long duration) {
        super(startValue, endValue, duration);
    }

    public CameraZoomModifier(float startValue, float endValue, long duration, long startDelay) {
        super(startValue, endValue, duration, startDelay);
    }

    public CameraZoomModifier(float startValue, float endValue, long duration, Tweener tweener) {
        super(startValue, endValue, duration, tweener);
    }

    public CameraZoomModifier(float startValue, float endValue, long duration, long startDelay, Tweener tweener) {
        super(startValue, endValue, duration, startDelay, tweener);
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    protected void onInitValue(Camera camera, float zoom) {
        camera.setZoom(zoom);
    }

    @Override
    protected void onUpdateValue(Camera camera, float zoom) {
        camera.setZoom(zoom);
    }

    @Override
    protected void onEndValue(Camera camera, float zoom) {
        camera.setZoom(zoom);
    }
    //========================================================

}
