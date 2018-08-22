package com.example.hp.demo.activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.easymap.android.maps.MapActivity;
import com.example.hp.demo.R;

/**
 * Created by Steven on 2017/2/3.
 */
public class BaseMapActivity extends MapActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    protected void showBack() {
        findViewById(R.id.logo).setVisibility(View.GONE);
        findViewById(R.id.back).setVisibility(View.VISIBLE);
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    protected void setTitleBarTitle(String title) {
//        TextView titleTv = (TextView) findViewById(R.id.titleTv);
//        titleTv.setText(title);
    }
}
