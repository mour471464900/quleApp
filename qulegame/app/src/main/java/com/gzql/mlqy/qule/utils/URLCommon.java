package com.gzql.mlqy.qule.utils;

/**
 * Created by yanyan on 2017/7/04.
 */
public class URLCommon {
//手机注册
    public static final String REGISTER_MOB = "openapi/user/registerByMobile";
//发送短信
    public  final static String SMS_CODE_URL = "openapi/sms/sendSmsCode/reg";
//用户登录
    public final static String USER_LOGIN_URL = "openapi/user/login";
//注销
    public   final static String USER_LOGOUT_URL = "openapi/user/logout";


   //通过选取环境获得 api 前缀
   public static String baseApi(){
       return  LogUtil.getLogEnabled()? Constants.SDK_DEBAG_API_HOST : Constants.SDK_RELEASE_API_HOST;
   }

   public static String getApiAddress(String url){
       return  baseApi()+url;
   }

}
