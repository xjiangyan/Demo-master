package com.example.hp.demo.adapter;

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
public class ExpandCheckboxAdapter extends BaseAdapter {
    private Context context;

    private List<String> datas;
    private List<Boolean> checkInfoList;

    public ExpandCheckboxAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<String> datas, List<Boolean> checkInfoList) {
        this.datas = datas;
        this.checkInfoList = checkInfoList;
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
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        CheckBox checkBox = new CheckBox(context);

        if (checkInfoList.get(position)) {
            checkBox.setChecked(true);
        } else {
            checkBox.setChecked(false);
        }
        checkBox.setText(datas.get(position));


        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()

        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checkInfoList.set(position, isChecked);

            }
        });
        return checkBox;


    }
}
