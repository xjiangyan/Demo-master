package com.example.hp.demo.vp;

import net.tsz.afinal.http.AjaxParams;


public interface BasePresenter {
    void onSuccess(Object result);
    void onFailure(Throwable t, int errorNo, String strMsg);
    AjaxParams getParams();
}
