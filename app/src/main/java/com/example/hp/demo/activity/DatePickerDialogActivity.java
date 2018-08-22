package com.example.hp.demo.activity;

import android.app.Activity;
import android.app.ActivityManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.bilibili.magicasakura.utils.ThemeUtils;
import com.example.hp.demo.R;
import com.example.hp.demo.utils.ThemeHelper;
import com.example.hp.demo.view.CardPickerDialog;

public class DatePickerDialogActivity extends AppCompatActivity implements CardPickerDialog.ClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_picker_dialog);
        initView();
    }

    private void initView() {

        Button change = (Button) findViewById(R.id.changecolor);
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CardPickerDialog dialog = new CardPickerDialog();
                dialog.setClickListener(DatePickerDialogActivity.this);
                dialog.show(getSupportFragmentManager(), "theme");
            }
        });
    }

    @Override
    public void onConfirm(int currentTheme) {
        if (ThemeHelper.getTheme(DatePickerDialogActivity.this) != currentTheme) {
            ThemeHelper.setTheme(DatePickerDialogActivity.this, currentTheme);
            ThemeUtils.refreshUI(DatePickerDialogActivity.this, new ThemeUtils.ExtraRefreshable() {
                        @Override
                        public void refreshGlobal(Activity activity) {
                            //for global setting, just do once
                            if (Build.VERSION.SDK_INT >= 21) {
                                final DatePickerDialogActivity context = DatePickerDialogActivity.this;
                                ActivityManager.TaskDescription taskDescription =
                                        new ActivityManager.TaskDescription(null, null,
                                                ThemeUtils.getThemeAttrColor(context, android.R.attr.colorPrimary));
                                setTaskDescription(taskDescription);
                                getWindow().setStatusBarColor(
                                        ThemeUtils.getColorById(context, R.color.theme_color_primary_dark));
                            }
                        }

                        @Override
                        public void refreshSpecificView(View view) {
                            //TODO: will do this for each traversal
                        }
                    }
            );
        }
    }

}
