package activity.com.myappdata.activity.video;

import android.app.ListActivity;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import activity.com.myappdata.R;
import activity.com.myappdata.dialogbytiem.DoubleTimeSelectDialog;

public class ListViewByselftActivity extends ListActivity implements AbsListView.OnScrollListener {
    private ListView listView;
    private int visibleLastIndex = 0;	//最后的可视项索引
    private int visibleItemCount;		// 当前窗口可见项总数
    private ListViewAdapter adapter;
    private View loadMoreView;
    private Button loadMoreButton;
    private Handler handler = new Handler();

    public String defaultWeekBegin;
    /**
     * 默认的周结束时间，格式如：yyyy-MM-dd
     */
    public String defaultWeekEnd;
    private TextView showtime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_byselft);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }


        loadMoreView = getLayoutInflater().inflate(R.layout.load_more, null);
        loadMoreButton = (Button) loadMoreView.findViewById(R.id.loadMoreButton);
        showtime= (TextView) loadMoreView.findViewById(R.id.showtime);
        listView = getListView();				//获取id是list的ListView

        listView.addFooterView(loadMoreView);	//设置列表底部视图

        initAdapter();

        setListAdapter(adapter);				//自动为id是list的ListView设置适配器

        listView.setOnScrollListener(this);
    }
    private void initAdapter() {
        ArrayList<String> items = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            items.add(String.valueOf(i + 1));
        }
        adapter = new ListViewAdapter(this, items);
    }

    /**
     * 滑动时被调用
     */
    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        this.visibleItemCount = visibleItemCount;
        visibleLastIndex = firstVisibleItem + visibleItemCount - 1;
    }

    /**
     * 滑动状态改变时被调用
     */
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        int itemsLastIndex = adapter.getCount() - 1;	//数据集最后一项的索引
        int lastIndex = itemsLastIndex + 1;				//加上底部的loadMoreView项
        if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE && visibleLastIndex == lastIndex) {
            //如果是自动加载,可以在这里放置异步加载数据的代码
            Log.i("LOADMORE", "loading...");
        }
    }

    /**
     * 点击按钮事件
     * @param view
     */
    private DoubleTimeSelectDialog mDoubleTimeSelectDialog;
    public void loadMore(View view) {
        loadMoreButton.setText("loading...");	//设置按钮文字loading
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//
//                loadData();
//
//                adapter.notifyDataSetChanged();	//数据集变化后,通知adapter
//                listView.setSelection(visibleLastIndex - visibleItemCount + 1);	//设置选中项
//
//                loadMoreButton.setText("load more");	//恢复按钮文字
//            }
//        }, 2000);

        String beginDeadTime = "2011-01-01";
        if (mDoubleTimeSelectDialog == null) {
            mDoubleTimeSelectDialog = new DoubleTimeSelectDialog(this, beginDeadTime, defaultWeekBegin, defaultWeekEnd);
            mDoubleTimeSelectDialog.setOnDateSelectFinished(new DoubleTimeSelectDialog.OnDateSelectFinished() {
                @Override
                public void onSelectFinished(String startTime, String endTime) {
                    showtime.setText(startTime.replace("-", ".") + "至\n" + endTime.replace("-", "."));

                }
            });

            mDoubleTimeSelectDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                }
            });
        }
        if (!mDoubleTimeSelectDialog.isShowing()) {
            mDoubleTimeSelectDialog.recoverButtonState();
            mDoubleTimeSelectDialog.show();
        }
    }

    /**
     * 模拟加载数据
     */
    private void loadData() {
        int count = adapter.getCount();
        for (int i = count; i < count + 10; i++) {
            adapter.addItem(String.valueOf(i + 1));
        }
    }

}