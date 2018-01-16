package com.yzs.yzsbaseactivity.contract;


import com.yzs.yzsbaseactivitylib.basemvp.BaseModel;
import com.yzs.yzsbaseactivitylib.basemvp.BasePresenter;
import com.yzs.yzsbaseactivitylib.basemvp.BaseView;

import java.util.List;

/**
 * Author: 姚智胜
 * Version: V1.0版本
 * Description: mvp接口（未使用rxjava，自己使用接口回调）
 * Date: 2017/7/3
 * Email: 541567595@qq.com
 */

public interface TestMvpListContract {

    interface View extends BaseView {
        void showData(List<String> list);
    }

    interface Model extends BaseModel {
        /**
         * 未使用rxjava自己使用接口回掉
         */
        interface OnFinishedListener {
            void onFinished(List<String> items);
        }

        void getData(int page, OnFinishedListener listener);
    }

    abstract static class Presenter extends BasePresenter<View, Model> {
        public abstract void getDataRequest(int page);
    }
}
