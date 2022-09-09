package activity.com.myappdata.listionnet;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresPermission;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;

/***
 * 网络状态监听工具类
 */
public class NetworkUtil {
    private NetworkUtil() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    @RequiresPermission("android.permission.ACCESS_NETWORK_STATE")
    private static NetworkInfo getActiveNetworkInfo(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo();
    }

    /**
     * 获取当前网络类型
     * 需添加权限 {@code <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>}
     */
    @RequiresPermission("android.permission.ACCESS_NETWORK_STATE")
    public static NetworkType getNetworkType(Context context) {
        NetworkType netType = NetworkType.NETWORK_NO;
        NetworkInfo info = getActiveNetworkInfo(context);
        if (info != null && info.isAvailable()) {

            if (info.getType() == ConnectivityManager.TYPE_WIFI) {
                netType = NetworkType.NETWORK_WIFI;
            } else if (info.getType() == ConnectivityManager.TYPE_MOBILE) {
                switch (info.getSubtype()) {

//                    case TelephonyManager.NETWORK_TYPE_TD_SCDMA:
                    case TelephonyManager.NETWORK_TYPE_EVDO_A:
                    case TelephonyManager.NETWORK_TYPE_UMTS:
                    case TelephonyManager.NETWORK_TYPE_EVDO_0:
                    case TelephonyManager.NETWORK_TYPE_HSDPA:
                    case TelephonyManager.NETWORK_TYPE_HSUPA:
                    case TelephonyManager.NETWORK_TYPE_HSPA:
                    case TelephonyManager.NETWORK_TYPE_EVDO_B:
                    case TelephonyManager.NETWORK_TYPE_EHRPD:
                    case TelephonyManager.NETWORK_TYPE_HSPAP:
                        netType = NetworkType.NETWORK_3G;
                        break;

                    case TelephonyManager.NETWORK_TYPE_LTE:
//                    case TelephonyManager.NETWORK_TYPE_IWLAN:
                        netType = NetworkType.NETWORK_4G;
                        break;

//                    case TelephonyManager.NETWORK_TYPE_GSM:
                    case TelephonyManager.NETWORK_TYPE_GPRS:
                    case TelephonyManager.NETWORK_TYPE_CDMA:
                    case TelephonyManager.NETWORK_TYPE_EDGE:
                    case TelephonyManager.NETWORK_TYPE_1xRTT:
                    case TelephonyManager.NETWORK_TYPE_IDEN:
                        netType = NetworkType.NETWORK_2G;
                        break;
                    default:
                        String subtypeName = info.getSubtypeName();
                        if (subtypeName.equalsIgnoreCase("TD-SCDMA")
                                || subtypeName.equalsIgnoreCase("WCDMA")
                                || subtypeName.equalsIgnoreCase("CDMA2000")) {
                            netType = NetworkType.NETWORK_3G;
                        } else {
                            netType = NetworkType.NETWORK_UNKNOWN;
                        }
                        break;
                }
            } else {
                netType = NetworkType.NETWORK_UNKNOWN;
            }
        }
        return netType;
    }


    /***
     * 微信的网络状态
     */
    //发布上线改为true
    private static String TAG = "MicroMsg.NetworkUtil";

    public static final int GET_TOKEN = 1;
    public static final int CHECK_TOKEN = 2;
    public static final int REFRESH_TOKEN = 3;
    public static final int GET_INFO = 4;
    public static final int GET_IMG = 5;

    public static void sendWxAPI(Handler handler, String url, int msgTag) {
        HttpsThread httpsThread = new HttpsThread(handler, url, msgTag);
        httpsThread.start();
    }

    public static void getImage(Handler handler, String url, int msgTag) {
        HttpsThread httpsThread = new HttpsThread(handler, url, msgTag);
        httpsThread.start();
    }

    static class HttpsThread extends Thread {

        private Handler handler;
        private String httpsUrl;
        private int msgTag;

        public HttpsThread(Handler handler, String url, int msgTag) {
            this.handler = handler;
            this.httpsUrl = url;
            this.msgTag = msgTag;
        }

        @Override
        public void run() {
            if (msgTag == GET_IMG) {
                try {
                    byte[] imgdata = httpURLConnectionGet(httpsUrl);
                    Message msg = Message.obtain();
                    msg.what = msgTag;
                    Bundle data = new Bundle();
                    data.putByteArray("imgdata", imgdata);
                    msg.setData(data);
                    handler.sendMessage(msg);
                } catch (Exception e) {
                    Log.e(TAG, e.getMessage());
                }
            } else {
                int resCode;
                InputStream in;
                String httpResult = null;
                try {
                    URL url = new URL(httpsUrl);
                    URLConnection urlConnection = url.openConnection();
                    HttpsURLConnection httpsConn = (HttpsURLConnection) urlConnection;
                    httpsConn.setAllowUserInteraction(false);
                    httpsConn.setInstanceFollowRedirects(true);
                    httpsConn.setRequestMethod("GET");
                    httpsConn.connect();
                    resCode = httpsConn.getResponseCode();

                    if (resCode == HttpURLConnection.HTTP_OK) {
                        in = httpsConn.getInputStream();

                        BufferedReader reader = new BufferedReader(new InputStreamReader(
                                in, "iso-8859-1"), 8);
                        StringBuilder sb = new StringBuilder();
                        String line;
                        while ((line = reader.readLine()) != null) {
                            sb.append(line).append("\n");
                        }
                        in.close();
                        httpResult = sb.toString();
                        Log.i(TAG, httpResult);

                        Message msg = Message.obtain();
                        msg.what = msgTag;
                        Bundle data = new Bundle();
                        data.putString("result", httpResult);
                        msg.setData(data);
                        handler.sendMessage(msg);
                    }
                } catch (Exception e) {
                    Log.e(TAG, e.getMessage());
                }
            }
        }

        private static byte[] httpURLConnectionGet(String url) throws Exception {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            if (connection == null) {
                Log.i(TAG, "open connection failed.");
            }
            int responseCode = connection.getResponseCode();
            if (responseCode >= 300) {
                connection.disconnect();
                Log.w(TAG, "dz[httpURLConnectionGet 300]");
                return null;
            }

            InputStream is = connection.getInputStream();
            byte[] data = readStream(is);
            connection.disconnect();

            return data;
        }

        private static byte[] readStream(InputStream inStream) throws IOException {
            byte[] buffer = new byte[1024];
            int len = -1;
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            while ((len = inStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, len);
            }
            byte[] data = outStream.toByteArray();
            outStream.close();
            inStream.close();
            return data;
        }
    }

}
