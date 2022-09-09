package activity.com.myappdata;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
//import com.amap.api.maps.overlay.BusRouteOverlay;
//import com.amap.api.maps.overlay.DrivingRouteOverlay;
//import com.amap.api.maps.overlay.WalkRouteOverlay;
import com.amap.api.navi.AMapNavi;
import com.amap.api.navi.AMapNaviListener;
import com.amap.api.navi.AMapNaviView;
import com.amap.api.navi.AMapNaviViewListener;
import com.amap.api.navi.AMapNaviViewOptions;
import com.amap.api.navi.model.AMapCalcRouteResult;
import com.amap.api.navi.model.AMapLaneInfo;
import com.amap.api.navi.model.AMapModelCross;
import com.amap.api.navi.model.AMapNaviCameraInfo;
import com.amap.api.navi.model.AMapNaviCross;
import com.amap.api.navi.model.AMapNaviInfo;
import com.amap.api.navi.model.AMapNaviLocation;
import com.amap.api.navi.model.AMapNaviRouteNotifyData;
import com.amap.api.navi.model.AMapNaviTrafficFacilityInfo;
import com.amap.api.navi.model.AMapServiceAreaInfo;
import com.amap.api.navi.model.AimLessModeCongestionInfo;
import com.amap.api.navi.model.AimLessModeStat;
import com.amap.api.navi.model.NaviInfo;
import com.amap.api.navi.model.NaviLatLng;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.route.BusPath;
import com.amap.api.services.route.BusRouteResult;
import com.amap.api.services.route.DriveRouteResult;
import com.amap.api.services.route.RideRouteResult;
import com.amap.api.services.route.RouteSearch;
import com.amap.api.services.route.WalkRouteResult;
import com.autonavi.tbt.TrafficFacilityInfo;
import com.amap.api.services.route.RouteSearch.BusRouteQuery;
import java.util.ArrayList;
import java.util.List;
import activity.com.myappdata.util.RouteOverlay;
import activity.com.myappdata.util.traffictransfer.BusRouteOverlay;

/**
 * 高德地图规则路线
 */
public class AMapRouteActivity extends Activity implements AMapNaviViewListener,

        AMapNaviListener , View.OnClickListener, RouteSearch.OnRouteSearchListener {


    private AMapNaviView navi_map;

    private AMapNavi aMapNavi;

    private List startPointList = new ArrayList<>();// 起点的集合

    private List endPointList = new ArrayList<>(); // 终点的集合

    private List wayPointList = new ArrayList<>(); // 途径点的集合

    private AMap aMap;
    private AMapNaviView mapView;

    /**
     * 公交按钮，驾车按钮，步行按钮
     */
    private ImageView img_transit, img_driving, img_walk;

    private int busMode = RouteSearch.BusDefault;// 公交默认模式
    private int drivingMode = RouteSearch.DrivingDefault;// 驾车默认模式
    private int walkMode = RouteSearch.WalkDefault;// 步行默认模式
    private RouteSearch routeSearch;

    private BusRouteResult busRouteResult;// 公交模式查询结果
    private DriveRouteResult driveRouteResult;// 驾车模式查询结果
    private WalkRouteResult walkRouteResult;// 步行模式查询结果

    //    链接：https://www.jianshu.com/p/944ab9c1f3ac
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a_map_route);
        // 初始化地图并创建地图

        navi_map = (AMapNaviView) findViewById(R.id.navi_map);

        navi_map.setAMapNaviViewListener(this);

        navi_map.onCreate(savedInstanceState);

// 获取地图属性 并设置相关属性

        AMapNaviViewOptions options = navi_map.getViewOptions();

        // 关闭高德提供的导航图层

        options.setLayoutVisible(false);

// 关闭高德提供的导航路线绘制

        options.setAutoDrawRoute(false);

        // 设置自车位置

        options.setCarBitmap(BitmapFactory.decodeResource(this.getResources(), R.mipmap.shopping_car_shop));

        // 设置导航属性

        navi_map.setViewOptions(options);

        initAMapNavi();

// 添加导航路线 起点 终点 途径点

        NaviLatLng start = new NaviLatLng();

        NaviLatLng end = new NaviLatLng();

        NaviLatLng way1 = new NaviLatLng();

        NaviLatLng way2 = new NaviLatLng();

        start.setLatitude(30.291779);

        start.setLongitude(120.040998);

        startPointList.add(start);

        end.setLatitude(30.406169);

        end.setLongitude(120.305117);

        endPointList.add(end);

        way1.setLatitude(30.270999);

        way1.setLongitude(120.163277);

        way2.setLatitude(30.291124);

        way2.setLongitude(120.212892);

        wayPointList.add(way1);

        wayPointList.add(way2);


