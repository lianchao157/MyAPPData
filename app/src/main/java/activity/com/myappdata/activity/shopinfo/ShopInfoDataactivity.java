package activity.com.myappdata.activity.shopinfo;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;
import java.util.concurrent.TimeUnit;

import activity.com.myappdata.R;
import activity.com.myappdata.api.retorfitpack.GetRequest_Interface;
import activity.com.myappdata.bean.FindFragmentMenumBase;
import activity.com.myappdata.bean.FindFragmetnMenuTitleEntity;
import activity.com.myappdata.entity.ShopCarInfoenTity;
import activity.com.myappdata.fragment.FindFragment;
import activity.com.myappdata.util.ToastUtils;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/****
 * 商品的详情
 */
public class ShopInfoDataactivity extends AppCompatActivity {
    private static final String TAG = "ShopInfoDataactivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_info_dataactivity);

        initview();
        loadData();
    }

    /***
     * 创建这个购物车
     */
    private void loadData() {
        ShopCarInfoenTity shopCarInfoenTity = new ShopCarInfoenTity();// 创建实体类
        new Handler().postDelayed(() -> {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(client)
                    .baseUrl("http://192.168.1.6:8988") // 设置 网络请求 Url  http://localhost:8988
                    .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                    .build();

            // 步骤5:创建 网络请求接口 的实例
            GetRequest_Interface request = retrofit.create(GetRequest_Interface.class);

            //对 发送请求 进行封装
            Call<ShopCarInfoenTity> call = request.getDataByPost(shopCarInfoenTity);
            call.enqueue(new Callback<ShopCarInfoenTity>() {
                @Override
                public void onResponse(Call<ShopCarInfoenTity> call, Response<ShopCarInfoenTity> response) {
                    if (null == response || response.equals("")) {
                        Log.e(TAG, "我想我是空");
                        ToastUtils.show(ShopInfoDataactivity.this, "" + response);
                    } else {
                        String msg = response.message();
                        ShopCarInfoenTity s = response.body();
                    }
                }

                @Override
                public void onFailure(Call<ShopCarInfoenTity> call, Throwable t) {
                    Log.e(TAG, "失败：：：：s" + t);
                }
            });


        }, 2000);

    }

    private static final OkHttpClient client = new OkHttpClient.Builder().
            connectTimeout(60, TimeUnit.SECONDS).
            readTimeout(60, TimeUnit.SECONDS).
            writeTimeout(60, TimeUnit.SECONDS).build();

    private void initview() {

    }
}