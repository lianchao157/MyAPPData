package activity.com.myappdata.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.meituan.robust.patch.annotaion.Add;

import java.util.ArrayList;
import java.util.List;

import activity.com.myappdata.R;
import activity.com.myappdata.adapter.listviewadapter.MyAdapter;
import activity.com.myappdata.bean.FindFragmentMenumBase;
import activity.com.myappdata.bean.FindFragmetnMenuTitleEntity;
import activity.com.myappdata.net.RetrofitManager;
import activity.com.myappdata.server.CrashHandler;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/***
 * 1 主角 @Add
 * 2  listview  s三层不通布局展示数据
 */
public class TestActivity extends AppCompatActivity {
    private static final String TAG = "TestActivity";
    //    private StandardGSYVideoPlayer play;
    private String stedt;
    private TextView addtext;

    ListView listView;
    MyAdapter listAdapter;
    ArrayList<String> listString;

    private TextView tvteset_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        CrashHandler crashLog = CrashHandler.getInstance();
        crashLog.init(getApplicationContext());
//        play = (StandardGSYVideoPlayer) findViewById(R.id.play);
//        play.setUp("https://txmov2.a.yximgs.com/upic/2020/11/12/18/BMjAyMDExMTIxODA2NTBfMjUyMDg5MDg5XzM5MTA4MTEzMjYwXzFfMw==_b_B349b0c56632f063aeb72d151a3f5505c.mp4", true, "");
//        play.startPlayLogic();
//        if(stedt.equals("9")){
//
//        }

        listView = (ListView) this.findViewById(R.id.list_view_test);
        listString = new ArrayList<String>();
        for (int i = 0; i < 6; i++) {
            listString.add(Integer.toString(i));
        }
        listAdapter = new MyAdapter(this, listString);
        listView.setAdapter(listAdapter);
        tvteset_info = (TextView) findViewById(R.id.tvteset_info);
        addText();
        getData();
    }

    /***
     * 测试使用rerorfit封装
     */
    private void getData() {
        RetrofitManager.getInstance().getService().getTitleMenumdata("3").enqueue(new Callback<FindFragmentMenumBase>() {
            @Override
            public void onResponse(Call<FindFragmentMenumBase> call, Response<FindFragmentMenumBase> response) {
                if (response.isSuccessful()) {
                    Log.e(TAG, "11111" + response.body().getData());
                    List<FindFragmetnMenuTitleEntity> findFragmetnMenuTitleEntity = response.body().getData();
                    for (int i = 0; i < findFragmetnMenuTitleEntity.size(); i++) {
                        Log.e(TAG, "11111" + findFragmetnMenuTitleEntity.get(i).getPp_menum_textimage());
                        tvteset_info.setText(findFragmetnMenuTitleEntity.get(i).getPp_menum_texttitle());
                    }
                }
            }

            @Override
            public void onFailure(Call<FindFragmentMenumBase> call, Throwable t) {
                Log.e(TAG, "22222221");
            }
        });
    }

    @Add
    private void addText() {
        addtext = (TextView) findViewById(R.id.addtext);
        addtext.setText("测试代码");
    }


}
