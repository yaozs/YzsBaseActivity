package com.yzs.yzsbaseactivitylib.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Author: 姚智胜
 * Version: V1.0版本
 * Description: 懒加载fragment，没有使用fragmentation提供的懒加载方式，认为那种方式不友好
 * Date: 2016/12/29.
 */
public abstract class YzsBaseLazyFragment extends YzsBaseFragment {
    private static final String TAG = "YzsBaseLazyFragment";


    /**
     * Fragment title
     */
    public String fragmentTitle;
    /**
     * 是否可见状态
     */
    private boolean isVisible;
    /**
     * 标志位，View已经初始化完成。
     * 2016/04/29
     * 用isAdded()属性代替
     * 2016/05/03
     * isPrepared还是准一些,isAdded有可能出现onCreateView没走完但是isAdded了
     */
    private boolean isPrepared;
    /**
     * 是否第一次加载
     */
    protected boolean isFirstLoad = true;


    //    /**
//     * 在这里实现Fragment数据的缓加载.
//     *
//     * @param isVisibleToUser
//     */
//    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        super.setUserVisibleHint(isVisibleToUser);
//        if (getUserVisibleHint()) {
//            isVisible = true;
//            onVisible();
//        } else {
//            isVisible = false;
//            onInvisible();
//        }
//    }
//
//    @Override
//    protected void initLogic(View view) {
//        //懒加载中抛弃这个方法，使用lazyload方法
//        lazyLoad();
//    }
//
//    /**
//     * 显示时执行
//     */
//    protected void onVisible() {
//        lazyLoad();
//    }
//
//    /**
//     * 懒加载方法
//     */
//    protected abstract void lazyLoad();
//
//    /**
//     * 隐藏时执行（一般不写）
//     */
//    protected void onInvisible() {
//    }
}
