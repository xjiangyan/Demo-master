package com.example.hp.demo.utils;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

/**
 * Created by nbzl on 2017/8/2.
 */
public class GsonUtil {
    public static GsonUtil gsonUtil;
    public static Gson gson;

    public static GsonUtil getInstance() {
        if (gsonUtil == null) {
            gsonUtil = new GsonUtil();
            gsonUtil.gson = new Gson();
        }
        return gsonUtil;
    }

    public static <T> T getObject(String jsonString, Class<T> cls) {
        T t = null;
        try {
            t = gson.fromJson(jsonString, cls);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    public static <T> List<T> jsonToList(String jsonStr, Class<T[]> type) {
        T[] list = gson.fromJson(jsonStr,type);
        return Arrays.asList(list);
    }

    public static String getJson(Object object) {
        return gson.toJson(object);
    }
}
