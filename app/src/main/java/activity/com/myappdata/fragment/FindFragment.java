package activity.com.myappdata.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.google.gson.Gson;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import activity.com.myappdata.R;
import activity.com.myappdata.activity.TestActivity;
import activity.com.myappdata.adapter.FindGridAdapter;
import activity.com.myappdata.adapter.RecycleviewAdapter;
import activity.com.myappdata.api.retorfitpack.GetRequest_Interface;
import activity.com.myappdata.bean.FindFragmentMenumBase;
import activity.com.myappdata.bean.FindFragmetnMenuTitleEntity;
import activity.com.myappdata.bean.TabTitleBrandEntity;
import activity.com.myappdata.entity.EntityReccleview;
import activity.com.myappdata.net.RetrofitManager;
import activity.com.myappdata.util.ToastUtil;
import activity.com.myappdata.util.ToastUtils;
import activity.com.myappdata.widgets.SectionedGridDivider;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lianchao on 2021/1/7.
 * <p>
 * 实现典型分组复杂列表适配器；
 * 实现分组分割线；
 * 实现上拉加载的功能，添加回调，添加滑动的容差值；
 * 实现列数的控制；
 * 支持设置空布局，动态监听数据
 * 归纳复杂布局场景，使得通用性更为广泛
 */
//Recycleview   实现复杂布局
//https://www.cnblogs.com/aademeng/articles/98206
// https://github.com/gycold/SectionRecyclerViewAdapter

//Android 实现多种样式 item 列表
//    https://blog.csdn.net/xiangshiweiyu_hd/article/details/108123154


//        Gson gson = new Gson();
//        FindFragmetnMenuTitleEntity = gson.fromJson(jsonString, FindFragmetnMenuTitleEntity.class);
//        System.out.print("!!!!!!!!!!!" + FindFragmetnMenuTitleEntity);

public class FindFragment extends Fragment {
    //    private RecyclerView tabfragmentrv;
    private RecyclerView rvxianshiqianggou;// 限时枪口

    private RecycleviewAdapter mRecycleviewAdapter;
    private List<EntityReccleview> recyclelist = new ArrayList<>(); //  限时抢购
    private List<TabTitleBrandEntity> tabTitleBrandEntitieslist = new ArrayList<TabTitleBrandEntity>();//  品牌顶部

    private static final String TAG = "FindFragment";
    private static final String typestr = "str";
    private static final String typeentity = "tity";
    private SectionedGridDivider mDivider;//   分割线代码

    private GridView gv;//   底部菜单gradview


    private FindGridAdapter findGridAdapter;

    private List<FindFragmetnMenuTitleEntity> FindFragmetnMenuTitlelist = new ArrayList<FindFragmetnMenuTitleEntity>();//   顶部菜单  数据集合

    public FindFragment() {
    }

    public static FindFragment newInstance(String text) {
        Bundle bundle = new Bundle();
        bundle.putString("text", text);
        FindFragment TabFragment = new FindFragment();
        TabFragment.setArguments(bundle);
        return TabFragment;
    }

    public static Fragment newInstance() {
        FindFragment fragment = new FindFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_find, container, false);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(FindFragment.this.getActivity(), 2, GridLayoutManager.VERTICAL, false);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        revisionRecycler.setLayoutManager(gridLayoutManager);

//        LinearLayoutManager manager = new LinearLayoutManager(FindFragment.this.getActivity(),3, GridLayoutManager.HORIZONTAL, false);
        rvxianshiqianggou = (RecyclerView) rootView.findViewById(R.id.rv_xianshiqianggou);
        rvxianshiqianggou.setLayoutManager(gridLayoutManager);
        recyclelist.clear();
        LoadData();

        mRecycleviewAdapter = new RecycleviewAdapter(FindFragment.this.getActivity(), recyclelist);
        rvxianshiqianggou.setAdapter(mRecycleviewAdapter);

        mDivider = new SectionedGridDivider(FindFragment.this.getActivity(), 50, Color.parseColor("#F5F5F5"));
        rvxianshiqianggou.addItemDecoration(mDivider);

        FindFragmetnMenuTitlelist.clear();

