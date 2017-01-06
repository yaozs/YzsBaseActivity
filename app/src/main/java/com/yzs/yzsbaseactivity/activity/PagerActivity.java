package com.yzs.yzsbaseactivity.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.yzs.yzsbaseactivity.R;
import com.yzs.yzsbaseactivity.fragment.MyFragment;
import com.yzs.yzsbaseactivitylib.activity.YzsBaseDynamicPagerActivity;
import com.yzs.yzsbaseactivitylib.entity.EventCenter;
import com.yzs.yzsbaseactivitylib.fragment.YzsBaseFragment;

/**
 * Author: 姚智胜
 * Version: V1.0版本
 * Description:
 * Date: 2017/1/12
 */
public class PagerActivity extends YzsBaseDynamicPagerActivity {
    private static final String TAG = "PagerActivity";
    private TextView add;

    @Override
    protected void initView() {
        super.initView();
        add = (TextView) findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle;
                Log.e("yzs1","index" + tabLayout.getCurrentTab());
                mTitles.remove(0);
                YzsBaseFragment fragment = fragmentList.get(tabLayout.getCurrentTab());
                fragmentList.clear();
                int num = 0;
                for (int i = 0; i < mTitles.size(); i++) {
                    if (i == tabLayout.getCurrentTab()-1) {
                        Log.e("yzs1","yzs1"+i);
                        num = i;
                        fragmentList.add(fragment);
                    }else {
                        bundle = new Bundle();
                        bundle.putInt("num",i);
                        fragmentList.add(MyFragment.newInstance(bundle));
                    }
                }

                pagerAdapter.notifyDataSetChanged();
                tabLayout.notifyDataSetChanged();
                if (num==0) {
                    viewPager.setCurrentItem(0);
                } else {
                    viewPager.setCurrentItem(num);
                }

            }
        });
    }

    @Override
    protected void initTab() {
        Bundle bundle;
        for (int i = 0; i < 5; i++) {
            bundle = new Bundle();
            bundle.putInt("num",i);
            fragmentList.add(MyFragment.newInstance(bundle));
        }

    }

    @Override
    protected void initContentView(Bundle bundle) {
        setContentView(R.layout.ac_pager_demo);
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected void onEventComing(EventCenter center) {

    }

    @Override
    public void onClick(View v) {

    }
}
