package activity.com.myappdata.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Outline;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextPaint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import activity.com.myappdata.R;
import activity.com.myappdata.activity.JingDongShouyeActivity;
import activity.com.myappdata.activity.MainActivity;
import activity.com.myappdata.adapter.BlankFragmetistViewAdapter;
import activity.com.myappdata.adapter.HListViewAdapter;
import activity.com.myappdata.adapter.TaoAdapter;
import activity.com.myappdata.bean.ApprovalData;
import activity.com.myappdata.bean.BlankFragmentEntity;
import activity.com.myappdata.entity.PIctureBannerCode;
import activity.com.myappdata.entity.PictureBannerBase;
import activity.com.myappdata.entity.TaoAdapterMenum;
import activity.com.myappdata.fragment.fg.utils.GlobalParms;
import activity.com.myappdata.interfacepack.SkipFragments;
import activity.com.myappdata.util.JumperUtils;
import activity.com.myappdata.util.ToastUtils;
import butterknife.BindView;

/**
 * Created by lianchao on 2021/1/7.
 */

public class BlankFragment extends Fragment implements View.OnClickListener, OnBannerListener,SkipFragments {
    private static final String TAG = "BlankFragment";
    private Banner banner_recent;//  ??????
    private RecyclerView fragment_blank_rv;// ??????????????????
    private View vProgress;
    private RelativeLayout layoutProgress;
    private List<PictureBannerBase> homedatalist = new ArrayList<PictureBannerBase>();//  ??????
    private ListView ABaseListview;  //  listView
    private HListViewAdapter hHListViewAdapter;
    private List<ApprovalData> aABlist = new ArrayList();//get  ????????????????????????


//    private SmartRefreshLayout refreshLayout;


    ///  ?????????????????????
    private LinearLayout mGallery;
    private int[] mImgIds;
    private LayoutInflater mInflater;
    //  ?????????????????? ?????????????????????????????????

//    @BindView(R.id.toolbar_tab)
//    TabLayout toolbarTab;
//
//    @BindView(R.id.view_pager)
//    ViewPager viewPager;
//  ??????


    private Button allcityselect;//  ????????????
    private Button mytv;
    // ????????????
    private LinearLayout id_gallery1;
    private ListView abase_lv;

    private List<BlankFragmentEntity> datalist = new ArrayList();

//      ???????????????


    private BlankFragmetistViewAdapter blankFragmetistViewAdapter;

    public BlankFragment() {
    }

    public static BlankFragment newInstance(String text) {
        Bundle bundle = new Bundle();
        bundle.putString("text", text);
        BlankFragment blankFragment = new BlankFragment();
        blankFragment.setArguments(bundle);
        return blankFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_blank, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView textView = (TextView) view.findViewById(R.id.pager_text);
//        textView.setText(getArguments().getString("text"));
        banner_recent = (Banner) view.findViewById(R.id.banner_recent);
//         recvcleview
        fragment_blank_rv = (RecyclerView) view.findViewById(R.id.fragment_blank_rv);
        layoutProgress = (RelativeLayout) view.findViewById(R.id.layout);
        vProgress = view.findViewById(R.id.v);
//        fragment_blank_rv.setLayoutManager(new LinearLayoutManager(BlankFragment.this.getActivity(), LinearLayoutManager.HORIZONTAL, false));
        fragment_blank_rv.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.HORIZONTAL));
        banner_recent.setOnBannerListener(this);// ??????????????????OnBannerListener??????
// ???????????????
        banner_recent.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load(path).into(imageView);
            }
        });

//        https://blog.csdn.net/qq_41985689/article/details/103642088//  ???banner  ??????????????????
        banner_recent.setOutlineProvider(new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), 30);
            }
        });

