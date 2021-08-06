package activity.com.myappdata.util;

/**
 * 权限请求接口
 * Created by YuShuangPing on 2018/11/1.
 */
public interface PermissionInterface {

    /**
     * 可得到请求权限请求码
     */
    int getPermissionsRequestCode();

    /**
     * 请求权限成功回调
     */
    void requestPermissionsSuccess();

    /**
     * 请求权限失败回调
     */
    void requestPermissionsFail();
}

    //————————————————
//版权声明：本文为CSDN博主「yushuangping」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
//原文链接：https://blog.csdn.net/yushuangping/article/details/83758957