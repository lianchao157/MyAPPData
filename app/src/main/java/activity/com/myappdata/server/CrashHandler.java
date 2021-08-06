package activity.com.myappdata.server;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import activity.com.myappdata.util.LogUtil;

/***
 * 日志存储工具类代码
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler {
    private StringBuffer mErrorLogBuffer = new StringBuffer();
    public static final String TAG = "CrashHandler";
    public static final boolean DEBUG = true;
    private static final String SINGLE_RETURN = "\n";

    private static final String SINGLE_LINE = "----------------split line---------------";
    //日志保存路径
    public static final String PATH = Environment.getExternalStorageDirectory().getPath() + "/TestApp/CrashLog/";
    public static final String FILE_NAME = "carsh_";
    public static final String FILE_NAME_SUFFIX = ".txt";
    //系统默认的UncaughtException处理类
    private Thread.UncaughtExceptionHandler mDefaultHandler;
    //CrashLogManager实例
    private static CrashHandler INSTANCE = new CrashHandler();
    //程序的Context对象
    private Context mContext;
    //用来存储设备信息和异常信息
    private Map<String, String> infos = new HashMap<String, String>();

    /**
     * 保证只有一个CrashLogManager实例
     */
    private CrashHandler() {
    }

    /**
     * 获取CrashLogManager实例 ,单例模式
     */
    public static CrashHandler getInstance() {
        return INSTANCE;
    }

    /**
     * 初始化
     *
     * @param context
     */
    public void init(Context context) {
        mContext = context;
        //获取系统默认的UncaughtException处理器
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        //设置该CrashLogManager为程序的默认处理器
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /**
     * 最关键函数，当程序有未捕获的异常时，系统自动调用次方法
     *
     * @param thread 未捕获异常线程
     * @param ex     未捕获异常信息
     */
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {

        if (!handleException(ex) && mDefaultHandler != null) {
            // 如果用户没有处理异常就由系统默认的异常处理器来处理
            mDefaultHandler.uncaughtException(thread, ex);
        } else {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.exit(1);
            // android.os.Process.killProcess(android.os.Process.myPid());
        }
//        try {
//            //异常信息写入sd卡
//            dumpExceptionToSD(ex);
//            //异常信息上传服务器
//            uploadExcptionToServer();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        if (mDefaultHandler != null) {
//            //如果用户没有处理则让系统默认的异常处理器来处理
//            mDefaultHandler.uncaughtException(thread, ex);
//        } else {
//            android.os.Process.killProcess(android.os.Process.myPid());
//            System.exit(1);
//        }
    }


    /**
     *
     * @param ex
     * @return
     */
    private boolean handleException(Throwable ex) {
        if (ex == null) {
            return false;
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Looper.prepare();
                    Toast.makeText(mContext, "很抱歉,现钞管理系统停止运行,即将退出.", Toast.LENGTH_SHORT).show();
                    Looper.loop();
                }catch (Exception e){
                    LogUtil.d(TAG,""+e);
                }

            }
        }).start();

        // 收集设备参数信息
        collectDeviceInfo(mContext);
        // 收集错误日志
        collectCrashInfo(ex);
        // 保存错误日志
        saveErrorLog();

        return true;
    }

    /**
     * 保存日志到/sdcard/PokaAppLog/目录下，文件名已日期的形式保存
     */
    private void saveErrorLog() {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            String format = sdf.format(new Date());
            format += ".txt";
            String path = Environment.getExternalStorageDirectory().getPath() + "/TestLog/";
            File file = new File(path);
            if (!file.exists()) {
                file.mkdirs();
            }

            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(path + format, true);
                fos.write(mErrorLogBuffer.toString().getBytes());
                fos.flush();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fos != null) {
                    try {
                        fos.close();
                        fos = null;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 收集错误信息
     *
     * @param ex
     */
    private void collectCrashInfo(Throwable ex) {
        Writer info = new StringWriter();
        PrintWriter printWriter = new PrintWriter(info);
        ex.printStackTrace(printWriter);

        Throwable cause = ex.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }

        String result = info.toString();
        printWriter.close();

        // 将错误信息加入mErrorLogBuffer中
        append("", result);
        mErrorLogBuffer.append(SINGLE_RETURN + SINGLE_LINE + SINGLE_RETURN);

        Log.d(TAG, "saveCrashInfo2File:" + mErrorLogBuffer.toString());
    }

    /**
     * 收集应用和设备信息
     *
     * @param context
     */
    private void collectDeviceInfo(Context context) {
        // 每次使用前，清掉mErrorLogBuffer里的内容
        mErrorLogBuffer.setLength(0);
        mErrorLogBuffer.append(SINGLE_RETURN + SINGLE_LINE + SINGLE_RETURN);

        // 获取应用的信息
        PackageManager pm = context.getPackageManager();
        try {
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), PackageManager.GET_ACTIVITIES);
            if (pi != null) {
                append("versionCode", pi.versionCode);
                append("versionName", pi.versionName);
                append("packageName", pi.packageName);
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        mErrorLogBuffer.append(SINGLE_RETURN + SINGLE_LINE + SINGLE_RETURN);

        // 获取设备的信息
        Field[] fields = Build.class.getDeclaredFields();
        getDeviceInfoByReflection(fields);

        fields = Build.VERSION.class.getDeclaredFields();
        getDeviceInfoByReflection(fields);

        mErrorLogBuffer.append(SINGLE_RETURN + SINGLE_LINE + SINGLE_RETURN);
    }

    /**
     * 获取设备的信息通过反射方式
     *
     * @param fields
     */
    private void getDeviceInfoByReflection(Field[] fields) {
        for (Field field : fields) {
            try {
                // 对private成员变量设置可访问
                field.setAccessible(true);
                append(field.getName(), field.get(null));
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    // mErrorLogBuffer添加友好的log信息
    private void append(String key, Object value) {
        mErrorLogBuffer.append("" + key + ":" + value + SINGLE_RETURN);
    }
}
