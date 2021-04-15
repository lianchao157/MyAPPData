package activity.com.myappdata.mvp.base.modelmvp;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Environment;
import android.os.IBinder;

import java.io.File;
import java.util.concurrent.TimeUnit;

import activity.com.myappdata.mvp.base.Contract.SerViceUpLoadContract;
import activity.com.myappdata.mvp.base.service.DownloadService;
import activity.com.myappdata.mvp.base.utilsmvp.ContentManager;
import activity.com.myappdata.mvp.base.utilsmvp.SDCardHelper;
import activity.com.myappdata.util.ToastUtils;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static android.content.Context.BIND_AUTO_CREATE;

/***
 * 实现代码 服务端下载代码
 */
public class SerViceUpLoadModel  implements SerViceUpLoadContract.Model {
    private DownloadService.DownloadBinder mDownloadBinder;
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mDownloadBinder = (DownloadService.DownloadBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mDownloadBinder = null;
        }
    };

    @Override
    public void bindModelService(Context context) {
        Intent intent = new Intent(context, DownloadService.class);
        context.startService(intent);
        context.bindService(intent, mConnection, BIND_AUTO_CREATE);//绑定服务
    }

    @Override
    public void startUpLoad(Context mContext, ProgressListener listener) {
        File file = new File(mContext.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "com.hbapp.apk");
        boolean isHave = SDCardHelper.fileIsHave(file.getPath());
        if (isHave) {
            ToastUtils.show(mContext,"存在了");
        } else {
            if (mDownloadBinder != null) {
                long downloadId = mDownloadBinder.startDownload(ContentManager.APK_URL);
                startCheckProgress(downloadId, mContext, listener);
            }
        }
    }

    //开始监听进度
    private void startCheckProgress(long downloadId, Context mContext, ProgressListener listener) {
        Observable
                .interval(100, 200, TimeUnit.MILLISECONDS, Schedulers.io())//无限轮询,准备查询进度,在io线程执行
                .filter(time -> mDownloadBinder != null)
                .map(i -> mDownloadBinder.getProgress(downloadId))//获得下载进度
                .takeUntil(progress -> progress >= 100)//返回true就停止了,当进度>=100就是下载完成了
                .distinct()//去重复
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ProgressObserver(mContext, listener));
    }

    //观察者
    private class ProgressObserver implements Observer {
        private Context mContext;
        private ProgressListener listener;

        public ProgressObserver(Context mContext, ProgressListener listener) {
            this.mContext = mContext;
            this.listener = listener;
        }

//        @Override
//        public void onSubscribe(Disposable d) {
//            listener.onSubscribeProgress(d);
//        }

        @Override
        public void onNext(Object o) {
            listener.onNextProgress((Integer) o);
        }

//        @Override
//        public void onNext(Integer progress) {
//            listener.onNextProgress(progress);
//        }

        @Override
        public void onSubscribe(Disposable d) {
            listener.onSubscribeProgress(d);
        }

        @Override
        public void onError(Throwable throwable) {
            listener.onErrorProgress(throwable);

        }

        @Override
        public void onComplete() {
            listener.onCompleteProgress();

        }
    }

}
