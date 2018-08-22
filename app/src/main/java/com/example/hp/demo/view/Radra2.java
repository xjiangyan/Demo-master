package com.example.hp.demo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;


/**
 * @author Admin
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class Radra2 extends View {

    private Paint mPaint;
    private int mMeasuredHeight;
    private int mMeasuredWidth;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };
    private float stopY = 0;
    private float stopX = 0;
    private float angle = 0;
    private Canvas canvas;

    public Radra2(Context context) {
        super(context);
    }

    public Radra2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }


    public Radra2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mMeasuredHeight = getMeasuredHeight();
        mMeasuredWidth = getMeasuredWidth();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.canvas = canvas;

        canvas.translate(mMeasuredWidth / 2, mMeasuredHeight / 2);
        canvas.scale(1, -1);
        mPaint.setColor(Color.BLUE);
        canvas.drawCircle(0, 360, mMeasuredWidth / 2, mPaint);
        mPaint.setColor(Color.GREEN);
        canvas.drawLine(0, 0, (float) (Math.cos(angle * (Math.PI / 180)) * (mMeasuredWidth / 2)), (float) (Math.sin(angle * (Math.PI / 180)) * (mMeasuredWidth / 2)), mPaint);
        mPaint.setColor(Color.RED);
        canvas.drawArc(-mMeasuredWidth / 2, mMeasuredWidth / 2, mMeasuredWidth / 2, -mMeasuredWidth / 2, 0, 360, false, mPaint);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                angle = angle + 1;

                invalidate();
            }
        }, 100);
    }

    private void initView() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mHandler.removeCallbacksAndMessages(null);
    }
}
