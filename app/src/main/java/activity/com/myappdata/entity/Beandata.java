package activity.com.myappdata.entity;

import java.io.Serializable;
import java.util.List;

public class Beandata  implements Serializable{
    public Beandata() {
    }

    public Beandata(String code, String message, DataBean data) {
        this.code = code;
        this.message = message;
        Data = data;

    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return Data;
    }

    public void setData(DataBean data) {
        Data = data;
    }

    private  String code;
    private String  message;
    private  DataBean Data;
}
