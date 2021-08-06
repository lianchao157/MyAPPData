package activity.com.myappdata.entity;

import java.io.Serializable;

/***
 * 购物车的实体类代码
 */
public class ShopCarInfoenTity implements Serializable {

    public ShopCarInfoenTity() {
    }

    private Integer shopcarinfiid;//  购物车的id
    private Integer shopcarinfocoutt;//  数量
    private double shopcarinfomoney; //钱数
    private String shopcarinfoname; // 名称
    private String shopcarinfourl;//  url

    public Integer getShopcarinfiid() {
        return shopcarinfiid;
    }

    public void setShopcarinfiid(Integer shopcarinfiid) {
        this.shopcarinfiid = shopcarinfiid;
    }

    public Integer getShopcarinfocoutt() {
        return shopcarinfocoutt;
    }

    public void setShopcarinfocoutt(Integer shopcarinfocoutt) {
        this.shopcarinfocoutt = shopcarinfocoutt;
    }

    public double getShopcarinfomoney() {
        return shopcarinfomoney;
    }

    public void setShopcarinfomoney(double shopcarinfomoney) {
        this.shopcarinfomoney = shopcarinfomoney;
    }

    public String getShopcarinfoname() {
        return shopcarinfoname;
    }

    public void setShopcarinfoname(String shopcarinfoname) {
        this.shopcarinfoname = shopcarinfoname;
    }

    public String getShopcarinfourl() {
        return shopcarinfourl;
    }

    public void setShopcarinfourl(String shopcarinfourl) {
        this.shopcarinfourl = shopcarinfourl;
    }

    public ShopCarInfoenTity(Integer shopcarinfiid, Integer shopcarinfocoutt, double shopcarinfomoney, String shopcarinfoname, String shopcarinfourl) {
        this.shopcarinfiid = shopcarinfiid;
        this.shopcarinfocoutt = shopcarinfocoutt;
        this.shopcarinfomoney = shopcarinfomoney;
        this.shopcarinfoname = shopcarinfoname;
        this.shopcarinfourl = shopcarinfourl;
    }
}
