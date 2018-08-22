package com.example.hp.demo.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.hp.demo.R;
import com.example.hp.demo.adapter.MyBaseQuickAdapter;
import com.example.hp.demo.adapter.StickyDecoration;
import com.example.hp.demo.bean.TestBean;
import com.example.hp.demo.constant.Static;
import com.example.hp.demo.utils.DensityUtil;
import com.example.hp.demo.utils.HttpRequestUtil;
import com.example.hp.demo.utils.PullRefreshHelper;
import com.example.hp.demo.view.SideBar;
import com.example.hp.demo.vp.BasePresenter;
import com.example.hp.demo.vp.GroupListener;
import com.example.hp.demo.vp.OnGroupClickListener;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import net.tsz.afinal.http.AjaxParams;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.hp.demo.R.id.sideBar;
import static com.example.hp.demo.constant.Static.THEURL;
import static com.example.hp.demo.constant.Static.appkey;

public class WebActivity extends AppCompatActivity implements BasePresenter {


    //    @BindView(R.id.ptr_layout)
    //    PtrClassicFrameLayout mPtrLayout;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(sideBar)
    SideBar mSideBar;
    @BindView(R.id.dialog_text)
    TextView mDialogText;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    private HttpRequestUtil mHttpRequestUtil;
    private MyBaseQuickAdapter mAdapter;
    private List<TestBean.ResultBeanX.ResultBean.ListBean> data = new ArrayList<>();
    private List<TestBean.ResultBeanX.ResultBean.ListBean> mList;
    private HashMap<String, Integer> positionMap = new HashMap<>();

    List<TestBean.ResultBeanX.ResultBean.ListBean> dataList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        ButterKnife.bind(this);

        mHttpRequestUtil = HttpRequestUtil.getInstance().init(this, getApplicationContext());
        mHttpRequestUtil.get(THEURL, TestBean.class);

