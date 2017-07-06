package com.gzql.mlqy.qule;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gzql.mlqy.qule.base.BaseActivity;
import com.gzql.mlqy.qule.bean.AppReturnBean;
import com.gzql.mlqy.qule.bean.ReturnBean;
import com.gzql.mlqy.qule.event.LoginOutEvent;
import com.gzql.mlqy.qule.utils.AppBaseInfo;
import com.gzql.mlqy.qule.utils.Constants;
import com.gzql.mlqy.qule.utils.DataUtil;
import com.gzql.mlqy.qule.utils.HttpHelper;
import com.gzql.mlqy.qule.utils.JsonUtil;
import com.gzql.mlqy.qule.utils.RequestCallback;
import com.gzql.mlqy.qule.utils.URLCommon;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/6/30.
 */

public class SafetyActivity extends BaseActivity {

    @BindView(R.id.go_back)
    ImageView goBack;
    @BindView(R.id.out_login)
    TextView outLogin;
    @BindView(R.id.chage_password_ll)
    LinearLayout chagePasswordLl;

    @Override
    public int getLayoutId() {
        return R.layout.activity_safety;
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
        //退出登入
        outLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(SafetyActivity.this);
                        builder.setTitle("退出当前帐号？");
                builder.setPositiveButton("确认",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                HashMap<String, String> request = new HashMap<String, String>();
                                request.put("appid", AppBaseInfo.gAppID + "");
                                request.put("uid", DataUtil.getInt(Constants.QL_LOGIN_UID) + "");
                                request.put("session", DataUtil.getString(Constants.QL_LOGIN_SESSION));
                                HttpHelper.getInstance().httpPostString(URLCommon.USER_LOGOUT_URL, request, getApplicationContext(), new RequestCallback<String>() {
                                    @Override
                                    public void succeedOnResult(String response) {
                                        ReturnBean<AppReturnBean> ret = JsonUtil.fromJson(response, AppReturnBean.class);
                                        if (ret.getCode() == 1) {
                                            DataUtil.setSharePreferences(Constants.QL_ISLOGIN, false);
                                            EventBus.getDefault().post(new LoginOutEvent());
                                            finish();
                                        } else {
                                            showShortToast("系统异常");
                                        }
                                    }

                                    @Override
                                    public void errorForCode(int code) {

                                    }
                                });

                            }
                        });
                builder.setNegativeButton("取消",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {

                            }
                        });
                builder.show();



            }
        });
        chagePasswordLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SafetyActivity.this,ChangePasswordActivity.class);
                startActivity(intent);
            }
        });
    }
}
