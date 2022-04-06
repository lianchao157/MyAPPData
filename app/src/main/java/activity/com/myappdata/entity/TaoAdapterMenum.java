package activity.com.myappdata.entity;

import java.io.Serializable;

/**
 * Created by lianchao on 2021/1/8.
 */

public class TaoAdapterMenum implements Serializable {
    public TaoAdapterMenum(String strname, String striamge) {
        this.strname = strname;
        this.striamge = striamge;
    }

    private String strname;

    private String striamge;
    public String getStrname() {
        return strname;
    }

    @Override
    public String toString() {
        return "TaoAdapterMenum{" +
                "strname='" + strname + '\'' +
                ", striamge='" + striamge + '\'' +
                '}';
    }

    public void setStrname(String strname) {
        this.strname = strname;
    }

    public String getStriamge() {
        return striamge;
    }

    public void setStriamge(String striamge) {
        this.striamge = striamge;
    }

    public TaoAdapterMenum() {
    }


}
