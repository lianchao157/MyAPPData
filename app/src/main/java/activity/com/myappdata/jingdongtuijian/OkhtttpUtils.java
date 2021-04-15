package activity.com.myappdata.jingdongtuijian;

import android.os.Environment;

import java.io.File;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class OkhtttpUtils {
    //单例模式，把构造方法进行私有化
    //
    private OkhtttpUtils(){};
    static OkHttpClient client;


    public static OkHttpClient getInstance(){


        if (client==null) {
            //更加安全
            synchronized (OkhtttpUtils.class) {
                //缓存的地方     mnt/sdcard
                File file = new File(Environment.getExternalStorageDirectory(), "cache11");
                client = new OkHttpClient().newBuilder()
                        .readTimeout(3000, TimeUnit.SECONDS)   //设置读取超时时间
                        .connectTimeout(3000, TimeUnit.SECONDS) //设置连接的超时时间

                        .cache(new Cache(file, 10 * 1024))
                        .build();
            }
        }
        return client;
    }


    /**
     * get请求
     * Callback  是一个接口
     */
    public static void doGet(String url, Callback callback){

        //1:拿到okhttpclient  对像

        OkHttpClient client = getInstance();
        //2:进行请求的操作

        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(callback);


    }

    //post请求

    /**
     *
     * @param url  请求的地址
     * @param parms   请求的参数
     * @param callback  callback
     */
    public static void doPost(String url, Map<String,String> parms, Callback callback){

        //得到客户端的对像
        OkHttpClient client = getInstance();

        //不是FormBody，而是一个Builder
        FormBody.Builder body = new FormBody.Builder();
        //key   value
        for (String key:parms.keySet()){
            //value的值
            body.add(key,parms.get(key));
        }
        Request request = new Request.Builder()
                .url(url)
                .post(body.build())
                .build();

        client.newCall(request).enqueue(callback);

    }

    //用来上传图片的

    //url  , 图片  ，参数    Callback
    public static  void upImage(String url,File file,String filenName,Map<String,String> params,Callback callback){

        OkHttpClient client = getInstance();


        //requestBody的实现类  Formbody
        MultipartBody.Builder builder = new MultipartBody.Builder();

        if (params!=null){
            for (String key :params.keySet()){
                builder.addFormDataPart(key,params.get(key));
            }
        }

        //设置类型
        builder.setType(MultipartBody.FORM);


        builder.addFormDataPart("file",filenName, RequestBody.create(MediaType.parse("application/octet-stream"),file));

        // builder.setType(MultipartBody.FORM);
        // builder.addFormDataPart("file",filenName,RequestBody.create(MediaType.parse("application/octet-stream"),file));
        //builder.addFormDataPart("file","aa.png",builder.build());

        Request request = new Request.Builder()
                .url(url)
                .post(builder.build())
                .build();

        client.newCall(request).enqueue(callback);


    }

}
