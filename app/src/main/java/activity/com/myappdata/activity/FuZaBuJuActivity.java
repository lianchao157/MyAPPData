package activity.com.myappdata.activity;

import android.graphics.Color;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import activity.com.myappdata.R;
import activity.com.myappdata.adapter.fuzaadapter.SectionedRecyclerViewAdapter;
import activity.com.myappdata.adapter.fuzaadapter.SectionedSpanSizeLookup;
import activity.com.myappdata.adapter.fuzaadapter.TestAdapter;
import activity.com.myappdata.adapter.fuzaadapter.base.Module;
import activity.com.myappdata.adapter.fuzaadapter.holder.LoadMoreListener;
import activity.com.myappdata.adapter.fuzaadapter.presenter.TestPresenter;
import activity.com.myappdata.adapter.fuzaadapter.utils.ListUtil;
import activity.com.myappdata.entity.TestEntity;
import activity.com.myappdata.widgets.SectionedGridDivider;

/*****
 *
 *
 * 复杂布局代码  验证适配器代码
 * recycleview
 *  使用
 */
public class FuZaBuJuActivity extends AppCompatActivity implements SwipeRefreshLayout
        .OnRefreshListener, Module.View {
    private TestAdapter mAdapter;
    private GridLayoutManager mGridLayoutManager;
    private SectionedGridDivider mDivider;
    private List<TestEntity.BodyBean.EListBean> mDatas = new ArrayList<>();
    private boolean isPull = true;//是否下拉刷新

    private LoadMoreListener loadMoreListener;

    private TestPresenter mPresenter;

    private RecyclerView rv;
    private SwipeRefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fu_za_bu_ju);
        mPresenter = new TestPresenter(this);
        rv = (RecyclerView) findViewById(R.id.rv);
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.swip_root);
        refreshLayout.setOnRefreshListener(this);
        initAdapter();
    }

    private void initAdapter() {
        mAdapter = new TestAdapter(mDatas, this);
        mGridLayoutManager = new GridLayoutManager(this, 3);
        mGridLayoutManager.setSpanSizeLookup(new SectionedSpanSizeLookup(mAdapter, mGridLayoutManager));
        rv.setLayoutManager(mGridLayoutManager);
        rv.setAdapter(mAdapter);
        mDivider = new SectionedGridDivider(this, 50, Color.parseColor("#F5F5F5"));
        rv.addItemDecoration(mDivider);

        loadMoreListener = new LoadMoreListener(mGridLayoutManager) {
            @Override
            public void onLoadMore() {
                isPull = false;
                isLoading = true;
                mAdapter.changeMoreStatus(SectionedRecyclerViewAdapter.LOADING_MORE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPresenter.loadData(1);
                    }
                }, 1000);
            }
        };
        rv.setOnScrollListener(loadMoreListener);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mPresenter.loadData(1);
            }
        }, 300);
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mPresenter.loadData(0);
            }
        }, 1000);
    }

    @Override
    public void showLoading(String msg) {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String errorMsg) {

    }

    @Override
    public void updateList(int type, List<TestEntity.BodyBean.EListBean> datas) {
        loadMoreListener.isLoading = false;
        if (refreshLayout.isRefreshing()) {
            refreshLayout.setRefreshing(false);
        }
        if (isPull) {
            if (!ListUtil.isEmpty(datas)) {
                mAdapter.getData().clear();
                mAdapter.notifyDataSetChanged();
                mAdapter.addMoreData(datas);
                if (loadMoreListener.isFullAScreen(rv)) {//显示item满一屏了
                    mAdapter.changeMoreStatus(SectionedRecyclerViewAdapter.PULLUP_LOAD_MORE);
                } else {
                    mAdapter.changeMoreStatus(SectionedRecyclerViewAdapter.LOADING_FINISH);
                }
                mAdapter.notifyDataSetChanged();
            } else {
                //显示空布局
            }
        } else {
            if (datas.size() > 0) {
                mAdapter.addMoreData(datas);
                mAdapter.changeMoreStatus(SectionedRecyclerViewAdapter.PULLUP_LOAD_MORE);
            } else {
                mAdapter.changeMoreStatus(SectionedRecyclerViewAdapter.LOADING_FINISH);
            }
        }

    }
}
