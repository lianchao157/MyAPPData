package activity.com.myappdata.mvp.base.modelmvp.GoodInfiLineEntity;


import java.util.List;

public class GoodinfoLineRoot {

    private String code;
    private String msg;
    private List<PPShopGoodInfo> data;
    public GoodinfoLineRoot() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<PPShopGoodInfo> getData() {
        return data;
    }

    public void setData(List<PPShopGoodInfo> data) {
        this.data = data;
    }

    public GoodinfoLineRoot(String code, String msg, List<PPShopGoodInfo> data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
