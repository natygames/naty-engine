package com.nativegame.nattyengine.collision.algorithm;

import android.graphics.Rect;

import com.nativegame.nattyengine.collision.Collidable;
import com.nativegame.nattyengine.engine.GameEngine;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class QuadTree {

    private static final int MAX_TREE_NODE = 12;

    private final QuadTreeNode mRoot;

    private final List<QuadTreeNode> mTreeNodePool = new ArrayList<>();
    private final List<Collision> mDetectedCollisions = new ArrayList<>();

    public QuadTree() {
        mRoot = new QuadTreeNode(this);
        // We add them to the pool now
        mTreeNodePool.clear();
        for (int i = 0; i < MAX_TREE_NODE; i++) {
            mTreeNodePool.add(new QuadTreeNode(this));
        }
    }

    public void init(int width, int height) {
        mRoot.setArea(new Rect(0, 0, width, height));
    }

    public void checkCollision(GameEngine gameEngine) {
        // Clear the collisions from the previous cycle
        while (!mDetectedCollisions.isEmpty()) {
            Collision.returnToPool(mDetectedCollisions.remove(0));
        }
        mRoot.checkCollision(gameEngine, mDetectedCollisions);
    }

    public void addCollidableObject(Collidable objectToAdd) {
        mRoot.mCollidables.add(objectToAdd);
    }

    public void removeCollidableObject(Collidable objectToRemove) {
        mRoot.mCollidables.remove(objectToRemove);
    }

    public int getPoolSize() {
        return mTreeNodePool.size();
    }

    public QuadTreeNode getNode() {
        return mTreeNodePool.remove(0);
    }

    public void returnToPool(QuadTreeNode treeNode) {
        mTreeNodePool.add(treeNode);
    }

}
