package com.wuxiaolong.androidmvpsample.mvp.main;

import com.wuxiaolong.androidmvpsample.mvp.IView;

/**
 * Created by WuXiaolong on 2015/9/23.
 * 处理业务需要哪些方法
 */
public interface MainView<P> extends IView<P> {

    void getDataSuccess(MainModel model);

    void getDataFail(String msg);

    void showLoading();

    void hideLoading();
}
