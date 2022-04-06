package activity.com.myappdata.retorfitutils;

import java.util.List;

import activity.com.myappdata.activity.video.HotMenums;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
//https://cloud.tencent.com/developer/article/1490801
public interface MovieService {

//    //获取豆瓣前20的榜单
//    @GET("top250")
//    Observable<HotMenums> getMovicTop(@Query("start") int start, @Query("count") int count);
    @GET("GetHotMenums")
    Observable <HotMenums> GetHotMenums();
    @GET("GetHotMenums")
    Observable <List<HotMenums>>GetHotMenums1();



    //用户输入字符串进行搜索
    @POST("GetHotMenumByinput")
    Observable <List<HotMenums>>GetHotMenumByinput(@Query("strInput") String strInput);


    //用户点击这个条目返沪数据
    @POST("GetHotMenumByOnclick")
    Observable <List<HotMenums>>GetHotMenumByOnclick(@Query("ByOnclick") String ByOnclick);

}
