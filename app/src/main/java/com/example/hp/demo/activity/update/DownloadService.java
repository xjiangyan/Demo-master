package com.example.hp.demo.activity.update;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.widget.RemoteViews;

import com.example.hp.demo.R;
import com.example.hp.demo.activity.MainActivity;
import com.example.hp.demo.utils.LogUtils;

import org.greenrobot.eventbus.EventBus;

import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 下载服务
 */
@SuppressWarnings("all")
public class DownloadService extends Service {
    public static boolean isDownload = false;

    private String mUrl;
    private String mTitle = "正在下载";

    // 默认存放文件下载的路径
    private String saveFileName = Environment
            .getExternalStorageDirectory()
            + File.separator
            + "OSChina"
            + File.separator + "download" + File.separator;

    private NotificationManager mNotificationManager;
    private Notification mNotification;


    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    mNotificationManager.cancel(0);
                    installApk();
                    break;
                case 1:
                    int rate = msg.arg1;
                    if (rate < 100) {
                        RemoteViews views = mNotification.contentView;
                        views.setTextViewText(R.id.tv_download_progress, mTitle + "(" + rate
                                + "%" + ")");
                        //views.setTextColor(R.id.tv_download_progress,getColor(R.color.text_title_color));
                        views.setProgressBar(R.id.pb_progress, 100, rate,
                                false);
                    } else {
                        // 下载完毕后变换通知形式
                        mNotification.flags = Notification.FLAG_AUTO_CANCEL;
                        mNotification.contentView = null;
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                        intent.putExtra("completed", "yes");

                        PendingIntent contentIntent = PendingIntent.getActivity(
                                getApplicationContext(), 0, intent,
                                PendingIntent.FLAG_UPDATE_CURRENT);
                    }
                    mNotificationManager.notify(0, mNotification);

                    EventBus.getDefault().post(rate);
                    break;
            }

        }
    };

    public static void startService(Context context, String downloadUrl) {
        Intent intent = new Intent(context, DownloadService.class);
        intent.putExtra("url", downloadUrl);
        context.startService(intent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        isDownload = true;
        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        LogUtils.e("oncreate了");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtils.e("onStartCommand了");

        mUrl = intent.getStringExtra("url");
        File file = new File(saveFileName);
        if (!file.exists()) {
            file.mkdirs();
        }
        final File apkFile = new File(saveFileName + "osc.apk");
        setUpNotification();
        new Thread() {
            @Override
            public void run() {
                try {
                    downloadUpdateFile(mUrl, apkFile);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
        return super.onStartCommand(intent, flags, startId);
    }

    private long downloadUpdateFile(String downloadUrl, File saveFile) throws Exception {
        int downloadCount = 0;
        int currentSize = 0;
        long totalSize = 0;
        int updateTotalSize = 0;

        HttpURLConnection httpConnection = null;
        InputStream is = null;
        FileOutputStream fos = null;

        try {
            URL url = new URL(downloadUrl);
            httpConnection = (HttpURLConnection) url.openConnection();
            httpConnection.setConnectTimeout(10000);
            httpConnection.setReadTimeout(20000);
            updateTotalSize = httpConnection.getContentLength();
            if (httpConnection.getResponseCode() == 404) {
                throw new Exception("fail!");
            }
            is = httpConnection.getInputStream();
            fos = new FileOutputStream(saveFile, false);
            byte buffer[] = new byte[2048];
            int readSize = 0;
            while ((readSize = is.read(buffer)) > 0) {

                fos.write(buffer, 0, readSize);
                totalSize += readSize;
                if ((downloadCount == 0)
                        || (int) (totalSize * 100 / updateTotalSize) - 4 > downloadCount) {
                    downloadCount += 4;
                    Message msg = mHandler.obtainMessage();
                    msg.what = 1;
                    msg.arg1 = downloadCount;
                    mHandler.sendMessage(msg);
                }
            }

            mHandler.sendEmptyMessage(0);
            isDownload = false;

        } finally {
            if (httpConnection != null) {
                httpConnection.disconnect();
            }
            close(is, fos);

            stopSelf();
        }
        return totalSize;
    }

    /**
     * 关闭流
     *
     * @param closeables Closeable
     */
    @SuppressWarnings("WeakerAccess")
    public static void close(Closeable... closeables) {
        if (closeables == null || closeables.length == 0)
            return;
        for (Closeable closeable : closeables) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void installApk() {
        File file = new File(saveFileName + "osc.apk");
        if (!file.exists())
            return;
        Intent intent = new Intent(Intent.ACTION_VIEW);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(getApplicationContext(), "com.example.hp.demo.provider", file);
            intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        startActivity(intent);
    }

    private void setUpNotification() {
        int icon = R.mipmap.ic_launcher;
        CharSequence tickerText = "开始下载";
        long when = System.currentTimeMillis();
        mNotification = new Notification(icon, tickerText, when);

        mNotification.flags = Notification.FLAG_ONGOING_EVENT;

        RemoteViews contentView = new RemoteViews(getPackageName(),
                R.layout.layout_notification_view);
        contentView.setTextViewText(R.id.tv_download_progress, mTitle);
        mNotification.contentView = contentView;

        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);

        mNotification.contentIntent = contentIntent;
        mNotificationManager.notify(0, mNotification);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        isDownload = false;
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
