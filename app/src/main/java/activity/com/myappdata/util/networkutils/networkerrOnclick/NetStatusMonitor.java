//package activity.com.myappdata.util.networkutils.networkerrOnclick;
//
//import android.content.Context;
//import android.net.ConnectivityManager;
//import android.net.NetworkInfo;
//
///**
// * 网络状态类型改变的监听接口
// */
//public class NetStatusMonitor {
//    public static final int TYPE_WIFI = 1;
//    public static final int TYPE_MOBILE = 2;
//    public static final int TYPE_NOT_CONNECTED = 0;
//
//
//    public static int getConnectivityStatus(Context context) {
//        ConnectivityManager cm = (ConnectivityManager) context
//                .getSystemService(Context.CONNECTIVITY_SERVICE);
//
//        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
//        if (null != activeNetwork) {
//            if(activeNetwork.getType() == ConnectivityManager.TYPE_WIFI)
//                return TYPE_WIFI;
//
//            if(activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
//                return TYPE_MOBILE;
//        }
//        return TYPE_NOT_CONNECTED;
//    }
//}
