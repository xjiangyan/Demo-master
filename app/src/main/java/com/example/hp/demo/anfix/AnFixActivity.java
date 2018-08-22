package com.example.hp.demo.anfix;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.hp.demo.R;
import com.example.hp.demo.activity.update.UpdateActivity;
import com.example.hp.demo.activity.update.Version;
import com.example.hp.demo.constant.Static;
import com.example.hp.demo.zxing.activity.CaptureActivity;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AnFixActivity extends AppCompatActivity {

    @BindView(R.id.test)
    Button mTest;
    @BindView(R.id.fix)
    Button mFix;
    @BindView(R.id.result)
    Button mResult;
    @BindView(R.id.btndownload)
    Button mDownload;
    @BindView(R.id.progress)
    ProgressBar mProgress;
    @BindView(R.id.capture)
    Button mCapture;
    @BindView(R.id.open_date)
    Button mOpenDate;
    private TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_an_fix);
        ButterKnife.bind(this);

        EventBus.getDefault().register(this);

        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    Static.toastShort(AnFixActivity.this, "语音初始化成功");
                    //                    int result = tts.setLanguage(Locale.CHINESE);

                    //                    if (result != TextToSpeech.LANG_COUNTRY_AVAILABLE && result != TextToSpeech.LANG_AVAILABLE) {

                    //                    } else {

                    //                        tts.speak("测试", TextToSpeech.QUEUE_ADD, null);
                    //                    }
                }
            }
        });
    }

    @OnClick({R.id.test, R.id.fix, R.id.btndownload, R.id.progress, R.id.speech, R.id.capture, R.id.open_date})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.test:
                int caculutor = Caculutor();
                mResult.setText(caculutor + "");
                break;
            case R.id.fix:

                break;
            case R.id.btndownload:
                Version version = new Version();
                version.setMessage("1.新增2.新增3.新增");
                version.setDownloadUrl("https://downapp.baidu.com/baidutieba/AndroidPhone/9.5.8.0/1/1021702g/20180627105420/baidutieba_AndroidPhone_9-5-8-0_1021702g.apk?responseContentDisposition=attachment%3Bfilename%3D%22baidutieba_AndroidPhone_v9.5.8.0%289.5.8.0%29_1021702g.apk%22&responseContentType=application%2Fvnd.android.package-archive&request_id=1531303103_5483617452&type=dynamic");

                UpdateActivity.show(AnFixActivity.this, version);
                break;
            case R.id.progress:
                break;
            case R.id.speech:
                tts.setLanguage(Locale.CHINESE);
                tts.speak("语音初始化成功了", TextToSpeech.QUEUE_ADD, null);
                break;
            case R.id.capture:
                startActivity(new Intent(AnFixActivity.this, CaptureActivity.class));
                break;
            case R.id.open_date:

                dialogDatePickerLight();
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(Integer rate) {//定义并接收事件
        mProgress.setProgress(rate);
    }

    private void dialogDatePickerLight() {
        Calendar cur_calender = Calendar.getInstance();
        DatePickerDialog datePicker = DatePickerDialog.newInstance(
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, monthOfYear);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        long date_ship_millis = calendar.getTimeInMillis();
                        String date = getFormattedDateSimple(date_ship_millis);
                        Toast.makeText(getApplicationContext(), date, Toast.LENGTH_SHORT).show();
                        Toast.makeText(getApplicationContext(), date.substring(0, 4), Toast.LENGTH_SHORT).show();
                        Toast.makeText(getApplicationContext(), date.substring(4, 6), Toast.LENGTH_SHORT).show();
                        Toast.makeText(getApplicationContext(), date.substring(6, 8), Toast.LENGTH_SHORT).show();
                    }
                },
                cur_calender.get(Calendar.YEAR),
                cur_calender.get(Calendar.MONTH),
                cur_calender.get(Calendar.DAY_OF_MONTH)
        );
        //set dark light
        datePicker.setThemeDark(false);
        datePicker.setAccentColor(getResources().getColor(R.color.colorPrimary));
        datePicker.setMinDate(cur_calender);
        datePicker.show(getFragmentManager(), "Datepickerdialog");
    }

    public static String getFormattedDateSimple(Long dateTime) {
        SimpleDateFormat newFormat = new SimpleDateFormat("yyyyMMdd");
        return newFormat.format(new Date(dateTime));
    }

    private int Caculutor() {
        int a = 66, b = 0;
        int c = 0;
        try {
            c = a / b;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        tts.shutdown();
    }
}
