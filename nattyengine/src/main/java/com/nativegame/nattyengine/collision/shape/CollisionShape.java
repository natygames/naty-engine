package com.nativegame.nattyengine.collision.shape;

import android.graphics.Rect;

public abstract class CollisionShape implements ShapeCollidable {

    private final int mCollisionShapeWidth;
    private final int mCollisionShapeHeight;

    private final Rect mBounds = new Rect(-1, -1, -1, -1);

    protected CollisionShape(int collisionShapeWidth, int collisionShapeHeight) {
        mCollisionShapeWidth = collisionShapeWidth;
        mCollisionShapeHeight = collisionShapeHeight;
    }

    @Override
    public Rect getCollisionBounds() {
        return mBounds;
    }

    @Override
    public void setCollisionBoundsPosition(int x, int y) {
        mBounds.set(x - mCollisionShapeWidth / 2,
                y - mCollisionShapeHeight / 2,
                x + mCollisionShapeWidth / 2,
                y + mCollisionShapeHeight / 2);
    }

}
