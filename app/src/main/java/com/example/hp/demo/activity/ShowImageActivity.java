package com.example.hp.demo.activity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.hp.demo.R;
import com.example.hp.demo.helper.PicassoImageLoader;
import com.example.hp.demo.helper.PicassoPauseOnScrollListener;
import com.example.hp.demo.utils.DensityUtil;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.finalteam.galleryfinal.CoreConfig;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.ImageLoader;
import cn.finalteam.galleryfinal.PauseOnScrollListener;
import cn.finalteam.galleryfinal.ThemeConfig;
import cn.finalteam.galleryfinal.model.PhotoInfo;
import uk.co.senab.photoview.PhotoView;

import static com.example.hp.demo.activity.QQChatActivity.PHOTO_PATH;
import static com.example.hp.demo.activity.QQChatActivity.REQ_CAMERA;

public class ShowImageActivity extends AppCompatActivity {


    @BindView(R.id.photoimage)
    PhotoView mPhotoimage;
    @BindView(R.id.opencamera)
    Button mOpencamera;
    private String photoPath;
    private int picWidth, picHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_image);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        Intent intent = getIntent();
        photoPath = intent.getStringExtra(PHOTO_PATH);
        if (photoPath != null) {
            //            getPhotoWidthHeight();

            File file = new File(photoPath);
            mPhotoimage.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            Picasso.with(getApplicationContext()).load(file).fit().into(mPhotoimage);
        }
        mOpencamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                beginCameraPhoto();
            }
        });
    }

    private void beginCameraPhoto() {

        initGalleryFinal();

        GalleryFinal.openCamera(REQ_CAMERA, new GalleryFinal.OnHanlderResultCallback() {
            @Override
            public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
                if (resultList != null && resultList.size() > 0) {

                    PhotoInfo photoInfo = resultList.get(0);
                    photoPath = photoInfo.getPhotoPath();
                    Log.e("Steven", "openCamera onHanlderSuccess: " + photoPath);
//                    getPhotoWidthHeight();
                    File file = new File(photoPath);
                    mPhotoimage.setScaleType(ImageView.ScaleType.CENTER_INSIDE);

                    Picasso.with(getApplicationContext()).load(file).memoryPolicy(MemoryPolicy.NO_CACHE)
                            .fit().into(mPhotoimage);
                    //                    saveOptimisePic(photoInfo.getPhotoPath(), photoInfo.getOriginPhotoPath());
                }
            }

            @Override
            public void onHanlderFailure(int requestCode, String errorMsg) {

            }
        });


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
            ImageLoader imageLoader;
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

    private void getPhotoWidthHeight() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(photoPath, options);
        /**
         *options.outHeight为原始图片的高
         */
        picHeight = options.outHeight;
        picWidth = options.outWidth;

        int screenWidth = DensityUtil.getScreenWidth(this);
        int screenHeight = DensityUtil.getScreenHeight(this);
        int height = (int) (screenHeight * 0.6);
        int width = (int) (screenWidth * 0.8);

        int reHeight = (int) (picHeight * width * 1.0f / picWidth);
        int reWidth = (int) (picWidth * height * 1.0f / picHeight);
        RelativeLayout.LayoutParams layoutParams = null;
        if (reHeight < height) {
            layoutParams = new RelativeLayout.LayoutParams(
                    width, reHeight
            );
        } else {
            layoutParams = new RelativeLayout.LayoutParams(
                    reWidth, height
            );
        }

        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        mPhotoimage.setLayoutParams(layoutParams);
    }
}
