package com.yzs.yzsbaseactivitylib.adapter;

import android.content.Context;

import java.util.List;

/**
 * Author: zhy
 * Version: V1.0版本
 * Description: ListView 普通list展示数据使用adapter(张弘扬写的，默认使用的不是这个)
 * Date: 2016/12/7
 */
public abstract class CommonAdapter<T> extends MultiItemTypeAdapter<T> {

    public CommonAdapter(Context context, final int layoutId, List<T> datas) {
        super(context, datas);

        addItemViewDelegate(new ItemViewDelegate<T>() {
            @Override
            public int getItemViewLayoutId() {
                return layoutId;
            }

            @Override
            public boolean isForViewType(T item, int position) {
                return true;
            }

            @Override
            public void convert(ViewHolder holder, T t, int position) {
                CommonAdapter.this.convert(holder, t, position);
            }
        });
    }

    protected abstract void convert(ViewHolder viewHolder, T item, int position);

}
