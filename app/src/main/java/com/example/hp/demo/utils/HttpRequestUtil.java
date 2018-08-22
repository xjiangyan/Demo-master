package com.example.hp.demo.utils;

import android.content.Context;

import com.example.hp.demo.constant.Static;
import com.example.hp.demo.vp.BasePresenter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;


public class HttpRequestUtil extends AjaxCallBack<Object> {

    private static HttpRequestUtil instance;

    private FinalHttp finalHttp;
    private BasePresenter presenter;
    private Context context;
    private Class<?> clazz;
    private TypeToken typeToken;
    //xml解析
    private BaseHttpParseHandler handler;

    private HttpRequestUtil() {

    }

    public static HttpRequestUtil getInstance() {
        instance = new HttpRequestUtil();
        return instance;
    }

    public HttpRequestUtil init(Context context) {
        finalHttp = new FinalHttp();
        finalHttp.configTimeout(Static.HTTP_TIMEOUT);
        this.context = context;
        clazz = null;
        typeToken = null;
        handler = null;
        return instance;
    }

    public HttpRequestUtil init(BasePresenter presenter, Context context) {
        if (finalHttp == null) {
            finalHttp = new FinalHttp();
            finalHttp.configTimeout(Static.HTTP_TIMEOUT);
            finalHttp.configRequestExecutionRetryCount(0);
        }
        this.presenter = presenter;
        this.context = context;
        clazz = null;
        typeToken = null;
        handler = null;
        return instance;
    }

    public static FinalHttp getFinalHttp() {
        FinalHttp finalHttp = new FinalHttp();
        finalHttp.configTimeout(Static.HTTP_TIMEOUT);
        finalHttp.configRequestExecutionRetryCount(0);
        return finalHttp;
    }

    public HttpRequestUtil initWithTimeOut(BasePresenter presenter, Context context, int timeout) {
        finalHttp = new FinalHttp();
        finalHttp.configTimeout(timeout);
        finalHttp.configRequestExecutionRetryCount(0);
        this.presenter = presenter;
        this.context = context;
        clazz = null;
        typeToken = null;
        handler = null;
        return instance;
    }

    //    public void cancelAllTask() {
    //        finalHttp.cancelAllTasks();
    //    }

    private void init() {
        clazz = null;
        typeToken = null;
        handler = null;
    }

    public void post(String url) {
        finalHttp.post(url, presenter.getParams(), this);
    }

    public void post(String url, BaseHttpParseHandler handler) {
        init();
        this.handler = handler;
        finalHttp.post(url, presenter.getParams(), this);
    }

    public void get(String url, Class<?> clazz) {
        init();
        this.clazz = clazz;
        finalHttp.get(url, presenter.getParams(), this);
    }

    public void post(String url, AjaxParams params, AjaxCallBack<Object> callBack) {
        finalHttp.post(url, params, callBack);
    }

    public void post(String url, AjaxCallBack<Object> callBack) {
        finalHttp.post(url, presenter.getParams(), callBack);
    }

    public void post(String url, Class<?> clazz) {
        init();
        this.clazz = clazz;
        finalHttp.post(url, presenter.getParams(), this);
    }

    public void post(String url, TypeToken typeToken) {
        init();
        this.typeToken = typeToken;
        finalHttp.post(url, presenter.getParams(), this);
    }

    public void post(String url, AjaxParams params, Class<?> clazz) {
        init();
        this.clazz = clazz;
        finalHttp.post(url, params, this);
    }

    @Override
    public void onSuccess(Object o) {
        Object result = null;
        if (o != null) {
            //            final String string = (String) o;
            //            if (string.contains("error:")) {
            //                ErrorInfo errorInfo = (ErrorInfo) parseJsonToObj(string, ErrorInfo.class);
            //                presenter.onError(errorInfo);
            //                return;
            //            }
            // 解析json数据
            if (clazz != null || typeToken != null) {
                result = parseJsonToObj((String) o);
            }
            // 解析xml数据
            else if (handler != null) {
                result = parseXmlToObj((String) o, handler);
            }
        }
        if (result != null) {
            presenter.onSuccess(result);
        } else {
            presenter.onSuccess(3);
        }
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

    private Object parseJsonToObj(String string, Class<?> clazz) {
        Object result = null;
        Gson gson = new Gson();
        try {
            result = gson.fromJson(string, clazz);
        } catch (com.google.gson.JsonSyntaxException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * xml解析
     *
     * @param string
     * @param handler
     * @return
     */
    private Object parseXmlToObj(String string, BaseHttpParseHandler handler) {
        SAXParserFactory f = SAXParserFactory.newInstance();
        Object result = null;
        try {
            SAXParser parser = f.newSAXParser();
            parser.parse(new InputSource(new StringReader(string)), handler);
            result = handler.getResult();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void onFailure(Throwable t, int errorNo, String strMsg) {
        presenter.onFailure(t, errorNo, strMsg);
    }

    public int dealWithFail(Context context, int errorNo, String msg) {
        if (msg != null && msg.contains("failed to respond")) {
            Static.toastShort(context, "服务未响应！");
            return 2;
        }

        if (errorNo == 404) {
            Static.toastShort(context, "服务未找到！");
            return 1;
        } else if (errorNo == 500) {
            Static.toastShort(context, "服务异常！");
            return 3;
        } else {
            Static.toastShort(context, "网络异常！");
            return 2;
        }
    }
}