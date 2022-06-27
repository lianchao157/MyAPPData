package activity.com.myappdata.mvp.base.view.showmainactivity.searchview;

import android.content.Context;

import java.util.List;

import activity.com.myappdata.mvp.base.view.showmainactivity.NormalContract;
import activity.com.myappdata.mvp.base.view.showmainactivity.searchview.SearchMvpContract.Present;


/**
 * 作者：MarkShuai
 * 邮箱：MarkShuai@163.com
 * 意图：
 */

public class SearchMvpPresent<T> extends SearchMvpContract.Present<SearchMvpContract.View> {



    private Context mContext;
    private SearchMvpContract.Model  mModel1;

    public SearchMvpPresent(Context mContext) {
        this.mContext = mContext;
        mModel1 = new SearchMvpModel();
    }



    @Override
    public void Searchinfo(SearchMvpContract.View view) {
        mModel1.Searchinfo(mContext);
        view.Searchinfo();
    }

    @Override
    public  void SHyouLike(SearchMvpContract.View view) {
        mModel1.SHyouLike(mContext);
        view.SHyouLike();
    }

    @Override
    public void fetch() {

    }

    @Override
    public void onDestroy() {


    }
    public void onItemClicked(String item) {
        if (mModel1 != null) {
            mModel1.showMessage(String.format("%s clicked", item));
        }
    }
    public void onFinished(List<String> items) {
        if (mModel1 != null) {
            mModel1.setItems(items);
        }
    }
}
