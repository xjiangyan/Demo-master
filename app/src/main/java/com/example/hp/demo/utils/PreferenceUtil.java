package com.example.hp.demo.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.hp.demo.constant.PcsNingbo;


public class PreferenceUtil {

    private SharedPreferences shared = null;
    private Context context = null;

    private final String SHARED_PREFERENCE_NAME = "policenews_shared";
    private final String POLICE_CODE = "policeId";
    private final String LAST_SUBSCRIBED_DATAS = "lastSubscribedDatas";
    private final String LAST_OTHER_SUBSCRIBE_DATAS = "lastOtherSubscribeDatas";
    private final String PCS_CODE = "pcsCode";

    private final String PARENTID = "parentid";
    private final String USERID = "userid";
    private final String OFFICEID = "officeid";
    private final String XZQH = "xzqh";
    private final String SESSION_ID = "SESSION_ID";
    private final String COOKIE_PATH = "COOKIE_PATH";
    private final String COOKIE_DOMAIN = "COOKIE_DOMAIN";
    private final String SESSION_INIT_DONE = "SESSION_INIT_DONE";
    private final String DAYORNIGHT = "dayornight";//夜間模式
    private final String DAYORNIGHT2 = "dayornight2";//夜間模式
    private final String LoadBigImageView = "loadbigimageview";//是否显示大图

    private static PreferenceUtil instance = null;

    private final static String DefaultSubscribedDatas = "领导动态,图片新闻,领导讲话";
    private final static String DefaultOtherSubscribedDatas =
            "通知通告,工作动态,网络传真,治安简报,治安警情,各地快讯,治安研究,情报信息,法制工作";

    /**
     * 获取实例
     * <p>
     * 注：context为application的context
     */
    public static PreferenceUtil getInstance(Context context) {
        if (null == instance && null != context) {
            instance = new PreferenceUtil(context.getApplicationContext());
        }
        return instance;
    }

    /**
     * 注：context为application的context
     *
     * @param context
     */
    private PreferenceUtil(Context context) {
        this.context = context;
        shared = context.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
    }

    //"020056"
    public String getPoliceNumber() {
        return shared.getString(POLICE_CODE, "");
    }

    public void setPoliceNumber(String policeNumber) {
        if (policeNumber == null || policeNumber.isEmpty())
            policeNumber = "";
        shared.edit().putString(POLICE_CODE, policeNumber).apply();
    }

    public String getPreLastPCSCode() {
        String dept = shared.getString(PCS_CODE, null);
        //dept 转 jgid  金诚的区划CODE
        return PcsNingbo.getInstance().tranDept2Jgid(dept);
    }


    /**
     * 获取实例
     * <p>
     * 注：context为application的context
     */


    public void setSESSION_INIT_DONE(boolean done) {
        shared.edit().putBoolean(SESSION_INIT_DONE, done).apply();
    }

    public boolean getSESSION_INIT_DONE() {
        return shared.getBoolean(SESSION_INIT_DONE, false);
    }

    public void setSESSION_ID(String sessionId) {
        shared.edit().putString(SESSION_ID, sessionId).apply();
    }

    public String getSESSION_ID() {
        return shared.getString(SESSION_ID, null);
    }

    public void setCOOKIE_PATH(String path) {
        shared.edit().putString(COOKIE_PATH, path).apply();
    }

    public String getCOOKIE_PATH() {
        return shared.getString(COOKIE_PATH, null);
    }

    public void setCOOKIE_DOMAIN(String domain) {
        shared.edit().putString(COOKIE_DOMAIN, domain).apply();
    }

    public String getCOOKIE_DOMAIN() {
        return shared.getString(COOKIE_DOMAIN, null);
    }


    public Boolean getDayOrNight() {
        return shared.getBoolean(DAYORNIGHT, false);
    }

    public void setDayOrNight(boolean b) {
        shared.edit().putBoolean(DAYORNIGHT, b).apply();
    }

    public Boolean getDayOrNight2() {
        return shared.getBoolean(DAYORNIGHT2, true);
    }

    public void setDayOrNight2(boolean b) {
        shared.edit().putBoolean(DAYORNIGHT2, b).apply();
    }

    public Boolean getLoadBigImageView() {
        return shared.getBoolean(LoadBigImageView, true);
    }

    public void setLoadBigImageView(boolean b) {
        shared.edit().putBoolean(LoadBigImageView, b).apply();
    }
}
