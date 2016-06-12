package com.wuxiaolong.androidmvpsample.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.wuxiaolong.androidmvpsample.R;
import com.wuxiaolong.androidmvpsample.mvp.MvpActivity;
import com.wuxiaolong.androidmvpsample.mvp.main.MainModel;
import com.wuxiaolong.androidmvpsample.mvp.main.MainPresenter;
import com.wuxiaolong.androidmvpsample.mvp.main.MainView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by WuXiaolong on 2015/9/23.
 * 由Activity/Fragment实现View里方法，包含一个Presenter的引用
 */
public class MainActivity extends MvpActivity<MainPresenter> implements MainView {

    @Bind(R.id.text)
    TextView text;
    @Bind(R.id.mProgressBar)
    ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //请求接口
        mvpPresenter.loadData("101010100");
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
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mProgressBar.setVisibility(View.GONE);
    }


}
