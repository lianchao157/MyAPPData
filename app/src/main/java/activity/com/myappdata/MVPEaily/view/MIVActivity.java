package activity.com.myappdata.MVPEaily.view;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.lang.ref.WeakReference;

import activity.com.myappdata.MVPEaily.present.IPresenter;
import activity.com.myappdata.MVPEaily.present.Presenter;
import activity.com.myappdata.R;

/***
 * mvp 代码  通过handler   传递数据
 */
public class MIVActivity extends AppCompatActivity implements IView {

    private Button mBtnShowToast;
    private TextView mText, mvpmodel_showtext;
    private MyHandler mHandler = new MyHandler(MIVActivity.this);
    private IPresenter mPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_miv);
        //实例化Presenter，并将实现了IView接口的类传入进去
        mPresenter = new Presenter(MIVActivity.this);
        //通过Presenter来实现业务逻辑操作，View层只负责UI相关操作
        mPresenter.loadData();
        System.out.print("");
//            }
//        });

        mvpmodel_showtext = (TextView) findViewById(R.id.mvpmodel_showtext);
    }

    @Override
    public void showLoadingProgress(final String message) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
//                mText.setText(message);
            }
        });
    }

    @Override
    public void showData(final String text) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
//                mText.setText(text);
                System.out.print(text);
                mvpmodel_showtext.setText(text);
            }
        });
    }

    private class MyHandler extends Handler {

        //弱引用，防止内存泄露
        WeakReference<MIVActivity> weakReference;

        public MyHandler(MIVActivity activity) {
            this.weakReference = new WeakReference<MIVActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    weakReference.get().mText.setText(msg.what);
//                    mvpmodel_showtext.setText(msg.what);
                    break;
            }
        }
    }
}
