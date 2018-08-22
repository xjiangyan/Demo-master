package com.example.hp.demo.fragment;

import android.Manifest;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.hp.demo.R;
import com.example.hp.demo.bean.RYBasicInfo;
import com.example.hp.demo.bean.User;
import com.example.hp.demo.databinding.FragmentbindingBinding;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionNo;
import com.yanzhenjie.permission.PermissionYes;


/**
 * @author Admin
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class BindFragment extends Fragment {
    private FragmentbindingBinding binding;
    private View rootView;
    private static final int REQUESTCODE = 1;

    public BindFragment() {
    }

    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView != null) {
            return rootView;
        }
        binding = DataBindingUtil.inflate(inflater, R.layout.fragmentbinding, container, false);
        binding.setClick(new MyClick());

        rootView = binding.getRoot();
        User user = new User("张三", "6", "中国");
        binding.setUser(user);
        binding.tvBirthday.setText("可以实现的");
        //                TextView tvAddress = binding.tvAddress;
        //                tvAddress.setOnClickListener(new View.OnClickListener() {
        //                    @Override
        //                    public void onClick(View v) {
        //                        Toast.makeText(getContext(), "是可以点击的", Toast.LENGTH_SHORT).show();
        //                    }
        //                });
        RYBasicInfo.RYBasic bean = new RYBasicInfo.RYBasic();
        bean.setByzk("****");
        binding.setRybasic(bean);
        //                RYBasicInfo bean = new RYBasicInfo();
        //                bean.getDa().setByzk("//////////");
        //                binding.setRybasicinfo(bean);

        return rootView;
    }

    public class MyClick {
        public void onMyClick(View view) {
            switch (view.getId()) {
                case R.id.tv_name:
                    Toast.makeText(getContext(), "点击的是姓名", Toast.LENGTH_SHORT).show();
                    binding.tvName.setText("姓名");
                    break;
                case R.id.tv_address:
                    Toast.makeText(getContext(), "点击的是地址", Toast.LENGTH_SHORT).show();
                    binding.tvAddress.setText("地址");

                    break;
            }
        }

        public void onMyRequest() {

            //            MPermissions.requestPermissions(BindFragment.this, REQUESTCODE, Manifest.permission.CAMERA);
            // 在Fragment：
            AndPermission.with(BindFragment.this)
                    .requestCode(101)
                    .permission(
                            // 申请多个权限组方式：
                            Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE
                    )
                    .callback(this)
                    .start();
        }

    }

    @PermissionYes(101)
    public void getpersissionSucess() {
        Toast.makeText(getContext(), "权限申请成功", Toast.LENGTH_SHORT).show();
    }

    @PermissionNo(101)
    public void getpersissionFail() {
        Toast.makeText(getContext(), "权限申请失败", Toast.LENGTH_SHORT).show();

    }

    //
    //    @PermissionGrant(REQUESTCODE)
    //    public void requestContactSuccess() {
    //        //        binding.tvName.setText("权限申请成功！");
    //        Log.e("BindFragment--------", "GRANT ACCESS CONTACTS!");
    //
    //    }
    //
    //    @PermissionDenied(REQUESTCODE)
    //    public void requestContactFailed() {
    //        //        binding.tvName.setText("权限申请失败！");
    //
    //        Log.e("BindFragment-------", "DENY ACCESS CONTACTS!");
    //    }

    //    @Override
    //    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    //        MPermissions.onRequestPermissionsResult(this, requestCode, permissions, grantResults);//onRequestPermissionsResult
    //        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    //    }
}
