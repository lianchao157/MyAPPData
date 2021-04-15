package activity.com.myappdata.mvp.base.utilsmvp;

import android.util.Log;

/***
 * log日志工具类代码
 */
public class LogUtils {

    private static int currentLev = 4;

    private static final Integer DEBUG_LEV = 4;
    private static final Integer INFO_LEV = 3;
    private static final Integer WARNING_LVE = 2;
    private static final Integer ERROR_LVE = 1;

    public static void d (Object object, String log) {
        if (currentLev >= DEBUG_LEV) {
            Log.d(object.getClass().getSimpleName(), log);
        }
    }
    public static void i (Object object, String log) {
        if (currentLev >= INFO_LEV) {
            Log.d(object.getClass().getSimpleName(), log);
        }
    }
    public static void w (Object object, String log) {
        if (currentLev >= WARNING_LVE) {
            Log.d(object.getClass().getSimpleName(), log);
        }
    }
    public static void e (Object object, String log) {
        if (currentLev >= ERROR_LVE) {
            Log.d(object.getClass().getSimpleName(), log);
        }
    }


}
