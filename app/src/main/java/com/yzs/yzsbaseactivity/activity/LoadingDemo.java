package com.yzs.yzsbaseactivity.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.yzs.yzsbaseactivity.R;
import com.yzs.yzsbaseactivitylib.activity.YzsBaseActivity;
import com.yzs.yzsbaseactivitylib.entity.EventCenter;
import com.yzs.yzsbaseactivitylib.util.ActivityGoUtils;
import com.yzs.yzsbaseactivitylib.util.LoadingDialogUtils;

/**
 * Author: 姚智胜
 * Version: V1.0版本
 * Description:
 * Date: 2017/3/15
 */
public class LoadingDemo extends YzsBaseActivity {
    private static final String TAG = "LoadingDemo";


    @Override
    protected void initContentView(Bundle bundle) {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initLogic() {
        LoadingDialogUtils.showLoadingDialog();
        new Handler().postDelayed(new Runnable() {
            public void run() {
                LoadingDialogUtils.cancelLoadingDialog();
                ActivityGoUtils.getInstance().readyGoThenKill(IndexActivity.class);
            }
        }, 5000);   //5秒
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected void onEventComing(EventCenter center) {

    }
}
