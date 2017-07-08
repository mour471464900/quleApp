package com.gzql.mlqy.qule;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.gzql.mlqy.qule.base.BaseActivity;
import com.gzql.mlqy.qule.bean.ReturnBean;
import com.gzql.mlqy.qule.bean.UserLoginBean;

import com.gzql.mlqy.qule.event.IsRegister;
import com.gzql.mlqy.qule.fragment.ProgressDialogFragment;
import com.gzql.mlqy.qule.utils.AppBaseInfo;
import com.gzql.mlqy.qule.utils.CheckInputAssist;
import com.gzql.mlqy.qule.utils.Constants;
import com.gzql.mlqy.qule.utils.DataUtil;
import com.gzql.mlqy.qule.utils.DeviceUtil;
import com.gzql.mlqy.qule.utils.HttpHelper;
import com.gzql.mlqy.qule.utils.JsonUtil;
import com.gzql.mlqy.qule.utils.RequestCallback;
import com.gzql.mlqy.qule.utils.URLCommon;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/7/3.
 */

public class RegisterActivity extends BaseActivity {
    @BindView(R.id.go_back)
    ImageView goBack;
    @BindView(R.id.ql_register_authcode)
    EditText mAuthCode;
    @BindView(R.id.ql_register_authcode_btn)
    Button qlRegisterAuthcodeBtn;
    @BindView(R.id.ll_vcode)
    LinearLayout llVcode;
    @BindView(R.id.ed_password1)
    EditText mPasswordEdit;
    @BindView(R.id.ed_mob)
    EditText mAccountEdit;
    @BindView(R.id.confirm)
    Button confirm;
    private Handler mTimeHandler = new Handler();
    private ProgressDialogFragment mProgressDialogFragment;
    private String mob;

    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        mProgressDialogFragment = ProgressDialogFragment.newInstance("正在注册,请稍候...");
        initListener();
    }

    private void initListener() {
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        qlRegisterAuthcodeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tel = mAccountEdit.getText().toString().trim();
                HashMap<String, String> request = new HashMap<String, String>();
                request.put("tel", tel);
                if (CheckInputAssist.isMobile(tel)) {
                    qlRegisterAuthcodeBtn.setClickable(false);
                    qlRegisterAuthcodeBtn.setTag(60);
                    HttpHelper.getInstance().httpPostString(URLCommon.SMS_CODE_URL, request, getApplicationContext(), new RequestCallback<String>() {
                        @Override
                        public void succeedOnResult(String response) {
                            Log.d("yanyan", "succeedOnResult: " + response);
                        }

                        @Override
                        public void errorForCode(int code) {

                        }
                    });
                    run.run();
                } else {
                    showShortToast("手机号码输入错误");
                }
            }
        });
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 mob = mAccountEdit.getText().toString();
                String password = mPasswordEdit.getText().toString();
                String code=mAuthCode.getText().toString();
                if(checkRegInputText(mob, password)) {
                    if(TextUtils.isEmpty(code)) {
                        showShortToast("验证码不能为空");
                    }else {
                        showDialogFragment(mProgressDialogFragment);
                        HashMap<String, String> request = new HashMap<String, String>();
                        request.put("appid", AppBaseInfo.gAppID+"");
                        request.put("mob", mob);
                        request.put("password", password);
                        request.put("udid", DeviceUtil.GetIMEI(getApplicationContext()));
                        Log.d("yanyan", "onClick: IEME"+DeviceUtil.GetIMEI(getApplicationContext()));
                        request.put("code", code);
                        request.put("os", "android");
                        HttpHelper.getInstance().httpPostString(URLCommon.REGISTER_MOB, request, getApplicationContext(), new RequestCallback<String>() {
                            @Override
                            public void succeedOnResult(String response) {
                                hideDialogFragment(mProgressDialogFragment);
                                ReturnBean<UserLoginBean> returnbean = JsonUtil.fromJson(response, UserLoginBean.class);
                                if (returnbean == null) {

                                        showShortToast("系统错误");

                                } else if (returnbean.getCode() == 1) {

                                    DataUtil.setSharePreferences("isregister",true);

                                    DataUtil.setSharePreferences(Constants.QL_LOGIN_UID,returnbean.getData().getUid());
                                    DataUtil.setSharePreferences(Constants.QL_LOGIN_SESSION,returnbean.getData().getSession()) ;
                                    DataUtil.setSharePreferences(Constants.QL_LOGIN_ACCOUNT,mob);
                                    DataUtil.setSharePreferences(Constants.QL_LOGIN_TOKEN, returnbean.getData().getToken());
                                    showShortToast("注册成功请登入");
                                    EventBus.getDefault().post(new IsRegister());
                                    finish();

                                } else {
                                        switch (returnbean.getCode()) {
                                            case 30001:
                                                showShortToast("用户名已经存在");
                                                break;
                                            case 30011:
                                                showShortToast("用户名格式错误");
                                                break;
                                            case 50002:
                                                showShortToast("验证码信息错误");
                                                break;
                                            case 50003:
                                                showShortToast("验证码已过期");
                                                break;
                                        }

                                }


                                Log.d("yanyan", "succeedOnResult:注册"+response);
                            }

                            @Override
                            public void errorForCode(int code) {
                                switch (code){
                                    case 100:
                                        showShortToast("网络异常");
                                        break;
                                    case 101:
                                        showShortToast("系统错误");
                                        break;
                                }
                            }
                        });
                    }
                }
            }
        });
    }

    private Runnable run = new Runnable() {
        @Override
        public void run() {
            int a = (int) qlRegisterAuthcodeBtn.getTag();
            qlRegisterAuthcodeBtn.setTag(a - 1);
            qlRegisterAuthcodeBtn.setText("重新获取(" + a + "s)");
            if (a > 0) {
                mTimeHandler.postDelayed(run, 1000L);
            } else {
                qlRegisterAuthcodeBtn.setClickable(true);
                qlRegisterAuthcodeBtn.setText("获取验证码");
            }
        }
    };
    private boolean checkRegInputText(String mob, String password) {
        int checkRslt = CheckInputAssist.isRegMobValid(mob, password);
        String msg = null;
        switch (checkRslt) {
            case CheckInputAssist.INPUT_CHECK_OK:
                return true;
            case CheckInputAssist.INPUT_CHECK_MOBILE_IILIGAL:
                msg = "手机号码输入错误";
                break;
            case CheckInputAssist.INPUT_CHECK_PASSWORD_TOO_SHORT:
                msg = "密码少于6位字符，非常不安全，请重新输入";
                break;
            default:
                msg = "系统繁忙,请稍候重试";
        }

        if(TextUtils.isEmpty(msg)){
            return true;
        }else{
            showShortToast(msg);
            return false;
        }
    }

    @Override
    public void onClick(View v) {

    }
}