package com.yzs.yzsbaseactivity.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.gyf.barlibrary.ImmersionBar;
import com.orhanobut.logger.Logger;
import com.yzs.yzsbaseactivity.R;
import com.yzs.yzsbaseactivity.base.BaseMvpListFragment;
import com.yzs.yzsbaseactivity.contract.TestMvpListContract;
import com.yzs.yzsbaseactivity.layout.CustomLoadMoreView;
import com.yzs.yzsbaseactivity.model.TestModel;
import com.yzs.yzsbaseactivity.presenter.TestPresenter;
import com.yzs.yzsbaseactivitylib.entity.BaseListType;

import java.util.List;

/**
 * Author: 姚智胜
 * Version: V1.0版本
 * Description:
 * Date: 2017/7/4
 * Email: 541567595@qq.com
 */

public class ListMvpDemoFragment extends BaseMvpListFragment<TestPresenter, TestModel, String> implements
        TestMvpListContract.View {
    private static final String TAG = "ListMvpDemoFragment" ;

    private boolean isRefresh = false;
    private boolean isFail = false;

    public static ListMvpDemoFragment newInstance() {

        Bundle args = new Bundle();

        ListMvpDemoFragment fragment = new ListMvpDemoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
    }
    @Override
    protected void immersionInit() {
        super.immersionInit();
        ImmersionBar.with(this).statusBarView(R.id.yzs_view,rootView)
                .statusBarDarkFont(true, 0.2f)
                .statusBarColor(R.color.md_red_500)
                .navigationBarColor(R.color.md_red_50)
                .init();
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    protected void initLogic() {
        setTitle("ListMvpDemoFragment");
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                start(ListMvpDemoFragment.newInstance());
            }
        });
        autoRefresh();
    }

    @Override
    public void showData(List<String> list) {
        try {
            if (isRefresh) {
                okRefresh();
                mAdapter.setNewData(list);
            } else {
                if (list.size() == 10) {
                    if (isFail) {
                        isFail = false;
                        failLoadMore();
                    } else {
                        isFail = true;
                        okLoadMore(true);
                        mAdapter.addData(list);
                    }
                } else {
                    okLoadMore(false);
                    mAdapter.addData(list);
                }

            }
        }catch (Exception e){
            Logger.e(TAG,e);
        }


    }



    @Override
    protected int initItemLayout() {
        return R.layout.item_list_demo;
    }

    @Override
    protected void initSetting() {
        isOpenLoad(true, true);//是否开启加载和刷新
        setListType(BaseListType.LINEAR_LAYOUT_MANAGER, true);//设置展示方式
//        setSpanCount(4);//为grid样式和瀑布流设置横向或纵向数量
        setLoadMordTypeLayout(new CustomLoadMoreView());//可以不设置，有默认
    }

    @Override
    protected void MyHolder(BaseViewHolder baseViewHolder, String t) {
        baseViewHolder.setText(R.id.tv_item_test, t);
    }


    @Override
    protected void refreshListener() {
        isRefresh = true;
        setPage(1);
        mPresenter.getDataRequest(getPage());
    }

    @Override
    protected void loadMoreListener() {
        isRefresh = false;
        Log.e("page", "page" + getPage());
        mPresenter.getDataRequest(getPage());
    }

    @Override
    public void showLoading(String title) {

    }

    @Override
    public void stopLoading() {

    }

    @Override
    public void showErrorTip(String msg) {

    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fg_list_demo;
    }

    @Override
    protected void getBundleExtras(Bundle bundle) {

    }
}