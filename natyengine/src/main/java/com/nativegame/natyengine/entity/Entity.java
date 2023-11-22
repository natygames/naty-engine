package com.nativegame.natyengine.entity;

import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.event.Event;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public abstract class Entity implements Updatable, Releasable {

    protected final Engine mEngine;

    private boolean mIsRunning = false;
    private boolean mIsActive = true;
    private boolean mIsRelease = false;

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
        mEngine.addToEngine(this);
        onStart();
    }

    @Override
    public void removeFromGame() {
        mEngine.removeFromEngine(this);
        onRemove();
    }

    @Override
    public void addToScene() {
        mEngine.addToScene(this);
        onStart();
    }

    @Override
    public void removeFromScene() {
        mEngine.removeFromScene(this);
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
    public void release() {
        mIsRelease = true;
        onRelease();
    }

    @Override
    public boolean isRelease() {
        return mIsRelease;
    }

    @Override
    public String getName() {
        return getClass().getSimpleName();
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    protected void onStart() {
    }

    protected void onRemove() {
    }

    protected void onRelease() {
    }

    protected void onPreUpdate(long elapsedMillis) {
    }

    protected void onUpdate(long elapsedMillis) {
    }

    protected void onPostUpdate(long elapsedMillis) {
    }

    public void dispatchEvent(Event event) {
        mEngine.dispatchEvent(event);
    }
    //========================================================

}
