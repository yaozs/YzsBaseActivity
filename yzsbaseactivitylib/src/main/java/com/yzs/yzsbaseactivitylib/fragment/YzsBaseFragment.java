package com.yzs.yzsbaseactivitylib.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yzs.yzsbaseactivitylib.R;
import com.yzs.yzsbaseactivitylib.entity.EventCenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Author: 姚智胜
 * Version: V1.0版本
 * Description:
 * Date: 2016/10/24
 */
public abstract class YzsBaseFragment extends SupportFragment {
    private static final String TAG = "YzsBaseFragment";
    protected android.view.View rootView;

    public Toolbar mToolbar;
    public TextView title;
    public TextView tv_menu;
    public ImageView iv_menu;
    public View view;


    public YzsBaseFragment() { /* compiled code */ }

    @Override
    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        if (null != getArguments()) {
            getBundleExtras(getArguments());
        }
        view = initContentView(inflater, container, savedInstanceState);
//        initView(view);
        return view;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        if (null != mToolbar) {
            initTitle();
        }
        initView(view);

        initLogic();
    }

    // 初始化UI setContentView
    protected abstract View initContentView(LayoutInflater inflater, @Nullable ViewGroup container,
                                            @Nullable Bundle savedInstanceState);

    // 初始化控件
    protected abstract void initView(View view);

    // 逻辑处理
    protected abstract void initLogic();

    protected void initTitle() {
        title = (TextView) view.findViewById(R.id.toolbar_title);
        iv_menu = (ImageView) view.findViewById(R.id.toolbar_iv_menu);
        tv_menu = (TextView) view.findViewById(R.id.toolbar_tv_menu);

    }


    /**
     * 获取bundle信息
     *
     * @param bundle
     */
    protected abstract void getBundleExtras(Bundle bundle);

    /**
     * EventBus接收消息
     *
     * @param center 消息接收
     */
    @Subscribe
    public void onEventMainThread(EventCenter center) {

        if (null != center) {
            onEventComing(center);
        }

    }

    /**
     * EventBus接收消息
     *
     * @param center 获取事件总线信息
     */
    protected abstract void onEventComing(EventCenter center);


    @Override
    public void onDestroyView() {
        EventBus.getDefault().unregister(this);
        super.onDestroyView();
    }
}
