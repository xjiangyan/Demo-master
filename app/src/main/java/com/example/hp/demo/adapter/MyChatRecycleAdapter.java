package com.example.hp.demo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hp.demo.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Admin
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class MyChatRecycleAdapter extends RecyclerView.Adapter {

    private final ArrayList<HashMap<Object, Object>> arrayList;
    private final Context context;

    public MyChatRecycleAdapter(Context context, ArrayList<HashMap<Object, Object>> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View chatitem = View.inflate(context, R.layout.chatitem, null);
        return new MyViewHolder(chatitem);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myviewholder = (MyViewHolder) holder;

        int person = arrayList.get(position).get("person") == "me" ? 0 : 1;
        myviewholder.setData(person, position);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView chatlist_text_me;
        private final TextView chatlist_text_other;

        public MyViewHolder(View itemView) {
            super(itemView);
            chatlist_text_me = (TextView) itemView.findViewById(R.id.chatlist_text_me);
            chatlist_text_other = (TextView) itemView.findViewById(R.id.chatlist_text_other);
        }

        public void setData(int i, int position) {
            String text = arrayList.get(position).get("text").toString();
            if (i == 0) {
                chatlist_text_me.setText(text);
                chatlist_text_me.setVisibility(View.VISIBLE);
                chatlist_text_other.setVisibility(View.GONE);
            } else {
                chatlist_text_other.setText(text);
                chatlist_text_other.setVisibility(View.VISIBLE);
                chatlist_text_me.setVisibility(View.GONE);
            }
        }
    }
}
