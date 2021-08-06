package activity.com.myappdata.mvp.base.presentermvp.IPlacePresenterImpl;

import android.widget.Toast;

import activity.com.myappdata.mvp.base.mvp.interactor.LoginInteractor1;
import activity.com.myappdata.mvp.base.view.LoginView;
import activity.com.myappdata.net.LoggingInterceptor;
import activity.com.myappdata.util.ToastUtil;

/**
 * mvp的login  登陆监听
 */
public class LoginPresenter implements LoginInteractor1.OnLoginFinishedListener {

    private LoginView loginView;
    private LoginInteractor1 loginInteractor;

    public LoginPresenter(LoginView loginView, LoginInteractor1 loginInteractor) {
        this.loginView = loginView;
        this.loginInteractor = loginInteractor;
    }

    public void validateCredentials(String username, String password) {
        if (loginView != null) {
            loginView.showProgress();
        }

        loginInteractor.login(username, password, this);
    }

    public void onDestroy() {
        loginView = null;
    }

    @Override
    public void onUsernameError() {
        if (loginView != null) {
            loginView.setUsernameError();
            loginView.hideProgress();
        }
    }

    @Override
    public void onPasswordError() {
        if (loginView != null) {
            loginView.setPasswordError();
            loginView.hideProgress();
        }
    }

    @Override
    public void onSuccess() {
        if (loginView != null) {
            loginView.navigateToHome();
        }
    }

    @Override
    public void LoginFaile() {
        loginView.hideProgress();//  无法成功登陆后 隐藏进度条代码
        System.out.print("登陆失败");
    }

    @Override
    public void showFaile(String throwable) {
        System.out.print("登陆失败1111");
        loginView.showFaile(throwable);
    }
}
