package activity.com.myappdata.mvp.base.view.showmainactivity;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;


import activity.com.myappdata.R;
import activity.com.myappdata.mvp.base.utilsmvp.LoadingDialog;
import activity.com.myappdata.mvp.base.view.showmainactivity.base.BaseActivity;
import activity.com.myappdata.util.ToastUtils;
import butterknife.BindView;

public class NormalActivity extends BaseActivity<NormalContract.View,
        NormalPresent<NormalContract.View>> implements NormalContract.View {

    @BindView(R.id.bt_upload)
    Button mButtonUpload;

    @Override
    public void showDialog() {
        LoadingDialog.show(this);
    }

    @Override
    public void dismissDialog() {
        LoadingDialog.dismiss(this);
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_normal;
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void initDataAfter() {

    }

    @Override
    public void setListener() {
        mButtonUpload.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.bt_upload:
                new AlertDialog.Builder(this)
                        .setTitle("提示")
                        .setMessage("发现新版本是否更新")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mPresent.upDataApk(NormalActivity.this);
                            }
                        }).setNegativeButton("取消", null)
                        .create()
                        .show();
                break;
        }
    }

    @Override
    protected NormalPresent<NormalContract.View> createPresent() {
        return new NormalPresent<>(mContext);
    }

    @Override
    public void startDownLoad() {
        ToastUtils.show(NormalActivity.this,"开始下载");
    }
}
