package com.wuxiaolong.androidmvpsample.mvp.main;

import com.wuxiaolong.androidmvpsample.mvp.BasePresenter;
import com.wuxiaolong.androidmvpsample.rxjava.ApiCallback;
import com.wuxiaolong.androidmvpsample.rxjava.SubscriberCallBack;

/**
 * Created by WuXiaolong
 * on 2015/9/23.
 */
public class MainPresenter extends BasePresenter<MainView> {

    public MainPresenter(MainView view) {
        attachView(view);
    }


    public void loadData(String cityId) {
        mvpView.showLoading();
        addSubscription(apiStores.loadData(cityId),
                new SubscriberCallBack<>(new ApiCallback<MainModel>() {
                    @Override
                    public void onSuccess(MainModel model) {
                        mvpView.getDataSuccess(model);
                    }

                    @Override
                    public void onFailure(int code, String msg) {
                        mvpView.getDataFail(msg);
                    }

                    @Override
                    public void onCompleted() {
                        mvpView.hideLoading();
                    }
                }));
    }

}
