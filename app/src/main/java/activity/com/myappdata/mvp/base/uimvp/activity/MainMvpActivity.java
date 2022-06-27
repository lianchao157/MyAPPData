package activity.com.myappdata.mvp.base.uimvp.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import activity.com.myappdata.R;
import activity.com.myappdata.api.VideoApi;
import activity.com.myappdata.application.MyApplication;
import activity.com.myappdata.mvp.base.modelmvp.entity.UserInfoByLogin;
import activity.com.myappdata.mvp.base.uimvp.fragment.HotFFragment;
import activity.com.myappdata.mvp.base.uimvp.fragment.MineFragment;
import activity.com.myappdata.mvp.base.uimvp.fragment.PlaceFragment;
import activity.com.myappdata.mvvm.ui.MvvmUiActivity;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * mvpi显示的activity 的代码
 */

//存在问题第一次加载没有默认的一页
public class MainMvpActivity extends BaseActivty_MVP_Activity {
    private PlaceFragment mPlaceFragment;
    private HotFFragment hotFFragment;
    private MineFragment mineFragment;
    private FragmentManager mFragmentManager;
    private VideoApi mvapi; ///  网络请求的url
    private RadioButton home_rb_personalcenter;
    private RadioButton home_rb_search;
    private RadioButton home_rb_home;
    private RadioButton home_rb_map;
    private RadioButton home_rb_more;
    private RadioGroup home_rg;
    private FrameLayout home_frameLayout;
    public UserInfoByLogin user = new UserInfoByLogin();
//    通过application  判断是否需要登陆
    private final static String TAG = MainMvpActivity.class.getSimpleName();
    private MyApplication myapplication;

    @Override
    protected void initView() {
        mPlaceFragment = new PlaceFragment();
        hotFFragment = new HotFFragment();
        mineFragment = new MineFragment();
        initFragment();
        home_frameLayout = (FrameLayout) findViewById(R.id.home_frameLayout);
        home_rb_personalcenter = (RadioButton) findViewById(R.id.home_rb_personalcenter);
        home_rb_search = (RadioButton) findViewById(R.id.home_rb_search);
        home_rb_home = (RadioButton) findViewById(R.id.home_rb_home);
        home_rb_map = (RadioButton) findViewById(R.id.home_rb_map);
        home_rb_more = (RadioButton) findViewById(R.id.home_rb_more);
        home_rg = (RadioGroup) findViewById(R.id.home_rg);
        mFragmentManager = getSupportFragmentManager();
        // 开启事务
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.replace(R.id.home_frameLayout, hotFFragment);
        // 提交事务
        transaction.commit();
        home_rb_personalcenter.setChecked(true);
        //设置RadioGroup的选中监听


        home_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (group.getCheckedRadioButtonId()) {
                    case R.id.home_rb_personalcenter:
                        mFragmentManager = getSupportFragmentManager();
                        // 开启事务
                        FragmentTransaction transaction = mFragmentManager.beginTransaction();
                        transaction.replace(R.id.home_frameLayout, hotFFragment);
                        // 提交事务
                        transaction.commit();
//                          fragment  嵌套fragment
//                        Drawable drawable = getResources().getDrawable(R.drawable.menu_contact_selected);
//设置图片的大小
//                        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
//在文本的右边设置图片
//                        home_rb_personalcenter.setCompoundDrawables(null, null, drawable, null);
//                        原文链接：https://blog.csdn.net/wenzhi20102321/article/details/65937545
                        break;
                    case R.id.home_rb_search:
                        mFragmentManager = getSupportFragmentManager();
                        // 开启事务
                        FragmentTransaction transaction1 = mFragmentManager.beginTransaction();
                        transaction1.replace(R.id.home_frameLayout, mPlaceFragment);
                        // 提交事务
                        transaction1.commit();
                        Drawable drawable1 = getResources().getDrawable(R.drawable.menu_find_selected);
//设置图片的大小
                        drawable1.setBounds(0, 0, drawable1.getMinimumWidth(), drawable1.getMinimumHeight());
                        break;
                    case R.id.home_rb_home:
                        mFragmentManager = getSupportFragmentManager();
                        FragmentTransaction minPagetransaction = mFragmentManager.beginTransaction();
                        minPagetransaction.replace(R.id.home_frameLayout, mineFragment);
                        minPagetransaction.commit();
                        break;
                    case R.id.home_rb_map:
                        break;
                }
            }
        });

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activit_main_mvp;
    }

    private void initFragment() {


        Retrofit retrofit = new Retrofit.Builder()
                //设置数据解析器
                .addConverterFactory(GsonConverterFactory.create())
                //设置网络请求的Url地址
                .baseUrl("http://apis.baidu.com/txapi/")
                .build();
        // 创建网络请求接口的实例
        mvapi = retrofit.create(VideoApi.class);

    }

    /**
     * 存登陆人员信息
     *
     * @param ul
     */
    public void saveUser(UserInfoByLogin ul) {
        SharedPreferences sp = this.getSharedPreferences("user_info", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("name", ul.getUsername());
        editor.putString("imageurl", ul.getUserpassword());
        editor.commit();//必须提交，否则保存不成功
    }


    /***
     * 读取用户信息  验证是否登陆
     * @return
     */
    public UserInfoByLogin readUser() {
        SharedPreferences sp = this.getSharedPreferences("user_info", Context.MODE_PRIVATE);
        UserInfoByLogin user = new UserInfoByLogin();
        user.setUsername(sp.getString("name", ""));
        user.setUserpassword(sp.getString("imageurl", ""));
        if (null != user.getUserpassword() || null != user.getUsername()) {
//        MyApplication.userInfoByLogin.setUserpassword(sp.getString("imageurl", "2"));
        } else {
            startActivity(new Intent(this, MVP_LoginActivity.class));
        }
        return user;
    }

    @Override
    protected void onResume() {
        super.onResume();
        readUser();
    }
}
