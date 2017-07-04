package com.yzs.yzsbaseactivitylib.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Author: 姚智胜
 * Version: V1.0版本
 * Description: 普通的fragment，无沉浸式，可至于viewpager等
 * Date: 2017/6/29
 * Email: 541567595@qq.com
 */

public abstract class YzsBaseCommonFragment extends SupportFragment {

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initLogic();
    }

    /**
     * 逻辑内容初始化，懒加载模式
     */
    protected abstract void initLogic();
}
