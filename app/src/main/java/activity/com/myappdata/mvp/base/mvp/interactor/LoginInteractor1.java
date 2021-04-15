package activity.com.myappdata.mvp.base.mvp.interactor;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import java.util.List;
import java.util.concurrent.TimeUnit;

import activity.com.myappdata.api.retorfitpack.GetRequest_Interface;
import activity.com.myappdata.api.retorfitpack.entity.Data;
import activity.com.myappdata.api.retorfitpack.entity.Root;
import activity.com.myappdata.mvp.base.Contract.LoginContract;
import activity.com.myappdata.mvp.base.modelmvp.entity.UserInfo;
import activity.com.myappdata.mvp.base.modelmvp.mvploginentity.MVPRoot;
import activity.com.myappdata.util.ToastUtil;
import activity.com.myappdata.util.ToastUtils;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/***
 * 一系列的监听代码
 */
public class LoginInteractor1 implements LoginContract.Interactor {
    private static final String TAG = "LoginInteractor1登陆服务类代码";

    @Override
    public void doLogin(String phone, String password, LoginCallback callback) {

    }

    @Override
    public boolean isLogin() {
        return false;
    }

    @Override
    public UserInfo getLoginUser() {
        return null;
    }

    public interface OnLoginFinishedListener {
        void onUsernameError();

        void onPasswordError();

        void onSuccess();

        void LoginFaile();

        void showFaile(String throwable);
    }

    ////////////---------------------------------底部老版本

    // 网络请求超时代码
    private static final OkHttpClient client = new OkHttpClient.Builder().
            connectTimeout(60, TimeUnit.SECONDS).
            readTimeout(60, TimeUnit.SECONDS).
            writeTimeout(60, TimeUnit.SECONDS).build();

    public void login(final String username, final String password, final OnLoginFinishedListener listener) {
        // Mock login. I'm creating a handler to delay the answer a couple of seconds
        new Handler().postDelayed(() -> {
            if (TextUtils.isEmpty(username)) {
                listener.onUsernameError();
                return;
            }
            if (TextUtils.isEmpty(password)) {
                listener.onPasswordError();
                return;
            }

            Retrofit retrofit = new Retrofit.Builder()
//                    .baseUrl("http://localhost:8888/") // 设置 网络请求 Url
//            http://localhost:8988/pplogin?username=1&userpassword=1
                    .client(client)
                    .baseUrl("http://192.168.1.167:8988") // 设置 网络请求 Url
                    .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                    .build();

            // 步骤5:创建 网络请求接口 的实例
            GetRequest_Interface request = retrofit.create(GetRequest_Interface.class);

            //对 发送请求 进行封装
            Call<MVPRoot> call = request.getPPMVPlog(username, password);

            //步骤6:发送网络请求(异步)
            call.enqueue(new Callback<MVPRoot>() {

                //请求成功时候的回调
                @Override
                public void onResponse(Call<MVPRoot> call, Response<MVPRoot> response) {
                    //请求处理,输出结果

                    System.out.println("连接" + response.body());
                    Log.e(TAG, response.body() + "连接");

                    String msg = response.body().getMsg();
                    activity.com.myappdata.mvp.base.modelmvp.mvploginentity.Data ld = response.body().getData();
                    Log.e(TAG, ld + "ld");
                    if (null == ld || ld.equals("")) {
                        listener.LoginFaile();
                        listener.showFaile(msg);
                    } else {

                        listener.onSuccess();

                    }

                }

                //请求失败时候的回调
                @Override
                public void onFailure(Call<MVPRoot> call, Throwable throwable) {
                    Log.e(TAG, "连接失败" + throwable);
                    listener.LoginFaile();
                    listener.showFaile(throwable+"");


                }
            });


        }, 2000);


    }
}
