package activity.com.myappdata.util.networkutils.networkutil;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/***
 * 对当前网络状态的监听
 * 2021.7.24
 */
public class Util_Net {


    //移动网络
    private static final int MOBILE_NET=0;
    //无线网络
    private static final int WIFI_NET=1;
    //没有网络
    private static final int NO_NET=0;

    public static int getNetWorkState(Context context){
//获取连接服务 CONNECTIVITY_SERVICE
        ConnectivityManager connectivityManager = (ConnectivityManager) context

                .getSystemService(Context.CONNECTIVITY_SERVICE);
        //获取当前网络连接
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        if (activeNetworkInfo!=null&&activeNetworkInfo.isConnected()){
            if (activeNetworkInfo.getType()==(ConnectivityManager.TYPE_WIFI)){
                //当前处于无线网络
                return WIFI_NET;
            }else if (activeNetworkInfo.getType()==(ConnectivityManager.TYPE_MOBILE)){
                //当前处于移动网络
                return MOBILE_NET;
            }
        }else {
            //无网络
            return NO_NET;
        }
        //默认返回  没有网络
        return NO_NET;
    }

//————————————————
//    版权声明：本文为CSDN博主「清祀廿七」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
//    原文链接：https://blog.csdn.net/Y_sunny_U/article/details/109001769
}
//    使用方式
//
//            在需要判断网络状态时调用
//
//Util_Net.getNetWorkState(context);