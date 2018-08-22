package com.example.hp.demo.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hp.demo.R;
import com.example.hp.demo.utils.DensityUtil;
import com.example.hp.demo.view.WaterMarkView;
import com.szugyi.circlemenu.view.CircleLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CircleMenuActivity extends AppCompatActivity implements CircleLayout.OnItemClickListener, CircleLayout.OnItemSelectedListener, CircleLayout.OnCenterClickListener, CircleLayout.OnRotationFinishedListener {
    @BindView(R.id.btn)
    Button mBtn;
    @BindView(R.id.logo)
    ImageView mLogo;
    @BindView(R.id.back)
    ImageView mBack;
    @BindView(R.id.title_tv)
    TextView mTitleTv;
    @BindView(R.id.right_btn)
    ImageView mRightBtn;
    @BindView(R.id.one)
    TextView mOne;
    @BindView(R.id.two)
    TextView mTwo;
    @BindView(R.id.three)
    TextView mThree;
    @BindView(R.id.four)
    TextView mFour;
    @BindView(R.id.five)
    TextView mFive;
    @BindView(R.id.six)
    TextView mSix;
    @BindView(R.id.CircleLayout)
    CircleLayout mCircleLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cirlce_menu);
        ButterKnife.bind(this);

        setShowWaterMark(true);
        hasShowWaterMark = false;
        initView();


    }

    private void initView() {
        mCircleLayout.setFirstChildPosition(CircleLayout.FirstChildPosition.NORTH);
        mCircleLayout.setOnItemClickListener(this);
        mCircleLayout.setOnRotationFinishedListener(this);
        mCircleLayout.setOnCenterClickListener(this);
        mCircleLayout.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemClick(View view) {

    }

    @Override
    public void onItemSelected(View view) {

    }

    @Override
    public void onCenterClick() {

    }


    @Override
    public void onRotationFinished(View view) {

    }

    @Override
    public void onRotationFinished(int position) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        initwatermark();
    }

    private void initwatermark() {
        if (hasShowWaterMark)
            return;
        if (!showWaterMark)
            return;
        //        PreferenceUtil preferenceUtil = PreferenceUtil.getInstance(this);
        //                String text = preferenceUtil.getSfzh()+"  "+preferenceUtil.getName();
        String text = "领域驱动设计在互联网业务开发中的实践";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            getWindow().getDecorView().setBackground(new WaterMarkView(text));
        } else {
            //            getWindow().getDecorView().setBackgroundDrawable(new WaterMarkView(text));
        }
        ImageView imageView = new ImageView(this);
        imageView.setImageDrawable(new WaterMarkView(text));
        ViewGroup viewGroup = (ViewGroup) getWindow().getDecorView();
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        );


        /**
         * 获取状态栏高度
         * */
        int statusBarHeight = 50;
        //获取status_bar_height资源的ID
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            //根据资源ID获取响应的尺寸值
            statusBarHeight = getResources().getDimensionPixelSize(resourceId);
        }
        Log.e("BaseActivity", "状态栏高度:" + statusBarHeight);
        layoutParams.topMargin = DensityUtil.dip2px(this, 44) + statusBarHeight;
        viewGroup.addView(imageView, layoutParams);
        imageView.bringToFront();

        View headView = findViewById(R.id.head_layout);
        if (headView != null) {
            headView.bringToFront();
        }


        //
        //        Button button = new Button(getApplicationContext());
        //        FrameLayout.LayoutParams layoutParams1 = new FrameLayout.LayoutParams(
        //                ViewGroup.LayoutParams.WRAP_CONTENT,
        //                ViewGroup.LayoutParams.WRAP_CONTENT
        //        );
        //        layoutParams1.topMargin = DensityUtil.dip2px(this, 44) + statusBarHeight;
        //        viewGroup.addView(button, layoutParams1);
        //        button.bringToFront();
        //
        //        button.setOnClickListener(new View.OnClickListener() {
        //            @Override
        //            public void onClick(View v) {
        //                Toast.makeText(CircleMenuActivity.this, "点击了", Toast.LENGTH_SHORT).show();
        //            }
        //        });
        //        hasShowWaterMark = true;
    }

    private boolean showWaterMark = true;
    private boolean hasShowWaterMark;

    public void setShowWaterMark(boolean needShow) {
        showWaterMark = needShow;
    }
}
