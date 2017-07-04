package com.yzs.yzsbaseactivitylib.entity;

/**
 * Author: 姚智胜
 * Version: V1.0版本
 * Description: EventBus传递消息总体类
 * Date: 2016/11/17
 */

public class BaseEventBusBean<T> {

    private int eventCode = -1;

    private T data;

    public BaseEventBusBean(int eventCode) {
        this.eventCode = eventCode;
    }

    public BaseEventBusBean(int eventCode, T data) {
        this.eventCode = eventCode;
        this.data = data;
    }

    public int getEventCode() {
        return eventCode;
    }

    public T getData() {
        return data;
    }
}
