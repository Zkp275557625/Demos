package com.zkp.demos;

import android.app.Application;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.coder.zzq.smartshow.core.SmartShow;
import com.coder.zzq.smartshow.toast.SmartToast;
import com.zkp.demos.crash.UnCaughtHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author: zkp
 * @project: Demos
 * @package: com.zkp.demos
 * @time: 2019/4/2 9:41
 * @description: App全局类
 */
public class App extends Application {

    private static App application;
    private static Context mContext;

    private List<AppCompatActivity> mActivityList;
    private List<Fragment> mFragmentsList;

    public static Context getContext() {
        return mContext;
    }

    public static App getApplication() {
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        mContext = this;
        mActivityList = new ArrayList<>();
        mFragmentsList = new ArrayList<>();

        //初始化SmartShow
        SmartShow.init(this);
        SmartToast.setting().typeInfoToastThemeColorRes(R.color.colorPrimary);

    }

    public void initUnCaughtHandler() {
        Thread.setDefaultUncaughtExceptionHandler(new UnCaughtHandler(this));
    }

    public void addActivity(AppCompatActivity activity) {
        mActivityList.add(activity);
    }

    public void addFragment(Fragment fragment) {
        mFragmentsList.add(fragment);
    }

    /**
     * 退出应用
     */
    public void exitApplication() {
        for (AppCompatActivity activity : mActivityList) {
            if (activity != null) {
                activity.finish();
            }
        }
        for (Fragment fragment : mFragmentsList) {
            if (fragment != null) {
                Objects.requireNonNull(fragment.getActivity()).onBackPressed();
            }
        }
        //杀死该应用进程
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
