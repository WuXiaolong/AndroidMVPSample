package com.wuxiaolong.androidmvpsample.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.wuxiaolong.androidmvpsample.R;
import com.wuxiaolong.androidmvpsample.mvp.main.MainModel;
import com.wuxiaolong.androidmvpsample.mvp.main.MainPresenter;
import com.wuxiaolong.androidmvpsample.mvp.main.MainView;
import com.wuxiaolong.androidmvpsample.mvp.other.MvpActivity;
import com.wuxiaolong.androidmvpsample.retrofit.ApiCallback;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 由Activity/Fragment实现View里方法，包含一个Presenter的引用
 * Created by WuXiaolong
 * on 2015/9/23.
 * github:https://github.com/WuXiaolong/
 * weibo:http://weibo.com/u/2175011601
 * 微信公众号：吴小龙同学
 * 个人博客：http://wuxiaolong.me/
 */
public class MainActivity extends MvpActivity<MainPresenter> implements MainView {

    @Bind(R.id.text)
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initToolBarAsHome("MVP+Retrofit+Rxjava");

    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter(this);
    }


    @Override
    public void getDataSuccess(MainModel model) {
        //接口成功回调
        MainModel.WeatherinfoBean weatherinfo = model.getWeatherinfo();
        String showData = getResources().getString(R.string.city) + weatherinfo.getCity()
                + getResources().getString(R.string.wd) + weatherinfo.getWD()
                + getResources().getString(R.string.ws) + weatherinfo.getWS()
                + getResources().getString(R.string.time) + weatherinfo.getTime();
        text.setText(showData);
    }

    @Override
    public void getDataFail(String msg) {
        toastShow("网络不给力");

    }

    @Override
    public void showLoading() {
        showProgressDialog();
    }

    @Override
    public void hideLoading() {
        dismissProgressDialog();
    }


    @OnClick({R.id.button1, R.id.button2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button1:
                loadData();
                break;
            case R.id.button2:
                //请求接口
                mvpPresenter.loadData("101310222");
                break;
        }
    }

    //全国+国外主要城市代码http://mobile.weather.com.cn/js/citylist.xml
    private void loadData() {
        showProgressDialog();
        addSubscription(apiStores.loadData("101190201"),
                new ApiCallback<MainModel>() {
                    @Override
                    public void onSuccess(MainModel model) {
                        MainModel.WeatherinfoBean weatherinfo = model.getWeatherinfo();
                        String showData = getResources().getString(R.string.city) + weatherinfo.getCity()
                                + getResources().getString(R.string.wd) + weatherinfo.getWD()
                                + getResources().getString(R.string.ws) + weatherinfo.getWS()
                                + getResources().getString(R.string.time) + weatherinfo.getTime();
                        text.setText(showData);
                    }

                    @Override
                    public void onFailure(int code, String msg) {
                        toastShow(msg);

                    }

                    @Override
                    public void onFinish() {
                        dismissProgressDialog();
                    }
                });
    }
}
