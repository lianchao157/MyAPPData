package activity.com.myappdata.api;

import android.database.Observable;

import activity.com.myappdata.bean.FindFragmentMenumBase;
import activity.com.myappdata.bean.cash.VideoEntity;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface VideoApi {

    /**
     * 获取视频标题等信息
     * http://toutiao.com/api/article/recent/?source=2&category=类型&as=A105177907376A5&cp=5797C7865AD54E1&count=20"
     */
//    @GET("api/article/recent/?source=2&as=A105177907376A5&cp=5797C7865AD54E1&count=30")
//    Observable<ResponseBody> getVideoArticle(
//            @Query("category") String category,
//            @Query("_") String time);

    /**
     * 获取视频信息
     * //     * Api 生成较复杂 详情查看 {@linkplain com.meiji.toutiao.module.video.content.VideoContentPresenter#doLoadVideoData(String)}
     * http://ib.365yg.com/video/urls/v/1/toutiao/mp4/视频ID?r=17位随机数&s=加密结果
     */
    @GET
    Observable<VideoEntity> queryBookList(@Url String url);

    @GET("/")
    Call<ResponseBody> getData2(@Query("q") String q);

    @POST("communal/order")
//post请求提交json
    Call<VideoEntity> postPay(@Body RequestBody route);

    @FormUrlEncoded
    @POST("property/houseuserrelation/add")
//post请求提交表单
    Call<VideoEntity> addPayAddress(@Field("userId") String userId, @Field("houseId") String houseId);


    @PUT("property/userCommunity/modifyUserCommunity")
//put请求提交json
    Call<String> putModify(@Body RequestBody route);

    @DELETE("property/houseuserrelation/delete")
//delete请求提交表单
    Call<VideoEntity> deleteHouse(@Query("id") String id);
//    @GET("property/userCommunity/getUserCommunity/{userId}")//get请求直接拼"/"加参数
//    Call<HttpResultBean<VideoEntity>> getUserCommunity(@Path("userId") String userId);
//    @GET("property/house/selectBuildingById")//get请求
//    Call<HttpResultArrayBean<VideoEntity>> getHouse(@Query("id") String id);


    //    测试
// 获取 顶部菜单数据                                         ?menumtype
    @GET("/getTitleMenumdata")
    Call<FindFragmentMenumBase> getTitleMenumdata(@Query("type") String menumtype);
}
