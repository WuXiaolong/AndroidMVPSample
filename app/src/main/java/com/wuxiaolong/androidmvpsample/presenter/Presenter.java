package com.wuxiaolong.androidmvpsample.presenter;

public interface Presenter<V> {

    void attachView(V view);

    void detachView();

}
