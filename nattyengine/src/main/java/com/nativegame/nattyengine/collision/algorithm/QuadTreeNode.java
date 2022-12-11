package com.nativegame.nattyengine.collision.algorithm;

import android.graphics.Rect;

import com.nativegame.nattyengine.collision.Collidable;
import com.nativegame.nattyengine.collision.CollisionType;
import com.nativegame.nattyengine.engine.GameEngine;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class QuadTreeNode {

    private static final int MAX_OBJECTS_TO_CHECK = 8;

    private final QuadTree mParent;

    private final Rect mArea = new Rect();

    public final List<Collidable> mCollidables = new ArrayList<>();
    private final List<QuadTreeNode> mChildren = new ArrayList<>(4);

    public QuadTreeNode(QuadTree quadTree) {
        mParent = quadTree;
    }

    public void setArea(Rect area) {
        mArea.set(area);
    }

    public void checkCollision(GameEngine gameEngine, List<Collision> detectedCollisions) {
        int size = mCollidables.size();
        if (size > MAX_OBJECTS_TO_CHECK && mParent.getPoolSize() >= 4) {
            // Divide the area in 4 part
            divideAndCheck(gameEngine, detectedCollisions);
        } else {
            for (int i = 0; i < size; i++) {
                Collidable collidableA = mCollidables.get(i);
                for (int j = i + 1; j < size; j++) {
                    Collidable collidableB = mCollidables.get(j);
                    if (CollisionHandler.isCollisionsDetected(collidableA, collidableB)) {
                        Collision c = Collision.init(collidableA, collidableB);
                        if (!hasBeenDetected(c, detectedCollisions)) {
                            detectedCollisions.add(c);
                            collidableA.onCollision(collidableB);
                            collidableB.onCollision(collidableA);
                        }
                    }
                }
            }
        }
    }

    private void divideAndCheck(GameEngine gameEngine, List<Collision> detectedCollisions) {
        mChildren.clear();
        // Add 4 children
        for (int i = 0; i < 4; i++) {
            mChildren.add(mParent.getNode());
        }
        // Check children collision
        for (int i = 0; i < 4; i++) {
            QuadTreeNode node = mChildren.get(i);
            node.setArea(getArea(i));
            node.checkObjects(mCollidables);
            node.checkCollision(gameEngine, detectedCollisions);
            // Clear and return to the pool
            node.mCollidables.clear();
            mParent.returnToPool(node);
        }
    }

    private void checkObjects(List<Collidable> collidables) {
        mCollidables.clear();
        int size = collidables.size();
        for (int i = 0; i < size; i++) {
            Collidable c = collidables.get(i);
            if (c.getCollisionType() != CollisionType.NONE
                    && Rect.intersects(c.getCollisionShape().getCollisionBounds(), mArea)) {
                mCollidables.add(c);
            }
        }
    }

    private boolean hasBeenDetected(Collision collision, List<Collision> detectedCollisions) {
        int size = detectedCollisions.size();
        for (int i = 0; i < size; i++) {
            if (detectedCollisions.get(i).equals(collision)) {
                return true;
            }
        }
        return false;
    }

    private Rect getArea(int area) {
        int startX = mArea.left;
        int startY = mArea.top;
        int width = mArea.width();
        int height = mArea.height();
        switch (area) {
            case 0:
                return new Rect(startX, startY, startX + width / 2, startY + height / 2);
            case 1:
                return new Rect(startX + width / 2, startY, startX + width, startY + height / 2);
            case 2:
                return new Rect(startX, startY + height / 2, startX + width / 2, startY + height);
            case 3:
                return new Rect(startX + width / 2, startY + height / 2, startX + width, startY + height);
        }

        return null;
    }

}
