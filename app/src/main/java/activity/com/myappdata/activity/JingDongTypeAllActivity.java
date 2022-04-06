package activity.com.myappdata.activity;

import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import activity.com.myappdata.R;
import activity.com.myappdata.activity.searchbyjindong.SearchByJongDongActivity;
import activity.com.myappdata.activity.video.HotMenums;
import activity.com.myappdata.application.MyApplication;
import activity.com.myappdata.fragment.typeallkind.Fragment_pro_type;
import activity.com.myappdata.fragment.typeallkind.Fragment_pro_type02;
import activity.com.myappdata.retorfitutils.HttpEngine;
import activity.com.myappdata.server.CrashHandler;
import activity.com.myappdata.util.intentaction.IntentUtil;
import activity.com.myappdata.util.writelog.LogService;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/***
 *京东的货物分类
 *
 */
public class JingDongTypeAllActivity extends AppCompatActivity {
    private static final String TAG = "JingDongTypeAllActivity";
    private String toolsList[];
    private TextView toolsTextViews[];
    private View views[];
    private LayoutInflater inflater;
    private ScrollView scrollView;
    private int scrllViewWidth = 0, scrollViewMiddle = 0;
    private ViewPager shop_pager;
    private int currentItem = 0;
    private ShopAdapter shopAdapter;
   public  CrashHandler.CrashUploader crashUploader;
    public   PendingIntent pendingIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jing_dong_type_all);
        initView();
        scrollView = (ScrollView) findViewById(R.id.tools_scrlllview);
        shopAdapter = new ShopAdapter(getSupportFragmentManager());
        inflater = LayoutInflater.from(this);
        getNetResutl();

//        new Thread(new MyRunnable()).start();//   开启 一个线程
//        CrashHandler crashLog = CrashHandler.getInstance();
//        crashLog.init(getApplicationContext());


/***
 * 启动记录日志服务
 *
 */
//        Intent intent = new Intent(JingDongTypeAllActivity.this, LogService.class);
//        startService(intent);
//        ;
        CrashHandler crashHandler=CrashHandler.getInstance();

        crashHandler.init(JingDongTypeAllActivity.this,crashUploader,pendingIntent);
    }

    /****
     * 初始哈组件
     */
    private ImageView type_icon;
    private EditText search_edit;

    private String   serinput;///  查询搜索的输入内容
    private void initView() {
        type_icon = (ImageView) findViewById(R.id.type_icon);
//        search_edit = (EditText) findViewById(R.id.search_edit);
//
//
//        serinput=search_edit.getText().toString().trim();//
//        if (serinput.equals("")) {
//            Log.i("onNext==222=", "输入的数据为null");
//        } else {
//            Log.i("onNext==222=", "输入的数据为"+serinput);

            type_icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(JingDongTypeAllActivity.this, SearchByJongDongActivity.class);
                    startService(intent);

                }
            });
        }

