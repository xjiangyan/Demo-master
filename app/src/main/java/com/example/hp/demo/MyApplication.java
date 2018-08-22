package com.example.hp.demo;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.StrictMode;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.util.Log;

import com.alipay.euler.andfix.patch.PatchManager;
import com.bilibili.magicasakura.utils.ThemeUtils;
import com.example.hp.demo.constant.Static;
import com.example.hp.demo.helper.ActivityLifecycleHelper;
import com.example.hp.demo.utils.ActivityManager;
import com.example.hp.demo.utils.ExternalStorageUtil;
import com.example.hp.demo.utils.PreferenceUtil;
import com.example.hp.demo.utils.SystemUtils;
import com.example.hp.demo.utils.ThemeHelper;
import com.example.hp.demo.utils.Utils;

import org.litepal.LitePal;

import java.io.IOException;

/**
 * @author Admin
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class MyApplication extends Application implements ThemeUtils.switchColor {
    public static MyApplication application;
    private static Handler handler;

    //以下属性应用于整个应用程序，合理利用资源，减少资源浪费
    private static Context mContext;//上下文
    private static Thread mMainThread;//主线程
    private static long mMainThreadId;//主线程id
    private static Looper mMainLooper;//循环队列

    private static final String TAG = " andrew";

    private static final String APATCH_PATH = "/out.apatch";

    private static final String DIR = "apatch";//补丁文件夹
    /**
     * patch manager
     */
    private PatchManager mPatchManager;

    public static MyApplication getInstance() {
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("MyApplication", "onCreate");

        mContext = getApplicationContext();
        application = this;
        Utils.init(this);//初始化所有Utils的context
        SystemUtils.init(this);//初始化线程
        ExternalStorageUtil.getInstance();//获取存储
        handler = new Handler(getMainLooper());//初始化handler


        //对全局属性赋值
        mContext = getApplicationContext();
        mMainThread = Thread.currentThread();
        mMainThreadId = android.os.Process.myTid();


        // 初始化Session相关
        PreferenceUtil.getInstance(this).setSESSION_INIT_DONE(false);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();

        //初始化主题换肤
        ThemeUtils.setSwitchColor(this);

        //腾讯的bugly崩溃日志
        //        CrashReport.initCrashReport(getApplicationContext(), "b6c6320311", true);
        registerActivityLifecycleCallbacks(ActivityLifecycleHelper.build());


        initAndFix();

        // 初始化LitePal数据库
        LitePal.initialize(this);
    }

    /**
     * 7.0以上不支持？？？
     */
    private void initAndFix() {
        mPatchManager = new PatchManager(this);
        mPatchManager.init("1.0");
        Log.d(TAG, "inited.");

        // load patch
        mPatchManager.loadPatch();
        try {
            // .apatch file path

            String patchFileString = Environment.getExternalStorageDirectory().getAbsolutePath() + APATCH_PATH;
            mPatchManager.addPatch(patchFileString);
            Log.d(TAG, "apatch:" + patchFileString + " added.");

            //复制且加载补丁成功后，删除下载的补丁
            //            File f = new File(this.getFilesDir(), DIR + APATCH_PATH);
            //            if (f.exists()) {
            //                boolean result = new File(patchFileString).delete();
            //                if (!result)
            //                    Log.e(TAG, patchFileString + " delete fail");
            //            }
        } catch (IOException e) {
            Static.toastShort(this, e + "");
            Log.e(TAG, "", e);
        }
    }

    public void exit(final boolean show) {
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                ActivityManager.cleanActivities();
                System.exit(0);
            }
        };
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (show)
                    Static.toastShort(getApplicationContext(), "登录过期，即将退出");
                handler.postDelayed(runnable, 1000);
            }
        });
    }

    /**
     * 重启当前应用
     */
    public static void restart() {
        Intent intent = mContext.getPackageManager().getLaunchIntentForPackage(mContext.getPackageName());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        mContext.startActivity(intent);
    }


    @Override
    public void onTerminate() {
        super.onTerminate();
        Log.e("MyApplication", "onTerminate");
    }

    public int dealWithFail(Context context, int errorNo, String msg) {
        if (msg != null && msg.contains("failed to respond")) {
            Static.toastShort(context, "服务未响应！");
            return 2;
        }

        if (errorNo == 404) {
            Static.toastShort(context, "服务未找到！");
            return 1;
        } else if (errorNo == 500) {
            Static.toastShort(context, "服务异常！");
            return 3;
        } else {
            Static.toastShort(context, "网络异常！");
            return 2;
        }
    }

    /*-------------------------------------------------------主题换肤-----------------------------------------------------*/
    @Override
    public int replaceColorById(Context context, @ColorRes int colorId) {
        if (ThemeHelper.isDefaultTheme(context)) {
            return context.getResources().getColor(colorId);
        }
        String theme = getTheme(context);
        if (theme != null) {
            colorId = getThemeColorId(context, colorId, theme);
        }
        return context.getResources().getColor(colorId);
    }

    @Override
    public int replaceColor(Context context, @ColorInt int originColor) {
        if (ThemeHelper.isDefaultTheme(context)) {
            return originColor;
        }
        String theme = getTheme(context);
        int colorId = -1;

        if (theme != null) {
            colorId = getThemeColor(context, originColor, theme);
        }
        return colorId != -1 ? getResources().getColor(colorId) : originColor;
    }

    private String getTheme(Context context) {
        if (ThemeHelper.getTheme(context) == ThemeHelper.CARD_STORM) {
            return "blue";
        } else if (ThemeHelper.getTheme(context) == ThemeHelper.CARD_HOPE) {
            return "purple";
        } else if (ThemeHelper.getTheme(context) == ThemeHelper.CARD_WOOD) {
            return "green";
        } else if (ThemeHelper.getTheme(context) == ThemeHelper.CARD_LIGHT) {
            return "green_light";
        } else if (ThemeHelper.getTheme(context) == ThemeHelper.CARD_THUNDER) {
            return "yellow";
        } else if (ThemeHelper.getTheme(context) == ThemeHelper.CARD_SAND) {
            return "orange";
        } else if (ThemeHelper.getTheme(context) == ThemeHelper.CARD_FIREY) {
            return "red";
        }
        return null;
    }

    private
    @ColorRes
    int getThemeColorId(Context context, int colorId, String theme) {
        switch (colorId) {
            case R.color.theme_color_primary:
                return context.getResources().getIdentifier(theme, "color", getPackageName());
            case R.color.theme_color_primary_dark:
                return context.getResources().getIdentifier(theme + "_dark", "color", getPackageName());
            case R.color.theme_color_primary_trans:
                return context.getResources().getIdentifier(theme + "_trans", "color", getPackageName());
        }
        return colorId;
    }

    private
    @ColorRes
    int getThemeColor(Context context, int color, String theme) {
        switch (color) {
            case 0xfffb7299:
                return context.getResources().getIdentifier(theme, "color", getPackageName());
            case 0xffb85671:
                return context.getResources().getIdentifier(theme + "_dark", "color", getPackageName());
            case 0x99f0486c:
                return context.getResources().getIdentifier(theme + "_trans", "color", getPackageName());
        }
        return -1;
    }


    /*-------------------------------------------------------其他-----------------------------------------------------*/


    public static Context getContext() {
        return mContext;
    }

    public static void setContext(Context mContext) {
        MyApplication.mContext = mContext;
    }

    public static Thread getMainThread() {
        return mMainThread;
    }

    public static void setMainThread(Thread mMainThread) {
        MyApplication.mMainThread = mMainThread;
    }

    public static long getMainThreadId() {
        return mMainThreadId;
    }

    public static void setMainThreadId(long mMainThreadId) {
        MyApplication.mMainThreadId = mMainThreadId;
    }

    public static Looper getMainThreadLooper() {
        return mMainLooper;
    }

    public static void setMainThreadLooper(Looper mMainLooper) {
        MyApplication.mMainLooper = mMainLooper;
    }

    public static Handler getMainHandler() {
        return handler;
    }

    public static void setMainHandler(Handler mHandler) {
        MyApplication.handler = mHandler;
    }

}
