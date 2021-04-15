package activity.com.myappdata.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.meituan.robust.patch.annotaion.Add;

import java.util.ArrayList;

import activity.com.myappdata.R;
import activity.com.myappdata.adapter.listviewadapter.MyAdapter;
import activity.com.myappdata.server.CrashHandler;

/***
 * 1 主角 @Add
 * 2  listview  s三层不通布局展示数据
 */
public class TestActivity extends AppCompatActivity {
    //    private StandardGSYVideoPlayer play;
    private String stedt;
    private TextView addtext;

    ListView listView;
    MyAdapter listAdapter;
    ArrayList<String> listString;

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
        for (int i = 0; i < 100; i++) {
            listString.add(Integer.toString(i));
        }
        listAdapter = new MyAdapter(this, listString);
        listView.setAdapter(listAdapter);
        addText();
    }

    @Add
    private void addText() {
        addtext = (TextView) findViewById(R.id.addtext);
        addtext.setText("测试代码");
    }
}
