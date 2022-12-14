package activity.com.myappdata.util.daohang;

import com.amap.api.services.poisearch.PoiSearch;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.services.core.PoiItem;
import java.util.ArrayList;
import java.util.List;
/**
 * Poi图层类。在高德地图API里，如果要显示Poi，可以用此类来创建Poi图层。如不满足需求，也可以自己创建自定义的Poi图层。
 *
 * @since V2.1.0
 */
public  class PoiOverlay {

    public PoiOverlay(List<PoiItem> pois, AMap mAap) {
        mPois = pois;
        this.mAMap = mAap;
    }

    private List<PoiItem> mPois;
    private AMap mAMap;
    private ArrayList<Marker> mPoiMarks = new ArrayList<Marker>();

    public PoiOverlay(AMap mMap, ArrayList<PoiItem> pois) {
        mPois = pois;
        this.mAMap = mMap;
    }


    /***
     * 添加marker到图层
     */
public void addToMap(){
    for (int i=0;i<mPois.size();i++){
        Marker marker = mAMap.addMarker(getMarkerOptions(i));
        marker.setObject(i);
        mPoiMarks.add(marker);
    }
}
    /**
     * 去掉PoiOverlay上所有的Marker。
     *
     * @since V2.1.0
     */
    public void removeFromMap() {
        for (Marker mark : mPoiMarks) {
            mark.remove();
        }
    }

    /**
     * 移动镜头到当前的视角。
     *
     * @since V2.1.0
     */
    public void zoomToSpan() {
        try {
            if (mPois != null && mPois.size() > 0) {
                if (mAMap == null)
                    return;
                if (mPois.size() == 1) {
                    mAMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(mPois.get(0).getLatLonPoint().getLatitude(),
                            mPois.get(0).getLatLonPoint().getLongitude()), 18f));
                } else {
                    LatLngBounds bounds = getLatLngBounds();
                    mAMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 5));
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    private LatLngBounds getLatLngBounds() {
        LatLngBounds.Builder b = LatLngBounds.builder();
        for (int i = 0; i < mPois.size(); i++) {
            b.include(new LatLng(mPois.get(i).getLatLonPoint().getLatitude(),
                    mPois.get(i).getLatLonPoint().getLongitude()));
        }
        return b.build();
    }

    private MarkerOptions getMarkerOptions(int index) {
        return new MarkerOptions()
                .position(
                        new LatLng(mPois.get(index).getLatLonPoint()
                                .getLatitude(), mPois.get(index)
                                .getLatLonPoint().getLongitude()))
                .title(getTitle(index)).snippet(getSnippet(index))
                .icon(getBitmapDescriptor(index));
    }

    /**
     * 给第几个Marker设置图标，并返回更换图标的图片。如不用默认图片，需要重写此方法。
     *
     * @param index 第几个Marker。
     * @return 更换的Marker图片。
     * @since V2.1.0
     */
    protected BitmapDescriptor getBitmapDescriptor(int index) {
        return null;
    }

    /**
     * 返回第index的Marker的标题。
     *
     * @param index 第几个Marker。
     * @return marker的标题。
     * @since V2.1.0
     */
    protected String getTitle(int index) {
        return mPois.get(index).getTitle();
    }

    /**
     * 返回第index的Marker的详情。
     *
     * @param index 第几个Marker。
     * @return marker的详情。
     * @since V2.1.0
     */
    protected String getSnippet(int index) {
        return mPois.get(index).getSnippet();
    }

    public float getDistance(int index) {
        return mPois.get(index).getDistance();
    }

    /**
     * 从marker中得到poi在list的位置。
     *
     * @param marker 一个标记的对象。
     * @return 返回该marker对应的poi在list的位置。
     * @since V2.1.0
     */
    public int getPoiIndex(Marker marker) {
        for (int i = 0; i < mPoiMarks.size(); i++) {
            if (mPoiMarks.get(i).equals(marker)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 返回第index的poi的信息。
     *
     * @param index 第几个poi。
     * @return poi的信息。poi对象详见搜索服务模块的基础核心包（com.amap.api.services.core）中的类 <strong><a href="../../../../../../Search/com/amap/api/services/core/PoiItem.html" title="com.amap.api.services.core中的类">PoiItem</a></strong>。
     * @since V2.1.0
     */
    public PoiItem getPoiItem(int index) {
        if (index < 0 || index >= mPois.size()) {
            return null;
        }
        return mPois.get(index);
    }
}
