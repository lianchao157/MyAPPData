package activity.com.myappdata.mvp.base.serviceupload;

import android.content.Context;
import android.content.Intent;

import activity.com.myappdata.mvp.base.Contract.MainContract;
import activity.com.myappdata.mvp.base.modelmvp.MainModel;
import activity.com.myappdata.mvp.base.uimvp.activity.DemoLoginByMVPActivity;


/**
 * 作者：MarkShuai
 * 时间：2017/12/19 14:48
 * 邮箱：MarkShuai@163.com
 * 意图：
 */

public class MainPresent<T> extends MainContract.Present<MainContract.View> {


    private MainContract.Model mModel;
    private Context mContext;

    public MainPresent(Context mContext) {
        this.mContext = mContext;
        mModel = new MainModel();
    }

    @Override
    public void jumpNormal() {
        Intent intent = new Intent(mContext, DemoLoginByMVPActivity.class);
        mContext.startActivity(intent);
    }


    @Override
    public void jumpService() {
        Intent intent = new Intent(mContext, DemoLoginByMVPActivity.class);
        mContext.startActivity(intent);
    }

    @Override
    public  void login(final MainContract.View view) {
        view.showDialog();
        mModel.loginNet(new MainContract.Model.LoginListener() {
            @Override
            public void success(String str) {
                view.logInOk(str);//  获取数据代码
                view.dismissDialog();
            }

            @Override
            public void error(String code) {
                view.logInError(code);
                view.dismissDialog();
            }
        });
    }


    @Override
    public void fetch() {

    }

    @Override
    public void onDestroy() {

    }
}
