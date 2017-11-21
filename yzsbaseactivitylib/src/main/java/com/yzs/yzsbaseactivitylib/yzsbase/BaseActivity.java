package com.yzs.yzsbaseactivitylib.yzsbase;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.FrameLayout;

import com.yzs.yzsbaseactivitylib.R;
import com.yzs.yzsbaseactivitylib.entity.BaseEventBusBean;
import com.yzs.yzsbaseactivitylib.util.AppManager;

import de.greenrobot.event.EventBus;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * Author: 姚智胜
 * Version: V1.0版本
 * Description:
 * Date: 2017/9/11
 * Email: 541567595@qq.com
 */

public abstract class BaseActivity extends SupportActivity {

    /**
     * 在使用自定义toolbar时候的根布局 =toolBarView+childView
     */
    protected View rootView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getAppManager().addActivity(this);
        Bundle extras = getIntent().getExtras();
        if (null != getIntent().getExtras()) {
            getBundleExtras(extras);
        }
        if (openEventBus()) {
            EventBus.getDefault().register(this);
        }
        setContentView(getContentViewResId());


    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        if (layoutResID == 0) {

            throw new RuntimeException("layoutResID==-1 have u create your layout?");
        }

        if (showToolBar() && getToolBarResId() != -1) {
            //如果需要显示自定义toolbar,并且资源id存在的情况下，实例化baseView;
            rootView = LayoutInflater.from(this).inflate(R.layout.ac_base, null, false);//根布局
            ViewStub mVs_toolbar = (ViewStub) rootView.findViewById(R.id.vs_toolbar);//toolbar容器
            FrameLayout fl_container = (FrameLayout) rootView.findViewById(R.id.fl_container);//子布局容器
            mVs_toolbar.setLayoutResource(getToolBarResId());//toolbar资源id
            mVs_toolbar.inflate();//填充toolbar
            LayoutInflater.from(this).inflate(layoutResID, fl_container, true);//子布局
            setContentView(rootView);
        } else {
            //不显示通用toolbar
            super.setContentView(layoutResID);

        }
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        throw new RuntimeException("please use setContentView(@LayoutRes int layoutResID) instead");
    }

    /**
     * 获取contentView 资源id
     */
    public abstract int getContentViewResId();

    /**
     * 是否显示通用toolBar
     */
    public abstract boolean showToolBar();

    /**
     * 是否开启eventBus
     */
    public abstract boolean openEventBus();

    //获取自定义toolbarview 资源id 默认为-1，showToolBar()方法必须返回true才有效
    public int getToolBarResId() {
        return R.layout.layout_common_toolbar;
    }


    /**
     * Bundle  传递数据
     *
     * @param extras
     */
    protected abstract void getBundleExtras(Bundle extras);

    /**
     * eventbus在主线程接收方法
     *
     * @param event
     */
    public void onEventMainThread(BaseEventBusBean event) {
        if (event != null) {
            EventBean(event);
        }
    }

    /**
     * EventBus接收信息的方法，开启后才会调用
     *
     * @param event
     */
    protected abstract void EventBean(BaseEventBusBean event);

    @Override
    protected void onDestroy() {
        super.onDestroy();
        rootView = null;
        if (openEventBus()) {
            EventBus.getDefault().unregister(this);
        }
        AppManager.getAppManager().finishActivity(this);
    }

}
