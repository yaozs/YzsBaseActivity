package com.yzs.yzsbaseactivity.fragment;

import android.os.Bundle;

import com.gyf.barlibrary.ImmersionBar;
import com.yzs.yzsbaseactivity.R;
import com.yzs.yzsbaseactivitylib.yzsbase.YzsBaseWebFragment;

/**
 * Author: 姚智胜
 * Version: V1.0版本
 * Description:
 * Date: 2017/7/4
 * Email: 541567595@qq.com
 */

public class WebFragment extends YzsBaseWebFragment {

    public static WebFragment newInstance() {

        Bundle args = new Bundle();

        WebFragment fragment = new WebFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initLogic() {

        initWeb("https://www.baidu.com/");
    }

    @Override
    protected void immersionInit(ImmersionBar mImmersionBar) {
        mImmersionBar
                .statusBarView(R.id.yzs_view,rootView)
                .statusBarColor(R.color.md_blue_300)
                .navigationBarColor(R.color.md_blue_300)
                .init();
    }


    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected boolean immersionEnabled() {
        return true;
    }
}
