package com.example.hp.demo.activity;

import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.easymap.android.maps.MapView;
import com.easymap.android.maps.unit.GeoPoint;
import com.easymap.android.maps.unit.LayerInfo;
import com.easymap.android.maps.unit.TileSourceType;
import com.easymap.android.maps.views.overlay.Overlay;
import com.example.hp.demo.R;
import com.example.hp.demo.constant.Static;
import com.example.hp.demo.model.EnterpriseStudio;
import com.example.hp.demo.model.JzwOverlay;
import com.example.hp.demo.utils.FileUtil;
import com.example.hp.demo.utils.PreferenceUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainMapActivity extends BaseMapActivity {
    @BindView(R.id.MapView)
    com.easymap.android.maps.MapView mapView;
    @BindView(R.id.reset)
    ImageView reset;
    @BindView(R.id.small)
    ImageView small;
    @BindView(R.id.big)
    ImageView big;

    //地图相关
    private Drawable otherPosDrawable;
    private List<Overlay> overlays;
    private String offline_path_sl;
    private LayerInfo layerInfo;
    private JzwOverlay myOverlay;
    private LocationManager locationManager;
    private Location location;
    private ImageView imgpos;
    private List<EnterpriseStudio.Enterprise> newdata;
    private boolean hasMoved = false;
    // 本轮加载
    private boolean isLoading = false;
    // 正加载中，又移动
    private boolean youyidong = false;
    private boolean firstShow, isgps = false;//gps是否已经定位到了

    private static final int map_level = 19;

    private boolean isHistoryData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_map);
        ButterKnife.bind(this);
        initMap();
        initGps();
        initView();
    }

    protected void onResume() {
        super.onResume();
        firstShow = true;
        if (myOverlay != null) {
            mapView.getOverlays().removeAll(myOverlay.getmItems());
            myOverlay.getmItems().clear();
            myOverlay.getBuildings().clear();
            if (null != myOverlay.getTv()) {
                mapView.removeView(myOverlay.getTv());
            }
        }
        if (getIntent().getExtras() != null) {
            isHistoryData = getIntent().getExtras().getBoolean("history", false);
            newdata = (List<EnterpriseStudio.Enterprise>) getIntent().getExtras().getSerializable("enterprise");

        }

        myOverlay.setIsHistory(isHistoryData);
        utilPosition(newdata);
        //        GeoPoint point = new GeoPoint(Double.valueOf(s.getX()), Double.valueOf(s.getY()));
        //        mapView.setMapCenter(point);
    }

    private void initView() {
        setTitleBarTitle("地图查看");
        showBack();
    }

    private void initMap() {
        initLis();
        otherPosDrawable = ContextCompat.getDrawable(this, R.drawable.other_pos);
        overlays = new ArrayList<>();
        // 在线地图数据
        // online_url = "http://172.18.65.78:7001/EzServer667/Maps/PGISChina";
        // online_url =
        // "http://127.0.0.1:8088/PGIS_S_TileMapServer/Maps/PGISSL";

        String pcsCode = PreferenceUtil.getInstance(this).getPreLastPCSCode();
        // 离线数据缓存路径
        offline_path_sl = Static.getMapPathByPcscode(MainMapActivity.this, pcsCode, "sl");

        layerInfo = new LayerInfo("photographic", "Geog2010(256)", 1, 20, TileSourceType.easymap, Static.map_online_url,
                offline_path_sl, null);

        mapView.setLayerInfo(layerInfo);
        mapView.setZoomLevel(map_level);
        // view.showZoomControls();
        mapView.showArrow();

        mapView.setMapCenter(new GeoPoint(121.56903, 29.87634));
        mapView.showScaleBar();
        mapView.setRotatable(true);
        // addMyOverlay(0,0);
        mapView.setOnTouchListener(new MyOnTouchListener());

        imgpos = new ImageView(MainMapActivity.this);
        imgpos.setBackgroundResource(R.drawable.my_pos);
    }

    private void initGps() {
        locationManager = (LocationManager) MainMapActivity.this.getSystemService(Context.LOCATION_SERVICE);
        if (locationManager.isProviderEnabled(android.location.LocationManager.GPS_PROVIDER)) {
            Intent GPSIntent = new Intent();
            GPSIntent.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");
            GPSIntent.addCategory("android.intent.category.ALTERNATIVE");
            GPSIntent.setData(Uri.parse("custom:3"));
            try {
                PendingIntent.getBroadcast(MainMapActivity.this, 0, GPSIntent, 0).send();
            } catch (PendingIntent.CanceledException e) {
                e.printStackTrace();
            }
        } else {
            showDialog();
        }

        location = locationManager.getLastKnownLocation(android.location.LocationManager.GPS_PROVIDER);
        if (location != null) {
            isgps = true;
            try {
                markPosPoint(location.getLongitude(), location.getLatitude(), imgpos, true, isgps);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            isgps = false;
            // List<OverlayItem> items = new ArrayList<OverlayItem>();
            // items.add(genOverlayItem(new GeoPoint(121.56903+0.00001*x,
            // p29.87634+0.00001*x) , "标记点", "标记的感兴趣的对象"));
            // items.add(genOverlayItem(new GeoPoint(121.63299852988436,
            // p29.91484597793877) , "标记点", "公安局门口 晚上测试"));
            // items.add(genOverlayItem(new GeoPoint(121.5759716666667,
            // p29.86487499999999) , "标记点", "公安局门口 十字路口白天测试"));

            // items.add(genOverlayItem(new GeoPoint(121.57014, p29.87745) ,
            // "标记点2", "标记的感兴趣的对象"));
            // markPoints(items);
            markPosPoint(121.5759716666667, 29.86487499999999, null, true, isgps);
            Static.toastShort(MainMapActivity.this, "定位失败，请到空旷场地上重试");
        }
        locationManager.requestLocationUpdates(android.location.LocationManager.GPS_PROVIDER, 2000, 10, locationListener);
    }

    private final LocationListener locationListener = new LocationListener() {
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }

        @Override
        public void onProviderEnabled(String provider) {
        }

        @Override
        public void onProviderDisabled(String provider) {
        }

        // 当位置变化时触发
        @Override
        public void onLocationChanged(Location location) {
            isgps = true;
            Log.e("MainMapActivity", "改变x=" + location.getLongitude() + " y=" + location.getLatitude());
            markPosPoint(location.getLongitude(), location.getLatitude(), imgpos, firstShow, isgps);
            myOverlay.getmItems().clear();
            myOverlay.setIsGps(true);
            utilPosition(newdata);
            firstShow = false;
            // 使用新的location更新TextView显示
        }
    };

    private void utilPosition(List<EnterpriseStudio.Enterprise> objs) {
        if (null != objs) {
            if (youyidong) { // 又移动过了，就不再加载了
                Log.e("MainMapActivity", "又在移动了，本轮加载提前结束" + "isLoading = false");
                youyidong = false;
                isLoading = false;
            } else {
                for (EnterpriseStudio.Enterprise obj : objs) {
                    myOverlay.addOverlayItem(obj);
                }
                mapView.getOverlays().add(myOverlay);
            }
        }
    }

    public void markPosPoint(Double lon, Double lat, ImageView img, Boolean isCenter, Boolean isGps) {// imgpos
        mapView.removeView(img);
        Log.e("MainMapActivity", "lon=" + lon + "  lat=" + lat);
        if (myOverlay == null) {
            myOverlay = new JzwOverlay(otherPosDrawable, MainMapActivity.this, mapView, lon, lat, isGps);
        }
        myOverlay.setIsGps(isGps);
        GeoPoint point = new GeoPoint(lon, lat);
        if (img != null) {
            img.setTag(point);
            mapView.addView(img, new MapView.LayoutParams(img.getWidth(), img.getHeight(), point, 0, -5,
                    MapView.LayoutParams.BOTTOM_CENTER));
        }
        if (isCenter) {
            mapView.setMapCenter(point);
        }
    }

    private void initLis() {
        String licPath = Static.SDCARD_APP_ROOT + File.separator + Static.HOME_LIC
                + File.separator + "EzServiceClient4Android.lic";
        File licFile = new File(licPath);
        if (!licFile.exists()) {
            FileUtil.copyFile("EzServiceClient4Android.lic", licFile, this);
        }
        initLisence(licFile.getAbsolutePath());
    }

    @OnClick({R.id.reset, R.id.small, R.id.big})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.reset:
                if (location != null) {
                    mapView.setMapCenter(location.getLongitude(), location.getLatitude());
                } else {
                    Toast.makeText(MainMapActivity.this, "定位失败", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.small:
                mapView.zoomOut();
                break;
            case R.id.big:
                mapView.zoomIn();
                break;
        }
    }

    class MyOnTouchListener implements View.OnTouchListener {
        @Override
        public boolean onTouch(View v, MotionEvent e) {
            switch (e.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    myOverlay.removeAll();
                    break;
                case MotionEvent.ACTION_UP:
                    // endX = e.getX();
                    // endY = e.getY();
                    // addMyOverlay((int)(startX-endX),(int)(startY-endY));
                    if (hasMoved) { // 移动过后才开始加
                        if (!isLoading) {
                            // reloadBuildingFirst();
                        }
                        hasMoved = false;
                    }
                    break;
                case MotionEvent.ACTION_MOVE:
                    hasMoved = true;
                    if (isLoading) {
                        youyidong = true;
                        Log.e("MainMapActivity", "youyidong = true;");
                    }
                default:
                    break;
            }
            return false;
        }
    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainMapActivity.this);
        builder.setTitle("设置").setMessage("请开启Gps！");
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                MainMapActivity.this.startActivity(intent);
                dialog.dismiss();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }
}
