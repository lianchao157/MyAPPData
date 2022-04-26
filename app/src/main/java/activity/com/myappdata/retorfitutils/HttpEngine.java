package activity.com.myappdata.retorfitutils;

import java.util.List;

import activity.com.myappdata.activity.video.HotMenums;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

//、创建实现接口来方便调用
public  class HttpEngine {
    private static MovieService movieService = RetrofitServiceManager.getInstance().create(MovieService.class);

    /*
     * 获取豆瓣电影榜单
     * **/
    public static void getDuoBanTop(Observer<HotMenums> observer) {
        setSubscribe1(movieService.GetHotMenums(), observer);
    }

    private static <T> void setSubscribe1(Observable<T> observable, Observer<T> observer) {
        observable.subscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.newThread())//子线程访问网络
                .observeOn(AndroidSchedulers.mainThread())//回调到主线程
                .subscribe(observer);
    }




//    获取集合返回

    public static void getDuoBanTop1(Observer<List<HotMenums>> observer) {
        setSubscribe(movieService.GetHotMenums1(), observer);
    }

    private static <T> void setSubscribe(Observable<T> observable, Observer<T> observer) {
        observable.subscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.newThread())//子线程访问网络
                .observeOn(AndroidSchedulers.mainThread())//回调到主线程
                .subscribe(observer);
    }


    //  传递用户要搜的类型

    public static void GetHotMenumByinput(String strinpt ,Observer<List<HotMenums>> observer) {
        setSubscribe(movieService.GetHotMenumByinput(strinpt), observer);
    }


    /***
     * 获取手机信息
     */

    public static void GetHotMenumOnclick(String typename ,Observer<List<HotMenums>> observer) {
        setSubscribe(movieService.GetHotMenumByOnclick(typename), observer);
    }

}
