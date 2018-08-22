package com.example.hp.demo.view;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.example.hp.demo.R;
import com.example.hp.demo.databinding.MycustomBinding;


/**
 * @author Admin
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class MyCustomView extends RelativeLayout {

    private MycustomBinding binding;
    private LayoutInflater mLayoutInflater;


    public MyCustomView(Context context) {
        super(context);
        mLayoutInflater = ((Activity) context).getLayoutInflater();

        init();
    }

    public MyCustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mLayoutInflater = ((Activity) context).getLayoutInflater();

        init();
    }

    public MyCustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mLayoutInflater = ((Activity) context).getLayoutInflater();

        init();
    }


    private void init() {

        binding = DataBindingUtil.inflate(mLayoutInflater, R.layout.mycustom, this, true);

        binding.proCustom.setMax(100);
        binding.proCustom.setProgress(20);


    }

    public void setSucess() {

        Log.e("Custom", "cheng");
        binding.teCustom.setText("加载成功！");
        binding.proCustom.setProgress(100);


    }

    public void setFail() {

        Log.e("Custom", "shibai");
        binding.proCustom.setProgress(0);
        binding.teCustom.setText("加载失败！");


    }
}