//    }


    /**
     * 动态生成显示items中的textview
     * "家用电器", "手机数码", "电脑办公", "个护化妆",
     * "母婴频道", "食物生鲜", "酒水饮料", "家居家纺", "整车车品", "鞋靴箱包", "运动户外", "图书", "玩具乐器", "钟表",
     * "居家生活", "珠宝饰品", "音像制品", "家具建材", "计生情趣", "营养保健", "奢侈礼品", "生活服务", "旅游出行"
     */
    private void showToolsView() {
        if (toolsList != null) {

        } else {
            toolsList = new String[]{"现钞页面", "潮流女装", "品牌男装", "内衣配饰"};
        }


        LinearLayout toolsLayout = (LinearLayout) findViewById(R.id.tools);
        toolsTextViews = new TextView[toolsList.length];
        views = new View[toolsList.length];

        for (int i = 0; i < toolsList.length; i++) {
            View view = inflater.inflate(R.layout.item_b_top_nav_layout, null);
            view.setId(i);
            view.setOnClickListener(toolsItemListener);
            TextView textView = (TextView) view.findViewById(R.id.text);//  顶部的数据
            textView.setText(toolsList[i]);
            toolsLayout.addView(view);
            toolsTextViews[i] = textView;
            views[i] = view;
        }
        changeTextColor(0);
    }

    private View.OnClickListener toolsItemListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            shop_pager.setCurrentItem(v.getId());
        }
    };


    /**
     * initPager<br/>
     * 初始化ViewPager控件相关内容
     */
    private void initPager() {
        shop_pager = (ViewPager) findViewById(R.id.goods_pager);
        shop_pager.setAdapter(shopAdapter);
        shop_pager.setOnPageChangeListener(onPageChangeListener);
        shopAdapter.notifyDataSetChanged();
    }

    /**
     * OnPageChangeListener<br/>
     * 监听ViewPager选项卡变化事的事件
     */

    private ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageSelected(int arg0) {
            if (shop_pager.getCurrentItem() != arg0) shop_pager.setCurrentItem(arg0);
            if (currentItem != arg0) {
                changeTextColor(arg0);
                changeTextLocation(arg0);
            }
            currentItem = arg0;
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }
    };


    /**
     * ViewPager 加载选项卡
     *
     * @author Administrator
     */
    private class ShopAdapter extends FragmentPagerAdapter {
        public ShopAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int arg0) {
            Fragment fragment = null;
            if (arg0 == 0) {
                fragment = new Fragment_pro_type();
                Bundle bundle = new Bundle();
                String str = toolsList[arg0];
                bundle.putString("typename", str);
                fragment.setArguments(bundle);
                return fragment;
            } else if (arg0 == 1) {
                fragment = new Fragment_pro_type02();
                Bundle bundle = new Bundle();
                String str = toolsList[arg0];
                bundle.putString("typename", str);
                fragment.setArguments(bundle);
                return fragment;
            } else if (arg0 == 2) {
                fragment = new Fragment_pro_type02();
                Bundle bundle = new Bundle();
                String str = toolsList[arg0];
                bundle.putString("typename", str);
                fragment.setArguments(bundle);
                return fragment;
            } else if (arg0 == 3) {
                fragment = new Fragment_pro_type02();
                Bundle bundle = new Bundle();
                String str = toolsList[arg0];
                bundle.putString("typename", str);
                fragment.setArguments(bundle);
                return fragment;
            } else if (arg0 == 4) {
                fragment = new Fragment_pro_type02();
                Bundle bundle = new Bundle();
                String str = toolsList[arg0];
                bundle.putString("typename", str);
                fragment.setArguments(bundle);
                return fragment;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return toolsList.length;
        }
    }


    /**
     * 改变textView的颜色
     *
     * @param id
     */
    private void changeTextColor(int id) {
        for (int i = 0; i < toolsTextViews.length; i++) {
            if (i != id) {
                toolsTextViews[i].setBackgroundResource(android.R.color.transparent);
                toolsTextViews[i].setTextColor(0xff000000);
            }
        }
        toolsTextViews[id].setBackgroundResource(android.R.color.white);
        toolsTextViews[id].setTextColor(0xffff5d5e);
    }


    /**
     * 改变栏目位置
     *
     * @param clickPosition
     */
    private void changeTextLocation(int clickPosition) {

        int x = (views[clickPosition].getTop() - getScrollViewMiddle() + (getViewheight(views[clickPosition]) / 2));
        scrollView.smoothScrollTo(0, x);
    }

    /**
     * 返回scrollview的中间位置
     *
     * @return
     */
    private int getScrollViewMiddle() {
        if (scrollViewMiddle == 0)
            scrollViewMiddle = getScrollViewheight() / 2;
        return scrollViewMiddle;
    }

    /**
     * 返回ScrollView的宽度
     *
     * @return
     */
    private int getScrollViewheight() {
        if (scrllViewWidth == 0)
            scrllViewWidth = scrollView.getBottom() - scrollView.getTop();
        return scrllViewWidth;
    }

    /**
     * 返回view的宽度
     *
     * @param view
     * @return
     */
    private int getViewheight(View view) {
        return view.getBottom() - view.getTop();
    }


    @Override
    protected void onResume() {
        super.onResume();
        //调用封装好的retrofit请求方法




    }

    private void getNetResutl() {

        HttpEngine.getDuoBanTop1(new Observer<List<HotMenums>>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.i(TAG,  ""+d);
            }

            @Override
            public void onNext(List<HotMenums> hotMenums) {
//                Log.i("onNext==222=", hotMenums.getHotmenumsimage() + "---" + hotMenums.getHotmenumsname());
                List<String> liststrmenum = new ArrayList<String>();
                for (int i = 0; i < hotMenums.size(); i++) {
                    Log.i("onNext==222=", hotMenums.get(i).getHotmenumsname());
                    liststrmenum.add(hotMenums.get(i).getHotmenumsname());
                }

                String[] strings = new String[liststrmenum.size()];
                toolsList = null;
                toolsList = liststrmenum.toArray(strings);
                showToolsView();
                initPager();
            }

            @Override
            public void onError(Throwable e) {
                showToolsView();
                initPager();
            }

            @Override
            public void onComplete() {

            }

        });
    }


}
