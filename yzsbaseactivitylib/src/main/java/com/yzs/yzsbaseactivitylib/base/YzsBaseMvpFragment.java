package com.yzs.yzsbaseactivitylib.base;


import com.yzs.yzsbaseactivitylib.entity.BaseEventBusBean;
import com.yzs.yzsbaseactivitylib.util.TUtil;

/**
 * Author: 姚智胜
 * Version: V1.0版本
 * Description: 兼容mvc的mvp架构普通fragment
 * Date: 2017/6/13
 * Email: 541567595@qq.com
 */

public abstract class YzsBaseMvpFragment<T extends BasePresenter, E extends BaseModel> extends YzsBaseFragment {
    public T mPresenter;
    public E mModel;

    private boolean isMvp = true;

    @Override
    public void mvpCreate() {
        if (isMvp) {
            mPresenter = TUtil.getT(this, 0);
            mModel = TUtil.getT(this, 1);
        }

        if (mPresenter != null) {
            mPresenter.mContext = this.getActivity();
        }

        initPresenter();
    }

    public void setMvp(boolean mvp) {
        isMvp = mvp;
    }

    /**
     * 是否是MVP架构,该方法是为了处理baseListNoMvp有其他泛型崩溃问题
     *
     * @return 是否是mvp架构
     */
    protected boolean isMvp() {
        return isMvp;
    }

    /**
     * 简单页面无需mvp就不用管此方法即可,完美兼容各种实际场景的变通
     */
    public abstract void initPresenter();


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null)
            mPresenter.onDestroy();
    }

    /**
     * 是否开启eventbus ，默认不开启
     *
     * @return false
     */
    @Override
    public boolean openEventBus() {
        return false;
    }
    /**
     * 是否开启ToolBar ，默认不开启
     *
     * @return false
     */
    @Override
    public boolean showToolBar() {
        return false;
    }

    /**
     * eventBus通用的接收器，如使用自己重写，如果想单独接收，请重写onEventMainThread方法并使用其他bean
     * @param event
     */
    @Override
    protected void EventBean(BaseEventBusBean event) {

    }

}

