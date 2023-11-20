package com.nativegame.nattyengine.scene;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class SceneController {

    private Scene mCurrentScene;

    private final Map<String, Scene> mScenes = new HashMap<>();

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public SceneController() {
    }

    public SceneController(Scene scene) {
        mCurrentScene = scene;
        addScene(scene);
    }
    //========================================================

    //--------------------------------------------------------
    // Getter and Setter
    //--------------------------------------------------------
    public void setCurrentScene(String sceneName) {
        mCurrentScene = mScenes.get(sceneName);
    }

    public void setCurrentScene(Scene scene) {
        mCurrentScene = scene;
    }

    public Scene getCurrentScene() {
        return mCurrentScene;
    }

    public Scene getScene(String sceneName) {
        return mScenes.get(sceneName);
    }

    public Map<String, Scene> getAllScenes() {
        return mScenes;
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    public void start() {
        if (mCurrentScene != null) {
            mCurrentScene.startScene();
        }
    }

    public void stop() {
        // Clear the map and all child Scene
        for (Scene child : mScenes.values()) {
            child.getAllChild().clear();
        }
        mScenes.clear();
        // Stop the current Scene
        if (mCurrentScene != null) {
            mCurrentScene.stopScene();
            mCurrentScene = null;
        }
    }

    public void pause() {
        if (mCurrentScene != null) {
            mCurrentScene.pauseScene();
        }
    }

    public void resume() {
        if (mCurrentScene != null) {
            mCurrentScene.resumeScene();
        }
    }

    public void addScene(Scene scene) {
        mScenes.put(scene.getName(), scene);
    }

    public void addScene(Scene scene, String sceneName) {
        mScenes.put(sceneName, scene);
    }

    public void removeScene(Scene scene) {
        mScenes.remove(scene.getName());
    }

    public void removeScene(String sceneName) {
        mScenes.remove(sceneName);
    }

    public void clearScene() {
        mScenes.clear();
    }

    public void changeCurrentScene(Scene nextScene) {
        if (mCurrentScene != null) {
            mCurrentScene.stopScene();
        }
        mCurrentScene = nextScene;
        mCurrentScene.startScene();
    }

    public void changeCurrentScene(String nextSceneName) {
        Scene nextScene = mScenes.get(nextSceneName);
        changeCurrentScene(nextScene);
    }
    //========================================================

}
