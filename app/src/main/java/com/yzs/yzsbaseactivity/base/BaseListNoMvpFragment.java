package com.yzs.yzsbaseactivity.base;

import com.gyf.barlibrary.ImmersionBar;
import com.yzs.yzsbaseactivity.R;
import com.yzs.yzsbaseactivitylib.base.YzsBaseListNoMPFragment;

/**
 * Author: 姚智胜
 * Version: V1.0版本
 * Description: 不使用MVP架构的baselistfragment
 * Date: 2017/7/4
 * Email: 541567595@qq.com
 */

public abstract class BaseListNoMvpFragment<T> extends YzsBaseListNoMPFragment<T> {
    @Override
    protected void immersionInit() {
        ImmersionBar.with(this).statusBarView(R.id.yzs_view)
                .statusBarDarkFont(false)
                .navigationBarColor(R.color.md_red_50)
                .init();
    }
}
