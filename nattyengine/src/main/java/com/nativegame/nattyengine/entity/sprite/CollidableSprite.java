package com.nativegame.nattyengine.entity.sprite;

import android.graphics.Canvas;

import com.nativegame.nattyengine.Game;
import com.nativegame.nattyengine.collision.Collidable;
import com.nativegame.nattyengine.collision.CollisionType;
import com.nativegame.nattyengine.collision.shape.ShapeCollidable;

public abstract class CollidableSprite extends Sprite implements Collidable {

    private CollisionType mCollisionType = CollisionType.ACTIVE;
    private ShapeCollidable mCollisionShape;

    protected CollidableSprite(Game game, int drawableId) {
        super(game, drawableId);
    }

    @Override
    public CollisionType getCollisionType() {
        return mCollisionType;
    }

    @Override
    public void setCollisionType(CollisionType type) {
        mCollisionType = type;
    }

    @Override
    public ShapeCollidable getCollisionShape() {
        return mCollisionShape;
    }

    @Override
    public void setCollisionShape(ShapeCollidable shapeCollidable) {
        mCollisionShape = shapeCollidable;
    }

    @Override
    public void onCollision(Collidable otherObject) {
    }

    @Override
    public void onPostUpdate(long elapsedMillis) {
        // We align the collision shape to center of sprite
        mCollisionShape.setCollisionBoundsPosition((int) mX + mWidth / 2, (int) mY + mHeight / 2);
    }

    @Override
    public void draw(Canvas canvas) {
        if (Game.getDebugMode()) {
            if (getCollisionType() != CollisionType.NONE) {
                // Draw collision shape on screen
                canvas.drawBitmap(mCollisionShape.getCollisionBitmap(), null,
                        mCollisionShape.getCollisionBounds(), null);
            }
        }
        super.draw(canvas);
    }

}
