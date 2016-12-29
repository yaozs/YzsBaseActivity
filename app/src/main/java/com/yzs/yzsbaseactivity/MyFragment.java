package com.yzs.yzsbaseactivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yzs.yzsbaseactivitylib.entity.EventCenter;
import com.yzs.yzsbaseactivitylib.fragment.YzsBaseLazyFragment;


/**
 * Author: 姚智胜
 * Version: V1.0版本
 * Description:
 * Date: 2016/12/28.
 */
public class MyFragment extends YzsBaseLazyFragment {
    private static final String TAG = "MyFragment";
    TextView textView;

    private int num;

    private int aa;

    // 标志位，标志已经初始化完成。
    private boolean isPrepared;

    private boolean firstLoadOk;

    @Override
    protected View initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e("第" + num + "MyFragment", num + "initContentView");
        View mView = inflater.inflate(R.layout.my_fragment, container, false);
        isPrepared = true;
        return mView;
    }

    @Override
    protected void initView(View view) {
        Log.e("第" + num + "MyFragment", num + "initView");

        textView = (TextView) view.findViewById(R.id.textview);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aa++;
                textView.setText(aa + "!");
            }
        });
    }


    @Override
    protected void getBundleExtras(Bundle bundle) {
        num = bundle.getInt("num", -1);
        Log.e("第" + num + "MyFragment", num + "getBundleExtras");
    }

    @Override
    protected void onEventComing(EventCenter center) {

    }

    @Override
    protected void lazyLoad() {
        if (!isPrepared || !isVisible) {
            return;
        }

        if (textView == null) {
            Log.e("第" + num + "MyFragment", "textView==null");
        } else {
            Log.e("第" + num + "MyFragment", "textView!=null");
        }
        if (!firstLoadOk) {
            firstLoadOk = true;
        }else {
            textView.setText("第" + num + "MyFragment");
            return;
        }
        textView.setText("1111111111111111111");
    }

    @Override
    protected void onInvisible() {
        super.onInvisible();
        Log.e("第" + num + "MyFragment", num + "onInvisible");
    }

    @Override
    protected void onVisible() {
        super.onVisible();
        Log.e("第" + num + "MyFragment", num + "onVisible");
    }
}