//shezhi???????????????
//        banner_recent .setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                if (positionOffset > 1) {//????????????????????????1?????????
//                    return;
//                }
//                //??????position????????????????????????????????????Banner????????????
//                if (position == 0) {
//                    position = count;
//                }
//                if (position > count) {
//                    position = 1;
//                }
//                int pos = (position + 1) % count;//?????????
//
//                int vibrantColor = ColorUtils.blendARGB(imageLoader.getVibrantColor(pos), imageLoader.getVibrantColor(pos + 1), positionOffset);
//                ivBannerHeadBg.setBackgroundColor(vibrantColor);
//                setStatusBarColor(BlankFragment.this, vibrantColor);
//            }
//
//            @Override
//            public void onPageSelected(final int position) {
//                if(isInit){// ?????????,??????????????????????????????
//                    isInit = false;
//                    new Handler().postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            int vibrantColor = imageLoader.getVibrantColor(1);
//                            ivBannerHeadBg.setBackgroundColor(vibrantColor);
//                            setStatusBarColor(BlankFragment.this, vibrantColor);
//                        }
//                    }, 200);
//
//                }
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int i) {
//
//            }
//        });


        banner_recent.setClipToOutline(true);
        banner_recent.start();
        String[] images = new String[]{
                "http://file02.16sucai.com/d/file/2014/0704/e53c868ee9e8e7b28c424b56afe2066d.jpg",
                "http://file02.16sucai.com/d/file/2015/0128/8b0f093a8edea9f7e7458406f19098af.jpg",
                "http://img02.tooopen.com/images/20150227/tooopen_sy_81141126968.jpg",
                "http://img4.imgtn.bdimg.com/it/u=2853553659,1775735885&fm=26&gp=0.jpg"};

//        ??????

        String[] tips = new String[]{
                "????????????",
                "????????????",
                "????????????",
                "????????????"};
        List imageList = new ArrayList();
        List titleList = new ArrayList();
        for (int i = 0; i < images.length; i++) {
            imageList.add(images[i]);
            titleList.add(tips[i]);
        }

        InitView();
        LoadData();
//        https://blog.csdn.net/weixin_43117800/article/details/107517755
//        banner_recent.setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE);//?????????????????????
        banner_recent.setDelayTime(1000);//??????????????????
        banner_recent.setImages(imageList);//???????????????
        banner_recent.setBannerTitles(titleList);//????????????
        banner_recent.setBannerAnimation(Transformer.Default);
        banner_recent.setIndicatorGravity(BannerConfig.CENTER)
                //???????????????????????????????????????????????????????????????????????????????????????????????????
                .setOnBannerListener(this);
        banner_recent.start();
//         ?????????????????????
        fragment_blank_rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int range = recyclerView.computeHorizontalScrollRange(); //?????????
                int extent = recyclerView.computeHorizontalScrollExtent(); // ?????????????????????
                int offset = recyclerView.computeHorizontalScrollOffset(); //?????????????????????
                //??????????????????
                float wai = range - extent;
                // ????????????????????????
                float huabi = (float) (offset * 1.0) / wai; //???????????????????????????Int??????????????????
                int huafan = layoutProgress.getWidth() - vProgress.getWidth(); //???????????????
                float translationX = huabi * huafan; // view?????????
                vProgress.setTranslationX(translationX);
            }
        });
        mInflater = LayoutInflater.from(BlankFragment.this.getActivity());
        //        ??????????????????
        mImgIds = new int[]{R.drawable.service_traffic_icon, R.drawable.service_public_icon, R.drawable.service_medical_icon,
                R.drawable.service_live_icon, R.drawable.service_life_icon, R.drawable.service_arrow_up, R.drawable.service_arrow_down};


        mGallery = (LinearLayout) view.findViewById(R.id.id_gallery);

        for (int i = 0; i < mImgIds.length; i++) {
//            ViewParent parent = mGallery.getParent();
//            if(parent!=null)
//                Log.i("who_are_you",parent.getClass().toString());
//
//            HorizontalScrollView HorizontalScrollView = (HorizontalScrollView)parent;
//            HorizontalScrollView.removeView(mGallery);
            View view1 = mInflater.inflate(R.layout.activity_index_gallery_item,
                    mGallery, false);
            ImageView img = (ImageView) view1
                    .findViewById(R.id.bf_aigiv);
            img.setImageResource(mImgIds[i]);
            TextView txt = (TextView) view1
                    .findViewById(R.id.bf_aigtv);
            txt.setText("some info ");
            mGallery.addView(view1);
        }


//         ????????????

        allcityselect = (Button) view.findViewById(R.id.allcityselect);
        allcityselect.setOnClickListener(this);
        mytv = (Button) view.findViewById(R.id.mytv);
        mytv.setOnClickListener(this);

        id_gallery1 = (LinearLayout) view.findViewById(R.id.id_gallery1);

        for (int i = 0; i < mImgIds.length; i++) {
            View view1 = mInflater.inflate(R.layout.activity_index_gallery_item_second,
                    id_gallery1, false);
            ImageView img = (ImageView) view1
                    .findViewById(R.id.bf_aigiv1);
            img.setImageResource(mImgIds[i]);
            TextView txt = (TextView) view1
                    .findViewById(R.id.bf_aigtv1);
            txt.setText("some info ");
            id_gallery1.addView(view1);
        }
        datalist.clear();
        for (int i = 0; i <= 20; i++) {
            BlankFragmentEntity blankFragmentEntity = new BlankFragmentEntity();
            blankFragmentEntity.setCity("??????" + i);
            blankFragmentEntity.setImage_fu("http://file02.16sucai.com/d/file/2014/0704/e53c868ee9e8e7b28c424b56afe2066d.jpg" + i);
            blankFragmentEntity.setMoney("100" + i);
            blankFragmentEntity.setTextinfo("???????????????" + i);


            datalist.add(blankFragmentEntity);
        }

        FragmentManager   fm = getFragmentManager();
        abase_lv = (ListView) view.findViewById(R.id.abase_lv);
        blankFragmetistViewAdapter = new BlankFragmetistViewAdapter(datalist, BlankFragment.this.getActivity());
        abase_lv.setAdapter(blankFragmetistViewAdapter);
        abase_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
