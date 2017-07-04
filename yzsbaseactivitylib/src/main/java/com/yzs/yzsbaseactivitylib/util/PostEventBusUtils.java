package com.yzs.yzsbaseactivitylib.util;


import com.yzs.yzsbaseactivitylib.entity.BaseEventBusBean;

import de.greenrobot.event.EventBus;

/**
 * Author 姚智胜
 * Version V1.0版本
 * Description: eventBus发送消息帮助类
 * Date: 2017/4/1
 */

public class PostEventBusUtils {

    //创建单例模式第一步：将构造方法私有化private,不允许外部直接创建对象
    private PostEventBusUtils() {

    }

    //在类中创建类的唯一实例,加static，为了安全起见最好将实例对象私有化private
    private static PostEventBusUtils instance = new PostEventBusUtils();

    //提供一个用于获取实例的方法,必须static
    public static PostEventBusUtils getInstance() {
        return instance == null ? new PostEventBusUtils() : instance;
    }

    /**
     * 发送消息
     *
     * @param bean 发送数据类
     * @param <T>  必须继承于BaseEventBusBean
     */
    public <T extends BaseEventBusBean> void postMessage(final T bean) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                EventBus.getDefault().post(bean);
            }
        }).start();
    }

    /**
     * 发送粘性消息
     *
     * @param bean 发送数据类
     * @param <T>  必须继承于BaseEventBusBean
     */
    public <T extends BaseEventBusBean> void postStickMessage(final T bean) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                EventBus.getDefault().postSticky(bean);
            }
        }).start();
    }

}
