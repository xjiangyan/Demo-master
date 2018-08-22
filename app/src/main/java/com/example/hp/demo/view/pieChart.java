package com.example.hp.demo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
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
public class pieChart extends View {
    private Paint paint;
    private Path path;
    private int piecharh;
    private int piecharw;
    private int size = 6;
    private int[] piecolor = new int[]{Color.BLUE, Color.RED, Color.GREEN, Color.GRAY, Color.WHITE, Color.YELLOW};
    private int angleto = -1;

    public pieChart(Context context) {
        super(context);
    }

    public pieChart(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    public pieChart(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init() {
        paint = new Paint();
        path = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(piecharw / 2, piecharh / 2);
        int length = piecharh < piecharw ? piecharh / 2 : piecharw / 2;
        for (int i = 0; i < size; i++) {
            path.reset();
            //            path.addArc(new RectF(-length, -length, length, length),
            //                    (float) ((360 / size) * i), 360 / size);
            paint.setColor(piecolor[i]);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawArc(new RectF(-length, -length, length, length), (float) ((360 / size) * i), 360 / size, true, paint);//true连接中心点
            //            canvas.drawPath(path, paint);
        }
        //        paint.setColor(Color.BLACK);
        //        paint.setStyle(Paint.Style.FILL);
        //        path.addCircle(piecharw / 2, piecharh / 2, (piecharh < piecharw ? piecharh/2 : piecharw/2), Path.Direction.CW);
        //        canvas.drawPath(path, paint);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.piecharw = w;
        this.piecharh = h;
    }
}
