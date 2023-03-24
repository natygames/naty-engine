package com.nativegame.nattyengine.engine.collision.algorithm;

import android.graphics.Rect;

import com.nativegame.nattyengine.engine.Engine;
import com.nativegame.nattyengine.engine.collision.Collidable;

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

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public QuadTree() {
        mRoot = new QuadTreeNode(this);
        // We add them to the pool now
        for (int i = 0; i < MAX_TREE_NODE; i++) {
            mTreeNodePool.add(new QuadTreeNode(this));
        }
    }
    //========================================================

    //--------------------------------------------------------
    // Getter and Setter
    //--------------------------------------------------------
    public int getPoolSize() {
        return mTreeNodePool.size();
    }

    public QuadTreeNode getNode() {
        return mTreeNodePool.remove(0);
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    public void init(int width, int height) {
        mRoot.setArea(new Rect(0, 0, width, height));
    }

    public void checkCollision(Engine engine) {
        // Clear the collisions from the previous loop
        while (!mDetectedCollisions.isEmpty()) {
            Collision.returnToPool(mDetectedCollisions.remove(0));
        }
        mRoot.checkCollision(engine, mDetectedCollisions);
    }

    public void addCollidable(Collidable collidable) {
        mRoot.getCollidables().add(collidable);
    }

    public void removeCollidable(Collidable collidable) {
        mRoot.getCollidables().remove(collidable);
    }

    public void returnToPool(QuadTreeNode treeNode) {
        mTreeNodePool.add(treeNode);
    }
    //========================================================

}
