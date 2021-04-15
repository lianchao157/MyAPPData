package activity.com.myappdata.bean;

import java.util.List;

public class JsonRootBeanByfindfragmettitle {

    public JsonRootBeanByfindfragmettitle() {
    }

    public JsonRootBeanByfindfragmettitle(List<GoodsConfigBean> data) {
        this.data = data;

    }

    private List<GoodsConfigBean> data;
    public void setData(List<GoodsConfigBean> data) {
        this.data = data;
    }
    public List<GoodsConfigBean> getData() {
        return data;
    }
}
