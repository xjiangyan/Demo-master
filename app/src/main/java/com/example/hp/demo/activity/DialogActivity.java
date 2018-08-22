package com.example.hp.demo.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.animation.DynamicAnimation;
import android.support.animation.SpringAnimation;
import android.support.animation.SpringForce;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.Toast;

import com.example.hp.demo.R;


public class DialogActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mOpendialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        findView();
        initView();
        //        Slide slide=new Slide(Gravity.BOTTOM);
        //        slide.setDuration(500);
        //        //内容变换，不包括底部导航栏和状态栏
        //        slide.excludeTarget(android.R.id.navigationBarBackground, true);
        //        slide.excludeTarget(android.R.id.statusBarBackground, true);
        //        getWindow().setEnterTransition(slide);
        //        getWindow().setReturnTransition(slide);
        //        SpringForce spring = new SpringForce(0)
        //                .setDampingRatio(SpringForce.DAMPING_RATIO_LOW_BOUNCY)
        //                .setStiffness(SpringForce.STIFFNESS_LOW);
        //
        //        new SpringAnimation(mOpendialog, DynamicAnimation.SCALE_Y)
        //                .setMinValue(1)
        //                .setSpring(spring)
        //                .setStartValue(5)
        //                .start();
        SpringForce spring = new SpringForce(0)
                .setDampingRatio(SpringForce.DAMPING_RATIO_MEDIUM_BOUNCY)//弹性阻尼，摆动次数
                .setStiffness(SpringForce.STIFFNESS_VERY_LOW);//弹性的生硬度，摆动时间
        SpringAnimation animation = new SpringAnimation(mOpendialog, DynamicAnimation.TRANSLATION_Y)
                .setSpring(spring)
                .setStartValue(-700);
        animation.addEndListener(new DynamicAnimation.OnAnimationEndListener() {
            @Override
            public void onAnimationEnd(DynamicAnimation animation, boolean canceled, float value, float velocity) {
                //                startLogin();
                Toast.makeText(DialogActivity.this, "动画结束", Toast.LENGTH_SHORT).show();
            }
        });
        animation.start();

        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setDuration(800);
    }

    private void findView() {
        mOpendialog = (Button) findViewById(R.id.opendialog);

    }

    private void initView() {
        mOpendialog.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("提示");
        builder.setView(R.layout.dialogview);
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(DialogActivity.this, "取消", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(DialogActivity.this, "确定", Toast.LENGTH_SHORT).show();
            }
        });
        builder.create();
        builder.show();
    }
}
