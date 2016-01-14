package com.wuxiaolong.androidmvpsample.presenter;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.wuxiaolong.androidmvpsample.model.MainModel;
import com.wuxiaolong.androidmvpsample.view.MainView;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by WuXiaolong on 2015/9/23.
 * 业务具体处理
 */
public class MainPresenter implements Presenter<MainView> {
    private MainView mMainView;

    public MainPresenter(MainView view) {
        attachView(view);
    }

    @Override
    public void attachView(MainView view) {
        this.mMainView = view;
    }

    public void loadData() {
        mMainView.showProgress();
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.get("http://www.weather.com.cn/adat/sk/101010100.html", new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    MainModel mainModel = new MainModel();
                    JSONObject weatherinfo = response.getJSONObject("weatherinfo");
                    mainModel.setCity(weatherinfo.getString("city"));
                    mainModel.setWd(weatherinfo.getString("WD"));
                    mainModel.setWs(weatherinfo.getString("WS"));
                    mainModel.setTime(weatherinfo.getString("time"));
                    mMainView.showData(mainModel);
                    mMainView.hideProgress();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                mMainView.hideProgress();
            }
        });
    }

    @Override
    public void detachView() {
        this.mMainView = null;
    }
}
