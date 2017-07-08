package com.gzql.mlqy.qule.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;

import com.gzql.mlqy.qule.FavoritesActivity;
import com.gzql.mlqy.qule.HelpActivity;
import com.gzql.mlqy.qule.MessageCenterActivity;
import com.gzql.mlqy.qule.MyOrderActivity;
import com.gzql.mlqy.qule.PasswordBackActivity;
import com.gzql.mlqy.qule.R;
import com.gzql.mlqy.qule.RegisterActivity;
import com.gzql.mlqy.qule.SafetyActivity;
import com.gzql.mlqy.qule.base.Basefragment;
import com.gzql.mlqy.qule.bean.ReturnBean;
import com.gzql.mlqy.qule.bean.UserLoginBean;
import com.gzql.mlqy.qule.event.FirstEvent;
import com.gzql.mlqy.qule.event.IsRegister;
import com.gzql.mlqy.qule.event.LoginOutEvent;
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
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2017/6/28.
 */

public class PersonalFragment extends Basefragment {


    @BindView(R.id.head_portrait)
    CircleImageView headPortrait;
    @BindView(R.id.login_text)
    TextView loginText;
    @BindView(R.id.sign_img)
    ImageView signImg;
    @BindView(R.id.message_text)
    TextView messageText;
    @BindView(R.id.order_text)
    TextView orderText;
    @BindView(R.id.favorites_text)
    TextView favoritesText;
    @BindView(R.id.share_ll)
    LinearLayout shareLl;
    @BindView(R.id.line_view1)
    View lineView1;
    @BindView(R.id.ticket_ll)
    LinearLayout ticketLl;
    @BindView(R.id.line_view2)
    View lineView2;
    @BindView(R.id.task_ll)
    LinearLayout taskLl;
    @BindView(R.id.line_view3)
    View lineView3;
    @BindView(R.id.safety_ll)
    LinearLayout safetyLl;
    @BindView(R.id.help_ll)
    LinearLayout helpLl;
    Unbinder unbinder;


    private Context mContent;
    private PopupWindow mPopWindow;
    private EditText edPassword;
    private LinearLayout llVcode;
    private Button registerBt;
    private Button loginBt;
    private EditText ed_mob;
    private ProgressDialogFragment mProgressDialogFragment;
    private String account;

