package com.nativegame.nattyengine.collision.algorithm;

import com.nativegame.nattyengine.collision.Collidable;

import java.util.ArrayList;
import java.util.List;

public class Collision {

    public Collidable mObjectA;
    public Collidable mObjectB;

    private static final List<Collision> sCollisionPool = new ArrayList<>();

    public static Collision init(Collidable objectA, Collidable objectB) {
        if (sCollisionPool.isEmpty()) {
            return new Collision(objectA, objectB);
        }
        Collision c = sCollisionPool.remove(0);
        c.mObjectA = objectA;
        c.mObjectB = objectB;
        return c;
    }

    public static void returnToPool(Collision c) {
        c.mObjectA = null;
        c.mObjectB = null;
        sCollisionPool.add(c);
    }

    public Collision(Collidable objectA, Collidable objectB) {
        mObjectA = objectA;
        mObjectB = objectB;
    }

    public boolean equals(Collision c) {
        return (mObjectA == c.mObjectA && mObjectB == c.mObjectB)
                || (mObjectA == c.mObjectB && mObjectB == c.mObjectA);
    }

}
