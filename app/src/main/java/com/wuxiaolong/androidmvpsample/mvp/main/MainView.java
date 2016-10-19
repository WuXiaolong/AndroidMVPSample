package com.wuxiaolong.androidmvpsample.mvp.main;

/**
 * Created by WuXiaolong on 2015/9/23.
 * 处理业务需要哪些方法
 * github:https://github.com/WuXiaolong/
 * weibo:http://weibo.com/u/2175011601
 * 微信公众号：吴小龙同学
 * 个人博客：http://wuxiaolong.me/
 */
public interface MainView extends BaseView{

    void getDataSuccess(MainModel model);

    void getDataFail(String msg);

}
