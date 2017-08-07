package com.yzs.yzsbaseactivity.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.gyf.barlibrary.ImmersionBar;
import com.yzs.yzsbaseactivity.R;
import com.yzs.yzsbaseactivity.base.BaseFragment;
import com.yzs.yzsbaseactivitylib.entity.BaseEventBusBean;


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
    protected void immersionInit() {
        super.immersionInit();
        Log.e("222222222222", "11111111111");
        ImmersionBar.with(this).statusBarView(R.id.yzs_view,rootView)
                .statusBarDarkFont(true, 0.2f)
                .statusBarColor(R.color.md_yellow_200)
                .navigationBarColor(R.color.md_yellow_200)
                .init();
    }

    @Override
    protected void initLogic() {
        setTitle("MyFragment");
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public boolean openEventBus() {
        return false;
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
    protected void EventBean(BaseEventBusBean event) {

    }

    @Override
    protected void getBundleExtras(Bundle bundle) {

    }

}
