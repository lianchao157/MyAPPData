package activity.com.myappdata.mvp.base.uimvp.activity;

import android.content.Context;
import android.graphics.Outline;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import activity.com.myappdata.R;
import activity.com.myappdata.mvp.base.modelmvp.entity.GoodsBean;
import activity.com.myappdata.mvp.base.presentermvp.OnSelectedListener;
import activity.com.myappdata.mvp.base.view.ShopPingGuigeTest.ShoppingSelectView;

/***
 * mvp
 * 模式下商品分类选择
 *
 * 选择规格
 */
public class GoodInfoTypeSelectActivity extends AppCompatActivity implements OnSelectedListener,OnBannerListener {
    private ShoppingSelectView view;
    String jsonString;
    GoodsBean data;
    String TAG = "MainActivity";
    private Banner goodinfoselectbanner;// 顶部图片轮播图

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_good_info_type_select);
        view = (ShoppingSelectView) findViewById(R.id.v);
        goodinfoselectbanner = (Banner) findViewById(R.id.goodinfoselectbanner);

        goodinfoselectbanner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load(path).into(imageView);
            }
        });

//        https://blog.csdn.net/qq_41985689/article/details/103642088//  给banner  设置圆角边框
        goodinfoselectbanner.setOutlineProvider(new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), 30);
            }
        });
        String[] images = new String[]{
                "http://file02.16sucai.com/d/file/2014/0704/e53c868ee9e8e7b28c424b56afe2066d.jpg",
                "http://file02.16sucai.com/d/file/2015/0128/8b0f093a8edea9f7e7458406f19098af.jpg",
                "http://img02.tooopen.com/images/20150227/tooopen_sy_81141126968.jpg",
                "http://img4.imgtn.bdimg.com/it/u=2853553659,1775735885&fm=26&gp=0.jpg"};

        String[] tips = new String[]{
                "我的日记",
                "我的周记",
                "我的感悟",
                "我的生活"};
        List imageList = new ArrayList();
        List titleList = new ArrayList();
        for (int i = 0; i < images.length; i++) {
            imageList.add(images[i]);
            titleList.add(tips[i]);
        }
        goodinfoselectbanner.setImages(imageList);//设置图片源
        goodinfoselectbanner.setBannerTitles(titleList);//设置标题
        goodinfoselectbanner.setBannerAnimation(Transformer.Default);
        goodinfoselectbanner.setIndicatorGravity(BannerConfig.RIGHT)
                //以上内容都可写成链式布局，这是轮播图的监听。比较重要。方法在下面。
                .setOnBannerListener(this).start();
        goodinfoselectbanner.startAutoPlay();
        goodinfoselectbanner.isAutoPlay(false);
        //设置监听需要在设置数据源之前
        view.setOnSelectedListener(this);
        initJsonData();
        getData();
        initData();

    }

    //本地数据测试专用
    private void initJsonData() {
        try {
            InputStream is = getAssets().open("specs.json");//打开json数据
            byte[] by = new byte[is.available()];//转字节
            is.read(by);
            jsonString = new String(by, "utf-8");
            is.close();//关闭流
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //解析Json数据
    private void getData() {
        Gson gson = new Gson();
        data = gson.fromJson(jsonString, GoodsBean.class);
    }

    private void initData() {

        //最终数据
        view.setData(data);
        Log.d(TAG, data + "");
    }

    @Override
    public void onSelected(String title, String smallTitle, int id) {
        Toast.makeText(this, title + "--" + smallTitle + "--" + id, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void OnBannerClick(int position) {

    }
}
