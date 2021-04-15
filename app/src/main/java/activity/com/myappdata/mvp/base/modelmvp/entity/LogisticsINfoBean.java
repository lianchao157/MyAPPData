package activity.com.myappdata.mvp.base.modelmvp.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 20210312   郝
 * 实体类 ： 快递线路代码
 */
public class LogisticsINfoBean implements Serializable {
    private String type;//  返回数据类型
    private String title;//  返回的标题头部
    private String emailgoodstate;

    public LogisticsINfoBean() {
    }

    private String emaigoodscompany;
    private String emaaigoodno;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEmailgoodstate() {
        return emailgoodstate;
    }

    public void setEmailgoodstate(String emailgoodstate) {
        this.emailgoodstate = emailgoodstate;
    }

    public String getEmaigoodscompany() {
        return emaigoodscompany;
    }

    public void setEmaigoodscompany(String emaigoodscompany) {
        this.emaigoodscompany = emaigoodscompany;
    }

    public String getEmaaigoodno() {
        return emaaigoodno;
    }

    public void setEmaaigoodno(String emaaigoodno) {
        this.emaaigoodno = emaaigoodno;
    }

    public String getEmailno() {
        return emailno;
    }

    public void setEmailno(String emailno) {
        this.emailno = emailno;
    }

    public List<Goodemail> getData() {
        return data;
    }

    public void setData(List<Goodemail> data) {
        this.data = data;
    }

    public LogisticsINfoBean(String type, String title, String emailgoodstate, String emaigoodscompany, String emaaigoodno, String emailno, List<Goodemail> data) {
        this.type = type;
        this.title = title;
        this.emailgoodstate = emailgoodstate;
        this.emaigoodscompany = emaigoodscompany;
        this.emaaigoodno = emaaigoodno;
        this.emailno = emailno;
        this.data = data;
    }

    private String emailno;
    private List<Goodemail> data;
}
