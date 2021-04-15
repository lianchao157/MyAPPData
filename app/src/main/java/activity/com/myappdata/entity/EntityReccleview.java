package activity.com.myappdata.entity;

import java.io.Serializable;

/***
 * 实体类代码
 */
public class EntityReccleview implements Serializable {

    private String imagurl;//  图片url
    private String iamgetext; /// 价格
    private String imagetextrae;  //  价格打折
    private int prorate;  //  进度条

    public EntityReccleview() {
    }

    public String getImagurl() {
        return imagurl;
    }

    @Override
    public String toString() {
        return "EntityReccleview{" +
                "imagurl='" + imagurl + '\'' +
                ", iamgetext='" + iamgetext + '\'' +
                ", imagetextrae='" + imagetextrae + '\'' +
                ", prorate=" + prorate +
                ", strrate='" + strrate + '\'' +
                '}';
    }

    public void setImagurl(String imagurl) {
        this.imagurl = imagurl;
    }

    public String getIamgetext() {
        return iamgetext;
    }

    public void setIamgetext(String iamgetext) {
        this.iamgetext = iamgetext;
    }

    public String getImagetextrae() {
        return imagetextrae;
    }

    public void setImagetextrae(String imagetextrae) {
        this.imagetextrae = imagetextrae;
    }

    public int getProrate() {
        return prorate;
    }

    public void setProrate(int prorate) {
        this.prorate = prorate;
    }

    public String getStrrate() {
        return strrate;
    }

    public void setStrrate(String strrate) {
        this.strrate = strrate;
    }

    public EntityReccleview(String imagurl, String iamgetext, String imagetextrae, int prorate, String strrate) {
        this.imagurl = imagurl;
        this.iamgetext = iamgetext;
        this.imagetextrae = imagetextrae;
        this.prorate = prorate;


        this.strrate = strrate;
    }

    private String strrate;// 已经销售
}
