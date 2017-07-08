package com.gzql.mlqy.qule.base;



import com.gzql.mlqy.qule.bean.UserInfo;
import com.gzql.mlqy.qule.utils.DataUtil;

import org.litepal.LitePal;
import org.litepal.LitePalApplication;

/**
 * Created by Administrator on 2017/6/28.
 */

public class App extends LitePalApplication {

    public static UserInfo userInfo;

    @Override
    public void onCreate() {
        super.onCreate();
        DataUtil.init(this);//初始化数据存储工具类
        LitePal.initialize(this); // 初始化 litePal
    }

    public static UserInfo getUserInfo() {
        return userInfo;
    }

    public static void setUserInfo(UserInfo userInfo) {
        App.userInfo = userInfo;
        if(null!=App.userInfo){App.userInfo.save();}
    }

    /**
     * 是否登录
     * @return
     */
    public static  boolean isLogin(){
        return null==userInfo? false :true;
    }
}
