package activity.com.myappdata.activity;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import activity.com.myappdata.R;
import activity.com.myappdata.entity.Beandata;
import activity.com.myappdata.entity.DataBean;
import activity.com.myappdata.jingdongtuijian.DataView;
import activity.com.myappdata.jingdongtuijian.MyAdapter;
import activity.com.myappdata.jingdongtuijian.MyDataPresenter;

/***
 * 分类  综推荐   销量    新品二手
 *
 *
 * https://blog.csdn.net/ch5211314/article/details/79762083
 */
public class JingDontTypeActivity extends AppCompatActivity implements DataView {
    private String url = "https://www.zhaoapi.cn/product/getProducts?pscid=2";
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            List<Beandata> list = (List<Beandata>) msg.obj;
            for (int i = 0; i < 8; i++) {


                DataBean bd = new DataBean();
                bd.setString("111");
                bd.setTextstr("2222");
                Beandata aa = new Beandata();
                aa.setData(bd);
                list.add(aa);
            }
            MyAdapter myAdapter = new MyAdapter(JingDontTypeActivity.this, list);
            rec.setAdapter(myAdapter);
        }
    };
    private RecyclerView rec;
    private int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jing_dont_type);
        rec = (RecyclerView) findViewById(R.id.rec);
        final ImageView image = (ImageView) findViewById(R.id.image);

        MyDataPresenter presenter = new MyDataPresenter(this);
        presenter.netWork(url);
        rec.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        List<Beandata> list = new ArrayList<>();
        for (int i = 0; i < 8; i++) {


            DataBean bd = new DataBean();
            bd.setString("111");
            bd.setTextstr("2222");
            Beandata aa = new Beandata();
            aa.setData(bd);
            list.add(aa);
        }
        MyAdapter myAdapter = new MyAdapter(JingDontTypeActivity.this, list);
        rec.setAdapter(myAdapter);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i++;
                if (i % 2 == 0) {
                    rec.setLayoutManager(new LinearLayoutManager(JingDontTypeActivity.this, LinearLayoutManager.VERTICAL, false));
                } else {
                    rec.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
                }


                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(image, "rotation", 0f, 180f);
                objectAnimator.setDuration(500);
                objectAnimator.start();
            }
        });
    }

    @Override
    public void toBackHome(List<Beandata> list) {
        Message msg = new Message();
        msg.obj = list;

        handler.sendMessage(msg);
    }

}
