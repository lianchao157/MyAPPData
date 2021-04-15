package activity.com.myappdata.mvp.base.Contract;

import android.content.Context;

import activity.com.myappdata.mvp.base.modelmvp.fengzhaungBaseModel;
import activity.com.myappdata.mvp.base.presentermvp.fengzhuangBasePresents;
import activity.com.myappdata.mvp.base.view.fengzhuangBaseView;
import io.reactivex.disposables.Disposable;

/**
 * 每次在我们进行调用的时候我是喜欢写这样的一个接口，因为这样的话后期方便你的管理，你直接从Contract中就看了大概你这个Activity做了些什么事情。
 */
public interface SerViceUpLoadContract {
    interface View extends fengzhuangBaseView<Present> {
        void setProgress(Integer progress);
    }

    abstract class Present<T> extends fengzhuangBasePresents<View> {
        //绑定服务
        public abstract void bindPresentService();
        //开始更新APK
        public abstract void startUpLoadAPK(T t);
    }


    interface Model extends fengzhaungBaseModel {
        //绑定服务
        void bindModelService(Context context);

        //开始下载
        void startUpLoad(Context mContext, ProgressListener listener);

        //观察者的监听接口回调
        interface ProgressListener {

            void onSubscribeProgress(Disposable d);

            void onNextProgress(Integer progress);

            void onErrorProgress(Throwable throwable);

            void onCompleteProgress();
        }
    }
}
