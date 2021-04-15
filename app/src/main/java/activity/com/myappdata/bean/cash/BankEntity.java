package activity.com.myappdata.bean.cash;

import java.io.Serializable;

public class BankEntity implements Serializable {
    private String  bondstr;

    public BankEntity() {
    }

    @Override
    public String toString() {
        return "BankEntity{" +
                "bondstr='" + bondstr + '\'' +
                ", usuallystr='" + usuallystr + '\'' +
                '}';
    }

    public BankEntity(String bondstr, String usuallystr) {
        this.bondstr = bondstr;
        this.usuallystr = usuallystr;
    }

    public String getBondstr() {
        return bondstr;
    }

    public void setBondstr(String bondstr) {
        this.bondstr = bondstr;
    }

    public String getUsuallystr() {
        return usuallystr;
    }

    public void setUsuallystr(String usuallystr) {
        this.usuallystr = usuallystr;
    }

    private  String  usuallystr;
}
