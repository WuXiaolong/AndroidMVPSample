package com.wuxiaolong.androidmvpsample.view;

import com.wuxiaolong.androidmvpsample.model.MainModelBean;

/**
 * Created by WuXiaolong on 2015/9/23.
 * 处理业务需要哪些方法
 */
public interface MainView {
    void showData(MainModelBean mainModelBean);

    void showProgress();

    void hideProgress();
}
