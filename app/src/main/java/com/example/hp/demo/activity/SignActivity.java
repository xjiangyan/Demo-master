package com.example.hp.demo.activity;


import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Bitmap;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.hp.demo.R;
import com.fourmob.datetimepicker.date.DatePickerDialog;
import com.github.gcacace.signaturepad.views.SignaturePad;
import com.sleepbot.datetimepicker.time.RadialPickerLayout;
import com.sleepbot.datetimepicker.time.TimePickerDialog;
import com.transitionseverywhere.ArcMotion;
import com.transitionseverywhere.ChangeBounds;
import com.transitionseverywhere.TransitionManager;

import java.io.ByteArrayOutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@RequiresApi(api = Build.VERSION_CODES.N)
public class SignActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    @BindView(R.id.signature_pad)
    SignaturePad mSignaturePad;
    @BindView(R.id.copyimage)
    ImageView mCopyimage;
    @BindView(R.id.clear)
    Button mClear;
    @BindView(R.id.open_date)
    Button mOpenDate;
    @BindView(R.id.rela_contain)
    RelativeLayout mRelaContain;
    @BindView(R.id.bottomimage)
    ImageView mBottomimage;
    @BindView(R.id.fab)
    FloatingActionButton mFab;
    @BindView(R.id.view_smalltobig)
    View mViewSmalltobig;


    private String dateString, timeString;

    private static final String DAY_SPLIT = "-";
    private final Calendar calendar = Calendar.getInstance();
    private final DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), true);
    private final TimePickerDialog timePickerDialog = TimePickerDialog.newInstance(this, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false, false);
    private boolean isReturnAnimation = false;
    private Bitmap signatureBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        ButterKnife.bind(this);
        initView();
        final LottieAnimationView animation_view = (LottieAnimationView) findViewById(R.id.animation_view);
        animation_view.setAnimation("helicopter.json");
        animation_view.loop(true);
        animation_view.playAnimation();
        //
        //        animation_view.addAnimatorListener(new Animator.AnimatorListener() {
        //            @Override
        //            public void onAnimationStart(Animator animation) {
        //
        //            }
        //
        //            @Override
        //            public void onAnimationEnd(Animator animation) {
        //                Toast.makeText(SignActivity.this, "结束了", Toast.LENGTH_SHORT).show();
        //            }
        //
        //            @Override
        //            public void onAnimationCancel(Animator animation) {
        //
        //            }
        //
        //            @Override
        //            public void onAnimationRepeat(Animator animation) {
        //
        //            }
        //        });
    }

    private void initView() {

        mSignaturePad.setOnSignedListener(new SignaturePad.OnSignedListener() {
            @Override
            public void onStartSigning() {

            }

            @Override
            public void onSigned() {
                signatureBitmap = mSignaturePad.getSignatureBitmap();
                mCopyimage.setImageBitmap(signatureBitmap);
            }

            @Override
            public void onClear() {
                mCopyimage.setImageBitmap(null);
            }
        });


    }


    @OnClick({R.id.clear, R.id.open_date, R.id.bottomimage, R.id.copyimage, R.id.fab})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.clear:
                mSignaturePad.clear();
                TransitionManager.beginDelayedTransition(mRelaContain,
                        new ChangeBounds().setPathMotion(new ArcMotion()).setDuration(500));

                FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) mClear.getLayoutParams();
                params.gravity = isReturnAnimation ? (Gravity.BOTTOM | Gravity.RIGHT) : (Gravity.LEFT | Gravity.TOP);
                mClear.setLayoutParams(params);

                isReturnAnimation = !isReturnAnimation;
                break;
            case R.id.open_date:
                openDatePicker();
                break;
            case R.id.bottomimage:
                startActivity(new Intent(SignActivity.this, PaintViewActivity.class),
                        ActivityOptions.makeSceneTransitionAnimation(this, mViewSmalltobig, "smalltobig").toBundle());
                break;
            case R.id.fab:
                startActivity(new Intent(SignActivity.this, PaintViewActivity.class),
                        ActivityOptions.makeSceneTransitionAnimation(this, mFab, "smalltobig").toBundle());
                break;
            case R.id.copyimage:
                if (signatureBitmap != null) {
                    Intent intent = new Intent(SignActivity.this, ShowImageActivity2.class);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    signatureBitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                    byte[] bitmapByte = baos.toByteArray();
                    intent.putExtra("imageinfo", bitmapByte);
                    startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this, mCopyimage, "bigimage").toBundle());
                }
                break;
        }
    }

    private void openTimePicker() {
        timePickerDialog.setVibrate(true);
        timePickerDialog.setCloseOnSingleTapMinute(false);
        timePickerDialog.show(getSupportFragmentManager(), "time-dialog");
    }

    private void openDatePicker() {
        datePickerDialog.setVibrate(true);
        datePickerDialog.setYearRange(2000, 2028);
        datePickerDialog.setCloseOnSingleTapDay(false);
        datePickerDialog.show(getSupportFragmentManager(), "date-dialog");
    }

    @Override
    public void onDateSet(DatePickerDialog datePickerDialog, int year, int month, int day) {
        month++;
        dateString = year + DAY_SPLIT
                + (month < 10 ? "0" + month : String.valueOf(month)) + DAY_SPLIT
                + (day < 10 ? "0" + day : String.valueOf(day));

        openTimePicker();
    }

    @Override
    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute) {
        timeString = (hourOfDay < 10 ? "0" + hourOfDay : "" + hourOfDay) + ":"
                + (minute < 10 ? "0" + minute : "" + minute) + ":00";
        Toast.makeText(this, dateString + " " + timeString, Toast.LENGTH_SHORT).show();
    }
}
