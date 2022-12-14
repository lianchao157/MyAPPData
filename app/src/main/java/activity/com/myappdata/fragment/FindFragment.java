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
 * ??????????????????????????????????????????
 * ????????????????????????
 * ????????????????????????????????????????????????????????????????????????
 * ????????????????????????
 * ??????????????????????????????????????????
 * ??????????????????????????????????????????????????????
 */
//Recycleview   ??????????????????
//https://www.cnblogs.com/aademeng/articles/98206
// https://github.com/gycold/SectionRecyclerViewAdapter

//Android ?????????????????? item ??????
//    https://blog.csdn.net/xiangshiweiyu_hd/article/details/108123154


//        Gson gson = new Gson();
//        FindFragmetnMenuTitleEntity = gson.fromJson(jsonString, FindFragmetnMenuTitleEntity.class);
//        System.out.print("!!!!!!!!!!!" + FindFragmetnMenuTitleEntity);

public class FindFragment extends Fragment {
    //    private RecyclerView tabfragmentrv;
    private RecyclerView rvxianshiqianggou;// ????????????

    private RecycleviewAdapter mRecycleviewAdapter;
    private List<EntityReccleview> recyclelist = new ArrayList<>(); //  ????????????
    private List<TabTitleBrandEntity> tabTitleBrandEntitieslist = new ArrayList<TabTitleBrandEntity>();//  ????????????

    private static final String TAG = "FindFragment";
    private static final String typestr = "str";
    private static final String typeentity = "tity";
    private SectionedGridDivider mDivider;//   ???????????????

    private GridView gv;//   ????????????gradview


    private FindGridAdapter findGridAdapter;

    private List<FindFragmetnMenuTitleEntity> FindFragmetnMenuTitlelist = new ArrayList<FindFragmetnMenuTitleEntity>();//   ????????????  ????????????

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
     * ????????????????????????
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
        tabTitleBrandEntity.setTabtitleStr("xiao??????");
        tabTitleBrandEntity.setTabtitltImage("http://file02.16sucai.com/d/file/2014/0704/e53c868ee9e8e7b28c424b56afe2066d.jpg");
        tabTitleBrandEntity2.setTabtitleStr("xiao??????2");
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
     * ????????????  ????????????
     */
    private static final OkHttpClient client = new OkHttpClient.Builder().
            connectTimeout(60, TimeUnit.SECONDS).
            readTimeout(60, TimeUnit.SECONDS).
            writeTimeout(60, TimeUnit.SECONDS).build();

    //????????????????????????
    private String jsonString = "2";

    /***
     * ????????????????????????
     */
    private void initJsonData() {
        new Handler().postDelayed(() -> {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(client)
                    .baseUrl("http://192.168.1.6:8988") // ?????? ???????????? Url  http://localhost:8988
                    .addConverterFactory(GsonConverterFactory.create()) //????????????Gson??????(??????????????????)
//                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();

            // ??????5:?????? ?????????????????? ?????????
            GetRequest_Interface request = retrofit.create(GetRequest_Interface.class);

            //??? ???????????? ????????????
            Call<FindFragmentMenumBase> call = request.getTitleMenumdata("0");
            call.enqueue(new Callback<FindFragmentMenumBase>() {
                @Override
                public void onResponse(Call<FindFragmentMenumBase> call, Response<FindFragmentMenumBase> response) {
                    if (null == response || response.equals("")) {
                        Log.e(TAG, "???????????????");
                        ToastUtils.show(FindFragment.this.getActivity(),""+response);
                    } else {
                        String msg = response.message();
                        List<FindFragmetnMenuTitleEntity> resultlist = response.body().getData();
                        for (int i = 0; i < resultlist.size(); i++) {
                            Log.e(TAG, "??????????????????state" + resultlist.get(i).getPp_menum_state());
                            Log.e(TAG, "??????????????????mage" + resultlist.get(i).getPp_menum_textimage());
                        }
                        FindFragmetnMenuTitlelist.clear();
                        FindFragmetnMenuTitlelist = resultlist;
                        Log.e(TAG, "??????????????????" + msg);
                        getData();
                    }
                }

                @Override
                public void onFailure(Call<FindFragmentMenumBase> call, Throwable t) {
                    Log.e(TAG, "??????????????????s" + t);
                }
            });


        }, 2000);
//        try {
//
//            InputStream is = FindFragment.this.getActivity().getAssets().open("grawview.json");//??????json??????
//            byte[] by = new byte[is.available()];//?????????
//            is.read(by);
//            jsonString = new String(by, "utf-8");
//            is.close();//?????????
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    //??????Json??????
    private void getData() {
        if (null == FindFragmetnMenuTitlelist) {
            Log.e(TAG, "??????FindFragmetnMenuTitlelist==null");

        } else {
//            int size = FindFragmetnMenuTitlelist.size();
//            DisplayMetrics dm = new DisplayMetrics();
//            float density = dm.density;
//            int allWidth = (int) (110 * size * density);
//            int itemWidth = (int) (100 * density);
//            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
//                    allWidth, LinearLayout.LayoutParams.FILL_PARENT);
//            gv.setLayoutParams(params);// ??????GirdView????????????
//            gv.setColumnWidth(itemWidth);// ????????????
//            gv.setHorizontalSpacing(13);// ?????????????????????
//            gv.setStretchMode(GridView.NO_STRETCH);
//????????????????????????????????????????????????
//            ????????????????????????CSDN?????????????????????????????????????????????CC 4.0 BY-SA???????????????????????????????????????????????????????????????
//            ???????????????https://blog.csdn.net/lishuaileibo/article/details/78942198
            findGridAdapter = new FindGridAdapter(FindFragment.this.getActivity(), FindFragmetnMenuTitlelist);
            gv.setAdapter(findGridAdapter);
            gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    switch (position) {
                        case 0:
                            ToastUtil.makeText(FindFragment.this.getActivity(), "????????????");
                            startActivity(new Intent(FindFragment.this.getActivity(), TestActivity.class));
                            break;

                    }
                }
            });
        }
    }


}
