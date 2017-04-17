package com.yzs.yzsbaseactivity.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseViewHolder;
import com.yzs.yzsbaseactivity.R;
import com.yzs.yzsbaseactivity.bean.DemoListBean;
import com.yzs.yzsbaseactivity.layout.CustomLoadMoreView;
import com.yzs.yzsbaseactivitylib.entity.EventCenter;
import com.yzs.yzsbaseactivitylib.fragment.YzsBaseListFragment;
import com.yzs.yzsbaseactivitylib.line.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;


/**
 * Author: 姚智胜
 * Version: V1.0版本
 * Description: ListFragment的demo（与activity几乎同样用法）
 * Date: 2016/12/15.
 */
public class MsgFragment extends YzsBaseListFragment<DemoListBean> implements SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = "MsgFragment";

    private SwipeRefreshLayout refreshLayout;
    /**
     * 所有数据数量
     */
    private static final int TOTAL_COUNTER = 36;

    private int mCurrentCounter = 0;

    private boolean isErr;
    private boolean mLoadMoreEndGone = false;

    public static MsgFragment newInstance(Bundle bundle) {
        MsgFragment fragment = new MsgFragment();
        if (null != bundle) {
            fragment.setArguments(bundle);
        }
        return fragment;
    }

    @Override
    protected void initItemLayout() {
        setLayoutResId(R.layout.item_list_demo);
    }

    @Override
    protected void initSetting() {
        setListType(LINEAR_LAYOUT_MANAGER, true);
        //这个方法不设置就会使用默认值
        setLoadMordTypeLayout(new CustomLoadMoreView());
        setPageSize(10);
        openLoadMore();
    }

    @Override
    protected void loadingMoreLister() {
        refreshLayout.setEnabled(false);
        //这是模拟数据逻辑为了展示各种状态
        if (mAdapter.getData().size() < getPageSize()) {
            mAdapter.loadMoreEnd(true);
        } else {
            if (mCurrentCounter >= TOTAL_COUNTER) {
                mAdapter.loadMoreEnd(mLoadMoreEndGone);//true is gone,false is visible
            } else {
                if (isErr) {
                    mAdapter.addData(addData());
                    mCurrentCounter = mAdapter.getData().size();
                    mAdapter.loadMoreComplete();
                } else {
                    isErr = true;
                    Toast.makeText(getActivity(), "加载失败", Toast.LENGTH_LONG).show();
                    mAdapter.loadMoreFail();

                }
            }
            refreshLayout.setEnabled(true);
        }
    }

    @Override
    protected View initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_msg, container, false);
        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh_fg_msg);
        refreshLayout.setOnRefreshListener(this);
        return view;
    }

    @Override
    protected void initLogic() {
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), 1));
        mAdapter.addData(addData());
    }


    @Override
    protected void getBundleExtras(Bundle bundle) {

    }

    @Override
    protected void onEventComing(EventCenter center) {

    }


    @Override
    protected void MyHolder(BaseViewHolder baseViewHolder, DemoListBean demoListBean) {
        baseViewHolder.setText(R.id.tv_item_list_demo, demoListBean.getTitle());
        ImageView iv = baseViewHolder.getView(R.id.iv_item_list_demo);
        iv.setImageResource(R.mipmap.icon);
    }

    @Override
    public void onRefresh() {
        mAdapter.setEnableLoadMore(false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mAdapter.setNewData(addData());
                isErr = false;
                mCurrentCounter = getPageSize();
                refreshLayout.setRefreshing(false);
                mAdapter.setEnableLoadMore(true);
            }
        }, 1000);
    }

    private List<DemoListBean> addData() {
        List<DemoListBean> list = new ArrayList<>();
        DemoListBean bean;
        for (int i = 0; i < getPageSize(); i++) {
            bean = new DemoListBean();
            bean.setTitle("消息--------" + i);
            list.add(bean);
        }

        return list;
    }
}
