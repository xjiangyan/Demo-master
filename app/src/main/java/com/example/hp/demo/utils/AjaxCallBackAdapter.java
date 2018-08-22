package com.example.hp.demo.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import net.tsz.afinal.http.AjaxCallBack;

/**
 * Created by ZhiLiSteven on 2017/12/2.
 */

public abstract class AjaxCallBackAdapter extends AjaxCallBack<String> {

    private Class<?> clazz;
    private TypeToken typeToken;

    public AjaxCallBackAdapter(Class<?> clazz, TypeToken typeToken) {
        this.clazz = clazz;
        this.typeToken = typeToken;
    }

    public abstract void doSuccess(Object result);
    public abstract void doFail(Throwable t, int errorNo, String strMsg);

    @Override
    public void onSuccess(String s) {
        Object result = null;
        if (s != null) {
            if (clazz != null || typeToken != null) {
                result = parseJsonToObj(s);
            }
        }
        doSuccess(result);
    }

    @Override
    public void onFailure(Throwable t, int errorNo, String strMsg) {
        doFail(t, errorNo, strMsg);
    }

    private Object parseJsonToObj(String string) {
        Object result = null;
        Gson gson = new Gson();
        try {
            if (clazz != null) {
                result = gson.fromJson(string, clazz);
            } else if (typeToken != null) {
                result = gson.fromJson(string, typeToken.getType());
            }
        } catch (com.google.gson.JsonSyntaxException e) {
            e.printStackTrace();
        }
        return result;
    }
}
