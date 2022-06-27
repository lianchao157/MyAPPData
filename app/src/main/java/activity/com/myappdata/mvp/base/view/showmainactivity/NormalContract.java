package activity.com.myappdata.mvp.base.view.showmainactivity;

import android.content.Context;

import activity.com.myappdata.mvp.base.view.showmainactivity.base.BaseModel;
import activity.com.myappdata.mvp.base.view.showmainactivity.base.BasePresent;
import activity.com.myappdata.mvp.base.view.showmainactivity.base.BaseView;


/**
 * 作者：MarkShuai
 * 时间：2017/12/19 15:36
 * 邮箱：MarkShuai@163.com
 * 意图：
 */

public interface NormalContract {
    interface View extends BaseView<Present> {
        void startDownLoad();
    }

    abstract class Present<T> extends BasePresent<View> {
        public abstract void upDataApk(T t);
    }

    interface Model extends BaseModel {
        void upDataApk(Context context);
    }
}
