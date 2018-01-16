package com.yzs.yzsbaseactivitylib.yzsbase;

import android.os.Bundle;

import com.yzs.yzsbaseactivitylib.R;
import com.yzs.yzsbaseactivitylib.basemvp.BaseModel;
import com.yzs.yzsbaseactivitylib.basemvp.BasePresenter;

import me.yokeyword.fragmentation.SupportFragment;
import me.yokeyword.fragmentation.anim.DefaultNoAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * Author: 姚智胜
 * Version: V1.0版本
 * Description:
 * Date: 2018/1/10
 * Email: 541567595@qq.com
 */

public abstract class YzsBaseSupportFragmentActivity<T extends BasePresenter, E extends BaseModel>
        extends YzsBaseActivity<T, E> {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        UnCeHandler.getInstance().init(this);
        if (null != setFragment()) {
            loadRootFragment(R.id.fl_container, setFragment());
        }

    }

    @Override
    public int getContentViewResId() {
        return R.layout.ac_base;
    }

    /**
     * 设置整个架构的第一个fragment
     *
     * @return
     */
    public abstract SupportFragment setFragment();


    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        // 设置默认Fragment动画  默认竖向(和安卓5.0以上的动画相同)
//        return super.onCreateFragmentAnimator();
        //无动画
        return new DefaultNoAnimator();
        // 设置横向(和安卓4.x动画相同)
//        return new DefaultHorizontalAnimator();
        // 设置自定义动画
//        return new FragmentAnimator(enter,exit,popEnter,popExit);
    }
}
