package com.yzs.yzsbaseactivitylib.annotations;


import androidx.annotation.IntDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.yzs.yzsbaseactivitylib.entity.BaseListType.GRID_LAYOUT_MANAGER;
import static com.yzs.yzsbaseactivitylib.entity.BaseListType.LINEAR_LAYOUT_MANAGER;
import static com.yzs.yzsbaseactivitylib.entity.BaseListType.STAGGERED_GRID_LAYOUT_MANAGER;

/**
 * Author: 姚智胜
 * Version: V1.0版本
 * Description: list列表类型选择注解
 * Date: 2017/9/8
 * Email: 541567595@qq.com
 */
@IntDef(
        value = {LINEAR_LAYOUT_MANAGER,
                GRID_LAYOUT_MANAGER,
                STAGGERED_GRID_LAYOUT_MANAGER
        })
@Retention(RetentionPolicy.SOURCE)//注解保留区间，source仅存在于源码中，在class字节码文件中不存在
@Target(ElementType.PARAMETER)
//注解作用目标，PARAMETER为参数
public @interface ListType {
}
