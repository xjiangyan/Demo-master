package com.example.hp.demo.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.storage.StorageManager;
import android.util.Log;

import com.example.hp.demo.constant.Static;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ExternalStorageUtil {
    private static ExternalStorageUtil mInstance = null;

    public static ExternalStorageUtil getInstance() {
        if (null == mInstance) {
            mInstance = new ExternalStorageUtil();
        }
        return mInstance;
    }

    private ExternalStorageUtil() {
        initDir();
    }

    /**
     * 创建存储文件夹
     *
     * @return
     */
    private void initDir() {
        // 判断SD卡是否存在，如果不存在，返回
        if (!FileUtil.isSdCardExist()) {
            return;
        }

        // 如果图片文件夹不存在，创建之
        if (!FileUtil.checkFolder(Static.SDCARD_APP_ROOT
                + File.separator +
                Static.HOME_CACHE_IMAGE)) {
            return;
        }
        // 如果附件文件夹不存在，创建之
        if (!FileUtil.checkFolder(Static.SDCARD_APP_ROOT
                + File.separator +
                Static.HOME_CACHE_ATTACH)) {
            return;
        }
        // 如果证书文件夹不存在，创建之
        if (!FileUtil.checkFolder(Static.SDCARD_APP_ROOT
                + File.separator +
                Static.HOME_LIC)) {
            return;
        }
    }

    /**
     * 创建随即的文件存储名，规则： 当前时间_4位随机数
     *
     * @return
     */
    public String genSavePath() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(Static.SDCARD_APP_ROOT + File.separator + Static.HOME_CACHE_IMAGE + File.separator);
        long curTime = SystemUtil.getCurTime();
        stringBuffer.append(curTime);
        stringBuffer.append('_');
        int randomNum = CommonUtil.getRandom();
        stringBuffer.append(randomNum);
        stringBuffer.append(".png");
        return stringBuffer.toString();
    }

    /**
     * 创建随即的文件存储名，规则： 取url的hashcode的绝对值
     *
     * @return
     */
    public String genSavePath(String url) {
        if (null == url) {
            return genSavePath();
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(Static.SDCARD_APP_ROOT + File.separator + Static.HOME_CACHE_IMAGE + File.separator);
        int hashcode = url.hashCode();
        stringBuffer.append(Math.abs(hashcode));
        stringBuffer.append(".png");
        return stringBuffer.toString();
    }

    public static String[] getVolumePaths(Activity activity){
        String[] paths = null;
        try {
            StorageManager sm = (StorageManager) activity.getSystemService("storage");
            Method method = sm.getClass().getMethod("getVolumePaths");
            paths = (String[]) method.invoke(sm);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return paths;

    }

    /**
     * 将图片资源转存到SD卡上，返回图片路径
     */
    public String storeResToSDCard(Context context, int resId, int width, int height) {
        if (null == context || resId <= 0) {
            return null;
        }

        // 将资源转成drawable
        BitmapDrawable drawable = (BitmapDrawable) context.getResources()
                .getDrawable(resId);
        if (null == drawable) {
            return null;
        }

        // drawable转bitmap
        Bitmap bitmap = drawable.getBitmap();
        if (null == bitmap) {
            return null;
        }

        Bitmap destImg = null;
        if (width > 0 && height > 0) {
            destImg = Bitmap.createScaledBitmap(bitmap, width, height, true);
            if (null == destImg) {
                return null;
            }

            // 释放bitmap，回收内存
            bitmap.recycle();
        } else {
            destImg = Bitmap.createBitmap(bitmap);
        }

        // 将bitmap转成byte[]
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        destImg.compress(Bitmap.CompressFormat.PNG, 100, baos);

        byte[] buf = baos.toByteArray();
        if (null == buf) {
            try {
                baos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            // 释放bitmap,回收内存
            destImg.recycle();

            return null;
        }

        // 创建图片保存路径，默认保存在SD卡中
        String imgPath = genSavePath();
        if (null == imgPath) {
            try {
                baos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            // 释放bitmap,回收内存
            destImg.recycle();

            return null;
        }

        // 储存图片至指定路径
        if (!writeImageExternal(buf, imgPath)) {
            try {
                baos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            // 释放bitmap,回收内存
            destImg.recycle();

            return null;
        }

        try {
            baos.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // 释放bitmap,回收内存
        destImg.recycle();

        return imgPath;
    }

    /**
     * 储存图片至SD卡指定路径
     * <p/>
     * 注： 往sdcard中写入数据的权限 android.permission.WRITE_EXTERNAL_STORAGE
     * 在sdcard中创建/删除文件的权限 android.permission.MOUNT_UNMOUNT_FILESYSTEMS
     */
    @SuppressWarnings("unused")
    public boolean writeImageExternal(byte[] data, String path) {
        if (null == data || data.length <= 0 || null == path
                || path.length() <= 0) {
            return false;
        }

        File savefile = new File(path);
        if (null == savefile) {
            return false;
        }

        boolean ret = true;

        // 保存图片
        RandomAccessFile accessFile = null;
        try {
            File imgFile = new File(path);
            if (null == imgFile) {
                return false;
            }

            accessFile = new RandomAccessFile(imgFile, "rw");
            if (null == accessFile) {
                return false;
            }
            accessFile.write(data);
        } catch (Exception e) {
            e.printStackTrace();

            // 设置返回值为false
            ret = false;
        } finally {
            try {
                // 关闭accessfile
                if (null != accessFile) {
                    accessFile.close();
                }
            } catch (IOException e) {
                Log.e("Cardboo Project",
                        "ExternalStorageUtil.java, writeImageExternal(),close fail");
                e.printStackTrace();

                ret = false;
            }
        }

        return ret;
    }

    //清空图片文件夹
    public void cleanImgDir() {
        FileUtil.cleanImgDir(Static.SDCARD_APP_ROOT + File.separator + Static.HOME_CACHE_IMAGE + File.separator);
    }
}
