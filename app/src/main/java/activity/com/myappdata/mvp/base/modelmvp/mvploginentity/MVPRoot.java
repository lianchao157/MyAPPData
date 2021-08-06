package activity.com.myappdata.mvp.base.modelmvp.mvploginentity;

public class MVPRoot {

    private String code;
    private String msg;
    private Data data;

    public MVPRoot() {
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

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public MVPRoot(String code, String msg, Data data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
