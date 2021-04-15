package activity.com.myappdata.mvp.base.view;

//登陆时候的view
public interface LoginView {
    void showProgress();

    void hideProgress();

    void setUsernameError();

    void setPasswordError();

    void navigateToHome();
    void showFaile(String throwable);
}
