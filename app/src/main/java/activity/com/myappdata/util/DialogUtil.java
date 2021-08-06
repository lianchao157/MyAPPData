package activity.com.myappdata.util;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * Created by YuShuangPing on 2018/11/5.   Android  6.0  的dialog  申请权限
 * 2021.5.14
 */
public class DialogUtil {
    /**
     * 创建一个选择对话框
     *
     * @param context
     * @param pContent            提示消息
     * @param dialogClickListener 点击监听
     * @return
     */
    public static Dialog showSelectDialog(Context context, String title, String pContent, String pLeftBtnStr,
                                          String pRightBtnStr,
                                          final DialogClickListener dialogClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        AlertDialog dialog = builder.setTitle(title)
                .setMessage(pContent)
                .setPositiveButton(pRightBtnStr, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialogClickListener.confirm();
                        dialog.dismiss();
                    }
                })
                .setNegativeButton(pLeftBtnStr, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialogClickListener.cancel();
                        dialog.dismiss();
//                        return;
                    }
                })
                .create();
        return dialog;
    }
 
 
    public interface DialogClickListener {
 
        public abstract void confirm();
 
        public abstract void cancel();
 
    }
}
//————————————————
//版权声明：本文为CSDN博主「yushuangping」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
//原文链接：https://blog.csdn.net/yushuangping/article/details/83758957