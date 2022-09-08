package activity.com.myappdata;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.design.widget.NavigationView;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.services.*;
import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.animation.ScaleAnimation;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.route.BusRouteResult;
import com.amap.api.services.route.DriveRouteResult;
import com.amap.api.services.route.RideRouteResult;
import com.amap.api.services.route.RouteSearch;
import com.amap.api.services.route.WalkPath;
import com.amap.api.services.route.WalkRouteResult;

import activity.com.myappdata.databinding.ActivityMaPGaoDeBinding;
import activity.com.myappdata.lib.LocationTask;
import activity.com.myappdata.lib.OnLocationGetListener;
import activity.com.myappdata.lib.PositionEntity;
import activity.com.myappdata.lib.RegeocodeTask;
import activity.com.myappdata.lib.RouteTask;
import activity.com.myappdata.lib.Sha1;
import activity.com.myappdata.lib.Utils;
import activity.com.myappdata.uiutils.selfviewMap.CommonUtils;
import activity.com.myappdata.uiutils.selfviewMap.WalkRouteOverlay;
import activity.com.myappdata.uiutils.selfviewMap.dialog.LoadDialog;
import activity.com.myappdata.uiutils.selfviewMap.statusbar.StatusBarUtil;
import activity.com.myappdata.util.ToastUtil;
import activity.com.myappdata.util.traffictransfer.AMapUtil;

/***
 * 各种导航条目和地图标点
 */
