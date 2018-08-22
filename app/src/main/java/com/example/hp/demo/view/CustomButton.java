package com.example.hp.demo.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.hp.demo.R;

/**
 * @author Admin
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class CustomButton extends View implements View.OnClickListener {

    private Bitmap slide_button;
    private Bitmap switch_background;
    private Paint paint;
    private float distance = 0;
    private boolean isleft = true;
    private float startX, distanceX;
    private int distanceMax;
    private boolean isClick = true;
    private onNightClickListener onnightclicklistener;

    public CustomButton(Context context) {
        super(context);
    }

    public CustomButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }


    public CustomButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    private void initView() {
        isleft = true;
        paint = new Paint();
        paint.setAntiAlias(true);
        slide_button = BitmapFactory.decodeResource(getResources(), R.drawable.slide_button);
        switch_background = BitmapFactory.decodeResource(getResources(), R.drawable.switch_background);
        setOnClickListener(this);
        //        setOnTouchListener(this);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(switch_background.getWidth(), switch_background.getHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(switch_background, 0, 0, paint);
        canvas.drawBitmap(slide_button, distance, 0, paint);
        distanceMax = switch_background.getWidth() - slide_button.getWidth();
    }

    @Override
    public void onClick(View v) {
        //        if (isClick) {
        isleft = !isleft;
        flushView();
        onnightclicklistener.switchDayOrNight();
        //        }
    }

    private void flushView() {
        distance = isleft ? 0 : switch_background.getWidth() - slide_button.getWidth();
        invalidate();
    }
    //
    //    @Override
    //    public boolean onTouch(View v, MotionEvent event) {
    //
    //        switch (event.getAction()) {
    //            case MotionEvent.ACTION_DOWN:
    //                startX = event.getX();
    //                isClick = true;
    //                break;
    //            case MotionEvent.ACTION_MOVE:
    //                //                distance = event.getX() - startX;
    //                //                if (distance > 0 && distance > distanceMax) {
    //                //                    distance = distanceMax;
    //                //                } else if (distance < 0 /*&& Math.abs(distance) > distanceMax*/) {
    //                //                    distance = 0;
    //                //                }
    //                distanceX = event.getX() - startX;
    //                distance += distanceX;
    //
    //                if (distance > distanceMax) {
    //                    distance = distanceMax;
    //                } else if (distance < 0) {
    //                    distance = 0;
    //                }
    //                invalidate();
    //                if (Math.abs(distanceX) > 5) {
    //                    isClick = false;
    //                }
    //                break;
    //            case MotionEvent.ACTION_UP:
    //                if (!isClick) {
    //                    if (distance < distanceMax / 2) {
    //                        isleft = true;
    //                    } else if (distance > distanceMax / 2) {
    //                        isleft = false;
    //                    }
    //                    flushView();
    //                }
    //                break;
    //        }
    //        return true;
    //    }

    public void setonNightClickListener(onNightClickListener onnightclicklistener) {
        this.onnightclicklistener = onnightclicklistener;
    }

    public interface onNightClickListener {
        void switchDayOrNight();
    }
}
