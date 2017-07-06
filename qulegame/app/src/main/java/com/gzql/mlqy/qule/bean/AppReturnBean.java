package com.gzql.mlqy.qule.bean;

/**
 * Created by LeonYu on 2017/5/3.
 */

public class AppReturnBean {

    private int appID;
    private String goldName;
    private int goldRate;


    public int getAppID() {
        return appID;
    }

    public void setAppID(int appID) {
        this.appID = appID;
    }

    public String getGoldName() {
        return goldName;
    }

    public void setGoldName(String goldName) {
        this.goldName = goldName;
    }

    public int getGoldRate() {
        return goldRate;
    }

    public void setGoldRate(int goldRate) {
        this.goldRate = goldRate;
    }

    @Override
    public String toString() {
        return "AppReturnBean{" +
                "appID=" + appID +
                ", goldName='" + goldName + '\'' +
                ", goldRate=" + goldRate +
                '}';
    }
}
