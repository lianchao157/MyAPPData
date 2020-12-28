package activity.com.myappdata.entity;

import java.io.Serializable;

/**
 * Created by lianchao on 2020/12/25.
 */

public class GridvieMenumEntity implements Serializable {
    public GridvieMenumEntity(String textstr, String imagstr) {
        this.textstr = textstr;
        this.imagstr = imagstr;
    }

    public GridvieMenumEntity() {
    }

    private String imagstr;
    private  String  textstr;
    @Override
    public String toString() {
        return "GridvieMenumEntity{" +
                "imagstr='" + imagstr + '\'' +
                ", textstr='" + textstr + '\'' +
                '}';
    }

    public String getImagstr() {
        return imagstr;
    }

    public void setImagstr(String imagstr) {
        this.imagstr = imagstr;
    }

    public String getTextstr() {
        return textstr;
    }

    public void setTextstr(String textstr) {
        this.textstr = textstr;
    }


}
