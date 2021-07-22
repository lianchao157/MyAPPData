package activity.com.myappdata.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import activity.com.myappdata.R;
import activity.com.myappdata.bean.cash.BankEntity;
import activity.com.myappdata.bean.cash.cash;
import activity.com.myappdata.util.Table;
import activity.com.myappdata.widgets.FloatingViewService;

import static activity.com.myappdata.util.Table.doParse;

/***
 * 实现类似京东的导航栏
 * https://www.jianshu.com/p/29f103f343a5
 */
//https://blog.csdn.net/iteye_5061/article/details/82681836?utm_medium=distribute.pc_relevant_t0.none-task-blog-BlogCommendFromMachineLearnPai2-1.control&depth_1-utm_source=distribute.pc_relevant_t0.none-task-blog-BlogCommendFromMachineLearnPai2-1.control
//顶部导航栏
public class JingDongShouyeActivity extends FragmentActivity implements View.OnClickListener {
    private TextView tva1, tva2, tva3, tva4;
    private LinearLayout lay;
    private LinearLayout lay11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jing_dong_shouye);

        if (Build.VERSION.SDK_INT >= 23) {
            if (!Settings.canDrawOverlays(JingDongShouyeActivity.this)) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, 10);
                Intent intent2 = new Intent(this, FloatingViewService.class);
                startService(intent2);
            }
        } else {

        }
//
//        NetworkChangeReceiver.registerObserver(this);
//解除注册
//        NetworkChangeReceiver.unRegisterReceiver(this);

