package activity.com.myappdata.mvp.base.presentermvp.IPlacePresenterImpl;

import android.util.Base64;
import android.util.Log;

import activity.com.myappdata.mvp.base.modelmvp.Api;
import activity.com.myappdata.mvp.base.presentermvp.IPlacePresenter;
import activity.com.myappdata.mvp.base.utilsmvp.Constants;
import activity.com.myappdata.mvp.base.utilsmvp.RetrofitFactory;
import activity.com.myappdata.mvp.base.view.IProvinceCallbask;
//import io.reactivex.schedulers.Schedulers;

public class PlacePresenterImpl implements IPlacePresenter {
    private static final String TAG = "PlacePresenterImpl";

    @Override
    public void getProvinList() {
        String url = Constants.BASE_URL;
//        RetrofitFactory.getRetrofit().create(Api.class).getVideoContent(url)
//                .subscribeOn(Schedulers.io())
//                .map(videoContentBean -> {
//                    VideoContentBean.DataBean.VideoListBean videoList = videoContentBean.getData().getVideo_list();
//                    if (videoList.getVideo_3() != null) {
//                        String base64 = videoList.getVideo_3().getMain_url();
//                        String url1 = (new String(Base64.decode(base64.getBytes(), Base64.DEFAULT)));
//                        Log.d(TAG, "getVideoUrls: " + url1);
//                        return url1;
//                    }
//
//                    if (videoList.getVideo_2() != null) {
//                        String base64 = videoList.getVideo_2().getMain_url();
//                        String url1 = (new String(Base64.decode(base64.getBytes(), Base64.DEFAULT)));
//                        Log.d(TAG, "getVideoUrls: " + url1);
//                        return url1;
//                    }
//
//                    if (videoList.getVideo_1() != null) {
//                        String base64 = videoList.getVideo_1().getMain_url();
//                        String url1 = (new String(Base64.decode(base64.getBytes(), Base64.DEFAULT)));
//                        Log.d(TAG, "getVideoUrls: " + url1);
//                        return url1;
//                    }
//                    return null;
//                })
//                .observeOn(AndroidSchedulers.mainThread())
//                .as(view.bindAutoDispose())
//                .subscribe(s -> {
//                    view.onSetVideoPlay(s);
//                    view.onHideLoading();
//                }, throwable -> {
//                    view.onShowNetError();
//                    view.onHideLoading();
//                    ErrorAction.print(throwable);
//                });
    }

    @Override
    public void registerViewCallback(IProvinceCallbask callback) {

    }

    @Override
    public void unRegisterViewCallback(IProvinceCallbask callback) {

    }
//    @Override
//    public void getProvinList() {
//
//    }
//
//    @Override
//    public void registerViewCallback(IProvinceCallbask callback) {
//
//    }
//
//    @Override
//    public void unRegisterViewCallback(IProvinceCallbask callback) {

//    }
}
