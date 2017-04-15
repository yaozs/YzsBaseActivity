package com.yzs.yzsbaseactivity.layout;


import com.chad.library.adapter.base.loadmore.LoadMoreView;
import com.yzs.yzsbaseactivity.R;

/**
 * Author 姚智胜
 * Version V1.0版本
 * Description: recyclerview的load状态各种布局
 * Date: 2017/3/28
 */

public final class CustomLoadMoreView extends LoadMoreView {

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
}
