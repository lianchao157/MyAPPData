//package activity.com.myappdata.mvp.base.modelmvp;
//
//import android.app.Application;
//
//import java.util.concurrent.TimeUnit;
//
//import javax.inject.Singleton;
//
//import activity.com.myappdata.api.ApiService;
//import dagger.Provides;
//import okhttp3.OkHttpClient;
//
//public class ApiServiceModule {
//
//    private static final String ENDPOINT = "";
//
//
//    @Provides
//    @Singleton
//    OkHttpClient provideOkHttpClient() {
//        OkHttpClient okHttpClient = new OkHttpClient();
////        okHttpClient.setConnectTimeout(60 * 1000, TimeUnit.MILLISECONDS);
////        okHttpClient.setReadTimeout(60 * 1000, TimeUnit.MILLISECONDS);
//        return okHttpClient;
//    }
//
////    @Provides
////    @Singleton
////    RestAdapter provideRestAdapter(Application application, OkHttpClient okHttpClient) {
////        RestAdapter.Builder builder = new RestAdapter.Builder();
////        builder.setClient(new OkClient(okHttpClient))
////                .setEndpoint(ENDPOINT);
////        return builder.build();
////    }
//
////    @Provides
////    @Singleton
////    ApiService provideApiService(RestAdapter restAdapter) {
////        return restAdapter.create(ApiService.class);
////    }
//
//}
