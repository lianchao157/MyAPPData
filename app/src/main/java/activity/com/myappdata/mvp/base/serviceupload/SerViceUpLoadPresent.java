package activity.com.myappdata.mvp.base.serviceupload;

import android.content.Context;
import android.widget.Toast;


import activity.com.myappdata.mvp.base.Contract.SerViceUpLoadContract;
import activity.com.myappdata.mvp.base.modelmvp.SerViceUpLoadModel;
import activity.com.myappdata.util.ToastUtil;
import activity.com.myappdata.util.ToastUtils;
import io.reactivex.disposables.Disposable;

/**
 * 作者：MarkShuai
 * 时间：2017/12/20 13:41
 * 邮箱：MarkShuai@163.com
 * 意图：
 */

public class SerViceUpLoadPresent<T> extends SerViceUpLoadContract.Present<SerViceUpLoadContract.View> {

    private Context mContext;
    private SerViceUpLoadContract.Model mModel;
    private Disposable mDisposable;//可以取消观察者


    public SerViceUpLoadPresent(Context mContext) {
        this.mContext = mContext;
        mModel = new SerViceUpLoadModel();
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

    @Override
    public void bindPresentService() {
        mModel.bindModelService(mContext);
    }

    @Override
    public void startUpLoadAPK(SerViceUpLoadContract.View view) {
        mModel.startUpLoad(mContext, new SerViceUpLoadContract.Model.ProgressListener() {
            @Override
            public void onSubscribeProgress(Disposable d) {

                mDisposable = d;
            }

            @Override
            public void onNextProgress(Integer progress) {
                view.setProgress(progress);
            }

            @Override
            public void onErrorProgress(Throwable throwable) {
                throwable.printStackTrace();
                ToastUtils.show(mContext,"出错了");
//                ToastUtil.makeText(,"");
            }

            @Override
            public void onCompleteProgress() {
                view.setProgress(100);
                ToastUtils.show(mContext,"下载完成");
            }
        });
    }
}
