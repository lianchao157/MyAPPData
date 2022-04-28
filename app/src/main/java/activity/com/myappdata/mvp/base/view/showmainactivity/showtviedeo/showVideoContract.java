package activity.com.myappdata.mvp.base.view.showmainactivity.showtviedeo;

import android.content.Context;

import activity.com.myappdata.mvp.base.view.showmainactivity.NormalContract;
import activity.com.myappdata.mvp.base.view.showmainactivity.base.BaseModel;
import activity.com.myappdata.mvp.base.view.showmainactivity.base.BasePresent;
import activity.com.myappdata.mvp.base.view.showmainactivity.base.BaseView;

/***
 *接口展示
 */
public interface showVideoContract {

    interface View extends BaseView<showVideoContract.Present> {
        void showvideo();
    }
    abstract   class Present<T> extends BasePresent<showVideoContract.View>{
        abstract void showvideo(T t);
    }
    interface Model extends BaseModel {
        void showvideo(Context context);
    }
}
