package com.example.hp.demo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hp.demo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Steven on 2016/12/12.
 */
public class ErrorView extends LinearLayout {

    @BindView(R.id.error_img)
    ImageView errorImg;
    @BindView(R.id.error_msg)
    TextView errorMsg;

    public ErrorView(Context context) {
        super(context);
        init();
    }

    public ErrorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ErrorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View.inflate(getContext(), R.layout.no_data_layout, this);
        ButterKnife.bind(this);
    }
    public void setErrorImg(int imgId) {
        errorImg.setImageResource(imgId);
    }
    public void setErrorMsg(String msg) {
        errorMsg.setText(msg);
    }
    public void setClickListener(OnClickListener listener) {
        errorImg.setOnClickListener(listener);
    }
    public boolean needToRefresh() {
        return !errorMsg.getText().equals("抱歉，没有数据");
    }

    /**
     * //0：表示没有数据
     * //1：表示服务没找到
     * //2：表示网络没连接好
     * //3：表示服务异常
     * //4: 连接超时
     * //5：登陆过期
     * //6: 没有权限
     * @param type
     */
    public void show(int type) {
        this.setVisibility(VISIBLE);
        int imgId = -1;
        String msg = "";
        switch (type) {
            case 0:
                imgId = R.drawable.nodata;
                msg = "抱歉，没有数据";
                break;
            case 1:
                imgId = R.drawable.service_error;
                msg = "抱歉，服务未找到";
                break;
            case 2:
                imgId = R.drawable.no_network;
                msg = "抱歉，网络异常";
                break;
            case 3:
                imgId = R.drawable.service_error;
                msg = "抱歉，服务异常";
                break;
            case 4:
                imgId = R.drawable.service_error;
                msg = "抱歉，连接超时";
                break;
            case 5:
                imgId = R.drawable.overtime;
                msg = "抱歉，登陆已过期";
                break;
            case 6:
                imgId = R.drawable.no_permission;
                msg = "抱歉，您没有该权限";
                break;
        }
        errorImg.setImageResource(imgId);
        errorMsg.setText(msg);
    }
    public void hide() {
        this.setVisibility(GONE);
    }
}
