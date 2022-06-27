package activity.com.myappdata.mvp.base.uimvp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import activity.com.myappdata.R;
import activity.com.myappdata.mvp.base.Contract.MainContract;
import activity.com.myappdata.mvp.base.serviceupload.MainPresent;
import activity.com.myappdata.util.ToastUtils;

public class DemoLoginByMVPActivity extends fengzhuangBaseActivity<MainContract.View, MainPresent<MainContract.View>> implements MainContract.View {

    private Button btn_mvpdatateset;//  按键测试

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        btn_mvpdatateset = (Button) findViewById(R.id.btn_mvpdatateset);
        btn_mvpdatateset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresent.login(DemoLoginByMVPActivity.this);
            }
        });

    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_demo_login_by_mvp;
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void initDataAfter() {

    }

    @Override
    public void setListener() {

    }

    @Override
    public void widgetClick(View v) {

    }

    @Override
    protected MainPresent<MainContract.View> createPresent() {
        return new MainPresent<>(mContext);
    }

    @Override
    public void logInOk(String str) {
        ToastUtils.show(DemoLoginByMVPActivity.this, str);
        btn_mvpdatateset.setText(str);
    }

    @Override
    public void logInError(String code) {
        ToastUtils.show(DemoLoginByMVPActivity.this, code);
    }

    @Override
    public void showDialog() {

    }


    @Override
    public void dismissDialog() {

    }
}
