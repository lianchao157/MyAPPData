package activity.com.myappdata.mvp.base.view.showmainactivity.showtviedeo;

import android.os.Bundle;
import android.view.View;

import java.util.ResourceBundle;

import activity.com.myappdata.R;
import activity.com.myappdata.mvp.base.serviceupload.MainPresent;
import activity.com.myappdata.mvp.base.utilsmvp.LoadingDialog;
import activity.com.myappdata.mvp.base.view.LoginView;
import activity.com.myappdata.mvp.base.view.showmainactivity.base.BaseActivity;


/***
 * mvp   下有趣的例子
 * https://github.com/antoniolg/androidmvp/blob/master/app/src/main/java/com/antonioleiva/mvpexample/app/login/LoginPresenter.java
 */
public class ShowvideActivity extends BaseActivity<showVideoContract.View,
        ShowVideoPresent<showVideoContract.View>>

        implements showVideoContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showvide);
        ShowVideoPresent showVideoPresent = new ShowVideoPresent(ShowvideActivity.this);
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_showvide;
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void initDataAfter() {

    }

    @Override
    public void setListener() {

    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case 0:
                mPresent.onDestroy();
                break;
        }
    }

    @Override
    protected ShowVideoPresent<showVideoContract.View> createPresent() {
        return new ShowVideoPresent<>(mContext);
    }


    @Override
    public void showvideo() {
        mPresent.showvideo(this);

    }

    @Override
    public void showDialog() {
        LoadingDialog.show(mContext);

    }

    @Override
    public void dismissDialog() {

    }
}