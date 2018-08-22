package com.example.hp.demo.view;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.example.hp.demo.R;


public class LoadAlertDialog extends AlertDialog {

    private TextView status;
    public LoadAlertDialog(Context context) {
        super(context);
    }

    public LoadAlertDialog(Context context, int themeResId){
        super(context,themeResId);
    }

    public LoadAlertDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_alert_loading);
        setCancelable(true);
        status=(TextView)findViewById(R.id.statusTxt);
    }
    public void showWithTxt(String str){
        this.show();
        status.setText(str);
    }
    public void dissmiss(){
        this.dismiss();
    }
}
