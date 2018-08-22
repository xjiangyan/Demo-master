package com.example.hp.demo.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.telephony.TelephonyManager;
import android.text.ClipboardManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.util.Locale;

/**
 * @author lkw
 *         封装系统相关函数：
 *         1.获取sdk版本号码
 */
public class SystemUtil {
    public static final String JHH_OS_ANDROID = "Android";

    /**
     * 获取当前手机语言设置类别，调用android的local类实现
     */
    public static final String getLanguage() {
        return Locale.getDefault().getLanguage();
    }


    /**
     * 获取当前手机语言设置类别，调用android的local类实现
     * 中文简体与繁体是通过countryCode来区分
     */
    public static final String getCountryCode() {
        return Locale.getDefault().getCountry();
    }

    /**
     * 获取手机品牌
     */
    public static String getBrand() {
        return android.os.Build.BRAND;
    }

    /**
     * 获取手机型号
     */
    public static String getModel() {
        return android.os.Build.MODEL;
    }

    /**
     * 获取操作系统型号
     */
    public static String getOS() {
        return JHH_OS_ANDROID;
    }

    /**
     * 获取操作系统版本
     */
    public static String getOSVersion() {
        return android.os.Build.VERSION.RELEASE;
    }

    /**
     * 获取手机imei号码
     */
    public static String getIMEI(Context context) {
        if (null == context) {
            return null;
        }

        //获取电话服务
        TelephonyManager telMgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (null == telMgr) {
            return null;
        }

        return telMgr.getDeviceId();
    }

    /**
     * 获取当前系统时间，单位：毫秒
     */
    public static long getCurTime() {
        return System.currentTimeMillis();
    }

    /**
     * 获取当前系统的SDK版本
     */
    public static int getSdkLevel() {
        return android.os.Build.VERSION.SDK_INT;
    }

    /**
     * 获取系统软件包版本号
     */
    public static String getApkVersion(Context context) {
        if (null == context) {
            return null;
        }

        PackageManager pkgManager = context.getPackageManager();
        if (null == pkgManager) {
            return null;
        }

        try {
            PackageInfo info = pkgManager.getPackageInfo(context.getPackageName(), PackageManager.GET_ACTIVITIES);
            if (null == info) {
                return null;
            }

            return info.versionName;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取archiveFilePath该文件的版本号
     *
     * @param context
     * @param archiveFilePath 为apk的路径
     * @return 版本号
     */
    public static String getApkVersion(Context context, String archiveFilePath) {
        if (null == context) {
            return null;
        }

        PackageManager pkgManager = context.getPackageManager();
        if (null == pkgManager) {
            return null;
        }

        try {
            PackageInfo info = pkgManager.getPackageArchiveInfo(archiveFilePath, PackageManager.GET_ACTIVITIES);
            if (null == info) {
                return null;
            }

            return info.versionName;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 安装APK
     */
    @SuppressWarnings("unused")
    public static Intent getInstallApkIntent(String path) {
        if (null == path || path.length() <= 0) {
            return null;
        }

        Intent intent = new Intent(Intent.ACTION_VIEW);
        if (null == intent) {
            return null;
        }

        intent.setDataAndType(Uri.parse("file://" + path), "application/vnd.android.package-archive");

        return intent;
    }

    /**
     * 获取粘贴板管理类
     */
    public static ClipboardManager getSystemClipboardManager(Context context) {
        ClipboardManager manager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        if (null == manager) {
            return null;
        }

        return manager;
    }

    public static void closeKeyBoard(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}