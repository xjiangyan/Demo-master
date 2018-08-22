package com.example.hp.demo.activity;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.hp.demo.R;
import com.example.hp.demo.bean.HomeItemBean;
import com.example.hp.demo.view.ZToast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SevenTabActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.iv_bigtitle)
    ImageView mIvBigtitle;
    @BindView(R.id.tv_bigtitle)
    TextView mTvBigtitle;
    @BindView(R.id.rela_top)
    RelativeLayout mRelaTop;
    @BindView(R.id.iv_one)
    ImageView mIvOne;
    @BindView(R.id.tv_one)
    TextView mTvOne;
    @BindView(R.id.iv_two)
    ImageView mIvTwo;
    @BindView(R.id.tv_two)
    TextView mTvTwo;
    @BindView(R.id.iv_three)
    ImageView mIvThree;
    @BindView(R.id.tv_three)
    TextView mTvThree;
    @BindView(R.id.line_one)
    LinearLayout mLineOne;
    @BindView(R.id.iv_four)
    ImageView mIvFour;
    @BindView(R.id.tv_four)
    TextView mTvFour;
    @BindView(R.id.iv_five)
    ImageView mIvFive;
    @BindView(R.id.tv_five)
    TextView mTvFive;
    @BindView(R.id.iv_six)
    ImageView mIvSix;
    @BindView(R.id.tv_six)
    TextView mTvSix;
    @BindView(R.id.line_two)
    LinearLayout mLineTwo;
    @BindView(R.id.line_three)
    LinearLayout mLineThree;
    @BindView(R.id.line_top)
    LinearLayout mLineTop;
    @BindView(R.id.line_four)
    LinearLayout mLineFour;
    @BindView(R.id.line_five)
    LinearLayout mLineFive;
    @BindView(R.id.line_six)
    LinearLayout mLineSix;
    @BindView(R.id.line_bottom)
    LinearLayout mLineBottom;
    @BindView(R.id.rela_bottom)
    RelativeLayout mRelaBottom;
    @BindView(R.id.cardView)
    CardView mCardView;
    private String[] datas = new String[]{"0", "1", "2", "3", "4", "5", "6"};
    private int[] images = new int[]{R.drawable.p9, R.drawable.p6, R.drawable.p11, R.drawable.p8, R.drawable.p7, R.drawable.p12, R.drawable.p10};
    private View.OnClickListener[] mOnClickListeners = new View.OnClickListener[]{new topClick(), new oneClick(), new twoClick(), new threeClick(), new fourClick(), new fiveClick(), new sixClick()};
    private ImageView iv_bigtitle;
    private TextView tv_bigtitle;

    private int currentChoose = 0;
    private View currentView = null;
    private ArrayList<HomeItemBean> mList;
    private HomeItemBean mHomeItemBean;
    private boolean first = true;
    private Animation animation3;
    private TranslateAnimation mTranslateAnimation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seven_tab);
        ButterKnife.bind(this);
        initView();

    }

    private void initView() {
        mList = new ArrayList<>();
        //初始化数据
        for (int i = 0; i < datas.length; i++) {
            mHomeItemBean = new HomeItemBean();
            mHomeItemBean.setTitle(datas[i]);
            mHomeItemBean.setImageres(images[i]);
            mHomeItemBean.setIstop(i == 0 ? true : false);
            mList.add(mHomeItemBean);
        }

        iv_bigtitle = (ImageView) findViewById(R.id.iv_bigtitle);
        tv_bigtitle = (TextView) findViewById(R.id.tv_bigtitle);

        OvershootInterpolator overshootInterpolator = new OvershootInterpolator();
        AnticipateOvershootInterpolator anticipateOvershootInterpolator = new AnticipateOvershootInterpolator();
        Window window = getWindow();
        Display defaultDisplay = window.getWindowManager().getDefaultDisplay();

        ObjectAnimator translationY = ObjectAnimator.ofFloat(mRelaBottom, "translationY", defaultDisplay.getHeight()/*, mRelaBottom.getTranslationY() - 30*/, mRelaBottom.getTranslationY());
        translationY.setDuration(2000);
        translationY.setInterpolator(anticipateOvershootInterpolator);
        translationY.start();

        ObjectAnimator translationY2 = ObjectAnimator.ofFloat(mCardView, "translationY", defaultDisplay.getHeight()/*, mRelaBottom.getTranslationY() - 30*/, mCardView.getTranslationY());
        translationY2.setDuration(2000);
        translationY2.setInterpolator(anticipateOvershootInterpolator);
        translationY2.start();


        resetItem();
    }

    /**
     * 修改点击后的数据
     *
     * @param view
     */
    private void startAnima(final View view) {
        if (animation3 != null) {
            animation3.cancel();
        }
        HomeItemBean homeItemBean = new HomeItemBean();
        homeItemBean.setTitle(mList.get(0).getTitle());
        homeItemBean.setImageres(mList.get(0).getImageres());

        HomeItemBean homeItemBean2 = new HomeItemBean();
        homeItemBean2.setTitle(mList.get(currentChoose).getTitle());
        homeItemBean2.setImageres(mList.get(currentChoose).getImageres());

        mList.remove(currentChoose);
        mList.remove(0);

        mList.add(0, homeItemBean2);
        mList.add(currentChoose, homeItemBean);
        resetItem();
    }

    /**
     * 重置图案以及点击事件
     */
    private void resetItem() {
        mIvOne.setImageResource(mList.get(1).getImageres());
        mTvOne.setText(mList.get(1).getTitle());
        mIvTwo.setImageResource(mList.get(2).getImageres());
        mTvTwo.setText(mList.get(2).getTitle());
        mIvThree.setImageResource(mList.get(3).getImageres());
        mTvThree.setText(mList.get(3).getTitle());
        mIvFour.setImageResource(mList.get(4).getImageres());
        mTvFour.setText(mList.get(4).getTitle());
        mIvFive.setImageResource(mList.get(5).getImageres());
        mTvFive.setText(mList.get(5).getTitle());
        mIvSix.setImageResource(mList.get(6).getImageres());
        mTvSix.setText(mList.get(6).getTitle());


        mLineOne.setOnClickListener(new oneClick());
        mLineTwo.setOnClickListener(new twoClick());
        mLineThree.setOnClickListener(new threeClick());
        mLineFour.setOnClickListener(new fourClick());
        mLineFive.setOnClickListener(new fiveClick());
        mLineSix.setOnClickListener(new sixClick());


        mRelaTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToNextActivity();
            }
        });
        iv_bigtitle.setImageResource(mList.get(0).getImageres());
        tv_bigtitle.setText(mList.get(0).getTitle());

        //第一次略过动画
        if (!first) {
            animation3 = AnimationUtils.loadAnimation(SevenTabActivity.this, R.anim.zoomout);
            animation3.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    mLineOne.setEnabled(false);
                    mLineTwo.setEnabled(false);
                    mLineThree.setEnabled(false);
                    mLineFour.setEnabled(false);
                    mLineFive.setEnabled(false);
                    mLineSix.setEnabled(false);
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    mLineOne.setEnabled(true);
                    mLineTwo.setEnabled(true);
                    mLineThree.setEnabled(true);
                    mLineFour.setEnabled(true);
                    mLineFive.setEnabled(true);
                    mLineSix.setEnabled(true);
                    goToNextActivity();
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            mRelaTop.startAnimation(animation3);
        }
        first = false;

    }

    /**
     * 开始跳转
     */
    private void goToNextActivity() {
        switch (mList.get(0).getTitle()) {
            case "0":
                ZToast.makeText(SevenTabActivity.this, "跳转到0", 800).show();

                break;
            case "1":
                ZToast.makeText(SevenTabActivity.this, "跳转到1", 800).show();

                break;
            case "2":
                ZToast.makeText(SevenTabActivity.this, "跳转到2", 800).show();

                break;
            case "3":
                ZToast.makeText(SevenTabActivity.this, "跳转到3", 800).show();

                break;
            case "4":
                ZToast.makeText(SevenTabActivity.this, "跳转到4", 800).show();

                break;
            case "5":
                ZToast.makeText(SevenTabActivity.this, "跳转到5", 800).show();

                break;
            case "6":
                ZToast.makeText(SevenTabActivity.this, "跳转到6", 800).show();

                break;
        }
    }

    /**
     * 在顶部item的点击事件
     */
    private class topClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            currentChoose = 0;
            currentView = mLineOne;
            startAnima(currentView);
            ZToast.makeText(SevenTabActivity.this, "点击了零", 800).show();
        }
    }

    private class oneClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            currentChoose = 1;
            currentView = mLineOne;
            startAnima(currentView);
            ZToast.makeText(SevenTabActivity.this, "点击了一", 800).show();
        }
    }

    private class twoClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            currentChoose = 2;
            currentView = mLineTwo;
            startAnima(currentView);
            ZToast.makeText(SevenTabActivity.this, "点击了二", 800).show();
        }
    }

    private class threeClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            currentChoose = 3;
            currentView = mLineThree;
            startAnima(currentView);
            ZToast.makeText(SevenTabActivity.this, "点击了三", 800).show();
        }
    }

    private class fourClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            currentChoose = 4;
            currentView = mLineFour;
            startAnima(currentView);
            ZToast.makeText(SevenTabActivity.this, "点击了四", 800).show();
        }
    }

    private class fiveClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            currentChoose = 5;
            currentView = mLineFive;
            startAnima(currentView);
            ZToast.makeText(SevenTabActivity.this, "点击了五", 800).show();
        }
    }

    private class sixClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            currentChoose = 6;
            currentView = mLineSix;
            startAnima(currentView);
            ZToast.makeText(SevenTabActivity.this, "点击了六", 800).show();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (animation3 != null) {
            animation3.cancel();
        }
        if (mTranslateAnimation != null) {
            mTranslateAnimation.cancel();
        }
    }
}
