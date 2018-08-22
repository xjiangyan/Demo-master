package com.example.hp.demo.activity;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.transition.Transition;
import android.transition.TransitionSet;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.hp.demo.R;
import com.example.hp.demo.transition.ChangeColor;
import com.example.hp.demo.transition.ChangePosition;
import com.example.hp.demo.transition.ShareElemEnterRevealTransition;
import com.example.hp.demo.transition.ShareElemReturnChangePosition;
import com.example.hp.demo.transition.ShareElemReturnRevealTransition;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PaintViewActivity extends AppCompatActivity {
    @BindView(R.id.checked)
    Button mChecked;
    @BindView(R.id.unchecked)
    Button mUnchecked;
    @BindView(R.id.animatebtn)
    Button mAnimatebtn;
    @BindView(R.id.line_contain)
    LinearLayout mLineContain;

    //    @BindView(R.id.checkview)
    //    CheckView mCheckview;
    //
    //    @BindView(R.id.drawimageview)
    //    ImageView mDrawimageview;
    private int count = 6;                //数据个数
    private float angle = (float) (Math.PI * 2 / count);
    private float radius;                   //网格最大半径
    private int centerX;                  //中心X
    private int centerY;                  //中心Y
    private String[] titles = {"a", "b", "c", "d", "e", "f"};
    private double[] data = {100, 60, 60, 60, 100, 50, 10, 20}; //各维度分值
    private float maxValue = 100;             //数据最大值
    private Paint mainPaint;                //雷达区画笔
    private Paint valuePaint;               //数据区画笔
    private Paint textPaint;                //文本画笔

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setTransition();

        setContentView(R.layout.activity_paint_view);
        ButterKnife.bind(this);
        initClick();
        //        initView();
        //        initCanvas();
        //        init();

        //        TransitionSet cotentTransition = new TransitionSet();
        //        Slide slide = new Slide(Gravity.LEFT);
        //        slide.setDuration(800);
        //        slide.excludeTarget(android.R.id.navigationBarBackground, true);
        //        slide.excludeTarget(android.R.id.statusBarBackground, true);
        //        slide.excludeTarget(R.id.animatebtn, true);
        //        cotentTransition.addTransition(slide);
        //        //fab进入动画
        //        FABTransition fabTransition = new FABTransition(mAnimatebtn, this);
        //        fabTransition.addTarget(R.id.animatebtn);
        //        fabTransition.setDuration(800);
        //        cotentTransition.addTransition(fabTransition);
        //        getWindow().setEnterTransition(cotentTransition);
        //        getWindow().setExitTransition(cotentTransition);

    }


    private void setTransition() {

        getWindow().setSharedElementEnterTransition(buildShareElemEnterSet());
        getWindow().setSharedElementReturnTransition(buildShareElemReturnSet());

    }

    /**
     * 分享 元素 进入动画
     *
     * @return
     */
    private TransitionSet buildShareElemEnterSet() {
        TransitionSet transitionSet = new TransitionSet();
        //
        Transition changePos = new ChangePosition();
        changePos.setDuration(300);
        changePos.addTarget(R.id.line_contain);
        transitionSet.addTransition(changePos);

        Transition revealTransition = new ShareElemEnterRevealTransition(mLineContain);
        transitionSet.addTransition(revealTransition);
        revealTransition.addTarget(R.id.line_contain);
        revealTransition.setInterpolator(new FastOutSlowInInterpolator());
        revealTransition.setDuration(300);

        ChangeColor changeColor = new ChangeColor(getResources().getColor(R.color.colorPrimary), getResources().getColor(R.color.white));
        changeColor.addTarget(R.id.line_contain);
        changeColor.setDuration(350);
        transitionSet.addTransition(changeColor);
        transitionSet.setDuration(900);
        return transitionSet;
    }

    /**
     * 分享元素返回动画
     *
     * @return
     */
    private TransitionSet buildShareElemReturnSet() {
        TransitionSet transitionSet = new TransitionSet();
        //
        Transition changePos = new ShareElemReturnChangePosition();
        changePos.addTarget(R.id.line_contain);
        changePos.setDuration(300);
        transitionSet.addTransition(changePos);

        ChangeColor changeColor = new ChangeColor(getResources().getColor(R.color.white), getResources().getColor(R.color.colorPrimary));
        changeColor.addTarget(R.id.line_contain);
        changeColor.setDuration(350);

        transitionSet.addTransition(changeColor);


        Transition revealTransition = new ShareElemReturnRevealTransition(mLineContain);
        revealTransition.addTarget(R.id.line_contain);
        revealTransition.setDuration(300);

        transitionSet.addTransition(revealTransition);

        transitionSet.setDuration(900);

        return transitionSet;
    }


    private void initClick() {
        mAnimatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0);
                TranslateAnimation translateAnimation = new TranslateAnimation(0, 0, 0, mLineContain.getHeight());
                AnimationSet animationSet = new AnimationSet(false);
                animationSet.addAnimation(alphaAnimation);
                animationSet.addAnimation(translateAnimation);
                animationSet.setDuration(800);
                mLineContain.startAnimation(animationSet);
            }
        });
    }
    //    private void init() {
    //
    //        Window window = getWindow();
    //        Display defaultDisplay = window.getWindowManager().getDefaultDisplay();
    //        int width = defaultDisplay.getWidth();
    //        int height = defaultDisplay.getHeight();
    //
    //        radius = Math.min(height, width) / 2 * 0.9f;
    //        //中心坐标
    //        centerX = width / 2;
    //        centerY = height / 2;
    //        //        postInvalidate();
    //        Bitmap bitmap = Bitmap.createBitmap(width, height, BitmapFactory.decodeResource(getResources(), R.drawable.p11).getConfig());
    //        mainPaint = new Paint();
    //        mainPaint.setStyle(Paint.Style.STROKE);
    //        valuePaint = new Paint();
    //        textPaint = new Paint();
    //        Canvas canvas = new Canvas(bitmap);
    //        drawPolygon(canvas);
    //        drawLines(canvas);
    //        drawText(canvas);
    //        drawRegion(canvas);
    //        mDrawimageview.setImageBitmap(bitmap);
    //    }

    /**
     * 绘制正多边形
     */
    private void drawPolygon(Canvas canvas) {
        Path path = new Path();
        float r = radius / (count - 1);//r是蜘蛛丝之间的间距
        for (int i = 1; i < count; i++) {//中心点不用绘制
            float curR = r * i;//当前半径
            path.reset();
            for (int j = 0; j < count; j++) {
                if (j == 0) {
                    path.moveTo(centerX + curR, centerY);
                } else {
                    //根据半径，计算出蜘蛛丝上每个点的坐标
                    float x = (float) (centerX + curR * Math.cos(angle * j));
                    float y = (float) (centerY + curR * Math.sin(angle * j));
                    path.lineTo(x, y);
                }
            }
            path.close();//闭合路径
            canvas.drawPath(path, mainPaint);
        }
    }

    /**
     * 绘制直线
     */
    private void drawLines(Canvas canvas) {
        Path path = new Path();
        for (int i = 0; i < count; i++) {
            path.reset();
            path.moveTo(centerX, centerY);
            float x = (float) (centerX + radius * Math.cos(angle * i));
            float y = (float) (centerY + radius * Math.sin(angle * i));
            path.lineTo(x, y);
            canvas.drawPath(path, mainPaint);
        }
    }

    /**
     * 绘制文字
     *
     * @param canvas
     */
    private void drawText(Canvas canvas) {
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        float fontHeight = fontMetrics.descent - fontMetrics.ascent;
        for (int i = 0; i < count; i++) {
            float x = (float) (centerX + (radius + fontHeight / 2) * Math.cos(angle * i));
            float y = (float) (centerY + (radius + fontHeight / 2) * Math.sin(angle * i));
            if (angle * i >= 0 && angle * i <= Math.PI / 2) {//第4象限
                canvas.drawText(titles[i], x, y, textPaint);
            } else if (angle * i >= 3 * Math.PI / 2 && angle * i <= Math.PI * 2) {//第3象限
                canvas.drawText(titles[i], x, y, textPaint);
            } else if (angle * i > Math.PI / 2 && angle * i <= Math.PI) {//第2象限
                float dis = textPaint.measureText(titles[i]);//文本长度
                canvas.drawText(titles[i], x - dis, y, textPaint);
            } else if (angle * i >= Math.PI && angle * i < 3 * Math.PI / 2) {//第1象限
                float dis = textPaint.measureText(titles[i]);//文本长度
                canvas.drawText(titles[i], x - dis, y, textPaint);
            }
        }
    }

    /**
     * 绘制区域
     *
     * @param canvas
     */
    private void drawRegion(Canvas canvas) {
        Path path = new Path();
        valuePaint.setAlpha(255);
        for (int i = 0; i < count; i++) {
            double percent = data[i] / maxValue;
            float x = (float) (centerX + radius * Math.cos(angle * i) * percent);
            float y = (float) (centerY + radius * Math.sin(angle * i) * percent);
            if (i == 0) {
                path.moveTo(x, centerY);
            } else {
                path.lineTo(x, y);
            }
            //绘制小圆点
            canvas.drawCircle(x, y, 10, valuePaint);
        }
        valuePaint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(path, valuePaint);
        valuePaint.setColor(Color.BLUE);

        valuePaint.setAlpha(127);
        //绘制填充区域
        valuePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawPath(path, valuePaint);
    }


    //
    //    @OnClick({R.id.checked, R.id.unchecked})
    //    public void onViewClicked(View view) {
    //        switch (view.getId()) {
    //            case R.id.checked:
    //                mCheckview.check();
    //                break;
    //            case R.id.unchecked:
    //                mCheckview.unCheck();
    //                break;
    //        }
    //    }
    //
    //    private void initCanvas() {
    //
    //        Paint mPaint = new Paint();             // 创建画笔
    //        mPaint.setColor(Color.BLACK);           // 画笔颜色 - 黑色
    //        mPaint.setStyle(Paint.Style.STROKE);    // 填充模式 - 描边
    //        mPaint.setStrokeWidth(10);              // 边框宽度 - 10
    //
    //
    //        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.p11);
    //        Matrix matrix = new Matrix();
    //        matrix.setScale(2, 2);
    //        Bitmap newBitmap = Bitmap.createBitmap(bitmap.getWidth() * 2, bitmap.getHeight() * 2, bitmap.getConfig());
    //        Canvas canvas = new Canvas(newBitmap);
    //        canvas.drawBitmap(bitmap, matrix, mPaint);
    //        mDrawimageview.setImageBitmap(newBitmap);
    //    }
    //
    //
    //    private void initView() {
    //        Paint mPaint = new Paint();             // 创建画笔
    //        mPaint.setColor(Color.BLACK);           // 画笔颜色 - 黑色
    //        mPaint.setStyle(Paint.Style.STROKE);    // 填充模式 - 描边
    //        mPaint.setStrokeWidth(10);              // 边框宽度 - 10
    //
    //
    //        Window window = getWindow();
    //        Display defaultDisplay = window.getWindowManager().getDefaultDisplay();
    //        int width = defaultDisplay.getWidth();
    //        int height = defaultDisplay.getHeight();
    //
    //        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.p11);
    //        Bitmap recbitmap = Bitmap.createBitmap(width, height, bitmap.getConfig());
    //        Canvas canvas = new Canvas(recbitmap);
    //        canvas.translate(width / 2, height / 2);  // 移动坐标系到屏幕中心(宽高数据在onSizeChanged中获取)
    //        Path path = new Path();                     // 创建Path
    //        path.lineTo(200, 200);                      // lineTo
    //        path.lineTo(200, 0);
    //
    //        path.addCircle(0, 0, 200, Path.Direction.CW);
    //        canvas.drawPath(path, mPaint);              // 绘制Path
    //        mDrawimageview.setImageBitmap(recbitmap);
    //
    //    }
}
