package activity.com.myappdata.server;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import activity.com.myappdata.application.MyApplication;
import activity.com.myappdata.util.LogUtil;
import io.reactivex.annotations.NonNull;

/***
 * 日志存储工具类代码
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler {
    private static final String TAG ="CrashHandler" ;
//    private StringBuffer mErrorLogBuffer = new StringBuffer();
//    public static final String TAG = "CrashHandler";
//    public static final boolean DEBUG = true;
//    private static final String SINGLE_RETURN = "\n";
//
//    private static final String SINGLE_LINE = "----------------split line---------------";
//    //日志保存路径
//    public static final String PATH = Environment.getExternalStorageDirectory().getPath() + "/TestApp/CrashLog/";
//    public static final String FILE_NAME = "carsh_";
//    public static final String FILE_NAME_SUFFIX = ".txt";
//    //系统默认的UncaughtException处理类
//    private Thread.UncaughtExceptionHandler mDefaultHandler;
//    //CrashLogManager实例
//    private static CrashHandler INSTANCE = new CrashHandler();
//    //程序的Context对象
    private Context mContext;
//    //用来存储设备信息和异常信息
//    private Map<String, String> infos = new HashMap<String, String>();
//
//    /**
//     * 保证只有一个CrashLogManager实例
//     */
//    private CrashHandler() {
//    }
//
//    /**
//     * 获取CrashLogManager实例 ,单例模式
//     */
//    public static CrashHandler getInstance() {
//        return INSTANCE;
//    }
//
//    /**
//     * 初始化
//     *
//     * @param context
//     */
    public void init(Context context) {
        mContext = context;
        //获取系统默认的UncaughtException处理器
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        //设置该CrashLogManager为程序的默认处理器
        Thread.setDefaultUncaughtExceptionHandler(this);
    }
