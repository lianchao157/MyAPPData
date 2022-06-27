package activity.com.myappdata.mvp.base.uimvp.fragment.hotfragmentpresenterimpl;

import java.util.List;

import activity.com.myappdata.mvp.base.modelmvp.mvploginentity.mvpuserinfolitit.UserinfoBywebData;
import activity.com.myappdata.mvp.base.presentermvp.IPlacePresenter;
import activity.com.myappdata.mvp.base.view.IProvinceCallbask;

/***
 * present 的接口实现类网
 * 网络请求代码
 */
public class HotfragmentpresentImple implements IPlacePresenter {
//    @Override
//    public void getProvinList() {
//
//    }

    @Override
    public Object getProvinList() {
        return null;
    }

    @Override
    public void registerViewCallback(IProvinceCallbask callback) {
//        callback.loadedData();
        callback.loadedDatabyuser((List<UserinfoBywebData>) getProvinList());

    }

    @Override
    public void unRegisterViewCallback(IProvinceCallbask callback) {

    }
}
