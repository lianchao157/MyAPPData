package activity.com.myappdata.mvp.base.uimvp.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import activity.com.myappdata.R;
import activity.com.myappdata.mvp.base.basemvp.BaseFragment;
import activity.com.myappdata.mvp.base.modelmvp.entity.Province;
import activity.com.myappdata.mvp.base.presentermvp.IPlacePresenter;
import activity.com.myappdata.mvp.base.presentermvp.IPlacePresenterImpl.PlacePresenterImpl;
import activity.com.myappdata.mvp.base.uimvp.activity.GoodInfoTypeSelectActivity;
import activity.com.myappdata.mvp.base.uimvp.activity.GoodsLineShowActivity;
import activity.com.myappdata.mvp.base.uimvp.adpter_mvp.PlaceAdapter;
import activity.com.myappdata.mvp.base.utilsmvp.LogUtils;
import activity.com.myappdata.mvp.base.view.IProvinceCallbask;

/**
 *
 * https://zhuanlan.zhihu.com/p/45701880
 *
 *
 * 自己的fragment
 */
public class MineFragment extends BaseFragment implements IProvinceCallbask {

    private RecyclerView mPlace;
    private PlaceAdapter mPlaceAdapter;
    private IPlacePresenter mPlacePresenter;
    private List<Province> provinceList=new ArrayList<>();
    private TextView tv_mine;
    private LinearLayout lin_tv_mine;//  商品选择类型的布局


    @Override
    protected int getLayoutInflaterResId() {
        return R.layout.fragment_item_goods_config_info;
    }

    @Override
    protected void initView(View rootView) {

        tv_mine= (TextView) rootView.findViewById(R.id.tv_mine);//  mvp  测试代码
        tv_mine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  i=new Intent(MineFragment.this.getActivity(), GoodsLineShowActivity.class);
                startActivity(i);
            }
        });
        // 创建布局管理器
        lin_tv_mine= (LinearLayout) rootView.findViewById(R.id.lin_tv_mine);

        lin_tv_mine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  i=new Intent(MineFragment.this.getActivity(), GoodInfoTypeSelectActivity.class);
                startActivity(i);
            }
        });
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