// fragmnet??????activity
                startActivity(new Intent(BlankFragment.this.getActivity(),MainActivity.class));



//                  fragment ??????
//           FragmentTransaction     ft = fm.beginTransaction();
//
//                GoodsInfoFragment df = new GoodsInfoFragment();
//                Bundle bundle = new Bundle();
//                bundle.putLong("id", id);
//                bundle.putString("name", "1");
//                df.setArguments(bundle);
//                ft.replace(R.id.fl_main_fragment, df);
//                ft.addToBackStack(null);
//                ft.commit();
//                Bundle bundle = new Bundle();
//                bundle.putLong("id", id);
//                bundle.putString("name", name);

//                GlobalParms.getBookrackFragment();
//                getActivity().getSupportFragmentManager()
//                        .beginTransaction()
//                        .replace(R.layout.fragment_goods_info, new GoodsInfoFragment(), null)
//                        .addToBackStack(null)
//                        .commit();

//                ????????????????????????
//                ?????????https://www.imooc.com/article/74779
//                ??????????????????
            }
        });
    }

    List<TaoAdapterMenum> mFruitList = new ArrayList<>();

    private void InitView() {
        TaoAdapterMenum tm = new TaoAdapterMenum();
        TaoAdapterMenum tm1 = new TaoAdapterMenum();
        TaoAdapterMenum tm2 = new TaoAdapterMenum();
        tm.setStriamge("http://cn.bing.com/az/hprichbg/rb/Dongdaemun_ZH-CN10736487148_1920x1080.jpg");
        tm.setStrname("??????01");
        tm1.setStriamge("http://img02.tooopen.com/images/20150227/tooopen_sy_81141126968.jpg");
        tm1.setStrname("??????01");
        tm2.setStriamge("http://img02.tooopen.com/images/20150227/tooopen_sy_81141126968.jpg");
        tm2.setStrname("??????01");
        for (int i = 0; i <2; i++) {
            mFruitList.add(tm);
            mFruitList.add(tm1);
            mFruitList.add(tm2);
        }

        //??????recyclerView?????????????????????????????????????????????????????????true????????????????????????????????????????????????????????????????????????????????????
        fragment_blank_rv.setHasFixedSize(true);
//        fragment_blank_rv.setAdapter(new TaoAdapter(mFruitList));
        fragment_blank_rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int range = recyclerView.computeHorizontalScrollRange(); //?????????
                int extent = recyclerView.computeHorizontalScrollExtent(); // ?????????????????????
                int offset = recyclerView.computeHorizontalScrollOffset(); //?????????????????????

//                Log.e("?????????:"+range
//                        +"\n????????????????????????"+extent
//                        +"\n????????????????????????"+offset);

                //??????????????????
                float wai = range - extent;
                // ????????????????????????
                float huabi = (float) (offset * 1.0) / wai; //???????????????????????????Int??????????????????

                int huafan = layoutProgress.getWidth() - vProgress.getWidth(); //???????????????
                float translationX = huabi * huafan; // view?????????
                vProgress.setTranslationX(translationX);
//                L.c("\n?????????????????????"+wai
//                        +"\n????????????:"+huabi
//                        +"\n??????????????????"+huafan
//                        +"\nview?????????"+translationX);
            }
        });


    }

