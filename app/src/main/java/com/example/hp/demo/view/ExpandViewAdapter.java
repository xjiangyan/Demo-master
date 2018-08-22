package com.example.hp.demo.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import java.util.List;

/**
 * @author Admin
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
class ExpandViewAdapter extends BaseAdapter {
    private final List<String> datas;
    private final Context context;
    private final List<Boolean> booleanList;

    public ExpandViewAdapter(List<String> datas, Context context, List<Boolean> booleanList) {
        this.datas = datas;
        this.context = context;
        this.booleanList = booleanList;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        CheckBox checkBox = new CheckBox(context);
        checkBox.setChecked(booleanList.get(position));
        checkBox.setText(datas.get(position));
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    booleanList.set(position, isChecked);
                }
            }
        });
        return checkBox;
    }
}
