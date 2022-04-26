package activity.com.myappdata.mvp.base.uimvp.activity.scnner;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Vibrator;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import activity.com.myappdata.R;
import activity.com.myappdata.mvp.base.uimvp.activity.BaseActivty_MVP_Activity;
import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zbar.ZBarView;

/***
 * 扫描工具类
 */
public class Scannerctivity extends BaseActivty_MVP_Activity
//        implements View.OnClickListener
{
    private ImageView title_back;
    private TextView title_name;
    private ZBarView qrCodeView;
    String[] permissions = new String[]{
            Manifest.permission.CAMERA,
            Manifest.permission.VIBRATE
    };
    private final int permissionCode = 100;//权限请求码

    //    原文链接：https://blog.csdn.net/a497785609/article/details/104759787
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scannerctivity);
    }

    @Override
    protected void initView() {
        qrCodeView = (ZBarView) findViewById(R.id.zbarview);
//        title_back= (ImageView) findViewById(R.id.title_back);
//        title_name= (TextView) findViewById(R.id.title_name);
//        title_name.setOnClickListener(Scannerctivity.this);
    }

    //检查权限
    private void checkPermission() {
        List<String> permissionList = new ArrayList<>();
        for (int i = 0; i < permissions.length; i++) {
            if (ContextCompat.checkSelfPermission(this, permissions[i]) != PackageManager.PERMISSION_GRANTED) {
                permissionList.add(permissions[i]);
            }
        }
        if (permissionList.size() <= 0) {
            //说明权限都已经通过，可以做你想做的事情去
            bindEvent();
        } else {
            //存在未允许的权限
            ActivityCompat.requestPermissions(this, permissions, permissionCode);
        }
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_scannerctivity;
    }

//    @Override
//    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.title_back:
//                break;
//        }
//    }


    private void bindEvent() {
        qrCodeView = (ZBarView) findViewById(R.id.zbarview);
        qrCodeView.setDelegate(new QRCodeView.Delegate() {
            @Override
            public void onScanQRCodeSuccess(String result) {
                vibrate();//震动手机
                //扫描成功后处理事件
                Toast.makeText(Scannerctivity.this, result, Toast.LENGTH_SHORT).show();
                qrCodeView.startSpot();//继续扫描
                TextView txtText = (TextView) findViewById(R.id.projectNameTv);
                txtText.setText(result);
            }

            public void onCameraAmbientBrightnessChanged(boolean isDark) {
                // 这里是通过修改提示文案来展示环境是否过暗的状态，接入方也可以根据 isDark 的值来实现其他交互效果
                String tipText = qrCodeView.getScanBoxView().getTipText();
                String ambientBrightnessTip = "\n环境过暗，请打开闪光灯";
                if (isDark) {
                    if (!tipText.contains(ambientBrightnessTip)) {
                        qrCodeView.getScanBoxView().setTipText(tipText + ambientBrightnessTip);
                    }
                } else {
                    if (tipText.contains(ambientBrightnessTip)) {
                        tipText = tipText.substring(0, tipText.indexOf(ambientBrightnessTip));
                        qrCodeView.getScanBoxView().setTipText(tipText);
                    }
                }
            }

            @Override
            public void onScanQRCodeOpenCameraError() {
                Toast.makeText(Scannerctivity.this, "错误", Toast.LENGTH_SHORT).show();
            }
        });
        qrCodeView.startCamera();

//        findViewById(R.id.start_spot).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onStart();
//                Toast.makeText(Page_QR.this, "开始扫码", Toast.LENGTH_SHORT).show();
//            }
//
//        });
//
//        findViewById(R.id.stop_spot).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                onStop();
//                Toast.makeText(Page_QR.this, "停止扫码", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        findViewById(R.id.open_flashlight).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                qrCodeView.openFlashlight();
//                Toast.makeText(Page_QR.this, "打开闪光灯", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        findViewById(R.id.close_flashlight).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                qrCodeView.closeFlashlight();
//                Toast.makeText(Page_QR.this, "关闭闪光灯", Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    private void vibrate() {
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(200);
    }

}