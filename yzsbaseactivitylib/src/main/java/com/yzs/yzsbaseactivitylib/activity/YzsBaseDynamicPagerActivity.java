package com.yzs.yzsbaseactivitylib.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.flyco.tablayout.SlidingTabLayout;
import com.yzs.yzsbaseactivitylib.R;
import com.yzs.yzsbaseactivitylib.fragment.YzsBaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: 姚智胜
 * Version: V1.0版本
 * Description: 仿今日头条的可动态添加删除viewpager页面的baseActivity
 * Date: 2017/1/2
 */
public abstract class YzsBaseDynamicPagerActivity extends YzsBaseActivity implements View.OnClickListener {
    private static final String TAG = "YzsBaseDynamicPagerActivity";

    protected List<YzsBaseFragment> fragmentList;
    protected SlidingTabLayout tabLayout;
    protected YzsViewPagerAdapter pagerAdapter;
    protected ViewPager viewPager;
    protected ImageView change;
    protected List<String> mTitles = new ArrayList<>();

    @Override
    protected void initView() {
        for (int i = 0; i < 5; i++) {
            mTitles.add("第" + (i + 1) + "界面");
        }

//        ;{
//            "热门", "iOS", "Android"
//                    , "前端", "后端"
//        };

        fragmentList = new ArrayList<>();
        tabLayout = (SlidingTabLayout) findViewById(R.id.yzs_base_pager_tabLayout);
        viewPager = (ViewPager) findViewById(R.id.yzs_base_pager_viewpager);
        change = (ImageView) findViewById(R.id.yzs_base_pager_change);
        if (change != null) {
            change.setOnClickListener(this);
        }
        initTab();
        pagerAdapter = new YzsViewPagerAdapter(getSupportFragmentManager(), fragmentList, mTitles);
        viewPager.setAdapter(pagerAdapter);
        if (viewPager == null) {
            Log.e(TAG, "viewpager==null");
        }
        if (pagerAdapter == null) {
            Log.e(TAG, "pagerAdapter==null");
        }
        tabLayout.setViewPager(viewPager);
        pagerAdapter.notifyDataSetChanged();
    }

    /**
     * 初始化tab信息
     */
    protected abstract void initTab();


    @Override
    protected void initLogic() {

    }

    public class YzsViewPagerAdapter extends FragmentStatePagerAdapter {
        private List<YzsBaseFragment> list;
        private List<String> tiltes;

        public YzsViewPagerAdapter(FragmentManager fm, List<YzsBaseFragment> list, List<String> tiltes) {
            super(fm);
            this.list = list;
            this.tiltes = tiltes;
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Log.e("getPageTitle", "getPageTitle" + tiltes.size());
            Log.e("position", "position" + position);

            return tiltes.get(position);
        }

        @Override
        public int getCount() {
            return list == null ? 0 : list.size();
        }

        @Override
        public int getItemPosition(Object object) {
            return PagerAdapter.POSITION_NONE;
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            Object obj = super.instantiateItem(container, position);
            return obj;
        }
    }

}
