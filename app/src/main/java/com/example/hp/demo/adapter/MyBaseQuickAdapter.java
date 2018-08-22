package com.example.hp.demo.adapter;

import android.content.Context;
import android.util.Log;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.hp.demo.R;
import com.example.hp.demo.bean.TestBean;

import java.util.ArrayList;

/**
 * @author Admin
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class MyBaseQuickAdapter extends BaseQuickAdapter<TestBean.ResultBeanX.ResultBean.ListBean> {

    private final Context context;


    public MyBaseQuickAdapter(int layoutResId, Context context) {
        super(layoutResId, new ArrayList<TestBean.ResultBeanX.ResultBean.ListBean>());
        this.context = context;


        Log.e("MyBaseQuickAdapter", "实例化了适配器");
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, TestBean.ResultBeanX.ResultBean.ListBean listBean) {

        baseViewHolder.setText(R.id.tv_itemview, listBean.getQuestion());
        baseViewHolder.setText(R.id.btn_itemview, baseViewHolder.getLayoutPosition() + "");
        baseViewHolder.addOnClickListener(R.id.btn_itemview);
        //
        //        baseViewHolder.addOnClickListener(R.id.tv_itemview);
        Log.e("MyBaseQuickAdapter", "设置了数据");
        //        Log.e(TAG, "点击了" + position);


    }


}
