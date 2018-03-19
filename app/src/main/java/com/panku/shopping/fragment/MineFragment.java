package com.panku.shopping.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.panku.shopping.R;
import com.panku.shopping.activity.PersonalActivity;
import com.panku.shopping.base.BaseFragment;

/**
 * Date：2018/3/12
 * Time: 15:24
 * author: huang ya nan
 */

public class MineFragment extends BaseFragment {
    private LinearLayout ll_next;
    private TextView tv_title;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        tv_title = view.findViewById(R.id.tv_title);
        ll_next = view.findViewById(R.id.ll_next);
        tv_title.setText("个人中心");
        ll_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), PersonalActivity.class));
            }
        });
    }
}
