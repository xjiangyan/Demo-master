package com.example.hp.demo.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.hp.demo.R;
import com.example.hp.demo.adapter.NewsAdapter;
import com.example.hp.demo.databinding.ActivityPageListViewBinding;
import com.example.hp.demo.view.PageListView;
import com.example.hp.demo.vp.INewsList;
import com.example.hp.demo.vp.impl.NewsPresenter;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;

public class PageListViewActivity extends AppCompatActivity implements INewsList.View {
    private ActivityPageListViewBinding binding;
    private NewsAdapter mAdapter;
    private NewsPresenter presenter;
    private int sum;
    private MaterialSearchView mSearchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_page_list_view);
        presenter = new NewsPresenter(this, getApplicationContext());
        initView();
        binding.pageListView.startRefresh(true);
        handerBackToolar(binding.toolbar, "返回");
        initSearchView();

    }


    protected void handerBackToolar(Toolbar toolbar, String title) {
        //getmNavButtonView();
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Drawable drawable = toolbar.getNavigationIcon();//
        drawable.setColorFilter(getResources().getColor(R.color.dodgerblue), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(drawable);
        toolbar.setTitleTextColor(getResources().getColor(R.color.dodgerblue));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void initView() {
        mSearchView = (MaterialSearchView) findViewById(R.id.search_view);

        mAdapter = new NewsAdapter(R.layout.newsitem, getApplicationContext());

        //        binding.pageListView.startRefresh(true);
        binding.pageListView.setPullRefreshListener(new PageListView.PullRefreshListener() {
            @Override
            public void startPullRefresh() {
                refresh();
            }

            @Override
            public void startLoadMore() {
                presenter.startLoad(1);
            }
        });
        binding.pageListView.setAdapter(mAdapter, true);
        binding.pageListView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                Toast.makeText(PageListViewActivity.this, "点击了第" + i + "项", Toast.LENGTH_SHORT).show();
                Log.e(TAG, ("点击了第" + i + "项"));

            }

            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                super.onItemChildClick(adapter, view, position);
                if (view.getId() == R.id.iv_newsitem) {
                    Toast.makeText(PageListViewActivity.this, "点击了图片", Toast.LENGTH_SHORT).show();

                }
            }
        });


    }

    private void refresh() {
        binding.pageListView.clearData();
        binding.pageListView.reset();
        binding.infoLayout.setVisibility(View.GONE);
        binding.sumTv.setText("0");
        presenter.startLoad(0);
    }

    @Override
    public void stopRefresh() {
        binding.pageListView.stopRefresh();

    }

    @Override
    public void updateView(List datas) {
//        mAdapter.setcount(datas.size());
        binding.pageListView.updatePageDatas(datas);
        //		        isDataLoaded = true;

    }

    @Override
    public void showErrorInfo(int type) {
        binding.pageListView.setErrorInfo(type);
    }

    @Override
    public void setTotal(int total) {
        sum = total;
        if (sum != 0) {
            binding.infoLayout.setVisibility(View.VISIBLE);
            binding.sumTv.setText(sum + "");
        }
        binding.pageListView.setTotal(10000);//总计根据后面需求该改
    }

    @Override
    public int getPage() {
        return binding.pageListView.getPage();

    }


    private void initSearchView() {
        //初始化SearchBar
        mSearchView.setVoiceSearch(false);
        mSearchView.setCursorDrawable(R.drawable.custom_cursor);
        mSearchView.setEllipsize(true);
        mSearchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(PageListViewActivity.this, "开始搜索", Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Toast.makeText(PageListViewActivity.this, "搜索条件变了", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        mSearchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                Toast.makeText(PageListViewActivity.this, "mSearchView出现了", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onSearchViewClosed() {
                Toast.makeText(PageListViewActivity.this, "mSearchView关闭了", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == MaterialSearchView.REQUEST_VOICE && resultCode == RESULT_OK) {
            ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            if (matches != null && matches.size() > 0) {
                String searchWrd = matches.get(0);
                if (!TextUtils.isEmpty(searchWrd)) {
                    mSearchView.setQuery(searchWrd, false);
                    Toast.makeText(PageListViewActivity.this, "setQuery", Toast.LENGTH_SHORT).show();
                }
            }

            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {
        if (mSearchView.isSearchOpen()) {
            mSearchView.closeSearch();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem item = menu.findItem(R.id.action_search);
        mSearchView.setMenuItem(item);

        return true;
    }
}
