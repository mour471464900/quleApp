package com.gzql.mlqy.qule.utils;

import android.content.Context;
import android.content.SharedPreferences;





/**
 * @author yanyan
 * @date 2017/6/30
 */

public class DataUtil {



    private static String name = "qulegame.xml";
    private static SharedPreferences mSharedPreferences;


    public static DataUtil instance;

    private DataUtil(Context context) {
        mSharedPreferences = context.getSharedPreferences(name, Context.MODE_APPEND);


    }


    public synchronized static void init(Context context) {
        if (null == instance) {
            new DataUtil(context);
        }

    }

    public static void setSharePreferences( String key, String value){

        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static void setSharePreferences( String key, boolean value){
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static void setSharePreferences( String key, int value){
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public static String getString( String key){

        String value = mSharedPreferences.getString(key, "");
        return value;
    }

    public static boolean getBoolean( String key){
        boolean value = mSharedPreferences.getBoolean(key, false);
        return value;
    }

    public static int getInt( String key){
        int value = mSharedPreferences.getInt(key, 0);
        return value;
    }







    
}
