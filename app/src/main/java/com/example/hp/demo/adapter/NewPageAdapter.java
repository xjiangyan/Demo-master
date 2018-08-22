package com.example.hp.demo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.hp.demo.R;
import com.example.hp.demo.bean.NewsBean;
import com.squareup.picasso.Picasso;

/**
 * @author Admin
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class NewPageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final Context context;
    private final NewsBean.ResultBeanX.ResultBean.ListBean bean;
    private boolean clickshow = false;

    public NewPageAdapter(Context context, NewsBean.ResultBeanX.ResultBean.ListBean bean) {
        this.context = context;
        this.bean = bean;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.newsitem, viewGroup);
        return new NewsHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        ((NewsHolder) viewHolder).mLeveltwo.setVisibility(clickshow ? View.VISIBLE : View.GONE);
        ((NewsHolder) viewHolder).setData(bean, i);
    }

    @Override
    public int getItemCount() {
        return 100;
    }

    private class NewsHolder extends RecyclerView.ViewHolder {

        private final RelativeLayout mRela_cntain;
        private final TextView mTv_title;
        private final TextView mTv_context;
        private final RelativeLayout mLeveltwo;
        private final ImageView mIv_newsitem;

        public NewsHolder(View itemView) {
            super(itemView);
            mRela_cntain = (RelativeLayout) itemView.findViewById(R.id.rela_contain);
            mTv_title = (TextView) itemView.findViewById(R.id.tv_title);
            mTv_context = (TextView) itemView.findViewById(R.id.tv_context);
            mLeveltwo = (RelativeLayout) itemView.findViewById(R.id.leveltwo);
            mIv_newsitem = (ImageView) itemView.findViewById(R.id.iv_newsitem);
        }

        public void setData(NewsBean.ResultBeanX.ResultBean.ListBean bean, final int i) {
            mTv_context.setText(bean.getContent());
            mTv_title.setText(bean.getTitle());

            if (bean.getPic() != null && bean.getPic() != "") {

                Picasso.with(context)
                        .load(bean.getPic())
                        .error(R.drawable.p9).into(mIv_newsitem);
            } else {
                mIv_newsitem.setImageResource(R.drawable.p11);
            }

            mRela_cntain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickshow = !clickshow;
                    notifyItemChanged(i);
                }
            });
        }
    }
}

