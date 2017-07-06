package com.gzql.mlqy.qule.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.gzql.mlqy.qule.utils.InterfaceShowToastAndProgress;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/6/28.
 */

public abstract class Basefragment extends Fragment implements InterfaceShowToastAndProgress {
    protected String Tag=getClass().getSimpleName();

    private BaseActivity mBaseActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (getContext() instanceof BaseActivity) {
            mBaseActivity = (BaseActivity) getContext();
        } else {
            throw new ClassCastException("getContext() is not instance of BaseActivity");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(getLayoutId(),null,false);

        ButterKnife.bind(this,view);
        return view;
    }

    public abstract int getLayoutId();

    @Override
    public void showLongToast(String msg) {
        mBaseActivity.showLongToast(msg);
    }

    @Override
    public void showShortToast(String msg) {
        mBaseActivity.showShortToast(msg);
    }


    @Override
    public void showDialogFragment(DialogFragment fragment) {
        mBaseActivity.showDialogFragment(fragment);
    }

    @Override
    public void hideDialogFragment(DialogFragment fragment) {
        mBaseActivity.hideDialogFragment(fragment);
    }


}
