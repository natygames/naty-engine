package com.nativegame.natyengine.util.pool;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public abstract class BasePool<T> implements Pool<T> {

    private final PoolObjectFactory<T> mFactory;

    private List<T> mObjects = new ArrayList<>();

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public BasePool(PoolObjectFactory<T> factory, int initCount) {
        mFactory = factory;
        // We add them to the pool now
        for (int i = 0; i < initCount; i++) {
            mObjects.add(factory.createObject());
        }
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public PoolObjectFactory<T> getFactory() {
        return mFactory;
    }

    @Override
    public List<T> getAllObjects() {
        return mObjects;
    }

    @Override
    public int getObjectCount() {
        return mObjects.size();
    }

    @Override
    public T obtainObject() {
        if (!mObjects.isEmpty()) {
            return mObjects.remove(0);
        } else {
            return getDefaultObject();
        }
    }

    @Override
    public void returnObject(T object) {
        onReturnObject(object);
    }

    @Override
    public void release() {
        mObjects.clear();
        mObjects = null;
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    protected abstract T getDefaultObject();

    protected abstract void onReturnObject(T object);
    //========================================================

}
