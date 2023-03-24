package com.nativegame.nattyengine.entity;

import com.nativegame.nattyengine.engine.Disposable;
import com.nativegame.nattyengine.engine.Engine;
import com.nativegame.nattyengine.engine.Updatable;
import com.nativegame.nattyengine.engine.event.Event;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public abstract class Entity implements Updatable, Disposable {

    protected final Engine mEngine;

    private boolean mIsRunning = false;
    private boolean mIsActive = true;
    private boolean mIsDisposed = false;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    protected Entity(Engine engine) {
        mEngine = engine;
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public boolean isRunning() {
        return mIsRunning;
    }

    @Override
    public void setRunning(boolean running) {
        mIsRunning = running;
    }

    @Override
    public boolean isActive() {
        return mIsActive;
    }

    @Override
    public void setActive(boolean active) {
        mIsActive = active;
    }

    @Override
    public void addToGame() {
        mEngine.addUpdatable(this);
        onStart();
    }

    @Override
    public void removeFromGame() {
        mEngine.removeUpdatable(this);
        onRemove();
    }

    @Override
    public void update(long elapsedMillis) {
        onPreUpdate(elapsedMillis);
        onUpdate(elapsedMillis);
        onPostUpdate(elapsedMillis);
    }

    @Override
    public void reset() {
        mIsActive = true;
    }

    @Override
    public void dispose() {
        mIsDisposed = true;
    }

    @Override
    public boolean isDisposed() {
        return mIsDisposed;
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    public void onStart() {
    }

    public void onRemove() {
    }

    public void onPreUpdate(long elapsedMillis) {
    }

    public void onUpdate(long elapsedMillis) {
    }

    public void onPostUpdate(long elapsedMillis) {
    }

    public void dispatchEvent(Event event) {
        mEngine.dispatchEvent(event);
    }
    //========================================================

}