//        链接：https://www.jianshu.com/p/944ab9c1f3ac


        mapView = (AMapNaviView) findViewById(R.id.navi_map);
        mapView.onCreate(savedInstanceState);// 此方法必须重写
        img_transit = (ImageView) findViewById(R.id.img_transit);
        img_driving = (ImageView) findViewById(R.id.img_driving);
        img_walk = (ImageView) findViewById(R.id.img_walk);

        try {
            routeSearch = new RouteSearch(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        routeSearch.setRouteSearchListener(this);
        init();

    }

    //初始化导航对象
    private void initAMapNavi() {
        aMapNavi = AMapNavi.getInstance(getApplicationContext());

        aMapNavi.addAMapNaviListener(this);

    }


    @Override
    public void onInitNaviFailure() {

    }

    @Override
    public void onInitNaviSuccess() {

    }

    @Override
    public void onStartNavi(int i) {

    }

    @Override
    public void onTrafficStatusUpdate() {

    }

    @Override
    public void onLocationChange(AMapNaviLocation aMapNaviLocation) {

    }

    @Override
    public void onGetNavigationText(int i, String s) {

    }

    @Override
    public void onGetNavigationText(String s) {

    }

    @Override
    public void onEndEmulatorNavi() {

    }

    @Override
    public void onArriveDestination() {

    }

    @Override
    public void onCalculateRouteFailure(int i) {

    }

    @Override
    public void onReCalculateRouteForYaw() {

    }

    @Override
    public void onReCalculateRouteForTrafficJam() {

    }

    @Override
    public void onArrivedWayPoint(int i) {

    }

    @Override
    public void onGpsOpenStatus(boolean b) {

    }

    @Override
    public void onNaviInfoUpdate(NaviInfo naviInfo) {

    }

    @Override
    public void onNaviInfoUpdated(AMapNaviInfo aMapNaviInfo) {

    }

    @Override
    public void updateCameraInfo(AMapNaviCameraInfo[] aMapNaviCameraInfos) {

    }

    @Override
    public void updateIntervalCameraInfo(AMapNaviCameraInfo aMapNaviCameraInfo, AMapNaviCameraInfo aMapNaviCameraInfo1, int i) {

    }

    @Override
    public void onServiceAreaUpdate(AMapServiceAreaInfo[] aMapServiceAreaInfos) {

    }

    @Override
    public void showCross(AMapNaviCross aMapNaviCross) {

    }

    @Override
    public void hideCross() {

    }

    @Override
    public void showModeCross(AMapModelCross aMapModelCross) {

    }

    @Override
    public void hideModeCross() {

    }

    @Override
    public void showLaneInfo(AMapLaneInfo[] aMapLaneInfos, byte[] bytes, byte[] bytes1) {

    }

    @Override
    public void showLaneInfo(AMapLaneInfo aMapLaneInfo) {

    }

    @Override
    public void hideLaneInfo() {

    }

    @Override
    public void onCalculateRouteSuccess(int[] ints) {

    }

    @Override
    public void notifyParallelRoad(int i) {

    }

    @Override
    public void OnUpdateTrafficFacility(AMapNaviTrafficFacilityInfo[] aMapNaviTrafficFacilityInfos) {

    }

    @Override
    public void OnUpdateTrafficFacility(AMapNaviTrafficFacilityInfo aMapNaviTrafficFacilityInfo) {

    }

    @Override
    public void OnUpdateTrafficFacility(TrafficFacilityInfo trafficFacilityInfo) {

    }

    @Override
    public void updateAimlessModeStatistics(AimLessModeStat aimLessModeStat) {

    }

    @Override
    public void updateAimlessModeCongestionInfo(AimLessModeCongestionInfo aimLessModeCongestionInfo) {

    }

    @Override
    public void onPlayRing(int i) {

    }

    @Override
    public void onCalculateRouteSuccess(AMapCalcRouteResult aMapCalcRouteResult) {

    }

    @Override
    public void onCalculateRouteFailure(AMapCalcRouteResult aMapCalcRouteResult) {

    }

    @Override
    public void onNaviRouteNotify(AMapNaviRouteNotifyData aMapNaviRouteNotifyData) {

    }


//    @Override
//    public void updateIntervalCameraInfo(AMapNaviCameraInfo aMapNaviCameraInfo, AMapNaviCameraInfo aMapNaviCameraInfo1, int i) {
//
//    }
//
//
//    @Override
//    public void showModeCross(AMapModelCross aMapModelCross) {
//
//    }
//
//    @Override
//    public void hideModeCross() {
//
//    }
//
//
//    @Override
//    public void showLaneInfo(AMapLaneInfo aMapLaneInfo) {
//
//    }
//
//
//    @Override
//    public void onCalculateRouteSuccess(int[] ints) {
//
//    }
//
//
//    @Override
//    public void updateAimlessModeCongestionInfo(AimLessModeCongestionInfo aimLessModeCongestionInfo) {
//
//    }
//
//
//    @Override
//    public void onCalculateRouteSuccess(AMapCalcRouteResult aMapCalcRouteResult) {
//
//    }
//
//    @Override
//    public void onCalculateRouteFailure(AMapCalcRouteResult aMapCalcRouteResult) {
//
//    }
//
//    @Override
//    public void onNaviRouteNotify(AMapNaviRouteNotifyData aMapNaviRouteNotifyData) {
//
//    }
//
//
//
//    @Override
//    public void onMapTypeChanged(int i) {
//
//    }
//
//    @Override
//    public void onNaviViewShowMode(int i) {
//
//    }
//    @Override
//
//    protected void onResume() {
//
//super.onResume();
//
//navi_map.onResume();
//
//    }
//
//    @Override
//
//    protected void onPause() {
//
// super.onPause();
//
//navi_map.onPause();
//
//    }
//
//    @Override
//
//    protected void onDestroy() {
//
//super.onDestroy();
//
//navi_map.onDestroy();
//
//    }
//
//    @Override
//
//    public void onNaviSetting() {
//
//    }
//
//// 取消导航监听
//
//    @Override
//
//    public void onNaviCancel() {
//
//    }
//
//// 推出导航监听
//
//    @Override
//
//    public boolean onNaviBackClick() {
//
//return false;
//
//    }
//
//    @Override
//
//    public void onNaviMapMode(int i) {
//
//    }
//
//    @Override
//
//    public void onNaviTurnClick() {
//
//    }
//
//    @Override
//
//    public void onNextRoadClick() {
//
//    }
//
//    @Override
//
//    public void onScanViewButtonClick() {
//
//    }
//
//    @Override
//
//    public void onLockMap(boolean b) {
//
//    }
//
//    @Override
//
//    public void onNaviViewLoaded() {
//
//    }
//
//    @Override
//
//    public void onInitNaviFailure() {
//
//// 路线规划对象初始化失败
//
//    }
//
//    @Override
//
//    public void onInitNaviSuccess() {
//
//// 路线规划对象初始化成功
//
// /**
//
//             * 方法:
//
//             *  int strategy=mAMapNavi.strategyConvert(congestion, avoidhightspeed, cost, hightspeed, multipleroute);
//
//             * 参数:
//
//             * @congestion 躲避拥堵
//
//             * @avoidhightspeed 不走高速
//
//             * @cost 避免收费
//
//             * @hightspeed 高速优先
//
//             * @multipleroute 多路径
//
//             *
//
//             * 说明:
//
//             *      以上参数都是boolean类型，其中multipleroute参数表示是否多条路线，如果为true则此策略会算出多条路线。
//
//             * 注意:
//
//             *      不走高速与高速优先不能同时为true
//
//             *      高速优先与避免收费不能同时为true
//
//             */
//
// int i = 0;
//
//try{
//
//// 设置属性
//
//i = aMapNavi.strategyConvert(true, false, false, false, false);
//
//}catch (Exception e){
//
//e.printStackTrace();
//
//}
//
// /**
//
//             * 驾车路线规划
//
//             * startPointList 起点经纬度
//
//             * endPointList  终点经纬度
//
//             * wayPointList  途径点经纬度
//
//             * i              路线规划属性
//
//             */
//
//aMapNavi.calculateDriveRoute(startPointList,endPointList,wayPointList,i);
//
//    }
//
//// 开始导航
//
//    @Override
//
//    public void onStartNavi(int i) {
//
//    }
//
//    @Override
//
//    public void onTrafficStatusUpdate() {
//
//    }
//
//// 位置变化监听
//
//    @Override
//
//    public void onLocationChange(AMapNaviLocation aMapNaviLocation) {
//
//    }
//
//    @Override
//
//    public void onGetNavigationText(int i, String s) {
//
//    }
//
//    @Override
//
//    public void onEndEmulatorNavi() {
//
//    }
//
//    @Override
//
//    public void onArriveDestination() {
//
//    }
//
//    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
//
//
//
//    /**
//
//     * view 转为 bitmap 对象
//
//     * @param view
//
//     * @return
//
//     */
//
//    public static Bitmap convertViewToBitmap(View view) {
//
// view.destroyDrawingCache();
//
//view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
//
//             View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
//
// view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
//
// view.setDrawingCacheEnabled(true);
//
// return view.getDrawingCache(true);
//
//    }
//
//    @Override
//
//    public void onCalculateRouteFailure(int i) {
//
// // 路线规划失败
//
//    }
//
//    @Override
//
//    public void onReCalculateRouteForYaw() {
//
//    }
//
//    @Override
//
//    public void onReCalculateRouteForTrafficJam() {
//
//    }
//
//// 途径点回调
//
//    @Override
//
//    public void onArrivedWayPoint(int i) {
//
//    }
//
////GPS是否开启
//
//    @Override
//
//    public void onGpsOpenStatus(boolean b) {
//
//    }
//
//// 导航信息变化
//
//    @Override
//
//    public void onNaviInfoUpdate(NaviInfo naviInfo) {
//
//    }
//
//    @Override
//
//    public void onNaviInfoUpdated(AMapNaviInfo aMapNaviInfo) {
//
//    }
//
//    @Override
//
//    public void updateCameraInfo(AMapNaviCameraInfo[] aMapNaviCameraInfos) {
//
//    }
//
//// 区域地图变化
//
//    @Override
//
//    public void onServiceAreaUpdate(AMapServiceAreaInfo[] aMapServiceAreaInfos) {
//
//    }
//
//    @Override
//
//    public void showCross(AMapNaviCross aMapNaviCross) {
//
//    }
//
//    @Override
//
//    public void hideCross() {
//
//    }
//
//    @Override
//
//    public void showLaneInfo(AMapLaneInfo[] aMapLaneInfos, byte[] bytes, byte[] bytes1) {
//
//    }
//
//    @Override
//
//    public void hideLaneInfo() {
//
//    }
//
//
//
//    @Override
//
//    public void notifyParallelRoad(int i) {
//
//    }
//
//    @Override
//
//    public void OnUpdateTrafficFacility(AMapNaviTrafficFacilityInfo aMapNaviTrafficFacilityInfo) {
//
//    }
//
//    @Override
//
//    public void OnUpdateTrafficFacility(AMapNaviTrafficFacilityInfo[] aMapNaviTrafficFacilityInfos) {
//
//    }
//
//    @Override
//
//    public void OnUpdateTrafficFacility(TrafficFacilityInfo trafficFacilityInfo) {
//
//    }
//
//    @Override
//
//    public void updateAimlessModeStatistics(AimLessModeStat aimLessModeStat) {
//
//    }
//
//
//
//// 行车监听
//
//    @Override
//
//    public void onPlayRing(int i) {
//
//    }

    /**
     * 初始化AMap对象
     */
    private void init() {
        if (aMap == null) {
            aMap = mapView.getMap();
        }

        img_transit.setOnClickListener(this);
        img_driving.setOnClickListener(this);
        img_walk.setOnClickListener(this);

        // 设置地图可视缩放大小
        aMap.moveCamera(CameraUpdateFactory.zoomTo(100));
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onClick(View v) {

        // 这里是写死的两个位置
        LatLonPoint startPoint = new LatLonPoint(31.383755, 118.438321);
        LatLonPoint endPoint = new LatLonPoint(31.339746, 118.381727);

        final RouteSearch.FromAndTo fromAndTo = new RouteSearch.FromAndTo(startPoint, endPoint);
        if (v == img_transit) {// 公交
            BusRouteQuery query = new RouteSearch.BusRouteQuery(fromAndTo, busMode, "芜湖市", 1);// 第一个参数表示路径规划的起点和终点，第二个参数表示公交查询模式，第三个参数表示公交查询城市区号，第四个参数表示是否计算夜班车，0表示不计算
            routeSearch.calculateBusRouteAsyn(query);// 异步路径规划公交模式查询
        } else if (v == img_driving) {// 驾车
            RouteSearch.DriveRouteQuery query = new RouteSearch.DriveRouteQuery(fromAndTo, drivingMode, null, null, "");// 第一个参数表示路径规划的起点和终点，第二个参数表示驾车模式，第三个参数表示途经点，第四个参数表示避让区域，第五个参数表示避让道路
            routeSearch.calculateDriveRouteAsyn(query);// 异步路径规划驾车模式查询
        } else if (v == img_walk) {// 步行
            RouteSearch.WalkRouteQuery query = new RouteSearch.WalkRouteQuery(fromAndTo, walkMode);
            routeSearch.calculateWalkRouteAsyn(query);// 异步路径规划步行模式查询
        }
    }

    @Override
    public void onBusRouteSearched(BusRouteResult result, int rCode) {
        if (rCode == 0||rCode==1000) {
            if (result != null && result.getPaths() != null && result.getPaths().size() > 0) {
                busRouteResult = result;
                BusPath busPath = busRouteResult.getPaths().get(0);
                aMap.clear();// 清理地图上的所有覆盖物
                BusRouteOverlay routeOverlay = new BusRouteOverlay(this, aMap, busPath, busRouteResult.getStartPos(), busRouteResult.getTargetPos());
                routeOverlay.removeFromMap();
                routeOverlay.addToMap();
                routeOverlay.zoomToSpan();
            } else {
                showToast("对不起，没有搜索到相关数据！");
            }
        } else if (rCode == 27) {
            showToast("搜索失败,请检查网络连接！");
        } else if (rCode == 32) {
            showToast("key验证无效！");
        } else {
            showToast("未知错误，请稍后重试!错误码为" + rCode);
        }
    }

    @Override
    public void onDriveRouteSearched(DriveRouteResult driveRouteResult, int i) {

    }

    @Override
    public void onWalkRouteSearched(WalkRouteResult walkRouteResult, int i) {

    }

//    @Override
//    public void onDriveRouteSearched(DriveRouteResult result, int rCode) {
//        if (rCode == 0) {
//            if (result != null && result.getPaths() != null && result.getPaths().size() > 0) {
//                driveRouteResult = result;
//                DrivePath drivePath = driveRouteResult.getPaths().get(0);
//                aMap.clear();// 清理地图上的所有覆盖物
//                DrivingRouteOverlay drivingRouteOverlay = new DrivingRouteOverlay(this, aMap, drivePath, driveRouteResult.getStartPos(), driveRouteResult.getTargetPos());
//                drivingRouteOverlay.removeFromMap();
//                drivingRouteOverlay.addToMap();
//                drivingRouteOverlay.zoomToSpan();
//            } else {
//                showToast("对不起，没有搜索到相关数据！");
//            }
//        } else if (rCode == 27) {
//            showToast("搜索失败,请检查网络连接！");
//        } else if (rCode == 32) {
//            showToast("key验证无效！");
//        } else {
//            showToast("未知错误，请稍后重试!错误码为" + rCode);
//        }
//    }

//    @Override
//    public void onWalkRouteSearched(WalkRouteResult result, int rCode) {
//        if (rCode == 0) {
//            if (result != null && result.getPaths() != null && result.getPaths().size() > 0) {
//                walkRouteResult = result;
//                WalkPath walkPath = walkRouteResult.getPaths().get(0);
//                aMap.clear();// 清理地图上的所有覆盖物
//                WalkRouteOverlay walkRouteOverlay = new WalkRouteOverlay
//                        (this, aMap, walkPath, walkRouteResult.getStartPos(), walkRouteResult.getTargetPos());
//                walkRouteOverlay.removeFromMap();
//                walkRouteOverlay.addToMap();
//                walkRouteOverlay.zoomToSpan();
//            } else {
//                showToast("对不起，没有搜索到相关数据！");
//            }
//        } else if (rCode == 27) {
//            showToast("搜索失败,请检查网络连接！");
//        } else if (rCode == 32) {
//            showToast("key验证无效！");
//        } else {
//            showToast("未知错误，请稍后重试!错误码为" + rCode);
//        }
//    }

    @Override
    public void onRideRouteSearched(RideRouteResult rideRouteResult, int i) {

    }


    /**
     * toast封装
     *
     * @param str
     */
    private void showToast(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onNaviSetting() {

    }

    @Override
    public void onNaviCancel() {

    }

    @Override
    public boolean onNaviBackClick() {
        return false;
    }

    @Override
    public void onNaviMapMode(int i) {

    }

    @Override
    public void onNaviTurnClick() {

    }

    @Override
    public void onNextRoadClick() {

    }

    @Override
    public void onScanViewButtonClick() {

    }

    @Override
    public void onLockMap(boolean b) {

    }

    @Override
    public void onNaviViewLoaded() {

    }

    @Override
    public void onMapTypeChanged(int i) {

    }

    @Override
    public void onNaviViewShowMode(int i) {

    }
}