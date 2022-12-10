package com.nativegame.nattyengine.engine;

import com.nativegame.nattyengine.GameView;
import com.nativegame.nattyengine.collision.Collidable;
import com.nativegame.nattyengine.collision.algorithm.QuadTree;
import com.nativegame.nattyengine.entity.Drawable;
import com.nativegame.nattyengine.entity.Updatable;
import com.nativegame.nattyengine.event.GameEvent;
import com.nativegame.nattyengine.event.GameEventListener;

import java.util.ArrayList;
import java.util.List;

public class GameEngine {

    private final GameView mGameView;
    private final QuadTree mQuadTree = new QuadTree();

    private final List<Updatable> mUpdatables = new ArrayList<>();
    private final List<Drawable> mDrawables = new ArrayList<>();
    private final List<Updatable> mAddWaitingPool = new ArrayList<>();
    private final List<Updatable> mRemoveWaitingPool = new ArrayList<>();

    private UpdateLoop mUpdateLoop;
    private DrawLoop mDrawLoop;

    public GameEngine(GameView gameView) {
        mGameView = gameView;
        mGameView.setDrawables(mDrawables);
        // Init the collision area of quadtree
        mQuadTree.init(gameView.getWidth(), gameView.getHeight());
    }

    //--------------------------------------------------------
    // Methods to change state of engine
    // start, stop, pause, resume
    //--------------------------------------------------------
    public void startGame() {
        // Stop a game if it is running
        stopGame();

        // Start the update loop
        mUpdateLoop = new UpdateLoop(this);
        mUpdateLoop.startLoop();

        // Start the drawing loop
        mDrawLoop = new DrawLoop(this);
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
    }

    public void pauseGame() {
        if (mUpdateLoop != null) {
            mUpdateLoop.pauseLoop();
        }
        if (mDrawLoop != null) {
            mDrawLoop.pauseLoop();
        }
    }

    public void resumeGame() {
        if (mUpdateLoop != null) {
            mUpdateLoop.resumeLoop();
        }
        if (mDrawLoop != null) {
            mDrawLoop.resumeLoop();
        }
    }

    public boolean isRunning() {
        return mUpdateLoop != null && mUpdateLoop.isRunning();
    }

    public boolean isPaused() {
        return mUpdateLoop != null && mUpdateLoop.isPaused();
    }
    //========================================================

    public void update(long elapsedMillis) {
        int size = mUpdatables.size();
        for (int i = 0; i < size; i++) {
            Updatable u = mUpdatables.get(i);
            if (u.isActive()) {
                u.update(elapsedMillis);
            }
        }
        checkCollision();
        synchronized (mDrawables) {
            while (!mRemoveWaitingPool.isEmpty()) {
                removeFromEngine(mRemoveWaitingPool.remove(0));
            }
            while (!mAddWaitingPool.isEmpty()) {
                addToEngine(mAddWaitingPool.remove(0));
            }
        }
    }

    public void draw() {
        mGameView.draw();
    }

    private void checkCollision() {
        mQuadTree.checkCollision(this);
    }

    private void addToEngine(Updatable updatable) {
        mUpdatables.add(updatable);
        if (updatable instanceof Drawable) {
            mDrawables.add((Drawable) updatable);
        }
        if (updatable instanceof Collidable) {
            mQuadTree.addCollidableObject((Collidable) updatable);
        }
    }

    private void removeFromEngine(Updatable updatable) {
        if (mUpdatables.remove(updatable)) {
            if (updatable instanceof Drawable) {
                mDrawables.remove((Drawable) updatable);
            }
            if (updatable instanceof Collidable) {
                mQuadTree.removeCollidableObject((Collidable) updatable);
            }
        }
    }

    public void addUpdatable(final Updatable updatable) {
        if (isRunning()) {
            mAddWaitingPool.add(updatable);
        } else {
            addToEngine(updatable);
        }
    }

    public void removeUpdatable(final Updatable updatable) {
        mRemoveWaitingPool.add(updatable);
    }

    public void onGameEvent(GameEvent gameEvent) {
        synchronized (mDrawables) {
            // We notify all the EventListener
            int size = mUpdatables.size();
            for (int i = 0; i < size; i++) {
                Updatable u = mUpdatables.get(i);
                if (u instanceof GameEventListener) {
                    GameEventListener eventListener = ((GameEventListener) u);
                    eventListener.onGameEvent(gameEvent);
                }
            }
        }
    }

}
