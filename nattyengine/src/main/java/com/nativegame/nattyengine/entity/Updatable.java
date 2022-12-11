package com.nativegame.nattyengine.entity;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public interface Updatable {

    void update(long elapsedMillis);

    void addToGame();

    void removeFromGame();

    boolean isActive();

}