//    }


    private void LoadData() {
        JSONArray jsonarray = new JSONArray();
        JSONObject jsonbject = new JSONObject();
        JSONObject jsonobject2 = new JSONObject();
        JSONObject jsonobject3 = new JSONObject();
        JSONObject jsonobjectlist = new JSONObject();
//        "http://file02.16sucai.com/d/file/2014/0704/e53c868ee9e8e7b28c424b56afe2066d.jpg",
//                "http://file02.16sucai.com/d/file/2015/0128/8b0f093a8edea9f7e7458406f19098af.jpg",
        try {
            jsonbject.put("homedataid", 1);
            jsonbject.put("homeimage", "http://file02.16sucai.com/d/file/2014/0704/e53c868ee9e8e7b28c424b56afe2066d.jpg");
            jsonbject.put("hometext", "??????1");
            jsonbject.put("homeyuliu", "1");
            jsonbject.put("homeyuliu2", "1");
            jsonobject2.put("homedataid", 2);
            jsonobject2.put("homeimage", "http://file02.16sucai.com/d/file/2015/0128/8b0f093a8edea9f7e7458406f19098af.jpg");
            jsonobject2.put("hometext", "??????2");
            jsonobject2.put("homeyuliu", "2");
            jsonobject2.put("homeyuliu2", "2");

            jsonobject3.put("homeimage", "http://file02.16sucai.com/d/file/2015/0128/8b0f093a8edea9f7e7458406f19098af.jpg");
            jsonobject3.put("hometext", "??????2");
            jsonobject3.put("homeyuliu", "2");
            jsonobject3.put("homeyuliu2", "2");


//            list_path.add("https://img-blog.csdnimg.cn/20191025202219499.jpg");
//            list_path.add("https://img-blog.csdnimg.cn/20191025202237531.jpg");
//            list_path.add("https://img-blog.csdnimg.cn/20191025202245720.jpg");
//            list_path.add("https://img-blog.csdnimg.cn/20191025202300909.jpg");
            jsonarray.put(jsonbject);
            jsonarray.put(jsonobject2);
            jsonarray.put(jsonobject3);
            jsonobjectlist.put("data", jsonarray);
            JSONArray array = new JSONArray();
            Gson gson = new Gson();
            PIctureBannerCode PIctureBannerCode = gson.fromJson(jsonobjectlist.toString(), PIctureBannerCode.class);
            List<PictureBannerBase> homedatalist = PIctureBannerCode.getData();
            Log.i("BankFragment", "???????????????????????????" + homedatalist.size() + "");

            SetDataHome();

        } catch (JSONException e) {
            e.printStackTrace();
            Log.i(TAG,""+e);
        }


    }

    private void SetDataHome() {
//        RecycleViewHomeDataAdapter = new RecycleViewHomeDataAdapter(homedatalist);
//        ac_home_image_recycle.setAdapter(RecycleViewHomeDataAdapter);
//
////        2.//???????????????
//        ac_home_image_recycle.addItemDecoration(new DividerGridItemDecoration(mContext));
//        RecycleViewHomeDataAdapter.setOnItemClickLitener(new RecycleViewHomeDataAdapter.OnItemClickLitener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                Toast.makeText(Home_Image_Activity.this,"????????????"+homedatalist.get(position).getHomeimage(),Toast.LENGTH_LONG).show();
//            }
//        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mytv:
                allcityselect.setTextSize(24);
                TextPaint tp = allcityselect.getPaint();
                tp.setFakeBoldText(true);

                mytv.setTextSize(20);
                break;

            case R.id.allcityselect:
                mytv.setTextSize(24);
                TextPaint tp2 = mytv.getPaint();
                tp2.setFakeBoldText(true);

                TextPaint allcityselect1 = mytv.getPaint();
                tp2.setFakeBoldText(false);
                allcityselect.setTextSize(20);
//                ??????????????????????????????????????????????????????????????????????????????
                break;

            default:
                break;
        }
    }

    @Override
    public void OnBannerClick(int position) {
        switch (position) {
            case 0:
                ToastUtils.show(BlankFragment.this.getActivity(), "?????????" + 0 + "???");
                JumperUtils.JumpTo(BlankFragment.this.getActivity(), JingDongShouyeActivity.class);
                break;
            case 1:
                ToastUtils.show(BlankFragment.this.getActivity(), "?????????" + 1 + "???");
                break;
            case 2:
                ToastUtils.show(BlankFragment.this.getActivity(), "?????????" + 2 + "???");
                break;
            case 3:
                ToastUtils.show(BlankFragment.this.getActivity(), "?????????" + 3 + "???");
                break;
            default:
                break;
        }
    }

    @Override
    public void skip(int postion) {
        switch (postion){}
    }
//??????Fragment???????????????????????????ViewPagerAdapter

    class MyAdapter extends FragmentPagerAdapter {


        private List<String> list;

        public MyAdapter(FragmentManager fm, List<String> list) {
            super(fm);
            this.list = list;
        }

        @Override
        public Fragment getItem(int position) {
            return BlankFragment.newInstance(list.get(position));
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return list.get(position);
        }


//    @Override
//    protected void onStart() {
//        super.onStart();
//        //????????????
//        banner_recent.startAutoPlay();
//    }


        private void SetDataHome() {
//        RecycleViewHomeDataAdapter = new RecycleViewHomeDataAdapter(homedatalist);
//        ac_home_image_recycle.setAdapter(RecycleViewHomeDataAdapter);
//
////        2.//???????????????
//        ac_home_image_recycle.addItemDecoration(new DividerGridItemDecoration(mContext));
//        RecycleViewHomeDataAdapter.setOnItemClickLitener(new RecycleViewHomeDataAdapter.OnItemClickLitener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                Toast.makeText(Home_Image_Activity.this,"????????????"+homedatalist.get(position).getHomeimage(),Toast.LENGTH_LONG).show();
//            }
//        });
        }
    }

}
