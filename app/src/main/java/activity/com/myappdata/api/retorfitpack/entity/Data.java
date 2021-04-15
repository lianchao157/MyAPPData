package activity.com.myappdata.api.retorfitpack.entity;

import java.io.Serializable;

public class Data implements Serializable {
    public Data(String usertype, String usertest, String usertel, String username) {
        this.usertype = usertype;
        this.usertest = usertest;
        this.usertel = usertel;
        this.username = username;
    }

    private String usertype;


    private String usertest;


    private String usertel;


    private String username;


    public void setUsertype(String usertype){

        this.usertype = usertype;

    }

    public String getUsertype(){

        return this.usertype;

    }

    public void setUsertest(String usertest){

        this.usertest = usertest;

    }

    public String getUsertest(){

        return this.usertest;

    }

    public void setUsertel(String usertel){

        this.usertel = usertel;

    }

    public String getUsertel(){

        return this.usertel;

    }

    public void setUsername(String username){

        this.username = username;

    }

    public String getUsername(){

        return this.username;

    }
}
