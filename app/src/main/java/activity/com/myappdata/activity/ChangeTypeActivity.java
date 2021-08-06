package activity.com.myappdata.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import activity.com.myappdata.R;
import activity.com.myappdata.adapter.tableadapter.MyAdapter;
import activity.com.myappdata.fragment.BlankFragment;
import activity.com.myappdata.fragment.FindFragment;
import activity.com.myappdata.fragment.TabFragment;
import activity.com.myappdata.widgets.MyLogcat;

/***
 * 仿写顶部导航栏
 * <p>
 * http://www.cppcns.com/ruanjian/android/191379.html
 */
public class ChangeTypeActivity extends AppCompatActivity {
    private static final String TAG = "ChangeTypeActivity";

    private TabLayout tab;
    private ViewPager pager;
    private List<String> list;
    private List<Fragment> fglist = new ArrayList<Fragment>();
    //  组件
    private ImageView head_img_sousuo;//  顶部的搜索框跳转

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_change_type);
        /*初始化界面*/
        initViews();
        /*初始化数据*/
        initData();
        /*设置Adapter*/
        pager.setAdapter(new MyAdapter(getSupportFragmentManager(), list, fglist));
//        pager.setAdapter(new Tabfragmet(getSupportFragmentManager(),list));
        /*Tab与ViewPager绑定*/
        tab.setupWithViewPager(pager);

//        Intent intent = new Intent(ChangeTypeActivity.this, MyLogcat.class);
//        startActivity(intent);

        Intent intent = new Intent();
//        intent.setAction("activity.com.myappdata.widgets.MyLogcat");
//        bindService(intent, conn, Service.BIND_AUTO_CREATE);
//        启动记录log日志
//        Intent intent2 = new Intent(this, MyLogcat.class);
//        startService(intent2);
    }

    /*初始化数据  向集合中添fragment*/
    private void initData() {
        list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(String.format(Locale.CHINA, "第%02d页", i));
        }
        BlankFragment bf = new BlankFragment();
        TabFragment tl = new TabFragment();
        FindFragment findfg = new FindFragment();
        fglist.add(findfg);
        fglist.add(bf);
        fglist.add(tl);
    }

    /*初始化界面*/
    private void initViews() {
        this.pager = (ViewPager) findViewById(R.id.pager);
        this.tab = (TabLayout) findViewById(R.id.tab);

        this.head_img_sousuo = (ImageView) findViewById(R.id.head_img_sousuo);
        this.head_img_sousuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChangeTypeActivity.this, SearchInfoActivity.class));
            }
        });
    }
}
