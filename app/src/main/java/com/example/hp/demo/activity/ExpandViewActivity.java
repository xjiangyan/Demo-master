package com.example.hp.demo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ExpandableListView;

import com.example.hp.demo.R;
import com.example.hp.demo.adapter.MyExpandViewAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ExpandViewActivity extends AppCompatActivity {

    @BindView(R.id.expandview)
    ExpandableListView mExpandview;
    private List<String> mParents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expand_view);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        String[] parents = new String[]{"parents1", "parents2", "parents3", "parents4"};
        String[] childs = new String[]{"child1", "child2", "child3", "child4"};

        MyExpandViewAdapter myExpandViewAdapter = new MyExpandViewAdapter(parents,childs,getApplicationContext());
        mExpandview.setAdapter(myExpandViewAdapter);

    }
}
