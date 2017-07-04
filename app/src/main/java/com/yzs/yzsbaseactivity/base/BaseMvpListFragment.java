package com.yzs.yzsbaseactivity.base;

import com.gyf.barlibrary.ImmersionBar;
import com.yzs.yzsbaseactivity.R;
import com.yzs.yzsbaseactivitylib.base.BaseModel;
import com.yzs.yzsbaseactivitylib.base.BasePresenter;
import com.yzs.yzsbaseactivitylib.base.YzsBaseListFragment;

/**
 * Author: 姚智胜
 * Version: V1.0版本
 * Description: 不使用MVP架构的baselistfragment
 * Date: 2017/7/4
 * Email: 541567595@qq.com
 */

public abstract class BaseMvpListFragment<T extends BasePresenter, E extends BaseModel, D> extends YzsBaseListFragment<T, E, D> {
    @Override
    protected void immersionInit() {
        ImmersionBar.with(this).statusBarView(R.id.yzs_view)
                .statusBarDarkFont(false)
                .navigationBarColor(R.color.md_red_50)
                .init();

    }

}
