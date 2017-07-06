package com.gzql.mlqy.qule;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.gzql.mlqy.qule.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/7/3.
 */

public class PasswordBackActivity extends BaseActivity {
    @BindView(R.id.ql_register_authcode)
    EditText qlRegisterAuthcode;
    @BindView(R.id.ql_register_authcode_btn)
    Button qlRegisterAuthcodeBtn;
    @BindView(R.id.ll_vcode)
    LinearLayout llVcode;
    @BindView(R.id.ed_password1)
    EditText edPassword1;
    @BindView(R.id.ed_password2)
    EditText edPassword2;
    @BindView(R.id.go_back)
    ImageView goBack;

    @Override
    public int getLayoutId() {
        return R.layout.activity_password_back;
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
