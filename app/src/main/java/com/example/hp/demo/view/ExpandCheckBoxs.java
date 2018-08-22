package com.example.hp.demo.view;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.example.hp.demo.R;
import com.example.hp.demo.adapter.ExpandCheckboxAdapter;
import com.example.hp.demo.databinding.ExpandcheckboxsBinding;

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
public class ExpandCheckBoxs extends RelativeLayout {

    private LayoutInflater mLayoutInflater;
    ExpandcheckboxsBinding binding;
    boolean isexpand = false;
    int position = 0;

    private List<Boolean> checkInfoList1 = new ArrayList<>();
    private List<Boolean> checkInfoList2 = new ArrayList<>();
    private List<Boolean> checkInfoList3 = new ArrayList<>();

    public ExpandCheckBoxs(Context context) {
        super(context);
        mLayoutInflater = ((Activity) context).getLayoutInflater();
        initView();
    }

    public ExpandCheckBoxs(Context context, AttributeSet attrs) {
        super(context, attrs);
        mLayoutInflater = ((Activity) context).getLayoutInflater();
        initView();
    }

    public ExpandCheckBoxs(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mLayoutInflater = ((Activity) context).getLayoutInflater();
        initView();
    }

    private void initView() {
        binding = DataBindingUtil.inflate(mLayoutInflater, R.layout.expandcheckboxs, this, true);
        binding.setExpandviewclick(new expandViewClick());

    }

    public class expandViewClick {

        private ExpandCheckboxAdapter mExpandViewAdapter;

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
                isexpand = false;
            } else {
                List<String> datas = getDatas(position);//初始化数据
                tryInitCheckInfoList(position, datas.size());//初始化选中
                if (mExpandViewAdapter == null) {
                    mExpandViewAdapter = new ExpandCheckboxAdapter(getContext());
                }
                switch (position) {
                    case 0:
                        mExpandViewAdapter.setDatas(datas, checkInfoList1);
                        break;
                    case 1:
                        mExpandViewAdapter.setDatas(datas, checkInfoList2);
                        break;
                    case 2:
                        mExpandViewAdapter.setDatas(datas, checkInfoList3);
                        break;
                }

                binding.containerlayout.setAdapter(mExpandViewAdapter);
                binding.containerlayout.setVisibility(VISIBLE);
                isexpand = true;

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

    private void tryInitCheckInfoList(int position, int size) {
        if (position == 0) {
            if (checkInfoList1.size() == 0) {
                for (int i = 0; i < size; i++) {
                    checkInfoList1.add(i, false);
                }
            }
        } else if (position == 1) {
            if (checkInfoList2.size() == 0) {
                for (int i = 0; i < size; i++) {
                    checkInfoList2.add(i, false);
                }
            }
        } else if (position == 2) {
            if (checkInfoList3.size() == 0) {
                for (int i = 0; i < size; i++) {
                    checkInfoList3.add(i, false);
                }
            }
        }
    }

}
