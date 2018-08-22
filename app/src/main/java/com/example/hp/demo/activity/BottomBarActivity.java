package com.example.hp.demo.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.hp.demo.R;
import com.example.hp.demo.transition.BottomEnterTransition;
import com.example.hp.demo.transition.TopEnterTransition;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.BottomBarTab;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;
import com.roughike.bottombar.TabSelectionInterceptor;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BottomBarActivity extends AppCompatActivity {
    @BindView(R.id.rela_contain)
    RelativeLayout mRelaContain;
    @BindView(R.id.bottomnavigationview)
    BottomNavigationView mBottomnavigationview;

    private BottomBar bottomBar;

    private BottomBarTab nearby;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_bar);
        ButterKnife.bind(this);

        initBottomBar();
        setTranstion();
        initBottomBar2();

    }

    private void initBottomBar2() {

        mBottomnavigationview.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.recents) {
                    Toast.makeText(getApplicationContext(), "点击了recents", Toast.LENGTH_SHORT).show();
                    return true;
                }
                if (menuItem.getItemId() == R.id.favorites) {
                    Toast.makeText(getApplicationContext(), "点击了favorites", Toast.LENGTH_SHORT).show();
                    return true;

                }
                if (menuItem.getItemId() == R.id.nearby) {
                    Toast.makeText(getApplicationContext(), "点击了nearby", Toast.LENGTH_SHORT).show();
                    return true;

                }
                return false;
            }
        });
    }

    private void setTranstion() {
        getWindow().setEnterTransition(new BottomEnterTransition(this, bottomBar));
        getWindow().setEnterTransition(new TopEnterTransition(this, mBottomnavigationview));
    }

    private void initBottomBar() {
        bottomBar = (BottomBar) findViewById(R.id.bottomBar);

        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                if (tabId == R.id.tab_favorites) {
                    // 选择指定 id 的标签
                    nearby = bottomBar.getTabWithId(R.id.tab_nearby);
                    nearby.setBadgeCount(5);
                }
            }
        });

        bottomBar.setOnTabReselectListener(new OnTabReselectListener() {
            @Override
            public void onTabReSelected(@IdRes int tabId) {
                if (tabId == R.id.tab_favorites) {
                    // 已经选择了这个标签，又点击一次。即重选。
                    nearby.removeBadge();
                }
            }
        });

        bottomBar.setTabSelectionInterceptor(new TabSelectionInterceptor() {
            @Override
            public boolean shouldInterceptTabSelection(@IdRes int oldTabId, @IdRes int newTabId) {
                // 点击无效
                if (newTabId == R.id.tab_restaurants) {
                    // ......
                    // 返回 true 。代表：这里我处理了，你不用管了。
                    return true;
                }

                return false;
            }
        });
    }


}
