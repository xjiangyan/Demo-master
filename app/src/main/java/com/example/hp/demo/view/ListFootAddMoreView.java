package com.example.hp.demo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AbsListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.hp.demo.R;
import com.example.hp.demo.utils.DensityUtil;
import com.example.hp.demo.view.inter.IListFooterView;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ListFootAddMoreView extends RelativeLayout implements IListFooterView {

    @BindView(R.id.loadTv)
    TextView loadMoreBtn;

    public ListFootAddMoreView(Context context) {
        super(context);
    }

    public ListFootAddMoreView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ListFootAddMoreView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ListFootAddMoreView init(int height, final OnLoadinglistener listener, Context context) {
        View.inflate(getContext(), R.layout.list_foot_add_more_view, this);
        ButterKnife.bind(this);

//        loadMoreBtn.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (listener != null) {
//                    listener.startLoadMore();
//                }
//            }
//        });

        AbsListView.LayoutParams lp = new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT,
                DensityUtil.dip2px(context, height));
        setLayoutParams(lp);
        hideFootView();
        this.listener = listener;

        return this;
    }

    public void hideFootView() {
        setVisibility(View.GONE);
    }
    public void showFootView() {
        setVisibility(View.VISIBLE);
        showLoadMoreTv();
    }

    public void showLoadMoreTv() {
        setVisibility(View.VISIBLE);
        loadMoreBtn.setText("上拉加载更多");
//        loadMoreBtn.setClickable(true);
        loadMoreBtn.setVisibility(View.VISIBLE);
    }
    public void showNoMoreData() {
        setVisibility(View.VISIBLE);
        loadMoreBtn.setText("没有更多数据了");
//        loadMoreBtn.setClickable(false);
        loadMoreBtn.setVisibility(View.VISIBLE);
    }
    public void startLoadAnim() {
        setVisibility(View.VISIBLE);
        loadMoreBtn.setVisibility(View.GONE);
    }

    public static interface OnLoadinglistener {
        void startLoadMore();
    }
    private OnLoadinglistener listener;
}
