package activity.com.myappdata.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import activity.com.myappdata.R;

import activity.com.myappdata.api.AndroidScheduler;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.http.Path;

/***
 * rx  响应式编程
 * 代码
 */
public class RxTestActivity extends AppCompatActivity {
private ImageView image;
    public final String Path = "https://image.baidu.com/search/detail?ct=503316480&z=&tn=baiduimagedetail&ipn=d&word=%E7%8E%8B%E7%8E%89%E8%90%8C&step_word=&ie=utf-8&in=&cl=2&lm=-1&st=-1&hd=&latest=&copyright=&cs=4195588078,1879933216&os=2243500546,2238398578&simid=0,0&pn=0&rn=1&di=18150&ln=158&fr=&fmq=1626519741328_R&ic=&s=undefined&se=&sme=&tab=0&width=&height=&face=undefined&is=0,0&istype=2&ist=&jit=&bdtype=0&spn=0&pi=0&gsm=0&objurl=https%3A%2F%2Fgimg2.baidu.com%2Fimage_search%2Fsrc%3Dhttp%253A%252F%252Fy.gtimg.cn%252Fmusic%252Fphoto_new%252FT023R750x750M000003vzpwB4TNCbB.jpg%253Fmax_age%253D2592000%26refer%3Dhttp%253A%252F%252Fy.gtimg.cn%26app%3D2002%26size%3Df9999%2C10000%26q%3Da80%26n%3D0%26g%3D0n%26fmt%3Djpeg%3Fsec%3D1629111742%26t%3D407fe332871ad1146d64ae74c82957cf&rpstart=0&rpnum=0&adpicid=0&nojc=undefined";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_test);
        image= (ImageView) findViewById(R.id.image);
        LoadDataObserverable();
    }

    private void LoadDataObserverable() {
//起点
        Observable.just(Path)
                .map(new Function<String, Bitmap>() {
                    @Override
                    public Bitmap apply(String s) throws Exception {
                        try {
                            URL url = new URL(Path);
                            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                            httpURLConnection.setConnectTimeout(5000);
                            int resultcode = httpURLConnection.getResponseCode();
                            if (resultcode == HttpURLConnection.HTTP_OK) {
                                InputStream inputStream = httpURLConnection.getInputStream();
                                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                                return bitmap;
                            }
                        } catch (Exception e) {

                        }
                        return null;
                    }
                })
//                // 给上面的图片下载分配异步线程  图片下载操作

                .subscribeOn(Schedulers.io())
//                 终点分配Android主线程
                .observeOn(AndroidScheduler.mainThread())

                .subscribe(new Observer<Bitmap>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Bitmap s) {
                        image.setImageBitmap(s);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                    //  订阅成功 观察者模式  关联起点和重点
                });
    }
}
