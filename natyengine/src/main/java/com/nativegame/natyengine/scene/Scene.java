package com.nativegame.natyengine.scene;

import com.nativegame.natyengine.entity.Updatable;

import java.util.List;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public interface Scene {

    List<Updatable> getAllChild();

    String getName();

    void addToScene(Updatable updatable);

    void removeFromScene(Updatable updatable);

    void startScene();

    void stopScene();

    void pauseScene();

    void resumeScene();

}
