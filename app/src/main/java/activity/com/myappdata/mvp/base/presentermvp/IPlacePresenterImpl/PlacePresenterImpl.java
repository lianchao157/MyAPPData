package activity.com.myappdata.mvp.base.presentermvp.IPlacePresenterImpl;

import android.annotation.SuppressLint;
import android.os.Message;
import android.util.Log;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import activity.com.myappdata.BuildConfig;
import activity.com.myappdata.mvp.base.modelmvp.mvploginentity.mvpuserinfolitit.UserinfoBywebData;
import activity.com.myappdata.mvp.base.presentermvp.IPlacePresenter;
import activity.com.myappdata.mvp.base.utilsmvp.Constants;
import activity.com.myappdata.mvp.base.view.IProvinceCallbask;
import activity.com.myappdata.util.LogUtil;

//项目地址
//        D:\Cenlent_Code\项目\downloadAndroid\AndroidMVPObject-master\AndroidMVPObject-master\app\src\main\java\com\markshuai\androidmvpobject\apkupload
public class PlacePresenterImpl implements IPlacePresenter {
    private static final String TAG = "PlacePresenterImpl";
    private List<UserinfoBywebData> userinfolist = new ArrayList<UserinfoBywebData>();
    String  netResult="";
    @Override
    public String getProvinList() {
        String url = Constants.BASE_URL1;

//        RetrofitFactory.getRetrofit().create(Api.class).getVideoContent(url)
//                .subscribeOn(Schedulers.io())
//                .map(videoContentBean -> {
//                    VideoContentBean.DataBean.VideoListBean videoList = videoContentBean.getData().getVideo_list();
//                    if (videoList.getVideo_3() != null) {
//                        String base64 = videoList.getVideo_3().getMain_url();
//                        String url1 = (new String(Base64.decode(base64.getBytes(), Base64.DEFAULT)));
//                        Log.d(TAG, "getVideoUrls: " + url1);
//                        return url1;
//                    }
//
//                    if (videoList.getVideo_2() != null) {
//                        String base64 = videoList.getVideo_2().getMain_url();
//                        String url1 = (new String(Base64.decode(base64.getBytes(), Base64.DEFAULT)));
//                        Log.d(TAG, "getVideoUrls: " + url1);
//                        return url1;
//                    }
//
//                    if (videoList.getVideo_1() != null) {
//                        String base64 = videoList.getVideo_1().getMain_url();
//                        String url1 = (new String(Base64.decode(base64.getBytes(), Base64.DEFAULT)));
//                        Log.d(TAG, "getVideoUrls: " + url1);
//                        return url1;
//                    }
//                    return null;
//                })
//                .observeOn(AndroidSchedulers.mainThread())
//                .as(view.bindAutoDispose())
//                .subscribe(s -> {
//                    view.onSetVideoPlay(s);
//                    view.onHideLoading();
//                }, throwable -> {
//                    view.onShowNetError();
//                    view.onHideLoading();
//                    ErrorAction.print(throwable);
//                });
        netResult="";
        OkHttpClient okHttpClient = new OkHttpClient();
//oki
        String mToken = "";
        RequestBody requestBody = new FormEncodingBuilder()
//                .add("reportid", reportid)
//                .add("reporttype", String.valueOf(reporttype))
//                .add("type", String.valueOf(type))
                .add("page", String.valueOf(1))
                .add("limit", String.valueOf(10))
                .build();
        final Request request = new Request.Builder()
                .addHeader("x-authorization", mToken)
                .url("http://10.17.4.119:8988/selectAllbytype")
//        http://localhost:8988/selectAllbytype?page=2&limit=5
                .put(requestBody)
                .build();

        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @SuppressLint("LongLogTag")
            @Override
            public void onFailure(Request request, IOException e) {
                if(BuildConfig.DEBUG){
                    LogUtil.e(TAG,  "onFailure"+e);
                    Message message = Message.obtain();
                    message.what = 0;
                }

            }

            @SuppressLint("LongLogTag")
            @Override
            public void onResponse(Response response) throws IOException {
                LogUtil.e(TAG, response.toString());
                String result = response.body().string();
                try {
                    if (result == null || result.equals("")) {
//                        mHandler.sendEmptyMessage(0);
                        LogUtil.e(TAG,  "result null"+result);
                    }else{
                        JSONObject jsonObject = new JSONObject(result);
                        JSONObject jsonObject1 = (JSONObject) jsonObject.get("data");
                        Log.e(TAG, "连接" + jsonObject1);
                        JSONArray jsonArray = jsonObject1.getJSONArray("data");
                        userinfolist.clear();
                        for (int i = 0; i < jsonArray.length(); i++) {
                            UserinfoBywebData userinfoBywebData = new UserinfoBywebData();
                            userinfoBywebData.setUsername(jsonArray.getJSONObject(i).getString("username"));
                            userinfoBywebData.setUsertel(jsonArray.getJSONObject(i).getString("usertel"));
                            userinfoBywebData.setUsertype(jsonArray.getJSONObject(i).getString("usertype"));
                            userinfolist.add(userinfoBywebData);
                        }
                        if (result == null || result.equals("")) {
//                            mHandler.sendEmptyMessage(0);
//                            return;
                            netResult="网路失败";
                        } else {
//                            Message message = Message.obtain();
//                            message.arg1 = jsonObject1.getInt("count");
                            Log.e(TAG, "连接::::::::::" + jsonObject1.getInt("count"));
                            Log.e(TAG, "连接::::::::::" + userinfolist.size());
//                            message.what = 1;
//                            mHandler.sendMessage(message);
                            netResult=result;
                        }

                    }
                } catch(JSONException e){
                    e.printStackTrace();
                    LogUtil.e(TAG,  "JSONException"+e);

                }
            }



        });
        if (null==netResult||netResult.equals("")){
            return "网路请求失败";
        }else{
            return netResult;
        }

    }

    @Override
    public void registerViewCallback(IProvinceCallbask callback) {

    }

    @Override
    public void unRegisterViewCallback(IProvinceCallbask callback) {

    }
}