        gv = (GridView) rootView.findViewById(R.id.gv);
        initJsonData();
        getData();
        LoadMenumGridviewData();
        return rootView;
    }

    /***
     * 获取东部菜单数据
     */
    private void LoadMenumGridviewData() {


    }

    private void LoadData() {
        EntityReccleview entityReccleview = new EntityReccleview();
        entityReccleview.setIamgetext("http://file02.16sucai.com/d/file/2014/0704/e53c868ee9e8e7b28c424b56afe2066d.jpg");
        entityReccleview.setImagetextrae("1111");
        entityReccleview.setImagurl("111");
        entityReccleview.setProrate(15 / 100);
        entityReccleview.setStrrate("1");
        EntityReccleview entityReccleview1 = new EntityReccleview();
        entityReccleview1.setIamgetext("http://file02.16sucai.com/d/file/2014/0704/e53c868ee9e8e7b28c424b56afe2066d.jpg");
        entityReccleview1.setImagetextrae("1111");
        entityReccleview1.setImagurl("111");
        entityReccleview1.setProrate(15 / 100);
        entityReccleview1.setStrrate("1");
        TabTitleBrandEntity tabTitleBrandEntity = new TabTitleBrandEntity();
        TabTitleBrandEntity tabTitleBrandEntity2 = new TabTitleBrandEntity();
        tabTitleBrandEntity.setTabtitleStr("xiao小米");
        tabTitleBrandEntity.setTabtitltImage("http://file02.16sucai.com/d/file/2014/0704/e53c868ee9e8e7b28c424b56afe2066d.jpg");
        tabTitleBrandEntity2.setTabtitleStr("xiao小米2");
        tabTitleBrandEntity2.setTabtitltImage("http://file02.16sucai.com/d/file/2014/0704/e53c868ee9e8e7b28c424b56afe2066d.jpg");
        for (int i = 0; i < 20; i++) {
            tabTitleBrandEntitieslist.add(tabTitleBrandEntity);
            tabTitleBrandEntitieslist.add(tabTitleBrandEntity2);
            recyclelist.add(entityReccleview);
            recyclelist.add(entityReccleview1);
        }

    }

    class MyAdapter extends FragmentPagerAdapter {
        private List<String> list;

        public MyAdapter(FragmentManager fm, List<String> list) {
            super(fm);
            this.list = list;
        }

        @Override
        public Fragment getItem(int position) {
            return FindFragment.newInstance(list.get(position));
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return list.get(position);
        }
    }


    public void ShowView() {

        Log.e(TAG, "111");
    }

    /***
     * 获取数据  设置超时
     */
    private static final OkHttpClient client = new OkHttpClient.Builder().
            connectTimeout(60, TimeUnit.SECONDS).
            readTimeout(60, TimeUnit.SECONDS).
            writeTimeout(60, TimeUnit.SECONDS).build();

    //本地数据测试专用
    private String jsonString = "2";

    /***
     * 获取数据顶部菜单
     */
    private void initJsonData() {
        new Handler().postDelayed(() -> {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(client)
                    .baseUrl("http://192.168.1.6:8988") // 设置 网络请求 Url  http://localhost:8988
                    .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
//                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();

            // 步骤5:创建 网络请求接口 的实例
            GetRequest_Interface request = retrofit.create(GetRequest_Interface.class);

            //对 发送请求 进行封装
            Call<FindFragmentMenumBase> call = request.getTitleMenumdata("0");
            call.enqueue(new Callback<FindFragmentMenumBase>() {
                @Override
                public void onResponse(Call<FindFragmentMenumBase> call, Response<FindFragmentMenumBase> response) {
                    if (null == response || response.equals("")) {
                        Log.e(TAG, "我想我是空");
                        ToastUtils.show(FindFragment.this.getActivity(),""+response);
                    } else {
                        String msg = response.message();
                        List<FindFragmetnMenuTitleEntity> resultlist = response.body().getData();
                        for (int i = 0; i < resultlist.size(); i++) {
                            Log.e(TAG, "获取数据代码state" + resultlist.get(i).getPp_menum_state());
                            Log.e(TAG, "获取数据代码mage" + resultlist.get(i).getPp_menum_textimage());
                        }
                        FindFragmetnMenuTitlelist.clear();
                        FindFragmetnMenuTitlelist = resultlist;
                        Log.e(TAG, "获取数据代码" + msg);
                        getData();
                    }
                }

                @Override
                public void onFailure(Call<FindFragmentMenumBase> call, Throwable t) {
                    Log.e(TAG, "失败：：：：s" + t);
                }
            });


        }, 2000);
//        try {
//
//            InputStream is = FindFragment.this.getActivity().getAssets().open("grawview.json");//打开json数据
//            byte[] by = new byte[is.available()];//转字节
//            is.read(by);
//            jsonString = new String(by, "utf-8");
//            is.close();//关闭流
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    //解析Json数据
    private void getData() {
        if (null == FindFragmetnMenuTitlelist) {
            Log.e(TAG, "失败FindFragmetnMenuTitlelist==null");

        } else {
//            int size = FindFragmetnMenuTitlelist.size();
//            DisplayMetrics dm = new DisplayMetrics();
//            float density = dm.density;
//            int allWidth = (int) (110 * size * density);
//            int itemWidth = (int) (100 * density);
//            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
//                    allWidth, LinearLayout.LayoutParams.FILL_PARENT);
//            gv.setLayoutParams(params);// 设置GirdView布局参数
//            gv.setColumnWidth(itemWidth);// 列表项宽
//            gv.setHorizontalSpacing(13);// 列表项水平间距
//            gv.setStretchMode(GridView.NO_STRETCH);
//————————————————
//            版权声明：本文为CSDN博主「李帅磊」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
//            原文链接：https://blog.csdn.net/lishuaileibo/article/details/78942198
            findGridAdapter = new FindGridAdapter(FindFragment.this.getActivity(), FindFragmetnMenuTitlelist);
            gv.setAdapter(findGridAdapter);
            gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    switch (position) {
                        case 0:
                            ToastUtil.makeText(FindFragment.this.getActivity(), "点击事件");
                            startActivity(new Intent(FindFragment.this.getActivity(), TestActivity.class));
                            break;

                    }
                }
            });
        }
    }


}
