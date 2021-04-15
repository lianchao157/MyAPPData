package activity.com.myappdata.mvp.base.uimvp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import activity.com.myappdata.R;

/***
 * mvp 模式下的base基类
 */
public abstract class BaseActivty_MVP_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        initView();
        initListener();
    }

    /**
     * 监听事件
     */
    protected void initListener() {
    }

    /**
     * 初始化控件
     */
    protected abstract void initView();

    /**
     * 获取Layout.xml的Id
     *
     * @return
     */
    protected abstract int getLayoutResId();
}