        //        initPtr();
        initSmartPullLayout();
        initRecycleView();
    }

    private void initSmartPullLayout() {


        //设置 Header 为 贝塞尔雷达 样式
        //refreshLayout.setRefreshHeader(new BezierRadarHeader(this).setEnableHorizontalDrag(true));
        mRefreshLayout.setRefreshHeader(new ClassicsHeader(this));
        //设置 Footer 为 球脉冲 样式
        mRefreshLayout.setRefreshFooter(new BallPulseFooter(this).setSpinnerStyle(SpinnerStyle.Scale));

        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                //                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
                Static.toastShort(WebActivity.this, "下拉刷新");
                mRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mRefreshLayout.finishLoadMore();
                        mAdapter.setNewData(mList);


                    }
                }, 2000);


            }
        });

        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                //                refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
                Static.toastShort(WebActivity.this, "加载更多");
                mRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mRefreshLayout.finishLoadMore(50);
                        mAdapter.setNewData(mList);
                    }
                }, 2000);
            }
        });

    }


    private void initRecycleView() {
        mAdapter = new MyBaseQuickAdapter(R.layout.item_view, getApplication());

        /**
         * 自定义一个 ItemDecoration 通常要根据需要，复写它的 3 个方法。
         * getItemOffsets 撑开 ItemView 上、下、左、右四个方向的空间
         * onDraw 在 ItemView 内容之下绘制图形
         * onDrawOver 在 ItemView 内容之上绘制图形。
         */
        StickyDecoration decoration = StickyDecoration.Builder
                .init(new GroupListener() {
                    @Override
                    public String getGroupName(int position) {
                        //组名回调
                        if (dataList.size() > position) {
                            //获取组名，用于判断是否是同一组
                            return dataList.get(position).getAnswer();
                        }
                        return null;
                    }
                })
                .setGroupBackground(Color.parseColor("#48BDFF"))  //背景色
                .setGroupHeight(DensityUtil.dip2px(this, 35))     //高度
                .setDivideColor(Color.parseColor("#CCCCCC"))      //分割线颜色
                .setDivideHeight(DensityUtil.dip2px(this, 1))     //分割线高度 (默认没有分割线)
                .setGroupTextColor(Color.BLACK)                   //字体颜色 （默认）
                .setGroupTextSize(DensityUtil.sp2px(this, 15))    //字体大小
                .setTextSideMargin(DensityUtil.dip2px(this, 10))  // 边距   靠左时为左边距  靠右时为右边距
                .setOnClickListener(new OnGroupClickListener() {  //点击事件，返回当前分组下的第一个item的position
                    @Override
                    public void onClick(int position) {           //Group点击事件
                        String content = "onGroupClick --> " + dataList.get(position).getAnswer();
                        Toast.makeText(WebActivity.this, content, Toast.LENGTH_LONG).show();
                    }
                })
                .build();

        mRecyclerView.addItemDecoration(decoration);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                Log.e("WebActivity", "点击了" + i);
            }
        });
        mAdapter.openLoadAnimation();
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mRecyclerView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter.setNewData(mList);
                        mAdapter.loadComplete();

                    }
                }, 500);

            }
        });
        mRecyclerView.setOnScrollListener(scrollListener);
        mSideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {
            @Override
            public void onTouchingLetterChanged(String s) {
                mDialogText.setText(s);
                mSideBar.setView(mDialogText);
                if (positionMap.get(s) != null) {
                    int i = positionMap.get(s);
                    ((LinearLayoutManager) mRecyclerView.getLayoutManager()).scrollToPositionWithOffset(i + 1, 0);
                }
            }
        });
        /**
         *  //如果内容更改不会更改RecyclerView  的布局大小，请使用此设置来提高性能；

         //将其设置为true并不意味着RecyclerView大小是固定的，只是意味着它不会因适配器内容的更改而改变。

         //当您在RecyclerView中添加或删除项目，并且不更改其高度或宽度时，请将setHasFixedSize设置为true避免不必要的布局传递。

         */
        mRecyclerView.setHasFixedSize(true);
    }

    private RecyclerView.OnScrollListener scrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                mSideBar.setVisibility(View.VISIBLE);
            }
        }
    };

    private void initPtr() {
        final PullRefreshHelper helper = new PullRefreshHelper(new PullRefreshHelper.PullRefreshListener() {
            @Override
            public void onStartRefresh() {
                //                mPtrLayout.postDelayed(new Runnable() {
                //                    @Override
                //                    public void run() {
                //                        mPtrLayout.refreshComplete();
                //                        mAdapter.setNewData(mList);
                //                    }
                //                }, 50);
            }

            @Override
            public void onStartLoadMore() {

            }

            @Override
            public boolean canLoadMore() {
                return false;
            }
        }, getApplicationContext());
        //        helper.initPullRefresh(mPtrLayout, mRecyclerView);

    }

    @Override
    public void onSuccess(Object result) {

        Log.e("WebActivity", "联网成功---------" + result.toString());
        TestBean results = (TestBean) result;

        mList = results.getResult().getResult().getList();

        Log.e("WebActivity", "数据----------" + mList.get(0).getQuestion());

        //        setPullAction(mList);
        //根据answer来排序
        Collections.sort(mList, new Comparator<TestBean.ResultBeanX.ResultBean.ListBean>() {
            @Override
            public int compare(TestBean.ResultBeanX.ResultBean.ListBean o1, TestBean.ResultBeanX.ResultBean.ListBean o2) {
                return o1.getAnswer().compareTo(o2.getAnswer());
            }
        });
        for (int i = 0; i < mList.size(); i++) {
            if (positionMap.get(mList.get(i).getAnswer()) == null)
                positionMap.put(mList.get(i).getAnswer(), i);
        }
        dataList.addAll(mList);

        mAdapter.setNewData(mList);

    }

    @Override
    public void onFailure(Throwable t, int errorNo, String strMsg) {
        Log.e("WebActivity", "联网失败---------" + strMsg);
        //        mTv.setText(strMsg);
    }

    @Override
    public AjaxParams getParams() {
        AjaxParams params = new AjaxParams();
        params.put("type", "c1");
        params.put("subject", "1");
        params.put("pagesize", "30");
        params.put("pagenum", "2");
        params.put("sort", "normal");
        params.put("appkey", appkey);
        return params;
    }
}
