package activity.com.myappdata.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import activity.com.myappdata.R;
//import activity.com.myappdata.adapter.RecycleviewAdapter;
import activity.com.myappdata.adapter.jingdongadapter.RecycleViewAdapter;
import activity.com.myappdata.bean.TabTitleBrandEntity;
import activity.com.myappdata.entity.EntityReccleview;
import activity.com.myappdata.widgets.SectionedGridDivider;

import static android.content.ContentValues.TAG;

/**
 * Created by lianchao on 2021/1/7.
 */
//https://www.zhihu.com/question/39304525/answer/80656470
public class TabFragment extends Fragment {
//    private RecyclerView tabfragmentrv;
    private RecyclerView rvxianshiqianggou;// 限时枪口

//    private RecycleviewAdapter mRecycleviewAdapter;
    private List<EntityReccleview> recyclelist = new ArrayList<>(); //  限时抢购
    private List<TabTitleBrandEntity> tabTitleBrandEntitieslist = new ArrayList<TabTitleBrandEntity>();//  品牌顶部

    private static final String TAG = "TabFragment";
    private static final String typestr = "str";
    private static final String typeentity = "tity";
    private  SectionedGridDivider   mDivider;//   分割线代码
    private View include;
    private String str;


    //目标项是否在最后一个可见项之后
    private boolean mShouldScroll;
    //记录目标项位置
    private int mToPosition;
    public static TabFragment newInstance(String text) {
        Bundle bundle = new Bundle();
        bundle.putString("text", text);
        TabFragment TabFragment = new TabFragment();
        TabFragment.setArguments(bundle);
        return TabFragment;
    }

    public static Fragment newInstance() {
        TabFragment fragment = new TabFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tab, container, false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        LinearLayoutManager manager = new LinearLayoutManager(TabFragment.this.getActivity(), LinearLayoutManager.VERTICAL, false);
        rvxianshiqianggou = (RecyclerView) rootView.findViewById(R.id.rv_xianshiqianggou);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvxianshiqianggou.setLayoutManager(layoutManager);
        recyclelist.clear();
        include = rootView.findViewById(R.id.include);
        LoadData();
//        mRecycleviewAdapter = new RecycleviewAdapter(recyclelist);
//        rvxianshiqianggou.setAdapter(mRecycleviewAdapter);

//        mDivider = new SectionedGridDivider(TabFragment.this.getActivity(), 50, Color.parseColor("#F5F5F5"));
//        rvxianshiqianggou.addItemDecoration(mDivider);
//  recycleview   滑动监听
        rvxianshiqianggou.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //定位初始点坐标
                View stickyInfoView = recyclerView.findChildViewUnder(recyclerView.getMeasuredWidth() / 2, 1);
                if (stickyInfoView != null) {
                    TextView typeName = (TextView) stickyInfoView.findViewById(R.id.typeName);
                    if (typeName != null) {
                        str = typeName.getText().toString();
                    }
                    if(null==str||str.equals("")){}else{


                    if (str!=null||str.equals("标题")) {
                        include.setVisibility(View.GONE);
                    } else if (str.equals("筛选")) {
                        include.setVisibility(View.VISIBLE);
                    } else if (str.equals("内容")) {
                        include.setVisibility(View.VISIBLE);
                    }
                    }
                }
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (mShouldScroll && RecyclerView.SCROLL_STATE_IDLE == newState) {
                    mShouldScroll = false;
                    smoothMoveToPosition(recyclerView, mToPosition);
                }
            }
        });
        return rootView;
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
        for(int i=0; i<20; i++){
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
            return TabFragment.newInstance(list.get(position));
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

    /**
     * 滑动到指定位置
     */
    private void smoothMoveToPosition(RecyclerView mRecyclerView, final int position) {
        // 第一个可见位置
        int firstItem = mRecyclerView.getChildLayoutPosition(mRecyclerView.getChildAt(0));
        // 最后一个可见位置
        int lastItem = mRecyclerView.getChildLayoutPosition(mRecyclerView.getChildAt(mRecyclerView.getChildCount() - 1));
        if (position < firstItem) {
            // 第一种可能:跳转位置在第一个可见位置之前
            mRecyclerView.smoothScrollToPosition(position);
        } else if (position <= lastItem) {
            // 第二种可能:跳转位置在第一个可见位置之后
            int movePosition = position - firstItem;
            if (movePosition >= 0 && movePosition < mRecyclerView.getChildCount()) {
                int top = mRecyclerView.getChildAt(movePosition).getTop();
                mRecyclerView.smoothScrollBy(0, top);
            }
        } else {
            // 第三种可能:跳转位置在最后可见项之后
            mRecyclerView.smoothScrollToPosition(position);
            mToPosition = position;
            mShouldScroll = true;
        }
    }
}
