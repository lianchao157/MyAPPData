package activity.com.myappdata.mvp.base.presentermvp;

import activity.com.myappdata.mvp.base.view.IProvinceCallbask;

public interface GetVideoListshow <T>{

    public T getProvinList();

    void registerViewCallback(IProvinceCallbask callback);

    void unRegisterViewCallback(IProvinceCallbask callback);
}
