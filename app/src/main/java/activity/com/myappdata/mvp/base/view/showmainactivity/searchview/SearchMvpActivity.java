package activity.com.myappdata.mvp.base.view.showmainactivity.searchview;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.List;

import activity.com.myappdata.R;
import activity.com.myappdata.mvp.base.uimvp.adpter_mvp.MainAdapter;
import activity.com.myappdata.mvp.base.uimvp.fragment.HotFFragment;
import activity.com.myappdata.mvp.base.utilsmvp.LoadingDialog;
import activity.com.myappdata.mvp.base.view.showmainactivity.base.BaseActivity;
import butterknife.BindView;

/***
 * 查询页面 仿写  京东查询页面
 */
public class SearchMvpActivity extends BaseActivity <SearchMvpContract.View,
        SearchMvpPresent<SearchMvpContract.View>> implements SearchMvpContract.View {
    @BindView(R.id.main_searchview)
    SearchView main_searchview;
    @BindView(R.id.main_listview)
    ListView  main_listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_mvp);
        getWindow().setSoftInputMode( WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_search_mvp;
    }

    @Override
    public void initView(View view) {

    }

    @Override
    protected void onResume() {
        mPresent.SHyouLike(SearchMvpActivity.this);

        super.onResume();
    }

    @Override
    public void initDataAfter() {

    }
    public void setItems(List<String> items) {
        main_listview.setAdapter((ListAdapter) new MainAdapter(items, mPresent::onItemClicked));
        LoadingDialog.dismiss(SearchMvpActivity.this);

    }

    @Override
    public void setListener() {
        main_searchview.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.bt_upload:
                mPresent.Searchinfo(SearchMvpActivity.this);
                break;
        }
    }

    /// 创建present
    @Override
    protected SearchMvpPresent<SearchMvpContract.View> createPresent() {
        return new SearchMvpPresent<>(mContext);
    }



    @Override
    public void Searchinfo() {

    }

    @Override
    public void SHyouLike() {

    }

    @Override
    public void showDialog() {
        LoadingDialog.show(this);
    }

    @Override
    public void dismissDialog() {
        LoadingDialog.dismiss(this);
    }
}