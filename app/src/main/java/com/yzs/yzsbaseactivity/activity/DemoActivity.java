package com.yzs.yzsbaseactivity.activity;

import android.os.Bundle;

import com.gyf.barlibrary.ImmersionBar;
import com.yzs.yzsbaseactivity.R;
import com.yzs.yzsbaseactivity.fragment.IndexFragment;
import com.yzs.yzsbaseactivitylib.yzsbase.YzsBaseSupportFragmentActivity;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Author: 姚智胜
 * Version: V1.0版本
 * Description:
 * Date: 2017/3/15
 */
public class DemoActivity extends YzsBaseSupportFragmentActivity {
    @Override
    public SupportFragment setFragment() {
        return IndexFragment.newInstance();
    }

    @Override
    public void initImmersion() {
        ImmersionBar.with(this).init();
    }

    @Override
    public void initView() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy();
    }


}
