package com.yzs.yzsbaseactivitylib.base;

import android.os.Bundle;
import android.util.Log;

import com.yzs.yzsbaseactivitylib.R;

import me.yokeyword.fragmentation.SupportFragment;
import me.yokeyword.fragmentation.anim.FragmentAnimator;
import me.yokeyword.fragmentation.helper.FragmentLifecycleCallbacks;

/**
 * Author: 姚智胜
 * Version: V1.0版本
 * Description: mvp架构中仅作为fragment容器的baseActivity，
 * Date: 2017/6/13
 * Email: 541567595@qq.com
 */

public abstract class YzsBaseSupportFragmentActivity extends YzsBaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null && null != setFragment()) {
            loadRootFragment(R.id.fl_container, setFragment());
        }


        registerFragmentLifecycleCallbacks(new FragmentLifecycleCallbacks() {
            // 可以监听该Activity下的所有Fragment的18个 生命周期方法

            @Override
            public void onFragmentCreated(SupportFragment fragment, Bundle savedInstanceState) {
                Log.i("MainActivity", "onFragmentCreated--->" + fragment.getClass().getSimpleName());
            }
        });
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
        return super.onCreateFragmentAnimator();
        // 设置横向(和安卓4.x动画相同)
//        return new DefaultHorizontalAnimator();
        // 设置自定义动画
//        return new FragmentAnimator(enter,exit,popEnter,popExit);
    }

}
