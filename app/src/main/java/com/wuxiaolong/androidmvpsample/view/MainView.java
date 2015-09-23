package com.wuxiaolong.androidmvpsample.view;

import com.wuxiaolong.androidmvpsample.model.MainModel;

/**
 * Created by WuXiaolong on 2015/9/23.
 */
public interface MainView {
    void showData(MainModel mainModel);
    void showProgress();
    void hideProgress();
}
