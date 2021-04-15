package activity.com.myappdata.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import activity.com.myappdata.R;
import activity.com.myappdata.adapter.recycleviewandrecycleview.BusinessAdapter;
import activity.com.myappdata.bean.BusinessEntity;
import activity.com.myappdata.bean.ProductEntity;
import activity.com.myappdata.bean.URLHistoryItem;
import butterknife.OnClick;

/***
 * 实现二级菜单代码
 */
public class TwoLevelMenumActivity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView recyclerView;

    private BusinessAdapter businessAdapter;

    private List<Object> mDatas;
    private ImageView imageView;

    private  Context  mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_level_menum);
        mContext=TwoLevelMenumActivity.this;
        initData();
        recyclerView = (RecyclerView) findViewById(R.id.activity_two);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        businessAdapter = new BusinessAdapter(this, mDatas);

        recyclerView.setAdapter(businessAdapter);
        imageView = (ImageView) findViewById(R.id.image);
        imageView.setOnClickListener(TwoLevelMenumActivity.this);
        businessAdapter.setOnRecyclerViewListener(new BusinessAdapter.OnRecyclerViewListener() {
            @Override
            public void onBusinessImgClick(View view, int position) {

            }

            @Override
            public void onBusinessNameClick(View view, int position) {

            }
        });

        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
    }

    // 数据
    protected void initData() {
        mDatas = new ArrayList<>();
        List<ProductEntity> products = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            ProductEntity productEntity = new ProductEntity();
            productEntity.setProductName("我是一个商品哈哈哈哈或或或" + i);
            productEntity.setProductImage("https://ss0.baidu.com/7Po3dSag_xI4khGko9WTAnF6hhy/image/h%3D200/sign=71cd4229be014a909e3e41bd99763971/472309f7905298221dd4c458d0ca7bcb0b46d442.jpg");
            productEntity.setProductPrice("99." + i);
            products.add(productEntity);
        }
        for (int i = 1; i < 30; i++) {
            BusinessEntity entity = new BusinessEntity();
            entity.setBusinessName("ddddfsdfdsfsdfsdfzzzz" + i);
            entity.setBusinessImage("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1564533037,3918553373&fm=116&gp=0.jpg");
            entity.setProducts(products);
            mDatas.add(entity);
        }

    }

    @Override
    public void onClick(View v) {

    }
}
