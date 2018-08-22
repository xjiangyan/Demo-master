package com.example.hp.demo.activity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dd.CircularProgressButton;
import com.example.hp.demo.R;
import com.example.hp.demo.utils.LogUtils;
import com.example.hp.demo.utils.PreferenceUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;


public class GifImageviewActivity extends AppCompatActivity {
    boolean isNight = false;
    private int[] state = new int[]{-1, 100};
    int i = 0;
    private CircularProgressButton mCs_bt;
    private TextView mDayandnight;
    private String TAG = "observable---------";
    private ImageView imageview;


    //    准备请求码和临时变量名

    private static final int PHOTO_REQUEST_CAREMA = 1;// 拍照
    private static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择
    private static final int PHOTO_REQUEST_CUT = 3;// 裁剪之后
    private static final String PHOTO_FILE_NAME = "temp_photo.jpg";//临时文件名
    private File tempFile;
    private Button mOpengallery;
    private Button mOpencamery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gif_imageview);
        initview();
        initEvent();

        //        final CircleProgressView cir = (CircleProgressView) findViewById(R.id.circle_progress);
        //        //        cir.setVisibility(View.VISIBLE);
        //        cir.spin();
        //        cir.postDelayed(new Runnable() {
        //            @Override
        //            public void run() {
        //                cir.stopSpinning();
        //            }
        //        }, 2000);

    }


    private void initEvent() {

        //        initrxJava();


        mDayandnight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchNightMode();
            }
        });
        mCs_bt.setIndeterminateProgressMode(true);

        mCs_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCs_bt.setClickable(false);
                mCs_bt.setProgress(50);
                mCs_bt.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mCs_bt.setProgress(state[i % 2]);
                        i++;
                        mCs_bt.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                mCs_bt.setProgress(0);
                                mCs_bt.setClickable(true);
                            }
                        }, 1000);
                    }
                }, 2000);
            }
        });

        mOpengallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gallery();
            }
        });

        mOpencamery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                camera();
            }
        });
    }

    /**
     * rxjava使用
     */
    private void initrxJava() {
        //创建一个上游 Observable：
        /**
         * 普通操作
         */
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();//subscribe在接收到oncomplete后不再处理下面的onnext方法
                emitter.onNext(6);
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.e(TAG, "subscribe");
            }

            @Override
            public void onNext(@NonNull Integer integer) {
                Log.e(TAG, "" + integer);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e(TAG, "error");
            }

            @Override
            public void onComplete() {
                Log.e(TAG, "complete");
            }
        });

        List<String> datas = new ArrayList<>();
        for (int i = 0; i < 5; i++) {

            datas.add(i + "");
        }
        /**简化操作
         * fromIteravle订阅Iterable类型的消息并且省略了onNext()方法
         */
        Observable.fromIterable(datas).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull String s) {
                Log.e("GifImageviewActivity", "onnext了---" + s);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
        Observable.fromIterable(datas).subscribe(new Consumer<String>() {
            @Override
            public void accept(@NonNull String s) throws Exception {
                LogUtils.e("打印了----" + s);
            }
        });

        /**
         * 加载图片
         */
        //        int[] imageres = new int[]{R.drawable.p9, R.drawable.p11};
        //        Observable.fromArray(imageres)
        //                .subscribe(new Consumer<int[]>() {
        //                    @Override
        //                    public void accept(@NonNull int[] ints) throws Exception {
        //                        imageview.setImageResource(ints[0]);
        //                    }
        //                });
    }

    private void initview() {
        mDayandnight = (TextView) findViewById(R.id.dayandnight);

        mCs_bt = (CircularProgressButton) findViewById(R.id.cs_bt);
        imageview = (ImageView) findViewById(R.id.imageview);
        mOpengallery = (Button) findViewById(R.id.open_gallery);
        mOpencamery = (Button) findViewById(R.id.open_camery);
    }
    /**
     * <com.dd.CircularProgressButton
     android:layout_width="300dp"
     android:layout_height="40dp"
     app:cpb_iconComplete="@drawable/ic_cpb_action_accept"//成功时的状态图标
     app:cpb_textError="@drawable/ic_cpb_action_cancel"//失败时的状态图标
     app:cpb_textIdle="登录" />//默认文字，注意：text在这里没用了
     四个状态的使用
     btnlogin.setIndeterminateProgressMode(true);
     btnlogin.setProgress(50);//运行状态
     btnlogin.setProgress(100);//成功状态
     btnlogin.setProgress(-1);//失败状态
     btnlogin.setProgress(0);//默认状态
     当处于失败状态是，如果按钮一直处于失败状态，给用户的体验就会不好，
     因此我们要给它一个时间，例如：登录失败时，过两秒它就又变成默认状态
     所以我给他起了一个线程
     handler.postDelayed(new Runnable() {

    @Override public void run() {
    btnlogin.setProgress(0);
    }
    }, 2000);//在失败时。隔两秒钟自动回到默认状态
     */


    /**
     * 日夜间模式切换
     */
    private void switchNightMode() {
        isNight = PreferenceUtil.getInstance(getApplicationContext()).getDayOrNight();
        if (isNight) {
            // 日间模式
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            PreferenceUtil.getInstance(getApplicationContext()).setDayOrNight(false);
        } else {
            // 夜间模式
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            PreferenceUtil.getInstance(getApplicationContext()).setDayOrNight(true);
        }

        recreate();
    }
    //
    //    @Override
    //    public void finish() {
    //        super.finish();
    //        startActivity(new Intent(GifImageviewActivity.this, MainActivity.class));
    //    }


    //    开启图库获取照片

    public void gallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, PHOTO_REQUEST_GALLERY);//携带请求码
    }
    //    开启相机获取照片

    public void camera() {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        if (hasSdcard()) {// 判断存储卡是否可以用,可用进行存储
            tempFile = new File(Environment.getExternalStorageDirectory(), PHOTO_FILE_NAME);
            Uri uri = Uri.fromFile(tempFile); // 从文件中创建uri
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        }
        startActivityForResult(intent, PHOTO_REQUEST_CAREMA);//携带请求码
    }
    //    裁剪图片

    private void crop(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // 裁剪框的比例,1:1
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // 裁剪后输出图片的尺寸大小
        intent.putExtra("outputX", 250);
        intent.putExtra("outputY", 250);
        intent.putExtra("outputFormat", "JPEG");// 图片格式
        intent.putExtra("noFaceDetection", true);// 取消人脸识别
        intent.putExtra("return-data", true);               //返回bitmap不太好,容易内存泄漏卡顿？？
        startActivityForResult(intent, PHOTO_REQUEST_CUT); // 开启一个带有返回值的Activity,请求码为PHOTO_REQUEST_CUT
    }
    //    判断是否挂在了SD卡

    private boolean hasSdcard() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }
    //    获取的是external数据库对应的Image文件

    private Uri external(String external) {
        String myImageUrl = "content://media" + external;
        Uri uri = Uri.parse(myImageUrl);
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor actualimagecursor = this.managedQuery(uri, proj, null, null, null);
        int actual_image_column_index = actualimagecursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        actualimagecursor.moveToFirst();
        String img_path = actualimagecursor.getString(actual_image_column_index);
        File file = new File(img_path);
        Uri fileUri = Uri.fromFile(file);
        return fileUri;
    }
    //    在回调中取图片

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PHOTO_REQUEST_GALLERY) {
            // 从相册返回的数据
            if (data != null) {// 得到图片的全路径
                Uri uri = data.getData();
                crop(uri);
            }
        } else if (requestCode == PHOTO_REQUEST_CAREMA) {//从相机返回的数据
            if (hasSdcard()) {
                crop(Uri.fromFile(tempFile));
            } else {
                Toast.makeText(GifImageviewActivity.this, "未找到存储卡,无法存储照片!", Toast.LENGTH_LONG).show();
            }
        } else if (requestCode == PHOTO_REQUEST_CUT) {//从剪切图片返回的数据
            if (data != null) {
                Bitmap bitmap = data.getParcelableExtra("data");
                //将bitmap转换为Uri
                Uri uri = Uri.parse(MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, null, null));
                //对非正确的Uri处理,这类Uri存在手机的external.db中,可以查询_data字段查出对应文件的uri
                if (uri.getPath().contains("external")) {
                    uri = external(uri.getPath());
                }
                //在这可以拿到裁剪后的图片Uri,然后进行你想要的操作
                //                upLoad2Server(uri);

                imageview.setImageBitmap(bitmap);
            }
            try {
                tempFile.delete();//将临时文件删除
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}
