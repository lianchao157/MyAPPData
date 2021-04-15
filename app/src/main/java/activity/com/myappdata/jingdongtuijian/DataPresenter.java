package activity.com.myappdata.jingdongtuijian;

import java.util.List;

import activity.com.myappdata.entity.Beandata;

public interface DataPresenter {
    void success(List<Beandata> list);
    void eroor();
}
