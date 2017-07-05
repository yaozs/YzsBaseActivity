package com.yzs.yzsbaseactivity.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.yzs.yzsbaseactivity.R;
import com.yzs.yzsbaseactivity.activity.SecondActivity;
import com.yzs.yzsbaseactivity.base.BaseFragment;
import com.yzs.yzsbaseactivitylib.entity.BaseEventBusBean;
import com.yzs.yzsbaseactivitylib.util.ActivityGoUtils;


/**
 * Author: 姚智胜
 * Version: V1.0版本
 * Description:
 * Date: 2016/12/15.
 */
public class HomeFragment extends BaseFragment {
    private static final String TAG = "HomeFragment";

    private TextView textView;

    public static HomeFragment newInstance() {
        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getLayoutRes() {
        return R.layout.fg_home;
    }

    @Override
    protected void initView(View view) {
        textView = (TextView) view.findViewById(R.id.home);
    }


    @Override
    protected void EventBean(BaseEventBusBean event) {

    }


    @Override
    public boolean openEventBus() {
        return true;
    }

    @Override
    protected void initLogic() {
        setTitle("HomeFragment");
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityGoUtils.getInstance().readyGo(_mActivity, SecondActivity.class);
            }
        });
    }


    @Override
    protected void getBundleExtras(Bundle bundle) {

    }

    @Override
    protected void immersionInit() {
        ImmersionBar.with(this).statusBarView(R.id.yzs_view, rootView)
                .statusBarDarkFont(true, 0.2f)
                .statusBarColor(R.color.md_red_100)
                .navigationBarColor(R.color.md_red_100)
                .init();
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public boolean showToolBar() {
        return true;
    }
}
