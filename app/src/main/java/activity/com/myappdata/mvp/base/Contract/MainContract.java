package activity.com.myappdata.mvp.base.Contract;


import activity.com.myappdata.mvp.base.modelmvp.fengzhaungBaseModel;
import activity.com.myappdata.mvp.base.presentermvp.fengzhuangBasePresents;
import activity.com.myappdata.mvp.base.view.fengzhuangBaseView;

/**
 * 作者：MarkShuai
 * 时间：2017/12/19 14:46
 * 邮箱：MarkShuai@163.com
 * 意图：
 */

public interface MainContract {

    interface View extends fengzhuangBaseView<Present> {
        //登陆成功
        void logInOk(String str);
        //登录失败
        void logInError(String code);
    }

    abstract class Present<T> extends fengzhuangBasePresents<View> {
        //跳转到普通下载更新
        public abstract void jumpNormal();
        //使用服务下载更新
        public abstract void jumpService();
        //登录方法
        protected abstract void login(T t);
    }

    interface Model extends fengzhaungBaseModel {
        //联网登录
        void loginNet(LoginListener listener);

        //登陆成功失败的监听
        interface LoginListener {
            void success(String str);

            void error(String code);
        }
    }

}
