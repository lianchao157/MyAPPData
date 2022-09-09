//package activity.com.myappdata.util.map;
//
//import android.app.Activity;
//import android.content.Context;
//import android.graphics.Bitmap;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Message;
//import android.util.Log;
//import android.widget.SimpleAdapter;
//import android.widget.Toast;
//
//import com.amap.api.maps.model.BitmapDescriptor;
//import com.amap.api.maps.model.BitmapDescriptorFactory;
//import com.amap.api.maps.model.LatLng;
//import com.amap.api.maps.model.MarkerOptions;
//import com.amap.api.services.core.AMapException;
//import com.amap.api.services.core.LatLonPoint;
//import com.amap.api.services.core.PoiItem;
//import com.amap.api.services.core.SuggestionCity;
//import com.amap.api.services.poisearch.Photo;
//import com.amap.api.services.poisearch.PoiResult;
//import com.amap.api.services.poisearch.PoiSearch;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * Created by nomad on 18-4-12.
// * poi搜索工具类 ,关键字搜索，周边（位置，半径）搜索
// */
//
//public class SearchUnity implements PoiSearch.OnPoiSearchListener{
//
//    private Map<String, Object> map = new HashMap<>();
//
//    private PoiSearch poiSearch = null;
//    //private int currentPage = 0;
//    private Context context;
//    private Handler handler;
//
//    private double latitude;
//    private double longitude;
//    Bitmap bitmap ;
//    boolean flag = false;
//
//    public void setLatitude(double latitude) {
//        this.latitude = latitude;
//    }
//
//    public void setLongitude(double longitude) {
//        this.longitude = longitude;
//    }
//
//    /*public int getCurrentPage() {
//        return currentPage;
//    }*/
//
//    private PoiSearch.Query  query = null;
//    public Map<String, Object> getMap() {
//        return map;
//    }
//
//
//    //keyWord表示搜索字符串
//    //POI类别code
//    //city code，""代表全国
//    public SearchUnity(Context context, String keyword, String cityCode, Handler handler) {
//        query = new PoiSearch.Query(keyword, cityCode);
//        query.setPageSize(15);// todo 设置每页最多返回多少条poiitem  设置15  单击item直接退出了app
//        this.context = context;
//        this.handler = handler;
//    }
//
//    //周边搜索
//    public SearchUnity(String poiCode, String cityCode, Context context, Handler handler) {
//        query = new PoiSearch.Query("", poiCode, cityCode);
//        query.setPageSize(15);// 设置每页最多返回多少条poiitem
//        this.context = context;
//        this.handler = handler;
//    }
//
//    public void searchAll(int currentPage, boolean flag){   // for searchactivity
//        this.flag = flag;
//        if (flag) {
//            query.setPageNum(currentPage + 1);//设置查询页码
//        } else {
//            query.setPageNum(currentPage - 1);//设置查询页码
//        }
//        query.setPageNum(currentPage);//设置查询页码
//        try {
//            poiSearch = new PoiSearch(context, query);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        poiSearch.setOnPoiSearchListener(this);
//        poiSearch.searchPOIAsyn();
//    }
//
//    //搜索 景点 \ 餐馆 \ hotel \..   for aroundactivity
//    public void searchPoi(double latitude, double longitude, int radius, int currentPage, boolean flag){ //flag=true 标志下一页
//        this.flag = flag;
//        if (flag) {
//            query.setPageNum(currentPage + 1);//设置查询页码
//        } else {
//            query.setPageNum(currentPage - 1);//设置查询页码
//        }
//        try {
//            poiSearch = new PoiSearch(context, query);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        poiSearch.setBound(new PoiSearch.SearchBound(new LatLonPoint(latitude,
//                longitude), radius));//设置周边搜索的中心点以及半径
//        poiSearch.setOnPoiSearchListener(this);
//        poiSearch.searchPOIAsyn();
//    }
//
//    //异步回调的结果
//    @Override
//    public void onPoiSearched(PoiResult poiResult, int i) {
//        new ProgressUnity(context).dissmissProgressDialog();
//        if (i == 1000) {
//            if (poiResult != null && poiResult.getQuery() != null) {// 搜索poi的结果
//                if (poiResult.getQuery().equals(query)) {// 是否是同一条
//                    // 取得搜索到的poiitems有多少页
//                    List<PoiItem> poiItems = poiResult.getPois();// 取得第一页的poiitem数据，页数从数字0开始
//                    List<SuggestionCity> suggestionCities = poiResult.getSearchSuggestionCitys();// 当搜索不到poiitem数据时，会返回含有搜索关键字的城市信息
//                    List<Map<String, Object>> arrayList = new ArrayList<>();; //POI的list集合
//                    List<MarkerOptions> markerOptions = new ArrayList<>(); //POI对应的标记点选项的list集合
//
//                    if (poiItems != null && poiItems.size() > 0) {
//                        //将PoiItems的标题和内容以列表的形式填到适配器，然后给listview显示。
//                        for (int j = 0; j < poiItems.size(); j++) {
//                            double latitude = poiItems.get(j).getLatLonPoint().getLatitude();
//                            double longitude = poiItems.get(j).getLatLonPoint().getLongitude();
//                            String city = poiItems.get(j).getCityName() + "(" + poiItems.get(j).getProvinceCode() + ")";
//                            String address = poiItems.get(j).getSnippet();
//                            String name = poiItems.get(j).getTitle();
//                            address = address + "  " + name;
//                            int distance = poiItems.get(j).getDistance();  //不是周边搜索就返回-1
//                            Log.d("Amap", "SearchUnity->onpoisearched()===name:" + name + ", distance:" + distance /*+ ", url:" + photo.getUrl()*/);
//
//                            List<Photo> list = poiItems.get(j).getPhotos();
//                            //String photoName = "location";
//                            String photoPath = "https://raw.githubusercontent.com/StackTipsLab/Async-ListView-Image-Loader/master/app/src/main/res/drawable/placeholder.png";
//                            if (list.size() > 0) {
//                                Photo photo = list.get(0);
//                                //photoName = photo.getTitle();
//                                photoPath = photo.getUrl();
//                            }
//
//
//                            /**一直在转圈
//                             * todo 新开线程+thread.join(5000)阻塞主线程; 试了 失败
//                             **/
//                             /*try {
//                                bitmap = BitmapUnity.getBitmap(photo.getUrl());
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }*/
//
//                            if (distance != -1) {
//                                city = city + "   距离中心点：" + distance + "米";
//                            }
//                            Log.d("Amap", "SearchActivity->onPoiSearched()=====>" + latitude + " ," + longitude + " ," + city + " ," + address);
//
//                            HashMap<String, Object> hashMap = new HashMap<>();
//                            MarkerOptions markerOption = new MarkerOptions();
//                            hashMap.put("latitude", latitude);
//                            hashMap.put("longitude", longitude);
//                            hashMap.put("city", city);
//                            hashMap.put("address", address);
//                            //hashMap.put("bitmap", bitmap);
//                            //hashMap.put("photoName", photoName);
//                            hashMap.put("photoPath", photoPath);
//                            arrayList.add(hashMap);
//                            markerOption.position(new LatLng(latitude, longitude))
//                                    .title(city)
//                                    .snippet(address)
//                                    .draggable(false)
//                                    .setFlat(false)
//                                    .visible(true)
//                            //        .icon(BitmapDescriptorFactory.defaultMarker());
//                                    .icon(BitmapDescriptorFactory.fromPath(photoPath));
//                            markerOptions.add(markerOption);
//                        }
//                        map.put("arrayList", arrayList);
//                        map.put("markerOptions", markerOptions);
//                        //handler处理异步搜索的结果
//                        Message message = new Message();
//                        message.what = 0;
//                        Bundle bundle = new Bundle();
//                        bundle.putBoolean("flag", flag);
//                        message.setData(bundle);
//                        handler.sendMessage(message);
//                    } else if (suggestionCities != null && suggestionCities.size() > 0) {
//                        new ProgressUnity(context).dissmissProgressDialog();
//                        showSuggestCity(suggestionCities);
//                    } else {
//                        new ProgressUnity(context).dissmissProgressDialog();
//                        Toast.makeText(context, "未找到结果", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            }else{
//                new ProgressUnity(context).dissmissProgressDialog();
//                Toast.makeText(context, "未找到结果", Toast.LENGTH_SHORT).show();
//            }
//        }else{
//            new ProgressUnity(context).dissmissProgressDialog();
//            Toast.makeText(context, "错误代码：" + i, Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    @Override
//    public void onPoiItemSearched(PoiItem poiItem, int i) {
//
//    }
//    /**
//     * 自定义函数，当搜索没有结果时候被调用
//     */
//    private void showSuggestCity(List<SuggestionCity> suggestionCities) {
//        String information = "推荐城市\n";
//        for (int i = 0; i < suggestionCities.size(); i++) {
//            information += "城市名称:" + suggestionCities.get(i).getCityName() + "\n";
//        }
//        Toast.makeText(context, information, Toast.LENGTH_SHORT).show();
//    }
//}
