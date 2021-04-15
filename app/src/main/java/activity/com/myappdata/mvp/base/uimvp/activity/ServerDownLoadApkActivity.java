package activity.com.myappdata.mvp.base.uimvp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import activity.com.myappdata.R;
import activity.com.myappdata.mvp.base.Contract.SerViceUpLoadContract;
import activity.com.myappdata.mvp.base.serviceupload.SerViceUpLoadPresent;
import activity.com.myappdata.mvp.base.utilsmvp.LoadingDialog;
import butterknife.BindView;

public class ServerDownLoadApkActivity extends fengzhuangBaseActivity<SerViceUpLoadContract.View,
        SerViceUpLoadPresent<SerViceUpLoadContract.View>> implements SerViceUpLoadContract.View, View.OnClickListener {
//    @BindView(R.id.bt_upload)
//    Button mButtonUpLoad;
//    @BindView(R.id.down_progress)
//    ProgressBar mDownLoadProgress;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
////        setContentView(R.layout.activity_server_down_load_apk);
//    }
//    @Override
//    public View bindView() {
//        return null;
//    }
//
//    @Override
//    public int bindLayout() {
//        return R.layout.activity_server_down_load_apk;
//    }
//
//    @Override
//    protected SerViceUpLoadPresent<SerViceUpLoadContract.View> createPresent() {
//        return new SerViceUpLoadPresent<>(this);
//    }
//
//    @Override
//    public void setProgress(Integer progress) {
//        mDownLoadProgress.setProgress(progress);
//    }
//
//    @Override
//    public void showDialog() {
////        LoadingDialog.show(mContext);
//    }
//
//    @Override
//    public void dismissDialog() {
////        LoadingDialog.dismiss(mContext);
//    }
//
//    @Override
//    public void widgetClick(View v) {
//        switch (v.getId()) {
//            case R.id.bt_upload:
//                mPresent.startUpLoadAPK(this);
//                break;
//        }
//    }

    @BindView(R.id.bt_upload1)
    Button mButtonUpLoad;
    ProgressBar mDownLoadProgress;

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_server_down_load_apk;
    }

    @Override
    public void initView(View view) {
        //绑定下载服务
        mPresent.bindPresentService();
    }

    @Override
    public void initDataAfter() {
    }

    @Override
    public void setListener() {
        mButtonUpLoad.setOnClickListener(ServerDownLoadApkActivity.this);
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.bt_upload1:
                mPresent.startUpLoadAPK(this);
                Toast.makeText(ServerDownLoadApkActivity.this, "点击事件成功", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    protected SerViceUpLoadPresent<SerViceUpLoadContract.View> createPresent() {
        return new SerViceUpLoadPresent<>(this);
    }

    @Override
    public void showDialog() {
        LoadingDialog.show(mContext);
    }

    @Override
    public void dismissDialog() {
        LoadingDialog.dismiss(mContext);
    }

    @Override
    public void setProgress(Integer progress) {
        mDownLoadProgress.setProgress(progress);
    }

}
