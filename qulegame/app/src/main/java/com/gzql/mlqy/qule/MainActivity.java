package com.gzql.mlqy.qule;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.gzql.mlqy.qule.base.BaseActivity;
import com.gzql.mlqy.qule.fragment.GameFragment;
import com.gzql.mlqy.qule.fragment.HomeFragment;
import com.gzql.mlqy.qule.fragment.PersonalFragment;
import com.gzql.mlqy.qule.fragment.TypeFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity{


    List<Fragment> fragments=new ArrayList<>();
    RadioGroup switchRg;
    RadioButton[] radioArray;
    private int cur;
    private FragmentManager fragmentManager;
    private long exitTime = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        initView();
        initFragments();
        initRadioArray();
        initListener();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    private void initListener() {
        switchRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                for (int i = 0; i < radioArray.length; i++) {
                    if (checkedId == radioArray[i].getId()) {
                        switch (checkedId){
                            case R.id.rbt_cloud:

                                break;
                            case R.id.rbt_promote:
                                break;
                            case R.id.rbt_setting:
                                break;
                            case R.id.rbt_url:
                                break;
                        }
                        //切换fragment页面
                        initSwitch(i);
                    }

                }
            }
        });
    }

    private void initSwitch(int i) {
        Fragment fragment = fragments.get(i);
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        if (!fragment.isAdded()) {
            transaction.add(R.id.container_fl, fragment).hide(fragments.get(cur));
        } else {

            transaction.hide(fragments.get(cur)).show(fragment);
        }
        transaction.commit();

        cur = i;
    }

    private void initRadioArray() {
        int length = switchRg.getChildCount();
        radioArray = new RadioButton[length];
        for (int i = 0; i < length; i++) {
            radioArray[i] = (RadioButton) switchRg.getChildAt(i);
        }
    }

    private void initFragments() {
        fragments.add(HomeFragment.newInstance(null));
        fragments.add(TypeFragment.newInstance(null));
        fragments.add(GameFragment.newInstance(null));
        fragments.add(PersonalFragment.newInstance(null));
        //往FrameLayout中提交第一个fragment；
        Fragment fragment = fragments.get(0);
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.container_fl, fragment);
        transaction.commit();
        cur = 0;
    }

    private void initView() {
        switchRg = (RadioGroup) findViewById(R.id.switch_rg);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            if((System.currentTimeMillis()-exitTime) > 2000){
                showShortToast("再按一次退出程序");
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View v) {

    }
}
