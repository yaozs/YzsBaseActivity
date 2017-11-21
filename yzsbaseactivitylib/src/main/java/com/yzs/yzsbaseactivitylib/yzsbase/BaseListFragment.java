package com.yzs.yzsbaseactivitylib.yzsbase;

import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;
import com.yzs.yzsbaseactivitylib.R;
import com.yzs.yzsbaseactivitylib.annotations.ListType;
import com.yzs.yzsbaseactivitylib.basemvp.BaseModel;
import com.yzs.yzsbaseactivitylib.basemvp.BasePresenter;
import com.yzs.yzsbaseactivitylib.entity.BaseListType;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: 姚智胜
 * Version: V1.0版本
 * Description:列表类fragment（使用mvp架构）
 * Date: 2017/6/13
 * Email: 541567595@qq.com
 */

public abstract class BaseListFragment<T extends BasePresenter, E extends BaseModel, D> extends
        BaseFragment<T, E> {

    private int mListType = 0;//默认为0单行布局
    private boolean mIsVertical = true;//排列方式默认垂直
    private int mSpanCount = 1;//grid布局与瀑布流布局默认行数
    protected RecyclerView mRecyclerView;
    protected YzsListAdapter mAdapter;//列表类界面实际使用的adapter
    protected RefreshLayout mRefreshLayout;//刷新加载控件
    private boolean isOpenRefresh = false;//是否开启刷新
    private boolean isOpenLoadMore = false;//是否开启加载更多
    private int mPage = 1;//页数角标
    private int mPageSize = 10;//每页数量

    public int getPage() {
        return mPage;
    }

    public void setPage(int page) {
        mPage = page;
    }

    public void setmPageSize(int mPageSize) {
        this.mPageSize = mPageSize;
    }

    public int getmPageSize() {
        return mPageSize;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.yzs_comment_list;
    }

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

    @Override
    protected void initView(View view) {
        if (0 == getLayoutResource()) {
            throw new RuntimeException("layoutResId is null!");
        }
        judgeViewIsNull();
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
    protected void setListType(@ListType int type, boolean isVertical) {
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

    /**
     * 初始化子布局
     */
    protected  void refreshListener(){

    };

    /**
     * 初始化子布局
     */
    protected  void loadMoreListener(){

    };


    public class YzsListAdapter extends BaseQuickAdapter<D, BaseViewHolder> {

        public YzsListAdapter(int layoutResId, List<D> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder baseViewHolder, D t) {
            MyHolder(baseViewHolder, t);
        }
    }


    /**
     * 设置adapter的多重布局res
     *
     * @param adapter
     */
    protected abstract void setRcTypeRes(BaseMoreTypeListFragment.YzsListAdapter adapter);

    /**
     * 刷新成功
     */
    public void okRefresh() {
        judgeViewIsNull();
        if (mAdapter != null) {
            mPage = 2;
            mRefreshLayout.finishRefresh();
            mRefreshLayout.setLoadmoreFinished(false);//恢复上拉状态
            Logger.d("refresh_complete");
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
     * 加载更多成功
     *
     * @param isHashNext
     */
    public void okLoadMore(boolean isHashNext) {
        judgeViewIsNull();
        mPage++;
        mRefreshLayout.finishLoadmore();

        if (!isHashNext) {
            //false为显示加载结束，true为不显示
            mRefreshLayout.setLoadmoreFinished(true);//设置之后，将不会再触发加载事件
        }
    }

    /**
     * 加载更多失败
     */
    public void failLoadMore() {
        judgeViewIsNull();
        mRefreshLayout.finishLoadmore();
    }

    @Override
    public void onDestroy() {
        mAdapter = null;
        mRefreshLayout = null;
        super.onDestroy();
    }


    /**
     * 处理mRefreshLayout与mRecyclerView的未知空情况bug
     */
    private void judgeViewIsNull() {
        if (mRefreshLayout == null) {
            mRefreshLayout = (RefreshLayout) rootView.findViewById(R.id.refreshLayout);
        }
        if (mRecyclerView == null) {
            mRecyclerView = (RecyclerView) rootView.findViewById(R.id.yzs_base_list);
        }
        if (mAdapter == null) {
            mAdapter = new YzsListAdapter(initItemLayout(), new ArrayList<D>());
            initSetting();
            chooseListType(mListType, mIsVertical);
            if (isOpenRefresh && mRefreshLayout != null) {
                if (isOpenLoadMore) {
                    mRefreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
                        @Override
                        public void onLoadmore(RefreshLayout refreshlayout) {
                            judgeViewIsNull();
                            if (mRefreshLayout != null)
                                loadMoreListener();
                        }

                        @Override
                        public void onRefresh(RefreshLayout refreshlayout) {
                            judgeViewIsNull();
                            refreshListener();
                        }
                    });
                } else {
                    mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
                        @Override
                        public void onRefresh(RefreshLayout refreshlayout) {
                            judgeViewIsNull();
                            refreshListener();
                        }
                    });
                }
            }
        }
    }

    /**
     * 进入自动加载
     */
    protected void autoRefresh() {
        mRefreshLayout.autoRefresh();
    }

    /**
     * 提供改变显示方法（该方法用于布局显示后动态改变显示方式）
     */
    protected void changeShowType(@ListType int listType, boolean isVertical) {
        chooseListType(listType, isVertical);
    }
}
