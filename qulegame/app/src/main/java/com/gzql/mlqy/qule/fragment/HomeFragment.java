package com.gzql.mlqy.qule.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.gzql.mlqy.qule.R;
import com.gzql.mlqy.qule.adapter.EmptyDataAdapter;
import com.gzql.mlqy.qule.base.Basefragment;

import butterknife.BindView;

/**
 * 首页fragment
 */
public class HomeFragment extends Basefragment {

    @BindView(R.id.recylerview_main)
    LRecyclerView mRecyclerView;
    LRecyclerViewAdapter mRecyclerViewAdapter;
    EmptyDataAdapter emptyDataAdapter;

    public static HomeFragment newInstance(Bundle bundle) {
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }


    @Override
    protected void requestData() {

    }

    @Override
    protected void initView() {
        View headView = LayoutInflater.from(getActivity()).inflate(R.layout.view_home_main, null);
        emptyDataAdapter = new EmptyDataAdapter(getActivity());
        mRecyclerViewAdapter = new LRecyclerViewAdapter(emptyDataAdapter);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
        mRecyclerViewAdapter.addHeaderView(headView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //禁用下拉刷新功能
        mRecyclerView.setPullRefreshEnabled(true);
        //禁用自动加载更多功能
        mRecyclerView.setLoadMoreEnabled(false);
    }


    @Override
    public void onClick(View v) {

    }
}
