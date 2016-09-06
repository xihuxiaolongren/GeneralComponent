package me.xihuxiaolong.generalcomponent.module.map;

import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.widget.ImageView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.PolylineOptions;
import com.baidu.mapapi.model.LatLng;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.xihuxiaolong.generalcomponent.R;
import me.xihuxiaolong.generalcomponent.base.activity.BaseActivity;

public class MapActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.bmapView)
    MapView bmapView;

    BaiduMap mBaiduMap;
    BitmapDescriptor mCurrentMarker;
    LocationClient mLocClient;
    MyLocationListenner myListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        int status = getIntent().getIntExtra("status", 0);
        switch (status){
            case 0:
                actionBar.setTitle(R.string.map_location);
                gpsInit();
                break;
            case 1:
                actionBar.setTitle(R.string.map_history_track);
                historyInit();
                break;
        }


    }

    private void gpsInit(){
        mBaiduMap = bmapView.getMap();
        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);
        myListener = new MyLocationListenner();
        LocationClientOption option = new LocationClientOption();
        option.setTimeOut(300000);
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
//        option.setEnableSimulateGps(true); //
        option.setScanSpan(1000);
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型,必须设置该值，默认的gc02载入定位经纬度偏差较大，如何设置gc02的定位暂未了解
//        ImageView imageView = (ImageView) LayoutInflater.from(this).inflate(R.layout.imageview_location_point, bmapView, false);
//        mCurrentMarker = BitmapDescriptorFactory
//                .fromView(imageView);
        mBaiduMap.setMyLocationConfigeration(new MyLocationConfiguration(
                MyLocationConfiguration.LocationMode.NORMAL, true, null));
        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);
        // 定位初始化
        mLocClient = new LocationClient(this);
        mLocClient.registerLocationListener(myListener);
        mLocClient.setLocOption(option);
        mLocClient.start();
    }

    /**
     * 定位SDK监听函数
     */
    public class MyLocationListenner implements BDLocationListener {

        boolean isFirstLoc = true; // 是否首次定位

        @Override
        public void onReceiveLocation(BDLocation location) {
            // map view 销毁后不在处理新接收的位置
            if (location == null || bmapView == null) {
                return;
            }
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(location.getDirection()).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            mBaiduMap.setMyLocationData(locData);
            if (isFirstLoc) {
                isFirstLoc = false;
                LatLng ll = new LatLng(location.getLatitude(),
                        location.getLongitude());
                MapStatus.Builder builder = new MapStatus.Builder();
                builder.target(ll).zoom(18.0f);
                mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
            }
        }

    }

    private void historyInit() {
        mBaiduMap = bmapView.getMap();
        //构造纹理资源
//        BitmapDescriptor custom = BitmapDescriptorFactory.fromAsset("icon_road_blue_arrow.png");

        // 定义点
        LatLng pt1 = new LatLng(39.93923, 116.357428);
        LatLng pt2 = new LatLng(36.91923, 116.327428);
        LatLng pt3 = new LatLng(37.89923, 111.347428);
        LatLng pt4 = new LatLng(35.89923, 105.367428);
        LatLng pt5 = new LatLng(36.91923, 102.387428);

        //构造纹理队列
//        List<BitmapDescriptor> customList = new ArrayList<BitmapDescriptor>();
//        customList.add(custom);

        List<LatLng> points = new ArrayList<>();
//        List<Integer> index = new ArrayList<Integer>();
        points.add(pt1);//点元素
////        index.add(0);//设置该点的纹理索引
        points.add(pt2);//点元素
////        index.add(0);//设置该点的纹理索引
        points.add(pt3);//点元素
////        index.add(0);//设置该点的纹理索引
        points.add(pt4);//点元素
////        index.add(0);//设置该点的纹理索引
        points.add(pt5);//点元素
        //构造对象
//        OverlayOptions ooPolyline = new PolylineOptions().width(15).color(0xAAFF0000).points(points).customTextureList(customList).textureIndex(index);
        OverlayOptions ooPolyline = new PolylineOptions().width(15).color(0xAAFF0000).points(points);
        //添加到地图
        float[] res = new float[3];
        Location.distanceBetween(39.93923, 116.357428, 36.91923, 102.387428, res);
        mBaiduMap.setMapStatus(MapStatusUpdateFactory.newMapStatus(new MapStatus.Builder().zoom(getZoom(res[0])).target(getCenter(points.get(0), points.get(points.size() - 1))).build()));
        mBaiduMap.addOverlay(ooPolyline);

        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.drawable.map_starting);
        OverlayOptions option = new MarkerOptions()
                .position(points.get(0))
                .icon(bitmap);
//在地图上添加Marker，并显示
        mBaiduMap.addOverlay(option);
        BitmapDescriptor bitmapend = BitmapDescriptorFactory
                .fromResource(R.drawable.map_terminal);
        OverlayOptions optionend = new MarkerOptions()
                .position(points.get(points.size() - 1))
                .icon(bitmapend);
//在地图上添加Marker，并显示
        mBaiduMap.addOverlay(optionend);
    }

    @Override
    protected void onPause() {
        bmapView.onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        bmapView.onResume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        // 退出时销毁定位
        mLocClient.stop();
        // 关闭定位图层
        mBaiduMap.setMyLocationEnabled(false);
        bmapView.onDestroy();
        bmapView = null;
        super.onDestroy();
    }

    float getZoom(float distance){
        distance = distance / 6;
        float[] s = {10, 20, 50, 100, 200, 500, 1000, 2000, 5000, 10000, 20000, 25000, 50000, 100000, 200000, 500000, 1000000, 2000000};
        if(distance > s[17])
            return 3;
        else if(distance > s[16] && distance < s[17])
            return 4;
        else if(distance > s[15] && distance < s[16])
            return 5;
        else if(distance > s[14] && distance < s[15])
            return 6;
        else if(distance > s[13] && distance < s[14])
            return 7;
        else if(distance > s[12] && distance < s[13])
            return 8;
        else if(distance > s[11] && distance < s[12])
            return 9;
        else if(distance > s[10] && distance < s[11])
            return 10;
        else if(distance > s[9] && distance < s[10])
            return 11;
        else if(distance > s[8] && distance < s[9])
            return 12;
        else if(distance > s[7] && distance < s[8])
            return 13;
        else if(distance > s[6] && distance < s[7])
            return 14;
        else if(distance > s[5] && distance < s[6])
            return 15;
        else if(distance > s[4] && distance < s[5])
            return 16;
        else if(distance > s[3] && distance < s[4])
            return 17;
        else
            return 18;
    }

    LatLng getCenter(LatLng a, LatLng b){
        return new LatLng((a.latitude + b.latitude) / 2, (a.longitude + b.longitude) / 2);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