public class MaPGaoDeActivity extends AppCompatActivity  implements AMap.OnCameraChangeListener,
        AMap.OnMapLoadedListener, OnLocationGetListener, View.OnClickListener, RouteTask.OnRouteCalculateListener,
        AMap.OnMapTouchListener, RouteSearch.OnRouteSearchListener,AMap.OnMapClickListener,AMap.InfoWindowAdapter{
    public static final String TAG = "MainActivity";
    public static final int REQUEST_CODE = 1;
    //地图view
    MapView mMapView = null;
    //初始化地图控制器对象
    AMap aMap;
    //刷新定位
    ImageView iv_refresh, iv_scan_code;

    //定位
    private LocationTask mLocationTask;
    //逆地理编码功能
    private RegeocodeTask mRegeocodeTask;
    //绘制点标记
    private Marker mPositionMark, mInitialMark,tempMark;//可移动、圆点、点击
    //初始坐标、移动记录坐标
    private LatLng mStartPosition,mRecordPositon;
    //默认添加一次
    private boolean mIsFirst = true;
    //就第一次显示位置
    private boolean mIsFirstShow = true;

    private LatLng initLocation;

    // 一定需要对应的bean
//    private ActivityMainBinding mBinding;
    private ActivityMaPGaoDeBinding mBinding;

    private NavigationView navView;
    private DrawerLayout drawerLayout;
    private FrameLayout llTitleMenu;
    private Toolbar toolbar;

    private ValueAnimator animator = null;//坐标动画
    private BitmapDescriptor initBitmap,moveBitmap,smallIdentificationBitmap,bigIdentificationBitmap;//定位圆点、可移动、所有标识（车）
    private RouteSearch mRouteSearch;

    private WalkRouteResult mWalkRouteResult;
    private LatLonPoint mStartPoint = null;//起点，116.335891,39.942295
    private LatLonPoint mEndPoint = null;//终点，116.481288,39.995576
    private final int ROUTE_TYPE_WALK = 3;
    private boolean isClickIdentification = false;
    WalkRouteOverlay walkRouteOverlay;//路线
    private String [] time;
    private String distance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_ma_p_gao_de);
        initId();
        // 顶部状态栏设定颜色（包含5.0 6.0 7.0）//  颜色直接定值
        StatusBarUtil.setColorNoTranslucentForDrawerLayout(MaPGaoDeActivity.this, drawerLayout,
                getResources().getColor(R
                        .color.colorPrimaryDark));
        initToolbar();
        initDrawerLayout();
        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.map);
        iv_refresh = (ImageView) findViewById(R.id.iv_refresh);
        iv_refresh.setOnClickListener(this);
        iv_scan_code = (ImageView) findViewById(R.id.iv_scan_code);
        iv_scan_code.setOnClickListener(this);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView.onCreate(savedInstanceState);
        initBitmap();
        initAMap();
        initLocation();
        RouteTask.getInstance(getApplicationContext())
                .addRouteCalculateListener(this);
        Log.e(TAG, "sha1" + Sha1.sHA1(this));
    }
    /**
     * 初始化定位
     */
    private void initLocation() {
        mLocationTask = LocationTask.getInstance(getApplicationContext());
        mLocationTask.setOnLocationGetListener(this);
        mRegeocodeTask = new RegeocodeTask(getApplicationContext());
    }
    /**
     * 初始化地图控制器对象
     */
    private void initAMap() {
        if (aMap == null) {
            aMap = mMapView.getMap();
            mRouteSearch = new RouteSearch(this);
            mRouteSearch.setRouteSearchListener(this);
            aMap.getUiSettings().setZoomControlsEnabled(false);
            aMap.getUiSettings().setGestureScaleByMapCenter(true);
//            aMap.getUiSettings().setScrollGesturesEnabled(false);
            aMap.setOnMapTouchListener(this);
            aMap.setOnMapLoadedListener(this);
            aMap.setOnCameraChangeListener(this);
            aMap.setOnMapClickListener(this);
            // 绑定 Marker 被点击事件
            aMap.setOnMarkerClickListener(markerClickListener);
            aMap.setInfoWindowAdapter(this);// 设置自定义InfoWindow样式
        }
    }
    // 定义 Marker 点击事件监听
    AMap.OnMarkerClickListener markerClickListener = new AMap.OnMarkerClickListener() {

        // marker 对象被点击时回调的接口
        // 返回 true 则表示接口已响应事件，否则返回false
        @Override
        public boolean onMarkerClick(final Marker marker) {
            Log.e(TAG, "点击的Marker");
            Log.e(TAG, marker.getPosition() + "");
            isClickIdentification = true;
            if(tempMark!=null)
            {
                tempMark.setIcon(smallIdentificationBitmap);
                walkRouteOverlay.removeFromMap();
                tempMark = null;
            }
            startAnim(marker);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(300);
                        tempMark = marker;
                        Log.e(TAG,mPositionMark.getPosition().latitude+"==="+mPositionMark.getPosition().longitude);
                        mStartPoint = new LatLonPoint(mRecordPositon.latitude, mRecordPositon.longitude);
                        mPositionMark.setPosition(mRecordPositon);
                        mEndPoint =new LatLonPoint(marker.getPosition().latitude,marker.getPosition().longitude);
                        marker.setIcon(bigIdentificationBitmap);
                        marker.setPosition(marker.getPosition());
                        searchRouteResult(ROUTE_TYPE_WALK, RouteSearch.WalkDefault);
//                        Intent intent = new Intent(MainActivity.this, RouteActivity.class);
//                        intent.putExtra("start_lat", mPositionMark.getPosition().latitude);
//                        intent.putExtra("start_lng", mPositionMark.getPosition().longitude);
//                        intent.putExtra("end_lat", marker.getPosition().latitude);
//                        intent.putExtra("end_lng", marker.getPosition().longitude);
//                        startActivity(intent);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
            return true;
        }
    };

    private void startAnim(Marker marker) {
        ScaleAnimation anim = new ScaleAnimation(1.0f, 1.3f, 1.0f, 1.3f);
        anim.setDuration(300);
        marker.setAnimation(anim);
        marker.startAnimation();
    }

    /**
     * 开始搜索路径规划方案
     */
    public void searchRouteResult(int routeType, int mode) {
        if (mStartPoint == null) {
            ToastUtil.makeText(this, "定位中，稍后再试...");
            return;
        }
        if (mEndPoint == null) {
            ToastUtil.makeText(this, "终点未设置");
        }
        showDialog();
        final RouteSearch.FromAndTo fromAndTo = new RouteSearch.FromAndTo(
                mStartPoint, mEndPoint);
        if (routeType == ROUTE_TYPE_WALK) {// 步行路径规划
            RouteSearch.WalkRouteQuery query = new RouteSearch.WalkRouteQuery(fromAndTo, mode);
            mRouteSearch.calculateWalkRouteAsyn(query);// 异步路径规划步行模式查询
        }
    }
    private void showDialog()
    {
        LoadDialog loadDialog =  LoadDialog.getInstance();
        loadDialog.setStyle(DialogFragment.STYLE_NORMAL, R.style.load_dialog);
        LoadDialog.getInstance().show(getSupportFragmentManager(),"");
    }
    private void initBitmap()
    {
        initBitmap = BitmapDescriptorFactory
                .fromResource(R.drawable.location_marker);
        moveBitmap = BitmapDescriptorFactory
                .fromResource(R.drawable.icon_loaction_start);
        smallIdentificationBitmap = BitmapDescriptorFactory
                .fromResource(R.drawable.stable_cluster_marker_one_normal);
        bigIdentificationBitmap = BitmapDescriptorFactory
                .fromResource(R.drawable.stable_cluster_marker_one_select);
    }

    private void initId() {
        drawerLayout = mBinding.drawerLayout;
        navView = mBinding.navView;
        toolbar = mBinding.include.toolbar;
        llTitleMenu = mBinding.include.llTitleMenu;
    }

    private void initToolbar()
    {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //去除默认Title显示
            actionBar.setDisplayShowTitleEnabled(false);
        }
    }

    private void initDrawerLayout() {
        navView.inflateHeaderView(R.layout.nav_header_main);//直接引用layout  的布局
        View headerView = navView.getHeaderView(0);
        RelativeLayout rl_header_bg = (RelativeLayout) headerView.findViewById(R.id.rl_header_bg);
        LinearLayout ll_nav_trip = (LinearLayout) headerView.findViewById(R.id.ll_nav_trip);
        LinearLayout ll_nav_money = (LinearLayout) headerView.findViewById(R.id.ll_nav_money);
        LinearLayout ll_nav_message = (LinearLayout) headerView.findViewById(R.id.ll_nav_message);
        LinearLayout ll_nav_guide = (LinearLayout) headerView.findViewById(R.id.ll_nav_guide);
        LinearLayout ll_nav_setting = (LinearLayout) headerView.findViewById(R.id.ll_nav_setting);
        rl_header_bg.setOnClickListener(this);
        ll_nav_trip.setOnClickListener(this);
        ll_nav_money.setOnClickListener(this);
        ll_nav_message.setOnClickListener(this);
        ll_nav_guide.setOnClickListener(this);
        ll_nav_setting.setOnClickListener(this);
        llTitleMenu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_title_menu:// 开启菜单
                drawerLayout.openDrawer(GravityCompat.START);
                // 关闭
//                drawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.rl_header_bg:
                mBinding.drawerLayout.closeDrawer(GravityCompat.START);
                mBinding.drawerLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
//                        PersonalInformationActivity.start(MainActivity.this);
                    }
                }, 360);
                break;
            case R.id.ll_nav_trip:
                mBinding.drawerLayout.closeDrawer(GravityCompat.START);
                mBinding.drawerLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
