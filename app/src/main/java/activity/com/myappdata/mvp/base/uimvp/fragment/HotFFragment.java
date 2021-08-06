package activity.com.myappdata.mvp.base.uimvp.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import activity.com.myappdata.R;
import activity.com.myappdata.adapter.fuzaadapter.base.Module;
import activity.com.myappdata.entity.TestEntity;
import activity.com.myappdata.mvp.base.basemvp.BaseFragment;
import activity.com.myappdata.mvp.base.modelmvp.entity.Province;
import activity.com.myappdata.mvp.base.modelmvp.mvploginentity.mvpuserinfolitit.UserinfoBywebData;
import activity.com.myappdata.mvp.base.mvp.interactor.FindItemsInteractor;
import activity.com.myappdata.mvp.base.presentermvp.IPlacePresenterImpl.MainPresenter;
import activity.com.myappdata.mvp.base.uimvp.activity.ServerDownLoadApkActivity;
import activity.com.myappdata.mvp.base.uimvp.adpter_mvp.MainAdapter;
import activity.com.myappdata.mvp.base.uimvp.adpter_mvp.PlaceAdapter;
import activity.com.myappdata.mvp.base.view.IProvinceCallbask;
import activity.com.myappdata.mvp.base.view.MainView;
import activity.com.myappdata.mvp.base.uimvp.fragment.hotfragmentpresenterimpl.HotfragmentpresentImple;
/***
 *  https://www.pianshen.com/article/3470354071/  mvp
 *  代码
 *
 *
 *  2  动态布局https://blog.csdn.net/u014608640/article/details/86636729
 *
 */
public class HotFFragment extends BaseFragment implements IProvinceCallbask, MainView, SwipeRefreshLayout
        .OnRefreshListener,Module.View {

    private RecyclerView mPlace;
    private PlaceAdapter mPlaceAdapter;
    private HotfragmentpresentImple HotfragmentpresentImple;
    private List<Province> provinceList = new ArrayList<>();
    private TextView mvpteset;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private MainPresenter presenter;
    private Button btn_down;
    private SmartRefreshLayout refreshLayoutfindfragmetn ;//  上拉刷新下拉加载更多
    private Context mContext;

    private ImageView imageViewscan;//需要查询的页面  仿写京东
    public HotFFragment() {
    }

    @Override
    protected int getLayoutInflaterResId() {
        return R.layout.fragment_hot;
    }

    @Override
    protected void initView(View rootView) {
        btn_down = (Button) rootView.findViewById(R.id.btn_down);
        btn_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HotFFragment.this.getActivity(), ServerDownLoadApkActivity.class);
                startActivity(i);
            }
        });
        mContext=HotFFragment.this.mContext;
        mPlace = (RecyclerView) rootView.findViewById(R.id.list);
        progressBar = (ProgressBar) rootView.findViewById(R.id.progress);
        // 创建布局管理器
        mPlace.setLayoutManager(new LinearLayoutManager(HotFFragment.this.getActivity()));
        presenter = new MainPresenter(this, new FindItemsInteractor());
        refreshLayoutfindfragmetn= (SmartRefreshLayout) rootView.findViewById(R.id.refreshLayoutfindfragmetn);
//        refreshLayoutfindfragmetn.setOnRefreshListener((OnRefreshListener) HotFFragment.this);//  r  refreshLayout在fragmen使用
        refreshLayoutfindfragmetn.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {

            }
        });
//
//        Province p = new Province();
//
//        p.setId(1);
//        p.setName("张三");
//        Province p1 = new Province();
//        provinceList.add(p);
//        p1.setId(1);
//        p1.setName("张三");
//        provinceList.add(p1);
//        provinceList.add(p);
//        mPlaceAdapter = new PlaceAdapter(provinceList);
////        mPlaceAdapter = new PlaceAdapter();
//        // 创建适配器
//        mPlace.setLayoutManager(new LinearLayoutManager(HotFFragment.this.getActivity()));
//        mPlace.setAdapter(mPlaceAdapter);



    }
    @Override
    protected void initPresenter() {
        // TODO: 创建 PlacePresenter 对象
        HotfragmentpresentImple = new HotfragmentpresentImple();
        HotfragmentpresentImple.registerViewCallback(this);
    }


    @Override
    public void onResume() {
        super.onResume();


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void setItems(List<String> items) {

        recyclerView.setAdapter(new MainAdapter(items, presenter::onItemClicked));

    }

    @Override
    public void showMessage(String message) {
    }

//    @Override
//    public void loadedData(List<Province> provinceList) {
//
//    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void updateList(int type, List<TestEntity.BodyBean.EListBean> datas) {

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
    public void loadedData(List<UserinfoBywebData> provinceList) {

        System.out.print(provinceList.size());
    }
}
