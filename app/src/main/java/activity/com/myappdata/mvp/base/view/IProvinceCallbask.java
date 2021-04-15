package activity.com.myappdata.mvp.base.view;

import java.util.List;

import activity.com.myappdata.mvp.base.modelmvp.entity.Province;

public interface IProvinceCallbask {
    void loadedData(List<Province> provinceList);
}
