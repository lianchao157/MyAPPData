package activity.com.myappdata.mvp.base.uimvp.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import activity.com.myappdata.R;
import activity.com.myappdata.mvp.base.mvp.interactor.LoginInteractor1;
import activity.com.myappdata.mvp.base.presentermvp.IPlacePresenterImpl.LoginPresenter;
import activity.com.myappdata.mvp.base.view.LoginView;
import activity.com.myappdata.util.LogUtil;
import activity.com.myappdata.util.ToastUtil;

/***
 * mvp模式下的登陆代码
 * https://github.com/antoniolg/androidmvp/tree/master/app  项目地址
 * 已完成
 */
public class MVP_LoginActivity extends AppCompatActivity implements LoginView {

    private ProgressBar progressBar;
    private EditText username;
    private EditText password;
    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp__login);
        progressBar = (ProgressBar) findViewById(R.id.progress);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        findViewById(R.id.button).setOnClickListener(v -> validateCredentials());

        presenter = new LoginPresenter(this, new LoginInteractor1());
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setUsernameError() {
        username.setError(getString(R.string.username_error));
    }

    @Override
    public void setPasswordError() {
        password.setError(getString(R.string.password_error));
    }

    @Override
    public void navigateToHome() {
        SharedPreferences sp = MVP_LoginActivity.this.getSharedPreferences("user_info", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("name", username.getText().toString());
        editor.putString("imageurl", password.getText().toString());
//        editor.putBoolean("iscredit", user.isCredit());
//        editor.putString("phone",user.getPhone());
        editor.commit();//必须提交，否则保存不成功
        startActivity(new Intent(this, MainMvpActivity.class));
        finish();
    }

    @Override
    public void showFaile(String throwable) {
        ToastUtil.makeText(MVP_LoginActivity.this,"登录失败"+throwable);
        LogUtil.e("MVP_LoginActivity::::::::::::ssssssss",""+throwable);
    }



    private void validateCredentials() {
        presenter.validateCredentials(username.getText().toString(), password.getText().toString());
    }
}
