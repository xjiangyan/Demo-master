package com.example.hp.demo.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.hp.demo.R;
import com.example.hp.demo.databinding.DatabindingBinding;
import com.example.hp.demo.fragment.BindFragment;

public class DatabindingActivity extends AppCompatActivity {
    private DatabindingBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.databinding);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_contaion, new BindFragment()).commit();
        //        mBinding = DataBindingUtil.setContentView(this, R.layout.databinding);
        //        User user = new User("张三", "6", "中国");
        //        mBinding.setUser(user);
        //        mBinding.tvBirthday.setText("可以实现的");
        //        TextView tvAddress = mBinding.tvAddress;
        //        tvAddress.setOnClickListener(new View.OnClickListener() {
        //            @Override
        //            public void onClick(View v) {
        //                Toast.makeText(DatabindingActivity.this, "是可以点击的", Toast.LENGTH_SHORT).show();
        //            }
        //        });
    }
}
