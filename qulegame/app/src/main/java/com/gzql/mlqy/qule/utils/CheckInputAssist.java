package com.gzql.mlqy.qule.utils;

import java.util.regex.Pattern;

/**
 * Created by LeonYu on 2017/5/19.
 */

public class CheckInputAssist {

    public static final int INPUT_CHECK_OK = 0;
    public static final int INPUT_CHECK_USERNAME_IILIGAL = 1001;
    public static final int INPUT_CHECK_PASSWORD_TOO_SHORT = 2001;
    public static final int INPUT_CHECK_MOBILE_IILIGAL = 1002;


    public static boolean isMobile(String mobile){

        String regEx = "^1[345789]\\d{9}$";
        Pattern pattern = Pattern.compile(regEx);
        return pattern.matcher(mobile).matches();
    }

    public static boolean isEmail(String email){
        String emailRegexp = "^([\\w_\\.-])+@([\\w_-])+((\\.[\\w_-]+)+)$";
        return Pattern.matches(emailRegexp, email);

    }

    public static boolean isUsername(String username){
        String userRegexp = "^[a-zA-Z][a-zA-Z0-9_]{5,17}$";
        return Pattern.matches(userRegexp, username);
    }

    public static String getAccountType(String account){
        if(isMobile(account)) return "mob";
        if(isEmail(account)) return "email";
        if(isUsername(account)) return "username";
        return "uid";
    }

    public static int isRegPasswordValid(String passowrd){
        if(passowrd.length() < 6){
            return INPUT_CHECK_PASSWORD_TOO_SHORT;
        }
        return INPUT_CHECK_OK;
    }

    public static int isRegUserValid(String username, String password) {
        if(!isUsername(username)){
            return INPUT_CHECK_USERNAME_IILIGAL;
        }
        return isRegPasswordValid(password);
    }

    public static int isRegMobValid(String mob, String password){
        if(!isMobile(mob)){
            return INPUT_CHECK_MOBILE_IILIGAL;
        }
        return isRegPasswordValid(password);
    }
}
