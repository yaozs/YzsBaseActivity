package com.yzs.yzsbaseactivity.activity;

import android.os.Bundle;

import com.gyf.barlibrary.ImmersionBar;
import com.yzs.yzsbaseactivity.R;
import com.yzs.yzsbaseactivity.fragment.ListMvpDemoFragment;
import com.yzs.yzsbaseactivitylib.base.YzsBaseSupportFragmentActivity;
import com.yzs.yzsbaseactivitylib.entity.BaseEventBusBean;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Author: 姚智胜
 * Version: V1.0版本
 * Description:
 * Date: 2017/7/4
 * Email: 541567595@qq.com
 */

public class SecondActivity  extends YzsBaseSupportFragmentActivity {
    @Override
    public SupportFragment setFragment() {
        ImmersionBar.with(this).init();
        return ListMvpDemoFragment.newInstance();
    }

    @Override
    public boolean showToolBar() {
        return false;
    }

    @Override
    public boolean openEventBus() {
        return false;
    }

    @Override
    public int getToolBarResId() {
        return R.layout.layout_common_toolbar;
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy();
    }

    @Override
    protected void EventBean(BaseEventBusBean event) {

    }

}