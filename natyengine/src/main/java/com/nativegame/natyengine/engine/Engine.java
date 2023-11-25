package com.nativegame.natyengine.engine;

import android.graphics.Canvas;

import com.nativegame.natyengine.camera.Camera;
import com.nativegame.natyengine.engine.loop.DrawLoop;
import com.nativegame.natyengine.engine.loop.UpdateLoop;
import com.nativegame.natyengine.entity.Drawable;
import com.nativegame.natyengine.entity.Releasable;
import com.nativegame.natyengine.entity.Updatable;
import com.nativegame.natyengine.event.Event;
import com.nativegame.natyengine.event.EventListener;
import com.nativegame.natyengine.input.sensor.AccelerationController;
import com.nativegame.natyengine.input.sensor.OrientationController;
import com.nativegame.natyengine.input.touch.BoundTouchEventListener;
import com.nativegame.natyengine.input.touch.TouchController;
import com.nativegame.natyengine.input.touch.TouchEvent;
import com.nativegame.natyengine.input.touch.TouchEventListener;
import com.nativegame.natyengine.scene.Scene;
import com.nativegame.natyengine.scene.SceneController;
import com.nativegame.natyengine.ui.GameView;
import com.nativegame.natyengine.util.debug.Debugger;
import com.nativegame.natyengine.util.exception.EngineRuntimeException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class Engine implements UpdateLoop.UpdateListener, DrawLoop.DrawListener, GameView.OnDrawListener {

    private GameView mGameView;
    private Camera mCamera;
    private UpdateLoop mUpdateLoop;
    private DrawLoop mDrawLoop;
    private TouchController mTouchController;
    private AccelerationController mAccelerationController;
    private OrientationController mOrientationController;
    private boolean mIsDebugMode = false;

    private final SceneController mSceneController = new SceneController();
    private final LayerComparator mLayerComparator = new LayerComparator();
    private final Debugger mDebugger = new Debugger();
    private final List<Updatable> mUpdatables = new ArrayList<>();
    private final List<Drawable> mDrawables = new ArrayList<>();
    private final List<Updatable> mUpdatablesToAdd = new ArrayList<>();
    private final List<Updatable> mUpdatablesToRemove = new ArrayList<>();
    private final List<EngineListener> mListeners = new ArrayList<>();

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Engine() {
    }

    public Engine(GameView gameView) {
        setGameView(gameView);
    }
    //========================================================

    //--------------------------------------------------------
    // Getter and Setter
    //--------------------------------------------------------
    public GameView getGameView() {
        return mGameView;
    }

    public void setGameView(GameView gameView) {
        mGameView = gameView;
        mGameView.setListener(this);
    }

    public Camera getCamera() {
        return mCamera;
    }

    public void setCamera(Camera camera) {
        mCamera = camera;
    }

    public SceneController getSceneController() {
        return mSceneController;
    }

    public TouchController getTouchController() {
        return mTouchController;
    }

    public void setTouchController(TouchController touchController) {
        mTouchController = touchController;
    }

    public AccelerationController getAccelerationController() {
        return mAccelerationController;
    }

    public void setAccelerationController(AccelerationController accelerationController) {
        mAccelerationController = accelerationController;
    }

    public OrientationController getOrientationController() {
        return mOrientationController;
    }

    public void setOrientationController(OrientationController orientationController) {
        mOrientationController = orientationController;
    }

    public boolean isDebugMode() {
        return mIsDebugMode;
    }

    public void setDebugMode(boolean debugMode) {
        mIsDebugMode = debugMode;
    }

    public Debugger getDebugger() {
        return mDebugger;
    }

    public void setDebugger(Debugger debugger) {
        mDebugger.set(debugger);
    }

    public boolean isRunning() {
        return mUpdateLoop != null && mUpdateLoop.isRunning();
    }

    public boolean isPaused() {
        return mUpdateLoop != null && mUpdateLoop.isPaused();
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public void update(long elapsedMillis) {
        processInput();
        synchronized (mUpdatables) {
            int updatableCount = mUpdatables.size();
            for (int i = 0; i < updatableCount; i++) {
                Updatable u = mUpdatables.get(i);
                if (u.isActive() && u.isRunning()) {
                    u.update(elapsedMillis);
                }
            }
        }
        mCamera.update(elapsedMillis);
        int listenerCount = mListeners.size();
        for (int i = 0; i < listenerCount; i++) {
            EngineListener listener = mListeners.get(i);
            listener.onEngineUpdate(elapsedMillis);
        }
        synchronized (mDrawables) {
            while (!mUpdatablesToRemove.isEmpty()) {
                removeUpdatable(mUpdatablesToRemove.remove(0));
            }
            while (!mUpdatablesToAdd.isEmpty()) {
                addUpdatable(mUpdatablesToAdd.remove(0));
            }
        }
    }

    @Override
    public void draw() {
        if (mGameView != null) {
            mGameView.draw();
        }
    }

    @Override
    public void onDraw(Canvas canvas) {
        synchronized (mDrawables) {
            // Sort drawables by layer
            Collections.sort(mDrawables, mLayerComparator);
            int drawableCount = mDrawables.size();
            for (int i = 0; i < drawableCount; i++) {
                Drawable d = mDrawables.get(i);
                if (d.isVisible() && !d.isCulling(canvas, mCamera)) {
                    d.draw(canvas, mCamera);
                }
            }
        }
        int listenerCount = mListeners.size();
        for (int i = 0; i < listenerCount; i++) {
            EngineListener listener = mListeners.get(i);
            listener.onEngineDraw(canvas, mCamera);
        }
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    public void start() {
        if (mGameView == null) {
            throw new EngineRuntimeException("GameView not found!");
        }
        if (mCamera == null) {
            throw new EngineRuntimeException("Camera not found!");
        }

        mSceneController.start();

        // Start the input controller
        if (mTouchController != null) {
            mTouchController.start();
        }
        if (mAccelerationController != null) {
            mAccelerationController.start();
        }
        if (mOrientationController != null) {
            mOrientationController.start();
        }

        // Start the game loop
        mUpdateLoop = new UpdateLoop();
        mUpdateLoop.setListener(this);
        mDrawLoop = new DrawLoop();
        mDrawLoop.setListener(this);
        mUpdateLoop.startLoop();
        mDrawLoop.startLoop();
    }

    public void stop() {
        mSceneController.stop();
        if (mUpdateLoop != null) {
            mUpdateLoop.stopLoop();
            mUpdateLoop = null;
        }
        if (mDrawLoop != null) {
            mDrawLoop.stopLoop();
            mDrawLoop = null;
        }
        if (mTouchController != null) {
            mTouchController.stop();
            mTouchController = null;
        }
        if (mAccelerationController != null) {
            mAccelerationController.stop();
            mAccelerationController = null;
        }
        if (mOrientationController != null) {
            mOrientationController.stop();
            mOrientationController = null;
        }
    }

    public void pause() {
        mSceneController.pause();
        if (mUpdateLoop != null) {
            mUpdateLoop.pauseLoop();
        }
        if (mDrawLoop != null) {
            mDrawLoop.pauseLoop();
        }
        if (mTouchController != null) {
            mTouchController.pause();
        }
        if (mAccelerationController != null) {
            mAccelerationController.pause();
        }
        if (mOrientationController != null) {
            mOrientationController.pause();
        }
    }

    public void resume() {
        mSceneController.resume();
        if (mUpdateLoop != null) {
            mUpdateLoop.resumeLoop();
        }
        if (mDrawLoop != null) {
            mDrawLoop.resumeLoop();
        }
        if (mTouchController != null) {
            mTouchController.resume();
        }
        if (mAccelerationController != null) {
            mAccelerationController.resume();
        }
        if (mOrientationController != null) {
            mOrientationController.resume();
        }
    }

    public void release() {
        synchronized (mUpdatables) {
            synchronized (mDrawables) {
                int updatableCount = mUpdatables.size();
                for (int i = updatableCount - 1; i >= 0; i--) {
                    Updatable u = mUpdatables.get(i);
                    u.removeFromGame();
                    if (u instanceof Releasable) {
                        Releasable r = (Releasable) u;
                        if (!r.isRelease()) {
                            r.release();
                        }
                    }
                }
            }
        }
    }

    private void processInput() {
        if (mTouchController == null) {
            return;
        }
        List<TouchEvent> touchEvents = mTouchController.getTouchEvents();
        if (touchEvents.isEmpty()) {
            return;
        }
        // We notify all the listener
        int updatableCount = mUpdatables.size();
        for (int i = 0; i < updatableCount; i++) {
            Updatable u = mUpdatables.get(i);

            // Check is TouchEventListener
            if (u instanceof TouchEventListener) {
                TouchEventListener listener = ((TouchEventListener) u);
                // Consume TouchEvent
                int touchEventCount = touchEvents.size();
                for (int j = 0; j < touchEventCount; j++) {
                    TouchEvent event = touchEvents.get(j);
                    // Transform screen coordinate to world coordinate
                    listener.onTouchEvent(event.getType(),
                            mCamera.getScreenToWorldX(event.getTouchX(), listener.getCoordinateType()),
                            mCamera.getScreenToWorldY(event.getTouchY(), listener.getCoordinateType()));
                }
            }

            // Check is BoundTouchEventListener
            if (u instanceof BoundTouchEventListener) {
                BoundTouchEventListener listener = ((BoundTouchEventListener) u);
                // Consume TouchEvent
                int touchEventCount = touchEvents.size();
                for (int j = 0; j < touchEventCount; j++) {
                    TouchEvent event = touchEvents.get(j);
                    // Transform screen coordinate to world coordinate
                    float touchX = mCamera.getScreenToWorldX(event.getTouchX(), listener.getCoordinateType());
                    float touchY = mCamera.getScreenToWorldY(event.getTouchY(), listener.getCoordinateType());
                    // Check is in bounds
                    if (touchX > listener.getX() && touchX < listener.getEndX()
                            && touchY > listener.getY() && touchY < listener.getEndY()) {
                        listener.onBoundTouchEvent(event.getType(), touchX - listener.getX(),
                                touchY - listener.getY());
                    }
                }
            }
        }
    }

    private void addUpdatable(Updatable updatable) {
        mUpdatables.add(updatable);
        if (updatable instanceof Drawable) {
            mDrawables.add((Drawable) updatable);
        }
        int listenerCount = mListeners.size();
        for (int i = 0; i < listenerCount; i++) {
            EngineListener listener = mListeners.get(i);
            listener.onAddToEngine(updatable);
        }
    }

    private void removeUpdatable(Updatable updatable) {
        mUpdatables.remove(updatable);
        if (updatable instanceof Drawable) {
            mDrawables.remove((Drawable) updatable);
        }
        int listenerCount = mListeners.size();
        for (int i = 0; i < listenerCount; i++) {
            EngineListener listener = mListeners.get(i);
            listener.onRemoveFromEngine(updatable);
        }
    }

    public void addToEngine(Updatable updatable) {
        if (updatable.isRunning()) {
            throw new EngineRuntimeException("'" + updatable.getName() + "' is already in the engine!");
        }
        updatable.setRunning(true);
        // Add to buffer if engine is running
        if (isRunning()) {
            mUpdatablesToAdd.add(updatable);
        } else {
            addUpdatable(updatable);
        }
    }

    public void removeFromEngine(Updatable updatable) {
        if (!updatable.isRunning()) {
            throw new EngineRuntimeException("'" + updatable.getName() + "' is not in the engine!");
        }
        updatable.setRunning(false);
        // Add to buffer if engine is running
        if (isRunning()) {
            if (mUpdatablesToAdd.contains(updatable)) {
                mUpdatablesToAdd.remove(updatable);
            } else {
                mUpdatablesToRemove.add(updatable);
            }
        } else {
            removeUpdatable(updatable);
        }
    }

    public void addToScene(Updatable updatable) {
        addToEngine(updatable);
        Scene scene = mSceneController.getCurrentScene();
        if (scene == null) {
            throw new EngineRuntimeException("Scene not found!");
        }
        if (scene.getAllChild().contains(updatable)) {
            throw new EngineRuntimeException("'" + updatable.getName()
                    + "' is already in the scene '" + scene.getName() + "'!");
        }
        scene.addToScene(updatable);
    }

    public void removeFromScene(Updatable updatable) {
        removeFromEngine(updatable);
        Scene scene = mSceneController.getCurrentScene();
        if (scene == null) {
            throw new EngineRuntimeException("Scene not found!");
        }
        if (!scene.getAllChild().contains(updatable)) {
            throw new EngineRuntimeException("'" + updatable.getName()
                    + "' is not in the scene '" + scene.getName() + "'!");
        }
        scene.removeFromScene(updatable);
    }

    public void addListener(EngineListener listener) {
        mListeners.add(listener);
    }

    public void removeListener(EngineListener listener) {
        mListeners.remove(listener);
    }

    public void clearListener() {
        mListeners.clear();
    }

    public void dispatchEvent(Event event) {
        synchronized (mDrawables) {
            // We notify all the EventListener
            int updatableCount = mUpdatables.size();
            for (int i = 0; i < updatableCount; i++) {
                Updatable u = mUpdatables.get(i);
                if (u instanceof EventListener) {
                    EventListener listener = ((EventListener) u);
                    listener.onEvent(event);
                }
            }
        }
    }
    //========================================================

    //--------------------------------------------------------
    // Inner Classes
    //--------------------------------------------------------
    private static class LayerComparator implements Comparator<Drawable> {

        @Override
        public int compare(Drawable drawableA, Drawable drawableB) {
            return Integer.compare(drawableA.getLayer(), drawableB.getLayer());
        }

    }
    //========================================================

}
