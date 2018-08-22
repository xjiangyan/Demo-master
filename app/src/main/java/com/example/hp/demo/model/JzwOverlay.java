package com.example.hp.demo.model;

/**
 * Created by Steven on 2017/2/4.
 */

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.easymap.android.maps.MapView;
import com.easymap.android.maps.unit.GeoPoint;
import com.easymap.android.maps.views.overlay.ItemizedOverlay;
import com.easymap.android.maps.views.overlay.OverlayItem;
import com.example.hp.demo.R;
import com.example.hp.demo.utils.CommonUtil;

import java.util.ArrayList;

public class JzwOverlay extends ItemizedOverlay<OverlayItem> {
    private ArrayList<OverlayItem> mItems;
    private ArrayList<EnterpriseStudio.Enterprise> buildings;
    private Context context;
    private MapView mapView;
    private TextView tv;
    private boolean isGps;
    private double jin, wei, distance;

    private boolean isHistory;

    public JzwOverlay(Drawable drawable, Context context, MapView mapView,
                      double jin, double wei, Boolean isGps) {
        super(boundCenterBottom(drawable));
        this.context = context;
        this.mapView = mapView;
        mItems = new ArrayList<>();
        buildings = new ArrayList<>();
        this.jin = jin;
        this.wei = wei;
        this.isGps = isGps;
    }

    public void setIsHistory(boolean isHistory) {
        this.isHistory = isHistory;
    }

    public void addOverlayItem(EnterpriseStudio.Enterprise b) {
        try {
            if (b.getLat() != null && !b.getLat().equals("")
                    && b.getLon() != null && !b.getLon().equals("")) {
                mItems.add(genOverlayItem(b));
                buildings.add(b);
                // 调用对新加入的item进行所有的初始化操作
                populate();
            } else {
                Log.d("JzwOverlay", b.getName() + " 没有坐标");
            }
        } catch (Exception e) {
            Log.e("JzwOverlay", "addOverlayItem异常");
        }
    }

    public void setIsGps(Boolean isGps) {
        this.isGps = isGps;
        populate();
    }

    private OverlayItem genOverlayItem(EnterpriseStudio.Enterprise jzw) {
        GeoPoint p = new GeoPoint(Double.valueOf(jzw.getLon()), Double.valueOf(jzw.getLat()));
        OverlayItem item = new OverlayItem(p, jzw.getName(), jzw.getName());
        return item;
    }

    /**
     * 当调用populate方法的时候,就会调用该方法去检索图层中的每一个标 * 记对象
     */
    @Override
    protected OverlayItem createItem(int arg0) {
        return mItems.get(arg0);
    }

    @Override
    protected int size() {
        return mItems.size();
    }

    protected boolean onTap(int index) {
        if (null != tv) {
            mapView.removeView(tv);
        }
        final EnterpriseStudio.Enterprise jzw = buildings.get(index);
        distance = Distance(new GeoPoint(Double.valueOf(jzw.getLon()), Double.valueOf(jzw.getLat())), new GeoPoint(jin, wei));

        Log.e("JzwOverlay", "参数：" + Double.valueOf(jzw.getLon()) + "," + Double.valueOf(jzw.getLat()) + "," + jin + "," + wei);
        Log.e("JzwOverlay", "距离：" + distance);
        tv = new TextView(context);
        tv.setGravity(Gravity.LEFT);//CENTER_VERTICAL
        tv.setTextColor(Color.WHITE);
        //tv.setTextSize(16f);
        tv.setBackgroundResource(R.drawable.pop_local);
        if (isGps) {
            tv.setText(CommonUtil.getString(jzw.getName()) + "\n" + "距离：" + distance + "米" + "　  >>更多");
        } else {
            tv.setText(CommonUtil.getString(jzw.getName()) + "\n" + "距离：  ---米      " + "　  >>更多");
        }

        tv.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO: 2018/1/15  
                //                Intent intent;
                //                if (isHistory) {
                //                    intent = new Intent(context, HistoryEnterpriseCheckActivity.class);
                //                } else {
                //                    intent = new Intent(context, SafeCheckNewItemActivity.class);
                //                }
                //                intent.putExtra("enterprise", jzw);
                //                context.startActivity(intent);
            }
        });
        mapView.addView(tv, new MapView.LayoutParams(tv.getWidth(), tv.getHeight(), mItems.get(index).getPoint(), 0, -30, MapView.LayoutParams.BOTTOM_CENTER));
        return true;
    }

    public void removeAll() {
        if (tv != null) {
            mapView.removeView(tv);
        }
    }

    public String sexUtil(String sex) {
        if (sex.trim().equals("") || !sex.trim().equals("1")) {
            return "男";
        } else {
            return "女";
        }
    }

    //距离查询
    public float Distance(GeoPoint start, GeoPoint end) {//double long1, double lat1, double long2,double lat2,
        return (float) (start.distanceTo(end));
    }


    public ArrayList<OverlayItem> getmItems() {
        return mItems;
    }

    public void setmItems(ArrayList<OverlayItem> mItems) {
        this.mItems = mItems;
    }

    public ArrayList<EnterpriseStudio.Enterprise> getBuildings() {
        return buildings;
    }

    public void setBuildings(ArrayList<EnterpriseStudio.Enterprise> buildings) {
        this.buildings = buildings;
    }

    public TextView getTv() {
        return tv;
    }

    public void setTv(TextView tv) {
        this.tv = tv;
    }

    public double getJin() {
        return jin;
    }

    public void setJin(double jin) {
        this.jin = jin;
    }

    public double getWei() {
        return wei;
    }

    public void setWei(double wei) {
        this.wei = wei;
    }
}
