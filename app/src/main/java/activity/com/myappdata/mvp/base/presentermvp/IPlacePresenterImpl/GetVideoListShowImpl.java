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

import activity.com.myappdata.mvp.base.modelmvp.mvploginentity.mvpuserinfolitit.UserinfoBywebData;
import activity.com.myappdata.mvp.base.presentermvp.GetVideoListshow;
import activity.com.myappdata.mvp.base.utilsmvp.Constants;
import activity.com.myappdata.mvp.base.view.IProvinceCallbask;

public  class GetVideoListShowImpl implements GetVideoListshow {
    private static final String TAG = "PlacePresenterImpl";
    private List<UserinfoBywebData> userinfolist = new ArrayList<UserinfoBywebData>();

    /**
     * 获取数据
     * @return
     */
    @Override
    public String getProvinList() {
        String url = Constants.BASE_URL1;
        String result = "";
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

        OkHttpClient okHttpClient = new OkHttpClient();
//oki
        String mToken = "";
        RequestBody requestBody = new FormEncodingBuilder()
//                .add("reportid", reportid)
//                .add("reporttype", String.valueOf(reporttype))
//                .add("type", String.valueOf(type))
//                .add("page", String.valueOf(1))
//                .add("limit", String.valueOf(10))
                .build();
        final Request request = new Request.Builder()
                .addHeader("x-authorization", mToken)
                .url("http://192.168.1.5:8988/selectAllbytype")
//        http://localhost:8988/selectAllbytype?page=2&limit=5
                .put(requestBody)
                .build();

        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @SuppressLint("LongLogTag")
            @Override
            public void onFailure(Request request, IOException e) {

                Log.e(TAG, e + "连接");
            }

            @SuppressLint("LongLogTag")
            @Override
            public void onResponse(Response response) throws IOException {
                Log.e(TAG, response.toString());
                String result = response.body().string();
            }



        });

        return result;
    }

    @Override
    public void registerViewCallback(IProvinceCallbask callback) {

    }

    @Override
    public void unRegisterViewCallback(IProvinceCallbask callback) {

    }
}
