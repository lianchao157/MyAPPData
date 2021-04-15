package activity.com.myappdata.entity;

import java.io.Serializable;

/**
 * Created by lianchao on 2021/1/7.
 */

public class PictureBannerBase implements Serializable {
    @Override
    public String toString() {
        return "HomeEntity{" +
                "homedataid=" + homedataid +
                ", homeimage='" + homeimage + '\'' +
                ", hometext='" + hometext + '\'' +
                ", homeyuliu='" + homeyuliu + '\'' +
                ", homeyuliu2='" + homeyuliu2 + '\'' +
                '}';
    }

    private int homedataid;
    private String homeimage;
    private String hometext;
    private String homeyuliu;
    private String homeyuliu2;

    public PictureBannerBase() {
    }

    public int getHomedataid() {
        return homedataid;
    }

    public void setHomedataid(int homedataid) {
        this.homedataid = homedataid;
    }

    public String getHomeimage() {
        return homeimage;
    }

    public void setHomeimage(String homeimage) {
        this.homeimage = homeimage;
    }

    public String getHometext() {
        return hometext;
    }

    public void setHometext(String hometext) {
        this.hometext = hometext;
    }

    public String getHomeyuliu() {
        return homeyuliu;
    }

    public void setHomeyuliu(String homeyuliu) {
        this.homeyuliu = homeyuliu;
    }

    public String getHomeyuliu2() {
        return homeyuliu2;
    }

    public void setHomeyuliu2(String homeyuliu2) {
        this.homeyuliu2 = homeyuliu2;
    }
}
