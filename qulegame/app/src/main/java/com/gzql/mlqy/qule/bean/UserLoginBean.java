package com.gzql.mlqy.qule.bean;

/**
 * Created by LeonYu on 2017/5/8.
 */

public class UserLoginBean {

    private int uid;
    private String session;
    private int expire;
    private String token;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public int getExpire() {
        return expire;
    }

    public void setExpire(int expire) {
        this.expire = expire;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "UserLoginBean{" +
                "uid=" + uid +
                ", session='" + session + '\'' +
                ", expire=" + expire +
                ", token='" + token + '\'' +
                '}';
    }
}
