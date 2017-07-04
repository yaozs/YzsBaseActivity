package com.yzs.yzsbaseactivitylib.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.orhanobut.logger.Logger;
import com.yzs.yzsbaseactivitylib.R;
import com.yzs.yzsbaseactivitylib.entity.TabEntity;

import java.util.ArrayList;

/**
 * Author: 姚智胜
 * Version: V1.0版本
 * Description: 首页的baseActivity类型 提供下方导航条用户只需放入图标与fragment，也支持固定title的导航条
 * Date: 2017/7/3
 * Email: 541567595@qq.com
 */

public abstract class YzsBaseHomeFragment<T extends BasePresenter, E extends BaseModel> extends YzsBaseMvpFragment<T, E> {

    private static final String TAG = "YzsBaseHomeActivity";
    /**
     * title文字部分
     */
    private String[] mTitles;
    /**
     * 未选中图标数组
     */
    private int[] mIconUnSelectIds;
    /**
     * 选中图标数组
     */
    private int[] mIconSelectIds;
    /**
     * fragment集合
     */
    protected YzsBaseCommonFragment[] mFragments;
    /**
     * 导航条
     */
    protected CommonTabLayout mTabLayout;
    /**
     * 图标信息对象
     */
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    protected ViewPager mViewPager;

    protected FrameLayout mFrameLayout;

    private Bundle bundle;

    private int initChooseTab;
    /**
     * 是不是程序默认选中
     */
    private boolean isFirst = true;

    /**
     * 如果使用viewpager，初始化选中必须用该方法
     *
     * @param initChooseTab 选中position
     */
    public void setInitChooseTab(int initChooseTab) {
        this.initChooseTab = initChooseTab;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBundle(savedInstanceState);
    }


    @Override
    protected void initView(View view) {
        mTabLayout = (CommonTabLayout) view.findViewById(R.id.yzs_base_tabLayout);
        mViewPager = (ViewPager) view.findViewById(R.id.yzs_base_tabLayout_viewPager);
        mFrameLayout = (FrameLayout) view.findViewById(R.id.yzs_base_tabLayout_frameLayout);
        initTab();

        if (null == mFragments || mFragments.length == 0) {
            throw new RuntimeException("mFragments is null!");
        }

        initTabEntities();
        if (null == mTabLayout) {
            throw new RuntimeException("CommonTabLayout is null!");
        }

        if (null == mTitles || mTitles.length == 0) {
            mTabLayout.setTextsize(0);
        }

        if (null != mViewPager) {
            Log.e(TAG, "Choose_ViewPager");
            initViewpagerAdapter();
        } else {
            initFragments();
            Log.e(TAG, "Choose_frameLayout");
        }
        setTabSelect();
        if (null != mViewPager) {
            mViewPager.setCurrentItem(initChooseTab);
        } else {
            mTabLayout.setCurrentTab(initChooseTab);
        }


    }

    /**
     * 初始化图标图片文字fragment数据
     */
    private void initTabEntities() {
        if (null == mFragments || mFragments.length == 0 ) {
            throw new RuntimeException("mFragments is null");
        }
//        || mFragments.length != mIconSelectIds.length || mFragments.length != mIconUnSelectIds.length
        if (mIconSelectIds==null||mFragments.length != mIconSelectIds.length||mIconUnSelectIds==null
                ||mFragments.length != mIconUnSelectIds.length){
            Logger.d("Fragments and the number of ICONS do not meet");
            for (int i = 0; i < mFragments.length; i++) {
                mTabEntities.add(new TabEntity(mTitles == null ? "" : mTitles[i], 0, 0));
            }
            mTabLayout.setIconVisible(false);
            mTabLayout.setTabData(mTabEntities);
        }else {
            for (int i = 0; i < mFragments.length; i++) {
                mTabEntities.add(new TabEntity(mTitles == null ? "" : mTitles[i], mIconSelectIds[i], mIconUnSelectIds[i]));
            }
            mTabLayout.setTabData(mTabEntities);
        }


    }

