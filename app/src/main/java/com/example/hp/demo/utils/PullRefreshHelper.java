package com.example.hp.demo.utils;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.hp.demo.R;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler2;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.header.StoreHouseHeader;


public class PullRefreshHelper {

    private static final String REFRESH_STRING = "JIANG";

    public static interface PullRefreshListener {
        void onStartRefresh();

        void onStartLoadMore();

        boolean canLoadMore();
    }

    private PullRefreshListener listener;
    private Context context;

    public PullRefreshHelper() {
    }

    public PullRefreshHelper(PullRefreshListener listener, Context context) {
        this.listener = listener;
        this.context = context;
    }

    public void initPullRefresh(final PtrClassicFrameLayout ptrLayout, final RecyclerView recyclerView) {
        // header
        final StoreHouseHeader header = new StoreHouseHeader(context);
        header.setPadding(0, DensityUtil.dip2px(context, 15), 0, 0);
        header.initWithString(REFRESH_STRING);
        header.setTextColor(ContextCompat.getColor(context, R.color.black));
        ptrLayout.setHeaderView(header);
        ptrLayout.addPtrUIHandler(header);

        //        final StoreHouseHeader footer = new StoreHouseHeader(context);
        //        footer.setPadding(0, 0, 0, DensityUtil.dip2px(context, 15));
        //        footer.initWithString(REFRESH_STRING);
        //        footer.setTextColor(ContextCompat.getColor(context, R.color.black));
        //        ptrLayout.setFooterView(footer);
        //        ptrLayout.addPtrUIHandler(footer);

        ptrLayout.setPtrHandler(new PtrDefaultHandler2() {
            @Override
            public void onLoadMoreBegin(PtrFrameLayout frame) {
                if (listener != null) {
                    listener.onStartLoadMore();
                }
            }

            @Override
            public boolean checkCanDoLoadMore(PtrFrameLayout frame, View content, View footer) {
                boolean canLoadMore = listener.canLoadMore();
                return super.checkCanDoLoadMore(frame, recyclerView, footer) && canLoadMore;
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return super.checkCanDoRefresh(frame, recyclerView, header);
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
