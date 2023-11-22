package com.nativegame.natyengine.engine;

import android.graphics.Canvas;

import com.nativegame.natyengine.camera.Camera;
import com.nativegame.natyengine.entity.Updatable;

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