//                        MyTripActivity.start(MainActivity.this);
                    }
                }, 360);
                break;
            case R.id.ll_nav_money:
                mBinding.drawerLayout.closeDrawer(GravityCompat.START);
                mBinding.drawerLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                      //  MyWalletActivity.start(MainActivity.this);
                    }
                }, 360);
                break;
            case R.id.ll_nav_message:
                mBinding.drawerLayout.closeDrawer(GravityCompat.START);
                mBinding.drawerLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
//                        MyMessageActivity.start(MainActivity.this);
                    }
                }, 360);
                break;
            case R.id.ll_nav_guide:
                mBinding.drawerLayout.closeDrawer(GravityCompat.START);
                mBinding.drawerLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
//                        UserKnowActivity.start(MainActivity.this);
                    }
                }, 360);
                break;
            case R.id.ll_nav_setting:
                mBinding.drawerLayout.closeDrawer(GravityCompat.START);
                mBinding.drawerLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
//                        LoginActivity.start(MainActivity.this);
                    }
                }, 360);
                break;
            case R.id.iv_refresh:
//                clickRefresh();
                break;
            case R.id.iv_scan_code:
//                Intent intent = new Intent(MainActivity.this, CaptureActivity.class);
//                startActivityForResult(intent, REQUEST_CODE);
                break;
        }
    }
    private void clickMap()
    {
        clickInitInfo();
        if(mRecordPositon!=null) {
            //设置缩放级别
            CameraUpdate cameraUpate = CameraUpdateFactory.newLatLngZoom(
                    mRecordPositon, 17f);
            aMap.animateCamera(cameraUpate);
        }
    }

    private void clickInitInfo()
    {
        isClickIdentification = false;
        if(null!=tempMark) {
            tempMark.setIcon(smallIdentificationBitmap);
            tempMark.hideInfoWindow();
            tempMark = null;
        }
        if(null!=walkRouteOverlay) {
            walkRouteOverlay.removeFromMap();
        }
    }

    @Override
    public View getInfoWindow(Marker marker) {

        Log.e(TAG,"getInfoWindow");
        View infoWindow = getLayoutInflater().inflate(
                R.layout.info_window, null);
        render(marker,infoWindow);
        return infoWindow;
    }
    /**
     * 自定义infowinfow窗口  显示 步行的时间和距离
     */
    public void render(Marker marker, View view) {
        TextView tv_time = (TextView) view.findViewById(R.id.tv_time);
        TextView tv_time_info = (TextView)view.findViewById(R.id.tv_time_info);
        TextView tv_distance =(TextView) view.findViewById(R.id.tv_distance);
        tv_time.setText(time[0]);
        tv_time_info.setText(time[1]);
        tv_distance.setText(distance);
    }
    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }

    @Override
    public void onCameraChange(CameraPosition cameraPosition) {

    }

    @Override
    public void onCameraChangeFinish(CameraPosition cameraPosition) {
        Log.e(TAG, "onCameraChangeFinish" + cameraPosition.target);
        if(!isClickIdentification) {
            mRecordPositon = cameraPosition.target;
        }
        mStartPosition = cameraPosition.target;
        mRegeocodeTask.setOnLocationGetListener(this);
        mRegeocodeTask
                .search(mStartPosition.latitude, mStartPosition.longitude);
//        Utils.removeMarkers();
        if(mIsFirst) {
            Utils.addEmulateData(aMap, mStartPosition);
            iv_refresh.setVisibility(View.VISIBLE);
            iv_scan_code.setVisibility(View.VISIBLE);
            createInitialPosition(cameraPosition.target.latitude, cameraPosition.target.longitude);
            createMovingPosition();
            mIsFirst = false;
        }
        if (mInitialMark != null) {
            mInitialMark.setToTop();
        }
        if (mPositionMark != null) {
            mPositionMark.setToTop();
            if(!isClickIdentification) {
                animMarker();
            }
        }
    }


    /****
     *    地图动画的图层
     */
    private void animMarker() {
        if (animator != null) {
            animator.start();
            return;
        }
        animator = ValueAnimator.ofFloat(mMapView.getHeight()/2, mMapView.getHeight()/2 - 30);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.setDuration(150);
        animator.setRepeatCount(1);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Float value = (Float) animation.getAnimatedValue();
                mPositionMark.setPositionByPixels(mMapView.getWidth() / 2, Math.round(value));
            }
        });
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mPositionMark.setIcon(moveBitmap);
            }
        });
        animator.start();
    }


    /**
     * 创建移动位置图标
     */
    private void createMovingPosition() {
        MarkerOptions markerOptions = new MarkerOptions();
//        markerOptions.setFlat(true);
//        markerOptions.anchor(0.5f, 0.5f);
        markerOptions.position(new LatLng(0, 0));
        markerOptions.icon(moveBitmap);
        mPositionMark = aMap.addMarker(markerOptions);
        mPositionMark.setPositionByPixels(mMapView.getWidth() / 2,
                mMapView.getHeight() / 2);
        mPositionMark.setClickable(false);
    }

    /**
     * 创建初始位置图标
     */
    private void createInitialPosition(double lat, double lng) {
        MarkerOptions markerOptions = new MarkerOptions();
//        markerOptions.setFlat(true);
        markerOptions.anchor(0.5f, 0.5f);
        markerOptions.position(new LatLng(lat, lng));
        markerOptions.icon(initBitmap);
        mInitialMark = aMap.addMarker(markerOptions);
        mInitialMark.setClickable(false);
    }

    @Override
    public void onMapClick(LatLng latLng) {

            clickMap();
    }
    private void clickRefresh()
    {
        clickInitInfo();
        if(initLocation!=null) {
            CameraUpdate cameraUpate = CameraUpdateFactory.newLatLngZoom(
                    initLocation, 17f);
            aMap.animateCamera(cameraUpate);
        }
    }
    @Override
    public void onMapLoaded() {

    }

    @Override
    public void onTouch(MotionEvent motionEvent) {
        if(motionEvent.getPointerCount()>=2)
        {
            aMap.getUiSettings().setScrollGesturesEnabled(false);
        }else
        {
            aMap.getUiSettings().setScrollGesturesEnabled(true);
        }
    }

    @Override
    public void onBusRouteSearched(BusRouteResult busRouteResult, int i) {

    }

    @Override
    public void onDriveRouteSearched(DriveRouteResult driveRouteResult, int i) {

    }
    private void endAnim() {
        if (animator != null && animator.isRunning())
            animator.end();
    }
    @Override
    public void onWalkRouteSearched(WalkRouteResult result, int errorCode) {

        LoadDialog.getInstance().dismiss();
        if (errorCode == AMapException.CODE_AMAP_SUCCESS) {
            if (result != null && result.getPaths() != null) {
                if (result.getPaths().size() > 0) {
                    mWalkRouteResult = result;
                    final WalkPath walkPath = mWalkRouteResult.getPaths()
                            .get(0);
                    walkRouteOverlay = new WalkRouteOverlay(
                            this, aMap, walkPath,
                            mWalkRouteResult.getStartPos(),
                            mWalkRouteResult.getTargetPos());
                    walkRouteOverlay.removeFromMap();
                    walkRouteOverlay.addToMap();
                    walkRouteOverlay.zoomToSpan();
                    int dis = (int) walkPath.getDistance();
                    int dur = (int) walkPath.getDuration();
                    time = AMapUtil.getFriendlyTimeArray(dur);
                    distance = AMapUtil.getFriendlyLength(dis);
                    String des = AMapUtil.getFriendlyTime(dur)+"("+AMapUtil.getFriendlyLength(dis)+")";
                    tempMark.setTitle(des);
                    tempMark.showInfoWindow();
                    Log.e(TAG,des);
                } else if (result != null && result.getPaths() == null) {
                    ToastUtil.makeText(this, "没结果");
                }
            } else {
                ToastUtil.makeText(this,"没结果");
            }
        } else {
            ToastUtil.makeText(this.getApplicationContext(), "没结果");
        }



    }


    @Override
    public void onRideRouteSearched(RideRouteResult rideRouteResult, int i) {

    }

    @Override
    public void onLocationGet(PositionEntity entity) {

    }

    @Override
    public void onRegecodeGet(PositionEntity entity) {

    }

    @Override
    public void onRouteCalculate(float cost, float distance, int duration) {

    }





    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        Utils.removeMarkers();
        mMapView.onDestroy();
        mLocationTask.onDestroy();
        RouteTask.getInstance(getApplicationContext()).removeRouteCalculateListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
        if (mInitialMark != null) {
            mInitialMark.setToTop();
        }
        if (mPositionMark != null) {
            mPositionMark.setToTop();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

}