package com.example.hp.demo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hp.demo.R;

/**
 * @author Admin
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class MyExpandViewAdapter extends BaseExpandableListAdapter {
    private final String[] childs;
    private final String[] parents;
    private final Context context;

    public MyExpandViewAdapter(String[] parents, String[] childs, Context context) {
        this.parents = parents;
        this.childs = childs;
        this.context = context;
    }

    @Override
    public int getGroupCount() {
        return parents.length;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return childs.length;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = View.inflate(context, R.layout.parentsview, null);
            viewHolder.tv_parentstitle = (TextView) convertView.findViewById(R.id.tv_title);
            viewHolder.iv_parentstitle = (ImageView) convertView.findViewById(R.id.iv_title);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();

        }
        viewHolder.tv_parentstitle.setText(parents[groupPosition]);
        if (isExpanded) {

            viewHolder.iv_parentstitle.setImageResource(R.drawable.group_down);
        } else {
            viewHolder.iv_parentstitle.setImageResource(R.drawable.group_up);
        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = View.inflate(context, R.layout.childview, null);
            viewHolder.tv_childtitle = (TextView) convertView.findViewById(R.id.tv_title);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();

        }
        viewHolder.tv_childtitle.setText(childs[childPosition]);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    static class ViewHolder {
        public TextView tv_parentstitle;
        public TextView tv_childtitle;
        public ImageView iv_parentstitle;
    }
}
