package activity.com.myappdata.mvp.base.presentermvp;

import activity.com.myappdata.mvp.base.view.IProvinceCallbask;

/**
 * presents   工具类代码
 * 加载数据和注册 解除注册
 */
public interface IPlacePresenter {
    void getProvinList();

    void registerViewCallback(IProvinceCallbask callback);

    void unRegisterViewCallback(IProvinceCallbask callback);
}
