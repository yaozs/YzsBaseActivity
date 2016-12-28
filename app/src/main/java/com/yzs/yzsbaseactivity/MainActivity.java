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

public class MainActivity extends YzsBaseActivity {

    private ViewPager viewPager;
    private SlidingTabLayout tabLayout;
    private TextView add;
    private TextView del;
    private ArrayList<YzsBaseFragment> fragments = new ArrayList<>();

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
        fragments.add(new MyFragment());
        fragments.add(new MyFragment());
        fragments.add(new MyFragment());
        fragments.add(new MyFragment());
        fragments.add(new MyFragment());
        fragments.add(new MyFragment());
        fragments.add(new MyFragment());

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
        private ArrayList<YzsBaseFragment> fragments;
        private FragmentManager fm;

        public NewsFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
            this.fm = fm;
        }

        public NewsFragmentPagerAdapter(FragmentManager fm,
                                        ArrayList<YzsBaseFragment> fragments) {
            super(fm);
            this.fm = fm;
            this.fragments = fragments;
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public YzsBaseFragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

        public void setFragments(ArrayList<YzsBaseFragment> fragments) {
            if (this.fragments != null) {
                FragmentTransaction ft = fm.beginTransaction();
                for (Fragment f : this.fragments) {
                    ft.remove(f);
                }
                ft.commit();
                ft = null;
                fm.executePendingTransactions();
            }
            this.fragments = fragments;
            notifyDataSetChanged();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            Object obj = super.instantiateItem(container, position);
            return obj;
        }

    }
}
