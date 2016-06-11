package com.wuxiaolong.androidmvpsample.ui;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.smlxt.lxts.R;
import com.smlxt.lxts.ui.login.LoginActivity;
import com.smlxt.lxts.utils.ActivityManager;
import com.smlxt.lxts.utils.AppConstants;
import com.smlxt.lxts.utils.LogUtil;
import com.smlxt.lxts.utils.SharedPreferencesUtil;
import com.smlxt.lxts.view.CustomLoading;
import com.umeng.analytics.MobclickAgent;

import butterknife.ButterKnife;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * A simple {@link Fragment} subclass.
 */
public class BaseFragment extends Fragment {
    public Activity mActivity;
    public String TAG = "wxl";
    public ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_base, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        mActivity = getActivity();
    }

    public Toolbar initToolBar(View view, String title) {
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        TextView toolbar_title = (TextView) toolbar.findViewById(R.id.toolbar_title);
        toolbar_title.setText(title);
        return toolbar;
    }


    //    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        Log.d(TAG, mActivity + "=onDestroy");
//    }
    public void toastShow(int resId) {
        Toast.makeText(mActivity, resId, Toast.LENGTH_SHORT).show();
    }

    public void toastShow(String resId) {
        Toast.makeText(mActivity, resId, Toast.LENGTH_SHORT).show();
    }

    CustomLoading customLoading;

    public CustomLoading showProgressDialog() {
//        progressDialog = new ProgressDialog(mActivity);
//        progressDialog.setMessage("加载中");
//        progressDialog.show();
//        return progressDialog;
        customLoading = new CustomLoading(mActivity, R.style.CustomDialog);
        customLoading.show();
        return customLoading;
    }

    public void dismissProgressDialog() {
        if (customLoading != null && customLoading.isShowing()) {
            customLoading.dismiss();
        }
//        if (progressDialog != null && progressDialog.isShowing()) {
//            progressDialog.dismiss();// progressDialog.hide();会导致android.view.WindowLeaked
//        }
    }

    public void startLogin() {
        toastShow("登陆超时，请重新登录");
        SharedPreferencesUtil.setString(mActivity, AppConstants.USER_PASSWORD, "");
        ActivityManager.getInstance().finishAllActivity();
        mActivity.startActivity(new Intent(mActivity, LoginActivity.class));

    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtil.d("BaseFragment onResume=" + this.getClass().getSimpleName());
        MobclickAgent.onPageStart(this.getClass().getSimpleName()); //统计页面，"MainScreen"为页面名称，可自定义
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(this.getClass().getSimpleName());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        onUnsubscribe();
    }

    private CompositeSubscription mCompositeSubscription;

    public void onUnsubscribe() {
        if (mCompositeSubscription != null) {
            mCompositeSubscription.unsubscribe();//取消注册，以避免内存泄露
        }
    }

    public void addSubscription(Subscription subscription) {
//        if (mCompositeSubscription == null) {
        mCompositeSubscription = new CompositeSubscription();
//        }
        mCompositeSubscription.add(subscription);
    }
}
