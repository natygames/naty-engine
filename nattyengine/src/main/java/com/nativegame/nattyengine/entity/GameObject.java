package com.nativegame.nattyengine.entity;

import com.nativegame.nattyengine.Game;
import com.nativegame.nattyengine.event.GameEvent;
import com.nativegame.nattyengine.event.GameEventListener;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public abstract class GameObject implements Updatable, GameEventListener {

    protected final Game mGame;

    public boolean mIsActive = false;

    protected GameObject(Game game) {
        mGame = game;
    }

    @Override
    public void update(long elapsedMillis) {
        onPreUpdate(elapsedMillis);
        onUpdate(elapsedMillis);
        onPostUpdate(elapsedMillis);
    }

    @Override
    public void addToGame() {
        mIsActive = true;
        mGame.getGameEngine().addUpdatable(this);
        onStart();
    }

    @Override
    public void removeFromGame() {
        mIsActive = false;
        mGame.getGameEngine().removeUpdatable(this);
        onRemove();
    }

    @Override
    public boolean isActive() {
        return mIsActive;
    }

    public abstract void onUpdate(long elapsedMillis);

    public void onPreUpdate(long elapsedMillis) {
    }

    public void onPostUpdate(long elapsedMillis) {
    }

    public void onStart() {
    }

    public void onRemove() {
    }

    @Override
    public void gameEvent(GameEvent gameEvent) {
        mGame.getGameEngine().onGameEvent(gameEvent);
    }

    @Override
    public void onGameEvent(GameEvent gameEvent) {
    }

}
