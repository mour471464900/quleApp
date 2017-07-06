package com.gzql.mlqy.qule.utils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by Administrator on 2017/6/30
 */
public class HttpUtils {
    private static HttpService mHttpService;
    public static final String BASE_URL = "http://api.daoway.cn";


    public static HttpService init() {
        if (mHttpService == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                   // .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            mHttpService = retrofit.create(HttpService.class);
        }

        return mHttpService;
    }
}
