package com.example.hp.demo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.hp.demo.R;
import com.example.hp.demo.adapter.MyBaseQuickAdapter;
import com.example.hp.demo.constant.Static;
import com.example.hp.demo.utils.FinalHttpUtil;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

public class AfinalActivity extends AppCompatActivity {

    private FinalHttpUtil finalHttpUtil;
    private RecyclerView recyclerView;
    private RefreshLayout refreshLayout;
    private MyBaseQuickAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afinal);

        initView();

    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        refreshLayout = (RefreshLayout) findViewById(R.id.refreshLayout);


        //设置 Header 为 贝塞尔雷达 样式
        //refreshLayout.setRefreshHeader(new BezierRadarHeader(this).setEnableHorizontalDrag(true));
        refreshLayout.setRefreshHeader(new ClassicsHeader(this));
        //设置 Footer 为 球脉冲 样式
        //refreshLayout.setRefreshFooter(new BallPulseFooter(this).setSpinnerStyle(SpinnerStyle.Scale));
        refreshLayout.setRefreshFooter(new ClassicsFooter(this));

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
                Static.toastShort(AfinalActivity.this, "下拉刷新");
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
                Static.toastShort(AfinalActivity.this, "加载更多");

            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(AfinalActivity.this, LinearLayoutManager.VERTICAL, true));
        mAdapter = new MyBaseQuickAdapter(R.layout.item_view, getApplication());        mAdapter.openLoadAnimation();

        recyclerView.setAdapter(mAdapter);
    }


}
