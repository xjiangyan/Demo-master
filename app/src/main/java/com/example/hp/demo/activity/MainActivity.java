package com.example.hp.demo.activity;

import android.Manifest;
import android.animation.ValueAnimator;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.airbnb.lottie.LottieAnimationView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.example.hp.demo.R;
import com.example.hp.demo.adapter.MainTabAdapter;
import com.example.hp.demo.anfix.AnFixActivity;
import com.example.hp.demo.bean.MainTabBean;
import com.example.hp.demo.databinding.ActivityMainBinding;
import com.example.hp.demo.fragment.MainItemInfoFragment;
import com.example.hp.demo.fragment.dialogfragment;
import com.example.hp.demo.utils.LogUtils;
import com.example.hp.demo.utils.PreferenceUtil;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionNo;
import com.yanzhenjie.permission.PermissionYes;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {


    private static final int SERVICEINTENT = 0;
    private ViewFlipper mViewflipper;


    private Button mBtn_databbinding;
    private Button mBtn_mvp;
    private Button mBtn_dialog;
    private Button mGson;
    private Button mBtn_custom;
    private Button mBtn_avLoadingIndicatorView;
    private Button mBtn_rebound;
    private Button mGridlayout;
    private Class clazz;
    long time = 0;

    Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (System.currentTimeMillis() - time >= 3000) {
                time = System.currentTimeMillis();

                mHandler.removeMessages(0);

                mHandler.sendEmptyMessageDelayed(0, 3000);
                Log.e("MainActivity", "当前时间：" + System.currentTimeMillis());
            } else {

                mHandler.removeCallbacksAndMessages(null);
            }
            return false;

        }
    });
    String[] datas = new String[]{"car.json", "lego_loader.json", "loading.json", "4_bar_loop.json",
            "chinese.json", "spinner.json", "circle.json", "basic_thick_circle_loader.json"
            , "material_wave_loading.json", "trail_loading.json"
            , "StickAndBall.json", "stripe loadingnew.json"};
    int num = 0;
    private boolean isNight;
    private ActivityMainBinding binding;
    private RecyclerView recyclerview;
    private MainTabAdapter mainTabAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //安卓5.0之后的分解转场动画
        //        getWindow().setEnterTransition(new Explode().setDuration(500));
        //        getWindow().setExitTransition(new Explode().setDuration(500));
        //        getWindow().setEnterTransition(new Slide().setDuration(800));
        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);
        //        initLottieView();

        initView();
        getPermission();
        switchNightMode();

        //获取系统给应用分配的可用内存
        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        int memorySize = activityManager.getMemoryClass();
        LogUtils.e("获取系统给应用分配的可用内存--" + memorySize);

        //        1522912274
        String formattedDateSimple = getFormattedDateSimple((long) 1522912274);
        Log.e("tag", formattedDateSimple);
        LogUtils.e("onCreate--------");

        //        Date dat = new Date(1522912274000l);
        Date dat = new Date(System.currentTimeMillis());
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(dat);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String sb = format.format(gc.getTime());
        Log.e("tag", "当前时间--" + sb);
    }

    public static String getFormattedDateSimple(Long dateTime) {
        SimpleDateFormat newFormat = new SimpleDateFormat("yyyyMMddhhmmss");
        return newFormat.format(new Date(dateTime));
    }

    /**
     * 日夜间模式切换
     */
    private void switchNightMode() {
        isNight = PreferenceUtil.getInstance(getApplicationContext()).getDayOrNight();
        if (isNight) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        recreate();
        LogUtils.e("onNewIntent");
    }

    private void initView() {

        Intent[] intents = new Intent[]{new Intent(MainActivity.this, DatabindingActivity.class), new Intent(MainActivity.this, MVPActivity.class),
                new Intent(MainActivity.this, DialogActivity.class), new Intent(MainActivity.this, GsonActivity.class),
                new Intent(MainActivity.this, CustomActivity.class), new Intent(MainActivity.this, AVLoadingIndicatorViewActivity.class),
                new Intent(MainActivity.this, GridLayoutActivity.class), new Intent(MainActivity.this, WebActivity.class),
                new Intent(MainActivity.this, DatePickerDialogActivity.class), new Intent(MainActivity.this, CheckBoxsActivity.class),
                new Intent(MainActivity.this, MainMapActivity.class), new Intent(MainActivity.this, ExpandViewActivity.class),
                new Intent(MainActivity.this, QQChatActivity.class), new Intent(MainActivity.this, AfinalActivity.class),
                new Intent(MainActivity.this, SignActivity.class), new Intent(MainActivity.this, CircleMenuActivity.class),
                new Intent(MainActivity.this, PageListViewActivity.class), new Intent(MainActivity.this, BottomBarActivity.class),
                new Intent(MainActivity.this, PaintViewActivity.class), new Intent(MainActivity.this, GifImageviewActivity.class),
                new Intent(MainActivity.this, GifImageviewActivity.class), new Intent(MainActivity.this, MDActivity.class),
                new Intent(MainActivity.this, MulitList2Activity.class), new Intent(MainActivity.this, CatchDataActivity.class),
                new Intent(MainActivity.this, SevenTabActivity.class), new Intent(MainActivity.this, MDactivity2.class),
                new Intent(MainActivity.this, AnFixActivity.class), new Intent(MainActivity.this, DatabaseActivity.class),
                new Intent(MainActivity.this, BaseRecyclerviewActivity.class), new Intent(MainActivity.this, DragRecycleViewActivity.class),
                new Intent(MainActivity.this,FaceDetectorActivity.class)
        };


        recyclerview = (RecyclerView) findViewById(R.id.rv);
        recyclerview.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mainTabAdapter = new MainTabAdapter(R.layout.maintabitem, getApplicationContext());
        recyclerview.setAdapter(mainTabAdapter);

        //只能用这种方式获取strings中的id资源
        TypedArray ar = getResources().obtainTypedArray(R.array.maintabitem_res);
        int len = ar.length();
        int[] resIds = new int[len];
        for (int i = 0; i < len; i++) {
            resIds[i] = ar.getResourceId(i, 0);
        }
        ar.recycle();

        final ArrayList<MainTabBean> mainTabBeans = new ArrayList<>();
        for (int i = 0; i < getResources().getStringArray(R.array.maintabitem_name).length; i++) {
            MainTabBean mainTabBean = new MainTabBean();
            mainTabBean.setName(getResources().getStringArray(R.array.maintabitem_name)[i]);
            mainTabBean.setResId(resIds[i]);
            mainTabBean.setIntent(intents[i]);
            mainTabBean.setInfo(getResources().getStringArray(R.array.maintabitem_info)[i]);
            mainTabBeans.add(mainTabBean);
        }

        mainTabAdapter.setNewData(mainTabBeans);
        recyclerview.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void SimpleOnItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                if (view.getId() == R.id.iv_more) {
                    //                    showPopupWindow(view, i);
                    showItemInfo(mainTabBeans.get(i).getInfo());
                }
            }
        });

        mViewflipper = (ViewFlipper) findViewById(R.id.viewflipper);
        for (int i = 0; i < 5; i++) {
            View view = getLayoutInflater().inflate(R.layout.view_flipper_content, null);
            TextView tv_content = (TextView) view.findViewById(R.id.tv_content);
            tv_content.setText(i + "");
            mViewflipper.addView(view);
        }
        mViewflipper.setFlipInterval(2000);
        mViewflipper.startFlipping();

    }

    private void showItemInfo(String name) {
        MainItemInfoFragment mainItemInfoFragment = new MainItemInfoFragment();
        Bundle bundle = new Bundle();
        bundle.putString("info", name);
        mainItemInfoFragment.setArguments(bundle);
        mainItemInfoFragment.show(getSupportFragmentManager(), "mainiteminfofragment");
    }

    public void showPromptDialog(String text) {
        dialogfragment a = new dialogfragment();

    }

    private void showPopupWindow(View view, int i) {
        View popupview = View.inflate(getApplicationContext(), R.layout.maintabitem_info, null);
        TextView tv_popup = (TextView) popupview.findViewById(R.id.tv_popup);
        tv_popup.setText(mainTabAdapter.getData().get(i).getName());


        int windowPos[] = calculatePopWindowPos(view, popupview);
        int xOff = 20;// 可以自己调整偏移
        windowPos[0] -= xOff;

        PopupWindow popupWindow = new PopupWindow(popupview, ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());

        popupWindow.showAsDropDown(view, 0, 0);
        //        popupWindow.showAtLocation(view, Gravity.TOP | Gravity.START, windowPos[0], windowPos[1]);
        // windowContentViewRoot是根布局View
    }

    /**
     * 计算出来的位置，y方向就在anchorView的上面和下面对齐显示，x方向就是与屏幕右边对齐显示
     * 如果anchorView的位置有变化，就可以适当自己额外加入偏移来修正
     *
     * @param anchorView  呼出window的view
     * @param contentView window的内容布局
     * @return window显示的左上角的xOff, yOff坐标
     */
    private static int[] calculatePopWindowPos(final View anchorView, final View contentView) {
        final int windowPos[] = new int[2];
        final int anchorLoc[] = new int[2];
        // 获取锚点View在屏幕上的左上角坐标位置
        anchorView.getLocationOnScreen(anchorLoc);
        final int anchorHeight = anchorView.getHeight();
        // 获取屏幕的高宽
        final int screenHeight = getScreenHeight(anchorView.getContext());
        final int screenWidth = getScreenWidth(anchorView.getContext());
        contentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        // 计算contentView的高宽
        final int windowHeight = contentView.getMeasuredHeight();
        final int windowWidth = contentView.getMeasuredWidth();
        // 判断需要向上弹出还是向下弹出显示
        final boolean isNeedShowUp = (screenHeight - anchorLoc[1] - anchorHeight < windowHeight);
        if (isNeedShowUp) {
            windowPos[0] = screenWidth - windowWidth;
            windowPos[1] = anchorLoc[1] - windowHeight;
        } else {
            windowPos[0] = screenWidth - windowWidth;
            windowPos[1] = anchorLoc[1] + anchorHeight;
        }
        return windowPos;
    }

    /**
     * 获取屏幕高度(px)
     */
    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * 获取屏幕宽度(px)
     */
    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtils.e("onStart--------");

    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtils.e("onResume--------");

    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtils.e("onPause--------");

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LogUtils.e("onRestart--------");

    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtils.e("onStop--------");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtils.e("onDestroy--------");

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }


    private void initLottieView() {
        setContentView(R.layout.view_kkgc_detail_info);
        final LottieAnimationView animation_view = (LottieAnimationView) findViewById(R.id.animation_view);
        Button car = (Button) findViewById(R.id.btn_car);
        Button next = (Button) findViewById(R.id.btn_next);
        animation_view.setAnimation(datas[0]);
        animation_view.playAnimation();
        animation_view.loop(true);
        car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (num > 0) {
                    num = num - 1;
                    animation_view.cancelAnimation();
                    animation_view.setAnimation(datas[num]);
                    animation_view.loop(true);
                    animation_view.playAnimation();
                    // 自定义速度与时长
                    ValueAnimator animator = ValueAnimator.ofFloat(0f, 1f)
                            .setDuration(5000);

                    animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {
                            animation_view.setProgress(Float.parseFloat(animation.getAnimatedValue().toString()));
                        }
                    });
                    animator.start();
                }
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (num < datas.length - 1) {
                    num = num + 1;
                    animation_view.cancelAnimation();
                    animation_view.setAnimation(datas[num]);
                    animation_view.loop(true);
                    animation_view.playAnimation();
                    // 自定义速度与时长
                    ValueAnimator animator = ValueAnimator.ofFloat(0f, 1f)
                            .setDuration(5000);

                    animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {

                            animation_view.setProgress(Float.parseFloat(animation.getAnimatedValue().toString()));
                        }
                    });

                    animator.start();

                }
            }
        });

    }

    private void getPermission() {
        AndPermission.with(MainActivity.this).permission(Manifest.permission.ACCESS_FINE_LOCATION).start();
    }

    @PermissionYes(101)
    public void getpersissionSucess() {
        Toast.makeText(getApplicationContext(), "权限申请成功", Toast.LENGTH_SHORT).show();
    }

    @PermissionNo(101)
    public void getpersissionFail() {
        Toast.makeText(getApplicationContext(), "权限申请失败", Toast.LENGTH_SHORT).show();

    }

}
