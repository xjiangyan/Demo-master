package com.example.hp.demo.bean;

import android.content.Intent;

/**
 * 主页的recycleview数据
 */
public class MainTabBean {
    public String name;//标题
    public int resId;//图片id
    public Intent mIntent;//要跳转的intent
    public String info;//简介

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public Intent getIntent() {
        return mIntent;
    }

    public void setIntent(Intent intent) {
        mIntent = intent;
    }

}
