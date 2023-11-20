package com.nativegame.nattyengine.engine;

import android.graphics.Canvas;

import com.nativegame.nattyengine.camera.Camera;
import com.nativegame.nattyengine.entity.Updatable;

public interface EngineListener {

    default void onEngineUpdate(long elapsedMillis) {
    }

    default void onEngineDraw(Canvas canvas, Camera camera) {
    }

    default void onAddToEngine(Updatable updatable) {
    }

    default void onRemoveFromEngine(Updatable updatable) {
    }

}
