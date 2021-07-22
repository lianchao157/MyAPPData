package activity.com.myappdata.activity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import activity.com.myappdata.R;
import activity.com.myappdata.sqllite.FeedMoneyContHelper;
import activity.com.myappdata.sqllite.FeedReaderDbHelper;
import activity.com.myappdata.typeui.blackbackgrould.GrayFrameLayout;
import activity.com.myappdata.util.LogUtil;

/****
 * android   第一次打开页面进行   指导页面
 *
 * 灰色背景图
 *
 *
 * 总结代码  写了两个  自定义的view 一个是   在布局修改
 *
 * 灰色北京
 * 2021.7.15 数据库操作
 */
public class GuideFirstActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "GuideFirstActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_first);

//        组件初试话
        initView();
    }


    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        try {
            if ("FrameLayout".equals(name)) {
                int count = attrs.getAttributeCount();
                for (int i = 0; i < count; i++) {
                    String attributeName = attrs.getAttributeName(i);
                    String attributeValue = attrs.getAttributeValue(i);
                    if (attributeName.equals("id")) {
                        int id = Integer.parseInt(attributeValue.substring(1));
                        String idVal = getResources().getResourceName(id);
                        if ("android:id/content".equals(idVal)) {
                            GrayFrameLayout grayFrameLayout = new GrayFrameLayout(context, attrs);
//                            grayFrameLayout.setWindow(getWindow());
                            return grayFrameLayout;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.onCreateView(name, context, attrs);
    }


    private static String DB_NAME = "mydb";
    private static String DB_NAMEcount = "mydbcount";

    private EditText et_name;
    private EditText et_age;
    private ArrayList<Map<String, Object>> data;
    private ArrayList<Map<String, Object>> datacount = new ArrayList<Map<String, Object>>();
    ;
    private FeedReaderDbHelper dbHelper;
    private FeedMoneyContHelper countdbCountdbHelper;//  计算heleper
    private SQLiteDatabase db;
    private SQLiteDatabase dbCount;
    private Cursor cursor;
    private SimpleAdapter listAdapter;
    private View view;
    private ListView listview;
    private Button selBtn, addBtn, updBtn, delBtn;
    private Map<String, Object> item;
    private String selId;
    private ContentValues selCV;
    private Button savedatainpu;
    //变量
    String Strtime = ""; // 时间
    int info;//                      编号


    private ListView listcount;// 总表
    private TextView moneycount;//  总的钱数

    private void initView() {

        moneycount = (TextView) findViewById(R.id.moneycount);
        moneycount.setOnClickListener(this);
        et_name = (EditText) findViewById(R.id.et_name);
        et_age = (EditText) findViewById(R.id.et_count);
        listview = (ListView) findViewById(R.id.listview);
        updBtn = (Button) findViewById(R.id.quanbieupdata);
        updBtn.setOnClickListener(this);
        savedatainpu = (Button) findViewById(R.id.tijiaoshuju);
        savedatainpu.setOnClickListener(this);

        addBtn = (Button) findViewById(R.id.changedata);// 修改
        addBtn.setOnClickListener(this);
        delBtn = (Button) findViewById(R.id.deletedata);//删除数据
        delBtn.setOnClickListener(this);
        dbHelper = new FeedReaderDbHelper(this, DB_NAME, null, 3);

        countdbCountdbHelper = new FeedMoneyContHelper(this, DB_NAMEcount, null, 2);
        db = dbHelper.getWritableDatabase();// 打开数据库


        dbCount = countdbCountdbHelper.getWritableDatabase();// 打开数据库
        data = new ArrayList<Map<String, Object>>();
        dbFindAll();

        GetNowData();
        listcount = (ListView) findViewById(R.id.listcount);//总钱数

    }

    /***
     * 获取当前的系统实际//  创建插入的记录
     */
    private void GetNowData() {
        Strtime = "";
        int max = 100, min = 1;
        long randomNum = System.currentTimeMillis();
        info = (int) (randomNum % (max - min) + min);
        System.out.println(info);
//        -------------------------------
        Date day = new Date();

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Strtime = df.format(day);

        System.out.println(df.format(day));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.quanbieupdata:
                dbFindAll();
                break;

            case R.id.tijiaoshuju:
                dbAdd();
                break;
            case R.id.moneycount:
                dbFindAllCountMoney();
                break;
            case R.id.deletedata:
                dbDel();
                break;

        }
    }

    //插入数据
    protected void dbAdd() {
        // TODO Auto-generated method stub
        List<Object> listsql = new ArrayList<Object>();
        listsql.clear();
        ContentValues values = new ContentValues();
        values.put("name", et_name.getText().toString().trim());
        values.put("age", et_age.getText().toString().trim());
        values.put("time", Strtime);
        ContentValues values2 = new ContentValues();
        values2.put("name", 999);
        values2.put("age", 999);
        values.put("time", Strtime);
        listsql.add(values);
        listsql.add(values2);
// 批量插入数据
        for (int i = 0; i < listsql.size(); i++) {
            ContentValues valuesa = (ContentValues) listsql.get(i);
            long rowid = db.insert(dbHelper.TB_NAME, null, valuesa);
            if (rowid == -1)
                Log.i("myDbDemo", "数据插入失败！");
            else
                Log.i("myDbDemo", "数据插入成功!" + rowid);
        }


//插入数据total

        ContentValues valuescount = new ContentValues();
        double moneytvale = Double.parseDouble(et_name.getText().toString().trim());
        double doubshulaing = Double.parseDouble(et_age.getText().toString().trim());
        double actionResult = (doubshulaing * moneytvale);
        valuescount.put("moneycount", actionResult);
        long rowidtotal = dbCount.insert(countdbCountdbHelper.TB_NAME, null, valuescount);
    }

    //查询数据
    protected void dbFindAll() {
        // TODO Auto-generated method stub
        data.clear();
        cursor = db.query(dbHelper.TB_NAME, null, null, null, null, null, "_id ASC");
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String id = cursor.getString(0);
            String name = cursor.getString(1);
            String age = cursor.getString(2);
            String time = cursor.getString(3);
            item = new HashMap<String, Object>();
            item.put("_id", id);
            item.put("name", name);
            item.put("age", age);
            item.put("time", time);
            data.add(item);
            cursor.moveToNext();
        }
        showList();
    }

    /***
     * 展示数据
     */
    private void showList() {
        // TODO Auto-generated method stub
        LogUtil.e(TAG, "数据源" + data);
        listAdapter = new SimpleAdapter(this, data,
                R.layout.listview, new String[]{"_id", "name", "age", "time"}, new int[]{R.id.tvID, R.id.tvName, R.id.tvAge, R.id.tvTime});
        listview.setAdapter(listAdapter);
    }

    /**
     * 删除数据
     */


    protected void dbDel() {
        // TODO Auto-generated method stub
        String where = "_id=" + selId;
        int i = db.delete(dbHelper.TB_NAME, where, null);
        if (i > 0)
            Log.i("myDbDemo", "数据删除成功!");
        else
            Log.i("myDbDemo", "数据未删除!");
    }

    //更新列表中的数据
    protected void dbUpdate() {
        // TODO Auto-generated method stub
        ContentValues values = new ContentValues();
        values.put("name", et_name.getText().toString().trim());
        values.put("age", et_age.getText().toString().trim());
        String where = "_id=" + selId;
        int i = db.update(dbHelper.TB_NAME, values, where, null);
        if (i > 0)
            Log.i("myDbDemo", "数据更新成功！");
        else
            Log.i("myDbDemo", "数据未更新");
    }


    //查询数据
    protected void dbFindAllCountMoney() {
        datacount.clear();
        // TODO Auto-generated method stub
        Cursor cursor;
        cursor = dbCount.query(countdbCountdbHelper.TB_NAME, null, null, null, null, null, "_id ASC");
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String id = cursor.getString(0);
            String name = cursor.getString(1);
//            String age = cursor.getString(2);
//            String time = cursor.getString(3);
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("_id", id);
            item.put("moneycount", name);
//            item.put("age", age);
//            item.put("time", time);
            datacount.add(item);
            cursor.moveToNext();
        }
        moneycount.setText(datacount.size() + "");
    }

}
