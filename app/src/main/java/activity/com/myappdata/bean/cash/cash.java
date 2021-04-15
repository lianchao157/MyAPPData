package activity.com.myappdata.bean.cash;

import java.io.Serializable;
//

//WD/网点1|PS/00001|ZZ/ZH000001ZZ,ZH000002ZZ|XJ/100,10000;50,20000|ZK/存储,100;汇票凭证,50|DY/DY202100001,DY202100002|PS/00002|ZZ/ZH000003ZZ,ZH000004ZZ|XJ/100,10000;50,20000|ZK/存储,100;汇票凭证,50|DY/DY202100003,DY202100004|WD/网点2|PS/00001|ZZ/ZH000001ZZ,ZH000002ZZ|XJ/100,10000;50,20000|ZK/存储,100;汇票凭证,50|DY/DY202100001,DY202100002|PS/00002|ZZ/ZH000003ZZ,ZH000004ZZ|XJ/100,10000;50,20000|ZK/存储,100;汇票凭证,50|DY/DY202100003,DY202100004
public class cash  implements Serializable{
    public cash(String wangdian, String wangdianps, String wangdianxianjin, String wangdianzhngkong) {
        this.wangdian = wangdian;
        this.wangdianps = wangdianps;
        this.wangdianxianjin = wangdianxianjin;
        this.wangdianzhngkong = wangdianzhngkong;
    }

    public cash() {
    }

    private String  wangdian;
    private String  wangdianps;
    private String  wangdianxianjin;
    private String  wangdianzhngkong;

    @Override
    public String toString() {
        return "cash{" +
                "wangdian='" + wangdian + '\'' +
                ", wangdianps='" + wangdianps + '\'' +
                ", wangdianxianjin='" + wangdianxianjin + '\'' +
                ", wangdianzhngkong='" + wangdianzhngkong + '\'' +
                '}';
    }

    public String getWangdian() {
        return wangdian;
    }

    public void setWangdian(String wangdian) {
        this.wangdian = wangdian;
    }

    public String getWangdianps() {
        return wangdianps;
    }

    public void setWangdianps(String wangdianps) {
        this.wangdianps = wangdianps;
    }

    public String getWangdianxianjin() {
        return wangdianxianjin;
    }

    public void setWangdianxianjin(String wangdianxianjin) {
        this.wangdianxianjin = wangdianxianjin;
    }

    public String getWangdianzhngkong() {
        return wangdianzhngkong;
    }

    public void setWangdianzhngkong(String wangdianzhngkong) {
        this.wangdianzhngkong = wangdianzhngkong;
    }
}
