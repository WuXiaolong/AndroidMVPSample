package com.wuxiaolong.androidmvpsample.mvp.main;

/**
 * Created by WuXiaolong on 2015/9/23.
 * 此接口作用是连接Model
 */
public interface IMainPresenter {
    void loadDataSuccess(MainModelBean mainModelBean);

    void loadDataFailure();
}
