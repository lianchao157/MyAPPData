package activity.com.myappdata.mvp.base.modelmvp;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import activity.com.myappdata.api.retorfitpack.GetRequest_Interface;
import activity.com.myappdata.mvp.base.Contract.MainContract;
import activity.com.myappdata.mvp.base.modelmvp.GoodInfiLineEntity.GoodinfoLineRoot;
import activity.com.myappdata.mvp.base.modelmvp.GoodInfiLineEntity.PPShopGoodInfo;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 作者：MarkShuai
 * 时间：2017/12/19 14:49
 * 邮箱：MarkShuai@163.com
 * 意图：
 */

public class MainModel implements MainContract.Model {
    String TAG = "MainModel";
    private Handler mHandler = new Handler(Looper.getMainLooper());
    public String netResult1 = "";

    @Override
    public void loginNet(final LoginListener listener) {
        //模拟登陆场景

        Retrofit retrofit = new Retrofit.Builder()
//                    .baseUrl("http://localhost:8888/") // 设置 网络请求 Url
                .baseUrl("http://192.168.1.166:8988") // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        retrofit.create(GetRequest_Interface.class)
                .selectLIneInfo("7978")//注意看，这里返回的对象时什么，和原生的返回不同，也是我们把上面接口改的原因
                //在子线程取数据
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                //在主线程显示ui
                // compile 'io.reactivex.rxjava2:rxandroid:2.0.1'  这个库下才有AndroidSchedulers.mainThread
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e(TAG, "testd=" + d);

                    }

                    public void onNext(ResponseBody responseBody) {
                        try {
                            String netResult = responseBody.string();
                            Log.e(TAG, "testd===" + netResult);
                            netResult1 = netResult;
                            Gson gson = new Gson();

                            GoodinfoLineRoot GoodinfoLineRoot = gson.fromJson(netResult, GoodinfoLineRoot.class);
                            List<PPShopGoodInfo> data = GoodinfoLineRoot.getData();
                            Log.e(TAG, "testd===" + data.size());

                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "testd====" + e);
                    }

                    public void onComplete() {
                        Log.e(TAG, "testdonComplete=======");
                    }

                });
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            mHandler.post(new Runnable() {
                @Override
                public void run() {


                    listener.success(netResult1);
                }
            });

        }).start();
    }
}
