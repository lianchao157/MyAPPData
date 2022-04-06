package activity.com.myappdata.activity.video;

import android.content.Context;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import activity.com.myappdata.R;
import activity.com.myappdata.application.MyApplication;
import activity.com.myappdata.listionnet.NetStateChangeObserver;
import activity.com.myappdata.listionnet.NetStateChangeReceiver;
import activity.com.myappdata.listionnet.NetworkType;
import activity.com.myappdata.retorfitutils.HttpEngine;
import activity.com.myappdata.util.ToastUtil;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/***
 * 视频activity
 *
 * 测试retorfit
 *
 * 可用
 */
public class VideobyActivity extends AppCompatActivity implements NetStateChangeObserver {
    private static final String TAG = "VideobyActivity";

    private RecyclerView recycle_byvideo;
    private TextView tvshow;
    private ImageView indicator_iamge;//  无网络的图
    private String gowei =null;
    private  List<String> listdata=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videoby);
        tvshow = (TextView) findViewById(R.id.tvshow);
        indicator_iamge = (ImageView) findViewById(R.id.indicator_iamge);

//        NetStateChangeReceiver.registerReceiver(this);

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                LoadDataAction();
//                Log.i(TAG, "开启线程了");
//            }
//        }).start();

//        if (gowei.equals("3")) {

//        }
//        for (int i=0; i<listdata.size();i++){
//            System.out.println(""+listdata.get(2));
//        }
        System.out.println(""+listdata.get(2));


    }

    /****
     * 使用当前方法注意后台的返回格式
     * 对象
     * List<对象>
     */
    private void LoadDataAction() {
//        final Handler mHandler = new Handler();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                final byte[] result = HttpUtils.get(url);
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        responseHandler.onSuccess(result);
//                    }
//                });
//            }
//        });


        //调用封装好的retrofit请求方法
        HttpEngine.getDuoBanTop1(new Observer<List<HotMenums>>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.i(TAG, "d" + d);

            }

            @Override
            public void onNext(List<HotMenums> hotMenums) {
//                Log.i("onNext==222=", hotMenums.getHotmenumsimage() + "---" + hotMenums.getHotmenumsname());
                for (int i = 0; i < hotMenums.size(); i++) {
                    Log.i("onNext==222=", hotMenums.get(i).getHotmenumsimage());
                }

                List<HotMenums> listdat = hotMenums;
                if (hotMenums != null || hotMenums.size() > 0) {
                    indicator_iamge.setVisibility(View.GONE);
                }

                tvshow.setText(hotMenums.get(0).getHotmenumsname());
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "e" + e);
                indicator_iamge.setVisibility(View.VISIBLE);
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "加载完成");
            }

        });
    }

    @Override
    public void onNetDisconnected() {
        ToastUtil.makeText(VideobyActivity.this, "网络");
    }

    @Override
    public void onNetConnected(NetworkType networkType) {

    }
}