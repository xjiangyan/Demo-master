package com.example.hp.demo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.demo.R;
import com.example.hp.demo.presenter.IVP;
import com.example.hp.demo.presenter.imp.Presenterimp;

import butterknife.ButterKnife;

import static com.example.hp.demo.R.id.tv;

public class MVPActivity extends Activity implements IVP.demo_view, View.OnClickListener {


    private Presenterimp mPresenterimp;
    private Button mBtn;
    private EditText mEdi;
    private TextView mTv;
    private BottomSheetBehavior<View> mBottomSheetBehavior;
    private BottomSheetDialog bottomSheetDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);
        ButterKnife.bind(this);
        initView();
        mPresenterimp = new Presenterimp(getApplicationContext(), this);

        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = mEdi.getText().toString();
                mPresenterimp.isedit(text);
            }
        });
        initBootomBar();
    }

    private void initBootomBar() {
        Button button1 = (Button) findViewById(R.id.button_1);
        Button button2 = (Button) findViewById(R.id.button_2);
        Button button3 = (Button) findViewById(R.id.button_3);
        Button bottomsheetbtn = (Button) findViewById(R.id.bottomsheet_btn);
        bottomsheetbtn.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);

        mBottomSheetBehavior = BottomSheetBehavior.from(findViewById(R.id.bottom_sheet));
        mBottomSheetBehavior.setPeekHeight(0);//设置内容栏初始高度
        mBottomSheetBehavior.setHideable(true);
        //        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);

        mBottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                    mBottomSheetBehavior.setPeekHeight(0);//设置内容栏默认高度为0，即用户看不到
                }
            }

            @Override
            public void onSlide(View bottomSheet, float slideOffset) {
            }
        });

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_1: {
                mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                break;
            }
            case R.id.button_2: {
                mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                break;
            }
            case R.id.button_3: {
                showBottomSheetDialog();
                break;
            }
            case R.id.bottomsheet_btn:
                Toast.makeText(this, "点击了bottomsheet按钮", Toast.LENGTH_SHORT).show();
                //                mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);//?STATE_HIDDEN跟STATE_COLLAPSED？？？？？不要一块使用
                break;
        }
    }

    private void showBottomSheetDialog() {
        bottomSheetDialog = new BottomSheetDialog(MVPActivity.this);

        bottomSheetDialog.setContentView(R.layout.bottom_sheet_dialog_layout);
        initTypeDialog(bottomSheetDialog);

        //        ListView bottom_sheetlistview = (ListView) bottomSheetDialog.findViewById(R.id.bottom_sheetlistview);
        //        bottom_sheetlistview.setAdapter(new MyListAdapter());
        bottomSheetDialog.show();
        //        //数据条数太多会产生异常
        //        bottomSheetDialog.findViewById(R.id.btn3).setOnClickListener(new View.OnClickListener() {
        //            @Override
        //            public void onClick(View v) {
        //                Toast.makeText(MVPActivity.this, "点击了第3个button", Toast.LENGTH_SHORT).show();
        //            }
        //        });
        //        bottomSheetDialog.findViewById(R.id.dismiss).setOnClickListener(new View.OnClickListener() {
        //            @Override
        //            public void onClick(View v) {
        //                bottomSheetDialog.dismiss();
        //            }
        //        });
    }

    private void initTypeDialog(BottomSheetDialog bottomSheetDialog) {

        TextView tv_apply = (TextView) bottomSheetDialog.findViewById(R.id.tv_shenhe);
        TextView tv_qianshou = (TextView) bottomSheetDialog.findViewById(R.id.tv_qianshou);
        tv_qianshou.setVisibility(View.VISIBLE);

    }

    private void initView() {
        mBtn = (Button) findViewById(R.id.btn);
        mEdi = (EditText) findViewById(R.id.edi);
        mTv = (TextView) findViewById(tv);
    }

    @Override
    public void changeword(String word) {
        mTv.setText(word);
    }


    private class MyListAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return 10;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            Button button = new Button(getApplicationContext());
            button.setText("第" + position + "个条目");

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (position != 0) {

                        Toast.makeText(MVPActivity.this, "点击了第" + position + "个条目", Toast.LENGTH_SHORT).show();
                    } else {
                        bottomSheetDialog.dismiss();

                    }
                }
            });
            return button;
        }

    }
}
