package com.yzs.yzsbaseactivitylib.view;

import com.chad.library.adapter.base.loadmore.LoadMoreView;
import com.yzs.yzsbaseactivitylib.R;

/**
 * Author: 姚智胜
 * Version: V1.0版本
 * Description: 默认recyclerview加载各种状态布局
 * Date: 2017/6/16
 * Email: 541567595@qq.com
 */

public class RecyclerViewLoaderMoreView extends LoadMoreView {
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
