package activity.com.myappdata.mvp.base.uimvp.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import activity.com.myappdata.R;
import activity.com.myappdata.adapter.fuzaadapter.base.Module;
import activity.com.myappdata.entity.TestEntity;
import activity.com.myappdata.jingdongtuijian.MyAdapter;
import activity.com.myappdata.mvp.base.basemvp.BaseFragment;
import activity.com.myappdata.mvp.base.modelmvp.entity.Province;
import activity.com.myappdata.mvp.base.modelmvp.mvploginentity.mvpuserinfolitit.UserinfoBywebData;
import activity.com.myappdata.mvp.base.mvp.interactor.FindItemsInteractor;
import activity.com.myappdata.mvp.base.presentermvp.IPlacePresenterImpl.MainPresenter;
import activity.com.myappdata.mvp.base.uimvp.activity.ServerDownLoadApkActivity;
import activity.com.myappdata.mvp.base.uimvp.adpter_mvp.MainAdapter;
import activity.com.myappdata.mvp.base.uimvp.adpter_mvp.PlaceAdapter;
import activity.com.myappdata.mvp.base.utilsmvp.LoadingDialog;
import activity.com.myappdata.mvp.base.view.IProvinceCallbask;
import activity.com.myappdata.mvp.base.view.MainView;
import activity.com.myappdata.mvp.base.uimvp.fragment.hotfragmentpresenterimpl.HotfragmentpresentImple;
import activity.com.myappdata.mvp.base.view.showmainactivity.searchview.SearchMvpActivity;
import activity.com.myappdata.uiutils.ClockView;
import activity.com.myappdata.util.ActivitySkipUtil;
import activity.com.myappdata.util.LogUtil;
import activity.com.myappdata.util.ToastUtil;
import butterknife.ButterKnife;
/***
 * 1   https://www.pianshen.com/article/3470354071/  mvp
 *  代码
 *  2  动态布局https://blog.csdn.net/u014608640/article/details/86636729
 *
 */
public class HotFFragment extends BaseFragment implements IProvinceCallbask, MainView, SwipeRefreshLayout
        .OnRefreshListener, Module.View, View.OnClickListener {
    private String TAG = "HotFFragment";
    private RecyclerView mPlace;
    private PlaceAdapter mPlaceAdapter;
    private HotfragmentpresentImple HotfragmentpresentImple;
    private List<Province> provinceList = new ArrayList<>();
    private TextView mvpteset;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private MainPresenter presenter;
    private Button btn_down;
    private SmartRefreshLayout refreshLayoutfindfragmetn;//  上拉刷新下拉加载更多
    private Context mContext;
    private ImageView imageViewscan;//需要查询的页面  仿写京东

    //    @InjectView(R.id.toolbar)
    public HotFFragment() {
    }

    @SuppressLint("HandlerLeak")
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    int count = msg.arg1;
                default:
                    break;
            }
        }
    };

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
//                 当前的下载mvp
                Intent i = new Intent(HotFFragment.this.getActivity(), ServerDownLoadApkActivity.class);
                startActivity(i);
                SimpleDateFormat dateFormat = new SimpleDateFormat("'IMG'_yyyyMMdd_HHmmssSSS");
            }
        });
        mContext = HotFFragment.this.mContext;
        mPlace = (RecyclerView) rootView.findViewById(R.id.list);
        progressBar = (ProgressBar) rootView.findViewById(R.id.progress);
        // 创建布局管理器
        mPlace.setLayoutManager(new LinearLayoutManager(HotFFragment.this.getActivity()));
        presenter = new MainPresenter(this, new FindItemsInteractor());
        refreshLayoutfindfragmetn = (SmartRefreshLayout) rootView.findViewById(R.id.refreshLayoutfindfragmetn);
//        refreshLayoutfindfragmetn.setOnRefreshListener((OnRefreshListener) HotFFragment.this);//  r  refreshLayout在fragmen使用
        refreshLayoutfindfragmetn.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {

            }
        });
        imageViewscan = (ImageView) rootView.findViewById(R.id.iv_localcity);
        imageViewscan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.makeText(HotFFragment.this.getActivity(), "扫描");
                ActivitySkipUtil.skipAnotherActivity(HotFFragment.this.getActivity(), SearchMvpActivity.class);
            }
        });
        backgroundAlpha(1f);// 跳整屏幕亮度


