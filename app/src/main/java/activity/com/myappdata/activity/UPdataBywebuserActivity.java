package activity.com.myappdata.activity;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
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
import java.util.concurrent.TimeUnit;

import activity.com.myappdata.adapter.UserinfiByMPVAdapter;

import activity.com.myappdata.R;
import activity.com.myappdata.api.Constant;
import activity.com.myappdata.api.retorfitpack.GetRequest_Interface;
import activity.com.myappdata.mvp.base.modelmvp.mvploginentity.mvpuserinfolitit.MVPUserInfo;
import activity.com.myappdata.mvp.base.modelmvp.mvploginentity.mvpuserinfolitit.MVPUserinfoData;
import activity.com.myappdata.mvp.base.modelmvp.mvploginentity.mvpuserinfolitit.UserinfoBywebData;
import activity.com.myappdata.mvp.base.mvp.interactor.LoginInteractor1;
//import okhttp3.Call;
//import okhttp3.Callback;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.RequestBody;
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//import okhttp3.Response;
//import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/***
 * https://blog.csdn.net/cnn1990/article/details/81775209
 * 上拉刷新下拉加载
 */
public class UPdataBywebuserActivity extends AppCompatActivity {
    private UserinfiByMPVAdapter UserinfiByMPVAdapter;
    private SmartRefreshLayout refreshLayout;
    private static final String TAG = "GetUserInfoByMVPInteractor";
    private List<UserinfoBywebData> userinfolist = new ArrayList<UserinfoBywebData>();
    private int page = 1;
    private static final int pageCount = 2;
    private List<MVPUserInfo> MVPUserInfolist = new ArrayList<MVPUserInfo>();
    private RecyclerView mRecycleView;
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    int count = msg.arg1;
                    if (count >= userinfolist.size()) {
                        page += 1;
                    }
                    UserinfiByMPVAdapter.notifyDataSetChanged();
                    break;
                case 0:
                    Toast.makeText(UPdataBywebuserActivity.this, "暂无数据", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updata_bywebuser);
        initview();
        LoadData();
        login();
    }

    private void LoadData() {


    }

    public void login() {
//        // Mock login. I'm creating a handler to delay the answer a couple of seconds
//        new Handler().postDelayed(() -> {
////            if (TextUtils.isEmpty(username)) {
////                listener.onUsernameError();
////                return;
////            }
////            if (TextUtils.isEmpty(password)) {
////                listener.onPasswordError();
////                return;
////            }
//            // 网络请求超时代码
//            final OkHttpClient client = new OkHttpClient.Builder().
//                    connectTimeout(60, TimeUnit.SECONDS).
//                    readTimeout(60, TimeUnit.SECONDS).
//                    writeTimeout(60, TimeUnit.SECONDS).build();
//            Retrofit retrofit = new Retrofit.Builder()
////                    .baseUrl("http://localhost:8888/") // 设置 网络请求 Url
////            http://localhost:8988/pplogin?username=1&userpassword=1
//                    .client(client)
//                    .baseUrl("http://192.168.1.167:8988") // 设置 网络请求 Url
//                    .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
//                    .build();
//
//            // 步骤5:创建 网络请求接口 的实例
//            GetRequest_Interface request = retrofit.create(GetRequest_Interface.class);
//
//            //对 发送请求 进行封装
//            Call<MVPUserInfo> call = request.selectAllbytype(username, password);
//
//            //步骤6:发送网络请求(异步)
//            call.enqueue(new Callback<MVPUserInfo>() {
//
//                //请求成功时候的回调
//                @SuppressLint("LongLogTag")
//                @Override
//                public void onResponse(Call<MVPUserInfo> call, Response<MVPUserInfo> response) {
//                    //请求处理,输出结果
//
//                    System.out.println("连接" + response.body());
//                    Log.e(TAG, response.body() + "连接");
//
//                    String msg = response.body().getMsg();
//                    MVPUserinfoData ld = response.body().getData();
//                    Log.e(TAG, ld + "ld");
//                    if (null == ld || ld.equals("")) {
//                        listener.LoginFaile();
//                        listener.showFaile(msg);
//                    } else {
//
//                        listener.onSuccess();
//
//                    }
//
//                }
//
//                @Override
//                public void onFailure(Call<MVPUserInfo> call, Throwable throwable) {
//                    listener.LoginFaile();
//                    listener.showFaile(throwable+"");
//                }
//
//
//
//            });
//
//
//        }, 2000);
        OkHttpClient okHttpClient = new OkHttpClient();
//oki
        String mToken = "";
        RequestBody requestBody = new FormEncodingBuilder()
//                .add("reportid", reportid)
//                .add("reporttype", String.valueOf(reporttype))
//                .add("type", String.valueOf(type))
                .add("page", String.valueOf(page))
                .add("limit", String.valueOf(pageCount))
                .build();
        final Request request = new Request.Builder()
                .addHeader("x-authorization", mToken)
                .url("http://192.168.1.167:8988/selectAllbytype")
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
                try {
                    if (result == null || result.equals("")) {
                        mHandler.sendEmptyMessage(0);

                }else{
                    JSONObject jsonObject = new JSONObject(result);
                    Log.e(TAG, "连接" + MVPUserInfolist.size());
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
                        mHandler.sendEmptyMessage(0);
                    } else {
                        Message message = Message.obtain();
                        message.arg1 = jsonObject1.getInt("count");
                        Log.e(TAG, "连接::::::::::" + jsonObject1.getInt("count"));
                        Log.e(TAG, "连接::::::::::" + userinfolist.size());
                        message.what = 1;
                        mHandler.sendMessage(message);
                    }
                }
                } catch(JSONException e){
                    e.printStackTrace();
                }
            }



        });
    }


    private void initview() {
        mRecycleView = (RecyclerView) findViewById(R.id.recycle_report);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecycleView.setLayoutManager(linearLayoutManager);

        UserinfiByMPVAdapter = new UserinfiByMPVAdapter(userinfolist);
        mRecycleView.setAdapter(UserinfiByMPVAdapter);
        refreshLayout = (SmartRefreshLayout) findViewById(R.id.refreshLayout);
        refreshLayout.setEnableOverScrollDrag(false);//禁止越界拖动（1.0.4以上版本）
        refreshLayout.setEnableOverScrollBounce(false);//关闭越界回弹功能
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                userinfolist.clear();
                login();
                refreshLayout.finishRefresh();
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(final RefreshLayout refreshlayout) {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        login();
                        refreshlayout.finishLoadMore();
                    }
                }, 2000);
            }
        });

    }

    private void loadReportList() {
    }


}
