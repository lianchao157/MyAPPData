package activity.com.myappdata.mvp.base.modelmvp.entity;

import android.net.sip.SipErrorCode;

import java.io.Serializable;

public  class Goodemail  implements Serializable{

    public String getName() {
        return name;
    }

    public Goodemail() {
    }

    public Goodemail(String name, String data) {
        this.name = name;
        this.data = data;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    private  String name; //内容
    private String  data; //  时间
}
