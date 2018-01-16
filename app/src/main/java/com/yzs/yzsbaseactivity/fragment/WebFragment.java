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

        initWebView("https://www.baidu.com/", _mActivity);
    }

    @Override
    protected void immersionInit() {
        ImmersionBar.with(this).statusBarView(R.id.yzs_view,rootView)
                .statusBarDarkFont(true, 0.2f)
                .statusBarColor(R.color.md_blue_grey_200)
                .navigationBarColor(R.color.md_blue_grey_200)
                .init();
    }

    @Override
    protected void getBundleExtras(Bundle bundle) {

    }

    @Override
    public boolean showToolBar() {
        return true;
    }
}
