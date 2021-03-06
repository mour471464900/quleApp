package com.gzql.mlqy.qule.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.gzql.mlqy.qule.utils.InterfaceShowToastAndProgress;

/**
 * Created by Administrator on 2017/6/28.
 */

public abstract class BaseActivity extends AppCompatActivity implements InterfaceShowToastAndProgress, View.OnClickListener {


    private boolean showToast = true;//控制toast的开关
    private FragmentManager mFragmentManager;
    private DialogFragment mDialogFragment;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // AppUtil.setupActivityFullyTransparent(this);
        mFragmentManager = getSupportFragmentManager();

    }

    public abstract int getLayoutId();

    @Override
    public void showLongToast(String msg) {
        if(showToast){
            Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void showShortToast(String msg) {
        if(showToast){
            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void showDialogFragment(DialogFragment fragment) {
        mDialogFragment = fragment;
        mDialogFragment.show(mFragmentManager, getClass().getSimpleName());
    }

    @Override
    public void hideDialogFragment(DialogFragment fragment) {
        if (mDialogFragment != null) {
            mDialogFragment.dismiss();
            mDialogFragment = null;
        }
    }

    /**
     * 绑定视图点击事件
     * @param views    视图的数组
     */
    public void bindListener(View...views){
        for (View v :views) {
            if(null!=v){
                v.setOnClickListener(this);
            }
        }
    }
}
