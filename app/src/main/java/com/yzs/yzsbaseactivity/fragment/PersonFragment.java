package com.yzs.yzsbaseactivity.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.yzs.yzsbaseactivity.R;
import com.yzs.yzsbaseactivitylib.entity.EventCenter;
import com.yzs.yzsbaseactivitylib.fragment.YzsBaseFragment;
import com.yzs.yzsbaseactivitylib.util.ActivityStackManager;


/**
 * Author: 姚智胜
 * Version: V1.0版本
 * Description:
 * Date: 2016/12/15.
 */
public class PersonFragment extends YzsBaseFragment {
    private static final String TAG = "PersonFragment";

    public static PersonFragment newInstance(Bundle bundle) {
        PersonFragment fragment = new PersonFragment();
        if (null != bundle) {
            fragment.setArguments(bundle);
        }
        return fragment;
    }

    @Override
    protected View initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_person, container, false);
        return view;
    }

    ImageView imageView;
    @Override
    protected void initView(View view) {
        TextView textView = (TextView) view.findViewById(R.id.tv_person);
         imageView = (ImageView) view.findViewById(R.id.iv_person);
        Log.e(TAG, "11111111111111");
        textView.setText("0000000");
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "oooooo", Toast.LENGTH_SHORT).show();
                Activity activity = ActivityStackManager.getInstance().getTopActivity();
                if (null == activity) {
                    Log.e("ActivityStackManager", "null==activity");
                }
            }
        });
//        imageView.setImageResource(R.mipmap.icon);
    }


    @Override
    protected void initLogic() {
        Glide.with(this).
                load("http://f.hiphotos.baidu.com/image/h%3D200/sign=357261e548086e0675a8384b320a7b5a/5ab5c9ea15ce36d358d27ee43ef33a87e850b114.jpg").into(imageView);

    }


    @Override
    protected void getBundleExtras(Bundle bundle) {

    }

    @Override
    protected void onEventComing(EventCenter center) {

    }
}
