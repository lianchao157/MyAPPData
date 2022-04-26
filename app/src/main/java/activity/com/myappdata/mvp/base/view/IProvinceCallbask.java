package activity.com.myappdata.mvp.base.view;

import java.util.List;

import activity.com.myappdata.mvp.base.modelmvp.entity.Province;
import activity.com.myappdata.mvp.base.modelmvp.mvploginentity.mvpuserinfolitit.UserinfoBywebData;

public interface IProvinceCallbask {
    void loadedDatabyuser(List<UserinfoBywebData> provinceList);
}