//        画图

        RelativeLayout layout = (RelativeLayout) rootView.findViewById(R.id.main);
//        layout.addView(new DrawGeometryView(HotFFragment.this.getActivity()));
        layout.addView(new ClockView(HotFFragment.this.getActivity()));// 时钟
//        Paint mPaint = new Paint();
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
//                R.drawable.ic_launcher_background);
//        canvas.drawBitmap(bitmap,100,50,mPaint);
//        drawTranslate(canvas)；

//         进度条
        LoadingDialog.show(HotFFragment.this.getActivity());

        EditText etinfo = (EditText) rootView.findViewById(R.id.etinfo);
        String ed = etinfo.getText().toString().trim();
    }


    @Override
    protected void initPresenter() {
        // TODO: 创建 PlacePresenter 对象
        HotfragmentpresentImple = new HotfragmentpresentImple();
        HotfragmentpresentImple.registerViewCallback(this);
        LoadingDialog.dismiss(HotFFragment.this.getActivity());
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
        LoadingDialog.dismiss(HotFFragment.this.getActivity());
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
        recyclerView.setVisibility(View.VISIBLE);
        LoadingDialog.dismiss(HotFFragment.this.getActivity());
    }

    @Override
    public void setItems(List<String> items) {
        recyclerView.setAdapter(new MainAdapter(items, presenter::onItemClicked));
        LoadingDialog.dismiss(HotFFragment.this.getActivity());

    }

    @Override
    public void showMessage(String message) {
        LogUtil.d(TAG, "message" + message);
        LoadingDialog.dismiss(HotFFragment.this.getActivity());
    }


    @Override
    public void onRefresh() {
        LogUtil.d(TAG, "onRefresh");
        LoadingDialog.dismiss(HotFFragment.this.getActivity());
    }

    @Override
    public void updateList(int type, List<TestEntity.BodyBean.EListBean> datas) {
        LoadingDialog.dismiss(HotFFragment.this.getActivity());
        LogUtil.d(TAG, "updateList" + datas.size());
    }

    @Override
    public void showLoading(String msg) {
        LoadingDialog.dismiss(HotFFragment.this.getActivity());
        LogUtil.d(TAG, "showLoading" + msg);
    }

    @Override
    public void hideLoading() {
        LoadingDialog.dismiss(HotFFragment.this.getActivity());
        LogUtil.d(TAG, "hideLoading");
    }

    @Override
    public void showError(String errorMsg) {
        LogUtil.d(TAG, "数据异常" + errorMsg);
        LogUtil.d(TAG, "数据为null");
        String json = getJson(HotFFragment.this.getActivity(), "category.json");
        LogUtil.d(TAG, "json=============================================" + json);
        LoadingDialog.dismiss(HotFFragment.this.getActivity());
    }

    @Override   // 创建适配器
    public void loadedDatabyuser(List<UserinfoBywebData> provinceList) {
        if (provinceList == null || provinceList.size() == 0) {
            LogUtil.d(TAG, "数据为null");
            String json = getJson(HotFFragment.this.getActivity(), "category.json");
            LogUtil.d(TAG, "json=============================================" + json);


        } else {
            mPlace.setLayoutManager(new LinearLayoutManager(HotFFragment.this.getActivity()));
            mPlace.setAdapter(mPlaceAdapter);
        }


    }

    /**
     * 得到json文件中的内容
     *
     * @param context
     * @param fileName
     * @return
     */
    public static String getJson(Context context, String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        //获得assets资源管理器
        AssetManager assetManager = context.getAssets();
        //使用IO流读取json文件内容
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName), "utf-8"));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    /****
     * 变高亮的代码
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = HotFFragment.this.getActivity().getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        HotFFragment.this.getActivity().getWindow().setAttributes(lp);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_localcity:
                HotFFragment.this.getActivity().finish();
                break;
            default:
                break;
        }
    }
}