package activity.com.myappdata;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import activity.com.myappdata.activity.ChangeTypeActivity;
import activity.com.myappdata.adapter.Gridviewadapter;
import activity.com.myappdata.entity.GridvieMenumEntity;

//http://111.160.42.163:8801/#/system/users

//
//        3
//        1611001
//        十六对讲机管理员
//        12412341234
//        分公司对讲机操作员
//        华宝第十六分公司
//        正常
public class MainActivity extends Activity implements View.OnClickListener {
    private GridView gridView;
    private Gridviewadapter gridviewadapter;
    private List<GridvieMenumEntity> gridviewlist = new ArrayList<>();///  数据源代码
    private TextView tvmainmenum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadData();
        tvmainmenum = (TextView) findViewById(R.id.tvmainmenum);
        tvmainmenum.setOnClickListener(this);
        //  开启服务线程  写入日志log


    }

    private void loadData() {
        GridvieMenumEntity g = new GridvieMenumEntity();
        g.setImagstr("1");


    }

    private void initView() {
        gridView = (GridView) findViewById(R.id.gv);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvmainmenum:
                Intent intent = new Intent(MainActivity.this, ChangeTypeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
        }
    }



}
//  git remote add dev git@github.com:xxx/xxx.git


//    git config -–global user.name "lianchaolc"
//        git config –-global user.email "15642926631@163.com"