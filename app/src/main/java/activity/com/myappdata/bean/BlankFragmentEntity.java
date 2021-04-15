package activity.com.myappdata.bean;

import java.io.Serializable;

public class BlankFragmentEntity implements Serializable {

    public BlankFragmentEntity(String imagestr, String textinfo, String money, String city, String onclick, String image_fu, String yuliu, String type) {
        this.imagestr = imagestr;
        this.textinfo = textinfo;
        this.money = money;
        this.city = city;
        this.onclick = onclick;
        this.image_fu = image_fu;
        this.yuliu = yuliu;
        this.type = type;
    }

    public BlankFragmentEntity() {
    }

    public String getImagestr() {
        return imagestr;
    }

    public void setImagestr(String imagestr) {
        this.imagestr = imagestr;
    }

    public String getTextinfo() {
        return textinfo;
    }

    public void setTextinfo(String textinfo) {
        this.textinfo = textinfo;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getOnclick() {
        return onclick;
    }

    public void setOnclick(String onclick) {
        this.onclick = onclick;
    }

    public String getImage_fu() {
        return image_fu;
    }

    public void setImage_fu(String image_fu) {
        this.image_fu = image_fu;
    }

    public String getYuliu() {
        return yuliu;
    }

    public void setYuliu(String yuliu) {
        this.yuliu = yuliu;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String imagestr;  //顶部图片
    private String textinfo; //  当前商品的文字信息
    private String money;   //  商品的钱数
    private String city;   ///   城市
    private String onclick; ////  点家进入
    private String image_fu;  //    满400 减20 的图片
    private String yuliu;   //   下划线的钱数
    private String type;   // 会有分类

}
