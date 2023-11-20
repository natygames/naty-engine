package com.nativegame.nattyengine.entity;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public interface Reusable<T> {

    RecycleListener<T> getRecycleListener();

    void setRecycleListener(RecycleListener<T> listener);

    public interface RecycleListener<T> {

        void recycle(T object);

    }

}
