package activity.com.myappdata.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import activity.com.myappdata.MyService;
import activity.com.myappdata.R;
import activity.com.myappdata.widgets.FloatingViewService;

/***
 * 京东商品详情页面
 * 悬浮球代码
 */
public class JDGoodsInfoActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mBtnShow;
    private Button mBtnHide;
    private static final String TAG = "JDGoodsInfoActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jdgoods_info);


        if (Build.VERSION.SDK_INT >= 23) {
            if (!Settings.canDrawOverlays(this)) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivityForResult(intent, 1);
                Intent intent1 = new Intent(this, FloatingViewService.class);
                startService(intent1);
            } else {
                //TODO do something you need
            }
        }
        mBtnShow = (Button) findViewById(R.id.btn_show);
        mBtnHide = (Button) findViewById(R.id.btn_hide);

        mBtnShow.setOnClickListener(this);
        mBtnHide.setOnClickListener(this);

//        bindService(intent,mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 10) {
            if (Build.VERSION.SDK_INT >= 23) {
                if (!Settings.canDrawOverlays(this)) {
                    // SYSTEM_ALERT_WINDOW permission not granted...
                    Toast.makeText(JDGoodsInfoActivity.this, "not granted", Toast.LENGTH_SHORT);
                }
            }
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, MyService.class);
        switch (v.getId()) {
            case R.id.btn_show:
                intent.putExtra(MyService.ACTION, MyService.SHOW);
                break;
            case R.id.btn_hide:
                intent.putExtra(MyService.ACTION, MyService.HIDE);
                break;
        }
        startService(intent);
    }
}
