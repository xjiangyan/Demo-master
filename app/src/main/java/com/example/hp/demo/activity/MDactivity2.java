package com.example.hp.demo.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.hp.demo.R;
import com.example.hp.demo.view.TestScrollView;

public class MDactivity2 extends AppCompatActivity implements TestScrollView.onScrollViewListener {

    private TextView mTv_title;
    private RelativeLayout mRela_top;
    private TestScrollView mScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mdactivity2);
        mScrollView = (TestScrollView) findViewById(R.id.scrollView);
        mTv_title = (TextView) findViewById(R.id.tv_title);
        mRela_top = (RelativeLayout) findViewById(R.id.rela_top);
        mScrollView.setOnScrollListener(this);
    }

    @Override
    public void onScroll(int l, int t, int oldl, int oldt) {
        if (t <= 0) { //顶部图处于最顶部，标题栏透明
            mRela_top.setVisibility(View.GONE);
            mRela_top.setBackgroundColor(Color.argb(0, 0, 0, 0));
        } else if (t > 0 && t < 50) { //滑动过程中，渐变
            mRela_top.setVisibility(View.VISIBLE);
            float scale = (float) t / 50;//算出滑动距离比例
            float alpha = (255 * scale);//得到透明度
            mRela_top.setBackgroundColor(Color.argb((int) alpha, 67, 135, 245));
        } else { //过顶部图区域，标题栏定色

            mRela_top.setVisibility(View.VISIBLE);
            mRela_top.setBackgroundColor(Color.argb(255, 67, 135, 245));
        }
    }
}