    /**
     * 初始化Fragments
     */
    private void initFragments() {
        //加载mFragments
        if (getBundle() == null) {
            //加载mFragments
            loadMultipleRootFragment(R.id.yzs_base_tabLayout_frameLayout, initChooseTab, mFragments);
        } else {
            // 这里库已经做了Fragment恢复,所有不需要额外的处理了, 不会出现重叠问题
            for (int i = 0; i < mFragments.length; i++) {
                Log.e(TAG, "initFragments" + i);
                mFragments[i] = findFragment(mFragments[i].getClass());
            }
        }
    }

    /**
     * 初始化viewpager的adapter
     */
    private void initViewpagerAdapter() {
        mViewPager.setAdapter(new MyPagerAdapter(getChildFragmentManager()));
        mViewPager.setOffscreenPageLimit(mFragments.length - 1);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTabLayout.setCurrentTab(position);
            }

            /**
             * viewpager滑动状态判断，1为滑动状态，2为滑动完成，0为停止
             */
            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == 0) {
                    YzsBaseHomeFragment.this.onTabSelect(mViewPager.getCurrentItem());
                }

            }
        });

    }

    /**
     * 为mTabLayout设置监听
     */
    private void setTabSelect() {
        Log.e(TAG, "setTabSelect");
        mTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                if (null != mViewPager) {
                    mViewPager.setCurrentItem(position);
                } else {
                    int toDoHidden = -1;
                    for (int i = 0; i < mFragments.length; i++) {
                        if (!mFragments[i].isHidden()) {
                            toDoHidden = i;
                            Log.e(TAG, "查找显示中的fragment-------" + toDoHidden);
                        }
                    }
                    Log.e(TAG, "选中的fragment-------" + position);
                    Log.e(TAG, "确定显示中的fragment-------" + toDoHidden);

                    showHideFragment(mFragments[position], mFragments[toDoHidden]);
                }
                YzsBaseHomeFragment.this.onTabSelect(position);
            }

            @Override
            public void onTabReselect(int position) {

                Log.e(TAG, "再次选中项" + position);
                YzsBaseHomeFragment.this.onTabReselect(position);
            }
        });
    }

    /**
     * tab的选中回调
     */
    protected abstract void onTabSelect(int position);

    /**
     * tab的再次选中回调
     */
    protected abstract void onTabReselect(int position);


    /**
     * 设置TabLayout属性，所有关于TabLayout属性在这里设置
     */
    protected abstract void initTab();

    /**
     * 获取Fragment数组
     *
     * @return mFragments
     */
    public YzsBaseCommonFragment[] getmFragments() {
        return mFragments;
    }

    /**
     * 放入Fragment数组（必须继承YzsBaseFragment）
     *
     * @param mFragments
     */
    public void setmFragments(YzsBaseCommonFragment[] mFragments) {
        this.mFragments = mFragments;
    }

    /**
     * 获取选中图标数组
     *
     * @return mIconSelectIds
     */
    public int[] getmIconSelectIds() {
        return mIconSelectIds;
    }

    /**
     * 放入选中图标数组
     *
     * @param mIconSelectIds
     */
    public void setmIconSelectIds(int[] mIconSelectIds) {
        this.mIconSelectIds = mIconSelectIds;
    }

    /**
     * 获取未选中图标数组
     *
     * @return mIconUnSelectIds
     */
    public int[] getmIconUnSelectIds() {
        return mIconUnSelectIds;
    }

    /**
     * 放入未选中图标数组
     *
     * @param mIconUnSelectIds
     */
    public void setmIconUnSelectIds(int[] mIconUnSelectIds) {
        this.mIconUnSelectIds = mIconUnSelectIds;
    }

    /**
     * 获取mTitles数组
     *
     * @return mTitles
     */
    public String[] getmTitles() {
        return mTitles;
    }

    /**
     * 放入mTitles数组
     *
     * @param mTitles
     */
    public void setmTitles(String[] mTitles) {
        this.mTitles = mTitles;
    }

    public Bundle getBundle() {
        return bundle;
    }

    public void setBundle(Bundle bundle) {
        this.bundle = bundle;
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles == null ? "" : mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments[position];
        }
    }


}
