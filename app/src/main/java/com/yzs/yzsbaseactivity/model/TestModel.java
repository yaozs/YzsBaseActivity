package com.yzs.yzsbaseactivity.model;

import android.os.Handler;

import com.yzs.yzsbaseactivity.contract.TestMvpListContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: 姚智胜
 * Version: V1.0版本
 * Description: model层demo
 * Date: 2017/7/3
 * Email: 541567595@qq.com
 */

public class TestModel implements TestMvpListContract.Model {


    @Override
    public void onDestroy() {

    }

    @Override
    public void getData(final int page, final OnFinishedListener listener) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                listener.onFinished(getData(page));
            }
        }, 1000);
    }


    public List<String> getData(final int page) {

        List<String> list = new ArrayList<>();
        String string;
        for (int i = 0; i < (page < 4 ? 10 : 5); i++) {
            string = "第" + page + "页" + "第" + i + "个";
            list.add(string);
        }
        return list;

    }


}
