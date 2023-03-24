package com.nativegame.nattyengine.engine;

import android.graphics.Canvas;

import com.nativegame.nattyengine.engine.camera.Camera;
import com.nativegame.nattyengine.engine.camera.CameraListener;
import com.nativegame.nattyengine.engine.collision.Collidable;
import com.nativegame.nattyengine.engine.collision.CollisionType;
import com.nativegame.nattyengine.engine.collision.algorithm.QuadTree;
import com.nativegame.nattyengine.engine.event.Event;
import com.nativegame.nattyengine.engine.event.EventListener;
import com.nativegame.nattyengine.engine.loop.DrawLoop;
import com.nativegame.nattyengine.engine.loop.UpdateLoop;
import com.nativegame.nattyengine.input.sensor.AccelerationController;
import com.nativegame.nattyengine.input.sensor.OrientationController;
import com.nativegame.nattyengine.input.touch.SingleTouchController;
import com.nativegame.nattyengine.input.touch.TouchController;
import com.nativegame.nattyengine.input.touch.TouchEvent;
import com.nativegame.nattyengine.input.touch.TouchEventListener;
import com.nativegame.nattyengine.ui.GameView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class Engine implements UpdateLoop.UpdateListener, DrawLoop.DrawListener, GameView.OnDrawListener {

    private final GameView mGameView;

    private UpdateLoop mUpdateLoop;
    private DrawLoop mDrawLoop;
    private Camera mCamera;
    private TouchController mTouchController;
    private AccelerationController mAccelerationController;
    private OrientationController mOrientationController;

    private final QuadTree mQuadTree = new QuadTree();
    private final List<Updatable> mUpdatables = new ArrayList<>();
    private final List<Drawable> mDrawables = new ArrayList<>();
    private final List<Disposable> mDisposable = new ArrayList<>();
    private final List<Updatable> mUpdatablesToAdd = new ArrayList<>();
    private final List<Updatable> mUpdatablesToRemove = new ArrayList<>();
    private final LayerComparator mLayerComparator = new LayerComparator();

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Engine(GameView gameView) {
        mGameView = gameView;
        mGameView.setListener(this);
        // Init the collision area of QuadTree
        mQuadTree.init(gameView.getWidth(), gameView.getHeight());
    }
    //========================================================

    //--------------------------------------------------------
    // Getter and Setter
    //--------------------------------------------------------
    public Camera getCamera() {
        return mCamera;
    }

    public void setCamera(Camera camera) {
        mCamera = camera;
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
        int size = mUpdatables.size();
        for (int i = 0; i < size; i++) {
            Updatable u = mUpdatables.get(i);
            if (u.isActive() && u.isRunning()) {
                u.update(elapsedMillis);
            }
        }
        focusCamera();
        checkCollision();
        synchronized (mDrawables) {
            while (!mUpdatablesToRemove.isEmpty()) {
                removeFromEngine(mUpdatablesToRemove.remove(0));
            }
            while (!mUpdatablesToAdd.isEmpty()) {
                addToEngine(mUpdatablesToAdd.remove(0));
            }
        }
    }

    @Override
    public void draw() {
        mGameView.draw();
    }

    @Override
    public void onDraw(Canvas canvas) {
        synchronized (mDrawables) {
            // Sort drawables by layer
            Collections.sort(mDrawables, mLayerComparator);
            int size = mDrawables.size();
            for (int i = 0; i < size; i++) {
                Drawable d = mDrawables.get(i);
                if (d.isVisible() && !d.isCulling(mCamera)) {
                    d.draw(canvas, mCamera);
                }
            }
        }
    }

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    public void startGame() {
        // Stop the engine if it is running
        stopGame();

        // Init the default Camera
        if (mCamera == null) {
            mCamera = new Camera(mGameView, mGameView.getWidth(), mGameView.getHeight(),
                    mGameView.getWidth(), mGameView.getHeight());
        }

        // Init the default TouchController
        if (mTouchController == null) {
            mTouchController = new SingleTouchController(mGameView);
        }

        // Start sensor
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

    public void stopGame() {
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
        }
        if (mAccelerationController != null) {
            mAccelerationController.stop();
        }
        if (mOrientationController != null) {
            mOrientationController.stop();
        }
        int size = mDisposable.size();
        for (int i = 0; i < size; i++) {
            Disposable d = mDisposable.get(i);
            if (!d.isDisposed()) {
                d.dispose();
            }
        }
    }

    public void pauseGame() {
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

    public void resumeGame() {
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

    private void processInput() {
        List<TouchEvent> touchEvents = mTouchController.getTouchEvents();
        if (touchEvents.isEmpty()) {
            return;
        }
        // We notify all the TouchEventListener
        int size = mUpdatables.size();
        for (int i = 0; i < size; i++) {
            Updatable u = mUpdatables.get(i);
            if (u instanceof TouchEventListener) {
                TouchEventListener listener = ((TouchEventListener) u);
                // Consume TouchEvent
                int sizeEvent = touchEvents.size();
                for (int j = 0; j < sizeEvent; j++) {
                    TouchEvent event = touchEvents.get(j);
                    // Transform screen coordinate to world coordinate
                    listener.onTouchEvent(event.getType(),
                            mCamera.getScreenToWorldX(event.getTouchX()),
                            mCamera.getScreenToWorldY(event.getTouchY()));
                }
            }
        }
    }

    private void focusCamera() {
        // Check is camera has focus
        if (!mCamera.isFocus()) {
            return;
        }
        float offsetX = mCamera.getOffsetX();
        float offsetY = mCamera.getOffsetY();

        int size = mUpdatables.size();
        for (int i = 0; i < size; i++) {
            Updatable u = mUpdatables.get(i);
            if (u instanceof CameraListener) {
                CameraListener listener = ((CameraListener) u);
                listener.setCameraOffset(offsetX, offsetY);
            }
        }
    }

    private void checkCollision() {
        mQuadTree.checkCollision(this);
    }

    private void addToEngine(Updatable updatable) {
        mUpdatables.add(updatable);
        if (updatable instanceof Drawable) {
            mDrawables.add((Drawable) updatable);
        }
        if (updatable instanceof Disposable) {
            mDisposable.add((Disposable) updatable);
        }
        if (updatable instanceof Collidable) {
            Collidable c = (Collidable) updatable;
            if (c.getCollisionType() != CollisionType.NONE) {
                mQuadTree.addCollidable(c);
            }
        }
    }

    private void removeFromEngine(Updatable updatable) {
        mUpdatables.remove(updatable);
        if (updatable instanceof Drawable) {
            mDrawables.remove((Drawable) updatable);
        }
        if (updatable instanceof Disposable) {
            mDisposable.remove((Disposable) updatable);
        }
        if (updatable instanceof Collidable) {
            mQuadTree.removeCollidable((Collidable) updatable);
        }
    }

    public void addUpdatable(Updatable updatable) {
        if (updatable.isRunning()) {
            throw new IllegalStateException("'" + updatable.getClass().getSimpleName() + "' is already in the engine!");
        }
        updatable.setRunning(true);
        // Add to waiting pool if engine running
        if (isRunning()) {
            mUpdatablesToAdd.add(updatable);
        } else {
            addToEngine(updatable);
        }
    }

    public void removeUpdatable(Updatable updatable) {
        if (!updatable.isRunning()) {
            throw new IllegalStateException("'" + updatable.getClass().getSimpleName() + "' is not in the engine!");
        }
        updatable.setRunning(false);
        // Add to waiting pool if engine running
        if (isRunning()) {
            if (mUpdatablesToAdd.contains(updatable)) {
                mUpdatablesToAdd.remove(updatable);
            } else {
                mUpdatablesToRemove.add(updatable);
            }
        } else {
            removeFromEngine(updatable);
        }
    }

    public void dispatchEvent(Event event) {
        synchronized (mDrawables) {
            // We notify all the EventListener
            int size = mUpdatables.size();
            for (int i = 0; i < size; i++) {
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
