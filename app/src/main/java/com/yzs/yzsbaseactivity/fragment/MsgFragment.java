package com.yzs.yzsbaseactivity.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import com.chad.library.adapter.base.BaseViewHolder;
import com.gyf.barlibrary.ImmersionBar;
import com.yzs.yzsbaseactivity.R;
import com.yzs.yzsbaseactivity.base.BaseListFragment;
import com.yzs.yzsbaseactivitylib.entity.BaseListType;

import java.util.ArrayList;
import java.util.List;


/**
 * Author: 姚智胜
 * Version: V1.0版本
 * Description: ListFragment的demo（与activity几乎同样用法）
 * Date: 2016/12/15.
 */
public class MsgFragment extends BaseListFragment<String> {

    private int pageSize = 20;

    private Button button;

    private boolean isGrid = true;
    /**
     * 模拟加载错误
     */
    private boolean isFail = true;

    public static MsgFragment newInstance() {
        Bundle args = new Bundle();
        MsgFragment fragment = new MsgFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void immersionInit(ImmersionBar mImmersionBar) {
//        mImmersionBar
//                .statusBarView(R.id.yzs_view, rootView)
//                .statusBarColor(R.color.md_blue_300)
//                .navigationBarColor(R.color.md_blue_300)
//                .init();
        mImmersionBar
                .statusBarView(R.id.yzs_view,rootView)
                .statusBarColor(R.color.md_blue_300)
                .navigationBarColor(R.color.md_blue_300)
                .init();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fg_demo_change_list;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        button = (Button) view.findViewById(R.id.btn_change);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isGrid) {
                    changeShowType(BaseListType.LINEAR_LAYOUT_MANAGER, true);
                    isGrid = false;
                } else {
                    changeShowType(BaseListType.GRID_LAYOUT_MANAGER, true);
                    isGrid = true;
                }
            }
        });

    }


    @Override
    protected void initLogic() {
        setmPageSize(pageSize);//该方法为设置自动加载的每页数量，如果不足，则判定为结束
        autoRefresh();
    }

    @Override
    protected int initItemLayout() {
        return R.layout.item_list_demo;
    }

    @Override
    protected void initSetting() {

        isOpenLoad(true, true);//是否开启加载和刷新
        setListType(BaseListType.GRID_LAYOUT_MANAGER, true);//设置展示方式
        setSpanCount(4);//为grid样式和瀑布流设置横向或纵向数量
//        setLoadMordTypeLayout(new CustomLoadMoreView());//可以不设置，有默认
    }

    @Override
    protected void MyHolder(BaseViewHolder baseViewHolder, String s) {
        baseViewHolder.setText(R.id.tv_item_test, s);
    }

    @Override
    protected void refreshListener() {
        setPage(1);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //该方法使用在回调数据中，此为假数据所所以在这里使用
                autoListLoad(addData(getPage()), "", R.drawable.empty_address);
            }
        }, 1000);
    }


    @Override
    protected void loadMoreListener() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (getPage() < 5) {
                    if (isFail) {
                        isFail = false;
                        //此时为获取失败，调用该方法
                        autoListLoad(addData(getPage()), "", R.drawable.empty_address, true);
                    } else {
                        isFail = true;
                        autoListLoad(addData(getPage()), "", R.drawable.empty_address);
                    }
                } else {
                    autoListLoad(addData(getPage()), "", R.drawable.empty_address);
                }

            }
        }, 1000);

    }


    private List<String> addData(int page) {
        List<String> list = new ArrayList<>();
        String string;
        for (int i = 0; i < pageSize; i++) {
            string = "第" + page + "页" + "第" + i + "个";
            list.add(string);
        }
        return list;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }
    @Override
    protected boolean immersionEnabled() {
        return true;
    }
}

