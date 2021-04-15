package activity.com.myappdata.mvp.base.modelmvp.GoodInfiLineEntity;

import java.io.Serializable;


/***
 * 2021.3.22
 * 功能   接受到后天传递的城市时间的数据代码
 */
public class GoodInfoLineInfoBase implements Serializable {
    public String getPpshopGoodInfonoLocalcity() {
        return ppshopGoodInfonoLocalcity;
    }

    public void setPpshopGoodInfonoLocalcity(String ppshopGoodInfonoLocalcity) {
        this.ppshopGoodInfonoLocalcity = ppshopGoodInfonoLocalcity;
    }

    public String getPpshopGoodInfonoData() {
        return ppshopGoodInfonoData;
    }

    public void setPpshopGoodInfonoData(String ppshopGoodInfonoData) {
        this.ppshopGoodInfonoData = ppshopGoodInfonoData;
    }

    public GoodInfoLineInfoBase(String ppshopGoodInfonoLocalcity, String ppshopGoodInfonoData) {
        this.ppshopGoodInfonoLocalcity = ppshopGoodInfonoLocalcity;
        this.ppshopGoodInfonoData = ppshopGoodInfonoData;
    }

    public GoodInfoLineInfoBase() {
    }

    private String ppshopGoodInfonoLocalcity;// 城市
    private String ppshopGoodInfonoData;//   时间
}
