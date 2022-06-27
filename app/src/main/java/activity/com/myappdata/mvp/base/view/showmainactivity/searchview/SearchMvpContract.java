package activity.com.myappdata.mvp.base.view.showmainactivity.searchview;

import android.content.Context;

import java.util.List;

import activity.com.myappdata.mvp.base.view.showmainactivity.base.BaseModel;
import activity.com.myappdata.mvp.base.view.showmainactivity.base.BasePresent;
import activity.com.myappdata.mvp.base.view.showmainactivity.base.BaseView;

/***
 * mvp 下的搜索框
 */
public interface SearchMvpContract {
    interface View extends BaseView<Present> {
        void Searchinfo();
        void SHyouLike();
    }

    public abstract class Present<T> extends BasePresent<View> {
        public  abstract void Searchinfo(T t);
        public  abstract void SHyouLike(T t);
    }

     interface Model extends BaseModel {
         void Searchinfo(Context context);
         void SHyouLike(Context context);
         void setItems(List<String> items);
         void showMessage(String message);
     }
}
