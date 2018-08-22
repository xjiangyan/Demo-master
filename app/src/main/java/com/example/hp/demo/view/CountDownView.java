package com.example.hp.demo.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.hp.demo.utils.DensityUtil;

/**
 * 倒计时View
 */

public class CountDownView extends View implements Runnable {
    private Paint mPaint = new Paint();
    private Paint mTextPaint = new Paint();
    private Paint mCirclePaint = new Paint();
    private int mProgress;
    private boolean isCancel;
    private RectF mOVal;
    private int mCount = 5;
    private OnProgressListener mListener;
    protected float mTextBaseLine;
    private ValueAnimator animator;

    public CountDownView(Context context) {
        this(context, null);
    }

    public CountDownView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(DensityUtil.dip2px(context, 2));
        mPaint.setColor(0xff24cf5f);

        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setStyle(Paint.Style.FILL);
        mCirclePaint.setColor(0xFFFFFFFF);

        mTextPaint.setAntiAlias(true);
        mTextPaint.setStyle(Paint.Style.STROKE);
        mTextPaint.setColor(0xFF333333);
        mTextPaint.setTextSize(DensityUtil.dip2px(context, 12));

        Paint.FontMetrics metrics = mTextPaint.getFontMetrics();
        mTextBaseLine = metrics.descent + (metrics.bottom - metrics.top) / 2;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mOVal == null) {
            mOVal = new RectF();
            mOVal.left = getPaddingLeft();
            mOVal.top = getPaddingTop();
            mOVal.right = getWidth() - getPaddingRight();
            mOVal.bottom = getHeight() - getPaddingBottom();
        }
        int w = getWidth();
        int h = getHeight();
        int radius = (w - 2 * getPaddingLeft()) / 2 - DensityUtil.dip2px(getContext(), 1);
        int mStartAngle = -90;
        canvas.drawArc(mOVal, mStartAngle, mProgress, true, mPaint);
        canvas.drawCircle(w / 2, h / 2, radius, mCirclePaint);
        canvas.drawText("跳过", w / 2 - getTextX(), h / 3 + mTextBaseLine, mTextPaint);
    }

    @Override
    public void run() {
        if (isCancel) {
            mProgress = 0;
            return;
        }
        mProgress += 2;
        if (mProgress < 360) {
            postDelayed(this, 1000 * mCount / 180);
            invalidate();
        } else {
            if (mListener != null) {
                mListener.onFinish();
            }
        }
    }

    public void start() {
        //postDelayed(this, 1000 * mCount / 180);
        animator = ValueAnimator.ofInt(0, 360);
        animator.setDuration(5000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                if (isCancel) {
                    return;
                }
                mProgress = (int) animation.getAnimatedValue();
                invalidate();
            }
        });
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if (!isCancel && mListener != null) {
                    mListener.onFinish();
                }
            }
        });
        animator.start();
    }

    public void cancel() {
        isCancel = true;
        if (animator != null) {
            animator.cancel();
        }
    }

    public void setListener(OnProgressListener mListener) {
        this.mListener = mListener;
    }

    private float getTextX() {
        Rect bounds = new Rect();
        mTextPaint.getTextBounds("跳过", 0, "跳过".length(), bounds);
        return bounds.width() / 2;
    }

    public interface OnProgressListener {
        void onFinish();
    }
}
