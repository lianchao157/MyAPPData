package activity.com.myappdata.mvp.base.uimvp.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import activity.com.myappdata.R;
import activity.com.myappdata.mvp.base.basemvp.BaseFragment;
import activity.com.myappdata.mvp.base.modelmvp.entity.Province;
import activity.com.myappdata.mvp.base.presentermvp.IPlacePresenter;
import activity.com.myappdata.mvp.base.presentermvp.IPlacePresenterImpl.PlacePresenterImpl;
import activity.com.myappdata.mvp.base.uimvp.adpter_mvp.PlaceAdapter;
import activity.com.myappdata.mvp.base.utilsmvp.LogUtils;
import activity.com.myappdata.mvp.base.view.IProvinceCallbask;

/**
 *
 * https://zhuanlan.zhihu.com/p/45701880
 */
public class PlaceFragment extends BaseFragment implements IProvinceCallbask {

    private RecyclerView mPlace;
    private PlaceAdapter mPlaceAdapter;
    private IPlacePresenter mPlacePresenter;
    private List<Province> provinceList=new ArrayList<>();
    private TextView mvpteset;


    @Override
    protected int getLayoutInflaterResId() {
        return R.layout.fragment_place;
    }

    @Override
    protected void initView(View rootView) {
        mPlace = (RecyclerView) rootView.findViewById(R.id.place);

        mvpteset= (TextView) rootView.findViewById(R.id.mvpteset);//  mvp  测试代码
        // 创建布局管理器
        mPlace.setLayoutManager(new LinearLayoutManager(getContext()));
        Province   p=new Province();

        p.setId(1);
        p.setName("张三");
        Province   p1=new Province();
        provinceList.add(p);
        p1.setId(1);
        p1.setName("张三");
        provinceList.add(p1);
        mPlaceAdapter = new PlaceAdapter(provinceList);
//        mPlaceAdapter = new PlaceAdapter();
        // 创建适配器
        mPlace.setAdapter(mPlaceAdapter);
        mvpteset.setText("MVP");
    }

    @Override
    protected void initPresenter() {
        // TODO: 创建 PlacePresenter 对象
        mPlacePresenter = new PlacePresenterImpl();
        mPlacePresenter.registerViewCallback(this);
    }

    @Override
    protected void loadData() {
        mPlacePresenter.getProvinList();
    }

    @Override
    public void loadedData(List<Province> provinceList) {
        // 数据从这里回来
        LogUtils.d(this,  "loadedData" + provinceList.toString());
        if (provinceList != null) {
            mPlaceAdapter.setmData(provinceList);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPlacePresenter != null) {
            mPlacePresenter.unRegisterViewCallback(this);
        }
    }
}
