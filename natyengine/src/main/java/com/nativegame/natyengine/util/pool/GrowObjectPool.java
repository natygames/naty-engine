package com.nativegame.natyengine.util.pool;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class GrowObjectPool<T> extends BasePool<T> {

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public GrowObjectPool(PoolObjectFactory<T> factory, int minCount) {
        super(factory, minCount);
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
