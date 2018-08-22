package com.example.hp.demo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hp.demo.R;
import com.example.hp.demo.activity.DragRecycleViewActivity;
import com.example.hp.demo.bean.DragRecyclerBean;
import com.example.hp.demo.utils.ItemAnimation;

import java.util.Collections;
import java.util.List;


public class DragRecyclerAdapter extends RecyclerView.Adapter<DragRecyclerAdapter.ViewHolder> implements DragRecycleViewActivity.onDragListener {
    private final List<DragRecyclerBean.ResultBeanX.ResultBean.ListBean> data;
    private final Context context;
    private int animation_type = 0;


    public DragRecyclerAdapter(DragRecycleViewActivity context, List<DragRecyclerBean.ResultBeanX.ResultBean.ListBean> data) {
        this.context = context;
        this.data = data;
        context.setOnDragListener(this);
    }

    public DragRecyclerAdapter(DragRecycleViewActivity context, List<DragRecyclerBean.ResultBeanX.ResultBean.ListBean> data, int animation_type) {
        this.context = context;
        this.data = data;
        context.setOnDragListener(this);
        this.animation_type = animation_type;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.dragrecycleritem, null);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(context)
                .load(data.get(position).getPic())
                .into(holder.iv_newsitem);
        holder.tv_title.setText(data.get(position).getTitle());
        holder.tv_context.setText(data.get(position).getContent());

        //        holder.itemView;//recyclerview其中整个条目
        setAnimation(holder.itemView, position);//动画效果
    }

    private int lastPosition = -1;
    private boolean on_attach = true;

    private void setAnimation(View view, int position) {
        //        if (position > lastPosition) {
        ItemAnimation.animate(view, on_attach ? position : -1, animation_type);
        lastPosition = position;
        //        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    /**
     * 回调move接口
     *
     * @param fromposition
     * @param toposition
     */
    @Override
    public void onMove(int fromposition, int toposition) {
        Collections.swap(data, fromposition, toposition);
        notifyItemMoved(fromposition, toposition);
    }

    /**
     * 回调swipe接口
     *
     * @param position
     */
    @Override
    public void onSwiped(int position) {
        data.remove(position);
        notifyItemRemoved(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final ImageView iv_newsitem;
        //        public final ImageView iv_img;
        public final TextView tv_title;
        public final TextView tv_context;
        //        public final TextView tv_text;

        public ViewHolder(View itemView) {
            super(itemView);
            iv_newsitem = (ImageView) itemView.findViewById(R.id.iv_newsitem);
            //            iv_img = (ImageView) itemView.findViewById(R.id.iv_img);
            //            tv_text = (TextView) itemView.findViewById(R.id.tv_text);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            tv_context = (TextView) itemView.findViewById(R.id.tv_context);
        }
    }
}
