package com.wuxiaolong.androidmvpsample.presenter;

import com.wuxiaolong.androidmvpsample.model.MainModel;
import com.wuxiaolong.androidmvpsample.model.MainModelBean;
import com.wuxiaolong.androidmvpsample.view.MainView;

/**
 * Created by WuXiaolong on 2015/9/23.
 * View和Model的桥梁，它从Model层检索数据后，返回给View层
 */
public class MainPresenter implements Presenter<MainView>, IMainPresenter {
    private MainView mMainView;
    private MainModel mMainModel;

    public MainPresenter(MainView view) {
        attachView(view);
        mMainModel = new MainModel(this);
    }

    @Override
    public void attachView(MainView view) {
        this.mMainView = view;
    }

    @Override
    public void detachView() {
        this.mMainView = null;
    }

    public void loadData() {
        mMainView.showProgress();
        mMainModel.loadData();
    }


    @Override
    public void loadDataSuccess(MainModelBean mainModelBean) {
        mMainView.showData(mainModelBean);
        mMainView.hideProgress();
    }

    @Override
    public void loadDataFailure() {
        mMainView.hideProgress();
    }
}
