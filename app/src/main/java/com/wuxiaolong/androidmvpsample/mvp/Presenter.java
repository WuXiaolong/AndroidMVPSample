package com.wuxiaolong.androidmvpsample.mvp;

public interface Presenter<V> {

    void attachView(V view);

    void detachView();

}
