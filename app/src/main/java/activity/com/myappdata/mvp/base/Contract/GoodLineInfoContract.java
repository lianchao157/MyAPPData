package activity.com.myappdata.mvp.base.Contract;

import android.content.Context;

import activity.com.myappdata.mvp.base.modelmvp.GoodinfoLineModel;
import activity.com.myappdata.mvp.base.modelmvp.fengzhaungBaseModel;
import activity.com.myappdata.mvp.base.presentermvp.fengzhuangBasePresents;
import activity.com.myappdata.mvp.base.view.fengzhuangBaseView;
import io.reactivex.disposables.Disposable;

/***
 * mvp 模式下
 * 目的   显示你的activiyt  要做什么一目了然的可以看到
 */
public interface GoodLineInfoContract {
    interface View extends fengzhuangBaseView<MainContract.Present> {
        //登陆成功
        void logInOk(String str);

        //登录失败
        void logInError(String code);
    }

    //    interface View extends fengzhuangBaseView<Present> {
//        void setProgress(Integer progress);
//    }
    abstract class Present<T> extends fengzhuangBasePresents<View> {
        //绑定服务
        public abstract void bindPresentService();

        //开始更新APK
        public abstract void startUpLoadAPK(T t);

        //登录方法
        protected abstract void login(T t);
    }

    interface Model extends fengzhaungBaseModel {
        //绑定服务
        void bindModelService(Context context);

//        //开始下载
//        void RequesetData(Context mContext, GoodLineInfoContract.Model.LoginListener listener);

        //联网登录
        void loginNet(MainContract.Model.LoginListener listener);

        interface LoginListener {
            void success(String str);

            void error(String code);

        }
    }

}
