package com.gzql.mlqy.qule.utils;

/**
 * Created by LeonYu on 2017/5/3.
 */

public class QLReturnCode {

    public final static int QL_SUCCESS = 1;
    public static final int QL_ERROR = 0;
    public static final int QL_ERR_SERVICE = -2;

    public static final int QL_ERR_CONNECTION = 10001;
    public static final int QL_ERR_INIT_APP = 10002;

    public static final int QL_ERR_APPID_NOT_FOUND = 20001;

    public static final int QL_ERR_USER_NOT_FOUND = 30002;
    public static final int QL_ERR_USER_FORBIDDEN = 30021;
    public static final int QL_ERR_USER_PASSWORD = 30022;

    public final static int PAY_FAILURE =6002;
    public final static int PAY_CANCAL = 6001;

}
