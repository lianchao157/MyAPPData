package activity.com.myappdata.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import activity.com.myappdata.R;
import activity.com.myappdata.util.ToastUtils;
import activity.com.myappdata.util.bindviewutil.BindView;

/***
 * updata 自动更新
 * 款箱早送出库
 */
public class UPVersionActivity extends Activity implements View.OnClickListener {

    private Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upversion);
        initView();
    }

    private void initView() {
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                showDialog();
                break;

        }
    }

//    显示dialog

    public void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //设置对话框不能消失
        builder.setCancelable(false);
        //设置对话框的标题
        builder.setTitle("新版本:" + 1);
        //设置对话框的图标
        builder.setIcon(R.mipmap.icon_add);
        //设置对话框的描述信息
        builder.setMessage("11111");
        //设置升级取消按钮
        builder.setPositiveButton("升级", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                ToastUtils.show(UPVersionActivity.this, "正在更新请稍后。。。。。");
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                System.exit(1);
            }
        });
        //显示对话框
        //builder.create().show();//两种方式效果一样
        builder.show();
    }


    public void ShowDialog() {

    }
}
