package activity.com.myappdata.mvp.base.uimvp.activity.scnner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import activity.com.myappdata.R;
import activity.com.myappdata.mvp.base.uimvp.activity.BaseActivty_MVP_Activity;

/***
 * 扫描工具类
 */
public class Scannerctivity extends BaseActivty_MVP_Activity  implements View.OnClickListener {
    private ImageView  title_back;
    private TextView  title_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scannerctivity);
    }

    @Override
    protected void initView() {

        title_back= (ImageView) findViewById(R.id.title_back);
        title_name= (TextView) findViewById(R.id.title_name);
        title_name.setOnClickListener(Scannerctivity.this);
    }

    @Override
    protected int getLayoutResId() {
        return 0;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.title_back:
                break;
        }
    }
}