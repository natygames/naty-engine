package com.nativegame.natyengine.scene;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class MultiScene extends BaseScene {

    private final List<Scene> mChildScenes = new ArrayList<>();

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public MultiScene() {
        super();
    }
    //========================================================

    //--------------------------------------------------------
    // Getter and Setter
    //--------------------------------------------------------
    public List<Scene> getAllChildScenes() {
        return mChildScenes;
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public void startScene() {
        super.startScene();
        int childCount = mChildScenes.size();
        for (int i = 0; i < childCount; i++) {
            Scene child = mChildScenes.get(i);
            child.startScene();
        }
    }

    @Override
    public void stopScene() {
        super.stopScene();
        int childCount = mChildScenes.size();
        for (int i = 0; i < childCount; i++) {
            Scene child = mChildScenes.get(i);
            child.stopScene();
        }
        mChildScenes.clear();
    }

    @Override
    public void pauseScene() {
        super.pauseScene();
        int childCount = mChildScenes.size();
        for (int i = 0; i < childCount; i++) {
            Scene child = mChildScenes.get(i);
            child.pauseScene();
        }
    }

    @Override
    public void resumeScene() {
        super.resumeScene();
        int childCount = mChildScenes.size();
        for (int i = 0; i < childCount; i++) {
            Scene child = mChildScenes.get(i);
            child.resumeScene();
        }
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    public void addChildScene(Scene scene) {
        mChildScenes.add(scene);
    }

    public void removeChildScene(Scene scene) {
        mChildScenes.remove(scene);
    }

    public void clearChildScene() {
        mChildScenes.clear();
    }
    //========================================================

}
