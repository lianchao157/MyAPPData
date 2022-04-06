package activity.com.myappdata.mvp.base.Contract;

import java.util.List;

import activity.com.myappdata.adapter.fuzaadapter.base.BasePresenter;
import activity.com.myappdata.adapter.fuzaadapter.base.BaseView;
import activity.com.myappdata.mvp.base.modelmvp.entity.UserInfo;
import activity.com.myappdata.mvp.base.modelmvp.entity.videopack.VideoListEntity;

/***
 *    展示vidosholist 的想要做的事  意图类代码
 *    2021.11.20 周一
 */
interface VideoShowListContract {
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
         * @param
         * @param
         *
         * @param callback
         */
        void  getlist( VideoShowListContract.Interactor.getlistCallback callback);
        interface getlistCallback {
            void onSuccess(List<VideoListEntity>  list);
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
        List<VideoListEntity>  getListData();
    }
}
