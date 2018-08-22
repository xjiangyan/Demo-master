package com.example.hp.demo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.GridView;


public class NoScrollGridView extends GridView {
    private int flag = 0;

    private float StartX;

    private float StartY;
    public NoScrollGridView(Context context) {
        super(context);
    }

    public NoScrollGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NoScrollGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //MeasureSpec.AT_MOST的意思就是wrap_content
        //Integer.MAX_VALUE >> 2 是使用最大值的意思,也就表示的无边界模式
        //Integer.MAX_VALUE >> 2 此处表示是父布局能够给他提供的大小
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

    public boolean onInterceptTouchEvent(MotionEvent ev) {
        // 总是调用gridview的touch事件处理
        onTouchEvent(ev);
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            StartX = ev.getX();
            StartY = ev.getY();
            return false;
        }
        if (ev.getAction() == MotionEvent.ACTION_MOVE) {
            float ScollX = ev.getX() - StartX;
            float ScollY = ev.getY() - StartY;
            // 判断是横滑还是竖滑，竖滑的话拦截move事件和up事件（不拦截会由于gridview和scrollview同时执行滑动卡顿）
            if (Math.abs(ScollX) < Math.abs(ScollY)) {
                flag = 1;
                return true;
            }
            return false;
        }
        if (ev.getAction() == MotionEvent.ACTION_UP) {
            if (flag == 1) {

                return true;
            }
            return false;
        }
        return super.onInterceptTouchEvent(ev);
    }
}
