package com.wuxiaolong.androidmvpsample.mvp.main;

import com.wuxiaolong.androidmvpsample.mvp.BasePresenter;

/**
 * Created by WuXiaolong on 2015/9/23.
 * View和Model的桥梁，它从Model层检索数据后，返回给View层
 */
public class MainPresenter extends BasePresenter {

    public MainPresenter(MainView view) {
        attachView(view);
    }


    public void loadData() {

    }

}
