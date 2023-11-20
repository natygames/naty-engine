package com.nativegame.nattyengine.util.pool;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class GrowObjectPool<T> extends BasePool<T> {

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public GrowObjectPool(PoolObjectFactory<T> factory, int initCount) {
        super(factory, initCount);
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    protected T getDefaultObject() {
        return getFactory().createObject();
    }

    @Override
    protected void onReturnObject(T object) {
        getAllObjects().add(object);
    }
    //========================================================

}
