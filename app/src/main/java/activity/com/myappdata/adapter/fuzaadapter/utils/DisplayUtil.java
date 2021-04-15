package activity.com.myappdata.adapter.fuzaadapter.utils;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowInsets;
import android.view.WindowManager;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
/**
 * Description： 屏幕工具
 */
public class DisplayUtil {


        /**
         * 获取屏幕宽度
         *
         * @param context 上下文对象
         * @return 像素值
         */
        public static int getScreenWidthPx(Context context) {
            WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            DisplayMetrics dm = new DisplayMetrics();
            wm.getDefaultDisplay().getMetrics(dm);
            return dm.widthPixels;
        }



        /**
         * 获取屏幕高度
         *
         * @param context 上下文对象
         * @return 像素值
         */
        public static int getScreenHeightPx(Context context) {
            WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            DisplayMetrics dm = new DisplayMetrics();
            wm.getDefaultDisplay().getMetrics(dm);
            return dm.heightPixels;
        }

        /**
         * 屏幕宽度（dp）
         * @param context 上下文对象
         * @return DP值
         */
        public static int getScreenWidthDp(Context context) {
            WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            DisplayMetrics dm = new DisplayMetrics();
            wm.getDefaultDisplay().getMetrics(dm);

            int width = dm.widthPixels;         // 屏幕宽度（像素）
            float density = dm.density;         // 屏幕密度（0.75 / 1.0 / 1.5）
            int screenWidth = (int) (width / density);  // 屏幕宽度(dp)

            return screenWidth;
        }

        /**
         *屏幕高度（dp）
         * @param context  上下文对象
         * @return DP值
         */
        public static int getScreenHeightDp(Context context) {
            WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            DisplayMetrics dm = new DisplayMetrics();
            wm.getDefaultDisplay().getMetrics(dm);

            int height = dm.heightPixels;       // 屏幕高度（像素）
            float density = dm.density;         // 屏幕密度（0.75 / 1.0 / 1.5）
            int screenHeight = (int) (height / density);// 屏幕高度(dp)

            return screenHeight;
        }

        /**
         * 密度（0.75 / 1.0 / 1.5）
         * @param context
         * @return
         */
        public static float getScreenDensity(Context context) {
            WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            DisplayMetrics dm = new DisplayMetrics();
            wm.getDefaultDisplay().getMetrics(dm);
            return dm.density;
        }

        /**
         * 像素密度dpi（120 / 160 / 240）
         * @param context
         * @return
         */
        public static float getScreenDensityDpi(Context context) {
            WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            DisplayMetrics dm = new DisplayMetrics();
            wm.getDefaultDisplay().getMetrics(dm);
            return dm.densityDpi;
        }

        /**
         * dp转px
         *
         * @param context 上下文
         * @param dpValue dp值
         * @return
         */
        public static int dip2px(Context context, float dpValue) {
            final float scale = context.getResources().getDisplayMetrics().density;
            return (int) (dpValue * scale + 0.5f);
        }

        /**
         * px 转 dp
         *
         * @param context 上下文
         * @param pxValue px值
         * @return
         */
        public static int px2dip(Context context, float pxValue) {
            final float scale = context.getResources().getDisplayMetrics().density;
            return (int) (pxValue / scale + 0.5f);
        }

        /**
         * 判断是否是刘海屏
         *
         * @return
         */
//        public static boolean hasNotchScreen(Activity activity) {
//            if (getInt("ro.miui.notch", activity) == 1 || hasNotchAtHuawei(activity) ||
//                    hasNotchAtOPPO(activity)
//                    || hasNotchAtVivo(activity) || isAndroidP(activity) != null) { //TODO 各种品牌
//                return true;
//            }
//
//            return false;
//        }

        /**
         * Android P 刘海屏判断
         *
         * @param activity
         * @return
         */
//        public static DisplayCutout isAndroidP(Activity activity) {
//            View decorView = activity.getWindow().getDecorView();
//            if (decorView != null && android.os.Build.VERSION.SDK_INT >= 28) {
//                WindowInsets windowInsets = decorView.getRootWindowInsets();
//                if (windowInsets != null)
//                    return windowInsets.getDisplayCutout();
//            }
//            return null;
//        }

        /**
         * 小米刘海屏判断.
         *
         * @return 0 if it is not notch ; return 1 means notch
         * @throws IllegalArgumentException if the key exceeds 32 characters
         */
        public static int getInt(String key, Activity activity) {
            int result = 0;

            try {
                ClassLoader classLoader = activity.getClassLoader();
                @SuppressWarnings("rawtypes")
                Class SystemProperties = classLoader.loadClass("android.os.SystemProperties");
                //参数类型
                @SuppressWarnings("rawtypes")
                Class[] paramTypes = new Class[2];
                paramTypes[0] = String.class;
                paramTypes[1] = int.class;
                Method getInt = SystemProperties.getMethod("getInt", paramTypes);
                //参数
                Object[] params = new Object[2];
                params[0] = new String(key);
                params[1] = new Integer(0);
                result = (Integer) getInt.invoke(SystemProperties, params);

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

            return result;
        }

        /**
         * 华为刘海屏判断
         *
         * @return
         */
        public static boolean hasNotchAtHuawei(Context context) {
            boolean ret = false;
            try {
                ClassLoader classLoader = context.getClassLoader();
                Class HwNotchSizeUtil = classLoader.loadClass("com.huawei.android.util.HwNotchSizeUtil");
                Method get = HwNotchSizeUtil.getMethod("hasNotchInScreen");
                ret = (boolean) get.invoke(HwNotchSizeUtil);
            } catch (ClassNotFoundException e) {
                Log.e("Huawei", "hasNotchAtHuawei ClassNotFoundException");
            } catch (NoSuchMethodException e) {
                Log.e("Huawei", "hasNotchAtHuawei NoSuchMethodException");
            } catch (Exception e) {
                Log.e("Huawei", "hasNotchAtHuawei Exception");
            } finally {
                return ret;
            }
        }

        public static final int VIVO_NOTCH = 0x00000020;//是否有刘海
        public static final int VIVO_FILLET = 0x00000008;//是否有圆角

        /**
         * VIVO刘海屏判断
         *
         * @return
         */
        public static boolean hasNotchAtVivo(Context context) {
            boolean ret = false;
            try {
                ClassLoader classLoader = context.getClassLoader();
                Class FtFeature = classLoader.loadClass("android.util.FtFeature");
                Method method = FtFeature.getMethod("isFeatureSupport", int.class);
                ret = (boolean) method.invoke(FtFeature, VIVO_NOTCH);
            } catch (ClassNotFoundException e) {
                Log.e("Vivo", "hasNotchAtVivo ClassNotFoundException");
            } catch (NoSuchMethodException e) {
                Log.e("Vivo", "hasNotchAtVivo NoSuchMethodException");
            } catch (Exception e) {
                Log.e("Vivo", "hasNotchAtVivo Exception");
            } finally {
                return ret;
            }
        }

        /**
         * OPPO刘海屏判断
         *
         * @return
         */
        public static boolean hasNotchAtOPPO(Context context) {
            return context.getPackageManager().hasSystemFeature("com.oppo.feature.screen.heteromorphism");
        }

    }
