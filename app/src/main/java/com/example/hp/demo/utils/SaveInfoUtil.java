package com.zl.app.util;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import android.os.Environment;

public class SaveInfoUtil {
	private StringBuffer sBuffer;
	String fileName = null;
	private File file2 = null;
	public static final String APP_SHORT_NAME = "demo";
	public static final String ZL_SD_HOME = "hhapp";

	public static final String SDCARD_APP_ROOT = Environment
			.getExternalStoragePublicDirectory(ZL_SD_HOME + File.separator + APP_SHORT_NAME).getAbsolutePath();
	public static final String HOME_CACHE_LOG = "cache" + File.separator + "log2";

	private static SaveInfoUtil saveInfoUtil = null;

	private void SaveInfoUtil() {

	}

	public static SaveInfoUtil getInstance() {
		if (saveInfoUtil == null) {
			saveInfoUtil = new SaveInfoUtil();
		}
		return saveInfoUtil;
	}

	public void initcache() {
		sBuffer = new StringBuffer();
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			File file1 = new File(SDCARD_APP_ROOT + File.separator + HOME_CACHE_LOG);
			if (!file1.exists()) {
				file1.mkdirs();
			}
			fileName = file1.toString() + File.separator + paserTime(System.currentTimeMillis()) + ".txt";
			file2 = new File(fileName);
		}
	}

	/**
	 * 保存异常信息到sdcard中
	 */
	public void saveToSdcard(String state) {
		// 添加异常信息
		sBuffer.append("\n" + state + "------" + paserTime(System.currentTimeMillis()));
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(file2);
			fos.write(sBuffer.toString().getBytes());
			fos.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
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
}
