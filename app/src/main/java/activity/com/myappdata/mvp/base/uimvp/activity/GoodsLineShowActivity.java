package activity.com.myappdata.mvp.base.uimvp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

//import javax.inject.Inject;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import activity.com.myappdata.R;
import activity.com.myappdata.mvp.base.Contract.GoodLineInfoContract;
import activity.com.myappdata.mvp.base.modelmvp.GoodInfiLineEntity.GoodInfoLineInfoBase;
import activity.com.myappdata.mvp.base.modelmvp.GoodInfiLineEntity.GoodinfoLineRoot;
import activity.com.myappdata.mvp.base.modelmvp.GoodInfiLineEntity.PPShopGoodInfo;
import activity.com.myappdata.mvp.base.modelmvp.entity.City;
import activity.com.myappdata.mvp.base.modelmvp.entity.Component;
import activity.com.myappdata.mvp.base.modelmvp.entity.Goodemail;
import activity.com.myappdata.mvp.base.modelmvp.entity.LogisticsINfoBean;
import activity.com.myappdata.mvp.base.serviceupload.GoodLineinfoPresent;
//import activity.com.myappdata.mvp.base.uimvp.adpter_mvp.LogisticsInfoAdapter;
import activity.com.myappdata.util.ToastUtil;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.disposables.Disposable;
//import dagger.internal.DaggerCollections;

/***
 * 在mvp模式中实现
 * 快递到那里了的布局代码
 * 这是因为你要成功编译一次，DaggerXXXComponent才会自动生成，如果是Android Studio的话,快捷键Ctrl+F9编译就行了
 * https://www.jianshu.com/p/3dc10520f1f8
 *
 *
 * https://www.jianshu.com/p/b3a9c4a99053  布局使用url
 *
 *
 * mvp  解释代码 很重要
 * https://blog.csdn.net/Melect/article/details/79756943?utm_source=blogxgwz9
 *
 */
