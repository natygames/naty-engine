package com.nativegame.nattyengine.util.pool;

import java.util.List;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public interface Pool<T> {

    PoolObjectFactory<T> getFactory();

    List<T> getAllObjects();

    int getObjectCount();

    T obtainObject();

    void returnObject(T object);

    void release();

    public interface PoolObjectFactory<T> {

        T createObject();

    }

}
