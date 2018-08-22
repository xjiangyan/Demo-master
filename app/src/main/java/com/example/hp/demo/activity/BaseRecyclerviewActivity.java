package com.example.hp.demo.activity;

import android.graphics.drawable.BitmapDrawable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.hp.demo.BaseActivity;
import com.example.hp.demo.R;
import com.example.hp.demo.adapter.NewsAdapter;
import com.example.hp.demo.api.NetworkApi;
import com.example.hp.demo.bean.TestBean;
import com.example.hp.demo.bean.ZhuanLanAuthor;
import com.example.hp.demo.constant.Static;
import com.example.hp.demo.view.LoadMoreview;
import com.example.hp.demo.view.RecyclerRefreshLayout;
import com.example.hp.demo.view.shapeloading.LoadingView;
import com.example.hp.demo.vp.INewsList;
import com.example.hp.demo.vp.impl.NewsPresenter;
import com.socks.library.KLog;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseRecyclerviewActivity extends BaseActivity implements RecyclerRefreshLayout.SuperRefreshLayoutListener, INewsList.View {

    @BindView(R.id.edi_search)
    EditText mEdiSearch;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.RecyclerRefreshLayout)
    RecyclerRefreshLayout mRecyclerRefreshLayout;
    @BindView(R.id.iv_error)
    ImageView mIvError;
    @BindView(R.id.rela_error)
    RelativeLayout mRelaError;
    @BindView(R.id.tv_errormessage)
    TextView mTvErrormessage;
    @BindView(R.id.title)
    View mTitle;
    @BindView(R.id.loadView)
    LoadingView mLoadView;
    @BindView(R.id.rela_loading)
    RelativeLayout mRelaLoading;
    @BindView(R.id.btn_search)
    Button mBtnsearch;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    private NewsAdapter mAdapter;
    private NewsPresenter presenter;
    private boolean isrefresh;
    private LoadMoreview footview;
    private int total = 0;


    @Override
    public void initView() {
        setContentView(R.layout.activity_base_recyclerview);
        ButterKnife.bind(this);
    }

    @Override
    public void onCreateLoadData() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(BaseRecyclerviewActivity.this));
        mAdapter = new NewsAdapter(R.layout.newsitem, getApplicationContext());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                Toast.makeText(BaseRecyclerviewActivity.this, "点击了第" + i + "项", Toast.LENGTH_SHORT).show();
                Log.e(TAG, ("点击了第" + i + "项"));
            }

            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                super.onItemChildClick(adapter, view, position);
                if (view.getId() == R.id.iv_newsitem) {
                    Toast.makeText(BaseRecyclerviewActivity.this, "点击了图片", Toast.LENGTH_SHORT).show();
                }
            }
        });

        footview = new LoadMoreview(BaseRecyclerviewActivity.this);
        mAdapter.addFooterView(footview);

        mRecyclerRefreshLayout.setSuperRefreshLayoutListener(this);
        mRecyclerRefreshLayout.setEnabled(true);
        mRecyclerRefreshLayout.setColorSchemeResources(
                R.color.swiperefresh_color1, R.color.swiperefresh_color2,
                R.color.swiperefresh_color3, R.color.swiperefresh_color4);


        //        mRelaLoading.setVisibility(View.VISIBLE);
        //        onRefreshing();



    }

    @Override
    public void onResumeLoadData() {

    }

    @Override
    protected void onCreateDataInit() {
        super.onCreateDataInit();
        presenter = new NewsPresenter(this, getApplicationContext());

    }

    @OnClick({R.id.iv_error, R.id.btn_search})
    public void onViewClicked(View v) {
        switch (v.getId()) {
            case R.id.iv_error:
                mRelaLoading.setVisibility(View.VISIBLE);
                mRelaError.setVisibility(View.GONE);
                mRecyclerRefreshLayout.onRefresh();
                break;
            case R.id.btn_search:
                //                showPop();
                getDatafromnet();
                getDatafromnet2();
                break;
        }

    }


    public static final String API_URL = "https://zhuanlan.zhihu.com";

    private void getDatafromnet2() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        NetworkApi api = retrofit.create(NetworkApi.class);

        Call<ZhuanLanAuthor> call = api.getAuthor("qinchao");
        // 请求数据，并且处理response
        call.enqueue(new Callback<ZhuanLanAuthor>() {
            @Override
            public void onResponse(Call<ZhuanLanAuthor> call, Response<ZhuanLanAuthor> response) {
                Log.e("tag", response.body().getCreator().getDescription());
                KLog.e(response.body().getCreator().getDescription());
            }

            @Override
            public void onFailure(Call<ZhuanLanAuthor> call, Throwable t) {
                Log.e("tag", t.toString());
                KLog.e();
                t.toString();
            }

        });


    }

    public static final String THEURLBY2 = "https://way.jd.com/";//驾照

    private void getDatafromnet() {

        Retrofit retrofit = new Retrofit.Builder()
                //使用自定义的mGsonConverterFactory解析数据转成javabean
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(THEURLBY2)//只能传baseURL?  结尾必须是/结束
                .build();
        NetworkApi mApi = retrofit.create(NetworkApi.class);

        HashMap<String, String> params = new HashMap<>();
        params.put("type", "c1");
        params.put("subject", "1");
        params.put("pagesize", "30");
        params.put("pagenum", "2");
        params.put("sort", "normal");
        params.put("appkey", Static.appkey);
        Call<TestBean> news = mApi.getQuestion(params);
        news.enqueue(new Callback<TestBean>() {//异步
            @Override
            public void onResponse(Call<TestBean> call, Response<TestBean> response) {
                Log.e("tag", "成功：" + call + "******" + response.body().getResult().getResult().getList().get(0).getQuestion() + "");
                KLog.e("成功：" + call + "******" + response.body().getResult().getResult().getList().get(0).getQuestion() + "");
                Log.e("tag", response.body().toString());
                KLog.e(response.body());
            }

            @Override
            public void onFailure(Call<TestBean> call, Throwable t) {
                Log.e("tag", "失败" + call + "******" + t + "");
                KLog.e("失败" + call + "******" + t + "");

            }
        });


    }

    @Override
    public void onRefreshing() {
        presenter.startLoad(0);
        isrefresh = true;
    }

    @Override
    public void onLoadMore() {
        mRelaError.setVisibility(View.GONE);
        presenter.startLoad(1);
        isrefresh = false;
    }

    @Override
    public void onScrollToBottom() {

    }

    @Override
    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public int getPage() {
        return 0;
    }

    @Override
    public void stopRefresh() {
    }

    @Override
    public void updateView(List datas) {

        mRecyclerRefreshLayout.onComplete();

        //        if (datas.size() <= 10) {
        //            if (mAdapter.getData().size() <= 10) {
        //                footview.hideView();
        //            }
        //        } else {
        //            footview.showView();
        //        }

        mRelaLoading.setVisibility(View.GONE);

        if (isrefresh) {
            mAdapter.setNewData(datas);
        } else {
            mAdapter.addData(datas);
        }


        if (total > mAdapter.getData().size()) {
            footview.showView();
            mRecyclerRefreshLayout.setCanLoadMore(true);
        } else {
            mRecyclerRefreshLayout.setCanLoadMore(false);
            footview.hideView();
        }
    }

    @Override
    public void showErrorInfo(int type) {
        String msg = "抱歉，没有数据";
        switch (type) {
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
        mRecyclerRefreshLayout.onComplete();
        mRelaError.setVisibility(View.VISIBLE);
        mTvErrormessage.setText(msg);
    }

    private void showPop() {

        View popupView = LayoutInflater.from(this).inflate(R.layout.pop_add, null);
        PopupWindow popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        int popupWidth = popupView.getMeasuredWidth();
        int popupHeight = popupView.getMeasuredHeight();

        int[] location = new int[2];
        fab.getLocationOnScreen(location);
        popupWindow.showAtLocation(fab, Gravity.NO_GRAVITY, (location[0] + fab.getWidth() / 2) - popupWidth / 2,
                location[1] - popupHeight);

        TextView tv_renlian = (TextView) popupWindow.getContentView().findViewById(R.id.tv_renlian);
        TextView tv_luren = (TextView) popupWindow.getContentView().findViewById(R.id.tv_luren);


        tv_renlian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "点击了人脸识别", Toast.LENGTH_SHORT).show();
            }
        });
        tv_luren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "点击了路人识别", Toast.LENGTH_SHORT).show();

            }
        });

    }
}
