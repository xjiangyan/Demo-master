package com.example.hp.demo.adapter;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.hp.demo.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


/**
 * @author Admin
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class NewsAdapter extends BaseQuickAdapter<com.example.hp.demo.bean.NewsBean.ResultBeanX.ResultBean.ListBean> {


    private final Context context;
    private ArrayList<Boolean> mClickshow;


    public NewsAdapter(int layoutResId, Context context) {
        super(layoutResId, new ArrayList<com.example.hp.demo.bean.NewsBean.ResultBeanX.ResultBean.ListBean>());
        this.context = context;
        Log.e("SYFragment", "适配器初始化");
    }

    @Override
    protected void convert(final BaseViewHolder baseViewHolder, final com.example.hp.demo.bean.NewsBean.ResultBeanX.ResultBean.ListBean listBean) {
        //        listBean.setIsexpand(true);

        baseViewHolder.setText(R.id.tv_title, listBean.getTitle());
        baseViewHolder.setText(R.id.tv_context, listBean.getTime());
        Log.e("SYFragment", "适配器设置了数据");
        ImageView image = (ImageView) baseViewHolder.getView(R.id.iv_newsitem);
        image.setScaleType(ImageView.ScaleType.CENTER_CROP);

        if (listBean.getPic() != null && listBean.getPic() != "") {

            Picasso.with(context)
                    .load(listBean.getPic())
                    .error(R.drawable.p9).into(image);
        } else {
            image.setImageResource(R.drawable.p11);
        }
        baseViewHolder.addOnClickListener(R.id.iv_newsitem)
                .addOnLongClickListener(R.id.iv_newsitem);


        //        baseViewHolder.getView(R.id.iv_newsitem).setOnClickListener(null);

        //        image.setOnClickListener(new View.OnClickListener() {
        //            @Override
        //            public void onClick(View v) {
        //                baseViewHolder.getView(R.id.leveltwo).setVisibility(mClickshow.get(baseViewHolder.getAdapterPosition()) ? View.VISIBLE : View.GONE);
        //                mClickshow.set(baseViewHolder.getAdapterPosition(), !mClickshow.get(baseViewHolder.getAdapterPosition()));
        //            }
        //        });
        //        HiddenView hiddenview = baseViewHolder.getView(R.id.hiddenview);
        //        hiddenview.setData(listBean.getTitle(),listBean.getTime(),listBean.getPic());
    }

    public void setcount(int count) {
        mClickshow = new ArrayList<>();

        for (int i = 0; i < count; i++) {

            mClickshow.add(true);
        }
    }
}
