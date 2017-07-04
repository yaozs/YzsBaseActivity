package com.yzs.yzsbaseactivity.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.yzs.yzsbaseactivity.R;
import com.yzs.yzsbaseactivitylib.entity.EventCenter;
import com.yzs.yzsbaseactivitylib.fragment.YzsBaseFragment;


/**
 * Author: 姚智胜
 * Version: V1.0版本
 * Description:
 * Date: 2016/12/15.
 */
public class MoreFragment extends YzsBaseFragment {
    private static final String TAG = "MoreFragment";

    public static MoreFragment newInstance(Bundle bundle) {
        MoreFragment fragment = new MoreFragment();
        if (null != bundle) {
            fragment.setArguments(bundle);
        }
        return fragment;
    }

    @Override
    protected View initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_more, container, false);
        return view;
    }

    @Override
    protected void initView(View view) {
        WebView webView = (WebView) view.findViewById(R.id.web);
        webView.loadUrl("http://bjdev.china-dt.com/2017/ec/shengzhuli1/index.php/front-echarts-spotIncomeChart.html?goods_id=1");
    }

    @Override
    protected void initLogic() {

    }


    @Override
    protected void getBundleExtras(Bundle bundle) {

    }

    @Override
    protected void onEventComing(EventCenter center) {

    }
}
