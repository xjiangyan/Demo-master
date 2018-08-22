package com.example.hp.demo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.hp.demo.R;
import com.example.hp.demo.utils.DensityUtil;

public class LoadMoreview extends RelativeLayout {
    public LoadMoreview(Context context) {
        super(context);
        init(context);

    }

    public LoadMoreview(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }


    public LoadMoreview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View footview = LayoutInflater.from(context).inflate(R.layout.viewloadingdotsfade, this);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, DensityUtil.dip2px(context, 40));
        layoutParams.gravity = Gravity.CENTER_HORIZONTAL;
        setLayoutParams(layoutParams);
        setVisibility(INVISIBLE);
    }

    public void hideView() {
        setVisibility(INVISIBLE);
    }

    public void showView() {
        setVisibility(VISIBLE);
    }

}
