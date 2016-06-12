package com.wuxiaolong.androidmvpsample.rxjava;


/**
 * Created by oliver on 16/5/7.
 */
public interface ApiCallback<T> {

    void onSuccess(T model);

    void onFailure(int code, String msg);

    void onCompleted();

}
