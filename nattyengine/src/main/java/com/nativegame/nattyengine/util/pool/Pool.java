package com.nativegame.nattyengine.util.pool;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public interface Pool<T> {

    int getMinObject();

    int getMaxObject();

    T obtainObject();

    void returnObject(T object);

    void release();

    public interface PoolObjectFactory<T> {

        T createObject();

    }

}
