package com.yzs.yzsbaseactivity.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.yzs.yzsbaseactivity.R;
import com.yzs.yzsbaseactivitylib.loading.LoadingDialog;

/**
 * Author: 姚智胜
 * Version: V1.0版本
 * Description:
 * Date: 2017/3/15
 */
public class LoadingDemo extends AppCompatActivity {
    private static final String TAG = "LoadingDemo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LoadingDialog.showLoadingDialog(this);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                LoadingDialog.cancelLoadingDialog();
                Intent intent = new Intent(LoadingDemo.this, IndexActivity.class);
                startActivity(intent);
                LoadingDemo.this.finish();
            }
        }, 5000);   //5秒

    }
}