public class GoodsLineShowActivity extends fengzhuangBaseActivity<GoodLineInfoContract.View, GoodLineinfoPresent<GoodLineInfoContract.View>>
        implements GoodLineInfoContract.View, View.OnClickListener, GoodLineInfoContract.Model.LoginListener {
    //    @Inject
    City person;

//    private LogisticsInfoAdapter ladapter;
    @BindView(R.id.rv_logistics)
    RecyclerView rv_logistics;
    private List<LogisticsINfoBean> listgood = new ArrayList<LogisticsINfoBean>();
    List<Goodemail> Goodsinfo_data = new ArrayList<>();
    private Unbinder unbinder;
    ProgressBar mDownLoadProgress;
    private GoodLineinfoPresent GoodLineinfoPresent;
    @BindView(R.id.gls_wu_phoneno)//  官方电话
            TextView gls_wu_phoneno;
    @BindView(R.id.gls_wu_no)
    TextView gls_wu_no;//  快递编号
    @BindView(R.id.lin_shouw)
    ImageView lin_shouw;// 快递图片
    TextView textonclick;
    @BindView(R.id.gls_wu_state1)//  是否已经收取快递
            TextView gls_wu_state1;

    @BindView(R.id.gls_wu_company)
    TextView gls_wu_company;//  公司

    @OnClick(R.id.textonclick)
    public void click(View v) {
        mPresent.login(GoodsLineShowActivity.this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_line_show);
        unbinder = ButterKnife.bind(this);
//        DaggerActivityComponent.builder().build().inject(this);
//        版权声明：本文为CSDN博主「行走在青春路上的小蜜蜂」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
//        原文链接：https://blog.csdn.net/u010257931/article/details/104790257/
        mPresent.login(GoodsLineShowActivity.this);
        Goodsinfo_data.clear();
        LogisticsINfoBean logsticinfobean = new LogisticsINfoBean();
        Goodemail Goodemail = new Goodemail();
        Goodemail.setData("1");
        Goodemail.setName("2");
        Goodemail Goodemail1 = new Goodemail();
        Goodemail1.setData("3");
        Goodemail1.setName("4");
        Goodsinfo_data.add(Goodemail1);
        Goodsinfo_data.add(Goodemail);
        initView();
        GoodLineinfoPresent = new GoodLineinfoPresent(GoodsLineShowActivity.this);
        GoodLineinfoPresent.startUpLoadAPK(GoodsLineShowActivity.this);//请求数据
        System.out.print("");

    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
//        setContentView(R.layout.activity_goods_line_show);
        return R.layout.activity_goods_line_show;
    }

    @Override
    public void initView(View view) {
        textonclick = (TextView) findViewById(R.id.textonclick);
        textonclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresent.login(GoodsLineShowActivity.this);
            }
        });
    }

    @Override
    public void initDataAfter() {

    }

    @Override
    public void setListener() {
//        mButtonUpLoad.setOnClickListener(ServerDownLoadApkActivity.this);
    }

    @Override
    public void widgetClick(View v) {

    }


    private void initView() {
//        rv_logistics= (RecyclerView) findViewById(R.id.rv_logistics);
//        rv_logistics.setLayoutManager(new LinearLayoutManager(this));
//        //解决ScrollView嵌套RecyclerView出现的系列问题
//        rv_logistics.setNestedScrollingEnabled(false);
//        rv_logistics.setHasFixedSize(true);
//        ladapter = new LogisticsInfoAdapter(GoodsLineShowActivity.this, R.layout.goodinfo_lineitem, Goodsinfo_data);//  这里需要传递布局id
//        rv_logistics.setAdapter(ladapter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    /***
     * 获取数据的监听代码
     * @return
     */
    @Override
    protected GoodLineinfoPresent<GoodLineInfoContract.View> createPresent() {

        return new GoodLineinfoPresent<>(mContext);
    }


    @Override
    public void showDialog() {

    }

    @Override
    public void dismissDialog() {

    }

    List<GoodInfoLineInfoBase> list_GoodInfoLineInfoBase = new ArrayList<>();

    @Override
    public void success(String str) {

//        if (str.equals("") || null == str) {
//        } else {
//            Gson gson = new Gson();
//
//            GoodinfoLineRoot GoodinfoLineRoot = gson.fromJson(str, GoodinfoLineRoot.class);
//            List<PPShopGoodInfo> data = GoodinfoLineRoot.getData();
//            Log.e(TAG, "testd===" + data.size());
//            list_GoodInfoLineInfoBase.clear();
//            for (int i = 0; i < data.size(); i++) {
//            gls_wu_phoneno.setText(data.get(0).getPpshopGoodInfoPhone());
//            gls_wu_no.setText(data.get(0).getPpshopGoodInfono());
//            if (data.get(0).getPpshopGoodInfoState().equals("1")) {
//                gls_wu_state1.setText(" 未收");
//            } else {
//                gls_wu_state1.setText(" 已收");
//            }
//
//            Glide.with(this).load(data.get(0).getImage()).into(lin_shouw);
//
////             拿到详细的物流位置  快递到达位置和时间代码
//                GoodInfoLineInfoBase goodInfoLineInfoBase = new GoodInfoLineInfoBase();
//                goodInfoLineInfoBase.setPpshopGoodInfonoData(data.get(i).getPpshopGoodInfonoData());
//                goodInfoLineInfoBase.setPpshopGoodInfonoLocalcity(data.get(i).getPpshopGoodInfonoLocalcity());
//                list_GoodInfoLineInfoBase.add(goodInfoLineInfoBase);
//            }
//
//            rv_logistics.setLayoutManager(new LinearLayoutManager(this));
//            //解决ScrollView嵌套RecyclerView出现的系列问题
//            rv_logistics.setNestedScrollingEnabled(false);
//            rv_logistics.setHasFixedSize(true);
//            ladapter = new LogisticsInfoAdapter(GoodsLineShowActivity.this, R.layout.goodinfo_lineitem, list_GoodInfoLineInfoBase);//  这里需要传递布局id
//            rv_logistics.setAdapter(ladapter);
//        }
    }

    @Override
    public void error(String code) {

    }


    //     问题注释掉的没有显示
    @Override
    public void logInOk(String str) {
        if (str.equals("") || null == str) {
        } else {
            Gson gson = new Gson();

            GoodinfoLineRoot GoodinfoLineRoot = gson.fromJson(str, GoodinfoLineRoot.class);
            List<PPShopGoodInfo> data = GoodinfoLineRoot.getData();
            Log.e(TAG, "testd===" + data.size());
            list_GoodInfoLineInfoBase.clear();
            for (int i = 0; i < data.size(); i++) {
                gls_wu_phoneno.setText(data.get(0).getPpshopGoodInfoPhone());
                gls_wu_no.setText(data.get(0).getPpshopGoodInfono());
//                if (data.get(0).getPpshopGoodInfoState().equals("1")) {
//                    gls_wu_state1.setText(" 未收");
//                } else {
//                    gls_wu_state1.setText(" 已收");
//                }

//                Glide.with(this).load(data.get(0).getImage()).into(lin_shouw);

//             拿到详细的物流位置  快递到达位置和时间代码
                GoodInfoLineInfoBase goodInfoLineInfoBase = new GoodInfoLineInfoBase();
                goodInfoLineInfoBase.setPpshopGoodInfonoData(data.get(i).getPpshopGoodInfonoData());
                goodInfoLineInfoBase.setPpshopGoodInfonoLocalcity(data.get(i).getPpshopGoodInfonoLocalcity());
                list_GoodInfoLineInfoBase.add(goodInfoLineInfoBase);
            }

            rv_logistics.setLayoutManager(new LinearLayoutManager(this));
            //解决ScrollView嵌套RecyclerView出现的系列问题
            rv_logistics.setNestedScrollingEnabled(false);
            rv_logistics.setHasFixedSize(true);
//            ladapter = new LogisticsInfoAdapter(GoodsLineShowActivity.this, R.layout.goodinfo_lineitem, list_GoodInfoLineInfoBase);//  这里需要传递布局id
//            rv_logistics.setAdapter(ladapter);
        }
    }

    @Override
    public void logInError(String code) {

    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
