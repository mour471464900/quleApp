package com.gzql.mlqy.qule.base;



import com.gzql.mlqy.qule.utils.DataUtil;

import org.litepal.LitePal;
import org.litepal.LitePalApplication;

/**
 * Created by Administrator on 2017/6/28.
 */

public class App extends LitePalApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        DataUtil.init(this);//初始化数据存储工具类
        LitePal.initialize(this); // 初始化 litePal
    }
}
