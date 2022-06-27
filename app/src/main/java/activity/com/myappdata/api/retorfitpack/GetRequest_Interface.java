package activity.com.myappdata.api.retorfitpack;

import activity.com.myappdata.api.retorfitpack.entity.Root;
import activity.com.myappdata.api.retorfitpack.entity.Translation;
import activity.com.myappdata.bean.FindFragmentMenumBase;
import activity.com.myappdata.bean.FindFragmetnMenuTitleEntity;
import activity.com.myappdata.entity.Beandata;
import activity.com.myappdata.entity.ShopCarInfoenTity;
import activity.com.myappdata.mvp.base.modelmvp.mvploginentity.MVPRoot;
import activity.com.myappdata.mvp.base.modelmvp.mvploginentity.mvpuserinfolitit.MVPUserInfo;
import activity.com.myappdata.mvp.base.view.showmainactivity.searchview.HotMenums;
import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Query;
public interface GetRequest_Interface {
    @GET("/selectall/")
    Call<Root> selectall();

    // 注解里传入 网络请求 的部分URL地址
    // getCall()是接受网络请求数据的方法
    @POST("/deletebyid")
    Call<String> deletebyid(@Query("username") String username);

    @PUT("/selectbyid")
    Call<Root> selectbyid(@Query("username") String username);


    ///  https://www.jianshu.com/p/2e8b400909b7  封装
    // 使用call的情况
    @GET("/selectall/")
    Call<Root> login2();


    @GET("/selectall/")
    Observable<Root> getTop();

    @GET("/selectall/")
    Observable<ResponseBody> getGetData();

//  带有gson 解析的url

    @GET("/selectall/")
    Observable<Root> getGetDataGson();


//    MVP  模式的login

//    Multipart
//    @PUT("sys/user/resetPassword")
//    Observable<HttpResult<LzyResult>> resetPassword(
//            @Part("telephone") RequestBody telephone, @Part("password") RequestBody pwd);
//————————————————
//    版权声明：本文为CSDN博主「君若兮523」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
//    原文链接：https://blog.csdn.net/u010872619/article/details/81773333


    @PUT("/pplogin")
    Call<MVPRoot> getPPMVPlog(@Query("username") String username, @Query("userpassword") String userpassword
//            @Part("username") RequestBody username, @Part("userpassword") RequestBody userpassword
    );
//            @Field("username") String username, @Field("userpassword") String userpassword);


    /***
     * mvp  模式下获取快递当前位置  查看快递到哪里了
     *
     * @param userphone
     * @return
     */
//    @GET("/selectLineinfo")
    @GET("/selectLIneInfo")
    Observable<ResponseBody> selectLIneInfo(@Query("userphone") String userphone);

    //  获取人员信息
    @PUT("/selectAllbytype")
    Call<MVPUserInfo> selectAllbytype(@Query("page") String page, @Query("limit") String limit);

    // 获取 顶部菜单数据                                         ?menumtype
    @GET("/getTitleMenumdata")
    Call<FindFragmentMenumBase> getTitleMenumdata(@Query("type") String menumtype);
    // 通过post 方式获取数据
    @POST("/getTitleMenumdata")
    Call<ShopCarInfoenTity> getDataByPost(@Query("type") ShopCarInfoenTity mShopCarInfoenTity);

    @GET("/GetHotMenums")
    Call<HotMenums> GetHotMenums();

}
