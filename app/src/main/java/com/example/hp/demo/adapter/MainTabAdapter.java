package com.example.hp.demo.adapter;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.hp.demo.R;
import com.example.hp.demo.bean.MainTabBean;

import java.util.ArrayList;


public class MainTabAdapter extends BaseQuickAdapter<MainTabBean> {
    public MainTabAdapter(int layoutResId, Context context) {
        super(layoutResId, new ArrayList<MainTabBean>());
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, final MainTabBean mainTabBean) {
        baseViewHolder.setText(R.id.tv, mainTabBean.getName());
        if (mainTabBean.getResId() != 0) {
            RequestOptions requestOptions = new RequestOptions().override(150, 200);

            Glide.with(mContext)
                    .load(mainTabBean.getResId())
                    //                    .apply(requestOptions)
                    .into((ImageView) baseViewHolder.getView(R.id.iv));
            //            baseViewHolder.setImageResource(R.id.iv, mainTabBean.getResId());
        }
        baseViewHolder.addOnClickListener(R.id.iv_more);
        baseViewHolder.setOnClickListener(R.id.iv, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(mainTabBean.getIntent());
            }
        });

    }

    public void startActivity(Intent intent) {
        // 创建一个包含过渡动画信息的 ActivityOptionsCompat 对象
        mContext.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation((Activity) mContext).toBundle());
        //        overridePendingTransition(R.anim.next_in, R.anim.next_out);
        //        startActivity(new Intent(MainActivity.this, clazz), ActivityOptions.makeSceneTransitionAnimation(this, mBtn_dialog, "mydialogview").toBundle());
        //                        startActivity(new Intent(MainActivity.this, clazz));

        //        Intent intent = new Intent(this, clazz);
        //        int x=(int)mBtn_dialog.getTranslationX();
        //        int y=(int)mBtn_dialog.getTranslationY();
        //
        //            startActivity(intent, ActivityOptions.makeClipRevealAnimation(//拓展到全屏
        //                    mBtn_dialog,x,y,mBtn_dialog.getWidth(),mBtn_dialog.getHeight()).toBundle());


    }
}
