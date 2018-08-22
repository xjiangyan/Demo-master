package com.example.hp.demo.activity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.hp.demo.R;
import com.example.hp.demo.bean.User2;
import com.example.hp.demo.utils.CommonUtil;

import net.tsz.afinal.FinalDb;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DatabaseActivity extends AppCompatActivity {

    @BindView(R.id.edi_username)
    EditText mEdiUsername;
    @BindView(R.id.edi_userage)
    EditText mEdiUserage;
    @BindView(R.id.edi_usernaddress)
    EditText mEdiUsernaddress;
    @BindView(R.id.line_edi)
    LinearLayout mLineEdi;
    @BindView(R.id.add)
    Button mAdd;
    @BindView(R.id.delete)
    Button mDelete;
    @BindView(R.id.update)
    Button mUpdate;
    @BindView(R.id.select)
    Button mSelect;
    @BindView(R.id.line_btn)
    LinearLayout mLineBtn;
    @BindView(R.id.listview)
    ListView mListview;
    @BindView(R.id.add3)
    Button mAdd3;
    @BindView(R.id.delete3)
    Button mDelete3;
    @BindView(R.id.update3)
    Button mUpdate3;
    @BindView(R.id.select3)
    Button mSelect3;
    @BindView(R.id.refresh3)
    Button mRefresh3;
    private MyListViewAdapter myListViewAdapter;
    private List<User2> data;
    private FinalDb finalDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        ButterKnife.bind(this);
        finalDb = FinalDb.create(DatabaseActivity.this, Environment.getExternalStorageDirectory().getAbsolutePath(), "database.db", true, 1, new FinalDb.DbUpdateListener() {
            @Override
            public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
                Log.e("tag", "数据库版本--" + i);
            }
        });

        init();
    }


    private void init() {
        data = new ArrayList<>();
        myListViewAdapter = new MyListViewAdapter();
        mListview.setAdapter(myListViewAdapter);
    }

    @OnClick({R.id.add, R.id.delete, R.id.update, R.id.select, R.id.refresh, R.id.add2, R.id.delete2, R.id.update2, R.id.select2, R.id.refresh2, R.id.add3, R.id.delete3, R.id.update3, R.id.select3, R.id.refresh3})
    public void onViewClicked(View view) {
        String name = CommonUtil.getString(mEdiUsername.getText().toString());
        String age = CommonUtil.getString(mEdiUserage.getText().toString());
        String address = CommonUtil.getString(mEdiUsernaddress.getText().toString());
        User2 user2 = new User2();
        user2.setName(name);
        user2.setAge(age);
        user2.setAddress(address);

        switch (view.getId()) {
            case R.id.add:
                finalDb.save(user2);
                List<User2> all2 = finalDb.findAll(User2.class);
                data.clear();
                data.addAll(all2);
                myListViewAdapter.notifyDataSetChanged();

                break;
            case R.id.delete:
                //                finalDb.delete(User2.class);
                finalDb.deleteByWhere(User2.class, "name='" + name + "' and age='" + age + "' and address='" + address + "'");
                List<User2> all1 = finalDb.findAll(User2.class);
                data.clear();
                data.addAll(all1);
                myListViewAdapter.notifyDataSetChanged();

                break;
            case R.id.update:
                //                finalDb.update(user2);
                finalDb.update(User2.class, "name='" + name + "'");
                List<User2> all = finalDb.findAll(User2.class);
                data.clear();
                data.addAll(all);
                myListViewAdapter.notifyDataSetChanged();

                break;
            case R.id.select:

                List<User2> allByWhere = finalDb.findAllByWhere(User2.class, "name='" + name + "' and age='" + age + "' and address='" + address + "'");
                data.clear();
                data.addAll(allByWhere);
                myListViewAdapter.notifyDataSetChanged();

                break;
            case R.id.refresh:
                List<User2> all3 = finalDb.findAll(User2.class);
                data.clear();
                data.addAll(all3);
                myListViewAdapter.notifyDataSetChanged();

                break;
                /*
                litepal操作
                */
            case R.id.add2:

                user2.save();//继承litepalsupport 里面有save方法
                List<User2> all4 = LitePal.findAll(User2.class);
                data.clear();
                data.addAll(all4);
                myListViewAdapter.notifyDataSetChanged();

                break;
            case R.id.delete2:
                LitePal.deleteAll(User2.class, "name=? and age=? and address=?", name, age, address);//精确删除
                List<User2> all5 = LitePal.findAll(User2.class);
                data.clear();
                data.addAll(all5);
                myListViewAdapter.notifyDataSetChanged();

                break;
            case R.id.update2:
                ContentValues contentValues = new ContentValues();
                contentValues.put("name", name);
                contentValues.put("age", age);
                contentValues.put("address", address);
                LitePal.updateAll(User2.class, contentValues, "name=?", name);//修改名字为name的值为ContentValues

                List<User2> all6 = LitePal.findAll(User2.class);
                data.clear();
                data.addAll(all6);
                myListViewAdapter.notifyDataSetChanged();

                break;
            case R.id.select2:
                List<User2> user2s = LitePal.where("name like ?", name)//根据名字搜索
                        .find(User2.class);
                data.clear();
                data.addAll(user2s);
                myListViewAdapter.notifyDataSetChanged();

                break;
            case R.id.refresh2:

                List<User2> all7 = LitePal.findAll(User2.class);
                data.clear();
                data.addAll(all7);
                myListViewAdapter.notifyDataSetChanged();

                break;
                /*
               GreenDao操作
                */
            case R.id.add3:

                user2.save();//继承litepalsupport 里面有save方法
                List<User2> all8 = LitePal.findAll(User2.class);
                data.clear();
                data.addAll(all8);
                myListViewAdapter.notifyDataSetChanged();

                break;
            case R.id.delete3:
                LitePal.deleteAll(User2.class, "name=? and age=? and address=?", name, age, address);//精确删除
                List<User2> all9 = LitePal.findAll(User2.class);
                data.clear();
                data.addAll(all9);
                myListViewAdapter.notifyDataSetChanged();

                break;
            case R.id.update3:
                ContentValues contentValues99 = new ContentValues();
                contentValues99.put("name", name);
                contentValues99.put("age", age);
                contentValues99.put("address", address);
                LitePal.updateAll(User2.class, contentValues99, "name=?", name);//修改名字为name的值为ContentValues

                List<User2> all10 = LitePal.findAll(User2.class);
                data.clear();
                data.addAll(all10);
                myListViewAdapter.notifyDataSetChanged();

                break;
            case R.id.select3:
                List<User2> user2s99 = LitePal.where("name like ?", name)//根据名字搜索
                        .find(User2.class);
                data.clear();
                data.addAll(user2s99);
                myListViewAdapter.notifyDataSetChanged();

                break;
            case R.id.refresh3:

                List<User2> all11 = LitePal.findAll(User2.class);
                data.clear();
                data.addAll(all11);
                myListViewAdapter.notifyDataSetChanged();

                break;
        }
    }

    private class MyListViewAdapter extends BaseAdapter {


        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View root = View.inflate(DatabaseActivity.this, R.layout.database_item, null);
            TextView tv_name = (TextView) root.findViewById(R.id.tv_name1);
            TextView tv_age = (TextView) root.findViewById(R.id.tv_age1);
            TextView tv_address = (TextView) root.findViewById(R.id.tv_address1);
            tv_name.setText(data.get(position).getName());
            tv_age.setText(data.get(position).getAge());
            tv_address.setText(data.get(position).getAddress());
            return root;
        }
    }
}
