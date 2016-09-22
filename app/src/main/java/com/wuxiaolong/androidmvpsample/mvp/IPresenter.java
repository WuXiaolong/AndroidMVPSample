package com.wuxiaolong.androidmvpsample.mvp;

/**
 * Created by mengxn on 16-9-22.
 */
public interface IPresenter<V> {

    void attachView(V view);

    void detachView();

}
