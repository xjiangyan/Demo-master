package com.example.hp.demo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.demo.R;
import com.example.hp.demo.utils.PreferenceUtil;
import com.example.hp.demo.view.CustomButton;
import com.example.hp.demo.view.MyCustomView;
import com.example.hp.demo.view.MyCustomViewPager;
import com.example.hp.demo.view.VerticalSelector;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomActivity extends AppCompatActivity implements View.OnClickListener, VerticalSelector.LeftOnItemClick, CustomButton.onNightClickListener {
    @BindView(R.id.btn_sucess)
    Button mBtnSucess;
    @BindView(R.id.btn_fail)
    Button mBtnFail;
    @BindView(R.id.customcontrol)
    MyCustomView mCustomcontrol;
    @BindView(R.id.myviewpager)
    MyCustomViewPager mMyviewpager;
    @BindView(R.id.v_night)
    View mVNight;
    @BindView(R.id.custombutton)
    CustomButton mCustombutton;
    private int[] imgs = {R.drawable.p9, R.drawable.p11, R.drawable.hh, R.drawable.ss};
    //    @BindView(R.id.verticalselector)
    //    VerticalSelector mVerticalselector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);
        ButterKnife.bind(this);

        init();

    }


    private void init() {
        mBtnSucess.setOnClickListener(this);
        mBtnFail.setOnClickListener(this);
        mCustombutton.setonNightClickListener(this);
        //        mVerticalselector.initLeftRecycleView(new ArrayList<String>());
        //        mVerticalselector.setonLeftItemClickListener(this);
        //        mVerticalselector.initRightRecycleView(new ArrayList<String>());
        for (int i = 0; i < imgs.length; i++) {
            ImageView imageView = new ImageView(getApplicationContext());
            imageView.setImageResource(imgs[i]);
            mMyviewpager.addView(imageView);
        }
    }

    @Override
    public void onLeftItemClick(int position) {
        //        mVerticalselector.changeRightPosition(position);
    }

    @Override
    public void switchDayOrNight() {
        Boolean dayOrNight = PreferenceUtil.getInstance(CustomActivity.this).getDayOrNight2();
        mVNight.setVisibility(dayOrNight ? View.GONE : View.VISIBLE);
        PreferenceUtil.getInstance(CustomActivity.this).setDayOrNight2(!dayOrNight);
    }

    public class ViewHolder {
        TextView mTextView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_sucess:
                mCustomcontrol.setSucess();
                Toast.makeText(this, "成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_fail:
                mCustomcontrol.setFail();
                Toast.makeText(this, "失败", Toast.LENGTH_SHORT).show();
                break;
        }
    }

}
