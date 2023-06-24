package com.nativegame.nattyengine.engine.collision;

import com.nativegame.nattyengine.engine.collision.hitbox.HitBox;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public interface Collidable {

    HitBox getCollisionHitBox();

    void setCollisionHitBox(HitBox hitBox);

    CollisionType getCollisionType();

    void setCollisionType(CollisionType collisionType);

    void collide(Collidable collidable);

}
