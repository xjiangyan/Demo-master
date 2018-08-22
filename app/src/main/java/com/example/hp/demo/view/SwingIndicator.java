package com.example.hp.demo.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.example.hp.demo.R;

public class SwingIndicator extends View {
    private ParamsCreator paramsCreator = new ParamsCreator(this.getContext());
    private CircleWrapper leftWrapper;
    private CircleWrapper rightWrapper;
    private Paint paint = new Paint();
    private RectF oval = new RectF();
    //属性
    private int circleRadius;//圆半径
    private int swingRadius;//摆动半径
    private int circleColor;//圆的颜色
    private int increment = 4;//增量

    public SwingIndicator(Context context) {
        super(context);
    }


    //	TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SlidingDrawer, defStyle, 0);
    //	int contentId = a.getResourceId(R.styleable.SlidingDrawer_content, 0);
    // 如果在xml中没有定义SlidingDrawer_content这个属性，则获取到的contentId 值是后面设置的0，
    // 如果定义了SlidingDrawer_content这个属性，则后面设置的默认值0无意义。
    public SwingIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.swingindicator);
        circleRadius = (int) a.getDimension(R.styleable.swingindicator_circleRadius, paramsCreator.getDefaultCircleRadius());
        swingRadius = (int) a.getDimension(R.styleable.swingindicator_swingRadius, paramsCreator.getDefaultSwingRadius());

        circleColor = (int) a.getColor(R.styleable.swingindicator_circleColor, 0);
        if (circleColor == 0)
            circleColor = a.getResourceId(R.styleable.swingindicator_circleColor, 0);
        if (circleColor == 0)
            circleColor = 0xFF7A97EA;

        int cycle = a.getInt(R.styleable.swingindicator_cycle, 400);//周期，默认为2秒
        cycle = cycle / 2;
        int number = (int) (cycle * 1.0 / 1000 * 83);
        this.increment = (int) (this.swingRadius / number);
        this.increment = this.increment <= 0 ? 1 : this.increment;
    }

    /**
     * 测绘
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));
    }

    /**
     * 计算组件宽度
     * /**
     * MeasureSpec封装了父布局传递给子布局的布局要求，每个MeasureSpec代表了一组宽度和高度的要求
     * MeasureSpec由size和mode组成。
     * 三种Mode：
     * 1.UNSPECIFIED
     * 父没有对子施加任何约束，子可以是任意大小（也就是未指定）
     * (UNSPECIFIED在源码中的处理和EXACTLY一样。当View的宽高值设置为0的时候或者没有设置宽高时，模式为UNSPECIFIED
     * 2.EXACTLY
     * 父决定子的确切大小，子被限定在给定的边界里，忽略本身想要的大小。
     * (当设置width或height为match_parent时，模式为EXACTLY，因为子view会占据剩余容器的空间，所以它大小是确定的)
     * 3.AT_MOST
     * 子最大可以达到的指定大小
     * (当设置为wrap_content时，模式为AT_MOST, 表示子view的大小最多是多少，这样子view会根据这个上限来设置自己的尺寸)
     * <p>
     * MeasureSpecs使用了二进制去减少对象的分配。
     */
    private int measureWidth(int measureSpec) {
        int result;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = getDefaultWidth();
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

    /**
     * 计算组件高度
     */
    private int measureHeight(int measureSpec) {
        int result;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = getDefaultHeight();
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

    /**
     * 计算默认宽度
     */
    private int getDefaultWidth() {
        int diameter = this.circleRadius * 2;
        int defaultWidth = diameter * 7 + this.swingRadius * 2;
        return defaultWidth;
    }

    /**
     * 计算默认宽度
     */
    private int getDefaultHeight() {
        return this.swingRadius + this.circleRadius * 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //canvas.drawColor(0xFF00FF33);
        if (this.leftWrapper == null)
            createWrappers();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(this.circleColor);
        drawStatic(canvas);
        drawLeftDynamicCircle(canvas);
        drawRightDynamicCircle(canvas);
        this.invalidate();
    }

    /**
     * 创建wrappers
     */
    private void createWrappers() {
        int diameter = this.circleRadius * 2;//直径
        int totalWidth = diameter * 7 + this.swingRadius * 2;
        int totalHeight = this.swingRadius + diameter;
        //左边的摆圆
        CircleWrapper wrapper = new CircleWrapper();
        wrapper.diameter = diameter;
        wrapper.maxTop = this.getHeight() / 2 + this.swingRadius / 2;
        wrapper.minTop = wrapper.maxTop - this.swingRadius;
        wrapper.dynamicTop = wrapper.minTop;
        wrapper.minLeft = this.getWidth() / 2 - diameter * 3 - this.swingRadius;
        wrapper.maxLeft = wrapper.minLeft + this.swingRadius;
        wrapper.orientation = 1;
        wrapper.active = true;
        this.leftWrapper = wrapper;
        //右边的摆圆
        wrapper = new CircleWrapper();
        wrapper.diameter = diameter;
        wrapper.maxTop = this.getHeight() / 2 + this.swingRadius / 2;
        wrapper.minTop = wrapper.maxTop - this.swingRadius;
        wrapper.dynamicTop = wrapper.maxTop;
        wrapper.minLeft = this.getWidth() / 2 + this.circleRadius * 6;
        wrapper.maxLeft = wrapper.minLeft + this.swingRadius;
        wrapper.orientation = -1;
        wrapper.active = false;
        this.rightWrapper = wrapper;
    }

    /**
     * 画静态的圆
     */
    private void drawStatic(Canvas canvas) {
        int diameter = this.circleRadius * 2;//直径
        int left = this.getWidth() / 2 - this.circleRadius * 5;
        int top = this.getHeight() / 2 + this.swingRadius / 2 - this.circleRadius;
        for (int i = 0; i < 5; i++) {
            oval.left = left;
            oval.top = top;
            oval.right = oval.left + diameter;
            oval.bottom = oval.top + diameter;
            canvas.drawArc(oval, 0, 360, false, paint);
            left += diameter;
        }
    }

    /**
     * 画左边的动态源
     */
    private void drawLeftDynamicCircle(Canvas canvas) {
        CircleWrapper wrapper = this.leftWrapper;
        int diameter = this.circleRadius * 2;//直径
        int y = wrapper.maxTop - wrapper.dynamicTop;
        int x = (int) Math.sqrt(Math.pow(this.swingRadius, 2) - Math.pow(y - this.swingRadius, 2)) - this.swingRadius;
        oval.left = wrapper.minLeft + Math.abs(x) - this.circleRadius;
        oval.top = wrapper.dynamicTop - this.circleRadius;
        oval.right = oval.left + diameter;
        oval.bottom = oval.top + diameter;
        canvas.drawArc(oval, 0, 360, false, paint);
        if (!wrapper.active)
            return;
        wrapper.dynamicTop += this.increment * wrapper.orientation;
        if (wrapper.dynamicTop > wrapper.maxTop)
            wrapper.dynamicTop = wrapper.maxTop;
        if (wrapper.dynamicTop < wrapper.minTop)
            wrapper.dynamicTop = wrapper.minTop;
        if (wrapper.dynamicTop == wrapper.maxTop) {
            wrapper.active = false;
            this.rightWrapper.active = true;
            wrapper.orientation = -1;
        }
        if (wrapper.dynamicTop == wrapper.minTop) {
            wrapper.orientation *= -1;
        }
    }

    /**
     * 画右边的动态源
     */
    private void drawRightDynamicCircle(Canvas canvas) {
        CircleWrapper wrapper = this.rightWrapper;
        int diameter = this.circleRadius * 2;//直径
        int y = wrapper.maxTop - wrapper.dynamicTop;
        int x = (int) Math.sqrt(Math.pow(this.swingRadius, 2) - Math.pow(y - this.swingRadius, 2)) - this.swingRadius;
        System.out.println("xxx=" + x);
        oval.left = wrapper.minLeft - this.circleRadius + (this.swingRadius - Math.abs(x));
        oval.top = wrapper.dynamicTop - this.circleRadius;
        oval.right = oval.left + diameter;
        oval.bottom = oval.top + diameter;
        canvas.drawArc(oval, 0, 360, false, paint);
        if (!wrapper.active)
            return;
        wrapper.dynamicTop += this.increment * wrapper.orientation;
        if (wrapper.dynamicTop > wrapper.maxTop)
            wrapper.dynamicTop = wrapper.maxTop;
        if (wrapper.dynamicTop < wrapper.minTop)
            wrapper.dynamicTop = wrapper.minTop;
        if (wrapper.dynamicTop == wrapper.maxTop) {
            wrapper.active = false;
            this.leftWrapper.active = true;
            wrapper.orientation = -1;
        }
        if (wrapper.dynamicTop == wrapper.minTop) {
            wrapper.orientation *= -1;
        }
    }

    /**
     * 内部类
     */
    private class CircleWrapper {
        private int diameter;//圆的直径
        private int minTop;//圆心的最小top值
        private int maxTop;//圆心的最大top值
        private int dynamicTop;//动态直径
        private int minLeft;
        private int maxLeft;
        private int orientation;//方向，即增加还是减少 1:增加 -1为减少
        private boolean active;//是否活动的
    }
}
