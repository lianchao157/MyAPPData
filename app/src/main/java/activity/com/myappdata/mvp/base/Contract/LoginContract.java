package activity.com.myappdata.mvp.base.Contract;

import activity.com.myappdata.adapter.fuzaadapter.base.BasePresenter;
import activity.com.myappdata.adapter.fuzaadapter.base.BaseView;
import activity.com.myappdata.mvp.base.modelmvp.entity.UserInfo;

public interface  LoginContract {

    interface View extends BaseView {
        /**
         * 跳转Home
         */
        void goHome();
    }

    interface Presenter extends BasePresenter {
        /**
         * login
         * @param phone
         * @param password
         */
        void onLogin(String phone, String password);
    }

    interface Interactor {
        /**
         * do login
         * @param phone
         * @param password
         * @param callback
         */
        void doLogin(String phone, String password, LoginCallback callback);
        interface LoginCallback {
            void onSuccess(UserInfo user_info);
            void onFailure(String msg);
        }

        /**
         * 是否登录
         * @return
         */
        boolean isLogin();

        /**
         * 获取当前登录用户
         * @return
         */
        UserInfo getLoginUser();
    }
}
