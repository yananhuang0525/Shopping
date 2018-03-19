package com.panku.shopping.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.panku.shopping.R;
import com.panku.shopping.base.BaseFragment;


/**
 * Date：2018/3/12
 * Time: 15:24
 * author: huang ya nan
 */

public class OtherFragment extends BaseFragment {
    private TextView tv_title;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_other, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        tv_title = view.findViewById(R.id.tv_title);
        tv_title.setText("购物车");
    }
}
