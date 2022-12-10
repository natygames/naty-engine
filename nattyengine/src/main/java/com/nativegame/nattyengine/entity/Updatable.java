package com.nativegame.nattyengine.entity;

public interface Updatable {

    void update(long elapsedMillis);

    void addToGame();

    void removeFromGame();

    boolean isActive();

}
