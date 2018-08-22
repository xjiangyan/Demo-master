package com.example.hp.demo.view;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.example.hp.demo.R;
import com.example.hp.demo.databinding.ExpandviewBinding;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static com.example.hp.demo.constant.MapList.KK_JJ_MAP;
import static com.example.hp.demo.constant.MapList.KK_QT_MAP;
import static com.example.hp.demo.constant.MapList.KK_ZA_MAP;

/**
 * @author Admin
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class ExpandView extends RelativeLayout {
    ExpandviewBinding binding;
    private final LayoutInflater layoutInflater;
    private int position = 0;
    private boolean isexpand = false;
    List<Boolean> mBooleanList0 = new ArrayList<>();
    List<Boolean> mBooleanList1 = new ArrayList<>();
    List<Boolean> mBooleanList2 = new ArrayList<>();
    private List<String> datas;

    public ExpandView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        layoutInflater = ((Activity) context).getLayoutInflater();
        initView();
    }

    private void initView() {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.expandview, this, true);
        binding.setExpandviewclick(new expandViewClick());

    }

    public class expandViewClick {
        ExpandViewAdapter mExpandViewAdapter;

        public void startExpand() {
            if (binding.rbKKZA.isChecked()) {
                position = 0;
            } else if (binding.rbKKJJ.isChecked()) {
                position = 1;
            } else if (binding.rbKKQT.isChecked()) {
                position = 2;
            }
            if (isexpand) {
                binding.containerlayout.setVisibility(GONE);
                isexpand =false;
            } else {
                binding.containerlayout.setVisibility(VISIBLE);
                datas = getDatas(position);
                getCheckInfo(position, datas.size());
                switch (position) {
                    case 0:
                        mExpandViewAdapter = new ExpandViewAdapter(datas, getContext(), mBooleanList0);
                        break;
                    case 1:
                        mExpandViewAdapter = new ExpandViewAdapter(datas, getContext(), mBooleanList1);
                        break;
                    case 2:
                        mExpandViewAdapter = new ExpandViewAdapter(datas, getContext(), mBooleanList2);
                        break;
                }
                binding.containerlayout.setAdapter(mExpandViewAdapter);
                isexpand =true;
            }
        }
    }

    private void getCheckInfo(int position, int size) {
        if (position == 0) {
            if (mBooleanList0.size() == 0) {
                for (int i = 0; i < size; i++) {
                    mBooleanList0.add(i, false);
                }
            }
        } else if (position == 1) {
            if (mBooleanList1.size() == 0) {
                for (int i = 0; i < size; i++) {
                    mBooleanList1.add(i, false);
                }
            }
        } else if (position == 2) {
            if (mBooleanList2.size() == 0) {
                for (int i = 0; i < size; i++) {
                    mBooleanList2.add(i, false);
                }
            }
        }
    }


    private List<String> getDatas(int position) {
        Set<String> keys = null;
        int textposition = 0;
        List<String> datas = new ArrayList<>();
        if (position == 0) {

            keys = KK_ZA_MAP.keySet();// 得到全部的key
            Iterator<String> iter = keys.iterator();

            while (iter.hasNext()) {
                String str = iter.next();
                datas.add(textposition, str);
                textposition = textposition + 1;
            }
        } else if (position == 1) {

            keys = KK_JJ_MAP.keySet();// 得到全部的key
            Iterator<String> iter = keys.iterator();

            while (iter.hasNext()) {
                String str = iter.next();
                datas.add(textposition, str);
                textposition = textposition + 1;
            }
        } else if (position == 2) {

            keys = KK_QT_MAP.keySet();// 得到全部的key
            Iterator<String> iter = keys.iterator();

            while (iter.hasNext()) {
                String str = iter.next();
                datas.add(textposition, str);
                textposition = textposition + 1;
            }
        }

        return datas;
    }
}
