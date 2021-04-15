package activity.com.myappdata.api;

import android.net.sip.SipErrorCode;

import java.io.Serializable;

public class Book implements Serializable{
    public Book(String name, String nameid) {
        this.name = name;
        this.nameid = nameid;
    }

    private String  name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameid() {
        return nameid;
    }

    public void setNameid(String nameid) {
        this.nameid = nameid;
    }

    public Book() {
    }

    private String  nameid;

}
