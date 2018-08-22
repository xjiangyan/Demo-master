package com.example.hp.demo.activity;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.hp.demo.BaseActivity;
import com.example.hp.demo.MyApplication;
import com.example.hp.demo.R;
import com.example.hp.demo.adapter.MyChatRecycleAdapter;
import com.example.hp.demo.constant.Static;
import com.example.hp.demo.helper.PicassoImageLoader;
import com.example.hp.demo.helper.PicassoPauseOnScrollListener;
import com.example.hp.demo.utils.BitmapUtils;
import com.example.hp.demo.utils.FileUtil;
import com.example.hp.demo.view.LoadAlertDialog;
import com.wyt.searchbox.SearchFragment;
import com.wyt.searchbox.custom.IOnSearchClickListener;

import org.json.JSONException;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.finalteam.galleryfinal.CoreConfig;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.PauseOnScrollListener;
import cn.finalteam.galleryfinal.ThemeConfig;
import cn.finalteam.galleryfinal.model.PhotoInfo;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class QQChatActivity extends BaseActivity {

    @BindView(R.id.chat_edittext)
    EditText mChatEdittext;
    @BindView(R.id.chat_sendbutton)
    Button mChatSendbutton;
    @BindView(R.id.chat_recycleview)
    RecyclerView mChatRecycleview;
    @BindView(R.id.chat_bottom_add)
    ImageView mChatBottomAdd;
    @BindView(R.id.chat_bottom_emoji)
    ImageView mChatBottomEmoji;

    private HashMap<Object, Object> hashMap;
    private ArrayList<HashMap<Object, Object>> arrayList;
    private MyChatRecycleAdapter mMyChatRecycleAdapter;
    private String uuid;
    private String picturePath;
    public static final int REQ_CAMERA = 11;
    private String photoPath;
    private int picWidth, picHeight;

    public static final String PHOTO_PATH = "photo_path";
    public static final String PHOTO_UUID = "photo_uuid";
    public static final String PHOTO_MODIFY_TIME = "photo_modify_time";
    /**
     * 当前图片的时间
     */
    private Date imageDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qqchat);
        ButterKnife.bind(this);
        initViews();
        initData();
    }

    @Override
    public void initView() {

    }

    private void initViews() {
        mChatSendbutton.setEnabled(false);

        mChatEdittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (mChatEdittext.getText().toString() != null && mChatEdittext.getText().toString() != "\b") {
                    Log.e("QQChatActivity", "**************" + mChatEdittext.getText().toString() + "**********");
                    mChatSendbutton.setEnabled(true);
                } else {
                    Log.e("QQChatActivity", "不可用");

                    mChatSendbutton.setEnabled(false);
                }
            }
        });
    }

    @Override
    public void onCreateLoadData() {

    }

    @Override
    public void onResumeLoadData() {

    }


    private void initData() {
        arrayList = new ArrayList<>();

        addToHashMap("me", "hello word!");
        addToHashMap("me", "hahaha");
        addToHashMap("other", "hello word!");
        addToHashMap("me", "hello word!");
        addToHashMap("other", "heihe\niehiehei");
        addToHashMap("me", "hello word!");
        addToHashMap("me", "hello word!");
        addToHashMap("me", "hahaha");
        addToHashMap("other", "hello word!");
        addToHashMap("me", "hello word!");
        addToHashMap("other", "heiheiehiehei");
        addToHashMap("me", "hello word!");
        addToHashMap("me", "hello word!");
        addToHashMap("other", "heiheiehiehei");
        addToHashMap("me", "hello word!");

        mMyChatRecycleAdapter = new MyChatRecycleAdapter(getApplicationContext(), arrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        mChatRecycleview.setLayoutManager(linearLayoutManager);
        mChatRecycleview.setAdapter(mMyChatRecycleAdapter);
        mChatRecycleview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //关闭软键盘
                InputMethodManager imm = (InputMethodManager) getApplicationContext().getSystemService(getApplicationContext().INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(mChatEdittext.getWindowToken(), 0);
                return false;//返回false表示触摸事件没有被消费，还能继续执行该事件
            }
        });
    }

    private void addToHashMap(String person, String text) {
        hashMap = new HashMap<>();
        hashMap.put("person", person);
        hashMap.put("text", text);
        arrayList.add(hashMap);
    }


    private boolean galleryInit;

    private void initGalleryFinal() {
        if (!galleryInit) {
            ThemeConfig theme = new ThemeConfig.Builder()
                    .setTitleBarBgColor(Color.rgb(0x31, 0x7e, 0xf3))
                    .setTitleBarTextColor(Color.WHITE)
                    .setTitleBarIconColor(Color.WHITE)
                    .setFabNornalColor(Color.rgb(0x31, 0x7e, 0xf3))
                    .setFabPressedColor(Color.rgb(0x30, 0x3F, 0x9F))
                    .setCheckNornalColor(Color.WHITE)
                    .setCheckSelectedColor(Color.BLACK)
                    .setIconBack(R.drawable.ic_action_previous_item)
                    .build();

            FunctionConfig.Builder functionConfigBuilder = new FunctionConfig.Builder();
            cn.finalteam.galleryfinal.ImageLoader imageLoader;
            PauseOnScrollListener pauseOnScrollListener = null;
            imageLoader = new PicassoImageLoader();
            pauseOnScrollListener = new PicassoPauseOnScrollListener(false, true);

            ArrayList<String> filterList = new ArrayList<>();
            filterList.add("GalleryFinal");
            functionConfigBuilder.setEnableEdit(true);//配置可编辑
            functionConfigBuilder.setEnableRotate(false);//配置可旋转
            functionConfigBuilder.setRotateReplaceSource(false);//配置旋转时替换原图
            functionConfigBuilder.setEnableCrop(true);//配置可裁剪
            functionConfigBuilder.setCropSquare(false);//配置裁剪成正方形
            functionConfigBuilder.setCropReplaceSource(false);//配置裁剪后替换原图
            functionConfigBuilder.setForceCropEdit(false);//配置强制裁剪
            functionConfigBuilder.setEnableCamera(false);
            functionConfigBuilder.setEnablePreview(false);
            functionConfigBuilder.setFilter(filterList);

            FunctionConfig functionConfig = functionConfigBuilder.build();

            CoreConfig coreConfig = new CoreConfig.Builder(this, imageLoader, theme)
                    .setFunctionConfig(functionConfig)
                    .setPauseOnScrollListener(pauseOnScrollListener)
                    .setNoAnimcation(false)
                    .build();
            GalleryFinal.init(coreConfig);

            galleryInit = true;
        }
    }

    public static final int REQ_GALLERY = 2;

    private void galleryImageStart() {
        initGalleryFinal();
        GalleryFinal.openGallerySingle(REQ_GALLERY, new GalleryFinal.OnHanlderResultCallback() {
            @Override
            public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
                if (resultList != null && resultList.size() > 0) {

                    PhotoInfo photoInfo = resultList.get(0);
                    if (photoInfo.getOriginPhotoPath() != null) {

                        Log.e("QQChatActivity", "onHanlderSuccess: originPhotoPath " + photoInfo.getOriginPhotoPath());
                        Log.e("QQChatActivity", "onHanlderSuccess: photoPath " + photoInfo.getPhotoPath());
                        File originPicFile = new File(photoInfo.getOriginPhotoPath());
                        imageDate = new Date(originPicFile.lastModified());
                    }

                    // saveOptimisePic(photoInfo.getPhotoPath(), photoInfo.getOriginPhotoPath());

                    Intent intent = new Intent(getApplication(), ShowImageActivity.class);
                    intent.putExtra(PHOTO_PATH, photoInfo.getPhotoPath());
                    intent.putExtra(PHOTO_UUID, uuid);
                    intent.putExtra(PHOTO_MODIFY_TIME, imageDate.getTime());
                    startActivity(intent);

                }
            }

            @Override
            public void onHanlderFailure(int requestCode, String errorMsg) {
                Static.toastShort(getApplicationContext(), "获取图片异常！");
            }
        });
    }


    private LoadAlertDialog loadAlertDialog;

    /**
     * 保存适当大小的图片
     */
    private void saveOptimisePic(final String path, final String originPath) {
        if (path == null || path.isEmpty()) {
            Static.toastShort(getApplicationContext(), "图片选择异常！");
            return;
        }
        final String end = FileUtil.getFileEnd(path);
        if (end == null) {
            Static.toastShort(getApplicationContext(), "图片选择异常！");
            return;
        }
        File file = new File(path);
        if (!file.exists()) {
            Static.toastShort(getApplicationContext(), "图片不存在！");
            return;
        }
        if (!end.equals(".png") && !end.equals(".jpeg")
                && !end.equals(".jpg")) {
            Static.toastShort(getApplicationContext(), "不支持的图片类型！");
            return;
        }
        if (loadAlertDialog == null) {
            loadAlertDialog = new LoadAlertDialog(this, R.style.FullScreenDialog);
        }
        loadAlertDialog.showWithTxt("正在提取照片...");
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {
                Log.e("gr", "subscribe: Thread " + Thread.currentThread().getName());
                e.onNext(path);
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io())// 指定 subscribe() 发生在 IO 线程
                .map(new Function<String, Bitmap>() {
                    @Override
                    public Bitmap apply(@NonNull String path) throws Exception {
                        Bitmap bitmap = BitmapUtils.decodeFile(path, 200, 200);
                        //                        Bitmap bitmap = BitmapUtils.decodeFile(path, 1024*240);
                        if (originPath.contains(Static.APP_SHORT_NAME)) {
                            Static.deleteImage(originPath, QQChatActivity.this);
                        }
                        uuid = UUID.randomUUID().toString();
                        picturePath = Static.SDCARD_APP_ROOT + File.separator
                                + Static.HOME_CACHE_IMAGE + File.separator + uuid;
                        if (end.equals("png")) {
                            picturePath += ".png";
                            BitmapUtils.savePNG_After(bitmap, picturePath);
                        } else {
                            picturePath += ".jpg";
                            BitmapUtils.savePNG_After(bitmap, picturePath);//保存图片大小
                        }
                        Static.notifyMediaSync(picturePath, QQChatActivity.this);
                        return bitmap;
                    }
                }).observeOn(AndroidSchedulers.mainThread()) // 指定 Subscriber 的回调发生在主线程
                .subscribe(new Consumer<Bitmap>() {
                    @Override
                    public void accept(@NonNull Bitmap bitmap) throws Exception {
                        loadAlertDialog.dismiss();
                        Intent intent = new Intent(getApplication(), ShowImageActivity.class);
                        intent.putExtra(PHOTO_PATH, picturePath);
                        intent.putExtra(PHOTO_UUID, uuid);
                        intent.putExtra(PHOTO_MODIFY_TIME, imageDate.getTime());
                        startActivity(intent);
                    }
                });

    }

    private void showNotification(String text) throws JSONException {
        NotificationManager mNotifyMgr =
                (NotificationManager) MyApplication.application.getApplicationContext()
                        .getSystemService(NOTIFICATION_SERVICE);
        //        PendingIntent contentIntent = PendingIntent.getActivity(
        //                this, 0, new Intent(this, ResultActivity.class), 0);
        Intent intent = new Intent();
        intent.setClass(getApplicationContext(), QQChatActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 6, intent, 0);
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(MyApplication.application.getApplicationContext())
                        .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE)
                        .setSmallIcon(R.drawable.p9)
                        .setContentTitle("Chat")
                        .setContentText(text)
                        .setContentIntent(pendingIntent);
        Random random = new Random();
        mNotifyMgr.notify(random.nextInt(1000000), mBuilder.build());

    }

    class SearchClick implements IOnSearchClickListener {

        @Override
        public void OnSearchClick(String keyword) {
            //selectDialog.show();
            Toast.makeText(QQChatActivity.this, "点击了searchbar要搜索的是" + keyword, Toast.LENGTH_SHORT).show();

            //            ((MainTabFragement)flist.get(binding.viewpager.getCurrentItem())).search(keyword,key);
        }
    }

    @OnClick({R.id.chat_bottom_emoji, R.id.chat_bottom_add, R.id.chat_sendbutton})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.chat_bottom_emoji:

                SearchFragment searchFragment = SearchFragment.newInstance();
                searchFragment.show(getSupportFragmentManager(), SearchFragment.TAG);

                searchFragment.setOnSearchClickListener(new SearchClick());
                break;
            case R.id.chat_bottom_add:
                galleryImageStart();

                break;
            case R.id.chat_sendbutton:
                String textcontent = mChatEdittext.getText().toString();
                addToHashMap("me", textcontent);

                mMyChatRecycleAdapter.notifyDataSetChanged();
                mChatRecycleview.scrollToPosition(arrayList.size() - 1);

                try {
                    showNotification(textcontent);
                } catch (JSONException e) {
                    Toast.makeText(this, "json异常", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
                break;
        }
    }
}
