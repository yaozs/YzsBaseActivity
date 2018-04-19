package com.yzs.yzsbaseactivity.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.gyf.barlibrary.ImmersionBar;
import com.yzs.yzsbaseactivity.R;
import com.yzs.yzsbaseactivity.base.BaseFragment;


/**
 * Author: 姚智胜
 * Version: V1.0版本
 * Description:
 * Date: 2016/12/28.
 */
public class MyFragment extends BaseFragment {

    public static MyFragment newInstance() {

        Bundle args = new Bundle();
        MyFragment fragment = new MyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void immersionInit(ImmersionBar mImmersionBar) {
        mImmersionBar
                .statusBarView(R.id.yzs_view,rootView)
                .statusBarColor(R.color.md_blue_300)
                .navigationBarColor(R.color.md_blue_300)
                .init();
    }

    @Override
    protected void initLogic() {

    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.item_list_demo;
    }

    @Override
    protected void initView(View rootView) {

    }
    @Override
    protected boolean immersionEnabled() {
        return true;
    }

}
