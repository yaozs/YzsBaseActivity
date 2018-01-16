package com.yzs.yzsbaseactivity.fragment;

import android.os.Bundle;

import com.yzs.yzsbaseactivity.R;
import com.yzs.yzsbaseactivitylib.yzsbase.YzsBaseFragment;
import com.yzs.yzsbaseactivitylib.yzsbase.YzsBaseHomeFragment;

/**
 * Author: 姚智胜
 * Version: V1.0版本
 * Description: 首页demo
 * Date: 2017/3/5
 */
public class IndexFragment extends YzsBaseHomeFragment {


    public static IndexFragment newInstance() {

        Bundle args = new Bundle();
        IndexFragment fragment = new IndexFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private String[] mTitles = {"首页", "消息", "联系人", "更多"};

    private int[] mIconUnselectIds = {
            R.drawable.tab_home_unselect, R.drawable.tab_speech_unselect,
            R.drawable.tab_contact_unselect, R.drawable.tab_more_unselect};
    private int[] mIconSelectIds = {
            R.drawable.tab_home_select, R.drawable.tab_speech_select,
            R.drawable.tab_contact_select, R.drawable.tab_more_select};

    @Override
    protected void initLogic() {

    }

    @Override
    protected void immersionInit() {

    }

    @Override
    public void initPresenter() {

    }

    @Override
    public boolean showToolBar() {
        return false;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fg_index;
    }

    @Override
    protected void getBundleExtras(Bundle bundle) {

    }

    @Override
    protected void onTabSelect(int position) {
        mTabLayout.hideMsg(position);
    }

    @Override
    protected void onTabReselect(int position) {

    }

    @Override
    protected void initTab() {
        setmFragments(new YzsBaseFragment[]{HomeFragment.newInstance(),WebFragment.newInstance(),
              MsgFragment.newInstance(),MyFragment.newInstance()});

        //如果不想显示图标，最简便的方法就是直接不调用这两个方法
        // setmIconSelectIds(mIconSelectIds);
        // setmIconUnSelectIds(mIconUnselectIds);

        setmTitles(mTitles);
        setInitChooseTab(0);
    }


    @Override
    public int getToolBarResId() {
        return super.getToolBarResId();
    }
}
