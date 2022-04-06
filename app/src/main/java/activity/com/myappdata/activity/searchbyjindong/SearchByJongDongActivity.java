package activity.com.myappdata.activity.searchbyjindong;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import java.util.ArrayList;

import activity.com.myappdata.R;
import activity.com.myappdata.activity.searchbyjindong.adapter.MyRecyclerviewAdapter;
import activity.com.myappdata.util.androidtitlebar.StatusBarUtils;

/****
 * 京东搜索
 * 仿写京东搜素框
 * 下拉渐变
 * 2021.10.26
 */
public class SearchByJongDongActivity extends AppCompatActivity {
    private RecyclerView recyclerview;
    private View head_search_rr;
    private int mDistanceY;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchbyjingdong_activity);
        StatusBarUtils.setTranslucent(this, R.color.colorPrimary);

        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);//recyclerview
        head_search_rr = findViewById(R.id.head_search_rr);//serchview
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list.add("O(∩_∩)O哈哈~" + i);
        }

        staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
        //设置空隙处理方式为不处理--item乱跳问题
        staggeredGridLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        //LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerview.setLayoutManager(staggeredGridLayoutManager);
        MyRecyclerviewAdapter adapter = new MyRecyclerviewAdapter(this, list);
        recyclerview.setAdapter(adapter);

        recyclerview.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //防止item乱跳
                //staggeredGridLayoutManager.invalidateSpanAssignments();
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //滑动的距离
                mDistanceY += dy;
                //toolbar的高度
                int toolbarHeight = head_search_rr.getBottom();
                //当滑动的距离 <= toolbar高度的时候，改变Toolbar背景色的透明度，达到渐变的效果
                if (mDistanceY <= toolbarHeight) {
                    float scale = (float) mDistanceY / toolbarHeight;
                    float alpha = scale * 255;
                    head_search_rr.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));
                } else {
                    //将标题栏的颜色设置为完全不透明状态
                    head_search_rr.setBackgroundResource(R.color.white);
                }
            }
        });
    }


}