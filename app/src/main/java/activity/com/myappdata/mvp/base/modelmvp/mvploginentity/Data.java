package activity.com.myappdata.mvp.base.modelmvp.mvploginentity;

import java.io.Serializable;

/***
 * mvp  下的登路代码
 */
public class Data  implements Serializable{

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public Data() {
    }

    public Data(String username, String userpassword, String userpw, String userphone, String userstate, String carid) {
        this.username = username;
        this.userpassword = userpassword;
        this.userpw = userpw;
        this.userphone = userphone;
        this.userstate = userstate;
        this.carid = carid;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    public String getUserpw() {
        return userpw;
    }

    public void setUserpw(String userpw) {
        this.userpw = userpw;
    }

    public String getUserphone() {
        return userphone;
    }

    public void setUserphone(String userphone) {
        this.userphone = userphone;
    }

    public String getUserstate() {
        return userstate;
    }

    public void setUserstate(String userstate) {
        this.userstate = userstate;
    }

    public String getCarid() {
        return carid;
    }

    public void setCarid(String carid) {
        this.carid = carid;
    }

    private String username;
    private String userpassword;
    private String userpw;
    private String userphone;
    private String userstate;
    private String carid;
}
