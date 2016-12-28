package com.yzs.yzsbaseactivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yzs.yzsbaseactivitylib.entity.EventCenter;
import com.yzs.yzsbaseactivitylib.fragment.YzsBaseFragment;

import static android.view.View.Y;

/**
 * Author: 姚智胜
 * Version: V1.0版本
 * Description:
 * Date: 2016/12/28.
 */
public class MyFragment extends YzsBaseFragment {
    private static final String TAG = "MyFragment";
    TextView textView;

    @Override
    protected View initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_fragment, container, false);
        return view;
    }

    @Override
    protected void initView(View view) {
        textView = (TextView) view.findViewById(R.id.textview);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText(textView.getText().toString()+"!");
            }
        });
    }

    @Override
    protected void initLogic(View view) {

    }

    @Override
    protected void getBundleExtras(Bundle bundle) {

    }

    @Override
    protected void onEventComing(EventCenter center) {

    }

}
