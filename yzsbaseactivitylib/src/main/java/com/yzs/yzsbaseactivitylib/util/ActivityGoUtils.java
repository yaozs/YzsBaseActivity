package com.yzs.yzsbaseactivitylib.util;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.fragment.app.Fragment;

/**
 * Author 姚智胜
 * Version V1.0版本
 * Description: 跳转工具类
 * Date: 2017/4/22
 */

public class ActivityGoUtils {

    private static ActivityGoUtils activityUtils = new ActivityGoUtils();

    private ActivityGoUtils() {
    }

    /***
     * 获得AppManager的实例
     *
     * @return AppManager实例
     */
    public static ActivityGoUtils getInstance() {
        if (activityUtils == null) {
            activityUtils = new ActivityGoUtils();
        }
        return activityUtils;
    }

    /**
     * 界面跳转
     *
     * @param clazz 目标Activity
     */
    public void readyGo(Activity activity, Class<?> clazz) {
        readyGo(activity, clazz, null);
    }

    /**
     * 跳转界面，  传参
     *
     * @param clazz  目标Activity
     * @param bundle 数据
     */
    public void readyGo(Activity activity, Class<?> clazz, Bundle bundle) {
        if (null == activity) {
            Log.e("ActivityStackManager", "null==activity");
        } else {
            Intent intent = new Intent(activity, clazz);
            if (null != bundle)
                intent.putExtras(bundle);
            activity.startActivity(intent);
        }
    }

    /**
     * 跳转界面并关闭当前界面
     *
     * @param clazz 目标Activity
     */
    public void readyGoThenKill(Activity activity, Class<?> clazz) {
        readyGoThenKill(activity, clazz, null);
    }

    /**
     * @param clazz  目标Activity
     * @param bundle 数据
     */
    public void readyGoThenKill(Activity activity, Class<?> clazz, Bundle bundle) {
        readyGo(activity, clazz, bundle);
        activity.finish();
    }

    /**
     * startActivityForResult
     *
     * @param clazz       目标Activity
     * @param requestCode 发送判断值
     */
    public void readyGoForResult(Activity activity, Class<?> clazz, int requestCode) {

        if (null == activity) {
            Log.e("ActivityStackManager", "null==activity");
        } else {
            Intent intent = new Intent(activity, clazz);
            activity.startActivityForResult(intent, requestCode);

        }

    }

    /**
     * startActivityForResult with bundle
     *
     * @param clazz       目标Activity
     * @param requestCode 发送判断值
     * @param bundle      数据
     */
    public void readyGoForResult(Activity activity, Class<?> clazz, int requestCode, Bundle bundle) {
        Intent intent = new Intent(activity, clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        activity.startActivityForResult(intent, requestCode);
    }

    /**
     * startActivityForResult( fragment要返回值的方法)
     *
     * @param clazz       目标Activity
     * @param requestCode 发送判断值
     */
    public void readyGoForResult(Activity activity, Fragment fragment, Class<?> clazz, int requestCode) {
        Intent intent = new Intent(activity, clazz);
        fragment.startActivityForResult(intent, requestCode);
    }

    /**
     * startActivityForResult with bundle ( fragment要返回值的方法)
     *
     * @param clazz       目标Activity
     * @param requestCode 发送判断值
     * @param bundle      数据
     */
    public void readyGoForResult(Activity activity, Fragment fragment, Class<?> clazz, int requestCode, Bundle bundle) {
        Intent intent = new Intent(activity, clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        fragment.startActivityForResult(intent, requestCode);
    }


}
