//package activity.com.myappdata.net;
//
//import activity.com.myappdata.api.VideoApi;
//import activity.com.myappdata.bean.cash.VideoEntity;
//import retrofit2.Retrofit;
//import retrofit2.adapter.rxjava.Result;
//import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
//import retrofit2.converter.gson.GsonConverterFactory;
//import rx.Observable;
//import rx.functions.Func1;
//import rx.schedulers.Schedulers;
//
//public class HttpMethods {
//
//    public static final String BASE_URL = "https://suggest.taobao.com/sug?code=utf-8/";
//
//    private static final int DEFAULT_TIMEOUT = 5;
//
//    private Retrofit retrofit;
//    private VideoApi demoService;
//
//    //构造方法私有
//    private HttpMethods() {
//        retrofit = new Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .build();
//        demoService = retrofit.create(VideoApi.class);
//    }
//
//    //在访问HttpMethods时创建单例
//    private static class SingletonHolder{
//        private static final HttpMethods INSTANCE = new HttpMethods();
//    }
//
//    //获取单例
//    public static HttpMethods getInstance(){
//        return SingletonHolder.INSTANCE;
//    }
//
//    /**
//     * 用于获取豆瓣电影Top250的数据
//     */
//    public Observable<VideoEntity> getTopMovie(String username){
//        return demoService.readBaidu(username);
//    }
//
////    public Observable<Login> login(String username, String password){
////        return demoService.readBaidu(username, password)
////                .map(new ServerResultFunc<Login>())
////                .onErrorResumeNext(new HttpResultFunc<Login>())
////                .subscribeOn(Schedulers.io())
////                .unsubscribeOn(Schedulers.io())
////                .observeOn(AndroidSchedulers.mainThread());
////    }
////
////    private class ServerResultFunc<T> implements Func1<Result<T>, T> {
////        @Override
////        public T call(Result<T> httpResult) {
////            if (httpResult.state != 200) {
////                throw new ServerException(httpResult.state,httpResult.error);
////            }
////            return httpResult.infos;
////        }
////    }
//
//    private class HttpResultFunc<T> implements Func1<Throwable, Observable<T>> {
//        @Override
//        public Observable<T> call(Throwable throwable) {
//            return Observable.error(ExceptionEngine.handleException(throwable));
//        }
//    }
//}
