package com.example.hp.demo.utils;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class PhotoUtil {
	
	public static final String EXTERNAL_IMAGE_STORE_DIR = Environment.getExternalStorageDirectory().getPath()+"/zl/image/";
	
	private static PhotoUtil mInstance;

	public static PhotoUtil getInstance() {
		if (null == mInstance) {
			mInstance = new PhotoUtil();
		}
		return mInstance;
	}

	public PhotoUtil() {
	}

	public Intent getCameraIntent(Uri path) {
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, path);
		return intent;
	}
	
	/**
	 * �����漴���ļ��洢����� ��ǰʱ��_4λ�����
	 * 
	 * @return
	 */
	public String genSavePath() {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(EXTERNAL_IMAGE_STORE_DIR);
		long curTime = System.currentTimeMillis();
		stringBuffer.append(curTime);
		stringBuffer.append('_');
		int randomNum = getRandom();
		stringBuffer.append(randomNum);
		stringBuffer.append(".png");
		return stringBuffer.toString();
	}
	
	/**
	 * ��ȡ4λ�����
	 */
	public static int getRandom(){
		return (int) (Math.random() * 9000 + 1000);
	}
	
	/**
	 * 压缩图片，节约传输数据量
	 */
	public static boolean deflateImage(String imgPath,String outPutPath,int width,int height){
		if(null == imgPath || imgPath.length() <= 0 || !FileUtil.isFileExist(imgPath)){
			return false;
		}
	          
		//解码获取图片的真实宽高
		BitmapFactory.Options options = new BitmapFactory.Options();
		//获取宽，高,不做真实解码
		options.inJustDecodeBounds = true;
	
		//解码，注：此时返回的bitmap为空，在options中有图片的真实宽，高
		Bitmap bitmap = BitmapFactory.decodeFile(imgPath, options);
		int picWidth = options.outWidth;
		int picHeight = options.outHeight;
	
		//计算缩放比例
		int scaleX = 1;
		if(width > 0 && picWidth > 0 && picWidth > width){
			scaleX = picWidth / width;
		}
	
		int scaleY = 1;
		if(height > 0 && picHeight > 0 && picHeight > height){
			scaleY = picHeight / height;
		}
	
		//设置做真实解码
		options.inJustDecodeBounds = false;
		//设置图片文件为原大小的1/N
		options.inSampleSize = scaleX > scaleY ? scaleY : scaleX;
			
		//设置灰度
		options.inPreferredConfig = Bitmap.Config.RGB_565;
	
		//设置，解码不占用系统核心内存，随时可以释放
		options.inInputShareable = true;
		options.inPurgeable = true;
	
		//真实解码
//		Bitmap bitmap = null;
		try {
			bitmap = BitmapFactory.decodeFile(imgPath, options);
		} catch (Exception e) {
			e.printStackTrace();
	   
			return false;
		}
		
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		//转成PNG
		if(!bitmap.compress(Bitmap.CompressFormat.JPEG, 50, stream)){
			return false;
		}
  
		byte[] imageData = stream.toByteArray();
  
		//copy 数据
		byte[] result = imageData.clone();
  
		//关闭stream
		try {
			stream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//储存图片至指定路径
		if(!writeImageExternal(imageData,outPutPath)){
			return false;
		}
	   
		return true;
	}
	
	/**
	 * 储存图片至SD卡指定路径
	 * 
	 * 注：
	 * 往sdcard中写入数据的权限 android.permission.WRITE_EXTERNAL_STORAGE
	 * 在sdcard中创建/删除文件的权限 android.permission.MOUNT_UNMOUNT_FILESYSTEMS
	 */
	public static boolean writeImageExternal(byte[] data,String path){
		if(null == data || data.length <= 0 || null == path || path.length() <= 0){
			return false;
		}
		  
		//判断SD卡是否存在，如果不存在，返回
		if(!FileUtil.isSdCardExist()){
			return false;
		}
		
		boolean ret = true;
		  
		//保存图片
		RandomAccessFile accessFile = null;
		try {
			File imgFile = new File(path);
			accessFile = new RandomAccessFile(imgFile,"rw");
			accessFile.write(data);
		} catch (Exception e) {
			e.printStackTrace();
			//设置返回值为false
			ret = false;
		}
		
		finally{           
			try {
				//关闭accessfile
				if(null != accessFile){
					accessFile.close();
				}
			} catch (IOException e) {             
				e.printStackTrace();
				ret = false;
			}
		}

		return ret;
	}

}
