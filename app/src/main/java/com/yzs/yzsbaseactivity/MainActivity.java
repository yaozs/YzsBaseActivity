package com.yzs.yzsbaseactivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.yzs.yzsbaseactivitylib.activity.YzsBaseActivity;
import com.yzs.yzsbaseactivitylib.entity.EventCenter;
import com.yzs.yzsbaseactivitylib.fragment.YzsBaseFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends YzsBaseActivity {

    private ViewPager viewPager;
    private SlidingTabLayout tabLayout;
    private TextView add;
    private TextView del;
    private List<YzsBaseFragment> fragments = new ArrayList<>();

    private final String[] mTitles = {
            "热门", "iOS", "Android"
            , "前端", "后端", "设计", "工具资源"
    };

    @Override
    protected void initContentView(Bundle bundle) {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initView() {
        viewPager = (ViewPager) findViewById(R.id.my_viewPager);
        tabLayout = (SlidingTabLayout) findViewById(R.id.tab_layout);
        add = (TextView) findViewById(R.id.iv_add);
        del = (TextView) findViewById(R.id.iv_del);
        for (int i = 0; i < 7; i++) {
            MyFragment m = new MyFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("num", i);
            m.setArguments(bundle);
            fragments.add(m);
        }

    }

    @Override
    protected void initLogic() {
        NewsFragmentPagerAdapter mAdapetr = new NewsFragmentPagerAdapter(getSupportFragmentManager(), fragments);
//		mViewPager.setOffscreenPageLimit(0);
        viewPager.setAdapter(mAdapetr);
        viewPager.addOnPageChangeListener(pageListener);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        tabLayout.setViewPager(viewPager);
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected void onEventComing(EventCenter center) {

    }

    /**
     * ViewPager切换监听方法
     */
    public ViewPager.OnPageChangeListener pageListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageSelected(int position) {
            // TODO Auto-generated method stub
//            viewPager.setCurrentItem(position);
        }
    };


    public class NewsFragmentPagerAdapter extends FragmentPagerAdapter {
        private List<YzsBaseFragment> fragments;
        private FragmentManager fm;


        public NewsFragmentPagerAdapter(FragmentManager fm, List<YzsBaseFragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            super.destroyItem(container, position, object);
        }
    }
}
