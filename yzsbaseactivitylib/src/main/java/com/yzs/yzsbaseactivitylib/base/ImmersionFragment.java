package com.yzs.yzsbaseactivitylib.base;

/**
 * Author: 姚智胜
 * Version: V1.0版本
 * Description: 沉浸式fragment处理
 * Date: 2017/6/19
 * Email: 541567595@qq.com
 */

public abstract class ImmersionFragment extends YzsBaseCommonFragment {
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if ((isVisibleToUser && isResumed())) {
            onResume();
        }
    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        if (immersionEnabled()){
            immersionInit();
        }
    }

    /**
     * 当前页面Fragment支持沉浸式初始化。子类可以重写返回false，设置不支持沉浸式初始化
     * Immersion bar enabled boolean.
     *
     * @return the boolean
     */
    protected boolean immersionEnabled() {
        return true;
    }

    protected abstract void immersionInit();
}
