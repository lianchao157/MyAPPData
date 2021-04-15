package activity.com.myappdata.activity;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import activity.com.myappdata.R;
import activity.com.myappdata.fragment.fragmentqiantao.FragmentLeft;
import activity.com.myappdata.fragment.fragmentqiantao.FragmentRight;
import activity.com.myappdata.widgets.MyToolBar;

/***
 * 仿写 fragmemtn 嵌套fragment    示例
 * 2021.4.9
 *
 * ————————————————
 版权声明：本文为CSDN博主「尼古拉斯阳」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
 原文链接：https://blog.csdn.net/linder_qzy/article/details/50739541
 */
public class SearchInfoActivity extends FragmentActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private MyToolBar myToolBar;//  自定义 的toobar
    private Button[] mTabs;
    private FragmentLeft fragmentLeft;
    private FragmentRight fragmentRight;
    private Fragment mFragment;//标记当前显示的Fragment //  v4包
    private int currentTabIndex;//button当前选中的索引
    private int index;//选中button的索引值
    private LinearLayout linsearchelin;
    private Context mContext;

    private LinearLayout  lin_image;//   网络错误显示图片
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_info);
        mContext=SearchInfoActivity.this;
        initView();
        initfragmetnt();

        loadFragment();

//         fragment 嵌套代码
        initfindViewById();
        initFragment();
//         判断是否有网络
        isNetworkAvailable();
    }

    private void initfindViewById() {
        mTabs = new Button[2];
        mTabs[0] = (Button) findViewById(R.id.btn_tab_clock);
        mTabs[1] = (Button) findViewById(R.id.btn_tab_bell);
        mTabs[0].setOnClickListener(this);
        mTabs[1].setOnClickListener(this);

    }

    private void initFragment() {
        fragmentLeft = new FragmentLeft();
        fragmentRight = new FragmentRight();
        //开启事物将fragment添加到布局中并提交事物
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        beginTransaction.add(R.id.rl_fragment_container, fragmentLeft);
        beginTransaction.commitAllowingStateLoss();
        mFragment = fragmentLeft;
        currentTabIndex = 0;
        mTabs[currentTabIndex].setSelected(true);//设置默认Fragment的选择器

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_tab_clock:
                index = 0;
                if (null == fragmentLeft) {
                    fragmentLeft = new FragmentLeft();
                }
                switchContent(fragmentLeft);
                break;
            case R.id.btn_tab_bell:
                index = 1;
                if (null == fragmentLeft) {
                    fragmentRight = new FragmentRight();
                }
                switchContent(fragmentRight);
                break;

            default:
                break;
        }
    }

    /**
     * 修改显示的内容 不会重新加载（相当于做了缓存处理，切换时仍能有数据显示）
     **/
    public void switchContent(Fragment to) {
        try {
            if (mFragment != to) {
                FragmentTransaction transaction = getSupportFragmentManager()
                        .beginTransaction();
                if (!to.isAdded()) { // 先判断是否被add过
                    transaction.hide(mFragment)
                            .add(R.id.rl_fragment_container, to)
                            .commitAllowingStateLoss(); // 隐藏当前的fragment，add下一个到Activity中
                } else {
                    transaction.hide(mFragment).show(to)
                            .commitAllowingStateLoss(); // 隐藏当前的fragment，显示下一个
                }
                mFragment = to;
            }
            mTabs[currentTabIndex].setSelected(false);
            // 把当前tab设为选中状态
            mTabs[index].setSelected(true);
            currentTabIndex = index;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    private void initfragmetnt() {
    }

    /***
     * 加载fragmeng
     */
    private void loadFragment() {
    }

//    @Override
//    protected int layoutId() {
//        return 0;
//    }

//    @Override
//    protected int initLayout() {
//        return R.layout. activity_search_info;
//    }

    //    @Override
    protected void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.menu_save);//加载菜单栏
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SearchInfoActivity.this, "clicked back", Toast.LENGTH_SHORT).show();


            }
        });
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_save:
                        Toast.makeText(SearchInfoActivity.this, "clicked save", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu_setting:
                        Toast.makeText(SearchInfoActivity.this, "clicked seting", Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });
        linsearchelin = (LinearLayout) findViewById(R.id.linsearchelin);
        lin_image= (LinearLayout) findViewById(R.id.lin_image);
        lin_image.setVisibility(View.GONE);
    }

    /***
     * 网络判断  并显示图片
     * @return
     */
    public boolean isNetworkAvailable() {
        ConnectivityManager connectivity = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity == null) {
            System.out.println("**** newwork is off");
            return false;
        } else {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info == null) {
                System.out.println("**** newwork is off");
//                Drawable drawable = ContextCompat.getDrawable(SearchInfoActivity.this, R.drawable.neterr); //recommend
//                Resources resources = SearchInfoActivity.this.getResources();
//                Drawable btnDrawable = resources.getDrawable(R.drawable.jz_forward_icon);
//                linsearchelin.setBackgroundDrawable(drawable);
                linsearchelin.setBackgroundDrawable(getResources().getDrawable(R.drawable.neterr));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                    linsearchelin.setForeground(R.drawable.btnDrawable);
                }
                lin_image.setVisibility(View.VISIBLE);
                return false;
            } else {
                if (info.isAvailable()) {
                    System.out.println("**** newwork is on");
                    lin_image.setVisibility(View.GONE);
                    return true;
                }
            }
        }
        System.out.println("**** newwork is off");
        return false;
    }
}
