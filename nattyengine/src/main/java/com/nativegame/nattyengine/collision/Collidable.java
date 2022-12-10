package com.nativegame.nattyengine.collision;

import com.nativegame.nattyengine.collision.shape.ShapeCollidable;

public interface Collidable {

    CollisionType getCollisionType();

    void setCollisionType(CollisionType collisionType);

    ShapeCollidable getCollisionShape();

    void setCollisionShape(ShapeCollidable shapeCollidable);

    void onCollision(Collidable otherObject);

}
