package com.example.hp.demo.view;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by ZhiLiSteven on 2017/9/30.
 */

public class WaterMarkView extends Drawable {

    private Paint paint = new Paint();

    private String logo = "SoYoung";

    public WaterMarkView(String logo) {
        this.logo = logo;
    }

    @Override
    public void draw(@NonNull Canvas canvas) {

        int width = getBounds().right;
        int height = getBounds().bottom;

//        canvas.drawColor(Color.parseColor("#F3F5F9"));
        paint.setColor(Color.parseColor("#AEAEAE"));
        paint.setAntiAlias(true);
        paint.setTextSize(30);
        canvas.save();
        canvas.rotate(-30);
        float textWidth = paint.measureText(logo);
        int index = 0;
        for (int positionY = height / 10; positionY <= height; positionY += height / 10) {
            float fromX = -width + (index++ % 2) * textWidth;
            for (float positionX = fromX; positionX < width; positionX += textWidth * 2) {
                canvas.drawText(logo, positionX, positionY, paint);
            }
        }
        canvas.restore();
    }

    @Override
    public void setAlpha(@IntRange(from = 0, to = 255) int alpha) {

    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return PixelFormat.UNKNOWN;
    }
}