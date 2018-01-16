package com.yzs.yzsbaseactivity.activity;

import android.os.Bundle;

import com.gyf.barlibrary.ImmersionBar;
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
        ImmersionBar.with(this).init();
        return IndexFragment.newInstance();
    }

    @Override
    public void initImmersion() {

    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {

    }


//    @Override
//    public SupportFragment setFragment() {
//        ImmersionBar.with(this).init();
//        return IndexFragment.newInstance();
//    }
//
//    @Override
//    public boolean showToolBar() {
//        return false;
//    }
//
//    @Override
//    public boolean openEventBus() {
//        return false;
//    }
//
//    @Override
//    public int getToolBarResId() {
//        return R.layout.layout_common_toolbar;
//    }
//
//    @Override
//    protected void getBundleExtras(Bundle extras) {
//
//    }
//
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy();
    }
//
//    @Override
//    protected void EventBean(BaseEventBusBean event) {
//
//    }

}
