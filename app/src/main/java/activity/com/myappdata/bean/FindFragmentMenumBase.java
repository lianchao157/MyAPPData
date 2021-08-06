package activity.com.myappdata.bean;

import com.google.gson.annotations.Expose;

import java.util.List;

/***
 * findfragmng  顶部菜单数据
 */
public class FindFragmentMenumBase {
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "FindFragmentMenumBase{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    public String getMsg() {
        return msg;
    }


    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<FindFragmetnMenuTitleEntity> getData() {
        return data;
    }

    public void setData(List<FindFragmetnMenuTitleEntity> data) {
        this.data = data;
    }

    private String code;
    private String msg;
    private List<FindFragmetnMenuTitleEntity> data;
}
