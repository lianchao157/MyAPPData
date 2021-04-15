package activity.com.myappdata.util;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by lianchao on 2021/1/7.
 */



//跳转页面的工具类
public class JumperUtils {
    public static void JumpTo(Activity activity, Class<?> cls) {
        try {
            Intent intent = new Intent(activity, cls);
            activity.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public static void JumpTo(Activity activity, Class<?> cls, Bundle bundle) {
        try {
            Intent intent = new Intent(activity, cls);
            intent.putExtras(bundle);
            activity.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
    /**
     * startActivityForResult，跳�?
     * @param activity
     * @param cls
     * @param bundle
     * @param requestCode
     */
    public static void JumpToForResult(Activity activity, Class<?> cls, Bundle bundle, int requestCode) {
        try {
            Intent intent = new Intent(activity, cls);
            intent.putExtras(bundle);
            activity.startActivityForResult(intent,requestCode);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    /**
     * startActivityForResult，跳�?
     * @param activity
     * @param cls
     * @parambundle
     * @param requestCode
     */
    public static void JumpToForResult(Activity activity, Class<?> cls, int requestCode) {
        try {
            Intent intent = new Intent(activity, cls);
            activity.startActivityForResult(intent,requestCode);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
}
