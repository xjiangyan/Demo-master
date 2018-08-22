package com.example.hp.demo.activity;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import com.example.hp.demo.R;

public class GridLayoutActivity extends AppCompatActivity implements View.OnClickListener {
    /**
     * 1.给对象设置长按点击事件，开始拖拽操作
     * 2.给gridlayout设置拖拽监听
     * 3.当拖拽事件开始时，动态创建每个子条目的矩形对象，用来判断手指是否进入到某个子条目范围内
     * 4.当拖拽事件location执行时，使用rect的contains方法，判断是否进入到了某个子条目范围内
     * 如果进入了则进行删除添加操作
     */

    private int index = 0;
    private Button mAddItem;
    private GridLayout mGridlayout;
    private Rect[] mRects;
    private View dragView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_layout);
        init();
    }

    private void init() {
        mAddItem = (Button) findViewById(R.id.btn_addItem);
        mAddItem.setOnClickListener(this);
        mGridlayout = (GridLayout) findViewById(R.id.gridlayout);
        mGridlayout.setOnDragListener(new MyOnDragListener());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_addItem:
                addItem();
                break;

        }
    }

    private void addItem() {
        final TextView tv = new TextView(getApplicationContext());
        tv.setGravity(Gravity.CENTER);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                (getWindowManager().getDefaultDisplay().getWidth() / 4) - 10, ViewGroup.LayoutParams.WRAP_CONTENT);
        //        GridLayout.LayoutParams params1 = new GridLayout.LayoutParams(params);
        //        params1.setMargins(5, 5, 5, 5);
        tv.setLayoutParams(params);

        tv.setBackgroundResource(R.drawable.shape_tv_bg);
        tv.setText(index + "");
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGridlayout.removeView(tv);
            }
        });
        tv.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                v.startDragAndDrop(null, new View.DragShadowBuilder(tv), null, 0);
                dragView = v;
                return true;
            }
        });
        index++;
        mGridlayout.addView(tv, 0);

    }

    private class MyOnDragListener implements View.OnDragListener {
        @Override
        public boolean onDrag(View v, DragEvent event) {
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    Log.e("MyOnDragListener", "ACTION_DRAG_STARTED");
                    initRect();

                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    Log.e("MyOnDragListener", "ACTION_DRAG_ENTERED");

                    break;
                case DragEvent.ACTION_DRAG_LOCATION:
                    Log.e("MyOnDragListener", "ACTION_DRAG_LOCATION");

                    int index = getIndex(event);
                    if (index != -1 && dragView != null && dragView != mGridlayout.getChildAt(index)) {
                        mGridlayout.removeView(dragView);
                        mGridlayout.addView(dragView, index);
                    }


                    break;

                case DragEvent.ACTION_DROP:
                    Log.e("MyOnDragListener", "ACTION_DROP");

                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    Log.e("MyOnDragListener", "ACTION_DRAG_ENDED");

                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    Log.e("MyOnDragListener", "ACTION_DRAG_EXITED");
                    break;

            }

            return true;
        }
    }

    private int getIndex(DragEvent event) {
        for (int i = 0; i < mRects.length; i++) {
            if (mRects[i].contains((int) event.getX(), (int) event.getY())) {
                return i;
            }
        }
        return -1;
    }

    private void initRect() {
        mRects = new Rect[mGridlayout.getChildCount()];
        for (int i = 0; i < mGridlayout.getChildCount(); i++) {
            View childView = mGridlayout.getChildAt(i);
            mRects[i] = new Rect(childView.getLeft(), childView.getTop(), childView.getRight(), childView.getBottom());

        }
    }
}
