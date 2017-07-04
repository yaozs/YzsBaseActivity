package com.yzs.yzsbaseactivity.presenter;


import com.yzs.yzsbaseactivity.contract.TestMvpListContract;

import java.util.List;

/**
 * Author: 姚智胜
 * Version: V1.0版本
 * Description: Presenter的demo
 * Date: 2017/7/3
 * Email: 541567595@qq.com
 */

public class TestPresenter extends TestMvpListContract.Presenter implements TestMvpListContract.Model.OnFinishedListener {

    @Override
    public void getDataRequest(int page) {
        mModel.getData(page, this);
    }

    @Override
    public void onFinished(List<String> items) {
        mView.showData(items);
    }
}
