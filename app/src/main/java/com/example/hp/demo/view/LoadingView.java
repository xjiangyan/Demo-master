package com.example.hp.demo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
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
public class LoadingView extends View {
    int[] colors = new int[]{getResources().getColor(R.color.colorPrimary), getResources().getColor(R.color.yellow),
            getResources().getColor(R.color.black), getResources().getColor(R.color.green),
            getResources().getColor(R.color.gray), getResources().getColor(R.color.red),
            getResources().getColor(R.color.darkorange)};
    private int height;
    private int width;
    private Paint paint;
    int num1 = 1, num2 = 2, num3 = 3, num4 = 4, num5 = 5, num6 = 6, num7 = 7, num22 = 0, num11 = 1, radius = 20, swingradius = 80;
    private Path path1;
    private Path path2;
    private Path path3;
    private Path path4;
    private Path path5;
    private Path path6;
    private Path path7;

    public LoadingView(Context context) {
        super(context);
    }

    public LoadingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    public LoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init() {
        paint = new Paint();
        paint.setColor(getResources().getColor(R.color.colorPrimary));
        paint.setAntiAlias(true);//设置抗锯齿
        paint.setStyle(Paint.Style.STROKE);

        path1 = new Path();
        path2 = new Path();
        path3 = new Path();
        path4 = new Path();
        path5 = new Path();
        path6 = new Path();
        path7 = new Path();
    }

    @Override
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(width / 2, height / 2);
        canvas.scale(1, -1);
        swingball(canvas);
        //        logoshow(canvas);

    }

    private void logoshow(Canvas canvas) {
        canvas.drawArc(-200, 200, 200, -200, 0, 360, true, paint);
    }

    private void swingball(Canvas canvas) {
        //        path1.addCircle(-120, num11 % 6 == 0 ? 20 : 0, 20, Path.Direction.CW);
        //        paint.setColor(colors[num1 % 7]);
        //        canvas.drawPath(path1, paint);
        //        num1++;
        //
        //        path2.addCircle(-80, num11 % 6 == 1 ? 20 : 0, 20, Path.Direction.CW);
        //        paint.setColor(colors[num2 % 7]);
        //        canvas.drawPath(path2, paint);
        //        num2++;
        //
        //        path3.addCircle(-40, num11 % 6 == 2 ? 20 : 0, 20, Path.Direction.CW);
        //        paint.setColor(colors[num3 % 7]);
        //        canvas.drawPath(path3, paint);
        //        num3++;
        //
        //        path4.addCircle(0, num11 % 6 == 3 ? 20 : 0, 20, Path.Direction.CW);
        //        paint.setColor(colors[num4 % 7]);
        //        canvas.drawPath(path4, paint);
        //        num4++;
        //
        //        path5.addCircle(40, num11 % 6 == 4 ? 20 : 0, 20, Path.Direction.CW);
        //        paint.setColor(colors[num5 % 7]);
        //        canvas.drawPath(path5, paint);
        //        num5++;
        //
        //        path6.addCircle(80, num11 % 6 == 5 ? 20 : 0, 20, Path.Direction.CW);
        //        paint.setColor(colors[num6 % 7]);
        //        canvas.drawPath(path6, paint);
        //        num6++;
        //
        //        path7.addCircle(120, num11 % 6 == 6 ? 20 : 0, 20, Path.Direction.CW);
        //        paint.setColor(colors[num7 % 7]);
        //        canvas.drawPath(path7, paint);
        //        num7++;

        //
        path2.addCircle(-radius * 4, 0, radius, Path.Direction.CW);
        canvas.drawPath(path2, paint);
        path3.addCircle(-radius * 2, 0, radius, Path.Direction.CW);
        canvas.drawPath(path3, paint);
        path4.addCircle(0, 0, radius, Path.Direction.CW);
        canvas.drawPath(path4, paint);
        path5.addCircle(radius * 2, 0, radius, Path.Direction.CW);
        canvas.drawPath(path5, paint);
        path6.addCircle(radius * 4, 0, radius, Path.Direction.CW);
        canvas.drawPath(path6, paint);

        //
        path1.addCircle(num11 % 2 == 0 ? -160 : -120, num11 % 2 == 0 ? 40 : 0, radius, Path.Direction.CW);
        canvas.drawPath(path1, paint);

        //        if (num11 % 6 == 0) {
        //            path1.addCircle(-160, 40, 20, Path.Direction.CW);
        //            canvas.drawPath(path1, paint);
        //            path1.addCircle(120, 0, 20, Path.Direction.CW);
        //            canvas.drawPath(path1, paint);
        //        } else if (num11 % 6 == 1) {
        //            path1.addCircle(-160, 40, 20, Path.Direction.CW);
        //            canvas.drawPath(path1, paint);
        //            path1.addCircle(120, 0, 20, Path.Direction.CW);
        //            canvas.drawPath(path1, paint);
        //        }


        path1.addCircle(num11 % 2 == 1 ? 160 : 120, num11 % 2 == 1 ? 40 : 0, radius, Path.Direction.CW);
        canvas.drawPath(path1, paint);

        num11++;


        postDelayed(new Runnable() {
            @Override
            public void run() {
                path1.reset();
                postInvalidate();
            }
        }, 400);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.width = w;
        this.height = h;
    }
}
