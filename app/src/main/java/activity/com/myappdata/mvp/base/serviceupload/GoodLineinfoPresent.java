package activity.com.myappdata.mvp.base.serviceupload;

import android.content.Context;

import com.google.gson.Gson;

import java.util.List;

import activity.com.myappdata.mvp.base.Contract.GoodLineInfoContract;
import activity.com.myappdata.mvp.base.Contract.MainContract;
import activity.com.myappdata.mvp.base.Contract.SerViceUpLoadContract;
import activity.com.myappdata.mvp.base.modelmvp.GoodInfiLineEntity.GoodinfoLineRoot;
import activity.com.myappdata.mvp.base.modelmvp.GoodInfiLineEntity.PPShopGoodInfo;
import activity.com.myappdata.mvp.base.modelmvp.GoodinfoLineModel;
import activity.com.myappdata.mvp.base.modelmvp.SerViceUpLoadModel;
import activity.com.myappdata.util.ToastUtils;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;

public class GoodLineinfoPresent<T> extends GoodLineInfoContract.Present<GoodLineInfoContract.View> {


    public GoodLineinfoPresent(Context mContext, GoodLineinfoPresent mModel, Disposable mDisposable) {
        this.mContext = mContext;
        this.mDisposable = mDisposable;
    }

    public GoodLineinfoPresent(Context mContext) {
        this.mContext = mContext;
        mModel_good = new GoodinfoLineModel();
    }

    private Context mContext;
    private GoodLineInfoContract.Model mModel_good;
    private Disposable mDisposable;//可以取消观察者

    @Override
    public void bindPresentService() {
        mModel_good.bindModelService(mContext);
    }



    @Override
    public void startUpLoadAPK(GoodLineInfoContract.View view) {


    }

    @Override
    public void login(GoodLineInfoContract.View view) {
        view.showDialog();
        mModel_good.loginNet(new MainContract.Model.LoginListener() {
            @Override
            public void success(String str) {
                view.logInOk(str);
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
        if (mDisposable != null) {
            //取消监听
            mDisposable.dispose();
        }
    }

}
