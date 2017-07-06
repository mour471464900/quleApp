package com.gzql.mlqy.qule.utils;

import com.google.gson.Gson;
import com.gzql.mlqy.qule.bean.ReturnBean;


import java.io.Reader;
import java.io.StringReader;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Administrator on 2017/5/3.
 */

public class JsonUtil {

    public static <T> ReturnBean<T> fromJson(String json, Class<T> clazz){
        if(json == null) return null;
        StringReader reader = new StringReader(json);
        return fromJson(reader, clazz);
    }

    public static <T> ReturnBean<T> fromJson(Reader reader, Class<T> clazz){
        ReturnBean<T> r = null;
        Type type = new ParameterizedTypeImpl(ReturnBean.class, new Class[]{clazz});

        try {
            r = (new Gson()).fromJson(reader, type);
        }catch (Exception e){
            r = null;
        }
        return r;
    }

    public static <T> ReturnBean<List<T>> fromJsonArray(String json, Class<T> clazz) {
        if(json == null) return null;
        StringReader reader = new StringReader(json);
        return fromJsonArray(reader, clazz);
    }
    public static <T> ReturnBean<List<T>> fromJsonArray(Reader reader, Class<T> clazz){
        // 生成List<T> 中的 List<T>
        Type listType = new ParameterizedTypeImpl(List.class, new Class[]{clazz});
        // 根据List<T>生成完整的Result<List<T>>
        Type type = new ParameterizedTypeImpl(ReturnBean.class, new Type[]{listType});
        ReturnBean<List<T>> r = null;
        try{
            r = (new Gson()).fromJson(reader, type);
        }catch (Exception e){
            r = null;
        }
        return r;
    }


}

class ParameterizedTypeImpl implements ParameterizedType {
    private final Class raw;
    private final Type[] args;
    public ParameterizedTypeImpl(Class raw, Type[] args) {
        this.raw = raw;
        this.args = args != null ? args : new Type[0];
    }
    @Override
    public Type[] getActualTypeArguments() {
        return args;
    }
    @Override
    public Type getRawType() {
        return raw;
    }
    @Override
    public Type getOwnerType() {return null;}
}
