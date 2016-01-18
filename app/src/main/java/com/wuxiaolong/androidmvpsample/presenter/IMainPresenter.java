package com.wuxiaolong.androidmvpsample.presenter;

import com.wuxiaolong.androidmvpsample.model.MainModelBean;

/**
 * Created by WuXiaolong on 2015/9/23.
 * 此接口作用是连接Model
 */
public interface IMainPresenter {
    void loadDataSuccess(MainModelBean mainModelBean);

    void loadDataFailure();
}
