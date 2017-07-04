package com.yzs.yzsbaseactivitylib.okload;

/**
 * Author: 姚智胜
 * Version: V1.0版本
 * Description: 加载更多或刷新结束接口
 * Date: 2017/6/14
 * Email: 541567595@qq.com
 */

public interface IOkLoad {

    /**
     * 刷新完成
     */
    void okRefresh();

    /**
     * 加载更多完成
     *
     * @param isHashNext 是否有下一页
     */
    void okLoadMore(boolean isHashNext);

    /**
     * 加载失败
     */
    void failLoadMore();

    /**
     * 销毁
     */
    void onDestroy();

    /**
     * 获取当前页码数
     *
     * @return 页码数
     */
    int getPage();

    /**
     * 设置页码数
     *
     * @param page 页码数
     */
    void setPage(int page);

}