//
//    /**
//     * 最关键函数，当程序有未捕获的异常时，系统自动调用次方法
//     *
//     * @param thread 未捕获异常线程
//     * @param ex     未捕获异常信息
//     */
//    @Override
//    public void uncaughtException(Thread thread, Throwable ex) {
//
//        if (!handleException(ex) && mDefaultHandler != null) {
//            // 如果用户没有处理异常就由系统默认的异常处理器来处理
//            mDefaultHandler.uncaughtException(thread, ex);
//        } else {
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.exit(1);
//            // android.os.Process.killProcess(android.os.Process.myPid());
//        }
////        try {
////            //异常信息写入sd卡
////            dumpExceptionToSD(ex);
////            //异常信息上传服务器
////            uploadExcptionToServer();
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
////
////        if (mDefaultHandler != null) {
////            //如果用户没有处理则让系统默认的异常处理器来处理
////            mDefaultHandler.uncaughtException(thread, ex);
////        } else {
////            android.os.Process.killProcess(android.os.Process.myPid());
////            System.exit(1);
////        }
//    }
//
//
//    /**
//     *
//     * @param ex
//     * @return
//     */
//    private boolean handleException(Throwable ex) {
//        if (ex == null) {
//            return false;
//        }
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Looper.prepare();
//                    Toast.makeText(mContext, "很抱歉,现钞管理系统停止运行,即将退出.", Toast.LENGTH_SHORT).show();
//                    Looper.loop();
//                }catch (Exception e){
//                    LogUtil.d(TAG,""+e);
//                }
//
//            }
//        }).start();
//
//        // 收集设备参数信息
//        collectDeviceInfo(mContext);
//        // 收集错误日志
//        collectCrashInfo(ex);
//        // 保存错误日志
//        saveErrorLog();
//
//        return true;
//    }
//
//    /**
//     * 保存日志到/sdcard/PokaAppLog/目录下，文件名已日期的形式保存
//     */
//    private void saveErrorLog() {
//        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
//            String format = sdf.format(new Date());
//            format += ".txt";
//            String path = Environment.getExternalStorageDirectory().getPath() + "/TestLog/";
//            File file = new File(path);
//            if (!file.exists()) {
//                file.mkdirs();
//            }
//
//            FileOutputStream fos = null;
//            try {
//                fos = new FileOutputStream(path + format, true);
//                fos.write(mErrorLogBuffer.toString().getBytes());
//                fos.flush();
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            } finally {
//                if (fos != null) {
//                    try {
//                        fos.close();
//                        fos = null;
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }
//    }
//
//    /**
//     * 收集错误信息
//     *
//     * @param ex
//     */
//    private void collectCrashInfo(Throwable ex) {
//        Writer info = new StringWriter();
//        PrintWriter printWriter = new PrintWriter(info);
//        ex.printStackTrace(printWriter);
//
//        Throwable cause = ex.getCause();
//        while (cause != null) {
//            cause.printStackTrace(printWriter);
//            cause = cause.getCause();
//        }
//
//        String result = info.toString();
//        printWriter.close();
//
//        // 将错误信息加入mErrorLogBuffer中
//        append("", result);
//        mErrorLogBuffer.append(SINGLE_RETURN + SINGLE_LINE + SINGLE_RETURN);
//
//        Log.d(TAG, "saveCrashInfo2File:" + mErrorLogBuffer.toString());
//    }
//
//    /**
//     * 收集应用和设备信息
//     *
//     * @param context
//     */
//    private void collectDeviceInfo(Context context) {
//        // 每次使用前，清掉mErrorLogBuffer里的内容
//        mErrorLogBuffer.setLength(0);
//        mErrorLogBuffer.append(SINGLE_RETURN + SINGLE_LINE + SINGLE_RETURN);
//
//        // 获取应用的信息
//        PackageManager pm = context.getPackageManager();
//        try {
//            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), PackageManager.GET_ACTIVITIES);
//            if (pi != null) {
//                append("versionCode", pi.versionCode);
//                append("versionName", pi.versionName);
//                append("packageName", pi.packageName);
//            }
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        mErrorLogBuffer.append(SINGLE_RETURN + SINGLE_LINE + SINGLE_RETURN);
//
//        // 获取设备的信息
//        Field[] fields = Build.class.getDeclaredFields();
//        getDeviceInfoByReflection(fields);
//
//        fields = Build.VERSION.class.getDeclaredFields();
//        getDeviceInfoByReflection(fields);
//
//        mErrorLogBuffer.append(SINGLE_RETURN + SINGLE_LINE + SINGLE_RETURN);
//    }
//
//    /**
//     * 获取设备的信息通过反射方式
//     *
//     * @param fields
//     */
//    private void getDeviceInfoByReflection(Field[] fields) {
//        for (Field field : fields) {
//            try {
//                // 对private成员变量设置可访问
//                field.setAccessible(true);
//                append(field.getName(), field.get(null));
//            } catch (IllegalArgumentException e) {
//                e.printStackTrace();
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    // mErrorLogBuffer添加友好的log信息
//    private void append(String key, Object value) {
//        mErrorLogBuffer.append("" + key + ":" + value + SINGLE_RETURN);
//    }


    private static CrashHandler INSTANCE;

    private static PendingIntent restartIntent;

    private Thread.UncaughtExceptionHandler mDefaultHandler;
    private CrashUploader crashUploader;

    private DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");

    public static final String EXCEPTION_INFO = "EXCEPTION_INFO";
    public static final String PACKAGE_INFO = "PACKAGE_INFO";
    public static final String DEVICE_INFO = "DEVICE_INFO";
    public static final String SYSTEM_INFO = "SYSTEM_INFO";
    public static final String SECURE_INFO = "SECURE_INFO";
    public static final String MEM_INFO = "MEM_INFO";



    private String mExceptionInfo;
    private String mMemInfo;

    private ConcurrentHashMap<String, String> mPackageInfo = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, String> mDeviceInfo = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, String> mSystemInfo = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, String> mSecureInfo = new ConcurrentHashMap<>();

    private ConcurrentHashMap<String, Object> totalInfo = new ConcurrentHashMap<>();



    private CrashHandler() {

    }

    public static CrashHandler getInstance() {
        if (INSTANCE == null) {
            synchronized (CrashHandler.class) {
                if (INSTANCE == null) {
                    INSTANCE = new CrashHandler();
                }
            }
        }
        return INSTANCE;
    }

    public void init(Context context, CrashUploader crashUploader, PendingIntent pendingIntent) {
        mContext = context;
        this.crashUploader = crashUploader;
        this.restartIntent = pendingIntent;
        //保存一份系统默认的CrashHandler
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        //使用我们自定义的异常处理器替换程序默认的
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /**
     * @param t 出现未捕获异常的线程
     * @param e 未捕获的异常，有了这个ex，我们就可以得到异常信息
     */
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        if (!catchCrashException(e) && mDefaultHandler != null) {
            //没有自定义的CrashHandler的时候就调用系统默认的异常处理方式
            mDefaultHandler.uncaughtException(t, e);
        } else {
            //退出应用
            killProcess();
        }
    }


    /**
     * 自定义错误处理,收集错误信息 发送错误报告等操作均在此完成.
     *
     * @param ex 未捕获的异常
     * @return true:如果处理了该异常信息;否则返回false.
     */
    private boolean catchCrashException(Throwable ex) {
        if (ex == null) {
            return false;
        }
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(mContext, "Duang~~崩啦~~崩啦~~~~", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        }.start();
        collectInfo(ex);
        //保存日志文件
        saveCrashInfo2File();
        //上传崩溃信息
        uploadCrashMessage(totalInfo);

        return true;
    }

    /**
     * 退出应用
     */
    private static void killProcess() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Log.e("application", "error : ", e);
        }
        // 退出程序
        AlarmManager mgr = (AlarmManager) MyApplication.getMyApplication().getSystemService(Context.ALARM_SERVICE);
        mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 2000, restartIntent); // 2秒钟后重启应用
