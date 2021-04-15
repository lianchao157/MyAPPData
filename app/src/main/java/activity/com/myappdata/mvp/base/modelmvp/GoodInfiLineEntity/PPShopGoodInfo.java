package activity.com.myappdata.mvp.base.modelmvp.GoodInfiLineEntity;

import java.io.Serializable;


public class PPShopGoodInfo implements Serializable {


    public PPShopGoodInfo() {
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPpshopGoodInfoPhone() {
        return ppshopGoodInfoPhone;
    }

    public void setPpshopGoodInfoPhone(String ppshopGoodInfoPhone) {
        this.ppshopGoodInfoPhone = ppshopGoodInfoPhone;
    }

    public String getPpshopGoodInfoState() {
        return ppshopGoodInfoState;
    }

    public void setPpshopGoodInfoState(String ppshopGoodInfoState) {
        this.ppshopGoodInfoState = ppshopGoodInfoState;
    }

    public String getPpshopGoodInfono() {
        return ppshopGoodInfono;
    }

    public void setPpshopGoodInfono(String ppshopGoodInfono) {
        this.ppshopGoodInfono = ppshopGoodInfono;
    }

    public String getPpshopGoodInfoUser() {
        return ppshopGoodInfoUser;
    }

    public void setPpshopGoodInfoUser(String ppshopGoodInfoUser) {
        this.ppshopGoodInfoUser = ppshopGoodInfoUser;
    }

    public String getPpshopGoodImage() {
        return ppshopGoodImage;
    }

    public void setPpshopGoodImage(String ppshopGoodImage) {
        this.ppshopGoodImage = ppshopGoodImage;
    }

    public PPShopGoodInfo(String image, String ppshopGoodInfoPhone, String ppshopGoodInfoState, String ppshopGoodInfono, String ppshopGoodInfoUser, String ppshopGoodImage) {
        this.image = image;
        this.ppshopGoodInfoPhone = ppshopGoodInfoPhone;
        this.ppshopGoodInfoState = ppshopGoodInfoState;
        this.ppshopGoodInfono = ppshopGoodInfono;
        this.ppshopGoodInfoUser = ppshopGoodInfoUser;
        this.ppshopGoodImage = ppshopGoodImage;
    }

    private String image;

    private String ppshopGoodInfoPhone;

    private String ppshopGoodInfoState;

    private String ppshopGoodInfono;

    private String ppshopGoodInfoUser;

    private String ppshopGoodImage;
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


    private String ppshopGoodInfonoLocalcity;// 城市
    private String ppshopGoodInfonoData;//   时间
}
