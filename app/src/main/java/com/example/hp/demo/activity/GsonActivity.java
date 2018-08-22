package com.example.hp.demo.activity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hp.demo.R;
import com.example.hp.demo.bean.GsonData;
import com.example.hp.demo.bean.User2;
import com.example.hp.demo.constant.Static;
import com.example.hp.demo.utils.CommonUtil;
import com.example.hp.demo.utils.PermissionHelper;
import com.google.gson.Gson;

import net.tsz.afinal.FinalDb;

import java.util.List;

public class GsonActivity extends AppCompatActivity {

    private TextView mName;
    private TextView mBirthday;
    private TextView mAddress;
    private PermissionHelper mPermissionHelper;
    String TAG = "GsonActivity";
    private FinalDb finalDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gson);


        getPermission();
    }

    private void getPermission() {
        // 当系统为6.0以上时，需要申请权限
        mPermissionHelper = new PermissionHelper(this);
        mPermissionHelper.setOnApplyPermissionListener(new PermissionHelper.OnApplyPermissionListener() {
            @Override
            public void onAfterApplyAllPermission() {
                Log.i(TAG, "All of requested permissions has been granted, so run app logic.");
                runApp();
            }


        });
        if (Build.VERSION.SDK_INT < 23) {
            // 如果系统版本低于23，直接跑应用的逻辑
            Log.d(TAG, "The api level of system is lower than 23, so run app logic directly.");
            runApp();
        } else {
            // 如果权限全部申请了，那就直接跑应用逻辑
            if (mPermissionHelper.isAllRequestedPermissionGranted()) {
                Log.d(TAG, "All of requested permissions has been granted, so run app logic directly.");
                runApp();
            } else {
                // 如果还有权限为申请，而且系统版本大于23，执行申请权限逻辑
                Log.i(TAG, "Some of requested permissions hasn't been granted, so apply permissions first.");
                mPermissionHelper.applyPermissions();
            }
        }
    }

    private void runApp() {
        finalDb = FinalDb.create(GsonActivity.this, Environment.getExternalStorageDirectory().getAbsolutePath(), "afinal.db", true, 1, new FinalDb.DbUpdateListener() {
            @Override
            public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

            }
        });
        initView();
        initData();
    }

    private void initView() {
        mName = (TextView) findViewById(R.id.name);
        mBirthday = (TextView) findViewById(R.id.birthday);
        mAddress = (TextView) findViewById(R.id.address);

        final EditText edi_username = (EditText) findViewById(R.id.edi_username);
        final EditText edi_userage = (EditText) findViewById(R.id.edi_userage);
        final EditText edi_useraddress = (EditText) findViewById(R.id.edi_useraddress);
        final EditText edi_name = (EditText) findViewById(R.id.edi_name);
        final EditText edi_age = (EditText) findViewById(R.id.edi_age);
        final EditText edi_address = (EditText) findViewById(R.id.edi_address);
        Button save = (Button) findViewById(R.id.save);
        Button select = (Button) findViewById(R.id.select);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                User2 user = new User2();

                user.setName(CommonUtil.getString(edi_username.getText().toString()));
                user.setAge(CommonUtil.getString(edi_userage.getText().toString()));
                user.setAddress(CommonUtil.getString(edi_useraddress.getText().toString()));

                //                FinalDb finalDb = FinalDb.create(GsonActivity.this);

                finalDb.save(user);
                Static.toastShort(GsonActivity.this, "存储的是--" + CommonUtil.getString(edi_username.getText().toString()) + "----" +
                        CommonUtil.getString(edi_userage.getText().toString()) + "----" + CommonUtil.getString(edi_useraddress.getText().toString()) + "\n");

            }
        });

        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //                List<User> allByWhere = finalDb.findAllByWhere(User.class, new User().NUMBER);
                //                List<User2> all = finalDb.findAll(User2.class);
                //                String mResult = "";
                //                for (int i = 0; i < all.size(); i++) {
                //                    mResult = mResult + all.get(i).getName() + "/" + all.get(i).getAge() + "/" + all.get(i).getAddress() + "\n";
                //                }

                User2 user = new User2();
                user.setName(CommonUtil.getString(edi_name.getText().toString()));
                user.setAge(CommonUtil.getString(edi_age.getText().toString()));
                user.setAddress(CommonUtil.getString(edi_address.getText().toString()));

                List<User2> result = finalDb.findAllByWhere(User2.class, user.getName() == "" ? "" : "name='" + user.getName() + "'");
                for (User2 user2 : result) {
                    Log.e("tag", user2.getName() + "----" + user2.getAge() + "----" + user2.getAddress() + "\n");
                    Static.toastShort(GsonActivity.this, user2.getName() + "----" + user2.getAge() + "----" + user2.getAddress() + "\n");
                }

            }
        });
    }

    private void initData() {
        Gson gson = new Gson();
        String data = "\n" +
                "{\n" +
                " \"da\" : {\"byzk\":\"未服兵役\",\n" +
                " \"csrq\":\"19460902\", \"cym\":\"小明\",\n" +
                " \"hjdQh\":\"浙江省安吉县\",\n" +
                " \"hjdXxdz\":\"浙江省安吉县梅溪村陈桥自然村50号\",\n" +
                " \"hyzk\":\"已婚\",\n" +
                " \"jgQh\":\"浙江省安吉县\",\n" +
                " \"mz\":\"汉族\",\n" +
                " \"sjly\":\"A330523194609021618-00020-de426a18ff0f368f845fcba41a8cbbcd\",\n" +
                " \"validated\":true,\n" +
                " \"whcd\":\"小学\",\n" +
                " \"xb\":\"男\",\n" +
                " \"xm\":\"洪凤鸣\",\n" +
                " \"yxx\":\"\",\n" +
                " \"zjhm\":\"A330523194609021618\"}\n" +
                " }\n" +
                "   ";
        GsonData gsonData = gson.fromJson(data, GsonData.class);

        mName.setText(gsonData.getDa().getXm());
        mBirthday.setText(gsonData.getDa().getCsrq());
        mAddress.setText(gsonData.getDa().getHjdXxdz());

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        mPermissionHelper.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mPermissionHelper.onActivityResult(requestCode, resultCode, data);
    }
}
