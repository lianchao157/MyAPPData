package activity.com.myappdata.entity;

import java.util.List;

/**
 * Created by lianchao on 2021/1/7.
 */

public class PIctureBannerCode {
    public PIctureBannerCode() {
    }

    public String getMessage() {

        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<PictureBannerBase> getData() {
        return data;
    }

    public void setData(List<PictureBannerBase> data) {
        this.data = data;
    }

    private String  message;
    private  String code;
    private List<PictureBannerBase> data;
}
