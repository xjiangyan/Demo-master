
package com.example.hp.demo.activity;

import android.databinding.DataBindingUtil;
import android.view.View;

import com.example.hp.demo.BaseActivity;
import com.example.hp.demo.R;
import com.example.hp.demo.databinding.ActivitySplashBinding;
import com.example.hp.demo.view.CountDownView;

public class SplashActivity extends BaseActivity {

    private ActivitySplashBinding binding;

    @Override
    public void initView() {
        binding = DataBindingUtil.setContentView(SplashActivity.this, R.layout.activity_splash);
    }

    @Override
    public void onCreateLoadData() {


        binding.countDownView.setListener(new CountDownView.OnProgressListener() {
            @Override
            public void onFinish() {
                binding.countDownView.cancel();
                jumpToActivity(MainActivity.class);
                finish();
            }
        });
        binding.countDownView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.countDownView.cancel();
                jumpToActivity(MainActivity.class);
                finish();
            }
        });
        binding.countDownView.start();
    }

    @Override
    public void onResumeLoadData() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (binding.countDownView != null) {
            binding.countDownView.cancel();
        }
    }

    @Override
    public void onBackPressed() {
        //        super.onBackPressed();
    }
}
