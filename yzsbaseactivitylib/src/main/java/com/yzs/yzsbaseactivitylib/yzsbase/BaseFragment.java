package com.yzs.yzsbaseactivitylib.yzsbase;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yzs.yzsbaseactivitylib.R;
import com.yzs.yzsbaseactivitylib.basemvp.BaseModel;
import com.yzs.yzsbaseactivitylib.basemvp.BasePresenter;
import com.yzs.yzsbaseactivitylib.entity.BaseEventBusBean;
import com.yzs.yzsbaseactivitylib.util.TUtil;

import de.greenrobot.event.EventBus;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Author: 姚智胜
 * Version: V1.0版本
 * Description: 整合后的baseFragment
 * Date: 2017/9/1
 * Email: 541567595@qq.com
 */

public abstract class BaseFragment<T extends BasePresenter, E extends BaseModel> extends SupportFragment {

    protected View rootView;//在使用自定义toolbar时候的根布局 =toolBarView+childView
    private Toolbar toolbar;
    private TextView tv_title, tv_rightTitle;
    private ImageView iv_rightTitle;
    private LinearLayout ll_base_root;
    protected View emptyView;// 空布局，recyclerView使用
    public T mPresenter;
    public E mModel;
    private boolean openEventBus = false;//是否开启eventbus
    private boolean showToolbar = false;//是否显示toolbar

    private boolean isMvp = true;

    public LinearLayout getLl_base_root() {
        return ll_base_root;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initSetting();
        if (openEventBus) {
            EventBus.getDefault().register(this);
        }
        if (null != getArguments()) {
            getBundleExtras(getArguments());
        }
        if (rootView == null) {
            //为空时初始化。
            if (showToolbar && getToolBarResId() != 0) {
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
        tv_title = (TextView) toolbar.findViewById(R.id.toolbar_title);
        tv_rightTitle = (TextView) toolbar.findViewById(R.id.tv_toolbar_right);
        iv_rightTitle = (ImageView) toolbar.findViewById(R.id.iv_toolbar_right);

    }

    /**
     * 设置界面所有的初始化开关
     */
    protected abstract void initSetting();

    /**
     * mvp架构的初始化
     */
    public void mvpCreate() {
        if (isMvp) {
            mPresenter = TUtil.getT(this, 0);
            mModel = TUtil.getT(this, 1);
        }

        if (mPresenter != null) {
            mPresenter.mContext = this.getActivity();
        }

        initPresenter();
    }

    /**
     * 设置界面是否使用mvp模式（不用关注这个方法，这个方法是专门处理列表类界面的）
     *
     * @param mvp
     */
    public void setMvp(boolean mvp) {
        isMvp = mvp;
    }

    /**
     * 简单页面无需mvp就不用管此方法即可,完美兼容各种实际场景的变通
     */
    public void initPresenter() {
    }

    /**
     * 当前页面Fragment支持沉浸式初始化。子类可以重写返回false，设置不支持沉浸式初始化
     * Immersion bar enabled boolean.
     *
     * @return the boolean
     */
    protected boolean immersionEnabled() {
        return true;
    }

    protected abstract void immersionInit();

    /**
     * 逻辑内容初始化，懒加载模式
     */
    protected abstract void initLogic();

    protected Toolbar getToolbar() {
        return toolbar;
    }

    //显示返回按钮
    public void showBackButton() {
        if (toolbar != null) {
            toolbar.setNavigationIcon(R.drawable.base_toolbar_back);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (_mActivity.getSupportFragmentManager().getBackStackEntryCount() > 1) {
                        pop();
                    } else {
                        _mActivity.finish();
                    }
                }
            });
        }
    }

    //显示返回按钮
    public void showBackButton(@DrawableRes int res) {
        if (toolbar != null) {
            toolbar.setNavigationIcon(res);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (_mActivity.getSupportFragmentManager().getBackStackEntryCount() > 1) {
                        pop();
                    } else {
                        _mActivity.finish();
                    }
                }
            });
        }
    }

    //显示返回按钮
    public void showBackButton(@DrawableRes int res, @Nullable View.OnClickListener listener) {
        if (toolbar != null) {
            toolbar.setNavigationIcon(res);
            if (listener != null) {
                toolbar.setNavigationOnClickListener(listener);
            } else {
                toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (_mActivity.getSupportFragmentManager().getBackStackEntryCount() > 1) {
                            pop();
                        } else {
                            _mActivity.finish();
                        }
                    }
                });
            }
        }
    }

    //显示返回按钮
    public void showBackButton(Drawable drawable, @Nullable View.OnClickListener listener) {
        if (toolbar != null) {
            toolbar.setNavigationIcon(drawable);
            if (listener != null) {
                toolbar.setNavigationOnClickListener(listener);
            } else {
                toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (_mActivity.getSupportFragmentManager().getBackStackEntryCount() > 1) {
                            pop();
                        } else {
                            _mActivity.finish();
                        }
                    }
                });
            }

        }
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
        if (null != tv_title) {
            tv_title.setText(string);
            tv_title.setVisibility(View.VISIBLE);
        }

    }

    public void setTitle(int id) {
        if (null != tv_title) {
            tv_title.setText(id);
            tv_title.setVisibility(View.VISIBLE);
        }
    }


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
     * 获取bundle信息
     *
     * @param bundle
     */
    protected abstract void getBundleExtras(Bundle bundle);

    /**
     * 在底层为recyclerView提供空布局的简便实现方法
     *
     * @param str
     * @param drawRes
     * @return
     */
    public View getEmptyView(String str, @DrawableRes int drawRes) {
        if (emptyView != null) {
            return emptyView;
        }
        emptyView = LayoutInflater.from(_mActivity).inflate(R.layout.layout_empty_view, null, false);
        if (!TextUtils.isEmpty(str)) {
            TextView textView = (TextView) emptyView.findViewById(R.id.tv_text);
            ImageView imageView = (ImageView) emptyView.findViewById(R.id.iv_empty);
            imageView.setImageResource(drawRes);
            textView.setText(str);
        }
        return emptyView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null) {
            mPresenter.onDestroy();
        }
        if (openEventBus) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initLogic();
    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        if (immersionEnabled()) {
            immersionInit();
        }
    }

    /**
     * 设置是否开启eventBus
     *
     * @param isOpen
     */
    public void setOpenEventBus(boolean isOpen) {
        openEventBus = isOpen;
    }

    /**
     * 设置是否显示ToolBar
     *
     * @param isOpen
     */
    public void setShowToolBar(boolean isOpen) {
        showToolbar = isOpen;
    }

    /**
     * eventBus通用的接收器，如使用自己重写，如果想单独接收，请重写onEventMainThread方法并使用其他bean
     *
     * @param event
     */
    protected void EventBean(BaseEventBusBean event) {

    }


}

