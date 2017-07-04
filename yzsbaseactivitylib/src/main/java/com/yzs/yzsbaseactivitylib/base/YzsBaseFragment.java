package com.yzs.yzsbaseactivitylib.base;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yzs.yzsbaseactivitylib.R;
import com.yzs.yzsbaseactivitylib.entity.BaseEventBusBean;

import de.greenrobot.event.EventBus;


/**
 * Author: 姚智胜
 * Version: V1.0版本
 * Description: 替代activity的基础fragment
 * Date: 2017/6/29
 * Email: 541567595@qq.com
 */

public abstract class YzsBaseFragment extends ImmersionFragment {

    protected View rootView;//在使用自定义toolbar时候的根布局 =toolBarView+childView
    private Toolbar toolbar;
    private TextView tv_title, tv_rightTitle;
    private ImageView iv_rightTitle;
    private LinearLayout ll_base_root;

    public LinearLayout getLl_base_root() {
        return ll_base_root;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (openEventBus()) {
            EventBus.getDefault().register(this);
        }
        if (null != getArguments()) {
            getBundleExtras(getArguments());
        }
        if (rootView == null) {
            //为空时初始化。
            if (showToolBar() && getToolBarResId() != 0) {
                //如果需要显示自定义toolbar,并且资源id存在的情况下，实例化baseView;
                rootView = inflater.inflate(R.layout.ac_base, container, false);//根布局
                ll_base_root = (LinearLayout) rootView.findViewById(R.id.ll_base_root);
                ViewStub mVs_toolbar = (ViewStub) rootView.findViewById(R.id.vs_toolbar);//toolbar容器
                FrameLayout fl_container = (FrameLayout) rootView.findViewById(R.id.fl_container);//子布局容器
                mVs_toolbar.setLayoutResource(getToolBarResId());//toolbar资源id
                mVs_toolbar.inflate();//填充toolbar
                inflater.inflate(getLayoutResource(), fl_container, true);//子布局
            } else {
                //不显示通用toolbar
                rootView = inflater.inflate(getLayoutResource(), container, false);

            }
        }
        initView(rootView);
        initToolBar();
        mvpCreate();
        return rootView;
    }

    /**
     * 初始化toolbar可重写覆盖自定的toolbar,base中实现的是通用的toolbar
     */
    public void initToolBar() {
        toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        if (toolbar == null) {
            return;
        }
        toolbar.setTitle("");
        _mActivity.setSupportActionBar(toolbar);
        tv_title = (TextView) toolbar.findViewById(R.id.toolbar_title);
        tv_rightTitle = (TextView) toolbar.findViewById(R.id.tv_toolbar_right);
        iv_rightTitle = (ImageView) toolbar.findViewById(R.id.iv_toolbar_right);

    }

    public void setRightTitle(String string, View.OnClickListener listener) {
        if (null != tv_rightTitle) {
            tv_rightTitle.setText(string);
            tv_rightTitle.setVisibility(View.VISIBLE);
            tv_rightTitle.setOnClickListener(listener);
        }
    }

    public void setRightImage(@DrawableRes int res, View.OnClickListener listener) {
        if (null != iv_rightTitle) {
            iv_rightTitle.setImageResource(res);
            iv_rightTitle.setVisibility(View.VISIBLE);
            iv_rightTitle.setOnClickListener(listener);
        }
    }

    public void setRightImage(Drawable drawable, View.OnClickListener listener) {
        if (null != iv_rightTitle) {
            iv_rightTitle.setImageDrawable(drawable);
            iv_rightTitle.setVisibility(View.VISIBLE);
            iv_rightTitle.setOnClickListener(listener);
        }
    }


    public void setTitle(String string) {
        if (null != tv_title){
            tv_title.setText(string);
            tv_title.setVisibility(View.VISIBLE);
        }

    }

    public void setTitle(int id) {
        if (null != tv_title){
            tv_title.setText(id);
            tv_title.setVisibility(View.VISIBLE);
        }
    }

    /**
     * mvp架构的初始化
     */
    public abstract void mvpCreate();

    /**
     * 是否开启eventBus
     */
    public abstract boolean openEventBus();

    /**
     * 是否显示通用toolBar
     */
    public abstract boolean showToolBar();

    /**
     * 获取自定义toolbarview 资源id 默认为0，showToolBar()方法必须返回true才有效
     */
    public int getToolBarResId() {
        return R.layout.layout_common_toolbar;
    }


    /**
     * 获取布局文件
     */
    protected int getLayoutResource() {
        return getLayoutRes();
    }

    /**
     * 获取布局文件
     */
    protected abstract int getLayoutRes();

    /**
     * 初始化view
     */
    protected abstract void initView(View rootView);

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

    /**
     * 获取bundle信息
     *
     * @param bundle
     */
    protected abstract void getBundleExtras(Bundle bundle);

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (openEventBus()) {
            EventBus.getDefault().unregister(this);
        }
    }
}
