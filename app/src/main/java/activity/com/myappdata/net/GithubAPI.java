//package activity.com.myappdata.net;
//
//import activity.com.myappdata.api.AndroidScheduler;
//import activity.com.myappdata.api.VideoApi;
//import activity.com.myappdata.bean.cash.VideoEntity;
//import io.reactivex.Observable;
//import io.reactivex.schedulers.Schedulers;
//
///***
// * https://blog.csdn.net/qq_34336018/article/details/70311606   工具库
// */
//public class GithubAPI {
//    Observable<VideoEntity> queryJakeWhartonInfo() {
//        return RetrofitManager.getInstance().getRetrofit()
//                //动态代理创建GithubAPI对象
//                .create(VideoApi.class)
//                .getData2("粉笔")
//                .queryJakeWhartonInfo()
//                //指定上游发送事件线程
//                .subscribeOn(Schedulers.computation())
//                //指定下游接收事件线程
//                .observeOn(AndroidScheduler.mainThread());
//    }
//}
