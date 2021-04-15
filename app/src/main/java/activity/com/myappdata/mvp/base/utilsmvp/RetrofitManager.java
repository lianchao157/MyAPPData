package activity.com.myappdata.mvp.base.utilsmvp;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {
    private static RetrofitManager INSTANCE;
    private Retrofit retrofit;

    public static RetrofitManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RetrofitManager();
        }
        return INSTANCE;
    }

    private RetrofitManager() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

}
