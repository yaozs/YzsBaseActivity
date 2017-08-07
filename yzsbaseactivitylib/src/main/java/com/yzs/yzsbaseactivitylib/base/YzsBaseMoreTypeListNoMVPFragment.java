package com.yzs.yzsbaseactivitylib.base;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Author: 姚智胜
 * Version: V1.0版本
 * Description: 列表类多重子布局fragment（不使用mvp架构）
 * Date: 2017/6/13
 * Email: 541567595@qq.com
 */

public abstract class YzsBaseMoreTypeListNoMVPFragment<T extends MultiItemEntity>
        extends YzsBaseMoreTypeListFragment<BasePresenter, BaseModel, T> {

    @Override
    protected int getLayoutResource() {
        setMvp(false);
        return getLayoutRes();
    }

    @Override
    public void initPresenter() {

    }

}
