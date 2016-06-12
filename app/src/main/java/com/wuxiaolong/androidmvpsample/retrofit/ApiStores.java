package com.wuxiaolong.androidmvpsample.retrofit;

import com.wuxiaolong.androidmvpsample.mvp.main.MainModel;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by WuXiaolong
 * on 2016/3/24.
 */
public interface ApiStores {
    //baseUrl
    String API_SERVER_URL = "http://www.weather.com.cn/";

    //加载天气
    @GET("adat/sk/{cityId}.html")
    Observable<MainModel> loadData(@Path("cityId") String cityId);
}
