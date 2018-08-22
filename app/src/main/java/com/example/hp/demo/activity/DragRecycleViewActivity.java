package com.example.hp.demo.activity;

import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.ViewGroup;

import com.example.hp.demo.R;
import com.example.hp.demo.adapter.DragRecyclerAdapter;
import com.example.hp.demo.bean.DragRecyclerBean;
import com.example.hp.demo.constant.Static;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.StringReader;
import java.util.Arrays;
import java.util.List;

public class DragRecycleViewActivity extends AppCompatActivity {

    private onDragListener ondraglistener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drager_recycle_view);

        RecyclerView recy = (RecyclerView) findViewById(R.id.recycler_view);
        recy.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


        JsonReader jsonReader = new JsonReader(new StringReader(Static.newsdata));//其中jsonContext为String类型的Json数据
        jsonReader.setLenient(true);

        Gson gson = new Gson();
        DragRecyclerBean newsBean = gson.fromJson(jsonReader, DragRecyclerBean.class);
        DragRecyclerAdapter dragRecyclerAdapter = new DragRecyclerAdapter(this, newsBean.getResult().getResult().getList(), 4);

        recy.setAdapter(dragRecyclerAdapter);

        MyItemTouchhelper myItemTouchhelper = new MyItemTouchhelper();
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(myItemTouchhelper);
        itemTouchHelper.attachToRecyclerView(recy);


    }

    public static <T> List<T> jsonToList(String jsonStr, Class<T[]> type) {
        Gson gson = new Gson();
        T[] list = gson.fromJson(jsonStr, type);
        return Arrays.asList(list);
    }


    public interface onDragListener {
        void onMove(int fromposition, int toposition);

        void onSwiped(int position);
    }

    public void setOnDragListener(onDragListener ondraglistener) {
        this.ondraglistener = ondraglistener;
    }

    /**
     * 触摸事件
     */
    public class MyItemTouchhelper extends ItemTouchHelper.Callback {


        //限制ImageView长度所能增加的最大值
        private double ICON_MAX_SIZE = 50;
        //ImageView的初始长宽
        private int fixedWidth = 150;


        /**
         * 该方法用于返回可以滑动的方向，比如说允许从右到左侧滑，允许上下拖动等。
         * 我们一般使用makeMovementFlags(int,int)或makeFlag(int, int)来构造我们的返回值。
         */
        @Override
        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {

            //支持上下拖动
            int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
            //支持左右侧滑
            int swipeFlags = ItemTouchHelper.LEFT;

            return makeMovementFlags(dragFlags, swipeFlags);
        }

        /**
         * 当用户拖动一个Item进行上下移动从旧的位置到新的位置的时候会调用该方法，
         * 在该方法内，我们可以调用Adapter的notifyItemMoved方法来交换两个ViewHolder的位置，
         * 最后返回true，表示被拖动的ViewHolder已经移动到了目的位置。
         * 所以，如果要实现拖动交换位置，可以重写该方法（前提是支持上下拖动）
         *
         * @param recyclerView
         * @param viewHolder   原本的数据
         * @param target       目标的数据
         * @return
         */
        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {

            //回调move接口
            ondraglistener.onMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
            return false;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            //回调swipe接口
            ondraglistener.onSwiped(viewHolder.getAdapterPosition());

        }

        @Override
        public boolean isLongPressDragEnabled() {
            return true;
        }

        @Override
        public boolean isItemViewSwipeEnabled() {
            return true;
        }

        /**
         * 当用户操作完毕某个item并且其动画也结束后会调用该方法，一般我们在该方法内恢复ItemView的初始状态，防止由于复用而产生的显示错乱问题。
         */
        @Override
        public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            super.clearView(recyclerView, viewHolder);

            //            viewHolder.itemView.setScrollX(0);
            //            ((DragRecyclerAdapter.ViewHolder) viewHolder).tv_text.setText("左滑删除");
            //            FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) ((DragRecyclerAdapter.ViewHolder) viewHolder).iv_img.getLayoutParams();
            //            params.width = 150;
            //            params.height = 150;
            //            ((DragRecyclerAdapter.ViewHolder) viewHolder).iv_img.setLayoutParams(params);
            //            ((DragRecyclerAdapter.ViewHolder) viewHolder).iv_img.setVisibility(View.INVISIBLE);
        }

        /**
         * 我们可以在这个方法内实现我们自定义的交互规则或者自定义的动画效果。
         */
        @Override
        public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);

            if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                //侧滑的时候条目需要重绘--在此方法里干预，加一个动画效果，属性动画view
                //dx::左滑-width~0,右滑：0~width
                float alpha = 1 - Math.abs(dX) / viewHolder.itemView.getWidth();

                viewHolder.itemView.setScaleX(alpha);
                viewHolder.itemView.setScaleY(alpha);
                viewHolder.itemView.setAlpha(alpha);

                if (alpha == 0) {
                    //防止item复用 导致item还是缩放不显示状态
                    viewHolder.itemView.setScaleX(1);
                    viewHolder.itemView.setScaleY(1);
                    viewHolder.itemView.setAlpha(1);
                }
            }


            //            //仅对侧滑状态下的效果做出改变
            //            if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            //                //如果dX小于等于删除方块的宽度，那么我们把该方块滑出来
            //                if (Math.abs(dX) <= getSlideLimitation(viewHolder)) {
            //                    viewHolder.itemView.scrollTo(-(int) dX, 0);
            //                }
            //                //如果dX还未达到能删除的距离，此时慢慢增加“眼睛”的大小，增加的最大值为ICON_MAX_SIZE
            //                else if (Math.abs(dX) <= recyclerView.getWidth() / 2) {
            //                    double distance = (recyclerView.getWidth() / 2 - getSlideLimitation(viewHolder));
            //                    double factor = ICON_MAX_SIZE / distance;
            //                    double diff = (Math.abs(dX) - getSlideLimitation(viewHolder)) * factor;
            //                    if (diff >= ICON_MAX_SIZE)
            //                        diff = ICON_MAX_SIZE;
            //                    ((DragRecyclerAdapter.ViewHolder) viewHolder).tv_text.setText("");   //把文字去掉
            //                    ((DragRecyclerAdapter.ViewHolder) viewHolder).iv_img.setVisibility(View.VISIBLE);  //显示眼睛
            //                    FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) ((DragRecyclerAdapter.ViewHolder) viewHolder).iv_img.getLayoutParams();
            //                    params.width = (int) (fixedWidth + diff);
            //                    params.height = (int) (fixedWidth + diff);
            //                    ((DragRecyclerAdapter.ViewHolder) viewHolder).iv_img.setLayoutParams(params);
            //                }
            //            } else {
            //                //拖拽状态下不做改变，需要调用父类的方法
            //                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            //            }

        }
    }

    /**
     * 获取删除方块的宽度
     */
    public int getSlideLimitation(RecyclerView.ViewHolder viewHolder) {
        ViewGroup viewGroup = (ViewGroup) viewHolder.itemView;
        return viewGroup.getChildAt(1).getLayoutParams().width;
    }
}
