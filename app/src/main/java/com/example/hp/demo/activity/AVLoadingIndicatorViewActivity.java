package com.example.hp.demo.activity;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.airbnb.lottie.LottieAnimationView;
import com.example.hp.demo.R;
import com.wang.avi.AVLoadingIndicatorView;

import butterknife.BindView;
import steelkiwi.com.library.DotsLoaderView;

import static com.example.hp.demo.R.id.animation_view;


public class AVLoadingIndicatorViewActivity extends AppCompatActivity {
    @BindView(R.id.btn_car)
    Button mBtnCar;
    @BindView(R.id.btn_next)
    Button mBtnNext;
    @BindView(animation_view)
    LottieAnimationView mAnimationView;
    String[] datas = new String[]{"car.json", "lego_loader.json", "loading.json", "4_bar_loop.json",
            "chinese.json", "spinner.json", "circle.json", "basic_thick_circle_loader.json"
            , "material_wave_loading.json", "trail_loading.json"
            , "StickAndBall.json", "stripe loadingnew.json"};
    int num = 0;

    String IndicatorName[] = new String[]{"BallPulseIndicator", "BallGridPulseIndicator", "BallClipRotateIndicator", "BallClipRotatePulseIndicator",
            "SquareSpinIndicator", "BallClipRotateMultipleIndicator", "BallPulseRiseIndicator", "BallRotateIndicator", "CubeTransitionIndicator", "BallZigZagIndicator",
            "BallZigZagDeflectIndicator", "BallTrianglePathIndicator", "BallScaleIndicator", "LineScaleIndicator", "LineScalePartyIndicator", "BallScaleMultipleIndicator",
            "BallPulseSyncIndicator", "BallBeatIndicator", "LineScalePulseOutIndicator", "LineScalePulseOutRapidIndicator", "BallScaleRippleIndicator", "BallScaleRippleMultipleIndicator",
            "BallSpinFadeLoaderIndicator", "LineSpinFadeLoaderIndicator", "TriangleSkewSpinIndicator", "PacmanIndicator", "BallGridBeatIndicator", "SemiCircleSpinIndicator"};
    private AVLoadingIndicatorView mAVLoadingIndicatorView1;
    private AVLoadingIndicatorView mAVLoadingIndicatorView2;
    private AVLoadingIndicatorView mAVLoadingIndicatorView3;
    private AVLoadingIndicatorView mAVLoadingIndicatorView4;
    private AVLoadingIndicatorView mAVLoadingIndicatorView5;
    private AVLoadingIndicatorView mAVLoadingIndicatorView6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avloading_indicator_view);
        init();

        initLottieView();


        DotsLoaderView dotsLoaderView = (DotsLoaderView) findViewById(R.id.dotsLoaderView);
        dotsLoaderView.show();
        //        ZLoadingTextView z_loading_view = (ZLoadingTextView) findViewById(R.id.z_loading_view);
        //        z_loading_view.setText("NBZL");

        mAVLoadingIndicatorView1.setIndicator(IndicatorName[2]);
        mAVLoadingIndicatorView2.setIndicator(IndicatorName[8]);
        mAVLoadingIndicatorView3.setIndicator(IndicatorName[12]);
        mAVLoadingIndicatorView4.setIndicator(IndicatorName[26]);
        mAVLoadingIndicatorView5.setIndicator(IndicatorName[17]);
        mAVLoadingIndicatorView6.setIndicator(IndicatorName[23]);
    }

    private void initLottieView() {
        //        setContentView(R.layout.view_kkgc_detail_info);
        final LottieAnimationView animation_view = (LottieAnimationView) findViewById(R.id.animation_view);
        Button car = (Button) findViewById(R.id.btn_car);
        Button next = (Button) findViewById(R.id.btn_next);
        animation_view.setAnimation(datas[0]);
        animation_view.playAnimation();
        animation_view.loop(true);
        car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (num > 0) {
                    num = num - 1;
                    animation_view.cancelAnimation();
                    animation_view.setAnimation(datas[num]);
                    animation_view.loop(true);
                    animation_view.playAnimation();
                    // 自定义速度与时长
                    ValueAnimator animator = ValueAnimator.ofFloat(0f, 1f)
                            .setDuration(5000);

                    animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {

                            animation_view.setProgress(Float.parseFloat(animation.getAnimatedValue().toString()));
                        }
                    });
                    animator.start();
                }
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (num < datas.length - 1) {
                    num = num + 1;
                    animation_view.cancelAnimation();
                    animation_view.setAnimation(datas[num]);
                    animation_view.loop(true);
                    animation_view.playAnimation();
                    // 自定义速度与时长
                    ValueAnimator animator = ValueAnimator.ofFloat(0f, 1f)
                            .setDuration(5000);

                    animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {

                            animation_view.setProgress(Float.parseFloat(animation.getAnimatedValue().toString()));
                        }
                    });

                    animator.start();

                }
            }
        });

    }

    private void init() {
        mAVLoadingIndicatorView1 = (AVLoadingIndicatorView) findViewById(R.id.AVLoadingIndicatorView1);
        mAVLoadingIndicatorView2 = (AVLoadingIndicatorView) findViewById(R.id.AVLoadingIndicatorView2);
        mAVLoadingIndicatorView3 = (AVLoadingIndicatorView) findViewById(R.id.AVLoadingIndicatorView3);
        mAVLoadingIndicatorView4 = (AVLoadingIndicatorView) findViewById(R.id.AVLoadingIndicatorView4);
        mAVLoadingIndicatorView5 = (AVLoadingIndicatorView) findViewById(R.id.AVLoadingIndicatorView5);
        mAVLoadingIndicatorView6 = (AVLoadingIndicatorView) findViewById(R.id.AVLoadingIndicatorView6);

    }


}
