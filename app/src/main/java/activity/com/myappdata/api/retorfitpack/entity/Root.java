package activity.com.myappdata.api.retorfitpack.entity;

import java.util.List;

public class Root {
    private int code;


    private String msg;


    private List<Data> data ;


    public void setCode(int code){

        this.code = code;

    }

    public int getCode(){

        return this.code;

    }

    public void setMsg(String msg){

        this.msg = msg;

    }

    public String getMsg(){

        return this.msg;

    }

    public void setData(List<Data> data){

        this.data = data;

    }

    public List<Data> getData(){

        return this.data;

    }
}
