package com.example.hp.demo.view;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.hp.demo.R;
import com.example.hp.demo.databinding.NewsitemBinding;
import com.squareup.picasso.Picasso;

/**
 * @author Admin
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class HiddenView extends RelativeLayout {

    private Context context;
    private LayoutInflater mLayoutInflater;
    private NewsitemBinding binding;
    private boolean clickshow = true;

    public HiddenView(Context context) {
        super(context);
    }

    public HiddenView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mLayoutInflater = ((Activity) context).getLayoutInflater();
        this.context = context;
        init();
    }


    public HiddenView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init() {
        binding = DataBindingUtil.inflate(mLayoutInflater, R.layout.newsitem, this, true);
        binding.ivNewsitem.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.leveltwo.setVisibility(clickshow ? VISIBLE : INVISIBLE);
                clickshow = !clickshow;
            }
        });

    }

    public void setData(String title, String time, String pic) {
        binding.tvTitle.setText(title);
        binding.tvContext.setText(time);
        binding.ivNewsitem.setScaleType(ImageView.ScaleType.CENTER_CROP);

        if (pic != null && pic != "") {

            Picasso.with(context)
                    .load(pic)
                    .error(R.drawable.p9).into(binding.ivNewsitem);
        } else {
            binding.ivNewsitem.setImageResource(R.drawable.p11);
        }
    }
}
