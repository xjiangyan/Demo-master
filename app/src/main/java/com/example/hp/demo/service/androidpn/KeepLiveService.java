package com.example.hp.demo.service.androidpn;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

import com.example.hp.demo.R;
import com.example.hp.demo.constant.Static;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author Admin
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class KeepLiveService extends Service {

    public static final int NOTIFICATION_ID=0x11;
    long time = 0;
    StringBuffer sBuffer = new StringBuffer();

    Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (System.currentTimeMillis() - time >= 30000) {
                time = System.currentTimeMillis();
                mHandler.removeMessages(0);
                mHandler.sendEmptyMessageDelayed(0, 30000);
                saveToSdcard(true);
                Log.e("IbindService", "当前时间：" + paserTime(System.currentTimeMillis()));
            } else {

                mHandler.removeCallbacksAndMessages(null);
            }
            return false;

        }
    });
    public KeepLiveService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //API 18以下，直接发送Notification并将其置为前台
        if (Build.VERSION.SDK_INT <Build.VERSION_CODES.JELLY_BEAN_MR2) {
            startForeground(NOTIFICATION_ID, new Notification());
        } else {
            //API 18以上，发送Notification并将其置为前台后，启动InnerService
            Notification.Builder builder = new Notification.Builder(this);
//            builder.build().flags = Notification.FLAG_NO_CLEAR|Notification.FLAG_ONGOING_EVENT;
            builder.setSmallIcon(R.mipmap.ic_launcher);
            startForeground(NOTIFICATION_ID, builder.build());
                Log.e("KeepLiveService", "开启了");

            startService(new Intent(this, InnerService.class));
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mHandler.sendEmptyMessage(0);
                    Log.e("KeepLiveService", "启动了--oncreate");
                }
            }, 3000);
        }

    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    /**
     * 将毫秒数转换成yyyy-MM-dd-HH-mm-ss的格式
     *
     * @param milliseconds
     * @return
     */
    private String paserTime(long milliseconds) {
        System.setProperty("user.timezone", "Asia/Shanghai");
        TimeZone tz = TimeZone.getTimeZone("Asia/Shanghai");
        TimeZone.setDefault(tz);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        String times = format.format(new Date(milliseconds));

        return times;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        saveToSdcard(false);
    }
    /**
     * 保存异常信息到sdcard中
     */
    private void saveToSdcard(boolean b) {
        String fileName = null;
        // 添加异常信息
        if (b) {

            sBuffer.append("\n" + paserTime(System.currentTimeMillis()));
        } else {
            sBuffer.append("\n" + paserTime(System.currentTimeMillis()) + "被销毁了");

        }
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            File file1 = new File(Static.SDCARD_APP_ROOT
                    + File.separator +
                    Static.HOME_CACHE_LOG);
            if (!file1.exists()) {
                file1.mkdir();
            }
            fileName = file1.toString() + File.separator + "rizhi.txt";
            File file2 = new File(fileName);
            FileOutputStream fos;
            try {
                fos = new FileOutputStream(file2);
                fos.write(sBuffer.toString().getBytes());
                fos.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}