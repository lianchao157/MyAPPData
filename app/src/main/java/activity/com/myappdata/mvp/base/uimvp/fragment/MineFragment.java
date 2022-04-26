package activity.com.myappdata.mvp.base.uimvp.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import activity.com.myappdata.R;
import activity.com.myappdata.mvp.base.basemvp.BaseFragment;
import activity.com.myappdata.mvp.base.modelmvp.entity.Province;
import activity.com.myappdata.mvp.base.modelmvp.mvploginentity.mvpuserinfolitit.UserinfoBywebData;
import activity.com.myappdata.mvp.base.presentermvp.IPlacePresenter;
import activity.com.myappdata.mvp.base.presentermvp.IPlacePresenterImpl.PlacePresenterImpl;
import activity.com.myappdata.mvp.base.uimvp.activity.GoodInfoTypeSelectActivity;
import activity.com.myappdata.mvp.base.uimvp.activity.GoodsLineShowActivity;
import activity.com.myappdata.mvp.base.uimvp.adpter_mvp.PlaceAdapter;
import activity.com.myappdata.mvp.base.uimvp.adpter_mvp.UserInfoAdapter;
import activity.com.myappdata.mvp.base.utilsmvp.LogUtils;
import activity.com.myappdata.mvp.base.view.IProvinceCallbask;

/**
 * https://zhuanlan.zhihu.com/p/45701880
 * <p>
 * <p>
 * 自己的fragment
 */
public class MineFragment extends BaseFragment implements IProvinceCallbask, View.OnClickListener {

    private TextView tv_mine;
    private LinearLayout lin_tv_mine;//  商品选择类型的布局
    private Button my_logoutbtn;//  登出系统
    private LinearLayout setpowerlin; //设置预留

    @Override
    protected int getLayoutInflaterResId() {
        return R.layout.fragment_item_goods_config_info;
    }

    @Override
    protected void initView(View rootView) {

        tv_mine = (TextView) rootView.findViewById(R.id.tv_mine);//  mvp  测试代码
        tv_mine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MineFragment.this.getActivity(), GoodsLineShowActivity.class);
                startActivity(i);
            }
        });
        // 创建布局管理器
        lin_tv_mine = (LinearLayout) rootView.findViewById(R.id.lin_tv_mine);

        lin_tv_mine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MineFragment.this.getActivity(), GoodInfoTypeSelectActivity.class);
                startActivity(i);
            }
        });
        my_logoutbtn = (Button) rootView.findViewById(R.id.my_logout);
        my_logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MineFragment.this.getActivity().finish();
            }
        });
        setpowerlin = (LinearLayout) rootView.findViewById(R.id.setpowerlin);
        setpowerlin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MineFragment.this.getActivity(), GoodInfoTypeSelectActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected void initPresenter() {
        // TODO: 创建 PlacePresenter 对象
    }

    @Override
    protected void loadData() {
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void loadedDatabyuser(List<UserinfoBywebData> provinceList) {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        }
    }
}
