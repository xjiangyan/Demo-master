package com.example.hp.demo.vp.impl;

import android.content.Context;
import android.widget.Toast;

import com.example.hp.demo.bean.NewsBean;
import com.example.hp.demo.constant.Static;
import com.example.hp.demo.utils.FinalHttpUtil;
import com.example.hp.demo.utils.HttpRequestUtil;
import com.example.hp.demo.vp.INewsList;

import net.tsz.afinal.http.AjaxParams;

import java.lang.ref.WeakReference;

/**
 * @author Admin
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class NewsPresenter implements INewsList.Presenter {
    private final Context context;
    private final INewsList.View view;
    private final WeakReference<Context> weakContext;
    private FinalHttpUtil finalHttpUtil;
    private final HttpRequestUtil requestUtil;

    public NewsPresenter(INewsList.View view, Context context) {
        this.view = view;
        this.context = context;
        this.weakContext = new WeakReference<Context>(context);

        //                finalHttpUtil = FinalHttpUtil.getInstance();
        //        finalHttpUtil.init(context);
        requestUtil = HttpRequestUtil.getInstance().init(this, context);
    }

    @Override
    public void onSuccess(Object result) {
        if (weakContext.get() == null) {
            return;
        }
        if (result instanceof NewsBean) {
            NewsBean searchList = (NewsBean) result;


            try {
                searchList.getResult().getMsg();
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(context, e + "", Toast.LENGTH_SHORT).show();
            }


            if (searchList.getResult().getMsg().equals("ok")) {
                if (searchList.getResult() != null) {
                    view.setTotal(postion + 31);
                    postion = postion + 30;
                    view.updateView(searchList.getResult().getResult().getList());
                } else {
                    view.showErrorInfo(0);
                }
            } else {
                view.showErrorInfo(3);
            }
        } else {
            view.showErrorInfo(3);
        }
        view.stopRefresh();
    }

    @Override
    public void onFailure(Throwable t, int errorNo, String strMsg) {
        if (weakContext.get() == null) {
            return;
        }
        int error = requestUtil.dealWithFail(weakContext.get(), errorNo, strMsg);
        view.showErrorInfo(error);
        view.stopRefresh();
    }

    private int postion = 0;

    @Override
    public AjaxParams getParams() {
        AjaxParams params = new AjaxParams();
        params.put("channel", "头条");
        params.put("num", "30");
        params.put("start", postion + "");
        params.put("appkey", Static.HTTP_KEY);
        return params;
        //        params.put("sj_mjjh", preferenceUtil.getPoliceNumber());
        //        params.put("page", view.getPage() + "");
        //        params.put("rows", "5");
        //        params.put("queryStart", startTime);
        //        params.put("queryEnd", endTime);
        //        params.put("yj_cl_cz", czxm);
        //        params.put("yj_cl_cph", cphm);
    }

    @Override
    public void startLoad(int type) {
        if (type == 0) {
            postion = 0;
        }
        requestUtil.post(Static.BASE_URL, NewsBean.class);
        //        requestUtil.post("http://api.cellocation.com:81/cell/?mcc=0&mnc=0&lac=22545&ci=100696065&output=json", NewsBean.class);
        //        finalHttpUtil.post(Static.BASE_URL, getParams(), new AjaxCallBackAdapter(NewsBean.class, null) {
        //            @Override
        //            public void doSuccess(Object result) {
        //
        //            }
        //
        //            @Override
        //            public void doFail(Throwable t, int errorNo, String strMsg) {
        //
        //            }
        //        });

    }
}
