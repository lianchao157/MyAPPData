package activity.com.myappdata.entity;

import java.io.Serializable;

public class DataBean implements Serializable {


    public DataBean(String string, String textstr) {
        this.string = string;
        this.textstr = textstr;
    }

    public DataBean() {
    }

    public String getString() {
        return string;
    }

    @Override
    public String toString() {
        return "DataBean{" +
                "string='" + string + '\'' +
                ", textstr='" + textstr + '\'' +
                '}';
    }

    public void setString(String string) {
        this.string = string;
    }

    public String getTextstr() {
        return textstr;
    }

    public void setTextstr(String textstr) {
        this.textstr = textstr;
    }

    private String string;
    private String textstr;


}
