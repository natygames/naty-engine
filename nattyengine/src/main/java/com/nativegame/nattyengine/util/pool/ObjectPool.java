package com.nativegame.nattyengine.util.pool;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class ObjectPool<T> implements Pool<T> {

    private final PoolObjectFactory<T> mFactory;
    private final int mMaxObject;

    private List<T> mObjects;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public ObjectPool(PoolObjectFactory<T> factory, int minObject) {
        this(factory, minObject, minObject);
    }

    public ObjectPool(PoolObjectFactory<T> factory, int minObject, int maxObject) {
        mFactory = factory;
        mMaxObject = maxObject;
        mObjects = new ArrayList<>(maxObject);
        // We add them to the pool now
        for (int i = 0; i < minObject; i++) {
            mObjects.add(mFactory.createObject());
        }
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public T obtainObject() {
        if (!mObjects.isEmpty()) {
            return mObjects.remove(0);
        } else {
            return mFactory.createObject();
        }
    }

    @Override
    public void returnObject(T object) {
        if (mObjects.size() < mMaxObject) {
            mObjects.add(object);
        }
    }

    @Override
    public void release() {
        mObjects.clear();
        mObjects = null;
    }
    //========================================================

}
