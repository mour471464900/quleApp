package com.gzql.mlqy.qule.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gzql.mlqy.qule.R;
import com.gzql.mlqy.qule.base.Basefragment;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/6/28.
 */

public class TypeFragment extends Basefragment {


    public static TypeFragment newInstance(Bundle bundle) {
        TypeFragment fragment = new TypeFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
    @Override
    public int getLayoutId() {
        return R.layout.fragment_type;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView= super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this,rootView);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
