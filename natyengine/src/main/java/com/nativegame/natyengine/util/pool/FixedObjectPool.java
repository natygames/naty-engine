package com.nativegame.natyengine.util.pool;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class FixedObjectPool<T> extends BasePool<T> {

    private final int mMaxObjectCount;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public FixedObjectPool(PoolObjectFactory<T> factory, int minCount) {
        this(factory, minCount, minCount);
    }

    public FixedObjectPool(PoolObjectFactory<T> factory, int minCount, int maxCount) {
        super(factory, minCount);
        mMaxObjectCount = maxCount;
    }
    //========================================================

    //--------------------------------------------------------
    // Getter and Setter
    //--------------------------------------------------------
    public int getMaxObjectCount() {
        return mMaxObjectCount;
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    protected T getDefaultObject() {
        return null;
    }

    @Override
    protected void onReturnObject(T object) {
        if (getObjectCount() < mMaxObjectCount) {
            getAllObjects().add(object);
        }
    }
    //========================================================

}
