package activity.com.myappdata.mvp.base.modelmvp.entity;

import java.io.Serializable;

/***
 * 实现代码登陆保存的方法
 *
 * mvp  代码
 */
public class UserInfoByLogin implements Serializable {

    public String getUsername() {
        return Username;
    }

    public UserInfoByLogin(String username) {
        Username = username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public UserInfoByLogin() {
    }

    public void setUserpassword(String userpassword) {
        Userpassword = userpassword;
    }

    public String getUserpassword() {
        return Userpassword;
    }

    private String Username;
    private String Userpassword;
}
