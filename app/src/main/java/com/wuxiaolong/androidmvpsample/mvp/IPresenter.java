package com.wuxiaolong.androidmvpsample.mvp;

/**
 * Created by mengxn on 16-9-9.
 */
public interface IPresenter<V> {

    void attachView(V view);

    void detachView();

}
