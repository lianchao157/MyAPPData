package activity.com.myappdata.util;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import activity.com.myappdata.R;

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
//    底部显示dialog的方法
    private static Dialog dialog;
    private static View inflate;
    static TextView tv_take_pic;
    static  TextView tv_take_photo;
    //中间显示的dialog
    public static void showCentreDialogbyBoom(Context context) {
        //自定义dialog显示布局
        inflate = LayoutInflater.from(context).inflate(R.layout.showlayoutdialoge, null);
        tv_take_photo= (TextView) inflate.findViewById(R.id.tv_take_photo);
        tv_take_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

         tv_take_pic= (TextView) inflate.findViewById(R.id.tv_take_pic);
        tv_take_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        TextView tv_cancel= (TextView) inflate.findViewById(R.id.tv_cancel);
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogUtil.close();
            }
        });
        //自定义dialog显示风格
        dialog = new Dialog(context, R.style.DialogCentre);
        //点击其他区域消失
        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentView(inflate);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.CENTER;
        wlp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(wlp);
        dialog.show();
    }

    //关闭dialog时调用
    public static void close() {
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
    }
    AlertDialog alertDialog;

    private DialogButtonClick mClick;
    public interface DialogButtonClick {
        void cilckComfirmButton(View view);
        void cilckCancleButton(View view);
    }

    public void buttonClickEvent(DialogButtonClick bc){
        if(bc != null){
            mClick = bc;
            if (tv_take_pic != null) {
                tv_take_pic.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mClick.cilckCancleButton(v);
                    }
                });
                tv_take_photo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mClick.cilckComfirmButton(v);
                    }
                });
            }
            cilckEvent();
        }
    }

    private void cilckEvent() {


    }
}
//————————————————
//版权声明：本文为CSDN博主「yushuangping」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
//原文链接：https://blog.csdn.net/yushuangping/article/details/83758957