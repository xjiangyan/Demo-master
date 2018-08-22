package com.example.hp.demo.view;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.hp.demo.R;
import com.example.hp.demo.bean.ButtonBean;
import com.example.hp.demo.databinding.VerticalselectorViewBinding;

import java.util.List;

/**
 * @author Admin
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class VerticalSelector extends RelativeLayout {
    private Context context;
    private VerticalselectorViewBinding binding;
    private LayoutInflater mLayoutInflater;

    public VerticalSelector(Context context) {
        super(context);
    }

    public VerticalSelector(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        mLayoutInflater = ((Activity) context).getLayoutInflater();
        init();
    }


    public VerticalSelector(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init() {

        binding = DataBindingUtil.inflate(mLayoutInflater, R.layout.verticalselector_view, this, true);
    }


    public void initLeftRecycleView(List<String> datas) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        binding.recyclerLeft.setLayoutManager(linearLayoutManager);
        ButtonBean buttonBean = new ButtonBean();
        for (int i = 0; i < 100; i++) {
            buttonBean.setIscheck(false);
        }
        binding.recyclerLeft.setAdapter(new LeftAdapter());

    }

    public void initRightRecycleView(List<String> datas) {
        GridLayoutManager manager = new GridLayoutManager(getContext(), 1);

        //设置滑动到哪个位置了的监听
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                binding.recyclerLeft.scrollToPosition(position);
                return 1;
            }
        });
        binding.recyclerRight.setLayoutManager(manager);
        binding.recyclerRight.setAdapter(new RightAdapter());
    }

    public void changeRightPosition(int position) {
        binding.recyclerRight.scrollToPosition(position);
    }

    private class LeftAdapter extends RecyclerView.Adapter {
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View inflate = View.inflate(getContext(), R.layout.text_items, null);

            return new LeftViewHolder(inflate);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
            LeftViewHolder viewHolder1 = (LeftViewHolder) viewHolder;
            viewHolder1.setData(i);
        }

        @Override
        public int getItemCount() {
            return 100;
        }
    }

    private class LeftViewHolder extends RecyclerView.ViewHolder {


        private final TextView mTv_title;

        public LeftViewHolder(View inflate) {
            super(inflate);
            mTv_title = (TextView) inflate.findViewById(R.id.tv_title);
        }

        public void setData(final int position) {
            mTv_title.setText("ipad   " + position);
            mTv_title.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    leftonitemclick.onLeftItemClick(position);
                }
            });
        }
    }

    private LeftOnItemClick leftonitemclick;

    public interface LeftOnItemClick {
        void onLeftItemClick(int position);
    }

    public void setonLeftItemClickListener(LeftOnItemClick leftonitemclick) {
        this.leftonitemclick = leftonitemclick;
    }

    private class RightAdapter extends RecyclerView.Adapter {
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View inflate = View.inflate(getContext(), R.layout.imageview_items, null);

            return new RightViewHolder(inflate);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
            RightViewHolder viewHolder1 = (RightViewHolder) viewHolder;
            viewHolder1.setData(i);
        }

        @Override
        public int getItemCount() {
            return 100;
        }
    }

    private class RightViewHolder extends RecyclerView.ViewHolder {

        private int[] image = new int[]{R.drawable.ss, R.drawable.hh};

        private final ImageView iv_image;
        private final TextView mTextView;

        public RightViewHolder(View inflate) {
            super(inflate);
            iv_image = (ImageView) inflate.findViewById(R.id.iv_image);
            mTextView = (TextView) inflate.findViewById(R.id.position);
        }

        public void setData(int position) {
            iv_image.setImageResource(image[position % 2]);
            mTextView.setText(position + "");
        }
    }
}
