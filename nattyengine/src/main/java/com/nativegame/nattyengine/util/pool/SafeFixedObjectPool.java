package com.nativegame.nattyengine.util.pool;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class SafeFixedObjectPool<T> extends FixedObjectPool<T> {

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public SafeFixedObjectPool(PoolObjectFactory<T> factory, int minCount) {
        super(factory, minCount);
    }

    public SafeFixedObjectPool(PoolObjectFactory<T> factory, int minCount, int maxCount) {
        super(factory, minCount, maxCount);
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
