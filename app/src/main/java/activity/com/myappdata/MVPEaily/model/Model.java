package activity.com.myappdata.MVPEaily.model;

import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import activity.com.myappdata.api.retorfitpack.GetRequest_Interface;
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

public class Model implements IModel {
    private static final String TAG = "Model";

    @Override
    public void getData(final LoadDataCallback callback) {
        //数据获取操作，如数据库查询、网络加载等
        new Thread() {
            @Override
            public void run() {
                try {
                    //模拟耗时操作
                    Thread.sleep(3000);
                    //获取到了数据

                    Retrofit retrofit = new Retrofit.Builder()
//                    .baseUrl("http://localhost:8888/") // 设置 网络请求 Url
                            .baseUrl("http://192.168.1.76:8889") // 设置 网络请求 Url
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
                                        Gson gson = new Gson();

                                        GoodinfoLineRoot GoodinfoLineRoot = gson.fromJson(netResult, GoodinfoLineRoot.class);
                                        List<PPShopGoodInfo> data1 = GoodinfoLineRoot.getData();
                                        Log.e(TAG, "testd===" + data1.size());
                                        String data = data1.toString();
                                        //将获取的数据通过接口反馈出去
                                        callback.success(data);
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

                } catch (InterruptedException e) {
                    e.printStackTrace();
                    //获取数据失败的回调
                    callback.failure();
                }
            }
        }.start();
    }

    /**
     * 用于回传请求的数据的回传
     */
    public interface LoadDataCallback {

        void success(String taskId);

        void failure();
    }
//————————————————
//    版权声明：本文为CSDN博主「游走的大千世界的烤腰子」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
//    原文链接：https://blog.csdn.net/zhangqunshuai/article/details/82562801
}