    public android.os.Handler mHandler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                headPortrait.setImageDrawable(getResources().getDrawable(R.drawable.head_login));
                loginText.setText(DataUtil.getString(Constants.QL_NICHENG));
                lineView1.setVisibility(View.VISIBLE);
                ticketLl.setVisibility(View.VISIBLE);
                lineView2.setVisibility(View.VISIBLE);
                taskLl.setVisibility(View.VISIBLE);
                lineView3.setVisibility(View.VISIBLE);
                safetyLl.setVisibility(View.VISIBLE);
            }
        }
    };
    private EditDialogFragment editDialogFragment;


    public static PersonalFragment newInstance(Bundle bundle) {
        PersonalFragment fragment = new PersonalFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_personal;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContent = context;
        EventBus.getDefault().register(this);
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);

        unbinder = ButterKnife.bind(this, rootView);
        if (DataUtil.getBoolean(Constants.QL_ISLOGIN)) {
            headPortrait.setImageDrawable(getResources().getDrawable(R.drawable.head_login));
            loginText.setText(DataUtil.getString(Constants.QL_NICHENG));
            lineView1.setVisibility(View.VISIBLE);
            ticketLl.setVisibility(View.VISIBLE);
            lineView2.setVisibility(View.VISIBLE);
            taskLl.setVisibility(View.VISIBLE);
            lineView3.setVisibility(View.VISIBLE);
            safetyLl.setVisibility(View.VISIBLE);
        }
        return rootView;

    }

    @Override
    protected void requestData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        editDialogFragment = EditDialogFragment.newInstance(null);
        initListener();

    }

    private void initListener() {
        //帮助中心
        helpLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HelpActivity.class);
                startActivity(intent);
            }
        });
        //收藏夹
        favoritesText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DataUtil.getBoolean(Constants.QL_ISLOGIN)) {
                    Intent intent = new Intent(getActivity(), FavoritesActivity.class);
                    startActivity(intent);
                } else {
                    showPop();
                }

            }
        });
        //我的订单
        orderText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DataUtil.getBoolean(Constants.QL_ISLOGIN)) {
                    Intent intent = new Intent(getActivity(), MyOrderActivity.class);
                    startActivity(intent);
                } else {
                    showPop();
                }

            }
        });
        //消息中心
        messageText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DataUtil.getBoolean(Constants.QL_ISLOGIN)) {
                    Intent intent = new Intent(getActivity(), MessageCenterActivity.class);
                    startActivity(intent);
                } else {
                    showPop();
                }

            }
        });
        //分享
        shareLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShortToast("后续完善");
            }
        });
        //请登录

        loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DataUtil.getBoolean(Constants.QL_ISLOGIN)) {

                    showDialogFragment(editDialogFragment);
                } else {
                    showPop();
                }
            }
        });
        //签到
        signImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DataUtil.getBoolean(Constants.QL_ISLOGIN)) {
                    showShortToast("暂未开放");
                } else {
                    showPop();
                }
            }
        });
        //帐号与安全
        safetyLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SafetyActivity.class);
                startActivity(intent);
            }
        });
        //玩票
        ticketLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShortToast("暂未开放");
            }
        });

        //任务
        taskLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShortToast("暂未开放");
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void showPop() {
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.popupwindow_login, null);
        contentView.startAnimation(AnimationUtils.loadAnimation(mContent,
                R.anim.pop_up_in));
        mPopWindow = new PopupWindow(contentView);
        mPopWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        mPopWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        mPopWindow.setFocusable(true);
        mPopWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopWindow.setOutsideTouchable(true);
        mPopWindow.showAtLocation(loginText, Gravity.BOTTOM, 0, 0);

        RadioButton rbOneTop = (RadioButton) contentView.findViewById(R.id.rb_one_top);
        RadioButton rbTwoTop = (RadioButton) contentView.findViewById(R.id.rb_two_top);
        llVcode = (LinearLayout) contentView.findViewById(R.id.ll_vcode);
        edPassword = (EditText) contentView.findViewById(R.id.ed_password);
        registerBt = (Button) contentView.findViewById(R.id.register_bt);
        loginBt = (Button) contentView.findViewById(R.id.login_bt);
        LinearLayout pback = (LinearLayout) contentView.findViewById(R.id.ll_password_back);
        ed_mob = (EditText) contentView.findViewById(R.id.ed_mob);

        mProgressDialogFragment = ProgressDialogFragment.newInstance("正在登录,请稍候...");

        //设置各控件点击响应
        rbOneTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llVcode.setVisibility(View.GONE);
                edPassword.setVisibility(View.VISIBLE);
            }
        });
        rbTwoTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llVcode.setVisibility(View.VISIBLE);
                edPassword.setVisibility(View.GONE);
            }
        });

        registerBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), RegisterActivity.class);
                startActivity(intent);

            }
        });
        loginBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                account = ed_mob.getText().toString();
                String password = edPassword.getText().toString();
                if (checkLoginInputText(account, password)) {
                    String accountType = CheckInputAssist.getAccountType(account);
                    HashMap<String, String> request = new HashMap<String, String>();
                    request.put("appid", AppBaseInfo.gAppID + "");
                    request.put("udid", DeviceUtil.GetIMEI(getContext()));
                    request.put("uidVal", account);
                    request.put("uidType", accountType);
                    request.put("password", password);
                    request.put("os", "android");
                    showDialogFragment(mProgressDialogFragment);
                    // TODO: 2017/7/6   如果在运行程序时，用户清除缓存，app会崩溃的
                    HttpHelper.getInstance().httpPostString(URLCommon.USER_LOGIN_URL, request, getContext(), new RequestCallback<String>() {
                        @Override
                        public void succeedOnResult(String response) {
                            hideDialogFragment(mProgressDialogFragment);
                            ReturnBean<UserLoginBean> returnbean = JsonUtil.fromJson(response, UserLoginBean.class);
                            if (returnbean == null) {
                                showShortToast("系统错误");
                            } else if (returnbean.getCode() == 1) {
                                DataUtil.setSharePreferences(Constants.QL_ISLOGIN, true);
                                DataUtil.setSharePreferences(Constants.QL_LOGIN_UID, returnbean.getData().getUid());
                                DataUtil.setSharePreferences(Constants.QL_LOGIN_SESSION, returnbean.getData().getSession());
                                DataUtil.setSharePreferences(Constants.QL_LOGIN_ACCOUNT, account);
                                DataUtil.setSharePreferences(Constants.QL_LOGIN_TOKEN, returnbean.getData().getToken());
                                DataUtil.setSharePreferences(Constants.QL_NICHENG, account);
                                mHandler.sendEmptyMessage(0);
                                mPopWindow.dismiss();

                            } else {
                                switch (returnbean.getCode()) {
                                    case 30002:
                                        showShortToast("用户名不存在");
                                        break;
                                    case 30021:
                                        showShortToast("用户被禁止登录");
                                        break;
                                    case 30022:
                                        showShortToast("用户名不存在或密码错误");
                                        break;
                                }
                            }
                            Log.d("yanyan", "succeedOnResult:登录" + response);
                        }

                        @Override
                        public void errorForCode(int code) {

                        }
                    });
                    // TODO: 2017/7/6 建议使用下面方法
//                    HttpHelper.getInstance().httpPostString(URLCommon.USER_LOGIN_URL, request, getContext(), new RequestCallback<String>() {
//                        @Override
//                        public void succeedOnResult(String response) {
//                            hideDialogFragment(mProgressDialogFragment);
//                            ReturnBean<UserInfo> returnbean = JsonUtil.fromJson(response, UserInfo.class);
//                            if (returnbean == null) {
//                                showShortToast("系统错误");
//                            } else if (returnbean.getCode() == 1) {
//                                App.setUserInfo(returnbean.getData());
//                                mHandler.sendEmptyMessage(0);
//                                mPopWindow.dismiss();
//                            } else {
//                                switch (returnbean.getCode()) {
//                                    case 30002:
//                                        showShortToast("用户名不存在");
//                                        break;
//                                    case 30021:
//                                        showShortToast("用户被禁止登录");
//                                        break;
//                                    case 30022:
//                                        showShortToast("用户名不存在或密码错误");
//                                        break;
//                                }
//                            }
//                            Log.d("yanyan", "succeedOnResult:登录" + response);
//                        }
//
//                        @Override
//                        public void errorForCode(int code) {
//
//                        }
//                    });
//
//
                } else {
                    showShortToast("账号或密码格式错误");
                }


            }
        });
        pback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PasswordBackActivity.class);
                startActivity(intent);
            }
        });

        if (!DataUtil.getBoolean(Constants.QL_ISLOGIN)) {

            ed_mob.setText(DataUtil.getString(Constants.QL_LOGIN_ACCOUNT));
            ed_mob.setSelection(ed_mob.getText().length());

        }

    }

    private boolean checkLoginInputText(String account, String password) {
        if (CheckInputAssist.INPUT_CHECK_OK != CheckInputAssist.isRegPasswordValid(password)) {
            return false;
        }
        if (CheckInputAssist.isUsername(account)) {
            return true;
        }
        if (CheckInputAssist.isMobile(account)) {
            return true;
        }
        if (CheckInputAssist.isEmail(account)) {
            return true;
        }

        return false;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        EventBus.getDefault().unregister(this);//反注册EventBus
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(FirstEvent event) {
        String msg = event.getMsg();
        Log.d("yanyan", "onMessageEvent: " + msg);
        loginText.setText(msg);



    }





    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLoginOutEvent(LoginOutEvent event) {
        Log.d("yanyan", "onMessageEvent:退出登入 ++++++++++++++++++++");

            headPortrait.setImageDrawable(getResources().getDrawable(R.drawable.head_portrait));
            loginText.setText("请登录");
            lineView1.setVisibility(View.GONE);
            ticketLl.setVisibility(View.GONE);
            lineView2.setVisibility(View.GONE);
            taskLl.setVisibility(View.GONE);
            lineView3.setVisibility(View.GONE);
            safetyLl.setVisibility(View.GONE);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onIsRegisterEvent(IsRegister event) {
        Log.d("yanyan", "onMessageEvent:注册成功 ++++++++++++++++++++");
        ed_mob.setText(DataUtil.getString(Constants.QL_LOGIN_ACCOUNT));
        ed_mob.setSelection(ed_mob.getText().length());
    }

    @Override
    public void onClick(View v) {

    }
}
