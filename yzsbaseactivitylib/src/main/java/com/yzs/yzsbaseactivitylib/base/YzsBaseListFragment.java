package com.yzs.yzsbaseactivitylib.base;

import android.os.Handler;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.loadmore.LoadMoreView;
import com.yzs.yzsbaseactivitylib.R;
import com.yzs.yzsbaseactivitylib.entity.BaseListType;
import com.yzs.yzsbaseactivitylib.okload.IOkLoad;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: 姚智胜
 * Version: V1.0版本
 * Description:列表类fragment（使用mvp架构）
 * Date: 2017/6/13
 * Email: 541567595@qq.com
 */

public abstract class YzsBaseListFragment<T extends BasePresenter, E extends BaseModel, D> extends
        YzsBaseMvpFragment<T, E> implements SwipeRefreshLayout.OnRefreshListener,
        BaseQuickAdapter.RequestLoadMoreListener, IOkLoad {

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

    private LoadMoreView loadMoreView;

    protected SwipeRefreshLayout mRefreshLayout;

//    protected IOkLoad iOkLoad;

    private boolean isOpenRefresh = false;

    private boolean isOpenLoadMore = false;

    private int mPage = 1;
    //    public void setiOkLoad(IOkLoad iOkLoad) {
//        this.iOkLoad = iOkLoad;
//    }
    protected View emptyView;

    /**
     * 是否开启刷新和加载更多，默认不开启
     *
     * @param isOpenRefresh
     * @param isOpenLoadMore
     */
    public void isOpenLoad(boolean isOpenRefresh, boolean isOpenLoadMore) {
        this.isOpenRefresh = isOpenRefresh;
        this.isOpenLoadMore = isOpenLoadMore;
    }

    private int mPageSize = 10;

    public int getmPageSize() {
        return mPageSize;
    }
    public void setmPageSize(int mPageSize) {
        this.mPageSize = mPageSize;
    }
    @Override
    protected void initView(View view) {
        if (0 == getLayoutResource()) {
            throw new RuntimeException("layoutResId is null!");
        }
        mRecyclerView = (RecyclerView) view.findViewById(R.id.yzs_base_list);
        mRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.yzs_base_refreshLayout);
        mAdapter = new YzsListAdapter(initItemLayout(), new ArrayList<D>());
        initSetting();
        mAdapter.setLoadMoreView(getLoadMoreView());
        chooseListType(mListType, mIsVertical);

        if (isOpenRefresh) {
            mRefreshLayout.setOnRefreshListener(this);
        }
        if (isOpenLoadMore) {
            mAdapter.setOnLoadMoreListener(this, mRecyclerView);
        }
        initLogic();
    }

    /**
     * 逻辑处理
     */
    protected abstract void initLogic();

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
     * 初始化子布局
     */
    protected abstract
    @LayoutRes
    int initItemLayout();

    /**
     * 初始化各种状态处理
     * 在这个方法里处理的是recyclerview的所有的初始化，
     * 包括对他的展示形式，是list或grid或瀑布流
     */
    protected abstract void initSetting();

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
            case BaseListType.LINEAR_LAYOUT_MANAGER:
                //设置布局管理器
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

                linearLayoutManager.setOrientation(isVertical ? LinearLayoutManager.VERTICAL : LinearLayoutManager.HORIZONTAL);

                mRecyclerView.setLayoutManager(linearLayoutManager);
                break;
            case BaseListType.GRID_LAYOUT_MANAGER:

                GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), mSpanCount);

                gridLayoutManager.setOrientation(isVertical ? GridLayoutManager.VERTICAL : GridLayoutManager.HORIZONTAL);

                mRecyclerView.setLayoutManager(gridLayoutManager);
                break;
            case BaseListType.STAGGERED_GRID_LAYOUT_MANAGER:
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
        mRecyclerView.setAdapter(mAdapter);
    }

    /**
     * adapter内的处理
     *
     * @param baseViewHolder BaseViewHolder
     * @param t              泛型T
     */
    protected abstract void MyHolder(BaseViewHolder baseViewHolder, D t);

    @Override
    public void onRefresh() {
        mAdapter.setEnableLoadMore(false);
        refreshListener();
    }

    @Override
    public void onLoadMoreRequested() {
        mRefreshLayout.setEnabled(false);
        loadMoreListener();
    }

    /**
     * 初始化子布局
     */
    protected abstract void refreshListener();

    /**
     * 初始化子布局
     */
    protected abstract void loadMoreListener();


    public class YzsListAdapter extends BaseQuickAdapter<D, BaseViewHolder> {

        public YzsListAdapter(int layoutResId, List<D> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder baseViewHolder, D t) {
            MyHolder(baseViewHolder, t);
        }
    }

    @Override
    public void okRefresh() {
        judgeViewIsNull();
        if (mAdapter != null) {
            mPage = 2;
            mRefreshLayout.setRefreshing(false);
            mAdapter.setEnableLoadMore(true);
        }
    }

    @Override
    public void okLoadMore(boolean isHashNext) {
        judgeViewIsNull();
        if (mRefreshLayout != null) {
            mPage++;
            mRefreshLayout.setEnabled(true);
        }
        if (isHashNext) {
            mAdapter.loadMoreComplete();
        } else {
            //false为显示加载结束，true为不显示
            mAdapter.loadMoreEnd(false);
        }
    }

    @Override
    public void failLoadMore() {
        judgeViewIsNull();
        mRefreshLayout.setEnabled(true);
        mAdapter.loadMoreFail();
    }

    @Override
    public void onDestroy() {
        mAdapter = null;
        mRefreshLayout = null;
        super.onDestroy();
    }

    @Override
    public int getPage() {
        return mPage;
    }

    @Override
    public void setPage(int page) {
        mPage = page;
    }

    /**
     * 处理mRefreshLayout与mRecyclerView的未知空情况bug
     */
    private void judgeViewIsNull() {
        if (mRefreshLayout == null) {
            mRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.yzs_base_refreshLayout);
        }
        if (mRecyclerView == null) {
            mRecyclerView = (RecyclerView) rootView.findViewById(R.id.yzs_base_list);
        }
        if (mAdapter == null) {
            mAdapter = new YzsListAdapter(initItemLayout(), new ArrayList<D>());
            initSetting();
            mAdapter.setLoadMoreView(getLoadMoreView());
            chooseListType(mListType, mIsVertical);

            if (isOpenRefresh && mRefreshLayout != null) {
                mRefreshLayout.setOnRefreshListener(this);
            }
            if (isOpenLoadMore) {
                mAdapter.setOnLoadMoreListener(this, mRecyclerView);
            }
        }

    }

    public void autoListLoad(@Nullable List<D> tList, String empty_str, @DrawableRes int Empty_res) {
        tList = tList == null ? new ArrayList<D>() : tList;
        if (getPage() == 1) {
            okRefresh();
            mAdapter.setNewData(tList);
            if (tList.size() == 0) {
                mAdapter.setEmptyView(getEmptyView(empty_str, Empty_res));
            }
        } else {
            if (tList.size() == mPageSize) {
                okLoadMore(true);
            } else {
                okLoadMore(false);
            }
            mAdapter.addData(tList);
        }
    }

    public View getEmptyView(String str, @DrawableRes int drawRes) {
        if (emptyView != null) {
            return emptyView;
        }
        emptyView = LayoutInflater.from(_mActivity).inflate(R.layout.layout_empty_view, null, false);
        if (!TextUtils.isEmpty(str)) {
            TextView textView = (TextView) emptyView.findViewById(R.id.tv_text);
            ImageView imageView = (ImageView) emptyView.findViewById(R.id.iv_empty);
            imageView.setImageResource(drawRes);
            textView.setText(str);
        }
        return emptyView;
    }

    /**
     * 包含错误处理自动化，在接口返回错误处使用
     *
     * @param tList
     * @param empty_str
     * @param empty_res
     * @param isFail
     */
    public void autoListLoad(@Nullable List<D> tList, String empty_str, @DrawableRes int empty_res, boolean isFail) {
        if (isFail && getPage() != 1) {
            failLoadMore();
        } else {
            autoListLoad(tList, empty_str, empty_res);
        }
    }


    /**
     * 进入自动加载
     */
    protected void autoRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (null != mRefreshLayout) {
                    mRefreshLayout.setRefreshing(true);
                    onRefresh();
                }
            }
        }, 500);
    }

    /**
     * 提供改变显示方法（该方法用于布局显示后动态改变显示方式）
     */
    protected void changeShowType(int listType, boolean isVertical) {
        chooseListType(listType, isVertical);
    }
}
