package com.yzs.yzsbaseactivitylib.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.loadmore.LoadMoreView;
import com.yzs.yzsbaseactivitylib.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: 姚智胜
 * Version: V1.0版本
 * Description: 普通list的baseFragment
 * Date: 2016/12/15
 */
public abstract class YzsBaseListFragment<T> extends YzsBaseFragment implements BaseQuickAdapter.RequestLoadMoreListener {
    private static final String TAG = "YzsBaseListFragment";

    /**
     * 普通list布局
     */
    protected static final int LINEAR_LAYOUT_MANAGER = 0;
    /**
     * grid布局
     */
    protected static final int GRID_LAYOUT_MANAGER = 1;
    /**
     * 瀑布流布局
     */
    protected static final int STAGGERED_GRID_LAYOUT_MANAGER = 2;


    /**
     * 默认为0单行布局
     */
    private int mListType = 0;
    /**
     * 排列方式默认垂直
     */
    private boolean mIsVertical = true;
    /**
     * grid布局与瀑布流布局默认行数
     */
    private int mSpanCount = 1;

    protected RecyclerView mRecyclerView;

    protected YzsListAdapter mAdapter;
    /**
     * 子布局id
     */
    private int layoutResId = -1;

    private LoadMoreView loadMoreView;
    /**
     * 每页数量  默认10
     */
    private int pageSize = 10;

    public int getPageSize() {
        return pageSize;
    }

    protected void initView(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.yzs_base_list);
        initItemLayout();
        chooseListType(mListType, mIsVertical);
        if (-1 == layoutResId) {
            throw new RuntimeException("layoutResId is null!");
        }
        mAdapter = new YzsListAdapter(layoutResId, new ArrayList<T>());
        initSetting();
        mAdapter.setLoadMoreView(getLoadMoreView());
        mRecyclerView.setAdapter(mAdapter);

    }

    /**
     * 设置每页数量（开启loading才会使用）
     *
     * @param pageSize 每页数量
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 设置load界面的多种状态 没有更多、loading、加载失败三种三种状态
     *
     * @param loadMoreView load界面多状态布局
     */
    protected void setLoadMordTypeLayout(LoadMoreView loadMoreView) {
        this.loadMoreView = loadMoreView;
    }

    public LoadMoreView getLoadMoreView() {
        return loadMoreView == null ? new LoadMoreView() {
            @Override
            public int getLayoutId() {
                return R.layout.quick_view_load_more;
            }

            @Override
            protected int getLoadingViewId() {
                return R.id.load_more_loading_view;
            }

            @Override
            protected int getLoadFailViewId() {
                return R.id.load_more_load_fail_view;
            }

            @Override
            protected int getLoadEndViewId() {
                return R.id.load_more_load_end_view;
            }
        } : loadMoreView;
    }


    /**
     * 设置子布局layout
     *
     * @param layoutResId 子布局layout
     */
    public void setLayoutResId(int layoutResId) {
        this.layoutResId = layoutResId;
    }

    /**
     * 初始化子布局
     */
    protected abstract void initItemLayout();

    /**
     * 初始化各种状态处理
     * 在这个方法里处理的是recyclerview的所有的初始化，
     * 包括对他的展示形式，是list或grid或瀑布流
     */
    protected abstract void initSetting();

    /**
     * 是否打开加载更多
     */
    protected void openLoadMore() {
        mAdapter.setOnLoadMoreListener(this, mRecyclerView);
    }

    /**
     * @param type       布局管理type
     * @param isVertical 是否是垂直的布局 ，true垂直布局，false横向布局
     */
    protected void setListType(int type, boolean isVertical) {
        mListType = type;
        mIsVertical = isVertical;
    }

    /**
     * 为grid样式和瀑布流设置横向或纵向数量
     *
     * @param spanCount 数量
     */
    protected void setSpanCount(int spanCount) {
        if (spanCount > 0)
            mSpanCount = spanCount;
    }

    /**
     * @param listType 选择布局种类
     */
    private void chooseListType(int listType, boolean isVertical) {
        switch (listType) {
            case LINEAR_LAYOUT_MANAGER:
                //设置布局管理器
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

                linearLayoutManager.setOrientation(isVertical ? LinearLayoutManager.VERTICAL : LinearLayoutManager.HORIZONTAL);

                mRecyclerView.setLayoutManager(linearLayoutManager);
                break;
            case GRID_LAYOUT_MANAGER:

                GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), mSpanCount);

                gridLayoutManager.setOrientation(isVertical ? GridLayoutManager.VERTICAL : GridLayoutManager.HORIZONTAL);

                mRecyclerView.setLayoutManager(gridLayoutManager);
                break;
            case STAGGERED_GRID_LAYOUT_MANAGER:
                //设置布局管理器
                StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager
                        (mSpanCount, isVertical ? StaggeredGridLayoutManager.VERTICAL : StaggeredGridLayoutManager.HORIZONTAL);

                mRecyclerView.setLayoutManager(staggeredGridLayoutManager);
                break;
            default:
                //设置布局管理器
                LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

                layoutManager.setOrientation(isVertical ? LinearLayoutManager.VERTICAL : LinearLayoutManager.HORIZONTAL);

                mRecyclerView.setLayoutManager(layoutManager);
                break;
        }
    }

    @Override
    public void onLoadMoreRequested() {
        loadingMoreLister();
    }

    /**
     * adapter的加载更多监听
     */
    protected abstract void loadingMoreLister();

    /**
     * adapter内的处理
     *
     * @param baseViewHolder BaseViewHolder
     * @param t              泛型T
     */
    protected abstract void MyHolder(BaseViewHolder baseViewHolder, T t);


    public class YzsListAdapter extends BaseQuickAdapter<T, BaseViewHolder> {

        public YzsListAdapter(int layoutResId, List<T> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder baseViewHolder, T t) {
            MyHolder(baseViewHolder, t);
        }
    }

}
