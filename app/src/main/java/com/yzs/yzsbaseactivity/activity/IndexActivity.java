package com.yzs.yzsbaseactivity.activity;

import android.graphics.Color;
import android.os.Bundle;

import com.flyco.tablayout.widget.MsgView;
import com.yzs.yzsbaseactivity.R;
import com.yzs.yzsbaseactivity.fragment.HomeFragment;
import com.yzs.yzsbaseactivity.fragment.MoreFragment;
import com.yzs.yzsbaseactivity.fragment.MsgFragment;
import com.yzs.yzsbaseactivity.fragment.PersonFragment;
import com.yzs.yzsbaseactivitylib.activity.YzsBaseHomeActivity;
import com.yzs.yzsbaseactivitylib.entity.EventCenter;
import com.yzs.yzsbaseactivitylib.fragment.YzsBaseFragment;

/**
 * Author: 姚智胜
 * Version: V1.0版本
 * Description: 首页demo
 * Date: 2017/3/5
 */
public class IndexActivity extends YzsBaseHomeActivity {
    private static final String TAG = "IndexActivity";


    private String[] mTitles = {"首页", "消息", "联系人", "更多"};

    private int[] mIconUnselectIds = {
            R.drawable.tab_home_unselect, R.drawable.tab_speech_unselect,
            R.drawable.tab_contact_unselect, R.drawable.tab_more_unselect};
    private int[] mIconSelectIds = {
            R.drawable.tab_home_select, R.drawable.tab_speech_select,
            R.drawable.tab_contact_select, R.drawable.tab_more_select};

    @Override
    protected void onTabSelect(int position) {
        mTabLayout.hideMsg(position);
    }

    @Override
    protected void onTabReselect(int position) {
        mTabLayout.hideMsg(position);
    }

    @Override
    protected void initTab() {
        setmFragments(new YzsBaseFragment[]{new HomeFragment(), new MsgFragment(),
                new PersonFragment(), new MoreFragment()});
        setmIconSelectIds(mIconSelectIds);
        setmIconUnSelectIds(mIconUnselectIds);
        setmTitles(mTitles);
        setInitChooseTab(0);
    }

    @Override
    protected void initContentView(Bundle bundle) {
        setContentView(R.layout.ac_index);
    }

    @Override
    protected void initLogic() {
//        除了初始化方法，其他情况下改变选中position使用该方法
//        mTabLayout.setCurrentTab(1);

        //设置未读消息红点
        mTabLayout.showDot(1);
        //设置未读消息背景
        mTabLayout.showMsg(0, 5);
        //设置自定义颜色的msg
        mTabLayout.setMsgMargin(3, 0, 5);
        MsgView rtv_2_3 = mTabLayout.getMsgView(3);
        if (rtv_2_3 != null) {
            rtv_2_3.setBackgroundColor(Color.parseColor("#6D8FB0"));
        }


    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected void onEventComing(EventCenter center) {

    }
}
