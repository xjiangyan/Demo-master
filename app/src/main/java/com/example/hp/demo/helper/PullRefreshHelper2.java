package com.example.hp.demo.helper;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.example.hp.demo.R;
import com.example.hp.demo.utils.SizeUtils;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.header.StoreHouseHeader;


public class PullRefreshHelper2 {

    private static final String REFRESH_STRING = "ZHILI TECH";

    public static interface PullRefreshListener {
        void onStartRefresh();
    }
    private PullRefreshListener listener;

    public PullRefreshHelper2() {
    }

    public PullRefreshHelper2(PullRefreshListener listener) {
        this.listener = listener;
    }

    public void initPullRefresh(final PtrFrameLayout ptrLayout, Context context) {
        // header
        final StoreHouseHeader header = new StoreHouseHeader(context);
        header.setPadding(0, SizeUtils.dp2px(15), 0, 0);
        header.initWithString(REFRESH_STRING);
        header.setTextColor(ContextCompat.getColor(context, R.color.black));
        ptrLayout.setHeaderView(header);
        ptrLayout.addPtrUIHandler(header);
        ptrLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }

            @Override
            public void onRefreshBegin(final PtrFrameLayout frame) {
                if (listener != null) {
                    listener.onStartRefresh();
                }
            }
        });
    }
}
