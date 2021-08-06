package activity.com.myappdata.net;

import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import activity.com.myappdata.api.VideoApi;
import activity.com.myappdata.api.retorfitpack.NetWorkManager;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/****
 * http://www.cocoachina.com/cms/wap.php?action=article&id=35122
 */
//封装实现
public class RetrofitManager {
    //    private final String BASE_URL = "https://api.github.com";
    private final String BASE_URL = "http://192.168.1.166:8988";
////    private static RetrofitManager sInstance;
////    private Retrofit mRetrofit;
////
////    public static RetrofitManager getInstance() {
////        if (null == sInstance) {
////            synchronized (RetrofitManager.class) {
////                if (null == sInstance) {
////                    sInstance = new RetrofitManager();
////                }
////            }
////        }
////        return sInstance;
////    }
////
////    public void init() {
////        if (mRetrofit == null) {
////            //初始化一个OkHttpClient
////            OkHttpClient.Builder builder = new OkHttpClient.Builder()
////                    .connectTimeout(30000, TimeUnit.MILLISECONDS)
////                    .readTimeout(30000, TimeUnit.MILLISECONDS)
////                    .writeTimeout(30000, TimeUnit.MILLISECONDS);
////            builder.addInterceptor(new LoggingInterceptor());
////            OkHttpClient okHttpClient = builder.build();
////
////            //使用该OkHttpClient创建一个Retrofit对象
////            mRetrofit = new Retrofit.Builder()
////                    //添加Gson数据格式转换器支持
////                    .addConverterFactory(GsonConverterFactory.create())
////                    //添加RxJava语言支持
////                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
////                    //指定网络请求client
////                    .client(okHttpClient)
////                    .baseUrl(BASE_URL)
////                    .build();
////        }
////    }
////
////    public Retrofit getRetrofit() {
////        if (mRetrofit == null) {
////            throw new IllegalStateException("Retrofit instance hasn't init!");
////        }
////        return mRetrofit;
////    }
////
////
////    private static NetWorkManager mInstance;
////    private static Retrofit retrofit;
////    private static volatile Request request = null;
////
////    public static NetWorkManager getInstance1() {
////        if (mInstance == null) {
////            synchronized (NetWorkManager.class) {
////                if (mInstance == null) {
////                    mInstance = new NetWorkManager();
////                }
////            }
////        }
////        return mInstance;
////    }
////
////    /**
////     * 初始化必要对象和参数
////     */
////    public void init1() {
////        // 初始化okhttp
////        OkHttpClient client = new OkHttpClient.Builder()
////                .build();
////
////        // 初始化Retrofit
////        retrofit = new Retrofit.Builder()
////                .client(client)
////                .baseUrl(BASE_URL)
////                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
////                .addConverterFactory(GsonConverterFactory.create())
////                .build();
////    }
////
////    public static Request getRequest() {
////        if (request == null) {
////            synchronized (Request.class) {
////                request = retrofit.create(Request.class);
////            }
////        }
////        return request;
////    }

    private static final String TAG = "RetrofitManager";
    private static RetrofitManager mRetrofitManager;
    private Retrofit mRetrofit;
    private VideoApi apiService;

    public static RetrofitManager getInstance() {
        if (mRetrofitManager == null) {
            synchronized (RetrofitManager.class) {
                if (mRetrofitManager == null) {
                    mRetrofitManager = new RetrofitManager();
                }
            }
        }
        return mRetrofitManager;
    }


    private RetrofitManager() {
        initRetrofit();
    }

    public void initRetrofit() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();//添加访问网络的日志
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);//日志级别
        /*
         **打印retrofit信息部分
         */
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                //打印retrofit日志
                Log.e("RetrofitLog", "retrofitBack = " + message);
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()//okhttp设置部分，此处还可再设置网络参数
                .addInterceptor(new Interceptor() {// okhttp3i
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request()
                                .newBuilder()
//                                .addHeader("access_token", "")
//                                                                .addHeader("", "")//  添加header  的地方
                                .build();
                        return chain.proceed(request);
                    }
                })
                .addInterceptor(loggingInterceptor)
                .build();

        Log.e(TAG, "initRetrofit: ");
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        Log.e(TAG, "initRetrofit: " + mRetrofit);

        apiService = mRetrofit.create(VideoApi.class);

    }

    /***
     * inface 实例
     * @return
     */
    public VideoApi getService() {
        Log.e(TAG, "getService: " + apiService);
        return apiService;
    }

    public static RequestBody getBody(HashMap map) {
        Gson gson = new Gson();
        String strEntity = gson.toJson(map);
        Log.e("=====json串", strEntity);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json;charset=UTF-8"), strEntity);
        return body;
    }
}
