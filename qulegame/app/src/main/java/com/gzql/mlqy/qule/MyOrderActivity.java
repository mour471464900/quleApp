package com.gzql.mlqy.qule;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.gzql.mlqy.qule.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/6/30.
 */

public class MyOrderActivity extends BaseActivity {
    @BindView(R.id.go_back)
    ImageView goBack;

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_order;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        initListener();
    }
    private void initListener() {
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
