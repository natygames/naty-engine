package com.nativegame.nattyengine.util.pool;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class SafeFixedObjectPool<T> extends FixedObjectPool<T> {

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public SafeFixedObjectPool(PoolObjectFactory<T> factory, int minObject) {
        super(factory, minObject);
    }

    public SafeFixedObjectPool(PoolObjectFactory<T> factory, int minObject, int maxObject) {
        super(factory, minObject, maxObject);
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    protected T getDefaultObject() {
        return getFactory().createObject();
    }
    //========================================================

}