//        Process.killProcess(Process.myPid());
        System.exit(1);
    }


    /**
     * 获取异常信息和设备参数信息
     */

    private void collectInfo(Throwable ex) {
        mExceptionInfo = collectExceptionInfo(ex);
        collectPackageInfo(mContext );
        collectBuildInfos();
        collectSystemInfos();
        collectSecureInfo();
        mMemInfo = collectMemInfo();

        totalInfo.put(EXCEPTION_INFO, mExceptionInfo);
        totalInfo.put(PACKAGE_INFO, mPackageInfo);
        totalInfo.put(DEVICE_INFO, mDeviceInfo);
        totalInfo.put(SYSTEM_INFO, mSecureInfo);
        totalInfo.put(SECURE_INFO, mSecureInfo);
        totalInfo.put(MEM_INFO, MEM_INFO);
    }

    /**
     * 获取捕获异常的信息
     */
    private String collectExceptionInfo(Throwable ex) {
        Writer mWriter = new StringWriter();
        PrintWriter mPrintWriter = new PrintWriter(mWriter);
        ex.printStackTrace(mPrintWriter);
        ex.printStackTrace();
        Throwable mThrowable = ex.getCause();
        // 迭代栈队列把所有的异常信息写入writer中
        while (mThrowable != null) {
            mThrowable.printStackTrace(mPrintWriter);
            // 换行 每个个异常栈之间换行
            mPrintWriter.append("\r\n");
            mThrowable = mThrowable.getCause();
        }
        // 记得关闭
        mPrintWriter.close();
        return mWriter.toString();
    }

    /**
     * 获取应用包参数信息
     */
    private void collectPackageInfo(Context ctx) {
        try {
            PackageManager pm = ctx.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(ctx.getPackageName(), PackageManager.GET_ACTIVITIES);
            if (pi != null) {
                String versionName = pi.versionName == null ? "null" : pi.versionName;
                String versionCode = pi.versionCode + "";
                infos.put("versionName", versionName);
                infos.put("versionCode", versionCode);
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "collectDeviceInfo() an error occured when collect package info NameNotFoundException:", e);
        }
        Field[] fields = Build.class.getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                infos.put(field.getName(), field.get(null).toString());
                Log.d(TAG, field.getName() + " : " + field.get(null));
            } catch (Exception e) {
                Log.e(TAG, "collectDeviceInfo() an error occured when collect crash info Exception:", e);
            }
        }
    }

    /**
     * 从系统属性中提取设备硬件和版本信息
     */
    private void collectBuildInfos() {
        // 反射机制
        Field[] mFields = Build.class.getDeclaredFields();
        // 迭代Build的字段key-value 此处的信息主要是为了在服务器端手机各种版本手机报错的原因
        for (Field field : mFields) {
            try {
                field.setAccessible(true);
                mDeviceInfo.put(field.getName(), field.get("").toString());
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取系统常规设定属性
     */
    private void collectSystemInfos() {
        Field[] fields = Settings.System.class.getFields();
        for (Field field : fields) {
            if (!field.isAnnotationPresent(Deprecated.class)
                    && field.getType() == String.class) {
                try {
                    String value = Settings.System.getString(mContext.getContentResolver(), (String) field.get(null));
                    if (value != null) {
                        mSystemInfo.put(field.getName(), value);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * 获取系统安全设置信息
     */
    private void collectSecureInfo() {
        Field[] fields = Settings.Secure.class.getFields();
        for (Field field : fields) {
            if (!field.isAnnotationPresent(Deprecated.class)
                    && field.getType() == String.class
                    && field.getName().startsWith("WIFI_AP")) {
                try {
                    String value = Settings.Secure.getString(mContext.getContentResolver(), (String) field.get(null));
                    if (value != null) {
                        mSecureInfo.put(field.getName(), value);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * 获取内存信息
     */


    // 用来存储设备信息和异常信息
    private Map<String, String> infos = new HashMap<>();
    private String collectMemInfo() {
//        BufferedReader br = null;
//        StringBuffer sb = new StringBuffer();
//
//        ArrayList<String> commandLine = new ArrayList<>();
//        commandLine.add("cat");
//        commandLine.add("/proc/meminfo");
////        commandLine.add(Integer.toString(Process.myPid()));
//
//        try {
//            java.lang.Process process = Runtime.getRuntime()
//                    .exec(commandLine.toArray(new String[commandLine.size()]));
//            br = new BufferedReader(new InputStreamReader(process.getInputStream()), 1024);
//
//            while (true) {
//                String line = br.readLine();
//                if (line == null) {
//                    break;
//                }
//                sb.append(line);
//                sb.append("\n");
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (br != null) {
//                try {
//                    br.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }

//        try {
//            PackageManager pm = mContext.getPackageManager();
//            PackageInfo pi = pm.getPackageInfo(mContext.getPackageName(),
//                    PackageManager.GET_ACTIVITIES);
//
//            if (pi != null) {
//                String versionName = pi.versionName == null ? "null"
//                        : pi.versionName;
//                String versionCode = pi.versionCode + "";
//                infos.put("versionName", versionName);
//                infos.put("versionCode", versionCode);
//            }
//        } catch (PackageManager.NameNotFoundException e) {
//            Log.e(TAG, "an error occured when collect package info", e);
//        }
//        Field[] fields = Build.class.getDeclaredFields();
//        for (Field field : fields) {
//            try {
//                field.setAccessible(true);
//                infos.put(field.getName(), field.get(null).toString());
//            } catch (Exception e) {
//                Log.e(TAG, "an error occured when collect crash info", e);
//            }
//        }
//        return "sb.toString()";


        try {
            PackageManager pm = mContext.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(mContext.getPackageName(),
                    PackageManager.GET_ACTIVITIES);
            if (pi != null ) {
                String versionName = pi.versionName == null ? "null"
                        : pi.versionName;
                String versionCode = pi.versionCode + "" ;
                infos.put( "versionName" , versionName);
                infos.put( "versionCode" , versionCode);
                infos.put( "crashTime" , formatter.format( new Date()));
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "an error occured when collect package info" , e);
        }
        Field[] fields = Build. class .getDeclaredFields();
        for (Field field: fields) {
            try {
                field.setAccessible( true );
                infos.put(field.getName(), field.get( null ).toString());
                Log.d(TAG, field.getName() + " : " + field.get( null ));
            } catch (Exception e) {
                Log.e(TAG, "an error occured when collect crash info" , e);
            }
        } return "sb.toString()";
    }

    /**
     * 将崩溃日志信息写入本地文件
     */
    private String saveCrashInfo2File() {
        StringBuffer mStringBuffer = getInfoStr(mPackageInfo);
        mStringBuffer.append(mExceptionInfo);
        // 保存文件，设置文件名
        String mTime = formatter.format(new Date());
        String mFileName = "crash----" + mTime + ".txt";
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            try {
                File mDirectory = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/hive-crash");
                if (!mDirectory.exists()) {
                    boolean success = mDirectory.mkdirs();
                }
                FileOutputStream mFileOutputStream = new FileOutputStream(mDirectory + File.separator + mFileName);
                mFileOutputStream.write(mStringBuffer.toString().getBytes());
                mFileOutputStream.close();
                return mFileName;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 将HashMap遍历转换成StringBuffer
     */
    @NonNull
    private static StringBuffer getInfoStr(ConcurrentHashMap<String, String> info) {
        StringBuffer mStringBuffer = new StringBuffer();
        for (Map.Entry<String, String> entry : info.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            mStringBuffer.append(key + "=" + value + "\r\n");
        }
        return mStringBuffer;
    }

    /**
     * 上传崩溃信息到服务器
     */
    private void uploadCrashMessage(ConcurrentHashMap<String, Object> info) {
        crashUploader.uploadCrashMessage(info);
    }

    /**
     * 崩溃信息上传接口回调
     */
    public interface CrashUploader {
        void uploadCrashMessage(ConcurrentHashMap<String, Object> info);
    }
//————————————————
//        版权声明：本文为CSDN博主「walkeryudev」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
//        原文链接：https://blog.csdn.net/walkeryudev/article/details/84562824
}
