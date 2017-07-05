package com.yzs.yzsbaseactivity.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.chad.library.adapter.base.BaseViewHolder;
import com.gyf.barlibrary.ImmersionBar;
import com.yzs.yzsbaseactivity.R;
import com.yzs.yzsbaseactivity.base.BaseListNoMvpFragment;
import com.yzs.yzsbaseactivity.layout.CustomLoadMoreView;
import com.yzs.yzsbaseactivitylib.entity.BaseListType;

import java.util.ArrayList;
import java.util.List;


/**
 * Author: 姚智胜
 * Version: V1.0版本
 * Description: ListFragment的demo（与activity几乎同样用法）
 * Date: 2016/12/15.
 */
public class MsgFragment extends BaseListNoMvpFragment<String> {

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
    protected void immersionInit() {
        super.immersionInit();
        Log.e("44444444444444", "11111111111");
        ImmersionBar.with(this).statusBarView(R.id.yzs_view,rootView)
                .statusBarDarkFont(true, 0.2f)
                .statusBarColor(R.color.md_green_400)
                .navigationBarColor(R.color.md_red_500)
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
        setTitle("MsgFragment");
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
        setLoadMordTypeLayout(new CustomLoadMoreView());//可以不设置，有默认
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
                okRefresh();
                mAdapter.setNewData(addData(getPage()));
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
                        failLoadMore();
                    } else {
                        isFail = true;
                        okLoadMore(true);
                        mAdapter.addData(addData(getPage()));
                    }
                } else {
                    okLoadMore(false);
                    mAdapter.addData(addData(getPage()));
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
    protected void getBundleExtras(Bundle bundle) {

    }
}

