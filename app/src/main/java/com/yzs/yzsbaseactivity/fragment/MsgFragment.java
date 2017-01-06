package com.yzs.yzsbaseactivity.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.yzs.yzsbaseactivity.R;
import com.yzs.yzsbaseactivity.bean.DemoListBean;
import com.yzs.yzsbaseactivitylib.entity.EventCenter;
import com.yzs.yzsbaseactivitylib.fragment.YzsBaseListFragment;
import com.yzs.yzsbaseactivitylib.line.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;


/**
 * Author: 姚智胜
 * Version: V1.0版本
 * Description: ListFragment的demo（与activity几乎同样用法）
 * Date: 2016/12/15.
 */
public class MsgFragment extends YzsBaseListFragment<DemoListBean> {
    private static final String TAG = "MsgFragment";

    public static MsgFragment newInstance(Bundle bundle) {
        MsgFragment fragment = new MsgFragment();
        if (null != bundle) {
            fragment.setArguments(bundle);
        }
        return fragment;
    }

    @Override
    protected void initItemLayout() {
        setLayoutResId(R.layout.item_list_demo);
        setListType(LINEAR_LAYOUT_MANAGER, true);

    }

    @Override
    protected View initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_msg, container, false);
        return view;
    }

    @Override
    protected void initLogic() {
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), 1));
        List<DemoListBean> list = new ArrayList<>();
        DemoListBean bean;
        for (int i = 0; i < 20; i++) {
            bean = new DemoListBean();
            bean.setTitle("消息--------" + i);
            list.add(bean);
        }
        mAdapter.addData(list);
    }


    @Override
    protected void getBundleExtras(Bundle bundle) {

    }

    @Override
    protected void onEventComing(EventCenter center) {

    }


    @Override
    protected void MyHolder(BaseViewHolder baseViewHolder, DemoListBean demoListBean) {
        baseViewHolder.setText(R.id.tv_item_list_demo, demoListBean.getTitle());
        ImageView iv = baseViewHolder.getView(R.id.iv_item_list_demo);
        iv.setImageResource(R.mipmap.icon);
    }
}
