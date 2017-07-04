package com.yzs.yzsbaseactivitylib.base;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.yzs.yzsbaseactivitylib.R;
import com.yzs.yzsbaseactivitylib.util.TUtil;


/**
 * Author: 姚智胜
 * Version: V1.0版本
 * Description: mvp架构中单独的mvpActivity
 * Date: 2017/6/19
 * Email: 541567595@qq.com
 */

public abstract class YzsBaseMvpActivity<T extends BasePresenter, E extends BaseModel> extends YzsBaseActivity {

    public T mPresenter;
    public E mModel;
    public Context mContext;

    public Toolbar mToolbar;
    public TextView tv_title;
    public TextView tv_rightTitle;
    public ImageView iv_rightTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initImmersion();
        if (showToolBar()) {
            mToolbar = (Toolbar) findViewById(R.id.toolbar);
            if (null != mToolbar) {
                setSupportActionBar(mToolbar);
                getSupportActionBar().setDisplayShowTitleEnabled(false);
                initTitle();
            }
        }


        mContext = this;
        mPresenter = TUtil.getT(this, 0);
        mModel = TUtil.getT(this, 1);
        if (mPresenter != null) {
            mPresenter.mContext = this;
        }
        this.initPresenter();
        this.initView();
    }

    /**
     * 初始化toolbar
     */
    protected void initTitle() {
        if (mToolbar == null) {
            return;
        }
        tv_title = (TextView) mToolbar.findViewById(R.id.toolbar_title);
        tv_rightTitle = (TextView) mToolbar.findViewById(R.id.tv_toolbar_right);
        iv_rightTitle = (ImageView) mToolbar.findViewById(R.id.iv_toolbar_right);
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
        if (null != tv_title)
            tv_title.setText(string);
    }

    public void setTitle(int id) {
        if (null != tv_title)
            tv_title.setText(id);
    }


    /*********************子类实现*****************************/
    /**
     * 获取布局文件
     */
    public abstract int getLayoutId();

    /**
     * 简单页面无需mvp就不用管此方法即可,完美兼容各种实际场景的变通
     */
    public abstract void initPresenter();

    /**
     * 初始化view
     */
    public abstract void initView();

    /**
     * 初始化沉浸式
     */
    public abstract void initImmersion();

    /**
     * 显示返回按钮
     */
    public void showBackButton() {
        if (mToolbar != null) {
            mToolbar.setNavigationIcon(R.drawable.base_toolbar_back);
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }

    @Override
    public int getToolBarResId() {
        return R.layout.layout_common_toolbar;
    }

    @Override
    protected void onDestroy() {
        ImmersionBar.with(this).destroy();
        super.onDestroy();
    }
}