//网络状态变化，在回调方法里面
//        String str = "WD/网点1|PS/00001|ZZ/ZH000001ZZ,ZH000002ZZ|XJ/100,10000;50,20000|ZK/存储,100;汇票凭证,50|DY/DY202100001,DY202100002|PS/00002|ZZ/ZH000003ZZ,ZH000004ZZ|XJ/100,10000;50,20000|ZK/存储,100;汇票凭证,50|DY/DY202100003,DY202100004|WD/网点2|PS/00001|ZZ/ZH000001ZZ,ZH000002ZZ|XJ/100,10000;50,20000|ZK/存储,100;汇票凭证,50|DY/DY202100001,DY202100002|PS/00002|ZZ/ZH000003ZZ," +
//                "ZH000004ZZ|XJ/100,10000;50,20000|ZK/存储,100;汇票凭证,50|DY/DY202100003,DY202100004";
//        String str2 = str.replace("WD/", "网点1");
//        String str3 = str2.replace("|", "\n");
//        tva1 = (TextView) findViewById(R.id.tva1);
//        tva1.setText(str3);
//        tva1.setTextSize(20);
        lay11 = (LinearLayout) findViewById(R.id.lay11);
        showView();
        for (int i = 0; i < cashListBankEntity.size(); i++) {
            View v = LayoutInflater.from(JingDongShouyeActivity.this)
                    .inflate(R.layout.layoutwork, null);
            TextView tvType = (TextView) v.findViewById(R.id.tva1);
            TextView tvCount = (TextView) v.findViewById(R.id.tva2);
            tvType.setText(cashListBankEntity.get(i).getBondstr());
            tvCount.setText(cashListBankEntity.get(i).getUsuallystr());
            lay11.addView(v);
        }
        datasloulation();
    }

    private void datasloulation() {
        String str = "TCKTNAME:百元面额,二十元面额,十元面额,伍十元面额|NUMS:919400,134940,37320,110000|FLAG:0,0,0,0|\n"
                + "DTLNAME:存折,银行卡,支票|DTLNUMS:1620,6260,1560|\n" + "NUMID:|CVOUN:30|";
        Table[] tables = doParse(str);
        for (int i = 0; i < tables.length; i++)
            System.out.println(tables[i].toString());
    }

    List<BankEntity> cashListBankEntity = new ArrayList<BankEntity>();

    /****
     *    String str = "WD:网点1"+"/"+"WD:网点20";
     */
    private void showView() {
        String str = "WD:网点1|PS:00001|ZZ:ZH000001ZZ,ZH000002ZZ,ZH000003ZZ,ZH000004ZZ,ZH000001ZZ,ZH000002ZZ,ZH000001ZZ,ZH000002ZZ,ZH000001ZZ," +
                "ZH000002ZZ|XJ:100,10000;50,20000|ZK:存储,100;汇票凭证,50|DY:DY202100001,DY202100002|PS:00002|XJ:100,10000;50,20000|ZK:存储,100;汇票凭证,50" + "-" + "WD:网点2|PS:00002|ZZ:ZH000201ZZ,ZH002002ZZ,ZH000203ZZ,ZH020004ZZ,ZH200001ZZ,ZH220002ZZ,ZH000001ZZ,ZH000002ZZ,ZH000001ZZ,ZH022002ZZ|XJ:200,10020;52,22200|ZK:存储200;汇票凭证20|DY:DY222100001,DY202200002|PS:00002|XJ:100,10000;50,20000|ZK:存储,100;汇票凭证,50";

        Table[] table = doParse(str);
        for (int i = 0; i < table.length; i++) {
            System.out.println(table[i].toString());
            cash c = new cash();
            System.out.println(table[i].get("DY").getValues());

            if (table[i].get("DY").getName().equals("DY")) {
                BankEntity bankEntity = new BankEntity();
                bankEntity.setBondstr("抵质押品");
                bankEntity.setUsuallystr(table[i].get("DY").getValues().toString());
                cashListBankEntity.add(bankEntity);
            }
            if (table[i].get("WD").getName().equals("WD")) {
                BankEntity bankEntity = new BankEntity();
                bankEntity.setBondstr("网点");
                bankEntity.setUsuallystr(table[i].get("WD").getValues().toString());
                cashListBankEntity.add(bankEntity);
            }
            if (table[i].get("PS").getName().equals("PS")) {
                BankEntity bankEntity = new BankEntity();
                bankEntity.setBondstr("配送单");
                bankEntity.setUsuallystr(table[i].get("PS").getValues().toString());
                cashListBankEntity.add(bankEntity);
            }
            if (table[i].get("ZZ").getName().equals("ZZ")) {
                BankEntity bankEntity = new BankEntity();
                bankEntity.setBondstr("周转箱");
                bankEntity.setUsuallystr(table[i].get("ZZ").getValues().toString());
                cashListBankEntity.add(bankEntity);
            }
            String sss = "";
            if (table[i].get("ZK").getName().equals("ZK")) {
                BankEntity bankEntity = new BankEntity();
                bankEntity.setBondstr("重要凭证");
                List<String> zkstr = table[i].get("ZK").getValues();
                for (int x = 0; x < zkstr.size(); x++) {
                    sss = sss + zkstr.get(x).replace(";", "\n");
                    bankEntity.setUsuallystr(sss);
                }


                cashListBankEntity.add(bankEntity);
            }
            String xj = "";
            String xj1 = "";
            if (table[i].get("XJ").getName().equals("XJ")) {
                BankEntity bankEntity = new BankEntity();
                bankEntity.setBondstr("现金");
//                bankEntity.setUsuallystr(table[i].get("XJ").getValues().toString());
//
                List<String> zkstrxj = table[i].get("XJ").getValues();
                for (int k = 0; k < zkstrxj.size(); k++) {
                    if (null == xj || xj.equals("")) {
                        xj = xj + zkstrxj.get(k);
                    } else {
                        xj = xj + "元" + zkstrxj.get(k);
                        xj1 = xj.replace(";", "\n");
                    }

                    bankEntity.setUsuallystr(xj1);
                }
                cashListBankEntity.add(bankEntity);
            }
        }



    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case 0:


                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 10) {
            if (Build.VERSION.SDK_INT >= 23) {
                if (!Settings.canDrawOverlays(this)) {
                    // SYSTEM_ALERT_WINDOW permission not granted...
//                    Toast.makeText(LoginActivity. this , "not granted" ,Toast.LENGTH_SHORT);
                }
            }
        }
    }
}
