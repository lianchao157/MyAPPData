package activity.com.myappdata.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.io.IOException;
import java.util.List;


import activity.com.myappdata.R;
import activity.com.myappdata.api.VideoApi;
import activity.com.myappdata.api.retorfitpack.GetRequest_Interface;
import activity.com.myappdata.api.retorfitpack.entity.Data;
import activity.com.myappdata.api.retorfitpack.entity.Root;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.Subject;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/***
 *    retorfit   使用代码
 *    https://www.jianshu.com/p/ddd73eb6ffaa
 *    当前为网络请求retrorfit 使用
 */
public class CashInfoActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "CashInfoActivity";
    private TextView tv001;
    private TextView tv002;
    private LinearLayout linearLayout;//   组件声明
    private Button tvnetaction;
    private Button postactiong;//   post  代码

    private Button putaction;//   PUT 代码
    private VideoApi videoApi;
    public static String BASE_URL = "https://suggest.taobao.com/sug?code=utf-8";

    //    private  List<>   arraylist=new ArrayList<>()
    private Button testaction; //  测试按钮该按钮实现  rxjava 和retofti   使用观察者模式代码
    private Button gsonjiexi;//   在rxjava 和retofit  上使用  gson 解析代码
    private Button shezhishujujiexi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_info);
        linearLayout = (LinearLayout) findViewById(R.id.linlay);
        tv001 = (TextView) findViewById(R.id.tv001);
        tv002 = (TextView) findViewById(R.id.tv001);
        tvnetaction = (Button) findViewById(R.id.tvnetaction);
        postactiong = (Button) findViewById(R.id.postactiong);
        postactiong.setOnClickListener(this);
        putaction = (Button) findViewById(R.id.putaction);
        putaction.setOnClickListener(this);
        testaction = (Button) findViewById(R.id.testaction);
        testaction.setOnClickListener(this);


        gsonjiexi = (Button) findViewById(R.id.gsonjiexi);

        gsonjiexi.setOnClickListener(this);
        shezhishujujiexi = (Button) findViewById(R.id.shezhishujujiexi);
        shezhishujujiexi.setOnClickListener(this);

        tvnetaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://192.168.1.160:8888/") // 设置 网络请求 Url
                        .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                        .build();

                // 步骤5:创建 网络请求接口 的实例
                GetRequest_Interface request = retrofit.create(GetRequest_Interface.class);

                //对 发送请求 进行封装
                Call<Root> call = request.selectall();

                //步骤6:发送网络请求(异步)
                call.enqueue(new Callback<Root>() {
                    //请求成功时候的回调
                    @Override
                    public void onResponse(Call<Root> call, Response<Root> response) {
                        //请求处理,输出结果

                        System.out.println("连接" + response.body());
                        Log.e(TAG, response.body() + "连接");

                        String msg = response.body().getMsg();
                        List<Data> ld = response.body().getData();
                        for (int i = 0; i < ld.size(); i++) {
                            System.out.println("数据==========" + ld.get(i).getUsername());
                            Log.d(TAG, response.body().getData() + "连接");
                            Log.d(TAG, ld.get(i).getUsername() + "连接");
                        }
                        Log.d(TAG, response.body().getData() + "连接");
                    }

                    //请求失败时候的回调
                    @Override
                    public void onFailure(Call<Root> call, Throwable throwable) {
                        System.out.println("连接失败" + throwable);
                    }
                });


            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.postactiong:
                PostActiong();
                break;

            case R.id.putaction:
                PUTSelectId();

//              TesetAction();
                break;

            case R.id.testaction:
                getMovie();

                break;

            case R.id.gsonjiexi:


                GsonAction();
                break;


            case R.id.shezhishujujiexi:
                getMovieGson();

                break;
            default:


                break;


        }
    }

    private void GsonAction() {


    }

    private void TesetAction() {
//        Retrofit retrofit1 = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//新的配置
                .baseUrl(BASE_URL)
                .build();
        GetRequest_Interface service = retrofit.create(GetRequest_Interface.class);
//进行网络请求
//        service.selectall()          //获取Observable对象
//                .subscribeOn(Schedulers.newThread())//请求在新的线程中执行
//                .observeOn(Schedulers.io())         //请求完成后在io线程中执行
//                .doOnNext(new Action1<Root>() {
//                    @Override
//                    public void call(Root userInfo) {
//                        saveUserInfo(userInfo);//保存用户信息到本地
//                    }
//                })
//                .observeOn(AndroidSchedulers.mainThread())//最后在主线程中执行
//                .subscribe(new Subscriber<Root>() {
//
//                    @Override
//                    public void onError(Throwable e) {
//                        //请求失败
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//
//                    @Override
//                    public void onSubscribe(Subscription s) {
//
//                    }
//
//                    @Override
//                    public void onNext(Root userInfo) {
//                        //请求成功
//                    }
//                });

    }

    /***
     * 查询单个
     */
    private void PUTSelectId() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.160:8888/") // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // 步骤5:创建 网络请求接口 的实例
        GetRequest_Interface request = retrofit.create(GetRequest_Interface.class);

        //对 发送请求 进行封装
        Call<Root> call = request.selectbyid("4");


        //步骤6:发送网络请求(异步)
        call.enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                Log.e(TAG, response.body() + "delete结果");
                Log.e(TAG, response.body().getData().get(0).getUsername() + "delete结果");
            }

            @Override
            public void onFailure(Call<Root> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }

        });


    }

    /***
     *  post  请求
     */
    private void PostActiong() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.160:8888/") // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();

        // 步骤5:创建 网络请求接口 的实例
        GetRequest_Interface request = retrofit.create(GetRequest_Interface.class);

        //对 发送请求 进行封装
        Call<String> call = request.deletebyid("4");

        //步骤6:发送网络请求(异步)
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.e(TAG, response.body() + "delete结果");
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }

        });


    }


    /**
     * 获取电影
     * <p>
     * https://www.jianshu.com/p/ddd73eb6ffaa   rxhe retorfit  的代码
     */
    private void getMovie() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.160:8888/")
                // 支持RxJava平台
                //compile 'com.jakewharton.retrofit:retrofit2-rxjava2-adapter:1.0.0'   是他 就是他
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        retrofit.create(GetRequest_Interface.class)
                .getGetData()//注意看，这里返回的对象时什么，和原生的返回不同，也是我们把上面接口改的原因
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

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            Log.e(TAG, "testd===" + responseBody.string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "testd====" + e);
                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG, "testdonComplete=======");
                    }
                });


    }


    private void getMovieGson() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.160:8888/")
                // 支持RxJava平台
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        retrofit.create(GetRequest_Interface.class)
                .getGetDataGson()//注意看，这里返回的对象时什么，和原生的返回不同，也是我们把上面接口改的原因
                //在子线程取数据
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                //在主线程显示ui
                // compile 'io.reactivex.rxjava2:rxandroid:2.0.1'  这个库下才有AndroidSchedulers.mainThread
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Root>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e(TAG, "onSubscribe=" + d);
                        /*显示进度框*/
                    }

                    @Override
                    public void onNext(Root root) {
                        Log.e(TAG, "root=" + root.getData());
                        Log.e(TAG, "root=" + root.getMsg());
                        Log.e(TAG, "root=" + root.getCode());
                    }


                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError====" + e);
                        /*隐藏进度框，显示错误的提示信息*/

                    }

                    @Override
                    public void onComplete() {
                        /*隐藏进度框*/
                        Log.e(TAG, "testdonComplete=======");
                    }
                });


    }


}
