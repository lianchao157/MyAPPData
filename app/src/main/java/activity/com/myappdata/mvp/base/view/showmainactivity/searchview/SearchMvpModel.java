package activity.com.myappdata.mvp.base.view.showmainactivity.searchview;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;

import java.util.List;
import java.util.concurrent.TimeUnit;

import activity.com.myappdata.api.retorfitpack.GetRequest_Interface;
import activity.com.myappdata.mvp.base.utilsmvp.ContentManager;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class SearchMvpModel implements SearchMvpContract.Model {

    @Override
    public void Searchinfo(Context context) {
        Intent intent = new Intent();
        Uri content_url = Uri.parse(ContentManager.APK_URLsearch);
        intent.setData(content_url);
        context.startActivity(intent);
    }

    @Override
    public void SHyouLike(Context context) {
        Intent intent = new Intent();
//        Uri content_url = Uri.parse(ContentManager.APK_URLyoulike);
//        intent.setData(content_url);
//        context.startActivity(intent);
    }

    @Override
    public void setItems(List<String> items) {
        //  做数据处理
        final OkHttpClient client = new OkHttpClient.Builder().
                connectTimeout(60, TimeUnit.SECONDS).
                readTimeout(60, TimeUnit.SECONDS).
                writeTimeout(60, TimeUnit.SECONDS).build();


        /***
         * 获取数据顶部菜单
         */
            new Handler().postDelayed(() -> {
                Retrofit retrofit = new Retrofit.Builder()
                        .client(client)
                        .baseUrl(ContentManager.myappdata_url) // 设置 网络请求 Url  http://localhost:8988
                        .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                        .build();

                // 步骤5:创建 网络请求接口 的实例
                GetRequest_Interface request = retrofit.create(GetRequest_Interface.class);

                //对 发送请求 进行封装
                Call<HotMenums> call = request.GetHotMenums();
                call.enqueue(new Callback<HotMenums>() {
                    @Override
                    public void onResponse(Call<HotMenums> call, Response<HotMenums> response) {
                        if (null == response || response.equals("")) {
                            Log.e("searchmvpmodle", "我想我是空"+response);
                        } else {
                            String msg = response.message();
                            List<HotMenums> resultlist = (List<HotMenums>) response.body();
                            for (int i = 0; i < resultlist.size(); i++) {
                                Log.e("searchmvpmodle", "获取数据代码state" + resultlist.get(i).getHotmenumsimage());
                                Log.e("searchmvpmodle", "获取数据代码mage" + resultlist.get(i).getHotmenumsid());
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<HotMenums> call, Throwable t) {
                        Log.e("searchmvpmodle", "失败：：：：s" + t);
                    }
                });
            }, 2000);
        }

    @Override
    public void showMessage(String message) {

    }

}
