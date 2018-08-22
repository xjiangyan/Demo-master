package com.example.hp.demo.activity;

import android.databinding.DataBindingUtil;

import com.example.hp.demo.BaseActivity;
import com.example.hp.demo.R;

/**
 * @author Admin
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class RecyclerViewActivity extends BaseActivity {
    @Override
    public void initView() {
        DataBindingUtil.setContentView(this, R.layout.layout_recycleviewactivity);
    }

    @Override
    public void onCreateLoadData() {

    }

    @Override
    public void onResumeLoadData() {

    }
}
