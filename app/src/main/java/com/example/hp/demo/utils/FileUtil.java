
package com.example.hp.demo.utils;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author lkw
 */
public class FileUtil {
    private static final String FILE_PREFIX = "ZL_";

    public static String getFileEnd(String fileName) {
        if (fileName == null) {
            return null;
        }
        if (!fileName.contains(".")) {
            return fileName;
        }
        int dotIndex = fileName.lastIndexOf(".");
        return fileName.substring(dotIndex, fileName.length()).toLowerCase();
    }

    /**
     * 删除SD卡上指定路径的文件
     */
    @SuppressWarnings("unused")
    public static void deleteFileExternal(String path) {
        if (null == path || path.length() <= 0) {
            return;
        }

        //判断SD卡是否存在，如果不存在，返回s
        if (!isSdCardExist()) {
            return;
        }

        File file = new File(path);
        if (null == file) {
            return;
        }

        if (!file.isFile()) {
            return;
        }

        file.delete();
    }

    /**
     * 递归删除目录下的所有文件及子目录下所有文件
     *
     * @param dir 将要删除的文件目录
     */
    public static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            //递归删除目录中的子目录下
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }

    /**
     * 判断SD卡上此文件是否存在
     */
    @SuppressWarnings("unused")
    public static boolean isFileExist(String path) {
        if (null == path || path.length() <= 0) {
            return false;
        }


        File file = new File(path);
        if (null == file) {
            return false;
        }

        try {
            return file.exists();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 判断SD卡是否存在
     */
    public static boolean isSdCardExist() {
        String status = Environment.getExternalStorageState();
        if (null == status || status.length() <= 0) {
            return false;
        }

        if (status.equals(Environment.MEDIA_MOUNTED)) {
            return true;
        }

        return false;
    }

    /**
     * 检验文件夹是否存在，如果不存在则新建文件夹，并返回是否新建成功
     *
     * @param dir String
     * @return 检验值 boolean
     */
    public static boolean checkFolder(String dir) {
        File loveCard = new File(dir);
        boolean bLoveCardExist = loveCard.exists();
        if (!bLoveCardExist) {
            if (!loveCard.mkdirs()) {
                return false;
            }
        }
        return true;
    }

    /**
     * 清理图片文件夹
     */
    @SuppressWarnings("unused")
    public static void cleanImgDir(String dirPath) {
        //判断SD卡是否存在，如果不存在，返回s
        if (!FileUtil.isSdCardExist()) {
            return;
        }

        File file = new File(dirPath);
        if (null == file) {
            return;
        }

        if (!file.exists()) {
            return;
        }

        if (!file.isDirectory()) {
            return;
        }

        String[] fileList = file.list();
        if (null == fileList) {
            return;
        }

        for (String fileName : fileList) {
            String filePath = dirPath + fileName;
            if (null != filePath && filePath.length() > 0) {
                deleteFileExternal(filePath);
            }
        }
    }

    /**
     * 创建文件路径
     */
    @SuppressWarnings("unused")
    public static String createImageFilePath(String dirPath, String suffix) {
        if (null == suffix || null == dirPath) {
            return null;
        }

        StringBuffer path = new StringBuffer();
        if (null == path) {
            return null;
        }

        //目录
        path.append(dirPath);

        //前缀
        path.append(FILE_PREFIX);

        //获取当前时间
        long time = System.currentTimeMillis();
        path.append(time);
        //下划线
        path.append("_");

        //取四位随即数
        int randomNum = getRandom();
        path.append(randomNum);

        //点
        if (!suffix.startsWith(".")) {
            path.append(".");
        }
        //后缀
        path.append(suffix);
        //返回路径
        return path.toString();
    }

    public static int getRandom() {
        return (int) (Math.random() * 9000 + 1000);
    }

    /**
     * 从其它路径把文件移动程序图片目录下
     *
     * @param oldPath sdcard上的其它目录
     */
    @SuppressWarnings("unused")
    public static boolean moveFile(String oldPath, String newPath) {
        if (null == oldPath || oldPath.length() <= 0 || null == newPath || newPath.length() <= 0) {
            return false;
        }

        //用路径初始化文件
        File file_Old = new File(oldPath);
        if (null == file_Old) {
            return false;
        }

        //如果文件不存在走近返回null
        if (!file_Old.exists()) {
            return false;
        }

        //初始化一个空的新文件，用于移动老文件
        File file_New = new File(newPath);
        if (null == file_New) {
            return false;
        }

        //把老文件移动新文件
        file_Old.renameTo(file_New);

        //判断新文件是否存在，不存在就返回null
        if (!file_New.exists()) {
            return false;
        }

        //返回新文件地址
        return true;
    }

    /**
     * 复制文件
     *
     * @param fromFile
     * @param toFile
     * @return
     */
    public static boolean copyFile(File fromFile, File toFile) {
        if (!fromFile.exists()) {
            return false;
        }

        if (!fromFile.isFile()) {
            return false;
        }

        if (!fromFile.canRead()) {
            return false;
        }

        if (!toFile.getParentFile().exists()) {
            toFile.getParentFile().mkdirs();
        }

        if (toFile.exists()) {
            return false;
        }

        try {
            FileInputStream fosfrom = new FileInputStream(fromFile);

            FileOutputStream fosto = new FileOutputStream(toFile);

            byte bt[] = new byte[1024];

            int c;

            while ((c = fosfrom.read(bt)) > 0) {
                fosto.write(bt, 0, c); // 将内容写到新文件当中
            }

            fosfrom.close();

            fosto.close();

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

        return true;
    }

    public static void copyFile(String filename, File destination, Context context) {
        if (!destination.getParentFile().exists()) {
            destination.getParentFile().mkdirs();
        }
        AssetManager am = context.getAssets();
        FileOutputStream fos = null;
        try {
            InputStream is = am.open(filename);
            fos = new FileOutputStream(destination);
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = is.read(buffer)) != -1) {
                // len = is.read(buffer);
                fos.write(buffer, 0, len);
            }
            is.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
    }

    private static final String[][] MIME_MapTable = {
            //{后缀名，MIME类型}
            {".3gp", "video/3gpp"},
            {".apk", "application/vnd.android.package-archive"},
            {".asf", "video/x-ms-asf"},
            {".avi", "video/x-msvideo"},
            {".bin", "application/octet-stream"},
            {".bmp", "image/bmp"},
            {".c", "text/plain"},
            {".class", "application/octet-stream"},
            {".conf", "text/plain"},
            {".cpp", "text/plain"},
            {".doc", "application/msword"},
            {".docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document"},
            {".xls", "application/vnd.ms-excel"},
            {".xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"},
            {".exe", "application/octet-stream"},
            {".gif", "image/gif"},
            {".gtar", "application/x-gtar"},
            {".gz", "application/x-gzip"},
            {".h", "text/plain"},
            {".htm", "text/html"},
            {".html", "text/html"},
            {".jar", "application/java-archive"},
            {".java", "text/plain"},
            {".jpeg", "image/jpeg"},
            {".jpg", "image/jpeg"},
            {".js", "application/x-javascript"},
            {".log", "text/plain"},
            {".m3u", "audio/x-mpegurl"},
            {".m4a", "audio/mp4a-latm"},
            {".m4b", "audio/mp4a-latm"},
            {".m4p", "audio/mp4a-latm"},
            {".m4u", "video/vnd.mpegurl"},
            {".m4v", "video/x-m4v"},
            {".mov", "video/quicktime"},
            {".mp2", "audio/x-mpeg"},
            {".mp3", "audio/x-mpeg"},
            {".mp4", "video/mp4"},
            {".mpc", "application/vnd.mpohun.certificate"},
            {".mpe", "video/mpeg"},
            {".mpeg", "video/mpeg"},
            {".mpg", "video/mpeg"},
            {".mpg4", "video/mp4"},
            {".mpga", "audio/mpeg"},
            {".msg", "application/vnd.ms-outlook"},
            {".ogg", "audio/ogg"},
            {".pdf", "application/pdf"},
            {".png", "image/png"},
            {".pps", "application/vnd.ms-powerpoint"},
            {".ppt", "application/vnd.ms-powerpoint"},
            {".pptx", "application/vnd.openxmlformats-officedocument.presentationml.presentation"},
            {".prop", "text/plain"},
            {".rc", "text/plain"},
            {".rmvb", "audio/x-pn-realaudio"},
            {".rtf", "application/rtf"},
            {".sh", "text/plain"},
            {".tar", "application/x-tar"},
            {".tif", "image/tif"},
            {".tiff", "image/tiff"},
            {".tgz", "application/x-compressed"},
            {".txt", "text/plain"},
            {".wav", "audio/x-wav"},
            {".wma", "audio/x-ms-wma"},
            {".wmv", "audio/x-ms-wmv"},
            {".wps", "application/vnd.ms-works"},
            {".xml", "text/plain"},
            {".z", "application/x-compress"},
            {".zip", "application/x-zip-compressed"},
            {"", "*/*"}
    };


    /**
     * 根据文件后缀名获得对应的MIME类型。
     *
     * @param fName
     */
    public static String getMIMEType(String fName) {
        String type = "*/*";
        // 获取后缀名前的分隔符"."在fName中的位置。
        int dotIndex = fName.lastIndexOf(".");
        if (dotIndex < 0) {
            return type;
        }
        /* 获取文件的后缀名 */
        String end = fName.substring(dotIndex, fName.length()).toLowerCase();
        if (end == "")
            return type;
        // 在MIME和文件类型的匹配表中找到对应的MIME类型。
        for (int i = 0; i < MIME_MapTable.length; i++) {
            if (end.equals(MIME_MapTable[i][0]))
                type = MIME_MapTable[i][1];
        }
        return type;
    }

    public static void openNormalFile(String localPath, Context context) {
        // 打开一般文件
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.fromFile(new File(localPath));
        String type = FileUtil.getMIMEType(localPath);
        intent.setDataAndType(uri, type);
        try {
            context.startActivity(intent);
        } catch (Exception e) {
            int dotIndex = localPath.lastIndexOf(".");
            String end = "";
            if (dotIndex < 0) {
                Toast.makeText(context, "请先安装相关软件！", Toast.LENGTH_SHORT).show();
            }else {
                end = localPath.substring(dotIndex+1, localPath.length()).toLowerCase();
                Toast.makeText(context, "请先安装打开\"."+end+"\"后缀的软件！", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
