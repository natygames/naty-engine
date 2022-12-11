package com.nativegame.nattyengine.event;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public interface GameEventListener {

    void onGameEvent(GameEvent event);

    void gameEvent(GameEvent event);

}
