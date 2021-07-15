package activity.com.myappdata.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import activity.com.myappdata.R;
import activity.com.myappdata.util.DialogUtil;
import activity.com.myappdata.util.PermissionHelper;
import activity.com.myappdata.util.PermissionInterface;

public class changperssionactivity extends AppCompatActivity implements View.OnClickListener, PermissionInterface {
    private int requestCode = 10000;
    private Button btn_phone;//打电话
    private Button btn_camera;//打开相机
    private PermissionHelper mPermissionHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changperssion);
        btn_phone = (Button) findViewById(R.id.btn_phone);
        btn_camera = (Button) findViewById(R.id.btn_camera);
        btn_phone.setOnClickListener(this);
        btn_camera.setOnClickListener(this);
        //初始化
        mPermissionHelper = new PermissionHelper(this, this);
        //发起权限申请
        mPermissionHelper.requestPermissions(new String[]{Manifest.permission.CALL_PHONE,
                Manifest.permission.CAMERA});

    }

    //点击监听
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_phone://打电话
                requestCode = 0;
                mPermissionHelper.requestPermissions(new String[]{Manifest.permission.CALL_PHONE});

                break;
            case R.id.btn_camera://打开相机
                requestCode = 1;
                mPermissionHelper.requestPermissions(new String[]{Manifest.permission.CAMERA});

                break;
        }

    }

    /**
     * 可设置请求权限请求码
     */
    @Override
    public int getPermissionsRequestCode() {
        //设置权限请求requestCode，只有不跟onRequestPermissionsResult方法中的其他请求码冲突即可。
        return requestCode;
    }


    /**
     * 请求权限成功回调
     */
    @Override
    public void requestPermissionsSuccess() {
        //权限请求用户已经全部允许
        if (requestCode == 10000) {

        } else if (requestCode == 0) {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            Uri data = Uri.parse("tel:" + 10086);
            intent.setData(data);
            startActivity(intent);
        } else if (requestCode == 1) {
            Intent intent1 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent1, 0);
        }

    }

    /**
     * 请求权限失败回调
     */
    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void requestPermissionsFail() {
        //权限请求不被用户允许。可以提示并退出或者提示权限的用途并重新发起权限申请。
        if (requestCode == 10000) {

        } else if (requestCode == 0) {
            //如果拒绝授予权限,且勾选了再也不提醒
            if (!shouldShowRequestPermissionRationale(Manifest.permission.CALL_PHONE)) {
                DialogUtil.showSelectDialog(this, "说明", "需要使用电话权限，进行电话测试", "取消", "确定", new DialogUtil.DialogClickListener() {
                    @Override
                    public void confirm() {
                        //用于在用户勾选“不再提示”并且拒绝时，再次提示用户
                        DialogUtil.showSelectDialog(changperssionactivity.this, "电话权限不可用", "请在-应用设置-权限中，允许APP使用电话权限来测试", "取消", "立即开启", new DialogUtil.DialogClickListener() {
                            @Override
                            public void confirm() {
                                goToAppSetting();
                            }

                            @Override
                            public void cancel() {

                            }
                        }).show();
                    }

                    @Override
                    public void cancel() {

                    }
                }).show();
            } else {
                DialogUtil.showSelectDialog(changperssionactivity.this, "电话权限不可用", "请在-应用设置-权限中，允许APP使用电话权限来测试", "取消", "立即开启", new DialogUtil.DialogClickListener() {
                    @Override
                    public void confirm() {
                        goToAppSetting();
                    }

                    @Override
                    public void cancel() {

                    }
                }).show();
            }

        } else if (requestCode == 1) {
            //如果拒绝授予权限,且勾选了再也不提醒
            if (!shouldShowRequestPermissionRationale(Manifest.permission.CALL_PHONE)) {
                DialogUtil.showSelectDialog(this, "说明", "需要使用相机权限，进行相机测试", "取消", "确定", new DialogUtil.DialogClickListener() {
                    @Override
                    public void confirm() {
                        //用于在用户勾选“不再提示”并且拒绝时，再次提示用户
                        DialogUtil.showSelectDialog(changperssionactivity.this, "相机权限不可用", "请在-应用设置-权限中，允许APP使用相机权限来测试", "取消", "立即开启", new DialogUtil.DialogClickListener() {
                            @Override
                            public void confirm() {
                                goToAppSetting();
                            }

                            @Override
                            public void cancel() {

                            }
                        }).show();
                    }

                    @Override
                    public void cancel() {

                    }
                }).show();
            } else {
                DialogUtil.showSelectDialog(changperssionactivity.this, "相机权限不可用", "请在-应用设置-权限中，允许APP使用相机权限来测试", "取消", "立即开启", new DialogUtil.DialogClickListener() {
                    @Override
                    public void confirm() {
                        goToAppSetting();
                    }

                    @Override
                    public void cancel() {

                    }
                }).show();
            }
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (mPermissionHelper.requestPermissionsResult(requestCode, permissions, grantResults)) {
            //权限请求结果，并已经处理了该回调
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    /**
     * 打开Setting
     */
    private void goToAppSetting() {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, 123);
    }

}

//————————————————
//版权声明：本文为CSDN博主「yushuangping」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
//原文链接：https://blog.csdn.net/yushuangping/article/details/83758957