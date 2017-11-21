package com.yzs.yzsbaseactivitylib.basemvp;

/**
 * Author: 姚智胜
 * Version: V1.0版本
 * Description:
 * Date: 2017/6/13
 * Email: 541567595@qq.com
 */
public interface BaseView {
    /*******内嵌加载*******/
    void showLoading(String title);

    void stopLoading();

    void showErrorTip(String msg);

}
