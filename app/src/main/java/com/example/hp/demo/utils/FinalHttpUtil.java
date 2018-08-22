package com.example.hp.demo.utils;

import android.content.Context;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import java.util.HashMap;
import java.util.Map;


public class FinalHttpUtil {

    public static final int HTTP_TIMEOUT = 50 * 1000;

    private static FinalHttpUtil instance;
    private FinalHttp finalHttp;

    private FinalHttpUtil() {
        finalHttp = new FinalHttp();
        finalHttp.configTimeout(HTTP_TIMEOUT);
        finalHttp.configRequestExecutionRetryCount(0);
    }

    public synchronized static FinalHttpUtil getInstance() {
        if (instance == null) {
            instance = new FinalHttpUtil();
        }
        return instance;
    }

    /**
     * 获取cookie
     *
     * @param context
     */
    public void init(Context context) {
        boolean configCookieSuccess = finalHttp.configCookieStore();
        if (configCookieSuccess) {
            //已经获取到远端的Session
            boolean sessionInitDone = PreferenceUtil.getInstance(context).getSESSION_INIT_DONE();
            if (!sessionInitDone) {
                //保存一下
                Map<String, String> data = new HashMap<>();
                finalHttp.getCookie(data);
                PreferenceUtil.getInstance(context).setSESSION_ID(data.get("sessionId"));
                PreferenceUtil.getInstance(context).setCOOKIE_DOMAIN(data.get("domain"));
                PreferenceUtil.getInstance(context).setCOOKIE_PATH(data.get("path"));
                PreferenceUtil.getInstance(context).setSESSION_INIT_DONE(true);
            }
        } else {
            //可能已经崩溃过
            String sessionId = PreferenceUtil.getInstance(context).getSESSION_ID();
            String path = PreferenceUtil.getInstance(context).getCOOKIE_PATH();
            String domain = PreferenceUtil.getInstance(context).getCOOKIE_DOMAIN();
            Map<String, String> data = new HashMap<>();
            data.put("sessionId", sessionId);
            data.put("path", path);
            data.put("domain", domain);
            finalHttp.setCookie(data);
        }
    }

    public void post(String url, AjaxParams params, AjaxCallBack callback) {
        finalHttp.post(url, params, callback);
    }
}
