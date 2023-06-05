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

    private static final int MAX_NODE = 12;

    private final QuadTreeNode mRoot;

    private final List<QuadTreeNode> mNodePool = new ArrayList<>();
    private final List<Collision> mDetectedCollisions = new ArrayList<>();

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public QuadTree() {
        mRoot = new QuadTreeNode(this);
        // We add them to the pool now
        for (int i = 0; i < MAX_NODE; i++) {
            mNodePool.add(new QuadTreeNode(this));
        }
    }
    //========================================================

    //--------------------------------------------------------
    // Getter and Setter
    //--------------------------------------------------------
    public int getPoolSize() {
        return mNodePool.size();
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
            Collision.returnCollision(mDetectedCollisions.remove(0));
        }
        mRoot.checkCollision(engine, mDetectedCollisions);
    }

    public void addCollidable(Collidable collidable) {
        mRoot.getCollidables().add(collidable);
    }

    public void removeCollidable(Collidable collidable) {
        mRoot.getCollidables().remove(collidable);
    }

    public QuadTreeNode obtainNode() {
        return mNodePool.remove(0);
    }

    public void returnNode(QuadTreeNode treeNode) {
        mNodePool.add(treeNode);
    }
    //========================================================

}
