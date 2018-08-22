package com.example.hp.demo.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.hp.demo.R;
import com.example.hp.demo.constant.Static;
import com.example.hp.demo.utils.PullRefreshHelper;
import com.example.hp.demo.view.inter.IListFooterView;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;



public class PageListView<T> extends RelativeLayout {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.ptr_layout)
    PtrClassicFrameLayout ptrLayout;
    @BindView(R.id.error_view)
    ErrorView errorView;
    @BindView(R.id.loadAnimView)
    public AVLoadingIndicatorView loadAnimView;

    private IListFooterView footerView;
    private BaseQuickAdapter adapter;

    private int page = 1;
    private int total = 0;
    private int rows = 12;
    private boolean noMoreData = false;

    private List<T> listDatas;
    private Context context;

    public PageListView(Context context) {
        super(context);
        init(context);
    }

    public PageListView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PageListView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    public interface PullRefreshListener {
        void startPullRefresh();
        void startLoadMore();
    }

    private PullRefreshListener pullRefreshListener;

    public void setPullRefreshListener(PullRefreshListener pullRefreshListener) {
        this.pullRefreshListener = pullRefreshListener;
    }

    private void init(Context context) {
        this.context = context;
        View.inflate(context, R.layout.view_page_list_view, this);
        ButterKnife.bind(this);

        listDatas = new ArrayList<>();

        final PullRefreshHelper pullRefreshHelper = new PullRefreshHelper(new PullRefreshHelper.PullRefreshListener() {
            @Override
            public void onStartRefresh() {
                loadAnimView.setVisibility(VISIBLE);
                if (footerView != null) {
                    footerView.hideFootView();
                }
                if (pullRefreshListener != null) {
                    pullRefreshListener.startPullRefresh();
                }
            }

            @Override
            public void onStartLoadMore() {
                if (pullRefreshListener != null) {
                    if (footerView != null) {
                        footerView.hideFootView();
                    }
                    pullRefreshListener.startLoadMore();
                }
            }

            @Override
            public boolean canLoadMore() {
                return !noMoreData;
            }
        }, context);
        pullRefreshHelper.initPullRefresh(ptrLayout, recyclerView);

    }

    public void clearData() {
        listDatas.clear();
        adapter.notifyDataSetChanged();
    }
    public void clearDataNoRefresh() {
        listDatas.clear();
    }

    public void addOnItemTouchListener(RecyclerView.OnItemTouchListener listener) {
        recyclerView.addOnItemTouchListener(listener);
    }

    public void setFooterView(IListFooterView footerView) {
        this.footerView = footerView;
    }
    public void setAdapter(BaseQuickAdapter adapter, boolean needShowLoadMore) {
        this.adapter = adapter;

        if (needShowLoadMore) {
            addDefaultFooterView(adapter);
        }
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

    public void hideFooterView() {
        if (footerView != null) {
            footerView.hideFootView();
        }
    }

    public boolean isNoMoreData() {
        return noMoreData;
    }

    private void addDefaultFooterView(BaseQuickAdapter adapter) {
        footerView = new ListFootAddMoreView(context).init(48, null, context);
        adapter.addFooterView((View) footerView);
    }

    public void stopRefresh() {
        ptrLayout.refreshComplete();
        loadAnimView.setVisibility(GONE);
    }
    public void startRefresh(boolean showUpRefresh) {
        if (showUpRefresh) {
            ptrLayout.autoRefresh();
        }
        loadAnimView.setVisibility(VISIBLE);
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public void updatePageDatas(List datas) {
        //如果数据没有或者解析异常，直接返回
        if (datas == null) {
            //隐藏下面的footview
            if (footerView != null) {
                footerView.hideFootView();
            }
            if (listDatas.size() == 0) {
                errorView.show(0);
            }
            return;
        }
        //如果数据为零
        if (datas.size() == 0) {
            //先显示没有更多数据
            if (footerView != null) {
                footerView.showNoMoreData();
                noMoreData = true;
            }
            //如果总数据也是零
            if (listDatas.size() == 0) {
                //隐藏footview，显示没有数据
                if (footerView != null) {
                    footerView.hideFootView();
                }
                errorView.show(0);
            }
            return;
        }
        //如果有数据，先加进列表里
        if (datas.size() > 0) {
            listDatas.addAll(datas);
        }

        if (total == 0) {
            //如果没有设置总数
            if (footerView != null) {
                noMoreData = true;
                footerView.showNoMoreData();
            }
        } else {
            //如果已经积累的数据还没到达总数
            if (listDatas.size() < total) {
                //显示加载更多footview，page累加
                if (footerView != null) {
                    footerView.showLoadMoreTv();
                }
                page++;
            } else {
                //否则，footview显示没有数据
                if (footerView != null) {
                    noMoreData = true;
                    footerView.showNoMoreData();
                }
            }
        }

        adapter.setNewData(listDatas);
    }

    public int getPage() {
        return page;
    }

    public void setErrorInfo(int value) {
        if (listDatas.size() == 0) {
            errorView.show(value);
        }
        showErrorInfo(value);
    }

    private  void showErrorInfo(int value) {
        String msg = "抱歉，没有数据";
        switch (value) {
            case 0:
                msg = "抱歉，没有数据";
                break;
            case 1:
                msg = "抱歉，服务未找到";
                break;
            case 2:
                msg = "抱歉，网络异常";
                break;
            case 3:
                msg = "抱歉，服务异常";
                break;
            case 4:
                msg = "抱歉，连接超时";
                break;
            case 5:
                msg = "抱歉，登陆已过期";
                break;
            case 6:
                msg = "抱歉，您没有该权限";
                break;
        }
        Static.toastShort(getContext(), msg);
    }

    public void reset() {
        page = 1;
        errorView.hide();
//        footerView.hideFootView();
    }
}
